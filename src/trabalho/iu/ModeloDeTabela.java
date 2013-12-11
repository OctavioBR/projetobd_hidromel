package trabalho.iu;

import javax.swing.table.AbstractTableModel;

public class ModeloDeTabela extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	protected String[] cabecalho;
	protected String[][] dados;
	protected boolean editavel;

	public ModeloDeTabela() 
	{
		this.cabecalho = new String[0];
		this.dados = new String[0][0];
		editavel = false;
	}
	
	public ModeloDeTabela(boolean editavel) 
	{
		this.cabecalho = new String[0];
		this.dados = new String[0][0];
		this.editavel = editavel;
	}

	public int getRowCount() 
	{
		return dados.length;
	}

	public int getColumnCount() 
	{
		return cabecalho.length;
	}

	public boolean isCellEditable(int row, int col)
	{
		return editavel && col > 0;
	}
	
	public Object getValueAt(int row, int col) 
	{
		Object x = null;
		if (row < dados.length && col < cabecalho.length)
			x = dados[row][col];
		return x;
	}
	
	public void setValueAt(Object obj, int row, int col) 
	{
		if (row < dados.length && col < cabecalho.length)			
			dados[row][col] = obj == null ? "" : obj.toString();
	}
	
	public String getColumnName(int col) 
	{
		String s = null;
		if (col<cabecalho.length)
			s = cabecalho[col];
		return s;
	}

	public void setDados (String[][] dados)
	{
		this.dados = dados;
		fireTableDataChanged();
	}
	
	public void setCabecalho (String[] cabecalho)
	{
		this.cabecalho = cabecalho;
		fireTableStructureChanged();
	}
}