package projekt.server.core.abstraction;

import projekt.server.client.Client;
import projekt.server.game.abstraction.Lobby;

public interface ThreadManager {
    void startLobbyThread(Lobby lobby);
    void startClientThread(Client client);
}
