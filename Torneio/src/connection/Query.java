package connection;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JTextField;

public class Query {

	private static Connect con = new Connect();
	private Vector<JTextField> v1 = new Vector<JTextField>();
	
	public Query(){
	}
	public Query(Vector<JTextField> v){
		v1 = v;
	}
	public Query(JTextField j){
		v1.add(j);
	}
	private boolean checkQuery(){	//Checkar se um dos campos do formul�rio est� vazio.
		for(JTextField v: v1){
			if(v.getText().length() == 0){
				System.out.println("Preencha corretamente o formul�rio.");
				return false;
			}
		}	
		return true;
	}
	
	public static Date formataData(String data) throws Exception { 
 		if (data == null || data.equals(""))
 			return null;
         java.sql.Date date = null;
         try {
             DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
             date = new java.sql.Date( ((java.util.Date)formatter.parse(data)).getTime() );
         } catch (ParseException e) {            
             throw e;
         }
         return date;
 	}
	public String [][] getModalidades(String gender){
		String ret [][] = new String[50][50];
		ResultSet rs = null;
		int i = 0;
		
		PreparedStatement statement = con.getVetordeStatement().get(4);
		try {
			statement.setQueryTimeout(30);
			
			/*
			 * 
			 * 
			 * 
			 */
			//System.out.println("genero na getModalides eh: "+ gender);
			statement.setString(1, gender);
			rs = statement.executeQuery();
	 	      while(rs.next())
	 	      {
	 	    	ret [0][i] = rs.getString("ID_MODALIDADE");
	 	    	ret [1][i] = rs.getString("NOME");
	 	        i++;
	 	      }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Erro na conexao da query do qpox.");
		}		
		return ret;
	}
	public String [][] getTorneios(String id_modalidade){

		String ret [][] = new String[30][30];
		ResultSet rs = null;
		int i = 0;
		System.out.println(id_modalidade);
		double id_mod = Double.valueOf(id_modalidade);
		
		PreparedStatement statement = con.getVetordeStatement().get(3);
		try {
			statement.setQueryTimeout(30);
			statement.setDouble(1,id_mod);
			rs = statement.executeQuery();
	 	      while(rs.next())
	 	      {
	 	    	ret [0][i] = rs.getString("NOME");
	 	    	ret [1][i] = rs.getString("ID_TORNEIO");
	 	    	ret [2][i] = rs.getString("DIFICULDADE");
	 	    	System.out.println(ret [0][i] + " " + ret [1][i] + " "+ ret [2][i]);
	 	        i++;
	 	      }
	 	    } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Erro na conexao da query do qpox.");
		}		
		return ret;
	}
	
	public String [][] getPlayers(String id_torneio,String id_modalidade){

		String ret [][] = new String[30][30];
		ResultSet rs = null;
		int i = 0;
		System.out.println(id_modalidade);
		double id_mod = Double.valueOf(id_modalidade);
		
		PreparedStatement statement = con.getVetordeStatement().get(3);
		try {
			statement.setQueryTimeout(30);
			statement.setDouble(1,id_mod);
			rs = statement.executeQuery();
	 	      while(rs.next())
	 	      {
	 	    	ret [0][i] = rs.getString("NOME");
	 	    	ret [1][i] = rs.getString("ID_TORNEIO");
	 	    	ret [2][i] = rs.getString("DIFICULDADE");
	 	    	System.out.println(ret [0][i] + " " + ret [1][i] + " "+ ret [2][i]);
	 	        i++;
	 	      }
	 	      
			System.out.println("comboBOX do Torneio feito com sucesso.");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Erro na conexao da query do qpox.");
		}		
		return ret;
	}
	
	
	public void sendModalidade(String genero){
		System.out.println("Entrei na modalidade");
		if(checkQuery() == false)
			return;
		PreparedStatement statement = con.getVetordeStatement().get(0);
		try {
			statement.setInt(1,0);	//A Gera��o do IDincrementado � feito por Trigger!
			statement.setString(2,v1.get(0).getText());
			statement.setInt(3,Integer.parseInt(v1.get(1).getText()));
			statement.setString(4,genero);
			statement.setQueryTimeout(30);
			statement.executeUpdate();
			System.out.println("Insert da Modalidade feito com sucesso.");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("Erro na cria��o da Modalidade.");
		}
	}
	public void sendTornament(String modalidade_id){
		if(checkQuery() == false)
			return;
		//Agora vamos mandar um query pro servidor
		Double numberModaliade_id = Double.valueOf(modalidade_id);
		PreparedStatement statement = con.getVetordeStatement().get(1);
			try {
				statement.setInt(1,0); //A Gera��o do TorneioID � feito por Trigger!
				statement.setDouble(2,numberModaliade_id);
				statement.setString(3,v1.get(0).getText());
				statement.setInt(4,Integer.parseInt(v1.get(1).getText()));
				statement.setQueryTimeout(30);
				statement.executeUpdate();
				System.out.println("Insert do torneio feito com sucesso.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				System.out.println("Erro na cria��o do Torneio.");
			}
	}
	public void sendPlayer(String genero){
		Date dt = null;
		PreparedStatement statement = con.getVetordeStatement().get(2);
		try {
			dt = formataData(v1.get(1).getText());
		} catch (Exception e) {
			System.out.println("Erro na gera��o de Data");
		}
		double temp1 = Double.valueOf(v1.get(3).getText());
		try {
			statement.setDouble(1, temp1); 	// CPF
			statement.setString(2,v1.get(0).getText());				   	// NOME
			statement.setString(3,v1.get(2).getText());				   	// NACIONALIDADE
			statement.setDate(4,dt); 									//DATA
			statement.setString(5,genero);								// sexo
			
			statement.setQueryTimeout(10);
			statement.executeUpdate();
			System.out.println("Insert do player com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro na inscricao do participante na tabela PARTICIPANTE");
		} //Como estamos chamando o valro nao importa
	}
	public void sendInscrito(Double participanteID,Double modalidadeID,Double torneioID,int marca){
		//Agora vamos mandar um query pro servidor
		System.out.println("=====================================");
		System.out.println("id do participante:"+participanteID+
				" id da modalidade:"+modalidadeID+
				" id do torneio:"+torneioID+
				" marca"+marca);
		System.out.println("=====================================");
		PreparedStatement statement = con.getVetordeStatement().get(5);
			try {
				statement.setDouble(1,participanteID); 	//Participante ID
				statement.setDouble(2,modalidadeID); 	//Modalidade ID
				statement.setDouble(3,torneioID); 	//Torneio ID
				statement.setInt(4,marca);		//Marca
				statement.setQueryTimeout(30);
				statement.executeUpdate();
				System.out.println("Insert de inscrito feito com sucesso.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Erro na cria��o do Inscrito.");
			}
	}
	public void sendResultado(int resultado,Double participanteID,Double modalidadeID,int serieID){
		Date dt = null;
		//dt = Calendar.getInstance().get;
		try {
			dt = formataData(v1.get(1).getText());
		} catch (Exception e) {
			System.out.println("Erro na gera��o de Data");
		}
		PreparedStatement statement = con.getVetordeStatement().get(8);
		try {
			statement.setDouble(1,resultado); 	//Participante ID
			statement.setDouble(2,participanteID); 	//Participante ID
			statement.setDouble(3,modalidadeID);
			statement.setDouble(4,serieID); 	//Modalidade ID
			statement.setQueryTimeout(30);
			statement.executeUpdate();
			System.out.println("Insert de inscrito feito com sucesso.");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Erro no update do resultado");
		}
	}
	
	public String [][] getParticipante(String modalidadeID, int serieID){
		String ret [][] = new String[50][50];
		ResultSet rs = null;
		int i = 0;
		
		PreparedStatement statement = con.getVetordeStatement().get(9);
		try {
			statement.setQueryTimeout(30);
			
			/*
			 * 
			 * 
			 * 
			 */
			//System.out.println("genero na getModalides eh: "+ gender);
			
			statement.setDouble(1, Double.valueOf(modalidadeID));
			statement.setInt(2, serieID);
			rs = statement.executeQuery();
	 	      while(rs.next())
	 	      {
	 	    	ret [0][i] = rs.getString("NOME");
	 	    	ret [1][i] = rs.getString("ID_PARTICIPANTE");
	 	        i++;
	 	      }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Erro na conexao da query do qpox.");
		}		
		return ret;
	}
	
	public void  aloca(){
		try {
			CallableStatement pstm = Connect.getCon().prepareCall("{call seleciona_eliminatorias}");
			System.out.println("Executando Update");
			pstm.executeUpdate();
			System.out.println("Executei Update");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Deu merda no aloca");
			e.printStackTrace();
		}
		
	}
	public void sendQuery1(){
		if(checkQuery() == false)
			return;
		//Agora vamos mandar um query pro servidor
		PreparedStatement statement = con.getVetordeStatement().get(0);
			try {
				statement.setString(1,v1.get(0).getText()); //A Gera��o do TorneioID � feito por Trigger!
				statement.setQueryTimeout(30);
				statement.executeUpdate();
				System.out.println("Query(1) feito com sucesso.");
			} catch (SQLException e1) {
				System.out.println("Erro ao enviar o Query1.");
			}
	}
	public void sendQuery2(){
		if(checkQuery() == false)
			return;
		//Agora vamos mandar um query pro servidor
		PreparedStatement statement = con.getVetordeStatement().get(4);
			try {
				statement.setString(1,v1.get(0).getText()); //A Gera��o do TorneioID � feito por Trigger!
				statement.setQueryTimeout(30);
				statement.executeUpdate();
				System.out.println("Query(2) feito com sucesso.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				System.out.println("Erro ao enviar o Query2.");
			}
	}
	public void sendQuery3(){
		if(checkQuery() == false)
			return;
		//Agora vamos mandar um query pro servidor
		PreparedStatement statement = con.getVetordeStatement().get(5);
			try {
				statement.setString(1,v1.get(0).getText()); //A Gera��o do TorneioID � feito por Trigger!
				statement.setQueryTimeout(30);
				statement.executeUpdate();
				System.out.println("Query(3) feito com sucesso.");
			} catch (SQLException e1) {
				System.out.println("Erro ao enviar o Query3.");
			}
	}
}
