package projekt.server.dto;


public class ClientDto extends AbstractDto {

    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientDto(String username, String password) {
        this.username = username;
        this.password = password;
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
