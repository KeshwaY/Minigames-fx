package projekt.server.core;

import projekt.server.client.Client;
import projekt.server.core.abstraction.ClientThreadManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManagerImpl implements ClientThreadManager {

    private final ExecutorService executor;

    public ThreadManagerImpl(int capacity) {
        this.executor = Executors.newFixedThreadPool(capacity);
    }

    @Override
    public void startClientThread(Client client) {
        executor.submit(client);
    }
}
