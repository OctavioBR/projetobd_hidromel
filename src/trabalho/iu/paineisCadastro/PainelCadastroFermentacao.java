package trabalho.iu.paineisCadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.MaskFormatter;

import trabalho.iu.DialogoSelecaoDeChaveEstrangeira;
import trabalho.persistencia.BancoDeDados;

public class PainelCadastroFermentacao extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfInoculacao;
	private JFormattedTextField jtfData;
	private JLabel jlInoculacao, jlData, jlTipo;
	private JButton jbCadastrar, jbPesquisarInoculacao;
	private ButtonGroup bgTipo;
	private JRadioButton jrbAberta, jrbSelada;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroFermentacao(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{	
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			jtfData = new JFormattedTextField(mascaraData);
		} 
		catch (ParseException e) {
			jtfData = new JFormattedTextField();
		}
		jlData = new JLabel("Data Final:");
		jlTipo = new JLabel("Tipo:");
		jlInoculacao = new JLabel("Inoculação:");
		jtfInoculacao = new JTextField();
		jtfInoculacao.setEnabled(false);
		jrbAberta = new JRadioButton("Aberta");
		jrbAberta.setSelected(true);
		jrbSelada = new JRadioButton("Selada");
		bgTipo = new ButtonGroup();
		bgTipo.add(jrbAberta);
		bgTipo.add(jrbSelada);		
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarInoculacao = new JButton("Pesquisar...");
		jbPesquisarInoculacao.addActionListener(this);
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
														.addComponent(jlInoculacao)
														.addComponent(jlData)
														.addComponent(jlTipo)
																											
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfInoculacao,100,100,100)
														.addComponent(jtfData,100,100,100)
														.addComponent(jrbAberta)																																
													 )	
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarInoculacao)
														.addComponent(jrbSelada)																																														
													 )														 
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jlInoculacao)
										.addComponent(jtfInoculacao)
										.addComponent(jbPesquisarInoculacao)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jlData)
										.addComponent(jtfData)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jlTipo)
										.addComponent(jrbAberta)
										.addComponent(jrbSelada)
									    )						  						
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jbCadastrar)
									    )
		);
	}
	
	private String getStringTipo()
	{
		String finalidade;
		if (jrbAberta.isSelected())
			finalidade = jrbAberta.getText();
		else
			finalidade = jrbSelada.getText();
		
		return finalidade;
	}
	
	private void limparCampos()
	{
		jtfInoculacao.setText("");				
		jtfData.setText("");
		jtfData.setValue("");
	}	
	
	private void cadastrar()
	{
		String sql = "INSERT INTO fermentacao (idInoculacao, data_final, tipo) " +
				     "values ('" + jtfInoculacao.getText() + "', '" +
				     			   BancoDeDados.dataDeJavaParaOBanco(jtfData.getText()) + "', '" +
				     			   getStringTipo() + "')";		
		try {
			bancoDeDados.executarUpdate(sql);
			JOptionPane.showMessageDialog(null, "Dado cadastrado com sucesso.");
			limparCampos();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void pesquisarInoculacao()
	{
		String sql = "SELECT * FROM inoculacao;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				int idInoculacao = Integer.parseInt(resultado[selecionado][0]);
				jtfInoculacao.setText(idInoculacao+"");
			}
		} 
		catch (SQLException e) {}
	}
		
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == jbCadastrar)
			cadastrar();
		else
			pesquisarInoculacao();				
	}
}
