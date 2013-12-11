CREATE TABLE medicao (
id int NOT NULL AUTO_INCREMENT,
idFermentacao int NOT NULL,
ph float,
densidade float,
temperatura float,
data date,
CONSTRAINT pkMedicao PRIMARY KEY (id),
CONSTRAINT checkPh CHECK (ph > -1 AND ph < 15),
CONSTRAINT checkDensidade CHECK (densidade > -1)
);

CREATE TABLE garrafa (
id int NOT NULL AUTO_INCREMENT,
tipo_tampa varchar(8) NOT NULL,
material varchar(8) NOT NULL,
volume float NOT NULL,
CONSTRAINT pkGarrafa PRIMARY KEY (id),
CONSTRAINT checkTipo_tampa CHECK (tipo_tampa IN ('metal', 'plástico', 'rolha')),
CONSTRAINT checkMaterial CHECK (material IN ('vidro', 'plástico')),
CONSTRAINT checkVolume CHECK (volume IN (0.36, 0.6, 1, 2, 5))
);

CREATE TABLE pessoa (
id int NOT NULL AUTO_INCREMENT,
nome varchar(45) NOT NULL,
CONSTRAINT pkPessoa PRIMARY KEY (id) 
);

CREATE TABLE investidor (
idPessoa int NOT NULL,
numero_principal varchar(10),
numero_celular varchar(10),
CONSTRAINT pkInvestidor PRIMARY KEY (idPessoa) 
);

CREATE TABLE pessoa_juridica (
idPessoa int NOT NULL,
cnpj varchar(11) NOT NULL,
data_fundacao date,
area_atuacao varchar(255),
CONSTRAINT pkPessoa_juridica PRIMARY KEY (idPessoa),
CONSTRAINT cnpjUnico UNIQUE (cnpj)
);

CREATE TABLE socio (
idPessoa int NOT NULL,
data_saida date,
data_entrada date NOT NULL,
CONSTRAINT pkSocio PRIMARY KEY (idPessoa)
);

CREATE TABLE investimento (
id int NOT NULL AUTO_INCREMENT,
idInvestidor int,
data date,
valor float,
CONSTRAINT pkInvestimento PRIMARY KEY (id),
CONSTRAINT checkValor CHECK (valor > 0)
);

CREATE TABLE pessoa_fisica (
idPessoa int NOT NULL,
cpf char(14) NOT NULL,
data_nascimento date,
CONSTRAINT pkPessoa_fisica PRIMARY KEY (idPessoa),
CONSTRAINT cpfUnico UNIQUE (cpf)
);

CREATE TABLE cargo (
id int NOT NULL AUTO_INCREMENT,
nome varchar(70) NOT NULL,
CONSTRAINT pkCargo PRIMARY KEY (id),
CONSTRAINT nomeUnico UNIQUE(nome)
);

CREATE TABLE departamento (
id int NOT NULL AUTO_INCREMENT,
nome varchar(70) NULL,
CONSTRAINT pkDepartamento PRIMARY KEY (id),
CONSTRAINT nomeUnico UNIQUE(nome)
);

CREATE TABLE funcao (
idSocio int NOT NULL,
idCargo int NOT NULL,
idDepartamento int NOT NULL,
CONSTRAINT pkFuncao PRIMARY KEY (idSocio, idCargo, idDepartamento) 
);

CREATE TABLE local (
id int NOT NULL AUTO_INCREMENT,
idSocio int,
cidade varchar(70),
cep varchar(12),
endereco varchar(100) NOT NULL,
CONSTRAINT pkLocal PRIMARY KEY (id)
);

CREATE TABLE fornecedor (
id int NOT NULL AUTO_INCREMENT,
email varchar(80),
numero_telefone varchar(10),
endereco varchar(255),
nome varchar(255) NOT NULL,
CONSTRAINT pkFornecedor PRIMARY KEY (id),
CONSTRAINT nomeUnico UNIQUE(nome)
);

CREATE TABLE insumo (
id int NOT NULL AUTO_INCREMENT,
nome varchar(80) NOT NULL,
tipo varchar(13) NOT NULL,
marca varchar(80),
descricao varchar(255) NOT NULL,
unidade_medida varchar(5) NOT NULL,
CONSTRAINT pkInsumo PRIMARY KEY (id),
CONSTRAINT checkTipo CHECK (tipo IN ('químico', 'ferramenta', 'matéria-prima'))
);

CREATE TABLE compra_isumo (
id int NOT NULL AUTO_INCREMENT,
idFornecedor int NOT NULL,
idInsumo int,
preco_unitario float NOT NULL,
quantidade int NOT NULL,
data date NOT NULL,
preco_total float,
CONSTRAINT pkCompra_insumo PRIMARY KEY (id),
CONSTRAINT checkPreco_unitario CHECK (preco_unitario > 0),
CONSTRAINT checkQuantidade CHECK (quantidade > 0)
);

CREATE TABLE maturacao (
id int NOT NULL AUTO_INCREMENT,
idLocal int,
idEngarrafamento int NOT NULL,
temperatura_ambiente float,
tempo float,
CONSTRAINT pkMaturacao PRIMARY KEY (id),
CONSTRAINT checkTempo CHECK (tempo > 0)
);

CREATE TABLE engarrafamento (
id int NOT NULL AUTO_INCREMENT,
idGarrafa int,
idLote int,
gaseificado boolean,
finalidade varchar(9) NOT NULL,
CONSTRAINT pkEngarrafamento PRIMARY KEY (id),
CONSTRAINT checkFinalidade CHECK (finalidade IN ('marketing', 'vendas', 'P&D'))
);

CREATE TABLE venda (
id int NOT NULL AUTO_INCREMENT,
data date,
valor_garrafa float,
idEngarrafamento int,
CONSTRAINT pkVenda PRIMARY KEY (id),
CONSTRAINT checkValor_garrafa CHECK (valor_garrafa > 0)
);

CREATE TABLE receita (
id int NOT NULL AUTO_INCREMENT,
idPessoa int,
reidratacao_fermento boolean,
ph_desejado float,
grad_alcoolica_desejada float,
fonte varchar(255),
descricao varchar(255) NOT NULL,
CONSTRAINT pkReceita PRIMARY KEY (id),
CONSTRAINT checkPh_desejado CHECK (ph_desejado > -1 AND ph_desejado < 15),
CONSTRAINT checkGrad_alcoolica_desejada CHECK (grad_alcoolica_desejada > 0)
);

CREATE TABLE inoculacao (
id int NOT NULL AUTO_INCREMENT,
data date,
idMedicao int NOT NULL,
idLocal int,
idReceita int,
CONSTRAINT pkInoculacao PRIMARY KEY (id)
);

CREATE TABLE fermentacao (
id int NOT NULL AUTO_INCREMENT,
idInoculacao int NOT NULL,
data_final date,
tipo char(6),
CONSTRAINT pkFermentacao PRIMARY KEY (id),
CONSTRAINT checkTipo CHECK (tipo IN ('aberta', 'selada')),
CONSTRAINT idInoculacaoUnico UNIQUE(idInoculacao)
);

CREATE TABLE lote (
id int NOT NULL AUTO_INCREMENT,
idFermentacao int NOT NULL,
volume float NOT NULL,
CONSTRAINT pkLote PRIMARY KEY (id),
CONSTRAINT checkVolume CHECK (volume > 0)
);

CREATE TABLE transferencia (
id int NOT NULL AUTO_INCREMENT,
idFermentacao int,
novo_fermentador varchar(255),
data date,
filtragem boolean,
CONSTRAINT pkTransferencia PRIMARY KEY (id)
);

CREATE TABLE sanitizacao (
id int NOT NULL AUTO_INCREMENT,
idQuimico int NOT NULL,
idSanitizado int NOT NULL,
data date,
quantidadeQuimico float NOT NULL,
CONSTRAINT pkSanitizacao PRIMARY KEY (id),
CONSTRAINT checkQuantidadeQuimico CHECK (quantidadeQuimico > 0)
);

CREATE TABLE insumo_inoculacao (
idInoculacao int NOT NULL,
idInsumo int,
quantidade float,
CONSTRAINT pkInsumo_inoculacao PRIMARY KEY (idInoculacao, idInsumo),
CONSTRAINT checkQuantidade CHECK (quantidade > 0)
);

CREATE TABLE responsavel_inoculacao (
idSocio int NOT NULL,
idInoculacao int NOT NULL,
CONSTRAINT pkResponsavel_inoculacao PRIMARY KEY (idSocio, idInoculacao) 
);

ALTER TABLE medicao ADD CONSTRAINT FK_medicao_fermentacao FOREIGN KEY (idFermentacao) REFERENCES fermentacao (id);
ALTER TABLE investidor ADD CONSTRAINT FK_pessoa_investidor FOREIGN KEY (idPessoa) REFERENCES pessoa (id);
ALTER TABLE investimento ADD CONSTRAINT FK_investimento_investidor FOREIGN KEY (idInvestidor) REFERENCES investidor (idPessoa);
ALTER TABLE pessoa_fisica ADD CONSTRAINT FK_pessoafisica_pessoa FOREIGN KEY (idPessoa) REFERENCES investidor (idPessoa);
ALTER TABLE pessoa_juridica ADD CONSTRAINT FK_pessoajuridica_pessoa FOREIGN KEY (idPessoa) REFERENCES investidor (idPessoa);
ALTER TABLE socio ADD CONSTRAINT FK_socio_pessoafisica FOREIGN KEY (idPessoa) REFERENCES pessoa_fisica (idPessoa);
ALTER TABLE funcao ADD CONSTRAINT FK_funcao_socio FOREIGN KEY (idSocio) REFERENCES socio (idPessoa);
ALTER TABLE funcao ADD CONSTRAINT FK_funcao_cargo FOREIGN KEY (idCargo) REFERENCES cargo (id);
ALTER TABLE funcao ADD CONSTRAINT FK_funcao_departamento FOREIGN KEY (idDepartamento) REFERENCES departamento (id);
ALTER TABLE local ADD CONSTRAINT FK_local_socio FOREIGN KEY (idSocio) REFERENCES socio (idPessoa);
ALTER TABLE compra_isumo ADD CONSTRAINT FK_comprainsumo_fornecedor FOREIGN KEY (idFornecedor) REFERENCES fornecedor (id);
ALTER TABLE compra_isumo ADD CONSTRAINT FK_comprainsumo_insumo FOREIGN KEY (idInsumo) REFERENCES insumo (id);
ALTER TABLE maturacao ADD CONSTRAINT FK_maturacao_local FOREIGN KEY (idLocal) REFERENCES local (id);
ALTER TABLE maturacao ADD CONSTRAINT FK_maturacao_engarrafamento FOREIGN KEY (idEngarrafamento) REFERENCES engarrafamento (id);
ALTER TABLE engarrafamento ADD CONSTRAINT FK_engarrafamento_garrafa FOREIGN KEY (idGarrafa) REFERENCES garrafa (id);
ALTER TABLE engarrafamento ADD CONSTRAINT FK_engarrafamento_lote FOREIGN KEY (idLote) REFERENCES lote (id);
ALTER TABLE venda ADD CONSTRAINT FK_venda_engarrafamento FOREIGN KEY (idEngarrafamento) REFERENCES engarrafamento (id);
ALTER TABLE receita ADD CONSTRAINT FK_receita_autor FOREIGN KEY (idPessoa) REFERENCES pessoa (id);
ALTER TABLE inoculacao ADD CONSTRAINT FK_inoculacao_medicao FOREIGN KEY (idMedicao) REFERENCES medicao (id);
ALTER TABLE inoculacao ADD CONSTRAINT FK_inoculacao_local FOREIGN KEY (idLocal) REFERENCES local (id);
ALTER TABLE inoculacao ADD CONSTRAINT FK_inoculacao_receita FOREIGN KEY (idReceita) REFERENCES receita (id);
ALTER TABLE fermentacao ADD CONSTRAINT FK_fermentacao_inoculacao FOREIGN KEY (idInoculacao) REFERENCES inoculacao (id);
ALTER TABLE lote ADD CONSTRAINT FK_lote_fermentacao FOREIGN KEY (idFermentacao) REFERENCES fermentacao (id);
ALTER TABLE transferencia ADD CONSTRAINT FK_transferencia_fermentacao FOREIGN KEY (idFermentacao) REFERENCES fermentacao (id);
ALTER TABLE sanitizacao ADD CONSTRAINT FK_sanitizacao_quimico FOREIGN KEY (idQuimico) REFERENCES insumo (id);
ALTER TABLE sanitizacao ADD CONSTRAINT FK_sanitizacao_sanitizado FOREIGN KEY (idSanitizado) REFERENCES insumo (id);
ALTER TABLE insumo_inoculacao ADD CONSTRAINT FK_uso_inoculacao FOREIGN KEY (idInoculacao) REFERENCES inoculacao (id);
ALTER TABLE insumo_inoculacao ADD CONSTRAINT FK_uso_insumo FOREIGN KEY (idInsumo) REFERENCES insumo (id);
ALTER TABLE responsavel_inoculacao ADD CONSTRAINT FK_responsavel_socio FOREIGN KEY (idSocio) REFERENCES socio (idPessoa);
ALTER TABLE responsavel_inoculacao ADD CONSTRAINT FK_responsavel_inoculacao FOREIGN KEY (idInoculacao) REFERENCES inoculacao (id);
