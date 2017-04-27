package connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	public String [] getModalidades(){
		String ret [] = new String[10];
		ResultSet rs = null;
		int i = 0;
		
		PreparedStatement statement = con.getVetordeStatement().get(4);
		try {
			statement.setQueryTimeout(30);
			rs = statement.executeQuery();
	 	      while(rs.next())
	 	      {
	 	    	ret [i] = rs.getString("NOME");
	 	    	//System.out.println(rs.getInt("ID_MODALIDADE"));
	 	        //System.out.print(" "+rs.getString("NOME"));
	 	        i++;
	 	      }
			System.out.println("Query feito com sucesso.");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Erro na conexao da query do qpox.");
		}		
		return ret;
	}
	public String [] getTorneios(){

		String ret [] = new String[10];
		ResultSet rs = null;
		int i = 0;
		
		PreparedStatement statement = con.getVetordeStatement().get(3);
		try {
			statement.setQueryTimeout(30);
			rs = statement.executeQuery();
	 	      while(rs.next())
	 	      {
	 	    	ret [i] = rs.getString("NOME");
	 	    	System.out.println(rs.getInt("ID_TORNEIO"));
	 	        System.out.print(" "+rs.getString("NOME"));
	 	        i++;
	 	      }
			System.out.println("Query feito com sucesso.");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Erro na conexao da query do qpox.");
		}		
		return ret;
	}
	
	public void sendModalidade(){
		if(checkQuery() == false)
			return;
		PreparedStatement statement = con.getVetordeStatement().get(0);
		try {
			statement.setInt(1,0);	//A Gera��o do IDincrementado � feito por Trigger!
			statement.setString(2,v1.get(0).getText());
			statement.setInt(3,Integer.parseInt(v1.get(1).getText()));
			statement.setString(4,v1.get(2).getText());
			statement.setQueryTimeout(30);
			statement.executeUpdate();
			System.out.println("Insert feito com sucesso.");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("Erro na cria��o da Modalidade.");
		}
	}
	public void sendTornament(){
		if(checkQuery() == false)
			return;
		//Agora vamos mandar um query pro servidor
		PreparedStatement statement = con.getVetordeStatement().get(1);
			try {
				statement.setInt(1,0); //A Gera��o do TorneioID � feito por Trigger!
				statement.setInt(2,Integer.parseInt(v1.get(2).getText()));
				statement.setString(3,v1.get(0).getText());
				statement.setInt(4,Integer.parseInt(v1.get(1).getText()));
				statement.setQueryTimeout(30);
				statement.executeUpdate();
				System.out.println("Insert feito com sucesso.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				System.out.println("Erro na cria��o do Torneio.");
			}
	}
	public void sendPlayer(){
		Date dt = null;
		PreparedStatement statement = con.getVetordeStatement().get(2);
		try {
			dt = formataData(v1.get(1).getText());
		} catch (Exception e) {
			System.out.println("Erro na gera��o de Data");
		}
		System.out.println("Valor dentro da JTEXT"+v1.get(3).getText());
		//int temp = Integer.parseInt(v1.get(3).getText());
		double temp1 = Double.valueOf(v1.get(3).getText());
		try {
			statement.setDouble(1, temp1); 	// CPF
			statement.setString(2,v1.get(0).getText());				   	// NOME
			statement.setString(3,v1.get(2).getText());				   	// NACIONALIDADE
			statement.setDate(4,dt); 									//DATA
			statement.setQueryTimeout(10);
			System.out.println("ok");
			statement.executeUpdate();
			System.out.println("Query feito com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro na inscricao do participante na tabela PARTICIPANTE");
		} //Como estamos chamando o valro nao importa
	}
	public void sendChoiceModalidade(){
		
	}
	public void sendQuery1(){
		if(checkQuery() == false)
			return;
		//Agora vamos mandar um query pro servidor
		PreparedStatement statement = con.getVetordeStatement().get(3);
			try {
				statement.setString(1,v1.get(0).getText()); //A Gera��o do TorneioID � feito por Trigger!
				statement.setQueryTimeout(30);
				statement.executeUpdate();
				System.out.println("Query feito com sucesso.");
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
				System.out.println("Query feito com sucesso.");
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
				System.out.println("Query feito com sucesso.");
			} catch (SQLException e1) {
				System.out.println("Erro ao enviar o Query3.");
			}
	}
}