package projekt.server.client.abstraction;

import projekt.server.dto.AbstractDto;

import java.io.IOException;

public interface ConnectionManager {
    void sendMessage(String message) throws IOException;
    String getInput() throws IOException;
    void sendDto(AbstractDto object) throws IOException;
    <T> T getDto() throws IOException, ClassNotFoundException;
    void close() throws IOException;
}
