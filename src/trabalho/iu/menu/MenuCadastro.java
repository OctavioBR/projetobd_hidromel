package trabalho.iu.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuCadastro extends JMenu
{
	private static final long serialVersionUID = 1L;

	public MenuCadastro(MenuBar menuBar) 
	{
		super("Cadastrar");
		
		JMenuItem item = new JMenuItem("Cargo");
		item.setActionCommand(EnumOpcoes.CADASTRAR_CARGO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Compra de Insumo");
		item.setActionCommand(EnumOpcoes.CADASTRAR_COMPRA_INSUMO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Departamento");
		item.setActionCommand(EnumOpcoes.CADASTRAR_DEPARTAMENTO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Engarrafamento");
		item.setActionCommand(EnumOpcoes.CADASTRAR_ENGARRAFAMENTO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Fermentação");
		item.setActionCommand(EnumOpcoes.CADASTRAR_FERMENTACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Fornecedor");
		item.setActionCommand(EnumOpcoes.CADASTRAR_FORNECEDOR.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Função");
		item.setActionCommand(EnumOpcoes.CADASTRAR_FUNCAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Garrafa");
		item.setActionCommand(EnumOpcoes.CADASTRAR_GARRAFA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Inoculação");
		item.setActionCommand(EnumOpcoes.CADASTRAR_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Insumo");
		item.setActionCommand(EnumOpcoes.CADASTRAR_INSUMO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Insumo na Inoculação");
		item.setActionCommand(EnumOpcoes.CADASTRAR_INSUMO_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);		
		
		item = new JMenuItem("Investimento");
		item.setActionCommand(EnumOpcoes.CADASTRAR_INVESTIMENTO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Local");
		item.setActionCommand(EnumOpcoes.CADASTRAR_LOCAL.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Lote");
		item.setActionCommand(EnumOpcoes.CADASTRAR_LOTE.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Maturação");
		item.setActionCommand(EnumOpcoes.CADASTRAR_MATURACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Medição");
		item.setActionCommand(EnumOpcoes.CADASTRAR_MEDICAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoa");
		item.setActionCommand(EnumOpcoes.CADASTRAR_PESSOA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoa Física");
		item.setActionCommand(EnumOpcoes.CADASTRAR_PESSOA_FISICA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoa Jurídica");
		item.setActionCommand(EnumOpcoes.CADASTRAR_PESSOA_JURIDICA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Receita");
		item.setActionCommand(EnumOpcoes.CADASTRAR_RECEITA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Responsável na Inoculação");
		item.setActionCommand(EnumOpcoes.CADASTRAR_RESONSAVEL_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);			
		
		item = new JMenuItem("Sanitização");
		item.setActionCommand(EnumOpcoes.CADASTRAR_SANITIZACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Sócio");
		item.setActionCommand(EnumOpcoes.CADASTRAR_SOCIO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Transferência");
		item.setActionCommand(EnumOpcoes.CADASTRAR_TRANSFERENCIA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Venda");
		item.setActionCommand(EnumOpcoes.CADASTRAR_VENDA.name());
		item.addActionListener(menuBar);
		add(item);
	}
}
