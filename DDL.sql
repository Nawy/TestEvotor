CREATE DATABASE evotor_tst;

CREATE TABLE users (
	login VARCHAR(50) PRIMARY KEY UNIQUE,
	password VARCHAR(50) NOT NULL,
	balance NUMERIC(15,2) NOT NULL
);

CREATE UNIQUE INDEX users_index ON users (login);