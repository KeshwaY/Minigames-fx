package projekt.server.client;

import projekt.server.client.abstraction.ConnectionManager;
import projekt.server.dto.ActionDto;
import projekt.server.dto.ClientDto;
import projekt.server.dto.LobbiesDto;
import projekt.server.dto.LobbyDto;
import projekt.server.game.GameType;
import projekt.server.game.abstraction.Lobby;

import java.io.IOException;
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

    private ClientDto identity;

    public Client(
            ConnectionManager connectionManager,
            BiFunction<Client, GameType, Lobby> createLobbyFunction,
            BiFunction<Integer, Client, Lobby> joinLobbyFunction,
            ConcurrentHashMap<Integer, Lobby> lobbies
    ) {
        this.connectionManager = connectionManager;
        this.createLobbyFunction = createLobbyFunction;
        this.joinLobbyFunction = joinLobbyFunction;
        this.lobbies = lobbies;
        isRunning = true;
    }

    @Override
    public void run() {
        try {
            createIdentity();
            main: while (true) {
                ActionDto actionDto = getActionDto();
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
                        switch (actionDto.getActionType()) {
                            case START_GAME:
                                lobby.startGame();
                                break;
                            case CHANGE_DESCRIPTION:
                                String newDescription = connectionManager.getInput();
                                lobby.changeDescription(newDescription);
                                break;
                            case QUIT:
                                lobby.delete();
                                break;
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createIdentity() throws IOException, ClassNotFoundException {
        identity = connectionManager.getDto();
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

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public ClientDto getIdentity() {
        return identity;
    }

}
