package projekt.client;

import projekt.server.ActionType;
import projekt.server.dto.*;
import projekt.server.game.GameType;
import projekt.server.game.abstraction.Lobby;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class Client {

    private final Socket socket;
    private final DataOutputStream dataOutputStream;
    private final DataInputStream dataInputStream;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;

    private String username;

    public Client(String address, int port) throws IOException {
        this.socket = new Socket(address, port);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public LoginResponseDto login(String username, String password) throws IOException {
        sendActionDto(ActionType.LOGIN_PLAYER);
        ClientDto clientDto = new ClientDto(username, password);
        objectOutputStream.writeObject(clientDto);
        try {
            this.username = username;
            return (LoginResponseDto) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            return new LoginResponseDto(false);
        }
    }

    public RegisterResponseDto register(String username, String password) throws IOException {
        sendActionDto(ActionType.REGISTER_PLAYER);
        ClientDto clientDto = new ClientDto(username, password);
        objectOutputStream.writeObject(clientDto);
        try {
            return (RegisterResponseDto) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            return new RegisterResponseDto(false);
        }
    }

    public LobbiesDto getLobbies() throws IOException {
        sendActionDto(ActionType.GET_LOBBIES);
        try {
            return (LobbiesDto) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void joinGame(int id) throws IOException {
        sendActionDto(ActionType.JOIN_LOBBY);
        dataOutputStream.writeUTF(String.valueOf(id));
    }

    public void createGame(String name, GameType gameType) throws IOException {
        sendActionDto(ActionType.CREATE_LOBBY);
    }

    public void sendActionDto(ActionType actionType) throws IOException {
        ActionDto actionDto = new ActionDto();
        actionDto.setActionType(actionType);
        objectOutputStream.writeObject(actionDto);
    }

    public void relog() throws IOException {
        socket.close();
    }

    public String getUsername() {
        return username;
    }
//    public void test() throws IOException {
//        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//        Scanner scanner = new Scanner(System.in);
//        ClientDto clientDto = new ClientDto(scanner.nextLine());
//        objectOutputStream.writeObject(clientDto);
//        main: while (true) {
//            ActionDto actionDto = new ActionDto();
//            String input = scanner.nextLine();
//            System.out.println(input);
//            switch (input) {
//                case "q" -> {
//                    actionDto.setActionType(ActionType.QUIT);
//                    objectOutputStream.writeObject(actionDto);
//                }
//                case "c" -> {
//                    actionDto.setActionType(ActionType.CREATE_LOBBY);
//                    objectOutputStream.writeObject(actionDto);
//                }
//                case "l" -> {
//                    actionDto.setActionType(ActionType.GET_LOBBIES);
//                    objectOutputStream.writeObject(actionDto);
//                    try {
//                        LobbiesDto lobbiesDto = (LobbiesDto) objectInputStream.readObject();
//                        System.out.println(lobbiesDto);
//                        StringBuilder test = new StringBuilder();
//                        for (Map.Entry<Integer, LobbyDto> lobbyEntry : lobbiesDto.getLobbies().entrySet()) {
//                            LobbyDto lobbyDto = lobbyEntry.getValue();
//                            test.append(lobbyDto.getId())
//                                    .append(" ")
//                                    .append(lobbyDto.getOwner().getUsername())
//                                    .append(" ")
//                                    .append(lobbyDto.getGameType());
//                        }
//                        System.out.println(test);
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                case "j" -> {
//                    actionDto.setActionType(ActionType.JOIN_LOBBY);
//                    objectOutputStream.writeObject(actionDto);
//                    int id = scanner.nextInt();
//                    dataOutputStream.writeUTF(Integer.toString(id));
//                }
//                case "s" -> {
//                    actionDto.setActionType(ActionType.START_GAME);
//                    objectOutputStream.writeObject(actionDto);
//                }
//            }
//        }
//
//    }

}
