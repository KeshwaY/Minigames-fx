package projekt.server.game;

import projekt.server.client.Client;
import projekt.server.dto.ClientScore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.concurrent.*;

public class GameImpl implements Callable<ClientScore>, Serializable {

    private Client firstPlayer;
    private Client secondPlayer;
    private LocalDateTime timeCreated;

    public GameImpl() {}

    public Client getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Client firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Client getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Client secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public ClientScore call() throws Exception {
        return null;
    }
}
