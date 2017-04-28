package regras;

public class Torneio {

	private Double torneioID;
	private String nome;
	private int dificiculdade;
	private int marca;
	
	public Torneio(Double torneioID,String torneioName,int torneioDC,int nota){
		this.torneioID = torneioID;
		this.nome = torneioName;
		this.dificiculdade = torneioDC;
		this.marca = nota;
	}
	public Double getTorneioID() {
		return torneioID;
	}
	public void setTorneioID(Double torneioID) {
		this.torneioID = torneioID;
	}
	public int getMarca() {
		return marca;
	}
	public void setMarca(int marca) {
		this.marca = marca;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDificiculdade() {
		return dificiculdade;
	}
	public void setDificiculdade(int dificiculdade) {
		this.dificiculdade = dificiculdade;
	}
	
	
}
