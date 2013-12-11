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

public class PainelCadastroPessoaJuridica extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfCnpj, jtfNome, jtfAreaDeAtuacao, jtfTelefone, jtfTelefone2;
	private JFormattedTextField jtfDataFundacao;
	private JLabel jlCnpj, jlNome, jlDataFundacao, jlAreaDeAtuacao, jlTelefone, jlTelefone2;
	private JButton jbCadastrar, jbPesquisarPessoa;
	private int idPessoa;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroPessoaJuridica(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfNome = new JTextField();
		jlNome = new JLabel("Nome:");	
		jtfNome.setEnabled(false);
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			jtfDataFundacao = new JFormattedTextField(mascaraData);
		} 
		catch (ParseException e) {
			jtfDataFundacao = new JFormattedTextField();
		}
		jlDataFundacao = new JLabel("Data de Fundação:");
		jlCnpj = new JLabel("Cnpj:");
		jtfCnpj = new JTextField();
		jlAreaDeAtuacao = new JLabel("Área de Atuação:");
		jtfAreaDeAtuacao = new JTextField();
		jtfTelefone = new JTextField();
		jlTelefone = new JLabel("Telefone:");
		jtfTelefone2 = new JTextField();
		jlTelefone2 = new JLabel("Telefone Secundário:");
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
														.addComponent(jlNome)
														.addComponent(jlCnpj)
														.addComponent(jlDataFundacao)
														.addComponent(jlAreaDeAtuacao)
														.addComponent(jlTelefone)	
														.addComponent(jlTelefone2)	
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfNome,100,100,100)
														.addComponent(jtfCnpj,100,100,100)
														.addComponent(jtfDataFundacao,100,100,100)
														.addComponent(jtfAreaDeAtuacao,100,100,100)
														.addComponent(jtfTelefone,100,100,100)	
														.addComponent(jtfTelefone2,100,100,100)	
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarPessoa)														
													 )	
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlNome)
									      .addComponent(jtfNome)
										  .addComponent(jbPesquisarPessoa)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlCnpj)
									      .addComponent(jtfCnpj)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlDataFundacao)
									      .addComponent(jtfDataFundacao)	
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlAreaDeAtuacao)
									      .addComponent(jtfAreaDeAtuacao)	
									    )	
							 .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlTelefone)
									      .addComponent(jtfTelefone)	
									    )	
							.addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlTelefone2)
									      .addComponent(jtfTelefone2)	
									    )		  
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jbCadastrar)
									    )
							);
	}
	
	private void check() throws Exception
	{				
		if (jtfCnpj.getText().trim().length() == 0)
			throw new Exception("O campo Cnpj é obrigatório.");
		if (jtfCnpj.getText().trim().length() == 3)
			throw new Exception("O campo Data de Fundaçao deve ser preenchido.");
	}
	
	private void limparCampos()
	{
		jtfAreaDeAtuacao.setText("");				
		jtfCnpj.setText("");
		jtfDataFundacao.setText("");
		jtfDataFundacao.setValue("");
		jtfNome.setText("");
		jtfTelefone.setText("");
		jtfTelefone.setText("");
		idPessoa = -1;
	}
	
	private void cadastrar()
	{
		try {
			check();
			String sql = "INSERT INTO investidor (idPessoa, numero_principal, numero_celular) " +
					"values ('" + idPessoa + "', '" +
					              jtfTelefone.getText() + "', '" +
					              jtfTelefone2.getText() + "')";
			bancoDeDados.executarUpdate(sql);
			
			String dataFormatoBanco = BancoDeDados.dataDeJavaParaOBanco(jtfDataFundacao.getText());
			if (dataFormatoBanco == null)
				sql = "INSERT INTO pessoa_juridica (idPessoa, cnpj, area_atuacao) " +
					     "values ('" + idPessoa + "', '" +
		     			   jtfCnpj.getText() + "', '" +
		     			   jtfAreaDeAtuacao.getText() + "')";  
			else
				sql = "INSERT INTO pessoa_juridica (idPessoa, cnpj, data_fundacao, area_atuacao) " +
					     "values ('" + idPessoa + "', '" +
					     			   jtfCnpj.getText() + "', '" +
					     			   dataFormatoBanco + "', '" +
					     			   jtfAreaDeAtuacao.getText() + "')";    		       		
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
		String sql = "SELECT * FROM pessoa;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				idPessoa = Integer.parseInt(resultado[selecionado][0]);
				jtfNome.setText(resultado[selecionado][1]);
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
