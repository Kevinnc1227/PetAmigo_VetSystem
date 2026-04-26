public class Endereco {
	private String logadouro;
	private String numero;
	private String bairro;
	private String cidade;
	
	public Endereco() {
		this.logadouro = "";
		this.numero = "";
		this.bairro = "";
		this.cidade = "";
	}
	
	public String getLogadouro() {
		return this.logadouro;
	}
	
	public void setLodagouro(String logadouro) {
		this.logadouro = logadouro;
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
		return
		"Rua " + this.getLogadouro() +
		", nº" + this.getNumero() + 
		", " + this.getBairro() + 
		", " + this.getCidade();
	}
}	
