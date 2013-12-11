/*
 Navicat Premium Data Transfer

 Source Server         : MAMP
 Source Server Type    : MySQL
 Source Server Version : 50529
 Source Host           : 127.0.0.1
 Source Database       : hidromel

 Target Server Type    : MySQL
 Target Server Version : 50529
 File Encoding         : utf-8

 Date: 11/28/2013 00:57:24 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `cargo`
-- ----------------------------
DROP TABLE IF EXISTS `cargo`;
CREATE TABLE `cargo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nomeUnico` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `cargo`
-- ----------------------------
BEGIN;
INSERT INTO `cargo` VALUES ('2', 'auxiliar'), ('1', 'gerente');
COMMIT;

-- ----------------------------
--  Table structure for `compra_insumo`
-- ----------------------------
DROP TABLE IF EXISTS `compra_insumo`;
CREATE TABLE `compra_insumo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idFornecedor` int(11) NOT NULL,
  `idInsumo` int(11) DEFAULT NULL,
  `preco_unitario` float NOT NULL,
  `quantidade` int(11) NOT NULL,
  `data` date NOT NULL,
  `preco_total` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_comprainsumo_fornecedor` (`idFornecedor`),
  KEY `FK_comprainsumo_insumo` (`idInsumo`),
  CONSTRAINT `FK_comprainsumo_fornecedor` FOREIGN KEY (`idFornecedor`) REFERENCES `fornecedor` (`id`),
  CONSTRAINT `FK_comprainsumo_insumo` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `compra_insumo`
-- ----------------------------
BEGIN;
INSERT INTO `compra_insumo` VALUES ('1', '4', '1', '10', '20', '2013-09-12', '200'), ('2', '4', '2', '14', '10', '2013-11-19', '140'), ('3', '3', '3', '12', '10', '2013-11-22', '120'), ('4', '5', '4', '80', '1', '2013-11-22', '80'), ('5', '5', '5', '48.9', '1', '2013-10-25', '48.9'), ('6', '2', '6', '1.2', '40', '2013-11-03', '48'), ('7', '5', '7', '92.5', '1', '2013-11-22', '92.5'), ('8', '5', '8', '22.4', '1', '2013-10-01', '22.4'), ('9', '1', '9', '0.7', '20', '2013-10-04', '14'), ('10', '5', '10', '7.9', '80', '2013-10-25', '632'), ('11', '2', '11', '33', '1', '2013-11-10', '33'), ('12', '5', '12', '0.6', '300', '2013-11-08', '180'), ('13', '5', '13', '0.2', '300', '2013-11-04', '60'), ('14', '5', '14', '1.3', '50', '2013-11-04', '65'), ('15', '1', '15', '0.4', '500', '2013-11-21', '200'), ('16', '5', '16', '2', '100', '2013-11-22', '200'), ('17', '2', '9', '0.3', '30', '2013-11-27', '9');
COMMIT;

-- ----------------------------
--  Table structure for `departamento`
-- ----------------------------
DROP TABLE IF EXISTS `departamento`;
CREATE TABLE `departamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nomeUnico` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `departamento`
-- ----------------------------
BEGIN;
INSERT INTO `departamento` VALUES ('3', 'financeiro'), ('2', 'marketing'), ('1', 'producao');
COMMIT;

-- ----------------------------
--  Table structure for `engarrafamento`
-- ----------------------------
DROP TABLE IF EXISTS `engarrafamento`;
CREATE TABLE `engarrafamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idGarrafa` int(11) DEFAULT NULL,
  `idLote` int(11) DEFAULT NULL,
  `gaseificado` tinyint(1) DEFAULT NULL,
  `finalidade` varchar(9) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_engarrafamento_garrafa` (`idGarrafa`),
  KEY `FK_engarrafamento_lote` (`idLote`),
  CONSTRAINT `FK_engarrafamento_garrafa` FOREIGN KEY (`idGarrafa`) REFERENCES `garrafa` (`id`),
  CONSTRAINT `FK_engarrafamento_lote` FOREIGN KEY (`idLote`) REFERENCES `lote` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `engarrafamento`
-- ----------------------------
BEGIN;
INSERT INTO `engarrafamento` VALUES ('1', '1', '1', '0', 'venda'), ('2', '2', '1', '0', 'P&D'), ('3', '3', '1', '0', 'venda'), ('4', '4', '1', '0', 'venda'), ('5', '5', '1', '1', 'marketing'), ('6', '6', '1', '1', 'venda');
COMMIT;

-- ----------------------------
--  Table structure for `fermentacao`
-- ----------------------------
DROP TABLE IF EXISTS `fermentacao`;
CREATE TABLE `fermentacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idInoculacao` int(11) NOT NULL,
  `data_final` date DEFAULT NULL,
  `tipo` char(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idInoculacaoUnico` (`idInoculacao`),
  CONSTRAINT `FK_fermentacao_inoculacao` FOREIGN KEY (`idInoculacao`) REFERENCES `inoculacao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `fermentacao`
-- ----------------------------
BEGIN;
INSERT INTO `fermentacao` VALUES ('1', '3', '2014-01-23', 'selada');
COMMIT;

-- ----------------------------
--  Table structure for `fornecedor`
-- ----------------------------
DROP TABLE IF EXISTS `fornecedor`;
CREATE TABLE `fornecedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(80) DEFAULT NULL,
  `numero_telefone` varchar(10) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nomeUnico` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `fornecedor`
-- ----------------------------
BEGIN;
INSERT INTO `fornecedor` VALUES ('1', 'compras@angenoli.com.br', '3356878', 'R Beira Mar 12', 'angeloni'), ('2', 'contato@bistek.com.br', null, 'Costeira', 'bistek'), ('3', null, null, null, 'calcar'), ('4', null, null, null, 'prodapys'), ('5', null, null, null, 'campeiro');
COMMIT;

-- ----------------------------
--  Table structure for `funcao`
-- ----------------------------
DROP TABLE IF EXISTS `funcao`;
CREATE TABLE `funcao` (
  `idSocio` int(11) NOT NULL,
  `idCargo` int(11) NOT NULL,
  `idDepartamento` int(11) NOT NULL,
  PRIMARY KEY (`idSocio`,`idCargo`,`idDepartamento`),
  KEY `FK_funcao_cargo` (`idCargo`),
  KEY `FK_funcao_departamento` (`idDepartamento`),
  CONSTRAINT `FK_funcao_cargo` FOREIGN KEY (`idCargo`) REFERENCES `cargo` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_funcao_departamento` FOREIGN KEY (`idDepartamento`) REFERENCES `departamento` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_funcao_socio` FOREIGN KEY (`idSocio`) REFERENCES `socio` (`idPessoa`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `funcao`
-- ----------------------------
BEGIN;
INSERT INTO `funcao` VALUES ('1', '1', '3'), ('3', '1', '1'), ('4', '1', '2'), ('2', '2', '3');
COMMIT;

-- ----------------------------
--  Table structure for `garrafa`
-- ----------------------------
DROP TABLE IF EXISTS `garrafa`;
CREATE TABLE `garrafa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_tampa` varchar(8) NOT NULL,
  `material` varchar(8) NOT NULL,
  `volume` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `garrafa`
-- ----------------------------
BEGIN;
INSERT INTO `garrafa` VALUES ('1', 'rolha', 'vidro', '1'), ('2', 'metal', 'vidro', '0.6'), ('3', 'rolha', 'vidro', '1'), ('4', 'rolha', 'vidro', '0.5'), ('5', 'plástico', 'pet', '0.6'), ('6', 'rolha', 'vidro', '1.8'), ('7', 'rolha', 'vidro', '1'), ('8', 'rolha', 'vidro', '1');
COMMIT;

-- ----------------------------
--  Table structure for `inoculacao`
-- ----------------------------
DROP TABLE IF EXISTS `inoculacao`;
CREATE TABLE `inoculacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `idMedicao` int(11) NOT NULL,
  `idLocal` int(11) DEFAULT NULL,
  `idReceita` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_inoculacao_medicao` (`idMedicao`),
  KEY `FK_inoculacao_local` (`idLocal`),
  KEY `FK_inoculacao_receita` (`idReceita`),
  CONSTRAINT `FK_inoculacao_local` FOREIGN KEY (`idLocal`) REFERENCES `local` (`id`),
  CONSTRAINT `FK_inoculacao_receita` FOREIGN KEY (`idReceita`) REFERENCES `receita` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `inoculacao`
-- ----------------------------
BEGIN;
INSERT INTO `inoculacao` VALUES ('3', '2013-11-19', '1', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `insumo`
-- ----------------------------
DROP TABLE IF EXISTS `insumo`;
CREATE TABLE `insumo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `tipo` varchar(13) NOT NULL,
  `marca` varchar(80) DEFAULT NULL,
  `descricao` varchar(255) NOT NULL,
  `unidade_medida` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `insumo`
-- ----------------------------
BEGIN;
INSERT INTO `insumo` VALUES ('1', 'mel de larangeira', 'matéria-prima', 'prodapys', 'mel monofloral de larangeira', 'ml'), ('2', 'mel multifloral', 'matéria-prima', 'prodapys', 'mel oriundo de diferentes tipos de flor', 'ml'), ('3', 'mel multifloral', 'matéria-prima', 'calcar', 'mel mais doce', 'ml'), ('4', 'fermentador 20L', 'ferramenta', 'brewer', 'mermentador de polímero alimentício com airlock inbutido', 'unid'), ('5', 'fermentador 10L', 'ferramenta', 'brew4u', 'fermentador de vidro sem tampa', 'unid'), ('6', 'luvas', 'ferramenta', null, 'luvas atóxicas', 'unid'), ('7', 'phgametro', 'ferramenta', 'precise64', 'phgametro eletronico', 'unid'), ('8', 'densímetro', 'ferramenta', null, 'densímetro com pipeta para medição da densidade', 'unid'), ('9', 'água', 'matéria-prima', 'imperatriz', 'água de acidez normal', 'ml'), ('10', 'azul de metileno', 'químico', null, 'azul de metileno para eliminação de bactérias', 'g'), ('11', 'funil', 'ferramenta', null, 'funil de precisão', 'unid'), ('12', 'fermento safale', 'matéria-prima', 'safale', 'fermento de alta graduação', 'g'), ('13', 'fermento sorbate', 'matéria-prima', 'sorbate', 'desc fermento', 'g'), ('14', 'potássio', 'matéria-prima', null, 'desc...', 'g'), ('15', 'álcool', 'químico', null, 'álcool para higienização', 'ml'), ('16', 'levedo lavin', 'matéria-prima', 'lavin', 'desc...', 'g');
COMMIT;

-- ----------------------------
--  Table structure for `insumo_inoculacao`
-- ----------------------------
DROP TABLE IF EXISTS `insumo_inoculacao`;
CREATE TABLE `insumo_inoculacao` (
  `idInoculacao` int(11) NOT NULL,
  `idInsumo` int(11) NOT NULL DEFAULT '0',
  `quantidade` float DEFAULT NULL,
  PRIMARY KEY (`idInoculacao`,`idInsumo`),
  KEY `FK_uso_insumo` (`idInsumo`),
  CONSTRAINT `FK_uso_inoculacao` FOREIGN KEY (`idInoculacao`) REFERENCES `inoculacao` (`id`),
  CONSTRAINT `FK_uso_insumo` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `insumo_inoculacao`
-- ----------------------------
BEGIN;
INSERT INTO `insumo_inoculacao` VALUES ('3', '2', '5'), ('3', '5', '1'), ('3', '6', '4'), ('3', '8', '1'), ('3', '9', '8'), ('3', '12', '100');
COMMIT;

-- ----------------------------
--  Table structure for `investidor`
-- ----------------------------
DROP TABLE IF EXISTS `investidor`;
CREATE TABLE `investidor` (
  `idPessoa` int(11) NOT NULL,
  `numero_principal` varchar(10) DEFAULT NULL,
  `numero_celular` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idPessoa`),
  CONSTRAINT `FK_pessoa_investidor` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `investidor`
-- ----------------------------
BEGIN;
INSERT INTO `investidor` VALUES ('1', '32334846', '99781334'), ('2', '33456546', '99846567'), ('3', '33355646', '99841346'), ('4', '33554782', '99436558'), ('6', '99879987', null);
COMMIT;

-- ----------------------------
--  Table structure for `investimento`
-- ----------------------------
DROP TABLE IF EXISTS `investimento`;
CREATE TABLE `investimento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idInvestidor` int(11) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `valor` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_investimento_investidor` (`idInvestidor`),
  CONSTRAINT `FK_investimento_investidor` FOREIGN KEY (`idInvestidor`) REFERENCES `investidor` (`idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `investimento`
-- ----------------------------
BEGIN;
INSERT INTO `investimento` VALUES ('1', '1', '2013-09-19', '50'), ('2', '2', '2013-09-20', '30'), ('3', '1', '2013-10-11', '20'), ('4', '3', '2013-09-21', '56.8'), ('5', '4', '2013-10-26', '30'), ('6', '6', '2013-10-27', '200'), ('7', '1', '2013-11-12', '60.5'), ('8', '1', '2013-11-23', '20');
COMMIT;

-- ----------------------------
--  Table structure for `local`
-- ----------------------------
DROP TABLE IF EXISTS `local`;
CREATE TABLE `local` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idSocio` int(11) DEFAULT NULL,
  `cidade` varchar(70) DEFAULT NULL,
  `cep` varchar(12) DEFAULT NULL,
  `endereco` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_local_socio` (`idSocio`),
  CONSTRAINT `FK_local_socio` FOREIGN KEY (`idSocio`) REFERENCES `socio` (`idPessoa`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `local`
-- ----------------------------
BEGIN;
INSERT INTO `local` VALUES ('1', '1', 'Florianópolis', '88034700', 'R Vera Linhares de Andrade 2890'), ('2', '4', 'São José', '88654675', 'R Augusto Moura 65'), ('3', '3', 'Florianópolis', '65468724', 'R Joao da Silva 102'), ('4', null, 'Florianópolis', null, 'Armazen SC 401');
COMMIT;

-- ----------------------------
--  Table structure for `lote`
-- ----------------------------
DROP TABLE IF EXISTS `lote`;
CREATE TABLE `lote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idFermentacao` int(11) NOT NULL,
  `volume` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lote_fermentacao` (`idFermentacao`),
  CONSTRAINT `FK_lote_fermentacao` FOREIGN KEY (`idFermentacao`) REFERENCES `fermentacao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `lote`
-- ----------------------------
BEGIN;
INSERT INTO `lote` VALUES ('1', '1', '20');
COMMIT;

-- ----------------------------
--  Table structure for `maturacao`
-- ----------------------------
DROP TABLE IF EXISTS `maturacao`;
CREATE TABLE `maturacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idLocal` int(11) DEFAULT NULL,
  `idEngarrafamento` int(11) NOT NULL,
  `temperatura_ambiente` float DEFAULT NULL,
  `tempo` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_maturacao_local` (`idLocal`),
  KEY `FK_maturacao_engarrafamento` (`idEngarrafamento`),
  CONSTRAINT `FK_maturacao_engarrafamento` FOREIGN KEY (`idEngarrafamento`) REFERENCES `engarrafamento` (`id`),
  CONSTRAINT `FK_maturacao_local` FOREIGN KEY (`idLocal`) REFERENCES `local` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `maturacao`
-- ----------------------------
BEGIN;
INSERT INTO `maturacao` VALUES ('1', '1', '1', '23', '4'), ('2', '1', '3', '23', '4'), ('3', '1', '4', '20.5', '10');
COMMIT;

-- ----------------------------
--  Table structure for `medicao`
-- ----------------------------
DROP TABLE IF EXISTS `medicao`;
CREATE TABLE `medicao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idFermentacao` int(11) NOT NULL,
  `ph` float DEFAULT NULL,
  `densidade` float DEFAULT NULL,
  `temperatura` float DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_medicao_fermentacao` (`idFermentacao`),
  CONSTRAINT `FK_medicao_fermentacao` FOREIGN KEY (`idFermentacao`) REFERENCES `fermentacao` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `medicao`
-- ----------------------------
BEGIN;
INSERT INTO `medicao` VALUES ('1', '1', '1.2', '3', '26', '2013-11-19'), ('2', '1', '1.23', '3.2', '22', '2013-11-22'), ('3', '1', '0.5', '2.6', '24', '2013-12-04'), ('4', '1', '0.6', '2.6', '23', '2013-12-18'), ('5', '1', '0.8', '3', '26', '2014-01-06');
COMMIT;

-- ----------------------------
--  Table structure for `pessoa`
-- ----------------------------
DROP TABLE IF EXISTS `pessoa`;
CREATE TABLE `pessoa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `pessoa`
-- ----------------------------
BEGIN;
INSERT INTO `pessoa` VALUES ('1', 'Octávio'), ('2', 'Ramon'), ('3', 'Gustavo'), ('4', 'Yuri'), ('5', 'Chris'), ('6', 'DVI Invest');
COMMIT;

-- ----------------------------
--  Table structure for `pessoa_fisica`
-- ----------------------------
DROP TABLE IF EXISTS `pessoa_fisica`;
CREATE TABLE `pessoa_fisica` (
  `idPessoa` int(11) NOT NULL,
  `cpf` char(14) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  PRIMARY KEY (`idPessoa`),
  UNIQUE KEY `cpfUnico` (`cpf`),
  CONSTRAINT `FK_pessoafisica_pessoa` FOREIGN KEY (`idPessoa`) REFERENCES `investidor` (`idPessoa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `pessoa_fisica`
-- ----------------------------
BEGIN;
INSERT INTO `pessoa_fisica` VALUES ('1', '00346515947', '1992-08-07'), ('2', '66530097987', '1993-08-22'), ('3', '54054987325', '1991-04-12'), ('4', '00654578667', '1992-11-05');
COMMIT;

-- ----------------------------
--  Table structure for `pessoa_juridica`
-- ----------------------------
DROP TABLE IF EXISTS `pessoa_juridica`;
CREATE TABLE `pessoa_juridica` (
  `idPessoa` int(11) NOT NULL,
  `cnpj` varchar(11) NOT NULL,
  `data_fundacao` date DEFAULT NULL,
  `area_atuacao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idPessoa`),
  UNIQUE KEY `cnpjUnico` (`cnpj`),
  CONSTRAINT `FK_pessoajuridica_pessoa` FOREIGN KEY (`idPessoa`) REFERENCES `investidor` (`idPessoa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `pessoa_juridica`
-- ----------------------------
BEGIN;
INSERT INTO `pessoa_juridica` VALUES ('6', '90001875246', '2012-07-17', 'Asseguradora');
COMMIT;

-- ----------------------------
--  Table structure for `receita`
-- ----------------------------
DROP TABLE IF EXISTS `receita`;
CREATE TABLE `receita` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPessoa` int(11) DEFAULT NULL,
  `reidratacao_fermento` tinyint(1) DEFAULT NULL,
  `ph_desejado` float DEFAULT NULL,
  `grad_alcoolica_desejada` float DEFAULT NULL,
  `fonte` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_receita_autor` (`idPessoa`),
  CONSTRAINT `FK_receita_autor` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `receita`
-- ----------------------------
BEGIN;
INSERT INTO `receita` VALUES ('1', null, '1', '2.01', '6.5', 'WikiBread.com', 'Descrição de uma receira de hidromel'), ('2', '5', '0', '1.32', '5', null, 'Outra descrição de receita de hidromel');
COMMIT;

-- ----------------------------
--  Table structure for `responsavel_inoculacao`
-- ----------------------------
DROP TABLE IF EXISTS `responsavel_inoculacao`;
CREATE TABLE `responsavel_inoculacao` (
  `idSocio` int(11) NOT NULL,
  `idInoculacao` int(11) NOT NULL,
  PRIMARY KEY (`idSocio`,`idInoculacao`),
  KEY `FK_responsavel_inoculacao` (`idInoculacao`),
  CONSTRAINT `FK_responsavel_inoculacao` FOREIGN KEY (`idInoculacao`) REFERENCES `inoculacao` (`id`),
  CONSTRAINT `FK_responsavel_socio` FOREIGN KEY (`idSocio`) REFERENCES `socio` (`idPessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `responsavel_inoculacao`
-- ----------------------------
BEGIN;
INSERT INTO `responsavel_inoculacao` VALUES ('3', '3'), ('4', '3');
COMMIT;

-- ----------------------------
--  Table structure for `sanitizacao`
-- ----------------------------
DROP TABLE IF EXISTS `sanitizacao`;
CREATE TABLE `sanitizacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idQuimico` int(11) NOT NULL,
  `idSanitizado` int(11) NOT NULL,
  `data` date DEFAULT NULL,
  `quantidadeQuimico` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sanitizacao_quimico` (`idQuimico`),
  KEY `FK_sanitizacao_sanitizado` (`idSanitizado`),
  CONSTRAINT `FK_sanitizacao_quimico` FOREIGN KEY (`idQuimico`) REFERENCES `insumo` (`id`),
  CONSTRAINT `FK_sanitizacao_sanitizado` FOREIGN KEY (`idSanitizado`) REFERENCES `insumo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `sanitizacao`
-- ----------------------------
BEGIN;
INSERT INTO `sanitizacao` VALUES ('1', '15', '11', '2013-11-20', '20');
COMMIT;

-- ----------------------------
--  Table structure for `socio`
-- ----------------------------
DROP TABLE IF EXISTS `socio`;
CREATE TABLE `socio` (
  `idPessoa` int(11) NOT NULL,
  `data_saida` date DEFAULT NULL,
  `data_entrada` date NOT NULL,
  PRIMARY KEY (`idPessoa`),
  CONSTRAINT `FK_socio_pessoafisica` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa_fisica` (`idPessoa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `socio`
-- ----------------------------
BEGIN;
INSERT INTO `socio` VALUES ('1', null, '2013-07-10'), ('2', null, '2013-08-02'), ('3', null, '2013-08-16'), ('4', null, '2013-10-14');
COMMIT;

-- ----------------------------
--  Table structure for `transferencia`
-- ----------------------------
DROP TABLE IF EXISTS `transferencia`;
CREATE TABLE `transferencia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idFermentacao` int(11) DEFAULT NULL,
  `novo_fermentador` varchar(255) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `filtragem` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_transferencia_fermentacao` (`idFermentacao`),
  CONSTRAINT `FK_transferencia_fermentacao` FOREIGN KEY (`idFermentacao`) REFERENCES `fermentacao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `transferencia`
-- ----------------------------
BEGIN;
INSERT INTO `transferencia` VALUES ('1', '1', 'fermentador de vidro', '2013-12-09', '1');
COMMIT;

-- ----------------------------
--  Table structure for `venda`
-- ----------------------------
DROP TABLE IF EXISTS `venda`;
CREATE TABLE `venda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `valor_garrafa` float DEFAULT NULL,
  `idEngarrafamento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_venda_engarrafamento` (`idEngarrafamento`),
  CONSTRAINT `FK_venda_engarrafamento` FOREIGN KEY (`idEngarrafamento`) REFERENCES `engarrafamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `venda`
-- ----------------------------
BEGIN;
INSERT INTO `venda` VALUES ('1', '2014-02-03', '25', '1'), ('2', '2014-02-03', '25', '3'), ('3', '2014-02-14', '25', '4');
COMMIT;

-- ----------------------------
--  View structure for `_total_investimento`
-- ----------------------------
DROP VIEW IF EXISTS `_total_investimento`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `_total_investimento` AS select sum(`investimento`.`valor`) AS `total_investimento` from `investimento`;

-- ----------------------------
--  View structure for `caixaAtual`
-- ----------------------------
DROP VIEW IF EXISTS `caixaAtual`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `caixaAtual` AS select (`_total_investimento`.`total_investimento` - `lucro`.`lucro`) AS `total_investimento - lucro` from (`_total_investimento` join `lucro`);

-- ----------------------------
--  View structure for `despesasPorDia`
-- ----------------------------
DROP VIEW IF EXISTS `despesasPorDia`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `despesasPorDia` AS select `compra_insumo`.`data` AS `data`,sum(`compra_insumo`.`preco_total`) AS `total_despesa` from `compra_insumo` group by `compra_insumo`.`data`;

-- ----------------------------
--  View structure for `ganho`
-- ----------------------------
DROP VIEW IF EXISTS `ganho`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ganho` AS select sum(`venda`.`valor_garrafa`) AS `ganho` from `venda`;

-- ----------------------------
--  View structure for `garrafasDisponiveisVenda`
-- ----------------------------
DROP VIEW IF EXISTS `garrafasDisponiveisVenda`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `garrafasDisponiveisVenda` AS select `engarrafamento`.`id` AS `id` from `engarrafamento` where ((`engarrafamento`.`finalidade` = 'venda') and (not(`engarrafamento`.`id` in (select `venda`.`idEngarrafamento` from `venda`))));

-- ----------------------------
--  View structure for `gasto`
-- ----------------------------
DROP VIEW IF EXISTS `gasto`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `gasto` AS select sum(`compra_insumo`.`preco_total`) AS `gasto` from `compra_insumo`;

-- ----------------------------
--  View structure for `insumosUtilizadosPorLote`
-- ----------------------------
DROP VIEW IF EXISTS `insumosUtilizadosPorLote`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `insumosUtilizadosPorLote` AS select `u`.`idInsumo` AS `idInsumo`,sum(`u`.`quantidade`) AS `qtd_usado`,`l`.`id` AS `lote` from ((`insumo_inoculacao` `u` join `fermentacao` `f` on((`u`.`idInoculacao` = `f`.`idInoculacao`))) join `lote` `l` on((`f`.`id` = `l`.`idFermentacao`))) group by `u`.`idInsumo` order by `l`.`id`;

-- ----------------------------
--  View structure for `lucro`
-- ----------------------------
DROP VIEW IF EXISTS `lucro`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `lucro` AS select (`ganho`.`ganho` - `gasto`.`gasto`) AS `lucro` from (`ganho` join `gasto`);

-- ----------------------------
--  View structure for `maiorInvestidor`
-- ----------------------------
DROP VIEW IF EXISTS `maiorInvestidor`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `maiorInvestidor` AS select `investimento`.`idInvestidor` AS `idInvestidor`,sum(`investimento`.`valor`) AS `total_investido` from `investimento` group by `investimento`.`idInvestidor` order by sum(`investimento`.`valor`) desc;

-- ----------------------------
--  View structure for `materiaPrimaComprada`
-- ----------------------------
DROP VIEW IF EXISTS `materiaPrimaComprada`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `materiaPrimaComprada` AS select `i`.`id` AS `id`,sum(`c`.`quantidade`) AS `qtd_comprado` from (`insumo` `i` left join `compra_insumo` `c` on((`i`.`id` = `c`.`idInsumo`))) where (`i`.`tipo` = 'materia-prima') group by `i`.`id`;

-- ----------------------------
--  View structure for `materiaPrimaDisponivel`
-- ----------------------------
DROP VIEW IF EXISTS `materiaPrimaDisponivel`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `materiaPrimaDisponivel` AS select `materiaPrimaComprada`.`id` AS `id`,(sum(`materiaPrimaComprada`.`qtd_comprado`) - sum(`materiaPrimaUsada`.`qtd_usado`)) AS `disponivel` from (`materiaPrimaComprada` join `materiaPrimaUsada`) where (`materiaPrimaComprada`.`id` = `materiaPrimaUsada`.`idInsumo`) group by `materiaPrimaComprada`.`id`;

-- ----------------------------
--  View structure for `materiaPrimaUsada`
-- ----------------------------
DROP VIEW IF EXISTS `materiaPrimaUsada`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `materiaPrimaUsada` AS select `insumo_inoculacao`.`idInsumo` AS `idInsumo`,sum(`insumo_inoculacao`.`quantidade`) AS `qtd_usado` from `insumo_inoculacao` group by `insumo_inoculacao`.`idInsumo`;

-- ----------------------------
--  View structure for `mediaDensidadePhLote`
-- ----------------------------
DROP VIEW IF EXISTS `mediaDensidadePhLote`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mediaDensidadePhLote` AS select `l`.`id` AS `lote`,avg(`m`.`ph`) AS `ph_medio`,avg(`m`.`densidade`) AS `densidade_media` from (`medicao` `m` join `lote` `l` on((`m`.`idFermentacao` = `l`.`idFermentacao`))) group by `l`.`id`;

-- ----------------------------
--  View structure for `receitaMaiorTempoFermentacao`
-- ----------------------------
DROP VIEW IF EXISTS `receitaMaiorTempoFermentacao`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `receitaMaiorTempoFermentacao` AS select `i`.`idReceita` AS `idReceita`,max((to_days(`f`.`data_final`) - to_days(`i`.`data`))) AS `dias_fermentacao` from (`fermentacao` `f` join `inoculacao` `i` on((`f`.`idInoculacao` = `i`.`id`)));

-- ----------------------------
--  View structure for `receitaMenorTempoFermentacao`
-- ----------------------------
DROP VIEW IF EXISTS `receitaMenorTempoFermentacao`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `receitaMenorTempoFermentacao` AS select `i`.`idReceita` AS `idReceita`,min((to_days(`f`.`data_final`) - to_days(`i`.`data`))) AS `dias_fermentacao` from (`fermentacao` `f` join `inoculacao` `i` on((`f`.`idInoculacao` = `i`.`id`)));

-- ----------------------------
--  View structure for `socioMaisAtivoNaProducao`
-- ----------------------------
DROP VIEW IF EXISTS `socioMaisAtivoNaProducao`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `socioMaisAtivoNaProducao` AS select `responsavel_inoculacao`.`idSocio` AS `idSocio`,count(`responsavel_inoculacao`.`idInoculacao`) AS `atuacoes` from `responsavel_inoculacao` group by `responsavel_inoculacao`.`idSocio`;

-- ----------------------------
--  View structure for `vendasGarrafaPorDia`
-- ----------------------------
DROP VIEW IF EXISTS `vendasGarrafaPorDia`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vendasGarrafaPorDia` AS select `venda`.`data` AS `data`,sum(`venda`.`valor_garrafa`) AS `SUM(valor_garrafa)` from `venda` group by `venda`.`data`;

SET FOREIGN_KEY_CHECKS = 1;
