package projekt.server.game.abstraction;

import projekt.server.client.Client;
import projekt.server.game.GameType;

import java.util.concurrent.ConcurrentHashMap;

public interface LobbyManager {
    ConcurrentHashMap<Integer, Lobby> getLobbies();
    Lobby createLobby(Client owner, GameType gameType);

    Lobby joinPlayerToLobby(int gameId, Client client);
    void destroyLobby(int lobbyId, Lobby lobby);
}
