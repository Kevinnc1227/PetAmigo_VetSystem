
package model;
public class Prontuario {
	private String historico;
	private String observacoes;
	private float peso;
	private Animal animal;

	public Prontuario() {
		this.historico = "";
		this.observacoes = "";
		this.peso = -1;
		this.animal = null;
	}

	public Prontuario(String historico, String observacoes, Animal animal , float peso) {
		this.historico = historico;
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
		return "Prontuario [historico=" + historico + ", observacoes=" + observacoes
				+ ", animal=" + animal + " Peso=" + peso + "]";
	}

}