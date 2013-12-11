package trabalho.iu.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuAvancado extends JMenu
{
	private static final long serialVersionUID = 1L;

	public MenuAvancado(MenuBar menuBar) 
	{
		super("Avan�ado");
		
		JMenuItem item = new JMenuItem("Executar Pesquisa");
		item.setActionCommand(EnumOpcoes.EXECUTAR_PESQUISA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Executar A��o de Edi��o");
		item.setActionCommand(EnumOpcoes.EXECUTAR_UPDATE.name());
		item.addActionListener(menuBar);
		add(item);
	}
}
