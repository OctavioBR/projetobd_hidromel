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

public class PainelCadastroCompraInsumo extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfFornecedor, jtfInsumo, jtfPrecoUnitario, jtfQuantidade;
	private JFormattedTextField jtfData;
	private JLabel jlFornecedor, jlInsumo, jlPrecoUnitario, jlQuantidade, jlData;
	private JButton jbCadastrar, jbPesquisarFornecedor, jbPesquisarInsumo;
	private int idFornecedor, idInsumo;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroCompraInsumo(BancoDeDados bd)
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
		jtfPrecoUnitario = new JTextField();
		jlPrecoUnitario = new JLabel("Preço Unitário:");
		jtfQuantidade = new JTextField();
		jlQuantidade = new JLabel("Quantidade:");	
		jlFornecedor = new JLabel("Fornecedor:");
		jtfFornecedor = new JTextField();
		jtfFornecedor.setEnabled(false);
		jlData = new JLabel("Data da compra:");
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			jtfData = new JFormattedTextField(mascaraData);
		} 
		catch (ParseException e) {
			jtfData = new JFormattedTextField();
		}
		jbCadastrar = new JButton("Cadastrar");
		jbCadastrar.addActionListener(this);
		jbPesquisarFornecedor = new JButton("Pesquisar...");
		jbPesquisarFornecedor.addActionListener(this);
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
														.addComponent(jlFornecedor)
														.addComponent(jlInsumo)
														.addComponent(jlPrecoUnitario)
														.addComponent(jlQuantidade)
														.addComponent(jlData)
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfFornecedor,100,100,100)
														.addComponent(jtfInsumo,100,100,100)
														.addComponent(jtfPrecoUnitario,100,100,100)
														.addComponent(jtfQuantidade,100,100,100)
														.addComponent(jtfData,100,100,100)
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jbPesquisarFornecedor)
														.addComponent(jbPesquisarInsumo)
													 )	
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlFornecedor)
										  .addComponent(jtfFornecedor)
										  .addComponent(jbPesquisarFornecedor)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlInsumo)
										  .addComponent(jtfInsumo)
										  .addComponent(jbPesquisarInsumo)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlPrecoUnitario)
										  .addComponent(jtfPrecoUnitario)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlQuantidade)
										  .addComponent(jtfQuantidade)
									    )
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlData)
										  .addComponent(jtfData)
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
			if (jtfPrecoUnitario.getText().trim().length() == 0)
				throw new Exception("O campo Preço Unitário deve ser preenchido.");
			float precoUnitario = Float.parseFloat(jtfPrecoUnitario.getText().trim());
			if (precoUnitario <= 0)
				throw new Exception("O Preço Unitário deve ser mais que 0 (zero).");			
		}
		catch (NumberFormatException e)
		{
			throw new Exception("O valor de Preço Unitário deve ser um número.");
		}
		
		try
		{
			if (jtfQuantidade.getText().trim().length() == 0)
				throw new Exception("O campo Quantidade deve ser preenchido.");
			int quantidade = Integer.parseInt(jtfQuantidade.getText().trim());
			if (quantidade <= 0)
				throw new Exception("O valor de Quantidade deve ser um número inteiro maior que 0 (zero).");			
		}
		catch (NumberFormatException e)
		{
			throw new Exception("O valor de Quantidade deve ser um número inteiro.");
		}
		
		String dataFormatoBanco = BancoDeDados.dataDeJavaParaOBanco(jtfData.getText());
		if (dataFormatoBanco == null)
			throw new Exception("O campo Data deve ser preenchido.");
	}
	
	private void limparCampos()
	{
		jtfFornecedor.setText("");
		jtfInsumo.setText("");
		jtfPrecoUnitario.setText("");
		jtfQuantidade.setText("");
		jtfData.setText("");
		jtfData.setValue("");
		idFornecedor = -1;
		idInsumo = -1;
	}
	
	private void cadastrar()
	{
		String sql = "INSERT INTO compra_insumo (idFornecedor, idInsumo, preco_unitario, quantidade, data) " +
				     "values ('" + idFornecedor + "', '" +
				     			   idInsumo + "', '" +
				     			   jtfPrecoUnitario.getText() + "', '" +
				     			   jtfQuantidade.getText() + "', '" +
				     			   BancoDeDados.dataDeJavaParaOBanco(jtfData.getText()) + "')";	     		       
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
	
	private void pesquisarFornecedor()
	{
		String sql = "SELECT id, nome FROM fornecedor;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);					
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				idFornecedor = Integer.parseInt(resultado[selecionado][0]);						
				jtfFornecedor.setText(resultado[selecionado][1]);
			}
		} 
		catch (SQLException e) {}
	}
	
	private void pesquisarInsumo()
	{
		String sql = "SELECT id, nome, tipo, marca FROM insumo;";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			DialogoSelecaoDeChaveEstrangeira dlg = new DialogoSelecaoDeChaveEstrangeira(resultado);		
			int selecionado = dlg.interaja() + 1;
			if (selecionado != 0) {
				idInsumo = Integer.parseInt(resultado[selecionado][0]);						
				jtfInsumo.setText(resultado[selecionado][1]);
			}
		} 
		catch (SQLException e) {}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == jbCadastrar)
			cadastrar();
		else if (event.getSource() == jbPesquisarFornecedor)
			pesquisarFornecedor();		
		else
			pesquisarInsumo();
	}
}
