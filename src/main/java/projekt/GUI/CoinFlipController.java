package projekt.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import projekt.server.dto.GameResult;
import projekt.server.dto.GameStatusDto;

import java.io.IOException;

public class CoinFlipController {

    @FXML
    public Label enemyPoints;

    @FXML
    public Label yourPoints;

    @FXML
    public Label winner;

    public void draw(ActionEvent actionEvent) throws IOException {
        MainFX.client.draw();
        GameStatusDto gameStatusDto = MainFX.client.getGameStatus();

        yourPoints.setText(gameStatusDto.getFirstPlayerScore()+"");
        enemyPoints.setText(gameStatusDto.getSecondPlayerScore()+"");


        if(gameStatusDto.isFinished()){

            if(gameStatusDto.getFirstPlayerScore() == 3){
                winner.setText("Owner wins!");
            }
            else winner.setText("Guest wins!");

        }
    }
}
