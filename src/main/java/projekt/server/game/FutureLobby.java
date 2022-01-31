package projekt.server.game;

import projekt.database.DataBase;
import projekt.server.dto.GameResult;

import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureLobby extends FutureTask<GameResult> {

    private final DataBase dataBase;

    public FutureLobby(Callable<GameResult> callable, DataBase dataBase) {
        super(callable);
        this.dataBase = dataBase;
    }

    @Override
    protected void done() {
        try {
            GameResult gameResult = this.get();
            int gameID = dataBase.addGame(GameType.COIN_FLIP, gameResult.getTimeCreated());
            System.out.println(gameID);
            dataBase.addResult(gameID, gameResult.getFirstPlayer().getId(), gameResult.isOwnerAWinner() ? 1 : 0);
            dataBase.addResult(gameID, gameResult.getSecondPlayer().getId(), !gameResult.isOwnerAWinner() ? 1 : 0);
        } catch (InterruptedException | ExecutionException | SQLException e) {
            e.printStackTrace();
        }
    }
}
