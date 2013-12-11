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

public class PainelCadastroVenda extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfEngarrafamento, jtfValorGarrafa;
	private JFormattedTextField jtfData;
	private JLabel jlData, jlEngarrafamento, jlValorGarrafa;
	private JButton jbCadastrar, jbPesquisarEngarrafamento;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroVenda(BancoDeDados bd)
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
		jtfValorGarrafa = new JTextField();
		jlValorGarrafa = new JLabel("Valor da Garrafa:");
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
										.addComponent(jlData)
										.addComponent(jlValorGarrafa)
										.addComponent(jlEngarrafamento)														
									 )
							.addGroup(gl.createParallelGroup(Alignment.LEADING)
										.addComponent(jtfData,100,100,100)
										.addComponent(jtfValorGarrafa,100,100,100)
										.addComponent(jtfEngarrafamento,100,100,100)														
									 )
							.addGroup(gl.createParallelGroup(Alignment.LEADING)
										.addComponent(jbPesquisarEngarrafamento)														
									 )	
						 )
    			.addComponent(jbCadastrar)
		);
		
		gl.setVerticalGroup(gl.createSequentialGroup()
			  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
						  .addComponent(jlData)
						  .addComponent(jtfData)
					    )
		      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
						  .addComponent(jlValorGarrafa)
    			          .addComponent(jtfValorGarrafa)	
					    )
		      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
						  .addComponent(jlEngarrafamento)
    			          .addComponent(jtfEngarrafamento)
    			          .addComponent(jbPesquisarEngarrafamento)
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
			float tempo = Float.parseFloat(jtfValorGarrafa.getText().trim());
			if (tempo <= 0)
				throw new Exception("O valor de Garrafa deve ser maior que 0 (zero).");			
		}
		catch (NumberFormatException e)
		{
			throw new Exception("O valor de Garrafa deve ser um número.");
		}
	}
	
	private void limparCampos()
	{
		jtfData.setText("");			
		jtfData.setValue("");
		jtfEngarrafamento.setText("");
		jtfValorGarrafa.setText("");		
	}
	
	private void cadastrar()
	{		    		       
		try {
			check();
			String sql;
			String dataFormatoBanco = BancoDeDados.dataDeJavaParaOBanco(jtfData.getText());
			if (dataFormatoBanco == null)
				sql = "INSERT INTO venda (valor_garrafa, idEngarrafamento) " +
					     "values ('" + jtfValorGarrafa.getText() + "', '" +
					     			   jtfEngarrafamento.getText() + "')";	
			else
				sql = "INSERT INTO venda (data, valor_garrafa, idEngarrafamento) " +
					     "values ('" + dataFormatoBanco + "', '" +
					     			   jtfValorGarrafa.getText() + "', '" +
					     			   jtfEngarrafamento.getText() + "')";	
			bancoDeDados.executarUpdate(sql);
			JOptionPane.showMessageDialog(null, "Dado cadastrado com sucesso.");
			limparCampos();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
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
		else
			pesquisarEngarrafamento();		
	}
}
