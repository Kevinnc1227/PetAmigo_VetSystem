package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConsultaDAO {
	private Consulta consulta;
	private BD bd;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men;
	private String sql;

	public ConsultaDAO() {
		this.bd = new BD();
		this.men = "";
	}

	public boolean localizar() {

		this.sql = "SELECT * FROM consulta WHERE data_consulta = ? AND hora_consulta = ? AND animal_id = ?";

		if (!bd.getConnection()) {
			this.men = "Falha ao conectar ao banco de dados.";
			return false;
		}

		try {
			this.statement = bd.connection.prepareStatement(this.sql);
			this.statement.setString(1, consulta.getData().toString());
			this.statement.setString(2, consulta.getHora().toString());
			this.statement.setInt(3, consulta.getAnimal().getId());

			this.resultSet = this.statement.executeQuery();

			if (this.resultSet.next()) {
				// Se achou, você pode preencher o valor e os objetos relacionados se necessário
				consulta.setValor(this.resultSet.getFloat("valor"));
				return true;
			} else {
				this.men = "Consulta não encontrada.";
				return false;
			}
		} catch (SQLException e) {
			this.men = "Erro ao localizar consulta: " + e.getMessage();
			return false;
		} finally {
			bd.close();
		}
	}

	public String atualizar(int operacao) {
		if (!bd.getConnection()) {
			return "Falha ao conectar ao banco de dados.";
		}

		try {
			if (operacao == 1) {
				this.sql = "INSERT INTO consulta (data_consulta, hora_consulta, animal_id, veterinario_crmv, valor) VALUES (?, ?, ?, ?, ?)";
				this.statement = bd.connection.prepareStatement(this.sql);

				this.statement.setString(1, consulta.getData().toString());
				this.statement.setString(2, consulta.getHora().toString());
				this.statement.setInt(3, consulta.getAnimal().getId());
				this.statement.setString(4, consulta.getVet().getCrmv());
				this.statement.setFloat(5, consulta.getValor());

				this.statement.executeUpdate();
				this.men = "Consulta agendada com sucesso!";
			} else if (operacao == 2) {
				this.sql = "UPDATE consulta SET valor = ? WHERE data_consulta = ? AND hora_consulta = ? AND animal_id = ?";
				this.statement = bd.connection.prepareStatement(this.sql);

				this.statement.setFloat(1, consulta.getValor());
				this.statement.setString(2, consulta.getData().toString());
				this.statement.setString(3, consulta.getHora().toString());
				this.statement.setInt(4, consulta.getAnimal().getId());

				this.statement.executeUpdate();
				this.men = "Consulta alterada com sucesso!";
			} else if (operacao == 3) {
				this.sql = "DELETE FROM consulta WHERE data_consulta = ? AND hora_consulta = ? AND animal_id = ?";
				this.statement = bd.connection.prepareStatement(this.sql);

				this.statement.setString(1, consulta.getData().toString());
				this.statement.setString(2, consulta.getHora().toString());
				this.statement.setInt(3, consulta.getAnimal().getId());

				this.statement.executeUpdate();
				this.men = "Consulta cancelada/excluída com sucesso!";
			} else {
				this.men = "Operação inválida.";
			}

		} catch (SQLException e) {
			this.men = "Erro na operação " + operacao + ": " + e.getMessage();
		} finally {
			bd.close();
		}

		return this.men;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getMen() {
		return men;
	}

	public List<Consulta> listarConsultas() {
		// TODO Auto-generated method stub
		return null;
	}
}