package trabalho.iu.paineisCadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import trabalho.persistencia.BancoDeDados;

public class PainelCadastroPessoa extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfNome;
	private JLabel jlNome;
	private JButton jbCadastrar;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroPessoa(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfNome = new JTextField();
		jlNome = new JLabel("Nome:");		
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
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
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfNome,100,100,100)																											
													 )											
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlNome)
									      .addComponent(jtfNome)									     
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
			Double.parseDouble(jtfNome.getText());
			throw new Exception("O campo Nome deve ser preenchido com texto.");
		}
		catch (NumberFormatException e){}
		
		if (jtfNome.getText().trim().length() == 0)
			throw new Exception("O campo Nome deve ser preenchido.");	
	}
	
	private void limparCampos()
	{
		jtfNome.setText("");		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		try {
			check();
			String sql = "INSERT INTO pessoa (nome) values ('" + jtfNome.getText() + "')";
			bancoDeDados.executarUpdate(sql);
			JOptionPane.showMessageDialog(null, "Dado cadastrado com sucesso.");
			limparCampos();
		} 		
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
}
