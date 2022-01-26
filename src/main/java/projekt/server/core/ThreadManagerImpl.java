package projekt.server.core;

import projekt.server.client.Client;
import projekt.server.core.abstraction.ThreadManager;
import projekt.server.game.abstraction.Lobby;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManagerImpl implements ThreadManager {

    private final ExecutorService executor;

    public ThreadManagerImpl(int capacity) {
        this.executor = Executors.newFixedThreadPool(capacity);
    }

    @Override
    public void startLobbyThread(Lobby lobby) {

    }

    @Override
    public void startClientThread(Client client) {

    }
}
