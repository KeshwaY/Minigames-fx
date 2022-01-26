package projekt.server.dto;

public class ClientScore {
    private int clientId;
    private int opponentId;
    private int gameId;
    private boolean isWinner;

    public ClientScore(int clientId, int opponentId, int gameId, boolean isWinner) {
        this.clientId = clientId;
        this.opponentId = opponentId;
        this.gameId = gameId;
        this.isWinner = isWinner;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(int opponentId) {
        this.opponentId = opponentId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}
