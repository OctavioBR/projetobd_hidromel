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

public class PainelCadastroMedicao extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfFermentacao, jtfPh, jtfDensidade, jtfTemperatura;
	private JFormattedTextField jtfData;
	private JLabel jlFermentacao, jlPh, jlData, jlDensidade, jlTemperatura;
	private JButton jbCadastrar, jbPesquisarFermentacao;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroMedicao(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfPh = new JTextField();
		jlPh = new JLabel("Ph:");	
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			jtfData = new JFormattedTextField(mascaraData);
		} 
		catch (ParseException e) {
			jtfData = new JFormattedTextField();
		}
		jlData = new JLabel("Data:");
		jtfDensidade = new JTextField();
		jlDensidade = new JLabel("Densidade:");	
		jlFermentacao = new JLabel("Fermentação:");
		jtfFermentacao = new JTextField();
		jtfFermentacao.setEnabled(false);
		jtfTemperatura = new JTextField();
		jlTemperatura = new JLabel("Temperatura:");
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
														.addComponent(jlPh)
														.addComponent(jlData)
														.addComponent(jlDensidade)
														.addComponent(jlTemperatura)
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfFermentacao,100,100,100)
														.addComponent(jtfPh,100,100,100)
														.addComponent(jtfData,100,100,100)
														.addComponent(jtfDensidade,100,100,100)
														.addComponent(jtfTemperatura,100,100,100)
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
										  .addComponent(jlPh)
				    			          .addComponent(jtfPh)	
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlData)
									      .addComponent(jtfData)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlDensidade)
									      .addComponent(jtfDensidade)	
									    )
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlTemperatura)
										  .addComponent(jtfTemperatura)
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
			float ph = Float.parseFloat(jtfPh.getText());
			if (ph < 0 || ph > 14)
				throw new Exception("O valor de PH deve ser um número de 0 a 14.");			
		}
		catch (NumberFormatException e)
		{
			throw new Exception("O valor de PH deve ser um número.");
		}
		
		try
		{
			Float densidade = Float.parseFloat(jtfDensidade.getText());
			if (densidade < 0)
				throw new Exception("O valor de Densidade deve ser um número igual ou maior que 0 (zero).");			
		}
		catch (NumberFormatException e)
		{
			throw new Exception("O valor da densidade deve ser um número.");
		}				
	}
	
	private void limparCampos()
	{
		jtfData.setText("");	
		jtfData.setValue("");
		jtfDensidade.setText("");
		jtfFermentacao.setText("");
		jtfPh.setText("");
		jtfTemperatura.setText("");
	}
	
	private String getTemperatura()
	{
		String temp = jtfTemperatura.getText();
		if (temp.trim().length() == 0)
			temp = "NULL";
		return temp;
	}
	
	private void cadastrar()
	{			
		try {
			check();
			String sql;
			String dataFormatoBanco = BancoDeDados.dataDeJavaParaOBanco(jtfData.getText());
			if (dataFormatoBanco == null)
				sql = "INSERT INTO medicao (idFermentacao, ph, densidade, temperatura) " +
					     "values (" + jtfFermentacao.getText() + ", " +
					     			  jtfPh.getText() + ", " +
					     			  jtfDensidade.getText() + ", " +
					     			  getTemperatura() + ")";
			else
				sql = "INSERT INTO medicao (idFermentacao, ph, densidade, temperatura, data) " +
				     "values (" + jtfFermentacao.getText() + ", " +
				     			  jtfPh.getText() + ", " +
				     			  jtfDensidade.getText() + ", " +
				     			  getTemperatura() + ", '" +
				     			  BancoDeDados.dataDeJavaParaOBanco(jtfData.getText()) + "')";
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
