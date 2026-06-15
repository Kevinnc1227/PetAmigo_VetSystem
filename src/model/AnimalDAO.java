// Autor: Leonardo
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO implements OperacaoBD {

	private Animal animal;
	private BD bd;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String msg;
	private String sql;

	public AnimalDAO() {
		this.bd = new BD();
		this.msg = "";
	}

	public boolean localizar() {
		this.sql = "SELECT * FROM Animal WHERE id = ?";

		if (animal == null) {
			this.msg = "Objeto animal nulo.";
			return false;
		}

		try {
			if (!bd.getConnection()) {
				this.msg = "Falha ao conectar ao banco de dados.";
				return false;
			}

			this.statement = bd.connection.prepareStatement(this.sql);
			this.statement.setInt(1, animal.getId());
			this.resultSet = this.statement.executeQuery();

			if (this.resultSet.next()) {
				animal.setId(this.resultSet.getInt("id"));
				animal.setNome(this.resultSet.getString("nome"));

				String especie = this.resultSet.getString("especie");
				animal.setEspecie(TipoAnimal.valueOf(especie.toUpperCase()));

				Cliente cliente = new Cliente();
				cliente.setCpf(this.resultSet.getString("cpfCliente"));
				animal.setCliente(cliente);

				return true;
			}

			this.msg = "Animal não encontrado.";
			return false;
		} catch (SQLException e) {
			this.msg = "Erro ao localizar animal: " + e.getMessage();
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

	@Override
	public String atualizar(TipoOperacaoBD operacao) {
		if (animal == null) {
			return "Objeto animal nulo.";
		}
		try {
			if (!bd.getConnection()) {
				return "Falha ao conectar ao banco de dados.";
			}
			if (operacao == TipoOperacaoBD.INCLUSAO) {
				this.sql = "INSERT INTO Animal(nome, especie, cpfCliente) VALUES (?, ?, ?)";
				this.statement = bd.connection.prepareStatement(this.sql);
				this.statement.setString(1, animal.getNome());
				this.statement.setString(2, animal.getEspecie().name());
				this.statement.setString(3, animal.getCliente().getCpf());
				this.statement.executeUpdate();
				this.msg = "Animal incluído com sucesso!";
			} else if (operacao == TipoOperacaoBD.ALTERACAO) {
				this.sql = "UPDATE Animal SET nome=?, especie=?, cpfCliente=? WHERE id=?";
				this.statement = bd.connection.prepareStatement(this.sql);
				this.statement.setString(1, animal.getNome());
				this.statement.setString(2, animal.getEspecie().name());
				this.statement.setString(3, animal.getCliente().getCpf());
				this.statement.setInt(4, animal.getId());
				this.statement.executeUpdate();
				this.msg = "Animal alterado com sucesso!";
			} else if (operacao == TipoOperacaoBD.EXCLUSAO) {
				this.sql = "DELETE FROM Animal WHERE id=?";
				this.statement = bd.connection.prepareStatement(this.sql);
				this.statement.setInt(1, animal.getId());
				this.statement.executeUpdate();
				this.msg = "Animal excluído com sucesso!";
			} else {
				this.msg = "Operação inválida.";
			}
		} catch (SQLException e) {
			this.msg = "Erro na operação: " + e.getMessage();
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

	public List<Animal> listarAnimais() {
		List<Animal> lista = new ArrayList<>();
		this.sql = "SELECT * FROM Animal ORDER BY nome";
		if (!bd.getConnection()) {
			this.msg = "Falha ao conectar ao banco de dados.";
			return lista;
		}
		try {
			this.statement = bd.connection.prepareStatement(this.sql);
			this.resultSet = this.statement.executeQuery();
			while (this.resultSet.next()) {
				Animal a = new Animal();
				a.setId(this.resultSet.getInt("id"));
				a.setNome(this.resultSet.getString("nome"));

				String especie = this.resultSet.getString("especie");
				a.setEspecie(TipoAnimal.valueOf(especie.toUpperCase()));

				Cliente c = new Cliente();
				c.setCpf(this.resultSet.getString("cpfCliente"));
				a.setCliente(c);

				lista.add(a);
			}
		} catch (SQLException e) {
			this.msg = "Erro ao listar animais: " + e.getMessage();
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

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public String getMsg() {
		return msg;
	}

	public List<Animal> listarPorCliente(String cpfCliente) {
		// TODO Auto-generated method stub
		return null;
	}
}
