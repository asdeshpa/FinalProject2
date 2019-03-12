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

    Scene Home, Team, Player , Game, Stats, Points, Rebounds, Assists, Blocks, Steals;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX App"); 
 
// Home Scene
        Text label1= new Text();
        label1.setText("Basketball Database");
        label1.setFont(Font.font("HELVETICA", 50));
// add team
        Button add_team = new Button("Add Team");
        add_team.setOnAction(e -> primaryStage.setScene(Team));
        add_team.setStyle("-fx-border-color: #cca054;");
// add player        
        Button add_player = new Button("Add Player");
        add_player.setOnAction(e -> primaryStage.setScene(Player)); 
// add game
        Button add_game = new Button("Add Game");
        add_game.setOnAction(e -> primaryStage.setScene(Game));  
// add stats
        Button add_stats = new Button("Add Stats");
        add_stats.setOnAction(e -> primaryStage.setScene(Stats));  
// create table
        Button create_table = new Button("Create Table");
        create_table.setOnAction(e -> primaryStage.setScene(Stats));  
// drop table
        Button drop_table = new Button("Drop Table");
        drop_table.setOnAction(e -> primaryStage.setScene(Stats));  
        
        VBox layout1 = new VBox(30);   
        layout1.setAlignment(Pos.TOP_CENTER);
        layout1.getChildren().addAll(label1, add_team, add_player, add_game, add_stats, create_table, drop_table);


        Text label2= new Text();
        label2.setText("Queries");
        label2.setFont(Font.font("HELVETICA", 50));
// 1
        Button most_points = new Button("Select the player with the most points per game");
        most_points.setOnAction(e -> primaryStage.setScene(Team));  
// 2
        Button most_rebs = new Button("Select the player with the most rebounds per game");
        most_rebs.setOnAction(e -> primaryStage.setScene(Team)); 
// 3        
        Button most_assists = new Button("Select the player with the most assists per game");
        most_assists.setOnAction(e -> primaryStage.setScene(Team));  
// 4
        Button most_steals = new Button("Select the player with the most steals per game");
        most_steals.setOnAction(e -> primaryStage.setScene(Team));  
// 5
        Button most_blocks = new Button("Select the player with most blocks per game");
        most_blocks.setOnAction(e -> primaryStage.setScene(Team));  
// 6
        Button points_query = new Button("List the players who have scored more than (insert number here) points");
        points_query.setOnAction(e -> primaryStage.setScene(Points));  
// 7
        Button rebound_query = new Button("List the players who have grabbed more than (insert number here) rebounds");
        rebound_query.setOnAction(e -> primaryStage.setScene(Rebounds));  
// 8
        Button assist_query = new Button("List the players who have tallied more than (insert number here) assists");
        assist_query.setOnAction(e -> primaryStage.setScene(Assists));  
// 9
        Button blocks_query = new Button("List the players who have more than (insert number here) blocks");
        blocks_query.setOnAction(e -> primaryStage.setScene(Blocks));  
// 10
        Button steals_query = new Button("List the players who have more than (insert number here) steals");
        steals_query.setOnAction(e -> primaryStage.setScene(Steals));  


        VBox layout2 = new VBox(30);
        layout2.setAlignment(Pos.TOP_CENTER);
        layout2.getChildren().addAll(label2, most_points, most_rebs, most_assists, most_steals, most_blocks,
                                        points_query, rebound_query, assist_query, blocks_query, steals_query);

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

        VBox stats_vb = new VBox(5);
        stats_vb.setAlignment(Pos.TOP_CENTER);


        Label stats_label1 = new Label("Enter Player ID: ");
        Label stats_label2 = new Label("Enter Jersey Number: ");
        Label stats_label3 = new Label("Enter Team Name: ");
        Label stats_label4 = new Label("Enter Game ID: ");
        Label stats_label5 = new Label("Enter Points: ");
        Label stats_label6 = new Label("Enter Rebounds: ");
        Label stats_label7 = new Label("Enter Assists: ");
        Label stats_label8 = new Label("Enter Blocks: ");
        Label stats_label9 = new Label("Enter Steals: ");

        Label stats_label= new Label();
                
        TextField stats_text1 = new TextField();
        TextField stats_text2 = new TextField();
        TextField stats_text3 = new TextField();
        TextField stats_text4 = new TextField();
        TextField stats_text5 = new TextField();
        TextField stats_text6 = new TextField();
        TextField stats_text7 = new TextField();
        TextField stats_text8 = new TextField();
        TextField stats_text9 = new TextField();

        Button stats_submit= new Button("Submit");
        Button stats_go_back = new Button("Go Back");
        stats_go_back.setOnAction(e -> primaryStage.setScene(Home)); 
        stats_submit.setOnAction(e -> {         
            stats_label.setText("Player ID: " + stats_text1.getText() + " Jersey Number: " + stats_text2.getText()
                                + " Team Name: " + stats_text3.getText() + " Game ID: " + stats_text4.getText() + 
                                " Points: " + stats_text5.getText() + " Rebounds: " + stats_text6.getText() +
                                " Assists: " + stats_text7.getText() + " Blocks: " + stats_text8.getText() + " Steals: " + stats_text9.getText()
                                ); });

        stats_vb.getChildren().addAll(stats_label1, stats_text1, stats_label2, stats_text2, stats_label3,
                                    stats_text3, stats_label4, stats_text4, stats_label5, stats_text5, 
                                    stats_label6, stats_text6, stats_label7, stats_text7, stats_label8, stats_text8,  
                                    stats_label9, stats_text9, stats_submit, stats_label, stats_go_back);

        Stats = new Scene(stats_vb, 1200, 1000);   


// Points Query 
        VBox points_vb = new VBox(5);
        points_vb.setAlignment(Pos.TOP_CENTER);

        Label points_label= new Label("Enter Points: ");
        Label finalpoints_label= new Label();

        TextField points_text= new TextField();
                
        Button points_submit= new Button("Submit");
        Button points_go_back = new Button("Go Back");
        points_go_back.setOnAction(e -> primaryStage.setScene(Home)); 
             
        points_submit.setOnAction(e -> {         
            finalpoints_label.setText("List the players who have scored more than " + points_text.getText() + " points. ");
        });
        points_vb.getChildren().addAll(points_label, points_text, points_submit, finalpoints_label, points_go_back);

        Points = new Scene(points_vb, 1200, 1000);      

// Rebounds Query

        VBox reb_vb = new VBox(5);
        reb_vb.setAlignment(Pos.TOP_CENTER);

        Label reb_label= new Label("Enter Rebounds: ");
        Label final_reb_label= new Label();

        TextField reb_text= new TextField();
                
        Button reb_submit= new Button("Submit");
        Button reb_go_back = new Button("Go Back");
        reb_go_back.setOnAction(e -> primaryStage.setScene(Home)); 
             
        reb_submit.setOnAction(e -> {         
            final_reb_label.setText("List the players who have grabbed more than " + reb_text.getText() + " rebounds. ");
        });
        reb_vb.getChildren().addAll(reb_label, reb_text, reb_submit, final_reb_label, reb_go_back);

        Rebounds = new Scene(reb_vb, 1200, 1000);  

// Assists Query 

        VBox ast_vb = new VBox(5);
        ast_vb.setAlignment(Pos.TOP_CENTER);

        Label ast_label= new Label("Enter Assists: ");
        Label final_ast_label= new Label();

        TextField ast_text= new TextField();
                
        Button ast_submit= new Button("Submit");
        Button ast_go_back = new Button("Go Back");
        ast_go_back.setOnAction(e -> primaryStage.setScene(Home)); 
             
        ast_submit.setOnAction(e -> {         
            final_ast_label.setText("List the players who have tallied more than " + ast_text.getText() + " assists. ");
        });
        ast_vb.getChildren().addAll(ast_label, ast_text, ast_submit, final_ast_label, ast_go_back);

        Assists = new Scene(ast_vb, 1200, 1000); 

// Blocks Query 

        VBox blk_vb = new VBox(5);
        blk_vb.setAlignment(Pos.TOP_CENTER);

        Label blk_label= new Label("Enter Blocks: ");
        Label final_blk_label= new Label();

        TextField blk_text= new TextField();
                
        Button blk_submit= new Button("Submit");
        Button blk_go_back = new Button("Go Back");
        blk_go_back.setOnAction(e -> primaryStage.setScene(Home)); 
             
        blk_submit.setOnAction(e -> {         
            final_blk_label.setText("List the players who have more than " + blk_text.getText() + " blocks. ");
        });
        blk_vb.getChildren().addAll(blk_label, blk_text, blk_submit, final_blk_label, blk_go_back);

        Blocks = new Scene(blk_vb, 1200, 1000); 

// Steals Query

        VBox stl_vb = new VBox(5);
        stl_vb.setAlignment(Pos.TOP_CENTER);

        Label stl_label= new Label("Enter Steals: ");
        Label final_stl_label= new Label();

        TextField stl_text= new TextField();
                
        Button stl_submit= new Button("Submit");
        Button stl_go_back = new Button("Go Back");
        stl_go_back.setOnAction(e -> primaryStage.setScene(Home)); 
             
        stl_submit.setOnAction(e -> {         
            final_stl_label.setText("List the players who have more than " + stl_text.getText() + " steals. ");
        });
        stl_vb.getChildren().addAll(stl_label, stl_text, stl_submit, final_stl_label, stl_go_back);

        Steals = new Scene(stl_vb, 1200, 1000); 

        primaryStage.setScene(Home);
        primaryStage.show();
     
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

