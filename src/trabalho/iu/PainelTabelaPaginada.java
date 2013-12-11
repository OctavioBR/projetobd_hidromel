package trabalho.iu;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelTabelaPaginada extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTable tabela;
	private ModeloDeTabela modelo;
	private JScrollPane scroll;
	private JButton jbReorganizar, jbPrevious, jbNext;
	private JLabel jlContPaginas, jlLinhasPorPagina;
	private JTextField jtfLinhasPorPagina;
	private int linhasPorPagina, paginaAtual, ultimaPagina;
	private String[][] resultadoPesquisa;
	
	public PainelTabelaPaginada()
	{
		adicioneComponentes(false);
		posicioneComponentes();
		tabela.setEnabled(false);
	}
	
	public PainelTabelaPaginada(boolean habilitarSelecaoTabela)
	{
		adicioneComponentes(false);
		posicioneComponentes();
		tabela.setEnabled(habilitarSelecaoTabela);
	}
	
	public PainelTabelaPaginada(boolean habilitarSelecaoTabela, boolean habilitarEdicaoTabela)
	{
		adicioneComponentes(habilitarEdicaoTabela);
		posicioneComponentes();
		tabela.setEnabled(habilitarSelecaoTabela);
	}
	
	public void setResultadoPesquisa(String[][] resultado)
	{
		resultadoPesquisa = resultado;
		setDados(dadosDaPagina(0));
	}
	
	public void addCellChangeListener(AbstractAction action) 
	{
		TableCellListener tcl = new TableCellListener(tabela, action);
		tcl.getClass();
	}
	
	protected void adicioneComponentes(boolean habilitarEdicaoTabela) 
	{
		modelo = new ModeloDeTabela(habilitarEdicaoTabela);
		tabela = new JTable(modelo);
		tabela.getTableHeader().setReorderingAllowed(false);
		scroll = new JScrollPane(tabela);
		jlContPaginas = new JLabel ("1/1");
		jbPrevious = new JButton("Anterior");
		jbPrevious.addActionListener(this);
		jbPrevious.setEnabled(false);
		jbNext = new JButton("Próxima");
		jbNext.setEnabled(false);
		jbNext.addActionListener(this);
		jbReorganizar = new JButton("Reorganizar Tabela");
		jbReorganizar.addActionListener(this);
		jlLinhasPorPagina = new JLabel ("Dados por página:");
		jtfLinhasPorPagina = new JTextField("50");
		linhasPorPagina = 50;
	}

	protected void posicioneComponentes() 
	{
		GroupLayout gl = new GroupLayout(this);
		setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
	    gl.setAutoCreateGaps(true);

		
		gl.setHorizontalGroup(gl.createParallelGroup(Alignment.CENTER)
								.addGroup(gl.createSequentialGroup()
											.addComponent(jlLinhasPorPagina)
											.addComponent(jtfLinhasPorPagina,50,50,50)
											.addComponent(jbReorganizar)
										 )
								.addComponent(scroll)
								.addGroup(gl.createSequentialGroup()
												.addComponent(jbPrevious)
												.addComponent(jlContPaginas)
												.addComponent(jbNext)
										 )
							);
		gl.setVerticalGroup(gl.createSequentialGroup()
								.addGroup(gl.createParallelGroup(Alignment.BASELINE)
										.addComponent(jlLinhasPorPagina)
										.addComponent(jtfLinhasPorPagina)
										.addComponent(jbReorganizar)
									 )
								.addComponent(scroll)
								.addGroup(gl.createParallelGroup(Alignment.BASELINE)	
												.addComponent(jbPrevious)
												.addComponent(jlContPaginas)
												.addComponent(jbNext)
										 )
							);
	}
		
	private void setDados(String[][] dados) 
	{
		if (dados != null)
			modelo.setDados(dados);
	}
	
	public void setCabecalho(String[] cabecalho) 
	{
		if (cabecalho != null)
			modelo.setCabecalho(cabecalho);
	}
	
	public int getLinhaSelecionada()
	{
		return tabela.getSelectedRow();
	}
	
	public int getColunaSelecionada()
	{
		return tabela.getSelectedColumn();
	}
	
	public String getValueAt(int linha, int coluna)
	{
		return (String)modelo.getValueAt(linha, coluna);
	}
	
	public void setValueAt(Object obj, int linha, int coluna)
	{
		modelo.setValueAt(obj, linha, coluna);
	}
	
	public String getColumnName(int coluna)
	{
		return modelo.getColumnName(coluna);
	}
	
	public void atualizaTabela(String[][] resultadoPesquisa)
	{
		setCabecalho(resultadoPesquisa[0]);
		String[][] dados;
		if (resultadoPesquisa.length > 1) {
			int nLinhas = resultadoPesquisa.length-1;
			int nColunas = resultadoPesquisa[0].length;			
			dados = new String[nLinhas][nColunas];
			for (int i = 1; i < resultadoPesquisa.length; i++)
				dados[i-1] = resultadoPesquisa[i];
		}
		else
			dados = new String[0][0];
		
		setResultadoPesquisa(dados);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == jbNext)	
		{
			paginaAtual++;
		}
		else if (e.getSource() == jbPrevious)
		{
			paginaAtual--;			
		}
		else
		{
			try
			{
				int linhas = Integer.parseInt(jtfLinhasPorPagina.getText());
				if (linhas <= 0)
					throw new NumberFormatException();

				paginaAtual = 0;
				atualizeLinhasPorPagina(linhas);		
			}
			catch (NumberFormatException erro)
			{
				JOptionPane.showMessageDialog(null, "Dado Inválido. Digite um número superior a zero.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		modelo.setDados(dadosDaPagina(paginaAtual));
		atualizePainel();
	}
	
	public void atualizePainel()
	{
		ultimaPagina = (resultadoPesquisa.length - 1)/linhasPorPagina;
		jlContPaginas.setText((paginaAtual+1)+"/"+(ultimaPagina+1));
		jbPrevious.setEnabled(paginaAtual != 0);
		jbNext.setEnabled(paginaAtual != ultimaPagina);
	}

	public String[][] dadosDaPagina(int pagina) 
	{
		int startIndex = pagina*linhasPorPagina;
		int finalIndex = startIndex+linhasPorPagina;
		if (finalIndex>resultadoPesquisa.length)
			finalIndex = resultadoPesquisa.length;
		int linhas = finalIndex-startIndex;
		String[][] dados = new String[linhas][5];
		System.arraycopy(resultadoPesquisa, startIndex, dados, 0, linhas);
		return dados;
	}

	public void atualizeLinhasPorPagina(int linhas) 
	{
		linhasPorPagina = linhas;
	}
}
