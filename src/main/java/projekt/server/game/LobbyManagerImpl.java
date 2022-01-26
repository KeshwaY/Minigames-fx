package projekt.server.game;

import projekt.server.client.Client;
import projekt.server.game.abstraction.Lobby;
import projekt.server.game.abstraction.LobbyManager;

import java.util.concurrent.ConcurrentHashMap;

public class LobbyManagerImpl implements LobbyManager {

    private ConcurrentHashMap<Integer, Lobby> lobbies;

    public LobbyManagerImpl(ConcurrentHashMap<Integer, Lobby> lobbies) {
        this.lobbies = new ConcurrentHashMap<>();
    }

    @Override
    public Lobby createLobby(Client client, GameType gameType) {
        return null;
    }
}
