package projekt.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import projekt.model.Match;
import projekt.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HistoryController implements Initializable {
    @FXML
    private TableView tableView;

    private ObservableList<Match> getMatches(){
        ObservableList<Match> matches = FXCollections.observableArrayList();
        matches.add(new Match(1,1,2));
        matches.add(new Match(1,1,3));
        matches.add(new Match(1,2,1));
        return matches;
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Menu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<User, String> player1Col = new TableColumn<>("Player 1");
        player1Col.setCellValueFactory(new PropertyValueFactory<>("idPlayer1"));

        TableColumn<User, String> player2Col = new TableColumn<>("Player 2");
        player2Col.setCellValueFactory(new PropertyValueFactory<>("idPlayer2"));

        TableColumn<User, String> winnerCol = new TableColumn<>("Winner");
        winnerCol.setCellValueFactory(new PropertyValueFactory<>("idWinner"));

        TableColumn<User, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));

        TableColumn<User, String> points1Col = new TableColumn<>("Player1 points");
        points1Col.setCellValueFactory(new PropertyValueFactory<>("pointsPlayer1"));

        TableColumn<User, String> points2Col = new TableColumn<>("Player2 points");
        points2Col.setCellValueFactory(new PropertyValueFactory<>("pointsPlayer2"));

        tableView.getColumns().add(player1Col);
        tableView.getColumns().add(player2Col);
        tableView.getColumns().add(winnerCol);
        tableView.getColumns().add(points1Col);
        tableView.getColumns().add(points2Col);
        tableView.getColumns().add(dateCol);

        getMatches().forEach(match ->{
            tableView.getItems().add(match);
        });



    }


}
