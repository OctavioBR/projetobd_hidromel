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

public class PainelCadastroInsumoInoculacao extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfInoculacao, jtfInsumo, jtfQuantidade;
	private JLabel jlInoculacao, jlInsumo, jlQuantidade;
	private JButton jbCadastrar, jbPesquisarInoculacao, jbPesquisarInsumo;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroInsumoInoculacao(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfInsumo = new JTextField();
		jlInsumo = new JLabel("Insumo:");
		jtfInsumo.setEnabled(false);
		jtfQuantidade = new JTextField();
		jlQuantidade = new JLabel("Quantidade utilizada:");	
		jlInoculacao = new JLabel("Inoculação:");
		jtfInoculacao = new JTextField();	
		jtfInoculacao.setEnabled(false);
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarInoculacao = new JButton("Pesquisar...");
		jbPesquisarInoculacao.addActionListener(this);
		jbPesquisarInsumo = new JButton("Pesquisar...");
		jbPesquisarInsumo.addActionListener(this);
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
														.addComponent(jlInsumo)			
														.addComponent(jlQuantidade)																													
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfInoculacao,100,100,100)														
														.addComponent(jtfInsumo,100,100,100)
														.addComponent(jtfQuantidade,100,100,100)																												
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarInoculacao)		
													    .addComponent(jbPesquisarInsumo)																
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
										  .addComponent(jlInsumo)
										  .addComponent(jtfInsumo)
										  .addComponent(jbPesquisarInsumo)
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
	
	private void cadastrar()
	{
		String sql = "INSERT INTO insumo_inoculacao (idInoculacao, idInsumo, quantidade) " +
				     "values ('" + jtfInoculacao.getText() + "', '" +
				     			   jtfInsumo.getText() + "', '" +
				     			   jtfQuantidade.getText() + "')";		
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
	
	private void limparCampos()
	{
		jtfInoculacao.setText("");				
		jtfInsumo.setText("");
		jtfQuantidade.setText("");		
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
	
	private void pesquisarInsumo()
	{
		String sql = "SELECT * FROM insumo;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);					
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				int idInsumo = Integer.parseInt(resultado[selecionado][0]);						
				jtfInsumo.setText(idInsumo+"");
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
			pesquisarInsumo();
	}
}
