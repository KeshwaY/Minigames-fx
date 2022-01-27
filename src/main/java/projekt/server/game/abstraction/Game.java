package projekt.server.game.abstraction;

import projekt.server.client.Client;
import projekt.server.dto.GameResult;

import java.io.Serializable;

public interface Game extends Serializable {
    GameResult start();
    void addPlayer(Client client);
}
