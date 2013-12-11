package trabalho.logica;

public class ExcecaoCampoEmBranco extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public ExcecaoCampoEmBranco () 
	{
		super("Preencha todos os campos obrigatórios.");
	}
	
	

}