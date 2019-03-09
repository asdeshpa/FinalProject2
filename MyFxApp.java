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

    Scene Home, Team; //Player, Game, Stats;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX App");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(75, 75, 75, 75));
 
// Home Scene
        Label label1= new Label("This is the first scene");
        Button add_team = new Button("Add Team");
        //GridPane.setConstraints(add_team, 1, 2);
        //grid.getChildren().add(add_team);
        add_team.setOnAction(e -> primaryStage.setScene(Team));
        
        Button add_player = new Button("Add Player");
        add_player.setOnAction(e -> primaryStage.setScene(Team)); 

        Button add_game = new Button("Add Game");
        add_game.setOnAction(e -> primaryStage.setScene(Team));  

        Button add_stats = new Button("Add Stats");
        add_stats.setOnAction(e -> primaryStage.setScene(Team));  
        
        
        VBox layout1 = new VBox(20);   
        layout1.getChildren().addAll(add_team, add_player, add_game, add_stats, label1);
        Home = new Scene(layout1, 300, 250);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GridPane.setConstraints(scenetitle, 0, 0);


// Test Button
        
        Label label2= new Label("test");
        Button add_p = new Button("go back");
        add_p.setOnAction(e -> primaryStage.setScene(Home)); 
        VBox layout2 = new VBox(20);  
        layout2.getChildren().addAll(label2, add_p);
        Team = new Scene(layout2, 300, 250); 

        primaryStage.setScene(Home);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
