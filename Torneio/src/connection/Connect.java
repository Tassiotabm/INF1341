package connection;

import java.sql.*;
import java.util.Vector;
     
public final class Connect {

	private static Connection con;
	private static Vector <PreparedStatement> vetordeStatement = new Vector<PreparedStatement>();
	
	public Connect(){
		  
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: "); 
			System.err.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(url,"felipe", "felipe");
			System.out.println("Connection Sucess.");
		} catch(SQLException ex) {
			System.out.println("Não consegui conectar com servidor, tentar na PUC!");
			//System.err.println("SQLException: " + ex.getMessage());
		}
		try {
			Generate_Statements(con);
		} catch (SQLException e) {
			System.out.println("Erro na criação dos Statements.");
			//e.printStackTrace();
		}
	}
	private void Generate_Statements(Connection connection)throws SQLException{
		  vetordeStatement.add(connection.prepareStatement("insert into MODALIDADE (ID_MODALIDADE,NOME,DISTANCIA,SEXO) values (?,?,?,?)"));	 		  
		  vetordeStatement.add(connection.prepareStatement("insert into TORNEIO (ID_torneio,ID_modalidade,nome,dificuldade) values (?,?,?,?)"));	 		  
		  vetordeStatement.add(connection.prepareStatement("insert into PARTICIPANTE values (?,?,?,?,?)"));	 		  
		  vetordeStatement.add(connection.prepareStatement("select NOME,ID_TORNEIO,DIFICULDADE from TORNEIO WHERE ID_MODALIDADE = ?"));	 		  
		  vetordeStatement.add(connection.prepareStatement("select NOME,ID_MODALIDADE from MODALIDADE WHERE SEXO = ? "));
		  vetordeStatement.add(connection.prepareStatement("insert into INSCRITO values(?,?,?,?)"));
		  vetordeStatement.add(connection.prepareStatement("INSERT INTO ALOCADO values(?,?,?,?,?)"));

	}
	public static Connection getCon() {
		return con;
	}
	public Vector<PreparedStatement> getVetordeStatement() {
		return vetordeStatement;
	}
	
}