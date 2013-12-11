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

public class PainelCadastroFuncao extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfSocio, jtfDepartamento, jtfCargo;
	private JLabel jlSocio, jlDepartamento, jlCargo;
	private JButton jbCadastrar, jbPesquisarSocio, jbPesquisarDepartamento, jbPesquisarCargo;
	int idSocio, idCargo, idDepartamento;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroFuncao(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfDepartamento = new JTextField();
		jlDepartamento = new JLabel("Departamento:");
		jtfDepartamento.setEnabled(false);
		jtfCargo = new JTextField();
		jtfCargo.setEnabled(false);
		jlCargo = new JLabel("Cargo:");
		jlSocio = new JLabel("Sócio:");
		jtfSocio = new JTextField();
		jtfSocio.setEnabled(false);
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarDepartamento = new JButton("Pesquisar...");
		jbPesquisarDepartamento.addActionListener(this);
		jbPesquisarSocio = new JButton("Pesquisar...");
		jbPesquisarSocio.addActionListener(this);
		jbPesquisarCargo = new JButton("Pesquisar...");
		jbPesquisarCargo.addActionListener(this);
		idSocio = -1;
		idCargo = -1;
		idDepartamento = -1;
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
														.addComponent(jlCargo)
														.addComponent(jlDepartamento)														
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfSocio,100,100,100)
														.addComponent(jtfCargo,100,100,100)
														.addComponent(jtfDepartamento,100,100,100)														
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarSocio)
														.addComponent(jbPesquisarCargo)
														.addComponent(jbPesquisarDepartamento)
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
										 .addComponent(jlCargo)
										 .addComponent(jtfCargo)			    						    			
										 .addComponent(jbPesquisarCargo)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jlDepartamento)
										.addComponent(jtfDepartamento)
										.addComponent(jbPesquisarDepartamento)
									    )							
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jbCadastrar)
									    )
		);
	}
	
	private void check() throws Exception
	{		
		if (idSocio == -1)
			throw new Exception("Você deve selecionar um sócio.");
		if (idCargo == -1)
			throw new Exception("Você deve selecionar um cargo.");
		if (idDepartamento == -1)
			throw new Exception("Você deve selecionar um departamento.");
	}
	
	private void limparCampos()
	{
		jtfCargo.setText("");				
		jtfDepartamento.setText("");
		jtfSocio.setText("");
		idCargo = -1;
		idSocio = -1;
		idDepartamento = -1;
	}
	
	private void cadastrar()
	{			     		      
		try {
			check();
			String sql = "INSERT INTO funcao (idSocio, idCargo, idDepartamento) " +
				     "values ('" + idSocio + "', '" +
				     			   idCargo + "', '" +
				     			   idDepartamento + "')";
			bancoDeDados.executarUpdate(sql);
			JOptionPane.showMessageDialog(null, "Dado cadastrado com sucesso.");
			limparCampos();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void pesquisarDepartamento()
	{
		String sql = "SELECT * FROM departamento;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				idDepartamento = Integer.parseInt(resultado[selecionado][0]);
				jtfDepartamento.setText(resultado[selecionado][1]);
			}
		} 
		catch (SQLException e) {}
	}
	
	private void pesquisarCargo()
	{
		String sql = "SELECT * FROM cargo;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				idCargo = Integer.parseInt(resultado[selecionado][0]);
				jtfCargo.setText(resultado[selecionado][1]);
			}
		} 
		catch (SQLException e) {}
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
		else if (event.getSource() == jbPesquisarDepartamento)
			pesquisarDepartamento();
		else if (event.getSource() == jbPesquisarCargo)
			pesquisarCargo();
		else
			pesquisarSocio();
	}
}
