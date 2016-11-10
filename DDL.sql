CREATE USER 'evotor'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON *.* TO 'evotor'@'localhost';

CREATE DATABASE evotor_tst;

CREATE TABLE users (
	login VARCHAR(50) PRIMARY KEY,
	password VARCHAR(50) NOT NULL,
	balance NUMERIC(15,2) NOT NULL
);

CREATE UNIQUE INDEX users_index ON users (login);