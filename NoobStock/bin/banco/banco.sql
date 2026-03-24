create database db_noobstock;
use db_noobstock;

create table usuarios (
	id_usuario int primary key auto_increment,
	nome_usuario varchar(50) not null unique,
    email varchar(50) unique,
    senha varchar(100) not null,
    data_criacao timestamp default current_timestamp
);

create table categoria (
    idcategoria int primary key auto_increment,
    nome varchar(45)
);

create table fornecedor (
    idfornecedor int primary key auto_increment,
    nome varchar(45),
    cnpj varchar(45),
    contato varchar(45)
);

create table movimentacao (
    idmovimentacao int primary key auto_increment,
    datahora datetime
);

create table produto (
    idproduto int primary key auto_increment,
    nome varchar(45),
    preco double,
    descricao varchar(45),
    numeroserie int,
    qtdestoque int,
    localizacao varchar(45),
    fornecedor_idfornecedor int,
    categoria_idcategoria int,
    foreign key (fornecedor_idfornecedor) references fornecedor(idfornecedor),
    foreign key (categoria_idcategoria) references categoria(idcategoria)
);

create table venda (
    idvenda int primary key auto_increment,
    datavenda datetime,
    status enum('pendente', 'concluida', 'cancelada'),
    usuario_idusuario int,
    valortotal double,
    movimentacao_idmovimentacao int,
    foreign key (usuario_idusuario) references usuarios(id_usuario),
    foreign key (movimentacao_idmovimentacao) references movimentacao(idmovimentacao)
);

create table itemvenda (
    venda_idvenda int,
    produto_idproduto int,
    qtdvendida int,
    precounitario double,
    primary key (venda_idvenda, produto_idproduto),
    foreign key (venda_idvenda) references venda(idvenda),
    foreign key (produto_idproduto) references produto(idproduto)
);