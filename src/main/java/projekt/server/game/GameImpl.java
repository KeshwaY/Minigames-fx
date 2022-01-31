package projekt.server.game;

import projekt.server.client.Client;
import projekt.server.dto.ClientDto;
import projekt.server.dto.GameResult;
import projekt.server.dto.GameStatusDto;
import projekt.server.game.abstraction.Game;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GameImpl implements Game {

    private int id;
    private Client firstPlayer;
    private Client secondPlayer;
    private LocalDateTime timeCreated;

    private int drawCount = 0;
    private int firstPlayerScore;
    private int secondPlayerScore;

    private boolean isPlaying;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

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
    public void draw() {
        if (!isPlaying) {
            return;
        }
        GameStatusDto gameStatusDto = new GameStatusDto();
        List<Client> clientList = new ArrayList<>();
        clientList.add(firstPlayer);
        clientList.add(secondPlayer);
        drawCount++;
        Collections.shuffle(clientList, new Random());
        if (clientList.get(0) == firstPlayer) {
            firstPlayerScore++;
        } else {
            secondPlayerScore++;
        }
        gameStatusDto.setFinished(false);
        gameStatusDto.setFirstPlayerScore(firstPlayerScore);
        gameStatusDto.setSecondPlayerScore(secondPlayerScore);
        if (drawCount == 3) {
            gameStatusDto.setFinished(true);
            isPlaying = false;
        }
        clientList.forEach(c -> {
            try {
                c.sendGameStatusDto(gameStatusDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public GameResult start() {
        GameResult gameResult = new GameResult();
        this.isPlaying = true;
        Future<Boolean> task = executorService.submit(() -> {
            while (this.isPlaying) {
                Thread.onSpinWait();
            }
            return true;
        });
        try {
            boolean finished = task.get();
            if (finished) {
                try {
                    firstPlayer.close();
                    secondPlayer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gameResult.setGameId(id);
                gameResult.setFirstPlayer(firstPlayer.getIdentity());
                gameResult.setSecondPlayer(secondPlayer.getIdentity());
                gameResult.setOwnerAWinner(firstPlayerScore > secondPlayerScore);
                gameResult.setTimeCreated(timeCreated);
                gameResult.setTimeFinished(LocalDateTime.now());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return gameResult;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }
}
