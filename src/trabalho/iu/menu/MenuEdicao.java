package trabalho.iu.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuEdicao extends JMenu 
{
	private static final long serialVersionUID = 1L;

	public MenuEdicao(MenuBar menuBar) 
	{
		super("Edição");
		
		JMenuItem item = new JMenuItem("Cargos");
		item.setActionCommand(EnumOpcoes.EDITAR_CARGO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Compras de Insumos");
		item.setActionCommand(EnumOpcoes.EDITAR_COMPRA_INSUMO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Departamentos");
		item.setActionCommand(EnumOpcoes.EDITAR_DEPARTAMENTO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Engarrafamentos");
		item.setActionCommand(EnumOpcoes.EDITAR_ENGARRAFAMENTO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Fermentações");
		item.setActionCommand(EnumOpcoes.EDITAR_FERMENTACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Fornecedores");
		item.setActionCommand(EnumOpcoes.EDITAR_FORNECEDOR.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Funções");
		item.setActionCommand(EnumOpcoes.EDITAR_FUNCAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Garrafas");
		item.setActionCommand(EnumOpcoes.EDITAR_GARRAFA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Inoculações");
		item.setActionCommand(EnumOpcoes.EDITAR_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Insumos");
		item.setActionCommand(EnumOpcoes.EDITAR_INSUMO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Insumos nas Inoculações");
		item.setActionCommand(EnumOpcoes.EDITAR_INSUMO_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);		
		
		item = new JMenuItem("Investidores");
		item.setActionCommand(EnumOpcoes.EDITAR_INVESTIDOR.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Investimentos");
		item.setActionCommand(EnumOpcoes.EDITAR_INVESTIMENTO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Locais");
		item.setActionCommand(EnumOpcoes.EDITAR_LOCAL.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Lotes");
		item.setActionCommand(EnumOpcoes.EDITAR_LOTE.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Maturações");
		item.setActionCommand(EnumOpcoes.EDITAR_MATURACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Medições");
		item.setActionCommand(EnumOpcoes.EDITAR_MEDICAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoas");
		item.setActionCommand(EnumOpcoes.EDITAR_PESSOA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoas Físicas");
		item.setActionCommand(EnumOpcoes.EDITAR_PESSOA_FISICA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoas Jurídicas");
		item.setActionCommand(EnumOpcoes.EDITAR_PESSOA_JURIDICA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Receitas");
		item.setActionCommand(EnumOpcoes.EDITAR_RECEITA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Responsáveis nas Inoculações");
		item.setActionCommand(EnumOpcoes.EDITAR_RESONSAVEL_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);			
		
		item = new JMenuItem("Sanitizações");
		item.setActionCommand(EnumOpcoes.EDITAR_SANITIZACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Sócios");
		item.setActionCommand(EnumOpcoes.EDITAR_SOCIO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Transferências");
		item.setActionCommand(EnumOpcoes.EDITAR_TRANSFERENCIA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Vendas");
		item.setActionCommand(EnumOpcoes.EDITAR_VENDA.name());
		item.addActionListener(menuBar);
		add(item);
	}
}
