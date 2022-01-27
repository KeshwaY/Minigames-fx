package projekt.server.dto;

import java.time.LocalDateTime;

public class GameResult extends AbstractDto {

    private int gameId;
    private ClientDto firstPlayer;
    private ClientDto secondPlayer;

    private boolean isOwnerAWinner;

    private LocalDateTime timeCreated;
    private LocalDateTime timeFinished;

    public GameResult() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public ClientDto getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(ClientDto firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public ClientDto getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(ClientDto secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public boolean isOwnerAWinner() {
        return isOwnerAWinner;
    }

    public void setOwnerAWinner(boolean ownerAWinner) {
        isOwnerAWinner = ownerAWinner;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(LocalDateTime timeFinished) {
        this.timeFinished = timeFinished;
    }
}
