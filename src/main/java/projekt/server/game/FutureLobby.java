package projekt.server.game;

import projekt.server.dto.GameResult;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureLobby extends FutureTask<GameResult> {
    public FutureLobby(Callable<GameResult> callable) {
        super(callable);
    }

    @Override
    protected void done() {
        try {
            GameResult gameResult = this.get();
            System.out.println(gameResult.getFirstPlayer());
            System.out.println(gameResult.getSecondPlayer());
            System.out.println(gameResult.isOwnerAWinner());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
