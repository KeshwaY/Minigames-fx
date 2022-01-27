package projekt.server.game.abstraction;

import projekt.server.client.Client;
import projekt.server.game.GameType;

public interface GameCreator {
    Game createGame(int id, Client firstPlayer, GameType gameType);
}
