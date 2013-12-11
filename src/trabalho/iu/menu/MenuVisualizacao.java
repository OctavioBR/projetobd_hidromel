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
		
		item = new JMenuItem("Fermentações");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_FERMENTACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Fornecedores");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_FORNECEDOR.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Funções");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_FUNCAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Garrafas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_GARRAFA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Inoculações");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Insumos");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_INSUMO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Insumos nas Inoculações");
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
		
		item = new JMenuItem("Maturações");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_MATURACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Medições");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_MEDICAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_PESSOA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoas Físicas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_PESSOA_FISICA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoas Jurídicas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_PESSOA_JURIDICA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Receitas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_RECEITA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Responsáveis nas Inoculações");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_RESONSAVEL_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);			
		
		item = new JMenuItem("Sanitizações");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_SANITIZACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Sócios");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_SOCIO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Transferências");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_TRANSFERENCIA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Vendas");
		item.setActionCommand(EnumOpcoes.VISUALIZAR_VENDA.name());
		item.addActionListener(menuBar);
		add(item);
	}
}
