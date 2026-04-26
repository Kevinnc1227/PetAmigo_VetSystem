public class Telefone {
	private String ddd;
	private String numero;
	
	public Telefone() {
		this.ddd = "";
		this.numero = "";
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
		return
		this.getDdd() + " " +
		this.getNumero();
	}
}
