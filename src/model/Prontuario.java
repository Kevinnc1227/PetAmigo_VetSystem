// Autor: Leonardo
package model;
public class Prontuario {
	private String historico;
	private String ultimaVacina;
	private String observacoes;
	private float peso;
	private Animal animal;

	public Prontuario() {
		this.historico = "";
		this.ultimaVacina = "";
		this.observacoes = "";
		this.peso = -1;
		this.animal = new Animal();
	}

	public Prontuario(String historico, String ultimaVacina, String observacoes, Animal animal, float peso) {
		this.historico = historico;
		this.ultimaVacina = ultimaVacina;
		this.observacoes = observacoes;
		this.animal = animal;
		this.peso = peso;
	}

	public Prontuario(String historico, String observacoes, Animal animal, float peso) {
		this.historico = historico;
		this.ultimaVacina = "";
		this.observacoes = observacoes;
		this.animal = animal;
		this.peso = peso;
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

	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public void adicionarEntrada(String texto) {
		historico += "\n" + texto;
	}
	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Prontuario [historico=" + historico + ", ultimaVacina=" + ultimaVacina + ", observacoes=" + observacoes
				+ ", animal=" + animal + " Peso=" + peso + "]";
	}

}