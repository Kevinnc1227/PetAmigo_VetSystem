import java.util.ArrayList;

public class Cliente extends Pessoa {
	private String cpf;
	private ArrayList<Animal> pets;
	
	public Cliente() {
		super();
		this.cpf = "";
		this.pets = new ArrayList<Animal>();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ArrayList<Animal> getPets() {
		return pets;
	}

	public void setPets(ArrayList<Animal> pets) {
		this.pets = pets;
	}
	
	public String toString() {
		return
		"Nome: " + this.getNome() + "\r\n" + 
		"Email: " + this.getEmail() + "\r\n" +
		"CPF: " + this.getCpf() + "\r\n" + 
		"Telefone: " + this.getTelefone().toString() + "\r\n" +
		"Endereco: " + this.getEndereco().toString();
	}
}
