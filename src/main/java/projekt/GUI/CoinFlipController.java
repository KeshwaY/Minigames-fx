package projekt.GUI;

import javafx.application.Platform;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoinFlipController {

    @FXML
    public Label enemyPoints;

    @FXML
    public Label yourPoints;

    @FXML
    public Label winner;
    public Button drawButton;

    private boolean isFinished = false;

    public CoinFlipController() throws IOException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (!isFinished) {
                    GameStatusDto gameStatusDto = MainFX.client.getGameStatus();
                    if (gameStatusDto.getPlayer() == 1) {
                        Platform.runLater(() -> {
                            yourPoints.setText(String.valueOf(gameStatusDto.getFirstPlayerScore()));
                            enemyPoints.setText(String.valueOf(gameStatusDto.getSecondPlayerScore()));
                        });
                    } else {
                        Platform.runLater(() -> {
                            yourPoints.setText(String.valueOf(gameStatusDto.getSecondPlayerScore()));
                            enemyPoints.setText(String.valueOf(gameStatusDto.getFirstPlayerScore()));
                        });
                    }
                    if (gameStatusDto.isFinished()) {
                        isFinished = true;
                        if(gameStatusDto.getFirstPlayerScore() == 3){
                            Platform.runLater(() -> winner.setText("Owner wins!"));
                        }
                        else {
                            Platform.runLater(() -> winner.setText("Guest wins!"));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void draw(ActionEvent actionEvent) throws IOException, InterruptedException {
        MainFX.client.draw();

        if(isFinished) {
            drawButton.disableProperty();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Menu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
