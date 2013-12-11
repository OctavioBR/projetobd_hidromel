package trabalho.iu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;

import trabalho.persistencia.BancoDeDados;


public class DialogoEdicaoDados extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private PainelTabelaPaginada painelTabela;
	private String nomeTabela;
	private BancoDeDados bancoDeDados;
	private String[][] dados;
	private JButton jbApagar;
	
	public DialogoEdicaoDados(String nomeTabela, BancoDeDados bancoDeDados)
	{
		super((JDialog)null, "Editar Dados", true);				
		painelTabela = new PainelTabelaPaginada(true, true);
		jbApagar = new JButton("Apagar dado");
		jbApagar.addActionListener(this);
		this.nomeTabela = nomeTabela;
		this.bancoDeDados = bancoDeDados;
		try {
			String sql = "SELECT * FROM " + nomeTabela + ";";
			dados = bancoDeDados.executarPesquisa(sql);								
			painelTabela.atualizaTabela(dados);			
		} 
		catch (SQLException e) {}				
		posicioneComponentes();
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		painelTabela.addCellChangeListener(new UpdateDBAction(painelTabela, nomeTabela, bancoDeDados));	   
	}
	
	public void posicioneComponentes()
	{
		GroupLayout gl = new GroupLayout(getContentPane());
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		setLayout(gl);
		
		gl.setHorizontalGroup(gl.createParallelGroup(Alignment.CENTER)				   
				    			.addComponent(painelTabela)		
				    			.addComponent(jbApagar)
		);
		
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addComponent(painelTabela)
							  .addComponent(jbApagar)
		);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		int linha = painelTabela.getLinhaSelecionada();			    
	    String chavePrimaria = painelTabela.getColumnName(0);
	    String idSelecionado = painelTabela.getValueAt(linha, 0);
	              
	    String sql = "DELETE FROM " + nomeTabela + " WHERE " + chavePrimaria + " = " + idSelecionado + ";";    
	  	try {
	  		bancoDeDados.executarUpdate(sql);
	  		
	  		List<String[]> l = new ArrayList<String[]>(Arrays.asList(dados));
			l.remove(linha+1);
			dados = l.toArray(new String[][]{});
			
	  		painelTabela.atualizaTabela(dados);
	  	}
	  	catch (Exception erro){
	  		JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
	  	}
	}
}
