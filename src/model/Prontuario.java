
package model;

public class Prontuario {
	private String historico;
	private String ultimaVacina;
	private String observacoes;
	private int idAnimal;

	public Prontuario() {
		this.historico = "";
		this.ultimaVacina = "";
		this.observacoes = "";
	}

	public Prontuario(String historico, String ultimaVacina, String observacoes, int idAnimal) {
		this.historico = historico;
		this.ultimaVacina = ultimaVacina;
		this.observacoes = observacoes;
		this.idAnimal = idAnimal;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getUltimaVacina() {
		return ultimaVacina;
	}

	public void setUltimaVacina(String ultimaVacina) {
		this.ultimaVacina = ultimaVacina;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public int getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public void adicionarEntrada(String texto) {
		historico += "\n" + texto;
	}

	@Override
	public String toString() {
		return "Prontuario [historico=" + historico + ", ultimaVacina=" + ultimaVacina + ", observacoes=" + observacoes
				+ ", idAnimal=" + idAnimal + "]";
	}

}