create table BallTeams (
 team_name varchar(30),
 city varchar(30),
 primary key (team_name, city));
 
create table BasketballPlayer (
  player_name varchar(30),
  jersey_number integer,
  team_name varchar(30),
  primary key (team_name, jersey_number),
  foreign key (team_name) references BallTeams(team_name));
    
create table BallGames (
  id integer primary key auto_increment,
  home_team varchar(30),
  away_team varchar(30),
  home_points integer,
  away_points integer,
  date date,
  foreign key (home_team) references BallTeams(team_name),
  foreign key (away_team) references BallTeams(team_name));
  
create table BoxScore (
  id integer primary key auto_increment,
  jersey_number integer,
  team_name varchar(30),
  gameID integer,
  points integer,
  rebounds integer,
  assists integer,
  blocks integer,
  steals integer,
  foreign key (team_name, jersey_number) references BasketballPlayer(team_name, jersey_number),
  foreign key (gameID) references BallGames(id));

 drop table BallGames, BasketballPlayer, BallTeams;
drop table BoxScore;