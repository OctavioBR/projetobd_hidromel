package trabalho.iu.paineisCadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import trabalho.persistencia.BancoDeDados;

public class PainelCadastroInsumo extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField jtfNome, jtfMarca, jtfUnidadeMedida;
	private JTextArea jtaDescricao;
	private JLabel jlNome, jlMarca, jlUnidadeMedida, jlDescricao, jlTipo;
	private ButtonGroup bgTipos;
	private JRadioButton jrbQuimico, jrbFerramenta, jrbMateriaPrima;
	private JButton jbCadastrar;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroInsumo(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{
		jtfNome = new JTextField();
		jlNome = new JLabel("Nome:");	
		jtfMarca = new JTextField();
		jlMarca = new JLabel("Marca:");
		jtfUnidadeMedida = new JTextField();
		jlUnidadeMedida = new JLabel("Unidade de Medida:");	
		jlTipo = new JLabel("Tipo");
		jrbQuimico = new JRadioButton("Quimico");
		jrbQuimico.setSelected(true);
		jrbFerramenta = new JRadioButton("Ferramenta");
		jrbMateriaPrima = new JRadioButton("Materia-prima");
		bgTipos = new ButtonGroup();
		bgTipos.add(jrbQuimico);
		bgTipos.add(jrbFerramenta);
		bgTipos.add(jrbMateriaPrima);
		jlDescricao = new JLabel("Descrição:");
		jtaDescricao = new JTextArea();
		jtaDescricao.setLineWrap(true);
		jtaDescricao.setWrapStyleWord(true);
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
														.addComponent(jlMarca)			
														.addComponent(jlUnidadeMedida)	
														.addComponent(jlTipo)
														.addComponent(jlDescricao)													
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jtfNome,100,100,100)														
														.addComponent(jtfMarca,100,100,100)
														.addComponent(jtfUnidadeMedida,100,100,100)
														.addComponent(jrbQuimico)														
														.addComponent(jtaDescricao,350,350,350)																											
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jrbFerramenta)													   															
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jrbMateriaPrima)													   															
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
										  .addComponent(jlMarca)
										  .addComponent(jtfMarca)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlUnidadeMedida)
										  .addComponent(jtfUnidadeMedida)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlTipo)
										  .addComponent(jrbQuimico)
										  .addComponent(jrbFerramenta)
										  .addComponent(jrbMateriaPrima)
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

	private String getStringTipo()
	{
		if (jrbQuimico.isSelected())
			return jrbQuimico.getText();
		else if (jrbFerramenta.isSelected())
			return jrbFerramenta.getText();
		else
			return jrbMateriaPrima.getText();
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
		
		if (jtfUnidadeMedida.getText().trim().length() == 0)
			throw new Exception("O campo Unidade de Medida é obrigatório.");
		
		if (jtaDescricao.getText().trim().length() == 0)
			throw new Exception("É obrigatório inserir algum tipo de descrição");		
	}
	
	private void limparCampos()
	{
		jtfMarca.setText("");				
		jtfNome.setText("");
		jtfUnidadeMedida.setText("");
		jtaDescricao.setText("");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		String sql = "INSERT INTO insumo (nome, tipo, marca, descricao, unidade_medida) " +
				     "values ('" + jtfNome.getText() + "', '" +
				     			   getStringTipo() + "', '" +
				     			   jtfMarca.getText() + "', '" +
				     			   jtaDescricao.getText() + "', '" +
				     			   jtfUnidadeMedida.getText() + "')";	     		       
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
}
