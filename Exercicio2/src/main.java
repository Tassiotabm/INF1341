
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.CollationElementIterator;
import java.util.Scanner;
import java.util.Vector;

public class main {

		static int Decisao;
		static Vector <PreparedStatement> vetordeStatement = new Vector<PreparedStatement>();
		static Scanner sc = new Scanner(System.in);
		static String st;
		
	  public static void main(String[] args) throws ClassNotFoundException, SQLException
	  {
	    // load the sqlite-JDBC driver using the current class loader
	    Class.forName("org.sqlite.JDBC");

 	    Connection connection = null;
	      // create a database connection

	      connection = DriverManager.getConnection("jdbc:sqlite:C:/sqlite-tools-win32-x86-3170000/bd3");
	    
	     preparaStatment(connection);

	  	System.out.println("=================================");	    
 	    System.out.println("Bem-vindo ao seu banco de SQLITE");
 	    System.out.println("Por-favor escolher uma das opções abaixo:");

	    
	    while(true){
	 	    System.out.println("=================================");
	    	System.out.println("1-Consultar a lista de automóveis");
	 	    System.out.println("2-Consultar dados de um veículo");
	 	    System.out.println("3-Cadastrar novo veículo");
	 	    System.out.println("4-Atualizar preço de tabela");
	 	    System.out.println("5-Remover veículo");
	 	    System.out.println("6-Execução  de  comandos  SQL");
	 	    System.out.println("7-Sair do programa");
	 	    System.out.println("=================================");

	 	    Decisao = sc.nextInt();
	 	    ResultSet rs = null;

	 	      
	 	    try
	 	    {
	 	      // create a database connection

	 	      if(Decisao == 1){
	 		      //PreparedStatement statement = connection.prepareStatement("select * from Automoveis");
	 	    	  PreparedStatement statement = vetordeStatement.get(Decisao-1);
	 	    	  
	 		      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	 		      rs = statement.executeQuery();
	 		      
		 	      while(rs.next())
		 	      {
		 	        // read the result set
		 	    	System.out.println("========================================");
		 	        System.out.println("Codigo = " + rs.getInt("Codigo"));
		 	        System.out.println("Ano = " + rs.getInt("Ano"));
		 	        System.out.println("Fabricante = " + rs.getString("Fabricante"));
		 	        System.out.println("Modelo = " + rs.getString("Modelo"));
		 	        System.out.println("Preço_Tabela = " + rs.getLong("Preco_Tabela"));
		 	        System.out.println("Pais = " + rs.getString("Pais"));
		 	      }
	 	      }
	 	    	  
	 	      else if(Decisao == 2){
	 	    	  System.out.println("Por favor, digite o modelo do veiculo:");
	 	    	  st = sc.next();
	 	    	  System.out.println(st);
	 		      //PreparedStatement statement = connection.prepareStatement("select * from Automoveis WHERE Modelo=?");
	 		      
	 	    	  PreparedStatement statement = vetordeStatement.get(Decisao-1);
	 	    	  statement.setString(1,st);
	 		      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	 	    	  rs = statement.executeQuery();
	 	    	  
		 	      while(rs.next())
		 	      {
		 	        // read the result set
		 	    	System.out.println("========================================");
		 	        System.out.println("Codigo = " + rs.getInt("Codigo"));
		 	        System.out.println("Ano = " + rs.getInt("Ano"));
		 	        System.out.println("Fabricante = " + rs.getString("Fabricante"));
		 	        System.out.println("Modelo = " + rs.getString("Modelo"));
		 	        System.out.println("Preço_Tabela = " + rs.getLong("Preco_Tabela"));
		 	        System.out.println("Pais = " + rs.getString("Pais"));
		 	    	System.out.println("========================================");
		 	      }
	 	      }
	 	      else if(Decisao == 3){

	 	    	  System.out.println("Digite os dados do Veiculo:\nNa seguinte Ordem:");
	 	    	  System.out.println("Codigo/Fabricante/Ano/Modelo/Preço_Tabela/Pais");
	 	    	  PreparedStatement statement = vetordeStatement.get(Decisao-1);
	 	    	  
	 	    	  int j,i = 0;
	 	    	  while(i<6){
	 	    		  if(i==4 || i == 0 || i == 2){
		 	    		  j = sc.nextInt();
	 	    			  System.out.println("Adicionado ao statement: "+ j);
			 	    	  statement.setInt(i+1,j);
	 	    		  }
	 	    		  else{
	 	    			  st = sc.next();
		 	    		  System.out.println("Adicionado ao statement: "+st);
			 	    	  statement.setString(i+1,st);
	 	    		  }
	 	    		  i++;
	 	    	  }
	 	    	  
	 		      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	 	    	  statement.executeUpdate();
	 	      }
	 	    	  
	 	      else if(Decisao == 4){

	 	    	  int codigo,newPrice;
	 	    	  System.out.println("Digite o codigo do Veiculo:");
	 	    	  PreparedStatement statement = vetordeStatement.get(Decisao-1);
	 	    	  codigo = sc.nextInt();
	 	    	  System.out.println("Digite o novo valor do veiculo");
	 	    	  newPrice = sc.nextInt();
	 	    	  
			 	  statement.setInt(1,newPrice);
			 	  statement.setInt(2,codigo);

	 	    	  
	 		      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	 	    	  statement.executeUpdate();
	 	      }
	 	      
	 	      else if(Decisao == 5){
	 	    	  
	 	    	  String codigo;
	 	    	  System.out.println("Digite o codigo do Veiculo que deseja deletar:");
	 	    	  PreparedStatement statement = vetordeStatement.get(Decisao-1);
	 	    	  codigo = sc.next();	 	    	  
			 	  statement.setString(1,codigo);
	 		      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	 	    	  statement.executeUpdate();
	 	      }
	 	      else if(Decisao == 6){
	 	    	  	System.out.println("Digite o comando SQL");
	 	    	  	String comando = sc.next();
	 	    	  	comando += sc.nextLine();	
		 	    	System.out.println("Comando a ser execudado:"+comando);
		 	    	String colName = null;
		 	    	String colType = null;
			 		PreparedStatement statement = connection.prepareStatement(comando);
			 		if(statement.execute() == true){
			 			//ResultSet rs = stmt.executeQuery(selectstring);
			 			ResultSetMetaData rsmtadta;
			            rs = statement.executeQuery();
			            //rs = statement.getResultSet();
		                rsmtadta = rs.getMetaData();      // Cria um objeto ResultSetMetaData
		                int colCount = rsmtadta.getColumnCount();  // Descobre o número de colunas resultantes da consulta
		                
		                int j = rsmtadta.getColumnCount();
			 
				 	      while(rs.next())
				 	      {
					 	    	System.out.println("========================================");
					 	    	for(int n=0;n<j;n++)
						 	        System.out.println( rsmtadta.getColumnName(n+1) + " = " + rs.getString(rsmtadta.getColumnName(n+1)));
					 	    	System.out.println("========================================");
				 	      }
			 		}
			 		
			 		// REZA PRA FUNCIONAR
			 		
		 	    }
	 	      else{
	 	         try
	 	        {
	 	          if(connection != null)

	 	        	  connection.close();
	 	          break;
	 	        }
	 	        catch(SQLException e)
	 	        {
	 	          // connection close failed.
	 	          System.err.println(e);
	 	        }
	 	    	  
	 	      }
		 	    System.out.println("=================================");


	 	    }
	 	    catch(SQLException e)
	 	    {
	 	      // if the error message is "out of memory", 
	 	      // it probably means no database file is found
	 	      System.err.println(e.getMessage());
	 	    }

	    }
	   System.out.println("Fim do acesso");
	  }
	  public static void preparaStatment(Connection connection) throws SQLException{
 		  vetordeStatement.add(connection.prepareStatement("select * from Automoveis"));
 		  vetordeStatement.add(connection.prepareStatement("select * from Automoveis WHERE Modelo=?"));
 		  vetordeStatement.add(connection.prepareStatement("insert into automoveis (codigo, fabricante, modelo, ano, preco_tabela, pais) values (?,?,?,?,?,?)"));	 		  
 		  vetordeStatement.add(connection.prepareStatement("update Automoveis set preco_tabela=? where codigo=?"));
 		  vetordeStatement.add(connection.prepareStatement("delete from Automoveis WHERE codigo=?"));

	  }
}
