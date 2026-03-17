create database db_noobstock;
use db_noobstock;

create table usuarios (
	id_usuario int primary key auto_increment,
	nome_usuario varchar(50) not null unique,
    email varchar(50) unique,
    senha varchar(100) not null,
    data_criacao timestamp default current_timestamp
);