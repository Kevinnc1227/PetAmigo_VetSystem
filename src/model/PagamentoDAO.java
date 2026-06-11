package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagamentoDAO implements OperacaoBD {
	private Pagamento pagamento;
	private BD bd;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String msg;
	private String sql;

	public PagamentoDAO() {
		this.bd = new BD();
		this.msg = "";
	}

	public boolean localizar() {
		this.sql = "SELECT * FROM pagamento WHERE valorTotal = ?";

		if (!bd.getConnection()) {
			this.msg = "Falha ao conectar ao banco de dados.";
			return false;
		}

		try {
			this.statement = bd.connection.prepareStatement(this.sql);
			this.statement.setFloat(1, pagamento.getValorTotal());

			this.resultSet = this.statement.executeQuery();

			if (this.resultSet.next()) {
				String statusStr = this.resultSet.getString("status");
				pagamento.setStatus(StatusPagamento.valueOf(statusStr));

				return true;
			} else {
				this.msg = "Pagamento não encontrado.";
				return false;
			}
		} catch (SQLException e) {
			this.msg = "Erro ao localizar pagamento: " + e.getMessage();
			return false;
		} finally {
			bd.close();

		}
	}

	public String atualizar(TipoOperacaoBD operacao) {
		if (!bd.getConnection()) {
			return "Falha ao conectar ao banco de dados.";
		}

		try {

			if (operacao == TipoOperacaoBD.INCLUSAO) {
				this.sql = "INSERT INTO pagamento (valorTotal, status) VALUES (?, ?)";
				this.statement = bd.connection.prepareStatement(this.sql);
				this.statement.setFloat(1, pagamento.getValorTotal());
				this.statement.setString(2, pagamento.getStatus().name());
				this.statement.executeUpdate();
				this.msg = "Pagamento incluído com sucesso!";
			}

			else if (operacao == TipoOperacaoBD.ALTERACAO) {
				this.sql = "UPDATE pagamento SET status = ? WHERE valorTotal = ?";
				this.statement = bd.connection.prepareStatement(this.sql);
				this.statement.setString(1, pagamento.getStatus().name());
				this.statement.setFloat(2, pagamento.getValorTotal());
				this.statement.executeUpdate();
				this.msg = "Pagamento alterado com sucesso!";
			}

			else if (operacao == TipoOperacaoBD.EXCLUSAO) {
				this.sql = "DELETE FROM pagamento WHERE valorTotal = ?";
				this.statement = bd.connection.prepareStatement(this.sql);
				this.statement.setFloat(1, pagamento.getValorTotal());
				this.statement.executeUpdate();
				this.msg = "Pagamento excluído com sucesso!";
			} else {
				this.msg = "Operação inválida.";
			}

		} catch (SQLException e) {
			this.msg = "Erro na operação " + operacao + ": " + e.getMessage();
		} finally {
			bd.close();
		}

		return this.msg;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public String getMsg() {
		return msg;
	}
}