/* projeto_vacinacao_modelo_logico: */

drop database ProjetoVacina;

create database ProjetoVacina;

use ProjetoVacina;

CREATE TABLE unidade (
    idUnidade int auto_increment PRIMARY KEY,
    nome varchar(20) not null,
    centro enum('sim','não') default 'não',
    fk_idEndereco int
);

CREATE TABLE pessoa (
    nome varchar(20) not null,
    cpf varchar(15) unique,
    idPessoa int auto_increment PRIMARY KEY,
    fk_idEndereco int
);

CREATE TABLE endereco (
    idEndereco int auto_increment PRIMARY KEY,
    cidade varchar(20),
    bairro varchar(20),
    numero int,
    rua varchar(20),
    cep varchar(20),
    pais varchar(20),
    estado varchar(20),
    complemento varchar(20)
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
    FOREIGN KEY (fk_endereco_idEndereco)
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