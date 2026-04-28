public class pagamento {
	private float valorTotal;
	private StatusPagamento status;

	public pagamento() {

	}

	public pagamento(float valorTotal, StatusPagamento status) {
		this.valorTotal = valorTotal;
		this.status = status;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}

	public boolean confirmarPagamento() {
		return false;
	}

}
