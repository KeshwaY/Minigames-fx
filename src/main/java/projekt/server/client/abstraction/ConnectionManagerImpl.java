package projekt.server.client.abstraction;

import projekt.server.dto.AbstractDto;

import java.io.*;
import java.net.Socket;

public class ConnectionManagerImpl implements ConnectionManager {

    private final Socket socket;
    private final DataOutputStream dataOutputStream;
    private final DataInputStream dataInputStream;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;

    public ConnectionManagerImpl(Socket socket) throws IOException {
        this.socket = socket;
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void sendMessage(String message) throws IOException {
        dataOutputStream.writeUTF(message);
    }

    @Override
    public String getInput() throws IOException {
        return dataInputStream.readUTF();
    }

    @Override
    public void sendDto(AbstractDto object) throws IOException {
        objectOutputStream.writeObject(object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getDto() throws IOException, ClassNotFoundException {
        return (T) objectInputStream.readObject();
    }

    @Override
    public void close() throws IOException {
        dataOutputStream.close();
        dataInputStream.close();
        objectOutputStream.close();
        objectInputStream.close();
        socket.close();
    }
}
