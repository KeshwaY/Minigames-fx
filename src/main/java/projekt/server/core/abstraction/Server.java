package projekt.server.core.abstraction;

import projekt.server.client.Client;
import projekt.server.game.GameType;

import java.beans.PropertyChangeListener;

public interface Server extends PropertyChangeListener {
    void start();
    void createLobby(Client owner);

    void createLobby(Client owner, GameType gameType);

    void addToLobby(Client player, int lobbyId);
}
