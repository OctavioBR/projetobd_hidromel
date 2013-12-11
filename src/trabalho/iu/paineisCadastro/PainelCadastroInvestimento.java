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

public class PainelCadastroInvestimento extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfNome, jtfValor;
	private JFormattedTextField jtfData;
	private JLabel jlNome, jlData, jlValor;
	private JButton jbCadastrar, jbPesquisarInvestidor;
	private int idInvestidor;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroInvestimento(BancoDeDados bd)
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
			jtfData = new JFormattedTextField(mascaraData);
		} 
		catch (ParseException e) {
			jtfData = new JFormattedTextField();
		}
		jlData = new JLabel("Data:");
		jtfValor = new JTextField();
		jlValor = new JLabel("Valor:");		
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarInvestidor = new JButton("Pesquisar...");
		jbPesquisarInvestidor.addActionListener(this);
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
														.addComponent(jlData)			
														.addComponent(jlValor)															
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)													
														.addComponent(jtfNome,100,100,100)
														.addComponent(jtfData,100,100,100)	
														.addComponent(jtfValor,100,100,100)																	
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarInvestidor)																								
													 )	
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()				      
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlNome)
										  .addComponent(jtfNome)
										  .addComponent(jbPesquisarInvestidor)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlData)
										  .addComponent(jtfData)
									    )	
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlValor)
										  .addComponent(jtfValor)						
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
			float valor = Float.parseFloat(jtfValor.getText().trim());
			if (valor <= 0)
				throw new Exception("O valor deve ser maior que 0 (zero).");			
		}
		catch (NumberFormatException e)
		{
			throw new Exception("Você deve digitar um número no campo Valor.");
		}		
	}
	
	private void limparCampos()
	{
		jtfData.setText("");	
		jtfData.setValue("");
		jtfNome.setText("");
		jtfValor.setText("");
		idInvestidor = -1;
	}
	
	private void cadastrar()
	{
		String sql;
		String dataFormatoBanco = BancoDeDados.dataDeJavaParaOBanco(jtfData.getText());
		if (dataFormatoBanco == null)
			sql = "INSERT INTO investimento (idInvestidor, valor) " +
				     "values ('" + idInvestidor + "', '" +
				     			   jtfValor.getText() + "')";
		else
			sql = "INSERT INTO investimento (idInvestidor, data, valor) " +
				     "values ('" + idInvestidor + "', '" +
				     			   dataFormatoBanco + "', '" +
				     			   jtfValor.getText() + "')";				     		      
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
	
	private void pesquisarInvestidor()
	{
		String sql = "SELECT pessoa.id, pessoa.nome, investidor.numero_principal, " +
				"investidor.numero_celular FROM pessoa JOIN investidor ON pessoa.id = investidor.idPessoa";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				idInvestidor = Integer.parseInt(resultado[selecionado][0]);
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
			pesquisarInvestidor();		
	}
}
