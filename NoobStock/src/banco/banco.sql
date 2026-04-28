CREATE DATABASE IF NOT EXISTS db_noobstock;
USE db_noobstock;

CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome_usuario VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(50) UNIQUE,
    senha VARCHAR(100) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS fornecedor (
    idfornecedor INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    cnpj VARCHAR(45),
    contato VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS categoria (
    idcategoria INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS produto (
    idproduto INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    SKU VARCHAR(45),
    numeroserie VARCHAR(45),
    qtdestoque INT DEFAULT 0,
    estoque_minimo INT DEFAULT 0,
    localizacao VARCHAR(45),
    fornecedor_id INT,
    categoria_id INT,  
    preco DOUBLE,
    descricao VARCHAR(45),
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(idfornecedor),
    FOREIGN KEY (categoria_id) REFERENCES categoria(idcategoria)
);

CREATE TABLE IF NOT EXISTS movimentacao (
    idmovimentacao INT PRIMARY KEY AUTO_INCREMENT,
    produto_id INT NOT NULL,
    usuario_id INT NOT NULL,
    tipo ENUM('entrada', 'saida', 'ajuste') NOT NULL,
    quantidade INT NOT NULL,
    motivo VARCHAR(255),
    datahora DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (produto_id) REFERENCES produto(idproduto),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usuario)
);

CREATE TABLE IF NOT EXISTS venda (
    idvenda INT PRIMARY KEY AUTO_INCREMENT,
    datavenda DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pendente', 'concluida', 'cancelada') DEFAULT 'pendente',
    usuario_idusuario INT,
    valortotal DOUBLE,
    FOREIGN KEY (usuario_idusuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE IF NOT EXISTS itemvenda (
    venda_idvenda INT,
    produto_idproduto INT,
    qtdvendida INT,
    precounitario DOUBLE,
    PRIMARY KEY (venda_idvenda, produto_idproduto),
    FOREIGN KEY (venda_idvenda) REFERENCES venda(idvenda),
    FOREIGN KEY (produto_idproduto) REFERENCES produto(idproduto)
);
