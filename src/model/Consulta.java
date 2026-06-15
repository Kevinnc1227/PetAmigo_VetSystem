// Autor: Lucas
package model;

public class Consulta {

	private Data data;
	private Hora hora;
	private Animal animal;
	private Veterinario vet;
	private float valor;
	private Pagamento pagamento;

	public Consulta() {
		this.data = new Data();
		this.hora = new Hora();
		this.animal = new Animal();
		this.vet = new Veterinario();
		this.pagamento = new Pagamento();
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float v) {
		this.valor = v;
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Veterinario getVet() {
		return vet;
	}

	public void setVet(Veterinario vet) {
		this.vet = vet;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public void realizarAtendimento() {

		System.out.println("Atendimento realizado para o animal: " + this.animal);
	}

	public Veterinario getVeterinario() {
		return this.vet;
	}
}
