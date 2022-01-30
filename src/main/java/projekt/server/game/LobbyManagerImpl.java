package projekt.server.game;

import projekt.database.DataBase;
import projekt.server.client.Client;
import projekt.server.game.abstraction.GameCreator;
import projekt.server.game.abstraction.Lobby;
import projekt.server.game.abstraction.LobbyManager;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class LobbyManagerImpl implements LobbyManager {

    private final DataBase dataBase;
    private final ConcurrentHashMap<Integer, Lobby> lobbies;
    private final GameCreator gameCreator;

    private final ExecutorService executorService;

    private final ReentrantLock createLock = new ReentrantLock();
    private final ReentrantLock joinLock = new ReentrantLock();
    private final ReentrantLock deleteLock = new ReentrantLock();

    private int ids = 0;

    public LobbyManagerImpl(DataBase dataBase, ConcurrentHashMap<Integer, Lobby> lobbies, GameCreator gameCreator) {
        this.dataBase = dataBase;
        this.lobbies = lobbies;
        this.gameCreator = gameCreator;
        this.executorService = Executors.newFixedThreadPool(1);
    }

    public ConcurrentHashMap<Integer, Lobby> getLobbies() {
        return lobbies;
    }

    @Override
    public Lobby createLobby(Client owner, GameType gameType) {
        System.out.println(gameType);
        createLock.lock();
        try {
            Lobby lobby = new LobbyImpl(
                    ids,
                    owner,
                    gameType,
                    "New game",
                    gameCreator.createGame(ids, owner, gameType),
                    this::destroyLobby
            );
            lobbies.put(ids, lobby);
            ids++;
            executorService.submit(new FutureLobby(lobby));
            return lobby;
        } finally {
            createLock.unlock();
        }
    }

    @Override
    public Lobby joinPlayerToLobby(int gameId, Client client) {
        joinLock.lock();
        try {
            Lobby lobby = lobbies.get(gameId);
            lobby.addPlayer(client);
            return lobby;
        } finally {
            joinLock.unlock();
        }
    }

    public void destroyLobby(int lobbyId, Lobby lobby) {
        deleteLock.lock();
        try {
            lobbies.remove(lobbyId, lobby);
        } finally {
            deleteLock.unlock();
        }
    }
}
