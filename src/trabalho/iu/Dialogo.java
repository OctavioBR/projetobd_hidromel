package trabalho.iu;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class Dialogo extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	public Dialogo (JanelaPrincipal f, JPanel p, String titulo)
	{	
		super (f, titulo, true);
		setContentPane(p);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void interaja()
	{
		setVisible(true);
	}
}