package model;

public class Veterinario extends Pessoa {
	private String crmv;
	private String especialidade;

	public Veterinario() {
		super();
		this.crmv = "";
		this.especialidade = "";
	}

	public Veterinario(String crmv, String especialidade) {
		super();
		this.crmv = crmv;
		this.especialidade = especialidade;
	}

	public Veterinario(String crmv, String nome, String email, String endereco, String telefone, String especialidade) {
		super(nome, email, endereco, telefone);
		this.crmv = crmv;
		this.especialidade = especialidade;
	}

	public String getCrmv() {
		return this.crmv;
	}

	public void setCrmv(String crmv) {
		this.crmv = crmv;
	}

	public String getEspecialidade() {
		return this.especialidade;
	}

	public void setEspecialidade(String esp) {
		this.especialidade = esp;
	}

	public String toString() {
		return "Nome: " + this.getNome() + "\r\n" + "Email: " + this.getEmail() + "\r\n" + "CRMV: " + this.getCrmv()
				+ "\r\n" + "Especialidade: " + this.getEspecialidade() + "\r\n" + "Telefone: "
				+ (this.getTelefone() != null ? this.getTelefone() : "") + "\r\n" + "Endereço: "
				+ (this.getEndereco() != null ? this.getEndereco() : "");
	}
}
