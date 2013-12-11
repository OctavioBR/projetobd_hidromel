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

public class PainelCadastroSocio extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfCpf;
	private JFormattedTextField jtfDataEntrada, jtfDataSaida;
	private JLabel jlCpf, jlDataEntrada, jlDataSaida;
	private JButton jbCadastrar, jbPesquisarPessoa;
	private int idPessoa;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroSocio(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			jtfDataEntrada = new JFormattedTextField(mascaraData);
			jtfDataSaida = new JFormattedTextField(mascaraData);
		} 
		catch (ParseException e) {
			jtfDataEntrada = new JFormattedTextField();
			jtfDataSaida = new JFormattedTextField();
		}
	
		jlDataEntrada = new JLabel("Data de Entrada:");	
		jlDataSaida = new JLabel("Data de Saída:");
		jlCpf = new JLabel("Cpf:");
		jtfCpf = new JTextField();
		jtfCpf.setEnabled(false);
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarPessoa = new JButton("Pesquisar...");
		jbPesquisarPessoa.addActionListener(this);
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
														.addComponent(jlCpf)
														.addComponent(jlDataEntrada)
														.addComponent(jlDataSaida)														
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfCpf,100,100,100)
														.addComponent(jtfDataEntrada,100,100,100)
														.addComponent(jtfDataSaida,100,100,100)														
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarPessoa)														
													 )	
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										 .addComponent(jlCpf)
										 .addComponent(jtfCpf)
				    			         .addComponent(jbPesquisarPessoa)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										 .addComponent(jlDataEntrada)
				    			         .addComponent(jtfDataEntrada)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										 .addComponent(jlDataSaida)
										 .addComponent(jtfDataSaida)			
									    )						  
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jbCadastrar)
									    )
							);
	}
	
	private void check() throws Exception
	{				
		String dataFormatoBanco = BancoDeDados.dataDeJavaParaOBanco(jtfDataEntrada.getText());
		if (dataFormatoBanco == null)
			throw new Exception("O campo Data Entrada é obrigatório.");							
	}
	
	private void limparCampos()
	{
		jtfCpf.setText("");				
		jtfDataEntrada.setText("");
		jtfDataEntrada.setValue("");
		jtfDataSaida.setText("");
		jtfDataSaida.setValue("");
		idPessoa = -1;
	}
	
	private void cadastrar()
	{
		String sql;
		String dataFormatoBanco = BancoDeDados.dataDeJavaParaOBanco(jtfDataSaida.getText());
		if (dataFormatoBanco == null)
			sql = "INSERT INTO socio (idPessoa, data_entrada) " +
				     "values ('" + idPessoa + "', '" +
	     			   BancoDeDados.dataDeJavaParaOBanco(jtfDataEntrada.getText()) + "')";	 
		else
			sql = "INSERT INTO socio (idPessoa, data_saida, data_entrada) " +
				     "values ('" + idPessoa + "', '" +
				     			   dataFormatoBanco + "', '" +
				     			   BancoDeDados.dataDeJavaParaOBanco(jtfDataEntrada.getText()) + "')";	     		       
		try {
			check();
			bancoDeDados.executarUpdate(sql);
			JOptionPane.showMessageDialog(null, "Dado cadastrado com sucesso.");
			limparCampos();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void pesquisarPessoa()
	{
		String sql = "SELECT pessoa.id, pessoa.nome, " +
	            "pessoa_fisica.cpf, pessoa_fisica.data_nascimento " +
				"FROM pessoa JOIN pessoa_fisica ON pessoa.id = pessoa_fisica.idPessoa;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);	
			for (int i = 1; i < resultado.length; i++)
				resultado[i][3] = BancoDeDados.dataDoBancoParaJava(resultado[i][3]);
						
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				idPessoa = Integer.parseInt(resultado[selecionado][0]);
				jtfCpf.setText(resultado[selecionado][2]);
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
			pesquisarPessoa();		
	}
}
