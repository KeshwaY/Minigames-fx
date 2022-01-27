package projekt.server.dto;

import projekt.server.game.GameType;

public class LobbyDto extends AbstractDto {
    private int id;

    private ClientDto owner;
    private GameType gameType;
    private String description;

    private int currentSize;
    private int maxSize;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientDto getOwner() {
        return owner;
    }

    public void setOwner(ClientDto owner) {
        this.owner = owner;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
