package trabalho.iu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import trabalho.persistencia.BancoDeDados;

public class UpdateDBAction extends AbstractAction
{
	private static final long serialVersionUID = 1L;
	private BancoDeDados bancoDeDados;
	private PainelTabelaPaginada painelTabela;
	private String nomeTabela;
	
	public UpdateDBAction(PainelTabelaPaginada painelTabela, String nomeTabela, BancoDeDados bancoDeDados)
	{
		this.bancoDeDados = bancoDeDados;
		this.nomeTabela = nomeTabela;
		this.painelTabela = painelTabela;
	}

	public void actionPerformed(ActionEvent e)
    {
        TableCellListener tcl = (TableCellListener)e.getSource();
		int linha = tcl.getRow();
		int coluna = tcl.getColumn();		
	    String dadoAntigo = (String)tcl.getOldValue();
	    String dadoNovo = (String)tcl.getNewValue();
	    if (!dadoNovo.equals(dadoAntigo)) {
		    String nomeColuna = painelTabela.getColumnName(coluna);
		    String chavePrimaria = painelTabela.getColumnName(0);
		    String idSelecionado = painelTabela.getValueAt(linha, 0);
		              
		    String sql = "UPDATE " + nomeTabela + " SET " + nomeColuna + " = '" + dadoNovo + 
		      		     "' WHERE " + chavePrimaria + " = " + idSelecionado + ";";		
		  	try {
		  		bancoDeDados.executarUpdate(sql);	
		  	}
		  	catch (Exception erro){
		  		JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		  		painelTabela.setValueAt(dadoAntigo, linha, coluna);
		  	}
	    }
    }
}
