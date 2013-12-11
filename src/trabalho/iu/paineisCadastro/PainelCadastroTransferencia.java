package trabalho.iu.paineisCadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.MaskFormatter;

import trabalho.iu.DialogoSelecaoDeChaveEstrangeira;
import trabalho.persistencia.BancoDeDados;

public class PainelCadastroTransferencia extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfFermentacao, jtfNovoFermentador;
	private JFormattedTextField jtfData;
	private JLabel jlFermentacao, jlNovoFermentador, jlFiltragem, jlData;
	private JButton jbCadastrar, jbPesquisarFermentacao;
	private ButtonGroup bgFiltragem;
	private JRadioButton jrbSim, jrbNao;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroTransferencia(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{	
		jtfNovoFermentador = new JTextField();
		jlNovoFermentador = new JLabel("Novo Fermentador:");		
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			jtfData = new JFormattedTextField(mascaraData);
		} 
		catch (ParseException e) {
			jtfData = new JFormattedTextField();
		}
		jlData = new JLabel("Data:");
		jlFiltragem = new JLabel("Filtragem:");
		jlFermentacao = new JLabel("Fermentação:");
		jtfFermentacao = new JTextField();
		jtfFermentacao.setEnabled(false);
		jrbSim = new JRadioButton("Sim");
		jrbSim.setSelected(true);
		jrbNao = new JRadioButton("Não");
		bgFiltragem = new ButtonGroup();
		bgFiltragem.add(jrbSim);
		bgFiltragem.add(jrbNao);		
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
														.addComponent(jlNovoFermentador)
														.addComponent(jlData)
														.addComponent(jlFiltragem)														
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfFermentacao,100,100,100)
														.addComponent(jtfNovoFermentador,100,100,100)
														.addComponent(jtfData,100,100,100)
														.addComponent(jrbSim)														
													 )
													
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
													    .addComponent(jrbNao)						
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
										 .addComponent(jlNovoFermentador)
				    			         .addComponent(jtfNovoFermentador)
									    )
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										 .addComponent(jlData)
										 .addComponent(jtfData)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlFiltragem)
				    			          .addComponent(jrbSim)
				    			          .addComponent(jrbNao)
									    )							  
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jbCadastrar)
									    )
							);
	}
		
	private void cadastrar()
	{		
		try {
			String sql;
			String dataFormatoBanco = BancoDeDados.dataDeJavaParaOBanco(jtfData.getText());
			if (dataFormatoBanco == null)
				sql = "INSERT INTO transferencia (idFermentacao, novo_fermentador, filtragem) " +
					     "values ('" + jtfFermentacao.getText() + "', '" +
					     			   jtfNovoFermentador.getText() + "', '" +				     			   
					     			   (jrbSim.isSelected() ? 1 : 0) + "')";
			else
				sql = "INSERT INTO transferencia (idFermentacao, novo_fermentador, data, filtragem) " +
					     "values ('" + jtfFermentacao.getText() + "', '" +
					     			   jtfNovoFermentador.getText() + "', '" +
					     			   dataFormatoBanco + "', '" +
					     			   (jrbSim.isSelected() ? 1 : 0) + "')";	
			bancoDeDados.executarUpdate(sql);
			JOptionPane.showMessageDialog(null, "Dado cadastrado com sucesso.");
			limparCampos();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limparCampos()
	{
		jtfData.setText("");	
		jtfData.setValue("");
		jtfFermentacao.setText("");
		jtfNovoFermentador.setText("");
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
