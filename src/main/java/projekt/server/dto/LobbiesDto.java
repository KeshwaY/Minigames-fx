package projekt.server.dto;

import java.util.Map;

public class LobbiesDto extends AbstractDto {
    private Map<Integer, LobbyDto> lobbies;

    public Map<Integer, LobbyDto> getLobbies() {
        return lobbies;
    }

    public void setLobbies(Map<Integer, LobbyDto> lobbies) {
        this.lobbies = lobbies;
    }
}
