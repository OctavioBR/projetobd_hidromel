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

public class PainelCadastroInoculacao extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfReceita, jtfLocal;	
	private JFormattedTextField jtfData;
	private JLabel jlReceita, jlLocal, jlData;
	private JButton jbCadastrar, jbPesquisarReceita, jbPesquisarLocal;
	private JTextField jtfPh, jtfDensidade, jtfTemperatura;
	private JLabel jlMedicaoInicial, jlPh, jlDensidade, jlTemperatura;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroInoculacao(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfReceita = new JTextField();
		jlReceita = new JLabel("Receita:");	
		jtfReceita.setEnabled(false);
		jtfLocal = new JTextField();
		jlLocal = new JLabel("Local:");
		jtfLocal.setEnabled(false);
		jlData = new JLabel("Data:");
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			jtfData = new JFormattedTextField(mascaraData);
		} 
		catch (ParseException e) {
			jtfData = new JFormattedTextField();
		}
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarReceita = new JButton("Pesquisar...");
		jbPesquisarReceita.addActionListener(this);
		jbPesquisarLocal = new JButton("Pesquisar...");
		jbPesquisarLocal.addActionListener(this);
		
		/**********************MEDICAO***********************/
		jlMedicaoInicial = new JLabel("Medição Inicial:");
		jtfPh = new JTextField();
		jlPh = new JLabel("Ph:");	
		jtfDensidade = new JTextField();
		jlDensidade = new JLabel("Densidade:");	
		jtfTemperatura = new JTextField();
		jlTemperatura = new JLabel("Temperatura:");		
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
														.addComponent(jlReceita)	
														.addComponent(jlData)																									
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)														
														.addComponent(jtfLocal,100,100,100)
														.addComponent(jtfReceita,100,100,100)																								
														.addComponent(jtfData,100,100,100)																											
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)	
														.addComponent(jbPesquisarLocal)	
														.addComponent(jbPesquisarReceita)															
													 )														
										 )
								.addComponent(jlMedicaoInicial)		
								.addGroup(gl.createSequentialGroup()
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jlPh)
														.addComponent(jlDensidade)
														.addComponent(jlTemperatura)
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfPh,100,100,100)
														.addComponent(jtfDensidade,100,100,100)
														.addComponent(jtfTemperatura,100,100,100)
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
										  .addComponent(jlReceita)
										  .addComponent(jtfReceita)
										  .addComponent(jbPesquisarReceita)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlData)
										  .addComponent(jtfData)
									    )
							  .addGroup(gl.createParallelGroup(Alignment.CENTER)
									  .addComponent(jlMedicaoInicial)	
									  )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlPh)
				    			          .addComponent(jtfPh)	
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
				sql = "INSERT INTO medicao (ph, densidade, temperatura) " +
					     "values (" + jtfPh.getText() + ", " +
								      jtfDensidade.getText() + ", " +
								      getTemperatura() + ")";
			else
				sql = "INSERT INTO medicao (ph, densidade, temperatura, data) " +
					     "values (" + jtfPh.getText() + ", " +
								       jtfDensidade.getText() + ", " +
								       getTemperatura() + ", '" +
								       dataFormatoBanco + "')";
			bancoDeDados.executarUpdate(sql);
			sql = "SELECT id FROM medicao ORDER BY id DESC limit 1;";
			int idMedicao = Integer.parseInt(bancoDeDados.executarPesquisa(sql)[1][0]);
			
			if (dataFormatoBanco == null)
				sql = "INSERT INTO inoculacao (idMedicao, idLocal, idReceita) " +
					     "values ('" + idMedicao + "', '" +
								       jtfLocal.getText() + "', '" +
	 				     			   jtfReceita.getText() + "')";
			else
				sql = "INSERT INTO inoculacao (data, idMedicao, idLocal, idReceita) " +
					     "values ('" + dataFormatoBanco + "', '" +
								       idMedicao + "', '" +
								       jtfLocal.getText() + "', '" +
	  				     			   jtfReceita.getText() + "')";	
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
		jtfLocal.setText("");
		jtfReceita.setText("");
		jtfData.setText("");	
		jtfData.setValue("");
		jtfDensidade.setText("");
		jtfPh.setText("");
		jtfTemperatura.setText("");
	}
	
	private void pesquisarReceita()
	{
		String sql = "SELECT id, reidratacao_fermento, ph_desejado, grad_alcoolica_desejada, descricao FROM receita;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				int idReceita = Integer.parseInt(resultado[selecionado][0]);
				jtfReceita.setText(idReceita+"");
			}
		} 
		catch (SQLException e) {}
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
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == jbCadastrar)
			cadastrar();
		else if (event.getSource() == jbPesquisarReceita)
			pesquisarReceita();
		else
			pesquisarLocal();
	}
}
