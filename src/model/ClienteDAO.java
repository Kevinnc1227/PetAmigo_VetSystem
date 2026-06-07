package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO implements OperacaoBD {
    private BD bd;
    private Cliente cliente;

    private PreparedStatement statement;
    private ResultSet resultSet;

    private String sql, msg;

    // Construtor padrão limpo
    public ClienteDAO() {
        this.bd = null;
        this.cliente = null;
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

    public boolean localizar() {
        sql = "SELECT * FROM cliente WHERE cpf = ?";
        try {
            // Prepara a consulta usando a conexão ativa trazida pela classe BD
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, cliente.getCpf());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEmail(resultSet.getString("email"));
                return true;
            }
            return false;
        } catch (SQLException erro) {
            return false;
        }
    }

    // O método atualizar DEVE receber o TipoOperacaoBD para respeitar o livro e a interface
    public String atualizar(TipoOperacaoBD operacao) {
        msg = "Operação realizada com sucesso!";
        try {
            if (operacao == TipoOperacaoBD.INCLUSAO) {
                sql = "INSERT INTO cliente(cpf, nome, email, endereco, telefone) VALUES (?, ?, ?, ?, ?)";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, cliente.getCpf());
                statement.setString(2, cliente.getNome());
                statement.setString(3, cliente.getEmail());
                statement.setString(4, cliente.getEndereco() != null ? cliente.getEndereco().toString() : "");
                statement.setString(5, cliente.getTelefone() != null ? cliente.getTelefone().toString() : "");

            } else if (operacao == TipoOperacaoBD.ALTERACAO) {
                sql = "UPDATE cliente SET nome = ?, email = ?, endereco = ?, telefone = ? WHERE cpf = ?";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, cliente.getNome());
                statement.setString(2, cliente.getEmail());
                statement.setString(3, cliente.getEndereco() != null ? cliente.getEndereco().toString() : "");
                statement.setString(4, cliente.getTelefone() != null ? cliente.getTelefone().toString() : "");
                statement.setString(5, cliente.getCpf());

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
        }

        return msg;
    }
}