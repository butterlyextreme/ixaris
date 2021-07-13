CREATE TABLE rankings (
  id varchar(10)  PRIMARY KEY,
  points int NOT NULL,
  ranking int NOT NULL,
  publication_date TIMESTAMP NOT NULL
);

CREATE TABLE matches (
  id UUID PRIMARY KEY,
  match_date TIMESTAMP NOT NULL,
  playerWon varchar(10),
  playerLose varchar(10)
);


INSERT INTO rankings (id,points,ranking,publication_date) VALUES ('001', 100, 1, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'));