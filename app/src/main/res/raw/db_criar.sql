CREATE TABLE login(
    id integer N PRIMARY KEY,
    usuario varchar,
    senha varchar
);

CREATE TABLE usuario(
   id integer PRIMARY KEY,
   nome varchar(255) NOT NULL,
   frio varchar(255) NOT NULL,
   calor varchar(255) NOT NULL,
   chuva varchar(255) NOT NULL
);


