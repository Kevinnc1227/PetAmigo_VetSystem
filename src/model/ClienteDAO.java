package model;
import java.sql.*;

import Cliente;

public class ClienteDAO implements OperacaoBD {
    // Mantendo os atributos privados conforme boas práticas e o exemplo do professor
    private BD bd;
    private Cliente cliente;

    private PreparedStatement statement;
    private ResultSet resultSet;

    private String sql, msg;
    
    public ClienteDAO() {
        bd = null;
        cliente = null;
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
        // Usamos o CPF como chave de busca, pois é o identificador do Cliente
        sql = "SELECT * FROM cliente WHERE cpf = ?";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, cliente.getCpf()); 

            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEmail(resultSet.getString("email"));
                
                // NOTA: Como Endereco e Telefone são objetos no seu UML, 
                // você precisará instanciá-los ou pegar as Strings dependendo de como 
                // você estruturou seu banco de dados.
                // Exemplo simplificado assumindo que o BD guarda Strings:
                // cliente.setEndereco(new Endereco(resultSet.getString("endereco")));
                
                return true;
            }
            return false;
        }
        catch (SQLException erro) {
            return false;
        }
    }

    public String atualizar(TipoOperacaoBD operacao) {
        msg = "Operação realizada com sucesso!";
        try {
            if (operacao == TipoOperacaoBD.INCLUSAO) {
                // A quantidade de "?" precisa ser EXATAMENTE igual aos campos
                sql = "INSERT INTO cliente(cpf, nome, email, endereco, telefone) VALUES (?, ?, ?, ?, ?)";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, cliente.getCpf());
                statement.setString(2, cliente.getNome());
                statement.setString(3, cliente.getEmail());
                
                // Convertendo os objetos Endereco e Telefone para String temporariamente
                statement.setString(4, cliente.getEndereco() != null ? cliente.getEndereco().toString() : "");
                statement.setString(5, cliente.getTelefone() != null ? cliente.getTelefone().toString() : "");
            }
            else if (operacao == TipoOperacaoBD.ALTERACAO) {
                // No UPDATE, o CPF vai no final da frase (no WHERE)
                sql = "UPDATE cliente SET nome = ?, email = ?, endereco = ?, telefone = ? WHERE cpf = ?";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, cliente.getNome());
                statement.setString(2, cliente.getEmail());
                statement.setString(3, cliente.getEndereco() != null ? cliente.getEndereco().toString() : "");
                statement.setString(4, cliente.getTelefone() != null ? cliente.getTelefone().toString() : "");
                statement.setString(5, cliente.getCpf()); // CPF é o parâmetro 5
            }
            else if (operacao == TipoOperacaoBD.EXCLUSAO) {
                sql = "DELETE FROM cliente WHERE cpf = ?";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, cliente.getCpf());
            }

            if (statement.executeUpdate() == 0) {
                msg = "Falha na operação! Nenhum registro foi afetado.";
            }
        }
        catch (SQLException erro) {
            msg = "Falha na operação - " + erro.toString();
        }

        return msg;
    }
}