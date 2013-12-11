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

public class PainelCadastroLote extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfFermentacao, jtfVolume;
	private JLabel jlFermentacao, jlVolume;
	private JButton jbCadastrar, jbPesquisarFermentacao;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroLote(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{	
		jtfVolume = new JTextField();
		jlVolume = new JLabel("Volume:");
		jlFermentacao = new JLabel("Fermentação:");
		jtfFermentacao = new JTextField();
		jtfFermentacao.setEnabled(false);
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarFermentacao = new JButton("Pesquisar...");
		jbPesquisarFermentacao.addActionListener(this);
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
														.addComponent(jlFermentacao)
														.addComponent(jlVolume)																												
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfFermentacao,100,100,100)
														.addComponent(jtfVolume,100,100,100)																										
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarFermentacao)														
													 )	
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlFermentacao)
										  .addComponent(jtfFermentacao)
										  .addComponent(jbPesquisarFermentacao)
									    )						      
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlVolume)
									      .addComponent(jtfVolume)
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
			float volume = Float.parseFloat(jtfVolume.getText().trim());
			if (volume <= 0)
				throw new Exception("O valor de Volume deve ser maior que 0 (zero).");			
		}
		catch (NumberFormatException e)
		{
			throw new Exception("O valor de Volume deve ser um número.");
		}
	}
	
	private void limparCampos()
	{
		jtfFermentacao.setText("");				
		jtfVolume.setText("");		
	}
		
	private void cadastrar()
	{
		String sql = "INSERT INTO lote (idFermentacao, volume) " +
				     "values ('" + jtfFermentacao.getText() + "', '" + jtfVolume.getText() + "')";		
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
	
	private void pesquisarFermentacao()
	{
		String sql = "SELECT * FROM fermentacao;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				int idFermentacao = Integer.parseInt(resultado[selecionado][0]);
				jtfFermentacao.setText(idFermentacao+"");
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
			pesquisarFermentacao();				
	}
}
