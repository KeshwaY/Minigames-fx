package projekt.server.game.abstraction;

import projekt.server.client.Client;

public interface Lobby {
    boolean addPlayer(Client client);
    boolean changeDescription(String newDescription);
    void startGame();
}
