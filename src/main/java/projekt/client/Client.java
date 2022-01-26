package projekt.client;

import projekt.dto.TestDto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Client {

    private final Socket socket;

    public Client(String address, int port) throws IOException {
        this.socket = new Socket(address, port);
    }

    public void test() throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        try {
            TestDto testDto = (TestDto) objectInputStream.readObject();
            System.out.println(testDto.getTest());
            System.out.println(testDto.getTest2());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
