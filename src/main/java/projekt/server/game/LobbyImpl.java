package projekt.server.game;

import projekt.server.client.Client;
import projekt.server.game.abstraction.Game;
import projekt.server.game.abstraction.Lobby;

import java.io.Serializable;

public class LobbyImpl implements Lobby, Serializable {

    private Game game;
    private Client ownerPlayer;
    private Client secondPlayer;

    public LobbyImpl(Client ownerPlayer) {
        this.ownerPlayer = ownerPlayer;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean addPlayer(Client client) {
        return false;
    }

    @Override
    public boolean changeDescription(String newDescription) {
        return false;
    }

    @Override
    public void startGame() {
    }
}
