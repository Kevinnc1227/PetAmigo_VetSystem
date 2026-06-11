package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VeterinarioDAO implements OperacaoBD {
    private BD bd;
    private Veterinario veterinario;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String sql, msg;

    public VeterinarioDAO() {
        this.bd = null;
        this.veterinario = null;
    }

    public void setBd(BD bd) {
        this.bd = bd;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    // Método para buscar um veterinário no banco de dados usando o CRMV como chave.
    public boolean localizar() {
        sql = "SELECT * FROM veterinario WHERE crmv = ?";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, veterinario.getCrmv());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                veterinario.setCrmv(resultSet.getString("crmv"));
                veterinario.setNome(resultSet.getString("nome"));
                veterinario.setEmail(resultSet.getString("email"));
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
            
            // Aqui faz a INCLUSÃO: Salva um novo veterinário no banco de dados.
            if (operacao == TipoOperacaoBD.INCLUSAO) {
                sql = "INSERT INTO veterinario(crmv, nome, email, endereco, telefone) VALUES (?, ?, ?, ?, ?)";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, veterinario.getCrmv());
                statement.setString(2, veterinario.getNome());
                statement.setString(3, veterinario.getEmail());
                statement.setString(4, veterinario.getEndereco() != null ? veterinario.getEndereco().toString() : "");
                statement.setString(5, veterinario.getTelefone() != null ? veterinario.getTelefone().toString() : "");

            // Aqui faz a ALTERAÇÃO: Atualiza os dados de um veterinário que já existe.
            } else if (operacao == TipoOperacaoBD.ALTERACAO) {
                sql = "UPDATE veterinario SET nome = ?, email = ?, endereco = ?, telefone = ? WHERE crmv = ?";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, veterinario.getNome());
                statement.setString(2, veterinario.getEmail());
                statement.setString(3, veterinario.getEndereco() != null ? veterinario.getEndereco().toString() : "");
                statement.setString(4, veterinario.getTelefone() != null ? veterinario.getTelefone().toString() : "");
                statement.setString(5, veterinario.getCrmv());

            // Aqui faz a EXCLUSÃO: Apaga o veterinário do banco de dados usando o CRMV.
            } else if (operacao == TipoOperacaoBD.EXCLUSAO) {
                sql = "DELETE FROM veterinario WHERE crmv = ?";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, veterinario.getCrmv());
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