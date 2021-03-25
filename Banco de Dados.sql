create database db_ecommerce_pi;

use db_ecommerce_pi;

CREATE TABLE tb_categoria (
    id bigint(5) NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    ativo BOOLEAN NOT NULL,
    imagem varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tb_produto (
    id bigint(5) NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    descricao varchar(255) NOT NULL,
    preco DECIMAL(10,5) NOT NULL,
    marca varchar(255) NOT NULL,
    fornecedor varchar(255) NOT NULL,
    qtdEstoque bigint(5) NOT NULL,
    imagem varchar(255) NOT NULL,
    peso bigint(5) NOT NULL,
    categoria_id bigint,
    PRIMARY KEY (id),
	FOREIGN KEY (categoria_id) REFERENCES tb_categoria (id)
);

CREATE TABLE tb_usuario (
    id bigint(5) NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    senha varchar(255) NOT NULL,
    PRIMARY KEY (id)
);
