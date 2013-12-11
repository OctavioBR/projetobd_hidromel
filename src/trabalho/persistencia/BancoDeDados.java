package trabalho.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDados 
{	
	private static Connection conexao;
	private static Statement statement;
	
	public static String dataDoBancoParaJava(String dataBanco)
	{
		if (dataBanco == null || dataBanco.trim().length() == 0)
			return "";
		
		String[] dados = dataBanco.split("-");
		String data = dados[2] + "/" + dados[1] + "/" + dados[0];		
		return data;
	}
	
	public static String dataDeJavaParaOBanco(String dataJava)
	{	
		if (dataJava.trim().length() == 4)
			return null;
		
		String[] dados = dataJava.split("/");
		String data = dados[2] + "-" + dados[1] + "-" + dados[0];		
		return data;
	}
	
	public boolean conectar() throws SQLException 
	{
		boolean conectado = true;
		if (conexao == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hidromel","root","root");
				statement = conexao.createStatement();			
			}
			catch (Exception e){
				if (conexao != null)
					conexao.close();
				conectado = false;
			}
		}
		
		return conectado;
	}
	
	public void desconectar()
	{
		try {
			conexao.close();
			conexao = null;
			statement = null;
		} 
		catch (SQLException e) {}
	}
	
	public String[][] executarPesquisa(String query) throws SQLException 
	{
		ResultSet result = null;
		List<String[]> linhas = new ArrayList<String[]>();
				
		result = statement.executeQuery(query);
		
		int nColunas = result.getMetaData().getColumnCount();
		String[] linha = new String[nColunas];
		for (int i = 1; i <= nColunas; i++)
			linha[i-1] = result.getMetaData().getColumnLabel(i);
		linhas.add(linha);
		
		while (result.next()) {
			linha = new String[nColunas];
			for (int i = 1; i <= nColunas; i++)
				linha[i-1] = result.getString(i);
			linhas.add(linha);
		}
		
		String[][] matriz = new String[linhas.size()][nColunas];
		for (int i = 0; i < linhas.size(); i++)
			matriz[i] = linhas.get(i);		
				
		return matriz;
	}
	
	public void executarUpdate(String sql) throws SQLException 
	{
		statement.executeUpdate(sql);
	}
}
