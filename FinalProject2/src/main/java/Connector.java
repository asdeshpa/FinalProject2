import java.sql.*;


public class Connector {
    public static Connection connect;

    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://ambari-head.csc.calpoly.edu:3306/nriar?" +
                            "user=nriar" +
                            "&" +
                            "password=012383003");

            /* new code goes below this line */
            //addTeam(connect, "Kings", "Sacramento");
            //addTeam(connect, "Warriors", "Golden State");
            //addPlayer(connect, "Junior", 3, "Kings");
            //addBallGame(connect,
            //        "Kings", "Warriors", 100, 99, "2019-03-09");
            //addBoxScore(connect,
              //      3,
                //    "Kings", 1, 100, 50, 50, 10, 10);
            //blocksGreaterThan(connect, 10);
            winsForTeam(connect, "Kings");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createTables(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String sqlBallTeams = "create table BallTeams (\n" +
                    " team_name varchar(30),\n" +
                    " city varchar(30),\n" +
                    " primary key (team_name, city));";

            String sqlBPlayer = "create table BasketballPlayer (\n" +
                    "  player_name varchar(30),\n" +
                    "  jersey_number integer,\n" +
                    "  team_name varchar(30),\n" +
                    "  primary key (team_name, jersey_number),\n" +
                    "  foreign key (team_name) references BallTeams(team_name));";

            String sqlBallGames = "create table BallGames (\n" +
                    "  id integer primary key auto_increment,\n" +
                    "  home_team varchar(30),\n" +
                    "  away_team varchar(30),\n" +
                    "  home_points integer,\n" +
                    "  away_points integer,\n" +
                    "  date date,\n" +
                    "  foreign key (home_team) references BallTeams(team_name),\n" +
                    "  foreign key (away_team) references BallTeams(team_name));";

            String sqlBoxScore = "create table BoxScore (\n" +
                    "  id integer primary key auto_increment,\n" +
                    "  jersey_number integer,\n" +
                    "  team_name varchar(30),\n" +
                    "  gameID integer,\n" +
                    "  points integer,\n" +
                    "  rebounds integer,\n" +
                    "  assists integer,\n" +
                    "  blocks integer,\n" +
                    "  steals integer,\n" +
                    "  foreign key (team_name, jersey_number) references BasketballPlayer(team_name, jersey_number),\n" +
                    "  foreign key (gameID) references BallGames(id));";


            statement.executeUpdate(sqlBallTeams);
            statement.executeUpdate(sqlBPlayer);
            statement.executeUpdate(sqlBallGames);
            statement.executeUpdate(sqlBoxScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dropTables(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String dropBoxScore = "drop table BoxScore;";
            String dropBallGames = "drop table BallGames;";
            String dropBasketballPlayer = "drop table BasketballPlayer;";
            String dropBallTeams = "drop table BallTeams;";
            statement.executeUpdate(dropBoxScore);
            statement.executeUpdate(dropBallGames);
            statement.executeUpdate(dropBasketballPlayer);
            statement.executeUpdate(dropBallTeams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTeam(Connection connection, String team_name, String city) {
        try {
            Statement statement = connection.createStatement();
            String addTeam = "insert into BallTeams(team_name, city)" +
                    "\n values ('" + team_name + "', '" + city + "');";
            statement.executeUpdate(addTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPlayer(Connection connection, String name, int jersey_number, String team_name) {
        try {
            Statement statement = connection.createStatement();
            String addPlayer = "insert into BasketballPlayer(player_name, jersey_number, team_name)" +
                    " values ('" + name + "', " + jersey_number + ", '" + team_name + "');";
            System.out.println(addPlayer);
            statement.executeUpdate(addPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addBallGame(Connection connection, String home_team, String away_team,
                                   int home_points, int away_points, String date) {
        try {
            Statement statement = connection.createStatement();
            String addBallGame = "insert into BallGames(home_team, away_team, home_points, away_points, date)" +
                    "\n values('" + home_team + "', '" + away_team + "' , " + home_points + ", " + away_points +
                    ", '" + date + "');";
            statement.executeUpdate(addBallGame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addBoxScore(Connection connection,
                                   int jersey_number,
                                   String team_name,
                                   int gameID,
                                   int points,
                                   int rebounds,
                                   int assists,
                                   int blocks,
                                   int steals) {
        try {
            Statement statement = connection.createStatement();
            String addBoxScore = "insert into BoxScore(jersey_number, team_name, gameID, points, rebounds, assists," +
                    " blocks, steals)" +
                    "\n values (" + jersey_number + ",'" + team_name + "', " + gameID + ", " + points + ", " +
                    rebounds + ", " + assists + ", " + blocks + ", " + steals + ");";
            statement.executeUpdate(addBoxScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet pointsleader(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create view PointsPerGame as\n" +
                    "select S.jersey_number, sum(S.points) as points\n" +
                    "from BoxScore S\n" +
                    "group by S.jersey_number;\n" +
                    "\n");
            System.out.println("select bp.player_name, ppg.points\n" +
                    "from PointsPerGame ppg, BasketballPlayer bp\n" +
                    "where ppg.jersey_number = bp.jersey_number and ppg.points = (\n" +
                    "\tselect max(points)\n" +
                    "\tfrom PointsPerGame)\n");
            ResultSet rs = statement.executeQuery("select bp.player_name, ppg.points\n" +
            "from PointsPerGame ppg, BasketballPlayer bp\n" +
            "where ppg.jersey_number = bp.jersey_number and ppg.points = (\n" +
            "\tselect max(points)\n" +
            "\tfrom PointsPerGame)\n");


           /*while (rs.next()) {
                String playername = rs.getString(1);
                int amount = rs.getInt(2);

                System.out.println("Player = " + playername + "\n" + "Amount = " + amount);
            }

            statement.executeUpdate("drop view PointsPerGame;\n");*/
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ResultSet reboundsleader(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create view ReboundsPerGame as\n" +
                    "select S.jersey_number, sum(S.rebounds) as rebounds\n" +
                    "from BoxScore S\n" +
                    "group by S.jersey_number;\n" +
                    "\n");
            System.out.println("select bp.player_name, rpg.rebounds\n" +
                    "from ReboundsPerGame rpg, BasketballPlayer bp\n" +
                    "where rpg.jersey_number = bp.jersey_number and rpg.points = (\n" +
                    "\tselect max(rebounds)\n" +
                    "\tfrom ReboundsPerGame)\n");
            ResultSet rs = statement.executeQuery("select bp.player_name, rpg.rebounds\n" +
                    "from ReboundsPerGame rpg, BasketballPlayer bp\n" +
                    "where rpg.jersey_number = bp.jersey_number and rpg.rebounds = (\n" +
                    "\tselect max(rebounds)\n" +
                    "\tfrom ReboundsPerGame)\n");


            /*while (rs.next()) {
                String playername = rs.getString(1);
                int amount = rs.getInt(2);

                System.out.println("Player = " + playername + "\n" + "Amount = " + amount);
            }

            statement.executeUpdate("drop view ReboundsPerGame;\n");*/
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ResultSet stealsleader(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create view StealsPerGame as\n" +
                    "select S.jersey_number, sum(S.steals) as steals\n" +
                    "from BoxScore S\n" +
                    "group by S.jersey_number;\n" +
                    "\n");
            System.out.println("select bp.player_name, spg.steals\n" +
                    "from StealsPerGame spg, BasketballPlayer bp\n" +
                    "where spg.jersey_number = bp.jersey_number and spg.steals = (\n" +
                    "\tselect max(steals)\n" +
                    "\tfrom StealsPerGame)\n");
            ResultSet rs = statement.executeQuery("select bp.player_name, spg.steals\n" +
                    "from StealsPerGame spg, BasketballPlayer bp\n" +
                    "where spg.jersey_number = bp.jersey_number and spg.steals = (\n" +
                    "\tselect max(steals)\n" +
                    "\tfrom StealsPerGame)\n");


            /*while (rs.next()) {
                String playername = rs.getString(1);
                int amount = rs.getInt(2);

                System.out.println("Player = " + playername + "\n" + "Amount = " + amount);
            }

            statement.executeUpdate("drop view StealsPerGame;\n");*/
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ResultSet assistsleader(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create view AssistsPerGame as\n" +
                    "select S.jersey_number, sum(S.assists) as assists\n" +
                    "from BoxScore S\n" +
                    "group by S.jersey_number;\n" +
                    "\n");

            System.out.println("select bp.player_name, apg.assists\n" +
                    "from AssistsPerGame apg, BasketballPlayer bp\n" +
                    "where apg.jersey_number = bp.jersey_number and apg.assists = (\n" +
                    "\tselect max(assists)\n" +
                    "\tfrom AssistsPerGame)\n");

            ResultSet rs = statement.executeQuery("select bp.player_name, apg.assists\n" +
                    "from AssistsPerGame apg, BasketballPlayer bp\n" +
                    "where apg.jersey_number = bp.jersey_number and apg.assists = (\n" +
                    "\tselect max(assists)\n" +
                    "\tfrom AssistsPerGame)\n");


            /*while (rs.next()) {
                String playername = rs.getString(1);
                int amount = rs.getInt(2);

                System.out.println("Player = " + playername + "\n" + "Amount = " + amount);
            }

            statement.executeUpdate("drop view AssistsPerGame;\n");*/
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ResultSet blocksleader(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create view BlocksPerGame as\n" +
                    "select S.jersey_number, sum(S.blocks) as blocks\n" +
                    "from BoxScore S\n" +
                    "group by S.jersey_number;\n" +
                    "\n");

            System.out.println("select bp.player_name, bpg.blocks\n" +
                    "from BlocksPerGame bpg, BasketballPlayer bp\n" +
                    "where bpg.jersey_number = bp.jersey_number and bpg.blocks = (\n" +
                    "\tselect max(blocks)\n" +
                    "\tfrom BlocksPerGame)\n");

            ResultSet rs = statement.executeQuery("select bp.player_name, bpg.blocks\n" +
                    "from BlocksPerGame bpg, BasketballPlayer bp\n" +
                    "where bpg.jersey_number = bp.jersey_number and bpg.blocks = (\n" +
                    "\tselect max(blocks)\n" +
                    "\tfrom BlocksPerGame)\n");


            /*while (rs.next()) {
                String playername = rs.getString(1);
                int amount = rs.getInt(2);

                System.out.println("Player = " + playername + "\n" + "Amount = " + amount);
            }

            statement.executeUpdate("drop view BlocksPerGame;\n");*/
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ResultSet pointsGreaterThan(Connection connection, int value) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create view PointsPerGame as \n" +
                    "select S.jersey_number, sum(S.points) as points\n" +
                    "from BoxScore S\n" +
                    " group by S.jersey_number;\n" +
                    "\n");
            System.out.println("select bp.player_name\n" +
                    "from PointsPerGame ppg, BasketballPlayer bp\n" +
                    "where ppg.jersey_number = bp.jersey_number and ppg.points > " + value + "\n");
            ResultSet rs = statement.executeQuery("select bp.player_name\n" +
                    "from PointsPerGame ppg, BasketballPlayer bp\n" +
                    "where ppg.jersey_number = bp.jersey_number and ppg.points > " + value + "\n");
            /*while (rs.next()) {
                String playername = rs.getString(1);

                System.out.println("Player = " + playername + "\n");
            }*/
            System.out.println(rs);
            //statement.executeUpdate("drop view PointsPerGame;\n");

            return rs;
        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static ResultSet reboundsGreaterThan(Connection connection, int value) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create view ReboundsPerGame as \n" +
                    "select S.jersey_number, sum(S.rebounds) as rebounds\n" +
                    "from BoxScore S\n" +
                    " group by S.jersey_number;\n" +
                    "\n");
            System.out.println("select bp.player_name\n" +
                    "from ReboundsPerGame rpg, BasketballPlayer bp\n" +
                    "where rpg.jersey_number = bp.jersey_number and rpg.rebounds > " + value + "\n");
            ResultSet rs = statement.executeQuery("select bp.player_name\n" +
                    "from ReboundsPerGame rpg, BasketballPlayer bp\n" +
                    "where rpg.jersey_number = bp.jersey_number and rpg.rebounds > " + value + "\n");
            /*while (rs.next()) {
                String playername = rs.getString(1);

                System.out.println("Player = " + playername + "\n");
            }*/
            //statement.executeUpdate("drop view ReboundsPerGame;\n");
            return rs;
        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet assistsGreaterThan(Connection connection, int value) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create view assistsPerGame as \n" +
                    "select S.jersey_number, sum(S.assists) as assists\n" +
                    "from BoxScore S\n" +
                    " group by S.jersey_number;\n" +
                    "\n");
            System.out.println("select bp.player_name\n" +
                    "from assistsPerGame apg, BasketballPlayer bp\n" +
                    "where apg.jersey_number = bp.jersey_number and apg.assists > " + value + "\n");
            ResultSet rs = statement.executeQuery("select bp.player_name\n" +
                    "from assistsPerGame apg, BasketballPlayer bp\n" +
                    "where apg.jersey_number = bp.jersey_number and apg.assists > " + value + "\n");
            /*while (rs.next()) {
                String playername = rs.getString(1);

                System.out.println("Player = " + playername + "\n");
            }*/
            //statement.executeUpdate("drop view assistsPerGame;\n");
            return rs;
        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet blocksGreaterThan(Connection connection, int value) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create view blocksPerGame as \n" +
                    "select S.jersey_number, sum(S.blocks) as blocks\n" +
                    "from BoxScore S\n" +
                    " group by S.jersey_number;\n" +
                    "\n");
            System.out.println("select bp.player_name\n" +
                    "from blocksPerGame bpg, BasketballPlayer bp\n" +
                    "where bpg.jersey_number = bp.jersey_number and bpg.blocks > " + value + "\n");
            ResultSet rs = statement.executeQuery("select bp.player_name\n" +
                    "from blocksPerGame bpg, BasketballPlayer bp\n" +
                    "where bpg.jersey_number = bp.jersey_number and bpg.blocks > " + value + "\n");
            /*while (rs.next()) {
                String playername = rs.getString(1);

                System.out.println("Player = " + playername + "\n");
            }*/
            //statement.executeUpdate("drop view blocksPerGame;\n");
            return rs;
        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static ResultSet stealsGreaterThan(Connection connection, int value) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create view stealsPerGame as \n" +
                    "select S.jersey_number, sum(S.steals) as steals\n" +
                    "from BoxScore S\n" +
                    " group by S.jersey_number;\n" +
                    "\n");
            System.out.println("select bp.player_name\n" +
                    "from stealsPerGame spg, BasketballPlayer bp\n" +
                    "where spg.jersey_number = bp.jersey_number and spg.steals > " + value + "\n");
            ResultSet rs = statement.executeQuery("select bp.player_name\n" +
                    "from stealsPerGame spg, BasketballPlayer bp\n" +
                    "where spg.jersey_number = bp.jersey_number and spg.steals > " + value + "\n");
            /*while (rs.next()) {
                String playername = rs.getString(1);

                System.out.println("Player = " + playername + "\n");
            }*/
            //statement.executeUpdate("drop view stealsPerGame;\n");
            return rs;
        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet winsForTeam(Connection connection, String team_name){
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("  select BT.team_name, count(*) as wins\n" +
                    "  from BallTeams BT, BallGames BG\n" +
                    "  where  \n" +
                    "\t\t(BG.away_team = '" + team_name + "' and BG.away_points > BG.home_points)\n" +
                    "        or (BG.home_team = '" + team_name + "' and BG.home_points > BG.away_points)\n" +
                    "  group by BT.team_name\n" +
                    "  having BT.team_name = '" + team_name + "';");
            /*while (rs.next()){
                String teamName = rs.getString(1);
                int wins = rs.getInt(2);
                System.out.println(teamName + "\t" + wins);
            }*/
            return rs;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    
}
