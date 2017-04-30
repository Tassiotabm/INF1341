package connection;

import java.sql.*;
import java.util.Vector;
     
public final class Connect {

	private static Connection con;
	private static Vector <PreparedStatement> vetordeStatement = new Vector<PreparedStatement>();
	
	public Connect(){
		  
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
		//String url = "jdbc:oracle:thin:@139.82.3.27:1521:orcl"; NAO APAGAR ESSA PORRA É DA PUC
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: "); 
			System.err.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(url,"PUC", "PUC");
			//con = DriverManager.getConnection(url,"felipe", "felipe");
			//con = DriverManager.getConnection(url,"BD32017_1321881", "BD32017_1321881");  NAO APAGAR ESSA PORRA É DA PUC
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
		  vetordeStatement.add(connection.prepareStatement("select ID_PARTICIPANTE FROM ALOCADO WHERE ID_MODALIDADE = ? and ID_TORNEIO"));
		  vetordeStatement.add(connection.prepareStatement("UPDATE ALOCADO SET resultado = ? WHERE ID_PARTICIPANTE = ? and ID_MODALIDADE = ? and ID_SERIE = ?"));
		  vetordeStatement.add(connection.prepareStatement("SELECT NOME,ID_PARTICIPANTE FROM PARTICIPANTE WHERE ID_PARTICIPANTE IN( SELECT ID_PARTICIPANTE FROM ALOCADO WHERE ID_MODALIDADE = ? and ID_SERIE = ?)"));
		  vetordeStatement.add(connection.prepareStatement("UPDATE SERIE SET DATAINI = ? WHERE ID_SERIE = ? and ID_MODALIDADE = ?"));
		  vetordeStatement.add(connection.prepareStatement("UPDATE SERIE SET STATUS = 'executada' WHERE ID_SERIE = ? and ID_MODALIDADE = ?"));
		  vetordeStatement.add(connection.prepareStatement("SELECT aloc.NUMERO_PART,aloc.RESULTADO,modal.NOME FROM ALOCADO aloc JOIN MODALIDADE modal ON modal.ID_MODALIDADE = aloc.ID_MODALIDADE WHERE aloc.ID_PARTICIPANTE = ?"));
	}
	public static Connection getCon() {
		return con;
	}
	public Vector<PreparedStatement> getVetordeStatement() {
		return vetordeStatement;
	}
	
}