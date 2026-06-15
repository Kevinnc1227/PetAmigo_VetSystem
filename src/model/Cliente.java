// Autor: Kevin
package model;

import java.util.ArrayList;

public class Cliente extends Pessoa {

	private String cpf;
	private ArrayList<Animal> pets;

	public Cliente() {
		super();
		this.cpf = "";
		this.pets = new ArrayList<Animal>();
	}

	public Cliente(String cpf) {
		super();
		this.cpf = cpf;
		this.pets = new ArrayList<Animal>();
	}

	public Cliente(String cpf, String nome, String email, String endereco, String telefone) {
		super(nome, email, endereco, telefone);
		this.cpf = cpf;
		this.pets = new ArrayList<Animal>();
	}

	public void cadastrarPet(Animal animal) {
		if (animal != null) {
			this.pets.add(animal);
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setPets(ArrayList<Animal> pets) {
		this.pets = pets;
	}

	public String toString() {
		return "Nome: " + this.getNome() + "\r\n" + "Email: " + this.getEmail() + "\r\n" + "CPF: " + this.getCpf()
				+ "\r\n" + "Telefone: " + this.getTelefone() + "\r\n" + "Endereco: "
				+ this.getEndereco();
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ArrayList<Animal> getPets() {
		return pets;
	}

}
