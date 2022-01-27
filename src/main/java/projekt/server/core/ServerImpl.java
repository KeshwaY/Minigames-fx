package projekt.server.core;

import projekt.server.client.Client;
import projekt.server.client.abstraction.ConnectionManagerImpl;
import projekt.server.core.abstraction.ClientThreadManager;
import projekt.server.core.abstraction.Server;
import projekt.server.game.abstraction.LobbyManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerImpl implements Server {

    private final ServerSocket serverSocket;
    private final ClientThreadManager clientThreadManager;
    private final LobbyManager lobbyManager;

    public ServerImpl(int port, ClientThreadManager clientThreadManager, LobbyManager lobbyManager) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.lobbyManager = lobbyManager;
        this.clientThreadManager = clientThreadManager;
    }

    public void start() {

        while (!serverSocket.isClosed()) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New Client");
                Client client = new Client(
                        new ConnectionManagerImpl(clientSocket),
                        lobbyManager::createLobby,
                        lobbyManager::joinPlayerToLobby,
                        lobbyManager.getLobbies()
                );
                clientThreadManager.startClientThread(client);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
