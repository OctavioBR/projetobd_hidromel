package trabalho.iu;

import java.sql.SQLException;

import javax.swing.JFrame;

import trabalho.iu.menu.MenuBar;
import trabalho.iu.paineisCadastro.PainelCadastroCargo;
import trabalho.iu.paineisCadastro.PainelCadastroCompraInsumo;
import trabalho.iu.paineisCadastro.PainelCadastroDepartamento;
import trabalho.iu.paineisCadastro.PainelCadastroEngarrafamento;
import trabalho.iu.paineisCadastro.PainelCadastroFermentacao;
import trabalho.iu.paineisCadastro.PainelCadastroFornecedor;
import trabalho.iu.paineisCadastro.PainelCadastroFuncao;
import trabalho.iu.paineisCadastro.PainelCadastroGarrafa;
import trabalho.iu.paineisCadastro.PainelCadastroInoculacao;
import trabalho.iu.paineisCadastro.PainelCadastroInsumo;
import trabalho.iu.paineisCadastro.PainelCadastroInsumoInoculacao;
import trabalho.iu.paineisCadastro.PainelCadastroInvestimento;
import trabalho.iu.paineisCadastro.PainelCadastroLocal;
import trabalho.iu.paineisCadastro.PainelCadastroLote;
import trabalho.iu.paineisCadastro.PainelCadastroMaturacao;
import trabalho.iu.paineisCadastro.PainelCadastroMedicao;
import trabalho.iu.paineisCadastro.PainelCadastroPessoa;
import trabalho.iu.paineisCadastro.PainelCadastroPessoaFisica;
import trabalho.iu.paineisCadastro.PainelCadastroPessoaJuridica;
import trabalho.iu.paineisCadastro.PainelCadastroReceita;
import trabalho.iu.paineisCadastro.PainelCadastroResponsavelInoculacao;
import trabalho.iu.paineisCadastro.PainelCadastroSanitizacao;
import trabalho.iu.paineisCadastro.PainelCadastroSocio;
import trabalho.iu.paineisCadastro.PainelCadastroTransferencia;
import trabalho.iu.paineisCadastro.PainelCadastroVenda;
import trabalho.persistencia.BancoDeDados;

public class JanelaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private BancoDeDados bancoDeDados;
	
	public JanelaPrincipal(BancoDeDados gerenciador)
	{
		super("Hidromel");
		this.bancoDeDados = gerenciador;
		setLocationRelativeTo(null);
		setResizable(false);
		setJMenuBar(new MenuBar(this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		pack();
	}
	
	public void interaja()
	{
		setSize(300, 150);
		setVisible(true);
	}	
	
	public void cadastrarCargo()
	{
		PainelCadastroCargo painel = new PainelCadastroCargo(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Cargo");		
		dlg.interaja();
	}
	
	public void cadastrarCompraInsumo()
	{
		PainelCadastroCompraInsumo painel = new PainelCadastroCompraInsumo(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Compra de Insumo");		
		dlg.interaja();
	}
	
	public void cadastrarDepartamento()
	{
		PainelCadastroDepartamento painel = new PainelCadastroDepartamento(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Departamento");		
		dlg.interaja();
	}
	
	public void cadastrarEngarrafamento()
	{
		PainelCadastroEngarrafamento painel = new PainelCadastroEngarrafamento(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Engarrafamento");		
		dlg.interaja();
	}
	
	public void cadastrarFermentacao()
	{
		PainelCadastroFermentacao painel = new PainelCadastroFermentacao(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Fermentação");		
		dlg.interaja();
	}
	
	public void cadastrarFornecedor()
	{
		PainelCadastroFornecedor painel = new PainelCadastroFornecedor(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Fornecedor");		
		dlg.interaja();
	}
	
	public void cadastrarFuncao()
	{
		PainelCadastroFuncao painel = new PainelCadastroFuncao(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Função");		
		dlg.interaja();
	}
	
	public void cadastrarGarrafa()
	{
		PainelCadastroGarrafa painel = new PainelCadastroGarrafa(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Garrafa");		
		dlg.interaja();
	}

	public void cadastrarInoculacao()
	{
		PainelCadastroInoculacao painel = new PainelCadastroInoculacao(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Inoculação");		
		dlg.interaja();
	}
	
	public void cadastrarInsumo()
	{
		PainelCadastroInsumo painel = new PainelCadastroInsumo(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Insumo");		
		dlg.interaja();
	}
	
	public void cadastrarInsumoInoculacao()
	{
		PainelCadastroInsumoInoculacao painel = new PainelCadastroInsumoInoculacao(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Insumo na Inoculação");		
		dlg.interaja();
	}
	
	public void cadastrarInvestimento()
	{
		PainelCadastroInvestimento painel = new PainelCadastroInvestimento(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Investimento");		
		dlg.interaja();
	}
	
	public void cadastrarLocal()
	{
		PainelCadastroLocal painel = new PainelCadastroLocal(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Local");		
		dlg.interaja();
	}
	
	public void cadastrarLote()
	{
		PainelCadastroLote painel = new PainelCadastroLote(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Lote");		
		dlg.interaja();
	}
	
	public void cadastrarMaturacao()
	{
		PainelCadastroMaturacao painel = new PainelCadastroMaturacao(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Maturação");		
		dlg.interaja();
	}
	
	public void cadastrarMedicao()
	{
		PainelCadastroMedicao painel = new PainelCadastroMedicao(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Medição");		
		dlg.interaja();
	}
	
	public void cadastrarPessoa()
	{
		PainelCadastroPessoa painel = new PainelCadastroPessoa(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Pessoa");		
		dlg.interaja();
	}
	
	public void cadastrarPessoaFisica()
	{
		PainelCadastroPessoaFisica painel = new PainelCadastroPessoaFisica(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Pessoa Física");		
		dlg.interaja();
	}
	
	public void cadastrarPessoaJuridica()
	{
		PainelCadastroPessoaJuridica painel = new PainelCadastroPessoaJuridica(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Pessoa Jurídica");		
		dlg.interaja();
	}
	
	public void cadastrarReceita()
	{
		PainelCadastroReceita painel = new PainelCadastroReceita(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Receita");		
		dlg.interaja();
	}
	
	public void cadastrarResponsavelInoculacao()
	{
		PainelCadastroResponsavelInoculacao painel = new PainelCadastroResponsavelInoculacao(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Responsável na Inoculação");		
		dlg.interaja();
	}
	
	public void cadastrarSocio()
	{
		PainelCadastroSocio painel = new PainelCadastroSocio(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Sócio");		
		dlg.interaja();
	}
	
	public void cadastrarSanitizacao()
	{
		PainelCadastroSanitizacao painel = new PainelCadastroSanitizacao(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Sanitização");		
		dlg.interaja();
	}
	
	public void cadastrarTransferencia()
	{
		PainelCadastroTransferencia painel = new PainelCadastroTransferencia(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Transferência");		
		dlg.interaja();
	}
	
	public void cadastrarVenda()
	{
		PainelCadastroVenda painel = new PainelCadastroVenda(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Cadastrar Venda");		
		dlg.interaja();
	}
	
	public void visualizar(String nomeTabela)
	{
		String sql = "SELECT * FROM " + nomeTabela + ";";
		
		try {
			String[][] resultado = bancoDeDados.executarPesquisa(sql);		
			PainelTabelaPaginada painelTabela = new PainelTabelaPaginada();
			painelTabela.atualizaTabela(resultado);
			Dialogo dlg = new Dialogo (this, painelTabela, "Visualização de dados");		
			dlg.interaja();
		} 
		catch (SQLException e) {}
	}
	
	public void editar(String nomeTabela)
	{	
		DialogoEdicaoDados dlg = new DialogoEdicaoDados(nomeTabela, bancoDeDados);		
		dlg.setVisible(true);	
	}
	
	public void executarPesquisa()
	{	
		PainelExecutarQuery painel = new PainelExecutarQuery(bancoDeDados);
		PainelRelatorioDePesquisa painelPesquisa = new PainelRelatorioDePesquisa(painel);
		painel.setParent(painelPesquisa);
		Dialogo dlg = new Dialogo (this, painelPesquisa, "Execução de Pesquisa");		
		dlg.interaja();
	}
	
	public void executarUpdate()
	{
		PainelExecutarQuery painel = new PainelExecutarQuery(bancoDeDados);
		Dialogo dlg = new Dialogo (this, painel, "Execução de Ação de Edição");		
		dlg.interaja();
	}
}


