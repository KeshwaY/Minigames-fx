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
import projekt.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class RankController implements Initializable {
    @FXML
    private TableView tableView;

    private ObservableList<User> getUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();
        users.add(new User("radek", 10 ));
        users.add(new User("dawid", 42 ));
        users.add(new User("damian", 21 ));
        users.add(new User("user", 0 ));
        return users;
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

        TableColumn<User, String> positionCol = new TableColumn<>("Position");
        positionCol.setCellValueFactory(new PropertyValueFactory<>("pos"));

        TableColumn<User, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("login"));

        TableColumn<User, String> pointsCol = new TableColumn<>("Points");
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));


        final ObservableList columns = tableView.getColumns();
        columns.add(positionCol);
        columns.add(usernameCol);
        columns.add(pointsCol);

        List<User> userList = getUsers().stream().sorted(Comparator.comparingInt(User::getPoints).reversed())
                .collect(Collectors.toList());

        IntStream.range(0, userList.size())
                .forEach(i -> {
                    User user = userList.get(i);
                    user.setPos(i + 1);
                    tableView.getItems().add(user);
                });
    }
}
