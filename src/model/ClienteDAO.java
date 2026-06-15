// Autor: Kevin
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements OperacaoBD {
	private BD bd;
	private Cliente cliente;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String sql, msg;

	public ClienteDAO() {
		this.bd = new BD();
		this.cliente = new Cliente();
	}

	public void setBd(BD bd) {
		this.bd = bd;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	// Método para buscar um cliente no banco de dados usando o CPF como chave.
	public boolean localizar() {
		if (cliente == null || cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
			msg = "CPF do cliente não informado.";
			return false;
		}
		sql = "SELECT * FROM cliente WHERE cpf = ?";
		if (!bd.getConnection()) {
			msg = "Falha ao conectar ao banco de dados.";
			return false;
		}
		try {
			statement = bd.connection.prepareStatement(sql);
			statement.setString(1, cliente.getCpf());

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				cliente.setCpf(resultSet.getString("cpf"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setEmail(resultSet.getString("email"));

				cliente.setEndereco(resultSet.getString("endereco"));
				cliente.setTelefone(resultSet.getString("telefone"));

				return true;
			}
			msg = "Cliente não encontrado.";
			return false;
		} catch (SQLException erro) {
			msg = "Erro ao localizar cliente: " + erro.getMessage();
			return false;
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			bd.close();
		}
	}

	// Método principal que decide se vai salvar, alterar ou apagar os dados no
	// banco.
	public String atualizar(TipoOperacaoBD operacao) {
		msg = "Operação realizada com sucesso!";
		if (!bd.getConnection()) {
			return "Falha ao conectar ao banco de dados.";
		}
		try {
			// Aqui faz a INCLUSÃO: Salva um novo cliente no banco de dados.
			if (operacao == TipoOperacaoBD.INCLUSAO) {
				sql = "INSERT INTO cliente(cpf, nome, email, endereco, telefone) VALUES (?, ?, ?, ?, ?)";
				statement = bd.connection.prepareStatement(sql);

				statement.setString(1, cliente.getCpf());
				statement.setString(2, cliente.getNome());
				statement.setString(3, cliente.getEmail());
				statement.setString(4, cliente.getEndereco() != null ? cliente.getEndereco() : "");
				statement.setString(5, cliente.getTelefone() != null ? cliente.getTelefone() : "");

				// Aqui faz a ALTERAÇÃO: Atualiza os dados de um cliente que já existe.
			} else if (operacao == TipoOperacaoBD.ALTERACAO) {
				sql = "UPDATE cliente SET nome = ?, email = ?, endereco = ?, telefone = ? WHERE cpf = ?";
				statement = bd.connection.prepareStatement(sql);

				statement.setString(1, cliente.getNome());
				statement.setString(2, cliente.getEmail());
				statement.setString(3, cliente.getEndereco() != null ? cliente.getEndereco() : "");
				statement.setString(4, cliente.getTelefone() != null ? cliente.getTelefone() : "");
				statement.setString(5, cliente.getCpf());

				// Aqui faz a EXCLUSÃO: Apaga o cliente do banco de dados usando o CPF.
			} else if (operacao == TipoOperacaoBD.EXCLUSAO) {
				sql = "DELETE FROM cliente WHERE cpf = ?";
				statement = bd.connection.prepareStatement(sql);

				statement.setString(1, cliente.getCpf());
			}

			if (statement.executeUpdate() == 0) {
				msg = "Falha na operação! Nenhum registro foi afetado.";
			}
		} catch (SQLException erro) {
			msg = "Falha na operação - " + erro.toString();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			bd.close();
		}

		return msg;
	}

	public List<Cliente> listarClientes() {
		List<Cliente> lista = new ArrayList<Cliente>();
		String sqlListar = "SELECT * FROM cliente ORDER BY nome";
		if (!bd.getConnection()) {
			return lista;
		}
		try {
			statement = bd.connection.prepareStatement(sqlListar);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Cliente c = new Cliente();
				c.setCpf(resultSet.getString("cpf"));
				c.setNome(resultSet.getString("nome"));
				c.setEmail(resultSet.getString("email"));
				c.setEndereco(resultSet.getString("endereco"));
				c.setTelefone(resultSet.getString("telefone"));
				lista.add(c);
			}
		} catch (SQLException erro) {
			msg = "Erro ao listar clientes: " + erro.getMessage();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			bd.close();
		}
		return lista;
	}
}