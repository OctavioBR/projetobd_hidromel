package trabalho.iu.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

import trabalho.iu.JanelaPrincipal;

public class MenuBar extends JMenuBar implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JanelaPrincipal janela;
	
	public MenuBar (JanelaPrincipal janela)
	{		
		this.janela = janela;
		
		add(new MenuCadastro(this));
		add(new MenuVisualizacao(this));
		add(new MenuEdicao(this));
		add(new MenuViews(this));
		add(new MenuAvancado(this));			
	}
	
	public void actionPerformed(ActionEvent e)
	{		
		EnumOpcoes op = EnumOpcoes.valueOf(e.getActionCommand());
		switch (op)
		{			
			case CADASTRAR_CARGO: janela.cadastrarCargo(); break;
			case CADASTRAR_COMPRA_INSUMO: janela.cadastrarCompraInsumo(); break;
			case CADASTRAR_DEPARTAMENTO: janela.cadastrarDepartamento(); break;
			case CADASTRAR_ENGARRAFAMENTO: janela.cadastrarEngarrafamento(); break;
			case CADASTRAR_FERMENTACAO: janela.cadastrarFermentacao(); break;
			case CADASTRAR_FORNECEDOR: janela.cadastrarFornecedor(); break;
			case CADASTRAR_FUNCAO: janela.cadastrarFuncao(); break;
			case CADASTRAR_GARRAFA: janela.cadastrarGarrafa(); break;
			case CADASTRAR_INOCULACAO: janela.cadastrarInoculacao(); break;
			case CADASTRAR_INSUMO: janela.cadastrarInsumo(); break;
			case CADASTRAR_INSUMO_INOCULACAO: janela.cadastrarInsumoInoculacao(); break;			
			case CADASTRAR_INVESTIMENTO: janela.cadastrarInvestimento(); break;
			case CADASTRAR_LOCAL: janela.cadastrarLocal(); break;
			case CADASTRAR_LOTE: janela.cadastrarLote(); break;
			case CADASTRAR_MATURACAO: janela.cadastrarMaturacao(); break;
			case CADASTRAR_MEDICAO: janela.cadastrarMedicao(); break;
			case CADASTRAR_PESSOA: janela.cadastrarPessoa(); break;
			case CADASTRAR_PESSOA_FISICA: janela.cadastrarPessoaFisica(); break;
			case CADASTRAR_PESSOA_JURIDICA: janela.cadastrarPessoaJuridica(); break;
			case CADASTRAR_RECEITA: janela.cadastrarReceita(); break;
			case CADASTRAR_RESONSAVEL_INOCULACAO: janela.cadastrarResponsavelInoculacao(); break;
			case CADASTRAR_SANITIZACAO: janela.cadastrarSanitizacao(); break;
			case CADASTRAR_SOCIO: janela.cadastrarSocio(); break;
			case CADASTRAR_TRANSFERENCIA: janela.cadastrarTransferencia(); break;
			case CADASTRAR_VENDA: janela.cadastrarVenda(); break;
			case EDITAR_CARGO: janela.editar("cargo"); break;
			case EDITAR_COMPRA_INSUMO: janela.editar("compra_insumo"); break;
			case EDITAR_DEPARTAMENTO: janela.editar("departamento"); break;
			case EDITAR_ENGARRAFAMENTO: janela.editar("engarrafamento"); break;
			case EDITAR_FERMENTACAO: janela.editar("fermentacao"); break;
			case EDITAR_FORNECEDOR: janela.editar("fornecedor"); break;
			case EDITAR_FUNCAO: janela.editar("funcao"); break;
			case EDITAR_GARRAFA: janela.editar("garrafa"); break;
			case EDITAR_INOCULACAO: janela.editar("inoculacao"); break;
			case EDITAR_INSUMO: janela.editar("insumo"); break;
			case EDITAR_INSUMO_INOCULACAO: janela.editar("insumo_inoculacao"); break;			
			case EDITAR_INVESTIDOR: janela.editar("investidor"); break;
			case EDITAR_INVESTIMENTO: janela.editar("investimento"); break;
			case EDITAR_LOCAL: janela.editar("local"); break;
			case EDITAR_LOTE: janela.editar("lote"); break;
			case EDITAR_MATURACAO: janela.editar("maturacao"); break;
			case EDITAR_MEDICAO: janela.editar("medicao"); break;
			case EDITAR_PESSOA: janela.editar("pessoa"); break;
			case EDITAR_PESSOA_FISICA: janela.editar("pessoa_fisica"); break;
			case EDITAR_PESSOA_JURIDICA: janela.editar("pessoa_juridica"); break;
			case EDITAR_RECEITA: janela.editar("receita"); break;
			case EDITAR_RESONSAVEL_INOCULACAO: janela.editar("responsavel_inoculacao"); break;
			case EDITAR_SANITIZACAO: janela.editar("sanitizacao"); break;
			case EDITAR_SOCIO: janela.editar("socio"); break;
			case EDITAR_TRANSFERENCIA: janela.editar("transferencia"); break;
			case EDITAR_VENDA: janela.editar("venda"); break;
			case VIEW_CAIXAATUAL: janela.visualizar("caixaAtual"); break;
			case VIEW_CUSTOPORLOTE: janela.visualizar("custoPorLote"); break;
			case VIEW_DESPESASPORDIA: janela.visualizar("despesasPorDia"); break;	
			case VIEW_FORNECEDORMAISBARATO: janela.visualizar("fornecedorMaisBarato"); break;
			case VIEW_GARRAFASDISPONIVEISVENDA: janela.visualizar("garrafasDisponiveisVenda"); break;
			case VIEW_INSUMOSUTILIZADOSPORLOTE: janela.visualizar("insumosUtilizadosPorLote"); break;
			case VIEW_LUCRO: janela.visualizar("lucro"); break;	
			case VIEW_LUCROPORLOTE: janela.visualizar("lucroPorLote"); break;
			case VIEW_MAIORINVESTIDOR: janela.visualizar("maiorInvestidor"); break;
			case VIEW_MATERIAPRIMADISPONIVEL: janela.visualizar("materiaPrimaDisponivel"); break;	
			case VIEW_MEDIADENSIDADEPHLOTE: janela.visualizar("mediaDensidadePhLote"); break;
			case VIEW_RECEITAMAIORTEMPOFERMENTACAO: janela.visualizar("receitaMaiorTempoFermentacao"); break;
			case VIEW_RECEITAMENORTEMPOFERMENTACAO: janela.visualizar("receitaMenorTempoFermentacao"); break;
			case VIEW_SOCIOMAISATIVONAPRODUCAO: janela.visualizar("socioMaisAtivoNaProducao"); break;
			case VIEW_VENDAMAISLUCRATIVA: janela.visualizar("vendaMaisLucrativa"); break;
			case VIEW_VENDASGARRAFAPORDIA: janela.visualizar("vendasGarrafaPorDia"); break;
			case VISUALIZAR_CARGO: janela.visualizar("cargo"); break;
			case VISUALIZAR_COMPRA_INSUMO: janela.visualizar("compra_insumo"); break;
			case VISUALIZAR_DEPARTAMENTO: janela.visualizar("departamento"); break;
			case VISUALIZAR_ENGARRAFAMENTO: janela.visualizar("engarrafamento"); break;
			case VISUALIZAR_FERMENTACAO: janela.visualizar("fermentacao"); break;
			case VISUALIZAR_FORNECEDOR: janela.visualizar("fornecedor"); break;
			case VISUALIZAR_FUNCAO: janela.visualizar("funcao"); break;
			case VISUALIZAR_GARRAFA: janela.visualizar("garrafa"); break;
			case VISUALIZAR_INOCULACAO: janela.visualizar("inoculacao"); break;
			case VISUALIZAR_INSUMO: janela.visualizar("insumo"); break;
			case VISUALIZAR_INSUMO_INOCULACAO: janela.visualizar("insumo_inoculacao"); break;			
			case VISUALIZAR_INVESTIDOR: janela.visualizar("investidor"); break;
			case VISUALIZAR_INVESTIMENTO: janela.visualizar("investimento"); break;
			case VISUALIZAR_LOCAL: janela.visualizar("local"); break;
			case VISUALIZAR_LOTE: janela.visualizar("lote"); break;
			case VISUALIZAR_MATURACAO: janela.visualizar("maturacao"); break;
			case VISUALIZAR_MEDICAO: janela.visualizar("medicao"); break;
			case VISUALIZAR_PESSOA: janela.visualizar("pessoa"); break;
			case VISUALIZAR_PESSOA_FISICA: janela.visualizar("pessoa_fisica"); break;
			case VISUALIZAR_PESSOA_JURIDICA: janela.visualizar("pessoa_juridica"); break;
			case VISUALIZAR_RECEITA: janela.visualizar("receita"); break;
			case VISUALIZAR_RESONSAVEL_INOCULACAO: janela.visualizar("responsavel_inoculacao"); break;
			case VISUALIZAR_SANITIZACAO: janela.visualizar("sanitizacao"); break;
			case VISUALIZAR_SOCIO: janela.visualizar("socio"); break;
			case VISUALIZAR_TRANSFERENCIA: janela.visualizar("transferencia"); break;
			case VISUALIZAR_VENDA: janela.visualizar("venda"); break;
			case EXECUTAR_PESQUISA: janela.executarPesquisa(); break;
			case EXECUTAR_UPDATE: janela.executarUpdate(); break;			
		}
	}
}

