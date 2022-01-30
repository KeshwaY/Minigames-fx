package projekt.model;

public class User {
    private int id;
    private int pos;
    private String login;
    private String password;
    private int points;

    public User(int id, int pos, String login, String password) {
        this.id = id;
        this.pos = pos;
        this.login = login;
        this.password = password;
    }
    //test only
    public User(String login,int points) {
        this.points = points;
        this.login = login;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


}
