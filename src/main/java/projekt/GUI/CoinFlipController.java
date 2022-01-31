package projekt.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
    public Button drawButton;

    public void draw(ActionEvent actionEvent) throws IOException, InterruptedException {
        MainFX.client.draw();
        GameStatusDto gameStatusDto = MainFX.client.getGameStatus();

        yourPoints.setText(gameStatusDto.getFirstPlayerScore()+"");
        enemyPoints.setText(gameStatusDto.getSecondPlayerScore()+"");


        if(gameStatusDto.isFinished()){
            drawButton.disableProperty();

            if(gameStatusDto.getFirstPlayerScore() == 3){
                winner.setText("Owner wins!");
            }
            else winner.setText("Guest wins!");



            //Thread.sleep(5000);

            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Menu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
