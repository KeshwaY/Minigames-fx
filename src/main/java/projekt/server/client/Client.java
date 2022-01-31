package projekt.server.client;

import projekt.database.DataBase;
import projekt.server.ActionType;
import projekt.server.client.abstraction.ConnectionManager;
import projekt.server.dto.*;
import projekt.server.game.GameType;
import projekt.server.game.abstraction.Game;
import projekt.server.game.abstraction.Lobby;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Client implements Runnable {

    private final ConnectionManager connectionManager;
    private boolean isRunning;

    private final BiFunction<Client, GameType, Lobby> createLobbyFunction;
    private final BiFunction<Integer, Client, Lobby> joinLobbyFunction;

    private final ConcurrentHashMap<Integer, Lobby> lobbies;
    private Lobby lobby;
    private Game game;

    private ClientDto identity;

    private final DataBase dataBase;
    private int clientId;

    public Client(
            ConnectionManager connectionManager,
            BiFunction<Client, GameType, Lobby> createLobbyFunction,
            BiFunction<Integer, Client, Lobby> joinLobbyFunction,
            ConcurrentHashMap<Integer, Lobby> lobbies,
            DataBase database
    ) {
        this.connectionManager = connectionManager;
        this.createLobbyFunction = createLobbyFunction;
        this.joinLobbyFunction = joinLobbyFunction;
        this.lobbies = lobbies;
        this.dataBase = database;
        isRunning = true;
    }

    @Override
    public void run() {
        try {   
            ActionDto actionDto = getActionDto();
            if (actionDto.getActionType().equals(ActionType.REGISTER_PLAYER)) {
                registerIdentity();
            } else {
                createIdentity();
            }
            main: while (true) {
                actionDto = getActionDto();
                System.out.println(actionDto.getActionType());
                if (lobby == null) {
                    switch (actionDto.getActionType()) {
                        case QUIT:
                            break main;
                        case GET_LOBBIES:
                            connectionManager.sendDto(getLobbies());
                            break;
                        case CREATE_LOBBY:
                            lobby = createLobbyFunction.apply(
                                    this,
                                    GameType.COIN_FLIP
                            );
                            System.out.println(lobby);
                            break;
                        case JOIN_LOBBY:
                            Integer lobbyId = Integer.parseInt(connectionManager.getInput());
                            if (lobbies.get(lobbyId).getCurrentSize() == lobbies.get(lobbyId).getMaxSize()) {
                                break;
                            }
                            lobby = joinLobbyFunction.apply(
                                    lobbyId,
                                    this
                            );
                            break;
                        default:
                            System.out.println("Invalid action");
                    }
                } else {
                    if (lobby.getOwner() == this) {
                        if (game == null) {
                            game = lobby.getGame();
                        }
                        switch (actionDto.getActionType()) {
                            case START_GAME -> lobby.startGame();
                            case DRAW -> {
                                if (!lobby.isStart()) {
                                    lobby.startGame();
                                }
                                while(!game.isPlaying()) {
                                    Thread.onSpinWait();
                                }
                                game.draw();
                            }
                            case CHANGE_DESCRIPTION -> {
                                String newDescription = connectionManager.getInput();
                                lobby.changeDescription(newDescription);
                            }
                            case QUIT -> lobby.delete();
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createIdentity() throws IOException, ClassNotFoundException {
        ClientDto dto = connectionManager.getDto();
        try {
            ResultSet resultSet = dataBase.getPlayer(dto.getUsername());
            resultSet.next();
            dto.setId(resultSet.getInt("ID"));
            String password = resultSet.getString("password");
            if (!password.equals(dto.getPassword())) {
                connectionManager.sendDto(new LoginResponseDto(false));
                connectionManager.close();
                return;
            }
        } catch (SQLException e) {
            connectionManager.sendDto(new LoginResponseDto(false));
            connectionManager.close();
            return;
        }
        identity = dto;
        System.out.println(identity.getId());
        connectionManager.sendDto(new LoginResponseDto(true));
    }

    private void registerIdentity() throws IOException, ClassNotFoundException {
        ClientDto dto = connectionManager.getDto();
        try {
            if (dataBase.getPlayer(dto.getUsername()).getString("username").equals(dto.getUsername())) {
                connectionManager.sendDto(new RegisterResponseDto(false));
                connectionManager.close();
            }
        } catch (SQLException e) {
            try {
                dataBase.addPlayer(dto.getUsername(), dto.getPassword());
            } catch (SQLException ex) {
                connectionManager.sendDto(new RegisterResponseDto(false));
                connectionManager.close();
            }
        }
        identity = dto;
        connectionManager.sendDto(new RegisterResponseDto(true));
    }

    private ActionDto getActionDto() throws IOException, ClassNotFoundException {
        return connectionManager.getDto();
    }

    private LobbiesDto getLobbies() {
        LobbiesDto lobbiesDto = new LobbiesDto();
        Map<Integer, LobbyDto> lobbyDtoMap = new HashMap<>();

        for (Map.Entry<Integer, Lobby> entry : lobbies.entrySet()) {
            Lobby lobby = entry.getValue();
            LobbyDto lobbyDto = new LobbyDto();
            lobbyDto.setId(entry.getKey());
            lobbyDto.setOwner(lobby.getOwner().getIdentity());
            lobbyDto.setGameType(lobby.getGameType());
            lobbyDto.setDescription(lobby.getDescription());
            lobbyDto.setCurrentSize(lobby.getCurrentSize());
            lobbyDto.setMaxSize(lobby.getMaxSize());
            lobbyDtoMap.put(entry.getKey(), lobbyDto);

        }
        lobbiesDto.setLobbies(lobbyDtoMap);
        return lobbiesDto;
    }

    public void sendGameStatusDto(GameStatusDto gameStatusDto) throws IOException {
        gameStatusDto.setPlayer(lobby.getOwner() == this ? 1 : 2);
        connectionManager.sendDto(gameStatusDto);
    }

    public void close() throws IOException {
        connectionManager.close();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public ClientDto getIdentity() {
        return identity;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
