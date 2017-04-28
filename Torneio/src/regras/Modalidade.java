package regras;

import java.util.ArrayList;
import java.util.List;

public class Modalidade {

	private String modalidadeNome;
	private Double modalidadeID;
	private List<Torneio> listaTorneio;
	
	public Modalidade(Double id,String idName){
		listaTorneio = new ArrayList<Torneio>();
		this.modalidadeID = id;
		this.modalidadeNome = idName;
	}
	public String getModalidadeNome() {
		return modalidadeNome;
	}
	public void setModalidadeNome(String modalidadeNome) {
		this.modalidadeNome = modalidadeNome;
	}
	public Double getModalidadeID() {
		return modalidadeID;
	}
	public void setModalidadeID(Double modalidadeID) {
		this.modalidadeID = modalidadeID;
	}
	public List<Torneio> getListaTorneio() {
		return listaTorneio;
	}
	public void setListaTorneio(List<Torneio> t){
		listaTorneio = t;
		return;
	}
	public boolean checkTorneioName(String newName){
		
		for(Torneio t: listaTorneio){
			if(t.getNome().equals(newName))
				return true;
		}
		return false;
	}
}
