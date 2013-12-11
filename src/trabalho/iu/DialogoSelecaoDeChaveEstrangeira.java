package trabalho.iu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;


public class DialogoSelecaoDeChaveEstrangeira extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private PainelTabelaPaginada painelTabela;
	private JButton jbSelecionar;
	private int indiceSelecionado;
	
	public DialogoSelecaoDeChaveEstrangeira(String[][] dados) 
	{
		super((JDialog)null, "Selecionar Dado", true);
		indiceSelecionado = -1;
		painelTabela = new PainelTabelaPaginada(true);
		atualizaTabela(dados);
		jbSelecionar = new JButton("Selecionar");
		jbSelecionar.addActionListener(this);		
		posicioneComponentes();
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void posicioneComponentes()
	{
		GroupLayout gl = new GroupLayout(getContentPane());
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		setLayout(gl);
		
		gl.setHorizontalGroup(gl.createParallelGroup(Alignment.CENTER)				   
				    			.addComponent(painelTabela)
				    			.addComponent(jbSelecionar)		
		);
		
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addComponent(painelTabela)
				    		  .addComponent(jbSelecionar)
		);
	}
	
	public void atualizaTabela(String[][] resultadoPesquisa)
	{
		painelTabela.atualizaTabela(resultadoPesquisa);
	}

	public int interaja()
	{
		setVisible(true);
		return indiceSelecionado;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		setVisible(false);
		indiceSelecionado = painelTabela.getLinhaSelecionada();
		dispose();
	}
}
