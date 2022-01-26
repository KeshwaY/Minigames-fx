package projekt.server.client;

import java.io.Serializable;
import java.net.Socket;

public class Client implements Runnable, Serializable {

    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {}
}
