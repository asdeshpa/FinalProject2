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
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class MyFxApp extends Application {

    private static final String BACKGROUND_COLOR = "-fx-background-color: #fdaaff;";

    Scene Home, Team; //Player, Game, Stats;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX App");
 
// Home Scene
        Text label1= new Text();
        label1.setText("Basketball Database");
        label1.setFont(Font.font("HELVETICA", 50));

        Button add_team = new Button("Add Team");
        add_team.setOnAction(e -> primaryStage.setScene(Team));
        add_team.setStyle("-fx-border-color: #cca054;");
        
        Button add_player = new Button("Add Player");
        add_player.setOnAction(e -> primaryStage.setScene(Team)); 

        Button add_game = new Button("Add Game");
        add_game.setOnAction(e -> primaryStage.setScene(Team));  

        Button add_stats = new Button("Add Stats");
        add_stats.setOnAction(e -> primaryStage.setScene(Team));  
        
        
        VBox layout1 = new VBox(30);   
        layout1.setAlignment(Pos.TOP_CENTER);
        layout1.getChildren().addAll(label1, add_team, add_player, add_game, add_stats);


        Text label2= new Text();
        label2.setText("Queries");
        label2.setFont(Font.font("HELVETICA", 50));

        Button get_team = new Button("Get Team");
        get_team.setOnAction(e -> primaryStage.setScene(Team));  

        Button get_player = new Button("Get Player");
        get_player.setOnAction(e -> primaryStage.setScene(Team)); 
        
        Button get_game = new Button("Get Game");
        get_game.setOnAction(e -> primaryStage.setScene(Team));  

        Button get_stats = new Button("Get Stats");
        get_stats.setOnAction(e -> primaryStage.setScene(Team));  

        VBox layout2 = new VBox(30);
        layout2.setAlignment(Pos.TOP_CENTER);
        layout2.getChildren().addAll(label2, get_team, get_player, get_game, get_stats);

        SplitPane split_pane = new SplitPane(); 
        split_pane.getItems().add(layout1);
        split_pane.getItems().add( layout2);
        split_pane.setStyle(BACKGROUND_COLOR);

        Home = new Scene(split_pane, 1200, 1000);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GridPane.setConstraints(scenetitle, 0, 0);

// Add Team Page

        VBox vb = new VBox(5);
        vb.setAlignment(Pos.TOP_CENTER);

        Label labelfirst= new Label("Enter Team Name");
        Label labeltwo = new Label("Enter City: ");
        Label label= new Label();
                
        Button submit= new Button("Submit");
        TextField text= new TextField();
        TextField text2 = new TextField();
        submit.setOnAction(e -> {         
            label.setText("Team Name: " + text.getText() + " City: " + text2.getText());
        });
    
        vb.getChildren().addAll(labelfirst, text, labeltwo, text2, submit, label);

        Team = new Scene(vb, 1200, 1000);      


        primaryStage.setScene(Home);
        primaryStage.show();
     
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
