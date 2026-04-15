create database if not exists db_noobstock;
use db_noobstock;

create table usuarios (
    id_usuario    int          primary key auto_increment,
    nome_usuario  varchar(50)  not null unique,
    email         varchar(50)  unique,
    senha         varchar(255) not null,
    perfil        enum('operador', 'gestor') not null default 'operador',
    data_criacao  timestamp    default current_timestamp
);

create table fornecedor (
    idfornecedor  int         primary key auto_increment,
    nome          varchar(45) not null,
    cnpj          varchar(18),
    contato       varchar(45)
);

create table categoria (
    idcategoria   int         primary key auto_increment,
    nome          varchar(45) not null
);

create table produto (
    idproduto           int          primary key auto_increment,
    nome                varchar(45)  not null,
    preco               double,
    descricao           varchar(255),
    numeroserie         varchar(45),
    qtdestoque          int          not null default 0,
    estoque_minimo      int          not null default 0,
    localizacao         varchar(45),
    fornecedor_id       int,
    categoria_id        int,
    foreign key (fornecedor_id)  references fornecedor(idfornecedor),
    foreign key (categoria_id)   references categoria(idcategoria)
);

create table movimentacao (
    idmovimentacao  int      primary key auto_increment,
    produto_id      int      not null,
    usuario_id      int      not null,
    tipo            enum('entrada', 'saida', 'ajuste') not null,
    quantidade      int      not null,
    motivo          varchar(100),
    datahora        datetime default current_timestamp,
    foreign key (produto_id)  references produto(idproduto),
    foreign key (usuario_id)  references usuarios(id_usuario)
);

create table venda (
    idvenda                   int    primary key auto_increment,
    datavenda                 datetime default current_timestamp,
    status                    enum('pendente', 'concluida', 'cancelada') not null default 'pendente',
    usuario_id                int    not null,
    valortotal                double,
    movimentacao_id           int,
    foreign key (usuario_id)           references usuarios(id_usuario),
    foreign key (movimentacao_id)      references movimentacao(idmovimentacao)
);

create table itemvenda (
    venda_id        int    not null,
    produto_id      int    not null,
    qtdvendida      int    not null,
    precounitario   double not null,
    primary key (venda_id, produto_id),
    foreign key (venda_id)   references venda(idvenda),
    foreign key (produto_id) references produto(idproduto)
);

create table alerta (
    idalerta    int         primary key auto_increment,
    produto_id  int         not null,
    mensagem    varchar(255),
    status      enum('ativo', 'resolvido') not null default 'ativo',
    datahora    datetime    default current_timestamp,
    foreign key (produto_id) references produto(idproduto)
);
