import java.sql.*;

public class Connector {
    static Connection connect;
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://ambari-head.csc.calpoly.edu:3306/nriar?" +
                            "user=nriar" +
                            "&" +
                            "password=012383003");
            Statement statement = connect.createStatement();
            statement.executeUpdate(
                    "insert into BallTeams (team_name, city)" +
                            "values ('Scores2', 'SacTown')"
            );
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}


// Junior Riar
// I wish Anuj would just be my friend
// new comment