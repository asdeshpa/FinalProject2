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
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM Games"
            );
            while(rs.next()){
                String homeTeam = rs.getString(4);
                System.out.println("Home Team: " + homeTeam);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}


// Junior Riar 
// I wish Anuj would just be my friend
