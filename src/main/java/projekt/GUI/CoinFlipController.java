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

    public void draw(ActionEvent actionEvent) throws IOException {
        MainFX.client.draw();
        GameStatusDto gameStatusDto = MainFX.client.getGameStatus();
        System.out.println(gameStatusDto.getPlayer());
        System.out.println(gameStatusDto.getFirstPlayerScore());
        System.out.println(gameStatusDto.getSecondPlayerScore());
        System.out.println(gameStatusDto.isFinished());
    }
}
