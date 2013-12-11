package trabalho.iu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import trabalho.persistencia.BancoDeDados;

public class PainelExecutarQuery extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JLabel jlQuery;
	private JTextField jtQuery;
	private JButton jbExecutarQuery;
	private PainelRelatorioDePesquisa parent;
	private BancoDeDados bancoDeDados;
	
	public PainelExecutarQuery(BancoDeDados bancoDeDados)
	{
		this.bancoDeDados = bancoDeDados;
		adicioneComponentes();
		posicioneComponentes();		
	}	
	
	public void setParent(PainelRelatorioDePesquisa parent)
	{
		this.parent = parent;
	}
	
	protected void adicioneComponentes()
	{
		jlQuery = new JLabel("Query");
		jtQuery = new JTextField();
		
		jbExecutarQuery = new JButton ("Executar Query");
		jbExecutarQuery.addActionListener(this);
	}
	
	protected void posicioneComponentes() 
	{
		GroupLayout gl = new GroupLayout(this);
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		setLayout(gl);
		
		gl.setHorizontalGroup(gl.createParallelGroup(Alignment.CENTER)				   
								.addGroup(gl.createSequentialGroup()
									    .addGroup(gl.createParallelGroup(Alignment.LEADING)
									    			.addComponent(jlQuery)									    			
											     )
										  .addGroup(gl.createParallelGroup(Alignment.LEADING)
												  .addComponent(jtQuery,500,500,500)												  
										  ))
								.addGroup(gl.createSequentialGroup()
											.addComponent(jbExecutarQuery)
										 )
							  );
		gl.setVerticalGroup(gl.createSequentialGroup()
							.addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jlQuery)
										.addComponent(jtQuery)
									  )						    
							.addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jbExecutarQuery)
									  )
							);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String query = jtQuery.getText();
		try
		{				
			if (query.trim().length()==0)
				throw new Exception("O campo deve ser preenchido.");
			
			if (parent != null) {
				String[][] resultado = bancoDeDados.executarPesquisa(query);
				parent.atualizaTabela(resultado);
			}
			else {
				bancoDeDados.executarUpdate(query);
				JOptionPane.showMessageDialog(null, "Edição efetuada com sucesso.");
			}
		}
		catch (Exception erro) {
			JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
}
