// Autor: Kevin
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

	public String getlogradouro() {
		return this.logradouro;
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
		return "Rua " + this.getlogradouro() + ", nº" + this.getNumero() + ", " + this.getBairro() + ", "
			+ this.getCidade();
	}

	public static Endereco parse(String str) {
		if (str == null || str.trim().isEmpty()) {
			return new Endereco();
		}
		String logradouro = "", numero = "", bairro = "", cidade = "";
		try {
			String[] parts = str.split(", ");
			if (parts.length >= 1) {
				logradouro = parts[0];
				if (logradouro.startsWith("Rua ")) {
					logradouro = logradouro.substring(4);
				}
			}
			if (parts.length >= 2) {
				numero = parts[1];
				if (numero.startsWith("nº")) {
					numero = numero.substring(2);
				} else if (numero.startsWith("n°")) {
					numero = numero.substring(2);
				}
			}
			if (parts.length >= 3) {
				bairro = parts[2];
			}
			if (parts.length >= 4) {
				cidade = parts[3];
			}
		} catch (Exception e) {
			logradouro = str;
		}
		return new Endereco(logradouro, numero, bairro, cidade);
	}
}
