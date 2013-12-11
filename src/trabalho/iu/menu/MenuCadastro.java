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
		
		item = new JMenuItem("Fermenta��o");
		item.setActionCommand(EnumOpcoes.CADASTRAR_FERMENTACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Fornecedor");
		item.setActionCommand(EnumOpcoes.CADASTRAR_FORNECEDOR.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Fun��o");
		item.setActionCommand(EnumOpcoes.CADASTRAR_FUNCAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Garrafa");
		item.setActionCommand(EnumOpcoes.CADASTRAR_GARRAFA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Inocula��o");
		item.setActionCommand(EnumOpcoes.CADASTRAR_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Insumo");
		item.setActionCommand(EnumOpcoes.CADASTRAR_INSUMO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Insumo na Inocula��o");
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
		
		item = new JMenuItem("Matura��o");
		item.setActionCommand(EnumOpcoes.CADASTRAR_MATURACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Medi��o");
		item.setActionCommand(EnumOpcoes.CADASTRAR_MEDICAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoa");
		item.setActionCommand(EnumOpcoes.CADASTRAR_PESSOA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoa F�sica");
		item.setActionCommand(EnumOpcoes.CADASTRAR_PESSOA_FISICA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Pessoa Jur�dica");
		item.setActionCommand(EnumOpcoes.CADASTRAR_PESSOA_JURIDICA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Receita");
		item.setActionCommand(EnumOpcoes.CADASTRAR_RECEITA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Respons�vel na Inocula��o");
		item.setActionCommand(EnumOpcoes.CADASTRAR_RESONSAVEL_INOCULACAO.name());
		item.addActionListener(menuBar);
		add(item);			
		
		item = new JMenuItem("Sanitiza��o");
		item.setActionCommand(EnumOpcoes.CADASTRAR_SANITIZACAO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("S�cio");
		item.setActionCommand(EnumOpcoes.CADASTRAR_SOCIO.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Transfer�ncia");
		item.setActionCommand(EnumOpcoes.CADASTRAR_TRANSFERENCIA.name());
		item.addActionListener(menuBar);
		add(item);
		
		item = new JMenuItem("Venda");
		item.setActionCommand(EnumOpcoes.CADASTRAR_VENDA.name());
		item.addActionListener(menuBar);
		add(item);
	}
}
