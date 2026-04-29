create database if not exists db_noobstock;
use db_noobstock;

create table if not exists usuarios (
    id_usuario int primary key auto_increment,
    nome_usuario varchar(50) not null unique,
    email varchar(50) unique,
    senha varchar(100) not null,
    data_criacao timestamp default current_timestamp
);

create table if not exists fornecedor (
    idfornecedor int primary key auto_increment,
    nome varchar(45) not null,
    cnpj varchar(45),
    contato varchar(45)
);

create table if not exists categoria (
    idcategoria int primary key auto_increment,
    nome varchar(45) not null
);

create table if not exists produto (
    idproduto int primary key auto_increment,
    nome varchar(45) not null,
    SKU varchar(45),
    numeroserie varchar(45),
    qtdestoque int default 0,
    estoque_minimo int default 0,
    localizacao varchar(45),
    fornecedor_id int,
    categoria_id int,  
    preco double,
    descricao varchar(45),
    foreign key (fornecedor_id) references fornecedor(idfornecedor),
    foreign key (categoria_id) references categoria(idcategoria)
);

create table if not exists movimentacao (
    idmovimentacao int primary key auto_increment,
    produto_id int not null,
    usuario_id int not null,
    tipo enum('entrada', 'saida', 'ajuste') not null,
    quantidade int not null,
    motivo varchar(255),
    datahora datetime default current_timestamp,
    foreign key (produto_id) references produto(idproduto),
    foreign key (usuario_id) references usuarios(id_usuario)
);

create table if not exists venda (
    idvenda int primary key auto_increment,
    datavenda datetime default current_timestamp,
    status enum('pendente', 'concluida', 'cancelada') default 'pendente',
    usuario_idusuario int,
    valortotal double,
    foreign key (usuario_idusuario) references usuarios(id_usuario)
);

create table if not exists itemvenda (
    venda_idvenda int,
    produto_idproduto int,
    qtdvendida int,
    precounitario double,
    primary key (venda_idvenda, produto_idproduto),
    foreign key (venda_idvenda) references venda(idvenda),
    foreign key (produto_idproduto) references produto(idproduto)
);