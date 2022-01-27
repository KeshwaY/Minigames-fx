package projekt.client;

import projekt.server.ActionType;
import projekt.server.dto.ActionDto;
import projekt.server.dto.ClientDto;
import projekt.server.dto.LobbiesDto;
import projekt.server.dto.LobbyDto;
import projekt.server.game.abstraction.Lobby;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class Client {

    private final Socket socket;

    public Client(String address, int port) throws IOException {
        this.socket = new Socket(address, port);
    }

    public void test() throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        ClientDto clientDto = new ClientDto(scanner.nextLine());
        objectOutputStream.writeObject(clientDto);

        main: while (true) {
            ActionDto actionDto = new ActionDto();
            String input = scanner.nextLine();
            System.out.println(input);
            switch (input) {
                case "q":
                    actionDto.setActionType(ActionType.QUIT);
                    objectOutputStream.writeObject(actionDto);
                    break main;
                case "c":
                    actionDto.setActionType(ActionType.CREATE_LOBBY);
                    objectOutputStream.writeObject(actionDto);
                    break;
                case "l":
                    actionDto.setActionType(ActionType.GET_LOBBIES);
                    objectOutputStream.writeObject(actionDto);
                    try {
                        LobbiesDto lobbiesDto = (LobbiesDto) objectInputStream.readObject();
                        System.out.println(lobbiesDto);
                        StringBuilder test = new StringBuilder();
                        for (Map.Entry<Integer, LobbyDto> lobbyEntry : lobbiesDto.getLobbies().entrySet()) {
                            LobbyDto lobbyDto = lobbyEntry.getValue();
                            test.append(lobbyDto.getId())
                                    .append(" ")
                                    .append(lobbyDto.getOwner().getUsername())
                                    .append(" ")
                                    .append(lobbyDto.getGameType());
                        }
                        System.out.println(test);
                        break;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "j":
                    actionDto.setActionType(ActionType.JOIN_LOBBY);
                    objectOutputStream.writeObject(actionDto);
                    int id = scanner.nextInt();
                    dataOutputStream.writeUTF(Integer.toString(id));
                    break;
                case "s":
                    actionDto.setActionType(ActionType.START_GAME);
                    objectOutputStream.writeObject(actionDto);
                    break;
            }
        }

    }

}
