package model;

public class Pagamento {
	private float valorTotal;
	private StatusPagamento status;

	public Pagamento() {

	}

	public Pagamento(float valorTotal, StatusPagamento status) {
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
		if (this.status == StatusPagamento.PENDENTE) {
			this.status = StatusPagamento.PAGO;
			return true;
		}
		return false;
	}

}
