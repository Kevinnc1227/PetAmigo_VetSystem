package model;

public class Telefone {
	private String ddd;
	private String numero;

	public Telefone() {
		this.ddd = "";
		this.numero = "";
	}

	public Telefone(String ddd, String numero) {
		this.ddd = ddd;
		this.numero = numero;
	}

	public String getDdd() {
		return this.ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return this.numero;

	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String toString() {
		return this.getDdd() + " " + this.getNumero();
	}

	public static Telefone parse(String str) {
		if (str == null || str.trim().isEmpty()) {
			return new Telefone();
		}
		String ddd = "", numero = "";
		try {
			String[] parts = str.trim().split(" ", 2);
			if (parts.length >= 1) {
				ddd = parts[0];
			}
			if (parts.length >= 2) {
				numero = parts[1];
			}
		} catch (Exception e) {
			numero = str;
		}
		return new Telefone(ddd, numero);
	}
}
