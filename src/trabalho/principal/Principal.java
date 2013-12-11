package trabalho.principal;

import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import trabalho.iu.JanelaPrincipal;
import trabalho.persistencia.BancoDeDados;

public class Principal
{
	public static void main(String[] args) throws SQLException 
	{
		BancoDeDados bd = null;
		JanelaPrincipal jp = null;
		try 
		{
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		        if ("Nimbus".equals(info.getName())) 
		        {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    bd = new BancoDeDados();
		    bd.conectar();
		    jp = new JanelaPrincipal(bd);	 
		} 
		catch (Exception e) {} 
		 
		jp.interaja();
				
//		gerenciador.desconectar();
	}	

}
