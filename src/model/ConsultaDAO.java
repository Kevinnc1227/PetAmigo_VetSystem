package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConsultaDAO implements OperacaoBD {
	private Consulta consulta;
	private BD bd;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String msg;
	private String sql;

	public ConsultaDAO() {
		this.bd = new BD();
		this.msg = "";
	}

	public boolean localizar() {

		this.sql = "SELECT * FROM consulta WHERE data_consulta = ? AND hora_consulta = ? AND animal_id = ?";

		if (!bd.getConnection()) {
			this.msg = "Falha ao conectar ao banco de dados.";
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
				this.msg = "Consulta não encontrada.";
				return false;
			}
		} catch (SQLException e) {
			this.msg = "Erro ao localizar consulta: " + e.getMessage();
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
				this.sql = "INSERT INTO consulta (data_consulta, hora_consulta, animal_id, veterinario_crmv, valor) VALUES (?, ?, ?, ?, ?)";
				this.statement = bd.connection.prepareStatement(this.sql);

				this.statement.setString(1, consulta.getData().toString());
				this.statement.setString(2, consulta.getHora().toString());
				this.statement.setInt(3, consulta.getAnimal().getId());
				this.statement.setString(4, consulta.getVet().getCrmv());
				this.statement.setFloat(5, consulta.getValor());

				this.statement.executeUpdate();
				this.msg = "Consulta agendada com sucesso!";
			} else if (operacao == TipoOperacaoBD.ALTERACAO) {
				this.sql = "UPDATE consulta SET valor = ? WHERE data_consulta = ? AND hora_consulta = ? AND animal_id = ?";
				this.statement = bd.connection.prepareStatement(this.sql);

				this.statement.setFloat(1, consulta.getValor());
				this.statement.setString(2, consulta.getData().toString());
				this.statement.setString(3, consulta.getHora().toString());
				this.statement.setInt(4, consulta.getAnimal().getId());

				this.statement.executeUpdate();
				this.msg = "Consulta alterada com sucesso!";
			} else if (operacao == TipoOperacaoBD.EXCLUSAO) {
				this.sql = "DELETE FROM consulta WHERE data_consulta = ? AND hora_consulta = ? AND animal_id = ?";
				this.statement = bd.connection.prepareStatement(this.sql);

				this.statement.setString(1, consulta.getData().toString());
				this.statement.setString(2, consulta.getHora().toString());
				this.statement.setInt(3, consulta.getAnimal().getId());

				this.statement.executeUpdate();
				this.msg = "Consulta cancelada/excluída com sucesso!";
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

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getMsg() {
		return msg;
	}

	public List<Consulta> listarConsultas() {
		List<Consulta> lista = new java.util.ArrayList<>();
		this.sql = "SELECT c.data_consulta, c.hora_consulta, c.animal_id, a.nome AS animal_nome, c.veterinario_crmv, v.nome AS vet_nome, c.valor " +
		           "FROM consulta c " +
		           "INNER JOIN Animal a ON c.animal_id = a.id " +
		           "INNER JOIN veterinario v ON c.veterinario_crmv = v.crmv";

		if (!bd.getConnection()) {
			this.msg = "Falha ao conectar ao banco de dados.";
			return lista;
		}

		try {
			this.statement = bd.connection.prepareStatement(this.sql);
			this.resultSet = this.statement.executeQuery();

			while (this.resultSet.next()) {
				Consulta c = new Consulta();

				String dataStr = this.resultSet.getString("data_consulta");
				if (dataStr != null && dataStr.contains("/")) {
					String[] partes = dataStr.split("/");
					if (partes.length == 3) {
						c.setData(new Data(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]), Integer.parseInt(partes[2])));
					}
				}

				String horaStr = this.resultSet.getString("hora_consulta");
				if (horaStr != null && horaStr.contains(":")) {
					String[] partes = horaStr.split(":");
					if (partes.length == 2) {
						c.setHora(new Hora(Integer.parseInt(partes[0]), Integer.parseInt(partes[1])));
					}
				}

				c.getAnimal().setId(this.resultSet.getInt("animal_id"));
				c.getAnimal().setNome(this.resultSet.getString("animal_nome"));

				c.getVet().setCrmv(this.resultSet.getString("veterinario_crmv"));
				c.getVet().setNome(this.resultSet.getString("vet_nome"));

				c.setValor(this.resultSet.getFloat("valor"));

				lista.add(c);
			}
		} catch (SQLException e) {
			this.msg = "Erro ao listar consultas: " + e.getMessage();
		} finally {
			bd.close();
		}

		return lista;
	}
}