// Autor: Leonardo
package model;

public class Animal {
	private int id;
	private String nome;
	private TipoAnimal especie;
	private Prontuario prontuario;
	private Cliente cliente;

	public Animal() {
		this.cliente = new Cliente();
		this.prontuario = null;
	}

	public Animal(int id, String nome, TipoAnimal especie) {
		this.setId(id);
		this.setNome(nome);
		this.setEspecie(especie);
		this.cliente = new Cliente();
	}

	public Animal(String nome, TipoAnimal especie) {
		this.setNome(nome);
		this.setEspecie(especie);
		this.cliente = new Cliente();
		
	}

	public Animal(String nome, String especie) {
		this.setNome(nome);
		this.setEspecie(TipoAnimal.valueOf(especie.toUpperCase()));
		this.cliente = new Cliente();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoAnimal getEspecie() {
		return this.especie;
	}

	public void setEspecie(TipoAnimal especie) {
		this.especie = especie;
	}

	public Prontuario getProntuario() {
		return this.prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
	    return "Animal [id=" + id +
	           ", nome=" + nome +
	           ", especie=" + especie +
	           ", dono=" +
	           (cliente != null ? cliente.getNome() : "Sem dono") +
	           "]";
	}
}