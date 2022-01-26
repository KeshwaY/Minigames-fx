package projekt.server.game.abstraction;

import projekt.server.client.Client;
import projekt.server.game.GameType;

public interface LobbyManager {
    Lobby createLobby(Client client, GameType gameType);
}
