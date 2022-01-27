package projekt.server.dto;


public class ClientDto extends AbstractDto {

    private String username;

    public ClientDto(String username) {
        this.username = username;
    }

    public ClientDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
