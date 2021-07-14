CREATE TABLE rankings (
  id varchar(10)  PRIMARY KEY,
  points int NOT NULL,
  ranking int NOT NULL,
  publication_date TIMESTAMP NOT NULL
);

CREATE TABLE matches (
  id UUID PRIMARY KEY,
  match_date TIMESTAMP NOT NULL,
  score varchar(10) NOT NULL,
  player_win varchar(10) NOT NULL,
  player_lose varchar(10) NOT NULL
);

INSERT INTO rankings (id,points,ranking,publication_date) VALUES ('001', 100, 1, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'));
INSERT INTO rankings (id,points,ranking,publication_date) VALUES ('002', 80, 2, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'));
INSERT INTO rankings (id,points,ranking,publication_date) VALUES ('003', 50, 3, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'));
INSERT INTO rankings (id,points,ranking,publication_date) VALUES ('004', 25, 4, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'));
