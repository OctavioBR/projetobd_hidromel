package trabalho.iu;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JPanel;


public class PainelRelatorioDePesquisa extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel painelPesquisa;
	private PainelTabelaPaginada painelTabela;
	
	public PainelRelatorioDePesquisa(JPanel painelPesquisa)
	{
		this.painelPesquisa = painelPesquisa;
		painelTabela = new PainelTabelaPaginada();
		posicioneComponentes();
	}
	
	public void posicioneComponentes()
	{
		GroupLayout gl = new GroupLayout(this);
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		setLayout(gl);
		
		gl.setHorizontalGroup(gl.createParallelGroup(Alignment.CENTER)				   
				    			.addComponent(painelPesquisa)
				    			.addComponent(painelTabela)		
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
							  .addComponent(painelPesquisa)
				    		  .addComponent(painelTabela)
		);
	}
	
	public void atualizaTabela(String[][] resultadoPesquisa)
	{
		painelTabela.atualizaTabela(resultadoPesquisa);
	}
}
