/* projeto_vacinacao_modelo_logico: */

drop database projetovacina;

create database projetovacina;

use projetovacina;

CREATE TABLE unidade (
    idUnidade int auto_increment PRIMARY KEY,
    nome varchar(50) not null unique,
    centro enum('sim','não') default 'não',
    fk_idEndereco int
);

CREATE TABLE pessoa (
    nome varchar(50) not null,
    cpf varchar(15) unique,
    idPessoa int auto_increment PRIMARY KEY,
    fk_idEndereco int
);

CREATE TABLE endereco (
    idEndereco int auto_increment PRIMARY KEY,
    cidade varchar(50),
    bairro varchar(50),
    numero int,
    rua varchar(50),
    cep varchar(50),
    pais varchar(50),
    estado varchar(50),
    complemento varchar(50)
);

CREATE TABLE estoque (
    quantidade int,
    fk_idUnidade int,
    fk_lote int,
    PRIMARY KEY (fk_idUnidade, fk_lote)
);

CREATE TABLE lote (
    lote int auto_increment PRIMARY KEY,
    data_validade date
);

CREATE TABLE movimento_estoque (
    sequencia int,
    data_movimento datetime,
    quantidade int,
    tipo_transacao enum('rec','tra', 'apl'),
    fk_idPessoa int,
    fk_idUnidade int,
    fk_lote int,
    PRIMARY KEY (sequencia, fk_idUnidade, fk_lote)
);
 
ALTER TABLE unidade ADD CONSTRAINT FK_unidade_2
    FOREIGN KEY (fk_idEndereco)
    REFERENCES endereco (idEndereco)
    ON DELETE RESTRICT;
 
ALTER TABLE pessoa ADD CONSTRAINT FK_pessoa_2
    FOREIGN KEY (fk_idEndereco)
    REFERENCES endereco (idEndereco)
    ON DELETE RESTRICT;
 
ALTER TABLE estoque ADD CONSTRAINT FK_estoque_1
    FOREIGN KEY (fk_idUnidade)
    REFERENCES unidade (idUnidade)
    ON DELETE RESTRICT;
 
ALTER TABLE estoque ADD CONSTRAINT FK_estoque_2
    FOREIGN KEY (fk_lote)
    REFERENCES lote (lote)
    ON DELETE RESTRICT;
 
ALTER TABLE movimento_estoque ADD CONSTRAINT FK_movimento_estoque_2
    FOREIGN KEY (fk_idPessoa)
    REFERENCES pessoa (idPessoa)
    ON DELETE CASCADE;
 
ALTER TABLE movimento_estoque ADD CONSTRAINT FK_movimento_estoque_3
    FOREIGN KEY (fk_idUnidade)
    REFERENCES unidade (idUnidade)
    ON DELETE RESTRICT;
 
ALTER TABLE movimento_estoque ADD CONSTRAINT FK_movimento_estoque_4
    FOREIGN KEY (fk_lote)
    REFERENCES lote (lote)
    ON DELETE RESTRICT;

use projetovacina;

INSERT INTO endereco
(idEndereco, cep, pais, estado, cidade, bairro, rua, numero, complemento)
VALUES
(1, '93010-020', 'Brasil', 'RS', 'São Leopoldo', 'Centro', 'R. Dom João Becker', 745, 'nenhum');

INSERT INTO unidade
(nome, centro, fk_idEndereco)
VALUES
('Secretaria Municipal De Saúde', 'sim', 1);

INSERT INTO endereco
(idEndereco, cep, pais, estado, cidade, bairro, rua, numero, complemento)
VALUES
(2, '93020-690', 'Brasil', 'RS', 'São Leopoldo', 'São José', 'Av. João Corrêa', 657, 'nenhum');

INSERT INTO unidade
(nome, centro, fk_idEndereco)
VALUES
('Estação São Leopoldo', 'não', 2);

INSERT INTO endereco
(idEndereco, cep, pais, estado, cidade, bairro, rua, numero, complemento)
VALUES
(3, '93042-400','Brasil', 'RS', 'São Leopoldo', 'Rua Felipe Schiel', 155, 'casa 1');

INSERT INTO pessoa
(idPessoa, nome, cpf, fk_idEndereco)
VALUES
(1, 'Joszef Barrionuevo', '020.503.860-36', 3);

INSERT INTO lote
(lote, data_valSanto Andréidade)
VALUES
(1, '2030/12/31');

INSERT INTO movimento_estoque
(sequencia, data_movimento, tipo_transacao, quantidade, fk_idUnidade, fk_lote, fk_idPessoa)
VALUES
(1, '2022/05/06', 'rec', 100, 1, 1, null);

INSERT INTO lote
(lote, data_validade)
VALUES
(2, '2030/12/31');

INSERT INTO movimento_estoque
(sequencia, data_movimento, tipo_transacao, quantidade, fk_idUnidade, fk_lote, fk_idPessoa)
VALUES
(1, '2022/05/06', 'rec', 500, 1, 2, null);


select * from endereco;
select * from unidade;
select * from pessoa;
select * from lote;
select * from estoque;
select * from movimento_estoque;

select * from lote where lote.lote = 1;

SELECT *
FROM estoque, lote, unidade, endereco
WHERE
estoque.fk_lote = lote.lote AND
estoque.fk_idUnidade = unidade.idUnidade AND
endereco.idEndereco = unidade.fk_idEndereco;


SELECT
unidade.idUnidade, unidade.nome, unidade.centro, unidade.fk_idEndereco,
endereco.idEndereco, endereco.cidade, endereco.bairro, endereco.numero, endereco.rua, endereco.cep, endereco.pais, endereco.estado, endereco.complemento,
lote.lote, lote.data_validade,
estoque.quantidade, estoque.fk_idUnidade, estoque.fk_lote
FROM estoque, lote, unidade, endereco
WHERE
estoque.fk_lote = lote.lote AND
estoque.fk_idUnidade = unidade.idUnidade AND
endereco.idEndereco = unidade.fk_idEndereco;

SELECT
unidade.idUnidade, unidade.nome, unidade.centro, unidade.fk_idEndereco,
endereco.idEndereco, endereco.cidade, endereco.bairro, endereco.numero, endereco.rua, endereco.cep, endereco.pais, endereco.estado, endereco.complemento,
lote.lote, lote.data_validade,
estoque.quantidade, estoque.fk_idUnidade, estoque.fk_lote
FROM estoque, lote, unidade, endereco
WHERE
estoque.fk_lote = lote.lote AND
estoque.fk_idUnidade = unidade.idUnidade AND
endereco.idEndereco = unidade.fk_idEndereco AND
estoque.fk_lote = 1 AND
estoque.fk_idUnidade = 1;

SELECT
unidade.idUnidade, unidade.nome, unidade.centro, unidade.fk_idEndereco,
endereco.idEndereco, endereco.cidade, endereco.bairro, endereco.numero, endereco.rua, endereco.cep, endereco.pais, endereco.estado, endereco.complemento,
lote.lote, lote.data_validade,
estoque.quantidade, estoque.fk_idUnidade, estoque.fk_lote
FROM estoque, lote, unidade, endereco
WHERE
estoque.fk_lote = lote.lote AND
estoque.fk_idUnidade = unidade.idUnidade AND
endereco.idEndereco = unidade.fk_idEndereco AND
estoque.fk_idUnidade = 1;

SELECT *
FROM estoque, lote, unidade, endereco, movimento_estoque, pessoa
WHERE
estoque.fk_lote = lote.lote AND
estoque.fk_idUnidade = unidade.idUnidade AND
endereco.idEndereco = unidade.fk_idEndereco AND
movimento_estoque.fk_idPessoa = pessoa.idPessoa AND
movimento_estoque.fk_idUnidade = unidade.idUnidade AND
movimento_estoque.fk_lote = lote.lote AND
estoque.fk_idUnidade = 1 AND
movimento_estoque.tipo_transacao = 'apl';