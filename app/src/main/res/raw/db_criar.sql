CREATE TABLE login(
    ind integer NOT NULL PRIMARY KEY AUTOINCREMENT,
    usuario varchar,
    senha varchar
);

CREATE TABLE usuario(
   id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
   nome varchar(255) NOT NULL,
   frio varchar(255) NOT NULL,
   calor varchar(255) NOT NULL,
   chuva varchar(255) NOT NULL
);


