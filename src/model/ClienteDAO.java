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

    // Método para buscar um cliente no banco de dados usando o CPF como chave.
    public boolean localizar() {
        sql = "SELECT * FROM cliente WHERE cpf = ?";
        try {
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

    // Método principal que decide se vai salvar, alterar ou apagar os dados no banco.
    public String atualizar(TipoOperacaoBD operacao) {
        msg = "Operação realizada com sucesso!";
        try {
            
            // Aqui faz a INCLUSÃO: Salva um novo cliente no banco de dados.
            if (operacao == TipoOperacaoBD.INCLUSAO) {
                sql = "INSERT INTO cliente(cpf, nome, email, endereco, telefone) VALUES (?, ?, ?, ?, ?)";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, cliente.getCpf());
                statement.setString(2, cliente.getNome());
                statement.setString(3, cliente.getEmail());
                statement.setString(4, cliente.getEndereco() != null ? cliente.getEndereco().toString() : "");
                statement.setString(5, cliente.getTelefone() != null ? cliente.getTelefone().toString() : "");

            // Aqui faz a ALTERAÇÃO: Atualiza os dados de um cliente que já existe.
            } else if (operacao == TipoOperacaoBD.ALTERACAO) {
                sql = "UPDATE cliente SET nome = ?, email = ?, endereco = ?, telefone = ? WHERE cpf = ?";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, cliente.getNome());
                statement.setString(2, cliente.getEmail());
                statement.setString(3, cliente.getEndereco() != null ? cliente.getEndereco().toString() : "");
                statement.setString(4, cliente.getTelefone() != null ? cliente.getTelefone().toString() : "");
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
        }
        
        return msg;
    }
}