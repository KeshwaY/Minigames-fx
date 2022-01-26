package projekt.server.core;

import projekt.server.client.Client;
import projekt.server.core.abstraction.Server;
import projekt.server.core.abstraction.ThreadManager;
import projekt.server.game.GameType;
import projekt.server.game.abstraction.LobbyManager;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerImpl implements Server {

    private final ServerSocket serverSocket;
    private final ThreadManager threadManager;
    private final LobbyManager lobbyManager;

    public ServerImpl(int port, ThreadManager threadManager, LobbyManager lobbyManager) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.threadManager = threadManager;
        this.lobbyManager = lobbyManager;
    }

    public void start() {
        while (!serverSocket.isClosed()) {
            try {
                Socket clientSocket = serverSocket.accept();
                Client client = new Client(clientSocket);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void createLobby(Client owner) {
    }

    @Override
    public void createLobby(Client owner, GameType gameType) {
    }

    @Override
    public void addToLobby(Client player, int lobbyId) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
