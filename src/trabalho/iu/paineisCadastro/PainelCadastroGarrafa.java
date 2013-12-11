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
import javax.swing.GroupLayout.Alignment;

import trabalho.persistencia.BancoDeDados;

public class PainelCadastroGarrafa extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JLabel jlTipoTampa, jlVolume, jlMaterial;
	private JButton jbCadastrar;
	private ButtonGroup bgTipoTampa, bgMaterial, bgVolume;
	private JRadioButton jrbVidro, jrbPlastico, jrbMetal, jrbtampaPlastico, jrbRolha, jrb036, jrb06, jrb1, jrb2, jrb5;
	private BancoDeDados bancoDeDados;
	
	public PainelCadastroGarrafa(BancoDeDados bd)
	{
		bancoDeDados = bd;
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes()
	{	
		jlVolume = new JLabel("Volume (litros):");
		jlMaterial = new JLabel("Material:");
		jlTipoTampa = new JLabel("Tipo da Tampa:");
		jrbVidro = new JRadioButton("Vidro");
		jrbVidro.setSelected(true);
		jrbPlastico = new JRadioButton("Plástico");
		bgTipoTampa = new ButtonGroup();
		bgTipoTampa.add(jrbVidro);
		bgTipoTampa.add(jrbPlastico);
		jrbMetal = new JRadioButton("Metal");
		jrbMetal.setSelected(true);
		jrbtampaPlastico =	new JRadioButton("Plástico");
		jrbRolha = new JRadioButton("Rolha");
		bgMaterial = new ButtonGroup();
		bgMaterial.add(jrbMetal);
		bgMaterial.add(jrbtampaPlastico);
		bgMaterial.add(jrbRolha);		
		jrb036 = new JRadioButton("0.36");
		jrb036.setSelected(true);
		jrb06 =	new JRadioButton("0.6");
		jrb1 = new JRadioButton("1");
		jrb2 = new JRadioButton("2");
		jrb5 = new JRadioButton("5");
		bgVolume = new ButtonGroup();
		bgVolume.add(jrb036);
		bgVolume.add(jrb06);
		bgVolume.add(jrb1);
		bgVolume.add(jrb2);
		bgVolume.add(jrb5);		
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
														.addComponent(jlTipoTampa)
														.addComponent(jlMaterial)			
														.addComponent(jlVolume)																																						
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jrbMetal)														
														.addComponent(jrbVidro)
														.addComponent(jrb036)																																				
													 )
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jrbtampaPlastico)	
														.addComponent(jrbPlastico)	
														.addComponent(jrb06)															
													 )		
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jrbRolha)	
														.addComponent(jrb1)	
																												
													 )	
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jrb2)																									
													 )	
											.addGroup(gl.createParallelGroup(Alignment.LEADING)
														.addComponent(jrb5)																													
													 )														 
										 )
				    			.addComponent(jbCadastrar)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlTipoTampa)
										  .addComponent(jrbMetal)
										  .addComponent(jrbtampaPlastico)
										  .addComponent(jrbRolha)
									    )						      
						      .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlMaterial)				    			
										  .addComponent(jrbVidro)
										  .addComponent(jrbPlastico)
									    )	
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										  .addComponent(jlVolume)
										  .addComponent(jrb036)
										  .addComponent(jrb06)
										  .addComponent(jrb1)
										  .addComponent(jrb2)
										  .addComponent(jrb5)
									    )						 									
							  .addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jbCadastrar)			    			
									    )												
							);
	}
	
	private String getStringTipoTampa()
	{
		String finalidade;
		if (jrbMetal.isSelected())
			finalidade = jrbMetal.getText();
		else if (jrbtampaPlastico.isSelected())
			finalidade = jrbtampaPlastico.getText();
		else
			finalidade = jrbRolha.getText();
		
		return finalidade;
	}
	
	private String getStringMaterial()
	{
		String finalidade;
		if (jrbVidro.isSelected())
			finalidade = jrbVidro.getText();
		else
			finalidade = jrbPlastico.getText();
				
		return finalidade;
	}
	
	private String getStringVolume()
	{
		String finalidade;
		if (jrb036.isSelected())
			finalidade = jrb036.getText();
		else if (jrb06.isSelected())
			finalidade = jrb06.getText();
		else if (jrb1.isSelected())
			finalidade = jrb1.getText();
		else if (jrb2.isSelected())
			finalidade = jrb2.getText();
		else
			finalidade = jrb5.getText();
		
		return finalidade;
	}
			
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		String sql = "INSERT INTO garrafa (tipo_tampa, material, volume) " +
					 "values ('" + getStringTipoTampa() + "', '" +
					 			   getStringMaterial() + "', '" +
					 			   getStringVolume() + "')";		
		try {
			bancoDeDados.executarUpdate(sql);
			JOptionPane.showMessageDialog(null, "Dado cadastrado com sucesso.");
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
}
