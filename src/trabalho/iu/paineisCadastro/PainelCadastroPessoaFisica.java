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

public class PainelCadastroPessoaFisica extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfNome, jtfTelefone, jtfTelefone2;
	private JFormattedTextField jtfDataNascimento, jtfCpf;
	private JLabel jlCpf, jlNome, jlDataNascimento, jlTelefone, jlTelefone2;
	private JButton jbCadastrar, jbPesquisarPessoa;
	private int idPessoa;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroPessoaFisica(BancoDeDados bd)
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
			jtfDataNascimento = new JFormattedTextField(mascaraData);
		} 
		catch (ParseException e) {
			jtfDataNascimento = new JFormattedTextField();
		}
		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			jtfCpf = new JFormattedTextField(mascaraCpf);
		} 
		catch (ParseException e) {
			jtfCpf = new JFormattedTextField();
		}
		jlDataNascimento = new JLabel("Data de Nascimento:");
		jlCpf = new JLabel("Cpf:");
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
														.addComponent(jlCpf)
														.addComponent(jlDataNascimento)
														.addComponent(jlTelefone)
														.addComponent(jlTelefone2)
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfNome,100,100,100)
														.addComponent(jtfCpf,100,100,100)
														.addComponent(jtfDataNascimento,100,100,100)
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
										  .addComponent(jlCpf)
									      .addComponent(jtfCpf)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlDataNascimento)
										  .addComponent(jtfDataNascimento)
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
		String cpf = jtfCpf.getText().trim();	
		if (cpf.length() == 9)
			throw new Exception("O campo CPF é obrigatório.");	
	}
	
	private void limparCampos()
	{
		jtfCpf.setText("");		
		jtfCpf.setValue("");
		jtfDataNascimento.setText("");
		jtfDataNascimento.setValue("");
		jtfNome.setText("");
		jtfTelefone.setText("");
		jtfTelefone2.setText("");
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
			String dataFormatoBanco = BancoDeDados.dataDeJavaParaOBanco(jtfDataNascimento.getText());
			if (dataFormatoBanco == null)
				sql = "INSERT INTO pessoa_fisica (idPessoa, cpf) " +
					     "values ('" + idPessoa + "', '" + jtfCpf.getText() + "')";
			else
				sql = "INSERT INTO pessoa_fisica (idPessoa, cpf, data_nascimento) " +
				     "values ('" + idPessoa + "', '" +
				     			   jtfCpf.getText() + "', '" +
				     			   dataFormatoBanco + "')";
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
