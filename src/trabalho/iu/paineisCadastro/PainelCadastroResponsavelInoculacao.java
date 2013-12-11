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

public class PainelCadastroResponsavelInoculacao extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfInoculacao, jtfSocio;
	private JLabel jlInoculacao, jlSocio;
	private JButton jbCadastrar, jbPesquisarInoculacao, jbPesquisarSocio;
	private int idSocio;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroResponsavelInoculacao(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfSocio = new JTextField();
		jlSocio = new JLabel("Socio:");
		jtfSocio.setEnabled(false);
		jlInoculacao = new JLabel("Inoculação:");
		jtfInoculacao = new JTextField();	
		jtfInoculacao.setEnabled(false);
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarInoculacao = new JButton("Pesquisar...");
		jbPesquisarInoculacao.addActionListener(this);
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
														.addComponent(jlInoculacao)														
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfSocio,100,100,100)
														.addComponent(jtfInoculacao,100,100,100)														
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarSocio)
														.addComponent(jbPesquisarInoculacao)
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
										  .addComponent(jlInoculacao)
										  .addComponent(jtfInoculacao)
								          .addComponent(jbPesquisarInoculacao)
									    )						      
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jbCadastrar)
									    )
							);
	}
	
	private void cadastrar()
	{
		String sql = "INSERT INTO responsavel_inoculacao (idSocio, idInoculacao) " +
				     "values ('" + idSocio + "', '" + jtfInoculacao.getText() + "')";		
		try {
			bancoDeDados.executarUpdate(sql);
			JOptionPane.showMessageDialog(null, "Dado cadastrado com sucesso.");
			limparCampos();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limparCampos()
	{
		jtfInoculacao.setText("");				
		jtfSocio.setText("");
		idSocio = -1;
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
		else if (event.getSource() == jbPesquisarInoculacao)
			pesquisarInoculacao();		
		else
			pesquisarSocio();
	}
}
