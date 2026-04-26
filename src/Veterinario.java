public class Veterinario extends Pessoa {
	private String crmv;
	private String especialidade;
	
	public Veterinario() {
		super();
		this.crmv = "";
		this.especialidade = "";
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
		return
		"Nome: " + this.getNome() + "\r\n" + 
		"Email: " + this.getEmail() + "\r\n" +
		"CRMV: " + this.getCrmv() + "\r\n" + 
		"Especialidade: " + this.getEspecialidade() + "\r\n" +
		"Telefone: " + this.getTelefone().toString() + "\r\n" +
		"Endereço: " + this.getEndereco().toString();
	}
}
