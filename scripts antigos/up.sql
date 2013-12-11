ALTER TABLE 'medicao' DROP FOREIGN KEY 'FK_medicao_fermentacao';
ALTER TABLE 'investidor' DROP FOREIGN KEY 'FK_pessoa_investidor';
ALTER TABLE 'investimento' DROP FOREIGN KEY 'FK_investimento_investidor';
ALTER TABLE 'pessoa_fisica' DROP FOREIGN KEY 'FK_pessoafisica_pessoa';
ALTER TABLE 'pessoa_juridica' DROP FOREIGN KEY 'FK_pessoajuridica_pessoa';
ALTER TABLE 'socio' DROP FOREIGN KEY 'FK_socio_pessoafisica';
ALTER TABLE 'funcao' DROP FOREIGN KEY 'FK_funcao_socio';
ALTER TABLE 'funcao' DROP FOREIGN KEY 'FK_funcao_cargo';
ALTER TABLE 'funcao' DROP FOREIGN KEY 'FK_funcao_departamento';
ALTER TABLE 'local' DROP FOREIGN KEY 'FK_local_socio';
ALTER TABLE 'compra_isumo' DROP FOREIGN KEY 'FK_comprainsumo_fornecedor';
ALTER TABLE 'compra_isumo' DROP FOREIGN KEY 'FK_comprainsumo_insumo';
ALTER TABLE 'maturacao' DROP FOREIGN KEY 'FK_maturacao_local';
ALTER TABLE 'maturacao' DROP FOREIGN KEY 'FK_maturacao_engarrafamento';
ALTER TABLE 'engarrafamento' DROP FOREIGN KEY 'FK_engarrafamento_garrafa';
ALTER TABLE 'engarrafamento' DROP FOREIGN KEY 'FK_engarrafamento_lote';
ALTER TABLE 'venda' DROP FOREIGN KEY 'FK_venda_engarrafamento';
ALTER TABLE 'receita' DROP FOREIGN KEY 'FK_receita_autor';
ALTER TABLE 'inoculacao' DROP FOREIGN KEY 'FK_inoculacao_medicao';
ALTER TABLE 'inoculacao' DROP FOREIGN KEY 'FK_inoculacao_local';
ALTER TABLE 'inoculacao' DROP FOREIGN KEY 'FK_inoculacao_receita';
ALTER TABLE 'fermentacao' DROP FOREIGN KEY 'FK_fermentacao_inoculacao';
ALTER TABLE 'lote' DROP FOREIGN KEY 'FK_lote_fermentacao';
ALTER TABLE 'transferencia' DROP FOREIGN KEY 'FK_transferencia_fermentacao';
ALTER TABLE 'sanitizacao' DROP FOREIGN KEY 'FK_sanitizacao_quimico';
ALTER TABLE 'sanitizacao' DROP FOREIGN KEY 'FK_sanitizacao_sanitizado';
ALTER TABLE 'insumo_inoculacao' DROP FOREIGN KEY 'FK_uso_inoculacao';
ALTER TABLE 'insumo_inoculacao' DROP FOREIGN KEY 'FK_uso_insumo';
ALTER TABLE 'responsavel_inoculacao' DROP FOREIGN KEY 'FK_responsavel_socio';
ALTER TABLE 'responsavel_inoculacao' DROP FOREIGN KEY 'FK_responsavel_inoculacao';

ALTER TABLE 'medicao'DROP PRIMARY KEY;
ALTER TABLE 'garrafa'DROP PRIMARY KEY;
ALTER TABLE 'pessoa'DROP PRIMARY KEY;
ALTER TABLE 'investidor'DROP PRIMARY KEY;
ALTER TABLE 'pessoa_juridica'DROP PRIMARY KEY;
ALTER TABLE 'socio'DROP PRIMARY KEY;
ALTER TABLE 'investimento'DROP PRIMARY KEY;
ALTER TABLE 'pessoa_fisica'DROP PRIMARY KEY;
ALTER TABLE 'cargo'DROP PRIMARY KEY;
ALTER TABLE 'departamento'DROP PRIMARY KEY;
ALTER TABLE 'funcao'DROP PRIMARY KEY;
ALTER TABLE 'local'DROP PRIMARY KEY;
ALTER TABLE 'fornecedor'DROP PRIMARY KEY;
ALTER TABLE 'insumo'DROP PRIMARY KEY;
ALTER TABLE 'compra_isumo'DROP PRIMARY KEY;
ALTER TABLE 'maturacao'DROP PRIMARY KEY;
ALTER TABLE 'engarrafamento'DROP PRIMARY KEY;
ALTER TABLE 'venda'DROP PRIMARY KEY;
ALTER TABLE 'receita'DROP PRIMARY KEY;
ALTER TABLE 'inoculacao'DROP PRIMARY KEY;
ALTER TABLE 'fermentacao'DROP PRIMARY KEY;
ALTER TABLE 'lote'DROP PRIMARY KEY;
ALTER TABLE 'transferencia'DROP PRIMARY KEY;
ALTER TABLE 'sanitizacao'DROP PRIMARY KEY;
ALTER TABLE 'insumo_inoculacao'DROP PRIMARY KEY;
ALTER TABLE 'responsavel_inoculacao'DROP PRIMARY KEY;

DROP TABLE 'medicao';
DROP TABLE 'garrafa';
DROP TABLE 'pessoa';
DROP TABLE 'investidor';
DROP TABLE 'pessoa_juridica';
DROP TABLE 'socio';
DROP TABLE 'investimento';
DROP TABLE 'pessoa_fisica';
DROP TABLE 'cargo';
DROP TABLE 'departamento';
DROP TABLE 'funcao';
DROP TABLE 'local';
DROP TABLE 'fornecedor';
DROP TABLE 'insumo';
DROP TABLE 'compra_isumo';
DROP TABLE 'maturacao';
DROP TABLE 'engarrafamento';
DROP TABLE 'venda';
DROP TABLE 'receita';
DROP TABLE 'inoculacao';
DROP TABLE 'fermentacao';
DROP TABLE 'lote';
DROP TABLE 'transferencia';
DROP TABLE 'sanitizacao';
DROP TABLE 'insumo_inoculacao';
DROP TABLE 'responsavel_inoculacao';

CREATE TABLE 'medicao' (
'id' int NOT NULL,
'idFermentacao' int NOT NULL,
'ph' numeric NULL,
'densidade' numeric NULL,
'temperatura' numeric NULL,
'data' date NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'garrafa' (
'id' int NOT NULL,
'tipo_tampa' enum('metal','plastico','rolha') NULL DEFAULT '',
'material' enum('vidro','plastico') NULL DEFAULT '',
PRIMARY KEY ('id') 
);

CREATE TABLE 'pessoa' (
'id' int NOT NULL,
'nome' varchar(255) NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'investidor' (
'idPessoa' int NOT NULL,
'numero_principal' varchar(10) NULL,
'numero_celular' varchar(10) NULL,
PRIMARY KEY ('idPessoa') 
);

CREATE TABLE 'pessoa_juridica' (
'idPessoa' int NOT NULL,
'cnpj' varchar(11) NOT NULL,
'data_fundacao' date NULL,
'area_atuacao' varchar(255) NULL,
PRIMARY KEY ('idPessoa') 
);

CREATE TABLE 'socio' (
'idPessoa' int NOT NULL,
'data_saida' date NULL,
'data_entrada' date NOT NULL,
PRIMARY KEY ('idPessoa') 
);

CREATE TABLE 'investimento' (
'id' int NOT NULL,
'idInvestidor' int NULL,
'data' date NULL,
'valor' numeric NULL COMMENT '>0',
PRIMARY KEY ('id') 
);

CREATE TABLE 'pessoa_fisica' (
'idPessoa' int NOT NULL,
'data_nascimento' date NULL,
'cpf' char(7) NOT NULL,
PRIMARY KEY ('idPessoa') 
);

CREATE TABLE 'cargo' (
'id' int NOT NULL,
'nome' varchar(255) NOT NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'departamento' (
'id' int NOT NULL,
'nome' varchar(255) NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'funcao' (
'idSocio' int NULL,
'idCargo' int NULL,
'idDepartamento' int NULL,
PRIMARY KEY ('idSocio', 'idCargo', 'idDepartamento') 
);

CREATE TABLE 'local' (
'id' int NOT NULL,
'idSocio' int NULL,
'cidade' varchar(255) NULL,
'ceo' char(7) NULL,
'endereco' varchar(255) NOT NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'fornecedor' (
'id' int NULL,
'email' varchar(255) NULL,
'numero_telefone' varchar(10) NULL,
'endereco' varchar(255) NULL,
'nome' varchar(255) NOT NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'insumo' (
'id' int NULL,
'nome' varchar(255) NOT NULL,
'tipo' enum('quimico','ferramenta','materia-prima') NOT NULL DEFAULT '',
'marca' varchar(255) NULL,
'descricao' varchar(255) NOT NULL,
'unidade_medida' varchar(4) NOT NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'compra_isumo' (
'id' int NULL,
'idFornecedor' int NOT NULL,
'idInsumo' int NULL,
'preco_unitario' numeric NOT NULL COMMENT '>0',
'quantidade' int NOT NULL COMMENT '>0',
'data' date NOT NULL,
'preco_total' numeric NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'maturacao' (
'id' int NULL,
'idLocal' int NULL,
'idEngarrafamento' int NOT NULL,
'temperatura_ambiente' numeric NULL,
'tempo' numeric NULL COMMENT '>0',
PRIMARY KEY ('id') 
);

CREATE TABLE 'engarrafamento' (
'id' int NULL,
'idGarrafa' int NULL,
'idLote' int NULL,
'gaseificado' bit(1) NULL,
'finalidade' enum('marketing','vendas','P&D') NOT NULL DEFAULT '',
PRIMARY KEY ('id') 
);

CREATE TABLE 'venda' (
'id' int NULL,
'data' date NULL,
'valor_garrafa' numeric NULL COMMENT '>0',
'idEngarrafamento' int NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'receita' (
'id' int NULL,
'idPessoa' int NULL,
'reidratacao_fermento' bit(1) NULL,
'ph_desejado' numeric NULL COMMENT '0-14',
'grad_alcoolica_desejada' numeric NULL COMMENT '>0',
'fonte' varchar(255) NULL,
'descricao' varchar(255) NOT NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'inoculacao' (
'id' int NULL,
'data' date NULL,
'idMedicao' int NOT NULL,
'idLocal' int NULL,
'idReceita' int NULL,
'idSocio' varchar(255) NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'fermentacao' (
'id' int NULL,
'idInoculacao' int NOT NULL,
'data_final' date NULL,
'tipo' enum('aberta','selada') NULL DEFAULT '',
PRIMARY KEY ('id') 
);

CREATE TABLE 'lote' (
'id' int NULL,
'idFermentacao' int NULL,
'volume' numeric NOT NULL COMMENT '>0',
PRIMARY KEY ('id') 
);

CREATE TABLE 'transferencia' (
'id' int NULL,
'idFermentacao' int NULL,
'novo_fermentador' varchar(255) NULL,
'data' date NULL,
'filtragem' bit(1) NULL,
PRIMARY KEY ('id') 
);

CREATE TABLE 'sanitizacao' (
'idQuimico' int NULL,
'idSanitizado' int NULL,
PRIMARY KEY ('idQuimico', 'idSanitizado') 
);

CREATE TABLE 'insumo_inoculacao' (
'idInoculacao' int NULL,
'inInsumo' int NULL,
'quantidade' numeric NULL COMMENT '>0',
PRIMARY KEY ('idInoculacao', 'inInsumo') 
);

CREATE TABLE 'responsavel_inoculacao' (
'idSocio' int NOT NULL,
'idInoculacao' int NOT NULL,
PRIMARY KEY ('idSocio', 'idInoculacao') 
);


ALTER TABLE 'medicao' ADD CONSTRAINT 'FK_medicao_fermentacao' FOREIGN KEY ('idFermentacao') REFERENCES 'fermentacao' ('id');
ALTER TABLE 'investidor' ADD CONSTRAINT 'FK_pessoa_investidor' FOREIGN KEY ('idPessoa') REFERENCES 'pessoa' ('id');
ALTER TABLE 'investimento' ADD CONSTRAINT 'FK_investimento_investidor' FOREIGN KEY ('idInvestidor') REFERENCES 'investidor' ('idPessoa');
ALTER TABLE 'pessoa_fisica' ADD CONSTRAINT 'FK_pessoafisica_pessoa' FOREIGN KEY ('idPessoa') REFERENCES 'investidor' ('idPessoa');
ALTER TABLE 'pessoa_juridica' ADD CONSTRAINT 'FK_pessoajuridica_pessoa' FOREIGN KEY ('idPessoa') REFERENCES 'investidor' ('idPessoa');
ALTER TABLE 'socio' ADD CONSTRAINT 'FK_socio_pessoafisica' FOREIGN KEY ('idPessoa') REFERENCES 'pessoa_fisica' ('idPessoa');
ALTER TABLE 'funcao' ADD CONSTRAINT 'FK_funcao_socio' FOREIGN KEY ('idSocio') REFERENCES 'socio' ('idPessoa');
ALTER TABLE 'funcao' ADD CONSTRAINT 'FK_funcao_cargo' FOREIGN KEY ('idCargo') REFERENCES 'cargo' ('id');
ALTER TABLE 'funcao' ADD CONSTRAINT 'FK_funcao_departamento' FOREIGN KEY ('idDepartamento') REFERENCES 'departamento' ('id');
ALTER TABLE 'local' ADD CONSTRAINT 'FK_local_socio' FOREIGN KEY ('idSocio') REFERENCES 'socio' ('idPessoa');
ALTER TABLE 'compra_isumo' ADD CONSTRAINT 'FK_comprainsumo_fornecedor' FOREIGN KEY ('idFornecedor') REFERENCES 'fornecedor' ('id');
ALTER TABLE 'compra_isumo' ADD CONSTRAINT 'FK_comprainsumo_insumo' FOREIGN KEY ('idInsumo') REFERENCES 'insumo' ('id');
ALTER TABLE 'maturacao' ADD CONSTRAINT 'FK_maturacao_local' FOREIGN KEY ('idLocal') REFERENCES 'local' ('id');
ALTER TABLE 'maturacao' ADD CONSTRAINT 'FK_maturacao_engarrafamento' FOREIGN KEY ('idEngarrafamento') REFERENCES 'engarrafamento' ('id');
ALTER TABLE 'engarrafamento' ADD CONSTRAINT 'FK_engarrafamento_garrafa' FOREIGN KEY ('idGarrafa') REFERENCES 'garrafa' ('id');
ALTER TABLE 'engarrafamento' ADD CONSTRAINT 'FK_engarrafamento_lote' FOREIGN KEY ('idLote') REFERENCES 'lote' ('id');
ALTER TABLE 'venda' ADD CONSTRAINT 'FK_venda_engarrafamento' FOREIGN KEY ('idEngarrafamento') REFERENCES 'engarrafamento' ('id');
ALTER TABLE 'receita' ADD CONSTRAINT 'FK_receita_autor' FOREIGN KEY ('idPessoa') REFERENCES 'pessoa' ('id');
ALTER TABLE 'inoculacao' ADD CONSTRAINT 'FK_inoculacao_medicao' FOREIGN KEY ('idMedicao') REFERENCES 'medicao' ('id');
ALTER TABLE 'inoculacao' ADD CONSTRAINT 'FK_inoculacao_local' FOREIGN KEY ('idLocal') REFERENCES 'local' ('id');
ALTER TABLE 'inoculacao' ADD CONSTRAINT 'FK_inoculacao_receita' FOREIGN KEY ('idReceita') REFERENCES 'receita' ('id');
ALTER TABLE 'fermentacao' ADD CONSTRAINT 'FK_fermentacao_inoculacao' FOREIGN KEY ('idInoculacao') REFERENCES 'inoculacao' ('id');
ALTER TABLE 'lote' ADD CONSTRAINT 'FK_lote_fermentacao' FOREIGN KEY ('idFermentacao') REFERENCES 'fermentacao' ('id');
ALTER TABLE 'transferencia' ADD CONSTRAINT 'FK_transferencia_fermentacao' FOREIGN KEY ('idFermentacao') REFERENCES 'fermentacao' ('id');
ALTER TABLE 'sanitizacao' ADD CONSTRAINT 'FK_sanitizacao_quimico' FOREIGN KEY ('idQuimico') REFERENCES 'insumo' ('id');
ALTER TABLE 'sanitizacao' ADD CONSTRAINT 'FK_sanitizacao_sanitizado' FOREIGN KEY ('idSanitizado') REFERENCES 'insumo' ('id');
ALTER TABLE 'insumo_inoculacao' ADD CONSTRAINT 'FK_uso_inoculacao' FOREIGN KEY ('idInoculacao') REFERENCES 'inoculacao' ('id');
ALTER TABLE 'insumo_inoculacao' ADD CONSTRAINT 'FK_uso_insumo' FOREIGN KEY ('inInsumo') REFERENCES 'insumo' ('id');
ALTER TABLE 'responsavel_inoculacao' ADD CONSTRAINT 'FK_responsavel_socio' FOREIGN KEY ('idSocio') REFERENCES 'socio' ('idPessoa');
ALTER TABLE 'responsavel_inoculacao' ADD CONSTRAINT 'FK_responsavel_inoculacao' FOREIGN KEY ('idInoculacao') REFERENCES 'inoculacao' ('id');

