package projekt.server.game;

import projekt.server.client.Client;
import projekt.server.dto.GameResult;
import projekt.server.game.abstraction.Game;
import projekt.server.game.abstraction.Lobby;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

public class LobbyImpl implements Lobby {

    private int id;

    private Client ownerPlayer;
    private Client secondPlayer;
    private GameType gameType;
    private String description;

    private volatile boolean start;
    private Game game;

    private final BiConsumer<Integer, Lobby> deleteLobbyFunction;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public LobbyImpl(int id, Client ownerPlayer, GameType gameType, String description, Game game, BiConsumer<Integer, Lobby> deleteLobbyFunction) {
        this.id = id;
        this.ownerPlayer = ownerPlayer;
        this.gameType = gameType;
        this.description = description;
        this.game = game;
        this.deleteLobbyFunction = deleteLobbyFunction;
        this.start = false;
    }

    @Override
    public Client getOwner() {
        return getOwnerPlayer();
    }

    @Override
    public int getCurrentSize() {
        return secondPlayer == null ? 1 : 2;
    }

    @Override
    public int getMaxSize() {
        return gameType.getLobbySize();
    }

    @Override
    public void addPlayer(Client client) {
        if (secondPlayer != null) {
            return;
        }
        secondPlayer = client;
        game.addPlayer(client);
    }

    @Override
    public void changeDescription(String newDescription) {
        description = newDescription;
    }

    @Override
    public void startGame() {
        this.start = true;
    }

    @Override
    public void delete() {
        this.deleteLobbyFunction.accept(id, this);
    }

    @Override
    public GameResult call() throws Exception {
        while (!start) {
            Thread.onSpinWait();
        }
        Future<GameResult> gameResultFuture = executorService.submit(() -> game.start());
        return gameResultFuture.get();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getOwnerPlayer() {
        return ownerPlayer;
    }

    public void setOwnerPlayer(Client ownerPlayer) {
        this.ownerPlayer = ownerPlayer;
    }

    public Client getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Client secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    @Override
    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
