
CREATE TABLE temperatura(
    id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
    frio integer NOT NULL,
    calor integer NOT NULL,
    chuva integer NOT NULL
);

CREATE TABLE usuario(
   id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
   temperatura_id integer NOT NULL,
   periodo_id integer NOT NULL,
   nome varchar(255) NOT NULL,
   FOREIGN KEY (temperatura_id) REFERENCES temperatura (id),
   FOREIGN KEY (periodo_id) REFERENCES periodo (id)
);

CREATE TABLE periodo(
   id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
--   temperatura_id integer NOT NULL,
   periodo varchar(255) NOT NULL
--   FOREIGN KEY (temperatura_id) REFERENCES temperatura (id)
);


