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

    Scene Home, Team, Player , Game; //, Stats;
    
    

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
        add_player.setOnAction(e -> primaryStage.setScene(Player)); 

        Button add_game = new Button("Add Game");
        add_game.setOnAction(e -> primaryStage.setScene(Game));  

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
        split_pane.getItems().add(layout2);
        split_pane.setStyle(BACKGROUND_COLOR);

        Home = new Scene(split_pane, 1200, 1000);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GridPane.setConstraints(scenetitle, 0, 0);

// Add Team Page

        VBox team_vb = new VBox(5);
        team_vb.setAlignment(Pos.TOP_CENTER);

        Label team_labelfirst= new Label("Enter Team Name");
        Label team_labeltwo = new Label("Enter City: ");
        Label finalteam_label= new Label();
                
        Button team_submit= new Button("Submit");
        Button team_go_back = new Button("Go Back");
        team_go_back.setOnAction(e -> primaryStage.setScene(Home)); 
        
        TextField text= new TextField();
        TextField text2 = new TextField();
        team_submit.setOnAction(e -> {         
            finalteam_label.setText("Team Name: " + text.getText() + " City: " + text2.getText());
        });
        team_vb.getChildren().addAll(team_labelfirst, text, team_labeltwo, text2, team_submit, finalteam_label, team_go_back);

        Team = new Scene(team_vb, 1200, 1000);      

// Player button

        VBox player_vb = new VBox(5);
        player_vb.setAlignment(Pos.TOP_CENTER);
    

        Label player_label1= new Label("Enter Player Name: ");
        Label player_label2 = new Label("Enter Jersey Number: ");
        Label player_label3 = new Label("Enter Team Name: ");
        Label player_label= new Label();
                
        Button player_submit= new Button("Submit");
        TextField player_text= new TextField();
        TextField player_text2 = new TextField();
        TextField player_text3 = new TextField();
        Button player_go_back = new Button("Go Back");
        player_go_back.setOnAction(e -> primaryStage.setScene(Home)); 
        player_submit.setOnAction(e -> {         
            player_label.setText("Player Name: " + player_text.getText() + " Jersey Number: " + player_text2.getText()
                                    + " Team Name: " + player_text3.getText()); });

        player_vb.getChildren().addAll(player_label1, player_text, player_label2, player_text2, player_label3,
                                    player_text3, player_submit, player_label, player_go_back);

        Player = new Scene(player_vb, 1200, 1000);      

         
        
// Game button
        VBox game_vb = new VBox(5);
        game_vb.setAlignment(Pos.TOP_CENTER);


        Label game_label1 = new Label("Enter Game ID: ");
        Label game_label2 = new Label("Enter Home Team: ");
        Label game_label3 = new Label("Enter Away Team: ");
        Label game_label4 = new Label("Enter Home Points: ");
        Label game_label5 = new Label("Enter Away Points: ");
        Label game_label6 = new Label("Enter Date: ");

        Label game_label= new Label();
                
        TextField game_text1 = new TextField();
        TextField game_text2 = new TextField();
        TextField game_text3 = new TextField();
        TextField game_text4 = new TextField();
        TextField game_text5 = new TextField();
        TextField game_text6 = new TextField();

        Button game_submit= new Button("Submit");
        Button game_go_back = new Button("Go Back");
        game_go_back.setOnAction(e -> primaryStage.setScene(Home)); 
        game_submit.setOnAction(e -> {         
            game_label.setText("Game ID: " + game_text1.getText() + " Home Team: " + game_text2.getText()
                                + " Away Team: " + game_text3.getText() + " Home Points: " + game_text4.getText() + 
                                " Away Points: " + game_text5.getText() + " Date: " + game_text6.getText()); });

        game_vb.getChildren().addAll(game_label1, game_text1, game_label2, game_text2, game_label3,
                                    game_text3, game_label4, game_text4, game_label5, game_text5, 
                                    game_label6, game_text6, game_submit, game_label, game_go_back);

        Game = new Scene(game_vb, 1200, 1000);       
  
// Stats button
        

        primaryStage.setScene(Home);
        primaryStage.show();
     
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

