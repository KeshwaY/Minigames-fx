package projekt.server.game;

import projekt.server.client.Client;
import projekt.server.game.abstraction.Game;
import projekt.server.game.abstraction.GameCreator;

import java.time.LocalDateTime;

public class GameCreatorImpl implements GameCreator {

    @Override
    public Game createGame(int id, Client firstPlayer, GameType gameType) {
        GameImpl game = new GameImpl();
        game.setFirstPlayer(firstPlayer);
        game.setId(id);
        game.setTimeCreated(LocalDateTime.now());
        return game;
    }

}
