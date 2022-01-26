package projekt.server.client;

import projekt.server.dto.ClientScore;

import java.util.concurrent.*;

public class FutureClient extends FutureTask<ClientScore> {

    public FutureClient(Callable<ClientScore> client) {
        super(client);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public ClientScore get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public ClientScore get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
