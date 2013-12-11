package trabalho.iu.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuVisualizacao extends JMenu 
{
	private static final long serialVersionUID = 1L;

	public MenuVisualizacao(MenuBar menuBar) 
	{
		super("Visualizar");
		
		JMenuItem item = new JMenuItem("Cargos");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_CARGO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Compras de Insumos");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_COMPRA_INSUMO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Departamentos");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_DEPARTAMENTO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Engarrafamentos");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_ENGARRAFAMENTO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Fermenta��es");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_FERMENTACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Fornecedores");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_FORNECEDOR.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Fun��es");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_FUNCAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Garrafas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_GARRAFA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Inocula��es");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Insumos");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_INSUMO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Insumos nas Inocula��es");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_INSUMO_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);		
		
		item = new JMenuItem("Investidores");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_INVESTIDOR.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Investimentos");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_INVESTIMENTO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Locais");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_LOCAL.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Lotes");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_LOTE.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Matura��es");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_MATURACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Medi��es");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_MEDICAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_PESSOA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoas F�sicas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_PESSOA_FISICA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoas Jur�dicas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_PESSOA_JURIDICA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Receitas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_RECEITA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Respons�veis nas Inocula��es");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_RESONSAVEL_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);			
		
		item = new JMenuItem("Sanitiza��es");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_SANITIZACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("S�cios");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_SOCIO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Transfer�ncias");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_TRANSFERENCIA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Vendas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_VENDA.name());
		item.addActionListener(menuBar);
		add(item);
	}
}
