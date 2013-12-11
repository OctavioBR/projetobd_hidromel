package trabalho.iu.paineisCadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.MaskFormatter;

import trabalho.iu.DialogoSelecaoDeChaveEstrangeira;
import trabalho.persistencia.BancoDeDados;

public class PainelCadastroSanitizacao extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfQuimico, jtfSanitizado, jtfQuantidade;
	private JFormattedTextField jtfData;
	private JLabel jlQuimico, jlSanitizado, jlData, jlQuantidade;
	private JButton jbCadastrar, jbPesquisarQuimico, jbPesquisarSanitizado;
	int idSanitizado, idQuimico;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroSanitizacao(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfSanitizado = new JTextField();
		jlSanitizado = new JLabel("Sanitizado:");	
		jtfSanitizado.setEnabled(false);
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			jtfData = new JFormattedTextField(mascaraData);
		} 
		catch (ParseException e) {
			jtfData = new JFormattedTextField();
		}
		jlData = new JLabel("Data da Sanitização:");
		jtfQuantidade = new JTextField();
		jlQuantidade = new JLabel("Quantidade de químico utilizado:");	
		jlQuimico = new JLabel("Químico:");		
		jtfQuimico = new JTextField();	
		jtfQuimico.setEnabled(false);
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarQuimico = new JButton("Pesquisar...");
		jbPesquisarQuimico.addActionListener(this);
		jbPesquisarSanitizado = new JButton("Pesquisar...");
		jbPesquisarSanitizado.addActionListener(this);
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
														.addComponent(jlQuimico)
														.addComponent(jlSanitizado)
														.addComponent(jlData)
														.addComponent(jlQuantidade)														
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfQuimico,100,100,100)
														.addComponent(jtfSanitizado,100,100,100)
														.addComponent(jtfData,100,100,100)
														.addComponent(jtfQuantidade,100,100,100)														
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarQuimico)
														.addComponent(jbPesquisarSanitizado)
													 )	
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlQuimico)
										  .addComponent(jtfQuimico)
									      .addComponent(jbPesquisarQuimico)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlSanitizado)
										  .addComponent(jtfSanitizado)
									      .addComponent(jbPesquisarSanitizado)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										 .addComponent(jlData)
				    			         .addComponent(jtfData)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlQuantidade)
									      .addComponent(jtfQuantidade)
									    )							 
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jbCadastrar)
									    )
							);

	}
	
	private void check() throws Exception
	{			
		try
		{
			float quantidade = Float.parseFloat(jtfQuantidade.getText().trim());
			if (quantidade <= 0)
				throw new Exception("O valor de Quantidade deve ser maior que 0 (zero).");			
		}
		catch (NumberFormatException e)
		{
			throw new Exception("O valor de Quantidade deve ser um número.");
		}
	}
	
	private void limparCampos()
	{
		jtfData.setText("");	
		jtfData.setValue("");
		jtfQuantidade.setText("");
		jtfQuimico.setText("");
		jtfSanitizado.setText("");		
	}
	
	private void cadastrar()
	{		
		try {
			check();
			String sql;
			String dataFormatoBanco = BancoDeDados.dataDeJavaParaOBanco(jtfData.getText());
			if (dataFormatoBanco == null)
				sql = "INSERT INTO sanitizacao (idQuimico, idSanitizado, quantidadeQuimico) " +
				     "values ('" + idQuimico + "', '" +
				     			   idSanitizado + "', '" +
				     			   jtfQuantidade.getText() + "')";
			else
				sql = "INSERT INTO sanitizacao (idQuimico, idSanitizado, data, quantidadeQuimico) " +
				     "values ('" + idQuimico + "', '" +
				     			   idSanitizado + "', '" +
				     			   dataFormatoBanco + "', '" +
				     			   jtfQuantidade.getText() + "')";
			bancoDeDados.executarUpdate(sql);
			JOptionPane.showMessageDialog(null, "Dado cadastrado com sucesso.");
			limparCampos();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void pesquisarQuimico()
	{
		String sql = "SELECT id, nome, marca, descricao FROM insumo WHERE tipo = 'químico';";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);		
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				idQuimico = Integer.parseInt(resultado[selecionado][0]);						
				jtfQuimico.setText(resultado[selecionado][1]);
			}
		} 
		catch (SQLException e) {}
	}
	
	private void pesquisarSanitizado()
	{
		String sql = "SELECT id, nome, tipo, marca, descricao FROM insumo;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);		
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				idSanitizado = Integer.parseInt(resultado[selecionado][0]);						
				jtfSanitizado.setText(resultado[selecionado][1]);
			}
		} 
		catch (SQLException e) {}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == jbCadastrar)
			cadastrar();
		else if (event.getSource() == jbPesquisarQuimico)
			pesquisarQuimico();		
		else
			pesquisarSanitizado();
	}
}
