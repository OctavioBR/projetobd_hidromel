package trabalho.iu.paineisCadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import trabalho.iu.DialogoSelecaoDeChaveEstrangeira;
import trabalho.persistencia.BancoDeDados;

public class PainelCadastroLocal extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfSocio, jtfCidade, jtfCep, jtfEndereco;
	private JLabel jlSocio, jlCidade, jlCep, jlEndereco;
	private JButton jbCadastrar, jbPesquisarSocio;
	private int idSocio;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroLocal(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfCidade = new JTextField();
		jlCidade = new JLabel("Cidade:");	
		jtfCep = new JTextField();
		jlCep = new JLabel("CEP:");
		jtfEndereco = new JTextField();
		jlEndereco = new JLabel("Endereço:");	
		jlSocio = new JLabel("Cpf do Sócio:");
		jtfSocio = new JTextField();
		jtfSocio.setEnabled(false);
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarSocio = new JButton("Pesquisar...");
		jbPesquisarSocio.addActionListener(this);
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
														.addComponent(jlSocio)
														.addComponent(jlCidade)			
														.addComponent(jlCep)			
														.addComponent(jlEndereco)															
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfSocio,100,100,100)
														.addComponent(jtfCidade,100,100,100)
														.addComponent(jtfCep,100,100,100)
														.addComponent(jtfEndereco,100,100,100)															
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarSocio)														
													 )	
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlSocio)
										  .addComponent(jtfSocio)
										  .addComponent(jbPesquisarSocio)
									    )						      
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlCidade)
										  .addComponent(jtfCidade)	
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlCep)
									      .addComponent(jtfCep)
									    )	
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlEndereco)
									      .addComponent(jtfEndereco)				
									    )											
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jbCadastrar)
									    )
		);
	}
	
	private void check() throws Exception
	{
		if (jtfEndereco.getText().trim().length() == 0)
			throw new Exception("O campo Endereço é obrigatório.");
	}
	
	private void limparCampos()
	{
		jtfCep.setText("");				
		jtfCidade.setText("");
		jtfEndereco.setText("");
		jtfSocio.setText("");
		idSocio = -1;
	}
	
	private void cadastrar()
	{
		String sql = "INSERT INTO local (idSocio, cidade, cep, endereco) " +
				     "values ('" + idSocio + "', '" +
				     			   jtfCidade.getText() + "', '" +
				     			   jtfCep.getText() + "', '" +
				     			   jtfEndereco.getText() + "')";	     		       
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
	
	private void pesquisarSocio()
	{
		String sql = "SELECT pessoa.id, pessoa.nome, " +
                "pessoa_fisica.cpf, pessoa_fisica.data_nascimento " +
			     "FROM pessoa JOIN pessoa_fisica ON pessoa.id = pessoa_fisica.idPessoa " +
			     "JOIN socio ON pessoa.id = socio.idPessoa;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);		
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				idSocio = Integer.parseInt(resultado[selecionado][0]);							
				jtfSocio.setText(resultado[selecionado][1]);
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
			pesquisarSocio();		
	}
}
