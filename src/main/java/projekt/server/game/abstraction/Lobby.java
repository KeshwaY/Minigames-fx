package projekt.server.game.abstraction;

import projekt.server.client.Client;
import projekt.server.dto.GameResult;
import projekt.server.game.GameType;

import java.io.Serializable;
import java.util.concurrent.Callable;

public interface Lobby extends Serializable, Callable<GameResult> {
    Client getOwner();
    GameType getGameType();
    String getDescription();
    int getCurrentSize();
    int getMaxSize();

    void addPlayer(Client client);
    void changeDescription(String newDescription);
    void startGame();
    void delete();
    boolean isStart();
    Game getGame();
}
