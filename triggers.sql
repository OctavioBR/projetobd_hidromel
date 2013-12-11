delimiter //

CREATE TRIGGER tr_insert_compra_insumo BEFORE INSERT ON compra_insumo
FOR EACH ROW
BEGIN	
	SET NEW.preco_total = NEW.preco_unitario * NEW.quantidade;
END;//

CREATE TRIGGER tr_update_preco_unitario BEFORE UPDATE ON compra_insumo
FOR EACH ROW
BEGIN
	DECLARE msg VARCHAR(255);
	SET msg = "O valor dessa coluna não pode ser alterado. Ele é calculado automaticamente.";
	IF OLD.preco_total <> NEW.preco_total THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	ELSEIF OLD.preco_unitario <> NEW.preco_unitario OR OLD.quantidade <> NEW.quantidade THEN
		SET NEW.preco_total = NEW.preco_unitario * NEW.quantidade;
	END IF;
END;//

CREATE TRIGGER tr_insert_venda BEFORE INSERT ON venda
FOR EACH ROW
BEGIN		
	DECLARE msg VARCHAR(255);
	SET msg = "Para vender um engarrafamento, esse deve ter como finalidade a venda.";
	IF (SELECT finalidade FROM engarrafamento WHERE id = NEW.idEngarrafamento) <> "Vendas" THEN		
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END;//

CREATE TRIGGER tr_update_engarrafamento_venda BEFORE UPDATE ON venda
FOR EACH ROW
BEGIN		
	DECLARE msg VARCHAR(255);
	SET msg = "Para vender um engarrafamento, esse deve ter como finalidade a venda.";
	IF OLD.idEngarrafamento <> NEW.idEngarrafamento AND (SELECT finalidade FROM engarrafamento WHERE id = NEW.idEngarrafamento) <> "Vendas" THEN		
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END;//

CREATE TRIGGER tr_atualiza_fermentacao_medicao AFTER INSERT ON fermentacao
FOR EACH ROW
BEGIN
	UPDATE medicao SET idFermentacao = NEW.id WHERE id = (SELECT idMedicao FROM inoculacao WHERE id = NEW.idInoculacao);	
END;//

CREATE TRIGGER tr_insert_insumo_inoculacao BEFORE INSERT ON insumo_inoculacao
FOR EACH ROW
BEGIN
	DECLARE msg VARCHAR(255);
	SET msg = "A quantidade utiizada deve ser menor ou igual a disponível no estoque.";
	IF NEW.quantidade > (SELECT disponivel FROM materiaPrimaDisponivel WHERE id = NEW.idInsumo) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END;//

CREATE TRIGGER tr_update_insumo_inoculacao BEFORE UPDATE ON insumo_inoculacao
FOR EACH ROW
BEGIN
	DECLARE msg VARCHAR(255);
	SET msg = "A quantidade utiizada deve ser menor ou igual a disponível no estoque.";
	IF OLD.quantidade <> NEW.quantidade AND NEW.quantidade > (SELECT disponivel FROM materiaPrimaDisponivel WHERE id = NEW.idInsumo) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END;//

delimiter ;