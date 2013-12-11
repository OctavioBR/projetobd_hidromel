CREATE VIEW _gasto AS
     SELECT SUM(preco_total) AS gasto FROM compra_insumo;

CREATE VIEW _ganho AS
     SELECT SUM(valor_garrafa) AS ganho FROM venda; 

CREATE VIEW lucro AS
     SELECT ganho - gasto AS lucro FROM _ganho, _gasto;


CREATE VIEW _total_investimento AS
     SELECT SUM(valor) as total_investimento FROM investimento;

CREATE VIEW caixaAtual AS
     SELECT total_investimento - lucro FROM _total_investimento, lucro;


CREATE VIEW despesasPorDia AS
     SELECT `data`, SUM(preco_total) total_despesa FROM compra_insumo GROUP BY `data`;


CREATE VIEW vendasGarrafaPorDia AS
     SELECT `data`, SUM(valor_garrafa) FROM venda GROUP BY `data`;


CREATE VIEW garrafasDisponiveisVenda AS
     SELECT id FROM engarrafamento
     WHERE finalidade = 'Vendas' AND
     id NOT IN (SELECT idEngarrafamento FROM venda);


CREATE VIEW _materiaPrimaComprada AS
     SELECT i.id, sum(c.quantidade) qtd_comprado
     FROM insumo i
     LEFT JOIN compra_insumo c ON i.id=c.idInsumo
     WHERE i.tipo = 'Materia-prima'
     GROUP BY i.id;


CREATE VIEW _materiaPrimaUsada AS
     SELECT i.id, COALESCE(sum(u.quantidade), 0)qtd_usado
     FROM insumo i
     LEFT JOIN insumo_inoculacao u ON i.id = u.idInsumo
     WHERE i.tipo = 'Materia-prima'
     GROUP BY i.id;

CREATE VIEW materiaPrimaDisponivel AS
     SELECT c.id, sum(c.qtd_comprado)- sum(u.qtd_usado) AS disponivel
     FROM _materiaPrimaComprada c, _materiaPrimaUsada u
     WHERE c.id = u.id
     GROUP BY c.id;

CREATE VIEW insumosUtilizadosPorLote AS
     SELECT u.idInsumo, sum(u.quantidade) qtd_usado, l.id as lote
     FROM insumo_inoculacao u
     JOIN fermentacao f ON u.idInoculacao = f.idInoculacao
     JOIN lote l ON f.id = l.idFermentacao
     GROUP BY u.idInsumo
     ORDER BY l.id;


CREATE VIEW socioMaisAtivoNaProducao AS
     SELECT idSocio, count(idInoculacao)atuacoes
     FROM responsavel_inoculacao
     GROUP BY idSocio;


CREATE VIEW maiorInvestidor AS
     SELECT idInvestidor, sum(valor)total_investido
     FROM investimento
     GROUP BY idInvestidor
     ORDER BY total_investido DESC;


CREATE VIEW mediaDensidadePhLote AS
     SELECT l.id lote, avg(m.ph) ph_medio, avg(m.densidade) densidade_media
     FROM medicao m
     JOIN lote l ON m.idFermentacao = l.idFermentacao
     GROUP BY l.id;


CREATE VIEW receitaMaiorTempoFermentacao AS
     SELECT i.idReceita, MAX(DATEDIFF(f.data_final, i.`data`)) dias_fermentacao
     FROM fermentacao f
     JOIN inoculacao i ON f.idInoculacao = i.id;


CREATE VIEW receitaMenorTempoFermentacao AS
     SELECT i.idReceita, MIN(DATEDIFF(f.data_final, i.`data`)) dias_fermentacao
     FROM fermentacao f
     JOIN inoculacao i ON f.idInoculacao = i.id;


CREATE VIEW _insumos_mais_baratos AS
SELECT idInsumo, min(preco_unitario)mais_barato
          FROM compra_insumo
          GROUP BY idInsumo;

CREATE VIEW fornecedorMaisBarato AS
     SELECT c.idFornecedor, c.idInsumo
     FROM compra_insumo c
     JOIN _insumos_mais_baratos b ON c.idInsumo = b.idInsumo
     WHERE c.preco_unitario = b.mais_barato;


CREATE VIEW _insumosUsadosComPrecoMedio AS
     SELECT l.id idLote, u.idInsumo, u.quantidade * avg(c.preco_unitario)custo_insumos
     FROM insumo_inoculacao u
     JOIN compra_insumo c ON u.idInsumo = c.idInsumo
     JOIN fermentacao f ON f.idInoculacao = u.idInoculacao
     JOIN lote l ON f.id = l.idFermentacao
      JOIN insumo i ON u.idInsumo = i.id
      WHERE i.tipo <> 'Ferramenta'
     GROUP BY l.id, u.idInsumo, u.quantidade
     ORDER BY l.id;

CREATE VIEW custoPorLote AS
     SELECT idLote, Sum(custo_insumos) custo_total_insumos
     FROM _insumosUsadosComPrecoMedio
     GROUP BY idLote;


CREATE VIEW lucroPorLote AS
     SELECT e.idLote, sum(v.valor_garrafa)- custo_total_insumos lucro_lote
     FROM engarrafamento e
     JOIN venda v ON e.id = v.idEngarrafamento
     JOIN custoPorLote c ON c.idLote = e.idLote
     GROUP BY idLote;


CREATE VIEW _custo_lote_por_litro AS SELECT
     l.id idLote, c.custo_total_insumos / l.volume custo_por_litro
FROM custoPorLote c
JOIN lote l ON c.idLote = l.id
GROUP BY l.id;
CREATE VIEW _lucro_por_garrafa AS
     SELECT e.id idEngarrafamento, e.idLote, v.id idVenda,
          v.valor_garrafa - g.volume * c.custo_por_litro lucro_garrafa
     FROM engarrafamento e
     JOIN garrafa g ON e.idGarrafa = g.id
     JOIN _custo_lote_por_litro c ON e.idLote = c.idLote
     JOIN venda v ON e.id = v.idEngarrafamento;


CREATE VIEW vendaMaisLucrativa AS
     SELECT *
     FROM _lucro_por_garrafa
     WHERE lucro_garrafa IN (
          SELECT max(lucro_garrafa)
          FROM _lucro_por_garrafa
     );