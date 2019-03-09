import java.lang.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.text.*;

public class MyFxApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaFX App");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(75, 75, 75, 75));
        
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GridPane.setConstraints(scenetitle, 0, 0);

        Button add_team = new Button("Add Team");
        GridPane.setConstraints(add_team, 1, 2);
        grid.getChildren().add(add_team);

        Button add_player = new Button("Add Player");
        GridPane.setConstraints(add_player, 1, 4);
        grid.getChildren().add(add_player);

        Button add_game = new Button("Add Game");
        GridPane.setConstraints(add_game, 1, 6);
        grid.getChildren().add(add_game);

        Button add_stats = new Button("Add Stats");
        GridPane.setConstraints(add_stats, 1, 8);
        grid.getChildren().add(add_stats);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
