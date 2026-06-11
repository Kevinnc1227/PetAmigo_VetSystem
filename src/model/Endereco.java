package model;

public class Endereco {
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;

	public Endereco() {
		this.logradouro = "";
		this.numero = "";
		this.bairro = "";
		this.cidade = "";
	}

	public Endereco(String logadouro, String numero, String bairro, String cidade) {
		this.logradouro = logadouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
	}

	public String getlogradouro() {
		return this.logradouro;
	}

	public void setLogragouro(String logadouro) {
		this.logradouro = logadouro;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String toString() {
		return "Rua " + this.getlogradouro() + ", nº" + this.getNumero() + ", " + this.getBairro() + ", "
				+ this.getCidade();
	}
}
