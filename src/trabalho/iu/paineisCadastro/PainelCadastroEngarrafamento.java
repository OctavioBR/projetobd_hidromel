package trabalho.iu.paineisCadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import trabalho.iu.DialogoSelecaoDeChaveEstrangeira;
import trabalho.persistencia.BancoDeDados;

public class PainelCadastroEngarrafamento extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfGarrafa, jtfLote;
	private JLabel jlGarrafa, jlLote, jlGaseificado, jlFinalidade;
	private JButton jbCadastrar, jbPesquisarGarrafa, jbPesquisarLote;
	private ButtonGroup bgGaseificado, bgFinalidade;
	private JRadioButton jrbSim, jrbNao, jrbMarketing, jrbVendas, jrbPeD;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroEngarrafamento(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{	
		jtfLote = new JTextField();
		jlLote = new JLabel("Lote:");
		jtfLote.setEnabled(false);
		jlFinalidade = new JLabel("Finalidade:");
		jlGaseificado = new JLabel("Gaseificado:");
		jlGarrafa = new JLabel("Garrafa:");
		jtfGarrafa = new JTextField();
		jtfGarrafa.setEnabled(false);
		jrbSim = new JRadioButton("Sim");
		jrbSim.setSelected(true);
		jrbNao = new JRadioButton("Não");
		bgGaseificado = new ButtonGroup();
		bgGaseificado.add(jrbSim);
		bgGaseificado.add(jrbNao);
		jrbMarketing = new JRadioButton("Marketing");
		jrbMarketing.setSelected(true);
		jrbVendas =	new JRadioButton("Vendas");
		jrbPeD = new JRadioButton("P&D");
		bgFinalidade = new ButtonGroup();
		bgFinalidade.add(jrbMarketing);
		bgFinalidade.add(jrbVendas);
		bgFinalidade.add(jrbPeD);
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarGarrafa = new JButton("Pesquisar...");
		jbPesquisarGarrafa.addActionListener(this);
		jbPesquisarLote = new JButton("Pesquisar...");
		jbPesquisarLote.addActionListener(this);
	}
	
	private void posicioneComponentes()
	{
		GroupLayout gl = new GroupLayout(this);
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		setLayout(gl);
		
		gl.setHorizontalGroup(gl.createParallelGroup(Alignment.CENTER)
								.addGroup(gl.createSequentialGroup()
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jlGarrafa)
														.addComponent(jlLote)
														.addComponent(jlGaseificado)
														.addComponent(jlFinalidade)																											
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfGarrafa,100,100,100)
														.addComponent(jtfLote,100,100,100)
														.addComponent(jrbSim)
														.addComponent(jrbMarketing)																
													 )	
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarGarrafa)
														.addComponent(jbPesquisarLote)	
														.addComponent(jrbNao)		
														.addComponent(jrbVendas)																
													 )	
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jrbPeD)																													
													 )																								 
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jlGarrafa)
										.addComponent(jtfGarrafa)
										.addComponent(jbPesquisarGarrafa)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jlLote)
										.addComponent(jtfLote)
										.addComponent(jbPesquisarLote)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jlGaseificado)
										.addComponent(jrbSim)
										.addComponent(jrbNao)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jlFinalidade)
										.addComponent(jrbMarketing)
										.addComponent(jrbVendas)
										.addComponent(jrbPeD)
									    )											
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jbCadastrar)
									    )
		);
	}
	
	private String getStringFinalidade()
	{
		String finalidade;
		if (jrbMarketing.isSelected())
			finalidade = jrbMarketing.getText();
		else if (jrbVendas.isSelected())
			finalidade = jrbVendas.getText();
		else
			finalidade = jrbPeD.getText();
		
		return finalidade;
	}
	
	private void limparCampos()
	{
		jtfLote.setText("");				
		jtfGarrafa.setText("");			
	}	
	
	private void cadastrar()
	{
		String sql = "INSERT INTO engarrafamento (idGarrafa, idLote, gaseificado, finalidade) " +
				     "values ('" + jtfGarrafa.getText() + "', '" +
				     			   jtfLote.getText() + "', '" +
				     			   (jrbSim.isSelected() ? 1 : 0) + "', '" +
				     			   getStringFinalidade() + "')";		
		try {
			bancoDeDados.executarUpdate(sql);
			JOptionPane.showMessageDialog(null, "Dado cadastrado com sucesso.");
			limparCampos();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void pesquisarGarrafa()
	{
		String sql = "SELECT * FROM garrafa;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				int idGarrafa = Integer.parseInt(resultado[selecionado][0]);
				jtfGarrafa.setText(idGarrafa+"");
			}
		} 
		catch (SQLException e) {}
	}
	
	private void pesquisarLote()
	{
		String sql = "SELECT * FROM lote;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				int idLote = Integer.parseInt(resultado[selecionado][0]);
				jtfLote.setText(idLote+"");
			}
		} 
		catch (SQLException e) {}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == jbCadastrar)
			cadastrar();
		else if (event.getSource() == jbPesquisarGarrafa)
			pesquisarGarrafa();		
		else
			pesquisarLote();
	}
}
