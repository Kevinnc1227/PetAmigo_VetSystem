
public class Prontuario {
	private String historico;
	private String ultimaVacina;
	private String observacoes;
	
	public Prontuario() {
	    this.historico = "";
	    this.ultimaVacina = "";
	    this.observacoes = "";
	}
	public Prontuario(String historico, String ultimaVacina, String observacoes) {
	    this.historico = historico;
	    this.ultimaVacina = ultimaVacina;
	    this.observacoes = observacoes;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getUltimaVacina() {
		return ultimaVacina;
	}

	public void setUltimaVacina(String ultimaVacina) {
		this.ultimaVacina = ultimaVacina;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	@Override
	public String toString() {
	    return "Prontuario:\n" +
	           "Historico = " + historico + "\n" +
	           "UltimaVacina = " + ultimaVacina + "\n" +
	           "Observacoes = " + observacoes;
	}
}
