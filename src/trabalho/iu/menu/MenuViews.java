package trabalho.iu.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuViews extends JMenu 
{
	private static final long serialVersionUID = 1L;

	public MenuViews(MenuBar menuBar) 
	{
		super("Views");	
		
		JMenuItem item = new JMenuItem("Caixa Atual");
		item.setActionCommand(EnumOpcoes.VIEW_CAIXAATUAL.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Custo Por Lote");
		item.setActionCommand(EnumOpcoes.VIEW_CUSTOPORLOTE.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Despesas Por Dia");
		item.setActionCommand(EnumOpcoes.VIEW_DESPESASPORDIA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Fornecedor Mais Barato");
		item.setActionCommand(EnumOpcoes.VIEW_FORNECEDORMAISBARATO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Garrafas Disponíveis Para Venda");
		item.setActionCommand(EnumOpcoes.VIEW_GARRAFASDISPONIVEISVENDA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Insumos Utilizados Por Lote");
		item.setActionCommand(EnumOpcoes.VIEW_INSUMOSUTILIZADOSPORLOTE.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Lucro");
		item.setActionCommand(EnumOpcoes.VIEW_LUCRO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Lucro Por Lote");
		item.setActionCommand(EnumOpcoes.VIEW_LUCROPORLOTE.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Maior Investidor");
		item.setActionCommand(EnumOpcoes.VIEW_MAIORINVESTIDOR.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Matéria Prima Disponível");
		item.setActionCommand(EnumOpcoes.VIEW_MATERIAPRIMADISPONIVEL.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Média de Densidade e PH por Lote");
		item.setActionCommand(EnumOpcoes.VIEW_MEDIADENSIDADEPHLOTE.name());
		item.addActionListener(menuBar);
		add(item);		
		
		item = new JMenuItem("Receita Com Maior Tempo de Fermentação");
		item.setActionCommand(EnumOpcoes.VIEW_RECEITAMAIORTEMPOFERMENTACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Receita Com Menor Tempo de Fermentação");
		item.setActionCommand(EnumOpcoes.VIEW_RECEITAMENORTEMPOFERMENTACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Sócio Mais Ativo Na Produção");
		item.setActionCommand(EnumOpcoes.VIEW_SOCIOMAISATIVONAPRODUCAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Venda Mais Lucrativa");
		item.setActionCommand(EnumOpcoes.VIEW_VENDAMAISLUCRATIVA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Vendas de Garrafa Por Dia");
		item.setActionCommand(EnumOpcoes.VIEW_VENDASGARRAFAPORDIA.name());
		item.addActionListener(menuBar);
		add(item);		
	}
}
