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

	public Endereco(String logradouro, String numero, String bairro, String cidade) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
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
		return "Rua " + this.getLogradouro() + ", nº" + this.getNumero() + ", " + this.getBairro() + ", "
				+ this.getCidade();
	}
}
