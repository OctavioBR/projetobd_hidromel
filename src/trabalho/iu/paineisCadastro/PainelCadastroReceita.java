package trabalho.iu.paineisCadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import trabalho.iu.DialogoSelecaoDeChaveEstrangeira;
import trabalho.persistencia.BancoDeDados;

public class PainelCadastroReceita extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfAutor, jtfFonte, jtfPh, jtfGraduacao;
	private JTextArea jtaDescricao;
	private JLabel jlAutor, jlFonte, jlPh, jlDescricao, jlReidratacao, jlGraduacao;
	private ButtonGroup bgReidratacao;
	private JRadioButton jrbSim, jrbNao;
	private JButton jbCadastrar, jbPesquisarAutor;
	private int idPessoa;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroReceita(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfAutor = new JTextField();
		jlAutor = new JLabel("Autor:");	
		jtfAutor.setEnabled(false);
		jtfFonte = new JTextField();
		jlFonte = new JLabel("Fonte:");
		jtfGraduacao = new JTextField();
		jlGraduacao = new JLabel("Graduação Alcoólica:");
		jtfPh = new JTextField();
		jlPh = new JLabel("PH:");	
		jlReidratacao = new JLabel("Reidratação:");
		jrbSim = new JRadioButton("Sim");
		jrbSim.setSelected(true);
		jrbNao = new JRadioButton("Não");
		bgReidratacao = new ButtonGroup();
		bgReidratacao.add(jrbSim);
		bgReidratacao.add(jrbNao);
		jlDescricao = new JLabel("Descrição:");
		jtaDescricao = new JTextArea();
		jtaDescricao.setLineWrap(true);
		jtaDescricao.setWrapStyleWord(true);
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarAutor = new JButton("Pesquisar...");
		jbPesquisarAutor.addActionListener(this);
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
														.addComponent(jlAutor)
														.addComponent(jlFonte)
														.addComponent(jlPh)
														.addComponent(jlReidratacao)
														.addComponent(jlGraduacao)
														.addComponent(jlDescricao)
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfAutor,100,100,100)
														.addComponent(jtfFonte,100,100,100)
														.addComponent(jtfPh,100,100,100)
														.addComponent(jrbSim)	
														.addComponent(jtfGraduacao)		
														.addComponent(jtaDescricao,350,350,350)
													 )												
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jrbNao)					
														.addComponent(jbPesquisarAutor)														
													 )	
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlAutor)
									      .addComponent(jtfAutor)
										  .addComponent(jbPesquisarAutor)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlFonte)
									      .addComponent(jtfFonte)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										 .addComponent(jlPh)
										 .addComponent(jtfPh)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										 .addComponent(jlReidratacao)
				    			         .addComponent(jrbSim)
				    			         .addComponent(jrbNao)
									    )
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										 .addComponent(jlGraduacao)
										 .addComponent(jtfGraduacao)
									    )
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										 .addComponent(jlDescricao)
									     .addComponent(jtaDescricao,250,250,250)
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
			float ph = Float.parseFloat(jtfPh.getText().trim());
			if (ph < 0 || ph > 14)
				throw new Exception("O valor de PH deve ser um número de 0 a 14.");			
		}
		catch (NumberFormatException e)
		{
			throw new Exception("O valor de PH deve ser um número.");
		}
		
		try
		{
			float grafuacao = Float.parseFloat(jtfGraduacao.getText());
			if (grafuacao <= 0)
				throw new Exception("O valor de Graduação Alcoólica deve ser um número maior que 0 (zero).");			
		}
		catch (NumberFormatException e)
		{
			throw new Exception("O valor de Graduação Alcoólica deve ser um número.");
		}	
		
		if(jtaDescricao.getText().trim().length() <= 0)
			throw new Exception("Você deve acresentar alguma descrição.");
	}
		
	private void limparCampos()
	{
		jtfAutor.setText("");				
		jtfFonte.setText("");
		jtfGraduacao.setText("");
		jtfPh.setText("");
		jtaDescricao.setText("");
		idPessoa = -1;
	}
	
	private void cadastrar()
	{
		String sql = "INSERT INTO receita (idPessoa, reidratacao_fermento, ph_desejado, grad_alcoolica_desejada, fonte, descricao) " +
				     "values ('" + idPessoa + "', '" +
				     			   (jrbSim.isSelected() ? 1 : 0) + "', '" +
				     			   jtfPh.getText() + "', '" +
				     			   jtfGraduacao.getText() + "', '" +
				     			   jtfFonte.getText() + "', '" +
				     			   jtaDescricao.getText() + "')";		
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
	
	private void pesquisarAutor()
	{
		String sql = "SELECT * from pessoa;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				idPessoa = Integer.parseInt(resultado[selecionado][0]);
				jtfAutor.setText(resultado[selecionado][1]);
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
			pesquisarAutor();		
	}
}
