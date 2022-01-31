package projekt.model;

import java.time.LocalDate;

public class Match {
    private int id;
    private int idPlayer1;
    private int idPlayer2;
    private int idWinner;
    private LocalDate createDate;
    private int pointsPlayer1;
    private int pointsPlayer2;

    public Match(int id, int idPlayer1, int idPlayer2) {
        this.id = id;
        this.idPlayer1 = idPlayer1;
        this.idPlayer2 = idPlayer2;
        this.createDate = LocalDate.now();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPlayer1() {
        return idPlayer1;
    }

    public void setIdPlayer1(int idPlayer1) {
        this.idPlayer1 = idPlayer1;
    }

    public int getIdPlayer2() {
        return idPlayer2;
    }

    public void setIdPlayer2(int idPlayer2) {
        this.idPlayer2 = idPlayer2;
    }

    public int getIdWinner() {
        return idWinner;
    }

    public void setIdWinner() {
        if(pointsPlayer1 == 3) idWinner = idPlayer1;
        else if (pointsPlayer2 == 3) idWinner = idPlayer2;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getPointsPlayer1() {
        return pointsPlayer1;
    }

    public void setPointsPlayer1(int pointsPlayer1) {
        this.pointsPlayer1 = pointsPlayer1;
    }

    public int getPointsPlayer2() {
        return pointsPlayer2;
    }

    public void setPointsPlayer2(int pointsPlayer2) {
        this.pointsPlayer2 = pointsPlayer2;
    }
}
