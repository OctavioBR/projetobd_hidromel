package trabalho.iu.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuAvancado extends JMenu
{
	private static final long serialVersionUID = 1L;

	public MenuAvancado(MenuBar menuBar) 
	{
		super("Avançado");
		
		JMenuItem item = new JMenuItem("Executar Pesquisa");
		item.setActionCommand(EnumOpcoes.EXECUTAR_PESQUISA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Executar Ação de Edição");
		item.setActionCommand(EnumOpcoes.EXECUTAR_UPDATE.name());
		item.addActionListener(menuBar);
		add(item);
	}
}
