package projekt.server.game;

import projekt.server.client.Client;
import projekt.server.dto.ClientDto;
import projekt.server.dto.GameResult;
import projekt.server.game.abstraction.Game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameImpl implements Game {

    private int id;
    private Client firstPlayer;
    private Client secondPlayer;
    private LocalDateTime timeCreated;

    public GameImpl() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    public void addPlayer(Client client) {
        setSecondPlayer(client);
    }

    @Override
    public GameResult start() {
        GameResult gameResult = new GameResult();
        List<Client> clientList = new ArrayList<>();
        clientList.add(firstPlayer);
        clientList.add(secondPlayer);
        Collections.shuffle(clientList, new Random());
        gameResult.setGameId(id);
        gameResult.setFirstPlayer(new ClientDto());
        gameResult.setSecondPlayer(new ClientDto());
        gameResult.setTimeCreated(timeCreated);
        gameResult.setTimeFinished(LocalDateTime.now());
        return gameResult;
    }

}
