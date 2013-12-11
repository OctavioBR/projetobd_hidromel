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

public class PainelCadastroMaturacao extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfLocal, jtfEngarrafamento, jtfTemperatura, jtfTempo;
	private JLabel jlLocal, jlEngarrafamento, jlTemperatura, jlTempo;
	private JButton jbCadastrar, jbPesquisarLocal, jbPesquisarEngarrafamento;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroMaturacao(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfEngarrafamento = new JTextField();
		jlEngarrafamento = new JLabel("Engarrafamento:");	
		jtfEngarrafamento.setEnabled(false);
		jtfTempo = new JTextField();
		jlTempo = new JLabel("Tempo de maturação:");	
		jtfTemperatura = new JTextField();
		jlTemperatura = new JLabel("Temperatura Ambiente (°C):");	
		jlLocal = new JLabel("Local:");
		jtfLocal = new JTextField();	
		jtfLocal.setEnabled(false);
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarLocal = new JButton("Pesquisar...");
		jbPesquisarLocal.addActionListener(this);
		jbPesquisarEngarrafamento = new JButton("Pesquisar...");
		jbPesquisarEngarrafamento.addActionListener(this);
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
														.addComponent(jlLocal)
														.addComponent(jlEngarrafamento)
														.addComponent(jlTemperatura)
														.addComponent(jlTempo)														
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfLocal,100,100,100)
														.addComponent(jtfEngarrafamento,100,100,100)
														.addComponent(jtfTemperatura,100,100,100)
														.addComponent(jtfTempo,100,100,100)														
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarLocal)
														.addComponent(jbPesquisarEngarrafamento)
													 )	
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlLocal)
										  .addComponent(jtfLocal)
										  .addComponent(jbPesquisarLocal)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlEngarrafamento)
										  .addComponent(jtfEngarrafamento)
									      .addComponent(jbPesquisarEngarrafamento)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlTemperatura)
										  .addComponent(jtfTemperatura)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlTempo)
										  .addComponent(jtfTempo)
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
			float tempo = Float.parseFloat(jtfTempo.getText().trim());
			if (tempo <= 0)
				throw new Exception("O valor de Tempo deve ser maior que 0 (zero).");			
		}
		catch (NumberFormatException e)
		{
			throw new Exception("O valor de Tempo deve ser um número.");
		}
	}
	
	private void limparCampos()
	{
		jtfEngarrafamento.setText("");				
		jtfLocal.setText("");
		jtfTemperatura.setText("");
		jtfTempo.setText("");
	}
	
	private void cadastrar()
	{
		String sql = "INSERT INTO maturacao (idLocal, idEngarrafamento, temperatura_ambiente, tempo) " +
				     "values ('" + jtfLocal.getText() + "', '" +
				     			   jtfEngarrafamento.getText() + "', '" +
				     			   jtfTemperatura.getText() + "', '" +
				     			   jtfTempo.getText() + "')";		
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
	
	private void pesquisarLocal()
	{
		String sql = "SELECT id, idSocio, endereco FROM local;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);		
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				int idLocal = Integer.parseInt(resultado[selecionado][0]);						
				jtfLocal.setText(idLocal+"");
			}
		} 
		catch (SQLException e) {}
	}
	
	private void pesquisarEngarrafamento()
	{
		String sql = "SELECT * FROM engarrafamento;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);		
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				int idEngarrafamento = Integer.parseInt(resultado[selecionado][0]);						
				jtfEngarrafamento.setText(idEngarrafamento+"");
			}
		} 
		catch (SQLException e) {}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == jbCadastrar)
			cadastrar();
		else if (event.getSource() == jbPesquisarLocal)
			pesquisarLocal();		
		else
			pesquisarEngarrafamento();
	}
}
