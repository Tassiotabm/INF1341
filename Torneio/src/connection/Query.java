package connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
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
	private boolean checkQuery(){	//Checkar se um dos campos do formulário está vazio.
		for(JTextField v: v1){
			if(v.getText().length() == 0){
				System.out.println("Preencha corretamente o formulário.");
				return false;
			}
		}	
		return true;
	}
	public String [] getTorneios(){
		String ret [] = {"vaca","mula"};
		
		return ret;
	}
	
	public void sendModalidade(){
		if(checkQuery() == false)
			return;
		PreparedStatement statement = con.getVetordeStatement().get(0);
		try {
			statement.setInt(1,0);	//A Geração do IDincrementado é feito por Trigger!
			statement.setString(2,v1.get(0).getText());
			statement.setInt(3,Integer.parseInt(v1.get(1).getText()));
			statement.setString(4,v1.get(2).getText());
			statement.setQueryTimeout(30);
			statement.executeUpdate();
			System.out.println("Insert feito com sucesso.");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("Erro na criação da Modalidade.");
		}
	}
	public void sendTornament(){
		if(checkQuery() == false)
			return;
		//Agora vamos mandar um query pro servidor
		PreparedStatement statement = con.getVetordeStatement().get(1);
			try {
				statement.setInt(1,0); //A Geração do TorneioID é feito por Trigger!
				statement.setInt(2,Integer.parseInt(v1.get(2).getText()));
				statement.setString(3,v1.get(0).getText());
				statement.setInt(4,Integer.parseInt(v1.get(1).getText()));
				statement.setQueryTimeout(30);
				statement.executeUpdate();
				System.out.println("Insert feito com sucesso.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				System.out.println("Erro na criação do Torneio.");
			}
	}
	public void sendPlayer1(){
		//insert into PARTICIPANTE values (?,?,?,?)
		System.out.println("entra");
		PreparedStatement statement = con.getVetordeStatement().get(3);
		
		PreparedStatement statement_iscrito = con.getVetordeStatement().get(4); //Conection 4
		//Terceiro campo é um textfield com as provas!
		List<String> queryList = Arrays.asList(v1.get(3).getText().split(","));
		
		try {
			statement.setInt(1,0); //Como estamos chamando o valro nao importa
			statement.setString(1,v1.get(0).getText()); //A Geração do TorneioID é feito por Trigger
			statement.setString(2,v1.get(1).getText());
			statement.setString(3,v1.get(2).getText()); //FUCKING DATA
			statement.executeUpdate();
			System.out.println("Query feito com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro na inscricao do participante na tabela PARTICIPANTE");
		} //Como estamos chamando o valro nao importa
		
		try {
			for( String s : queryList){
				statement_iscrito.setString(1,s);
				statement_iscrito.setQueryTimeout(30);
				statement_iscrito.executeUpdate();
				System.out.println("Query feito com sucesso.");
				System.out.println(s);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("Erro na criação do Competidor na tabela INCRITO.");
		}
	}
	public void sendQuery1(){
		if(checkQuery() == false)
			return;
		//Agora vamos mandar um query pro servidor
		PreparedStatement statement = con.getVetordeStatement().get(3);
			try {
				statement.setString(1,v1.get(0).getText()); //A Geração do TorneioID é feito por Trigger!
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
				statement.setString(1,v1.get(0).getText()); //A Geração do TorneioID é feito por Trigger!
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
				statement.setString(1,v1.get(0).getText()); //A Geração do TorneioID é feito por Trigger!
				statement.setQueryTimeout(30);
				statement.executeUpdate();
				System.out.println("Query feito com sucesso.");
			} catch (SQLException e1) {
				System.out.println("Erro ao enviar o Query3.");
			}
	}
}
