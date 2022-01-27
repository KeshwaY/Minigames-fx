package projekt.server.game;

public enum GameType {
    COIN_FLIP(2);

    private final int lobbySize;

    GameType(int size) {
        this.lobbySize = size;
    }

    public int getLobbySize() {
        return lobbySize;
    }

}
