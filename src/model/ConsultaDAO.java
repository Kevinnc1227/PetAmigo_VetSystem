// Autor: Lucas
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			if (this.resultSet != null) {
				try {
					this.resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (this.statement != null) {
				try {
					this.statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			bd.close();
		}
	}

	public String atualizar(TipoOperacaoBD operacao) {
		if (!bd.getConnection()) {
			return "Falha ao conectar ao banco de dados.";
		}

		try {
			if (operacao == TipoOperacaoBD.INCLUSAO) {
				// Ajustado para bater com as colunas: idAnimal, crmvVeterinario
				this.sql = "INSERT INTO Consulta (data_consulta, hora_consulta, idAnimal, crmvVeterinario, valor) VALUES (?, ?, ?, ?, ?)";
				this.statement = bd.connection.prepareStatement(this.sql);

				this.statement.setString(1, consulta.getData().toString());
				this.statement.setString(2, consulta.getHora().toString());
				this.statement.setInt(3, consulta.getAnimal().getId());
				this.statement.setString(4, consulta.getVet().getCrmv());
				this.statement.setFloat(5, consulta.getValor());

				this.statement.executeUpdate();
				this.msg = "Consulta agendada com sucesso!";
			} else if (operacao == TipoOperacaoBD.ALTERACAO) {
				this.sql = "UPDATE Consulta SET valor = ? WHERE data_consulta = ? AND hora_consulta = ? AND idAnimal = ?";
				this.statement = bd.connection.prepareStatement(this.sql);

				this.statement.setFloat(1, consulta.getValor());
				this.statement.setString(2, consulta.getData().toString());
				this.statement.setString(3, consulta.getHora().toString());
				this.statement.setInt(4, consulta.getAnimal().getId());

				this.statement.executeUpdate();
				this.msg = "Consulta alterada com sucesso!";
			} else if (operacao == TipoOperacaoBD.EXCLUSAO) {
				this.sql = "DELETE FROM Consulta WHERE data_consulta = ? AND hora_consulta = ? AND idAnimal = ?";
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
			if (this.statement != null) {
				try {
					this.statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		List<Consulta> lista = new ArrayList<>();
		this.sql = "SELECT c.data_consulta, c.hora_consulta, c.animal_id, a.nome AS animal_nome, c.veterinario_crmv, v.nome AS vet_nome, c.valor "
				+ "FROM consulta c " + "INNER JOIN Animal a ON c.animal_id = a.id "
				+ "INNER JOIN veterinario v ON c.veterinario_crmv = v.crmv";
		if (!bd.getConnection()) {
			this.msg = "Falha ao conectar ao banco de dados.";
			return lista;
		}

		try {
			this.statement = bd.connection.prepareStatement(this.sql);
			this.resultSet = this.statement.executeQuery();

			while (this.resultSet.next()) {
				Consulta c = new Consulta();

				// Garantindo que os objetos internos não sejam nulos
				Animal animal = new Animal();
				Veterinario vet = new Veterinario();
				c.setAnimal(animal);
				c.setVet(vet);

				// Tratamento e conversão da Data vinda do MySQL (Formato padrão: AAAA-MM-DD)
				String dataStr = this.resultSet.getString("data_consulta");
				if (dataStr != null) {
					if (dataStr.contains("/")) {
						String[] partes = dataStr.split("/");
						if (partes.length == 3) {
							c.setData(new Data(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]),
									Integer.parseInt(partes[2])));
						}
					} else if (dataStr.contains("-")) {
						String[] partes = dataStr.split("-");
						if (partes.length == 3) {
							// MySQL DATE is YYYY-MM-DD
							c.setData(new Data(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]),
									Integer.parseInt(partes[0])));
						}
					}
				}

				// Tratamento e conversão da Hora vinda do MySQL (Formato padrão: HH:MM:SS)
				String horaStr = this.resultSet.getString("hora_consulta");
				if (horaStr != null && horaStr.contains(":")) {
					String[] partes = horaStr.split(":");
					if (partes.length >= 2) {
						c.setHora(new Hora(Integer.parseInt(partes[0]), Integer.parseInt(partes[1])));
					}
				}

				c.getAnimal().setId(this.resultSet.getInt("idAnimal"));
				c.getAnimal().setNome(this.resultSet.getString("animal_nome"));

				c.getVet().setCrmv(this.resultSet.getString("crmvVeterinario"));
				c.getVet().setNome(this.resultSet.getString("vet_nome"));

				c.setValor(this.resultSet.getFloat("valor"));

				lista.add(c);
			}
		} catch (SQLException e) {
			this.msg = "Erro ao listar consultas: " + e.getMessage();
			System.out.println(this.msg);
		} finally {
			if (this.resultSet != null) {
				try {
					this.resultSet.close();
				} catch (SQLException e) {
				}
			}
			if (this.statement != null) {
				try {
					this.statement.close();
				} catch (SQLException e) {
				}
			}
			bd.close();
		}

		return lista;
	}
}