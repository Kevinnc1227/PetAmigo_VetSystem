// Autor: Kevin
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO implements OperacaoBD {
    private BD bd;
    private Veterinario veterinario;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String sql, msg;

    public VeterinarioDAO() {
        this.bd = new BD();
        this.veterinario = new Veterinario();
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
        if (veterinario == null || veterinario.getCrmv() == null || veterinario.getCrmv().trim().isEmpty()) {
            msg = "CRMV do veterinário não informado.";
            return false;
        }
        sql = "SELECT * FROM veterinario WHERE crmv = ?";
        if (!bd.getConnection()) {
            msg = "Falha ao conectar ao banco de dados.";
            return false;
        }
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, veterinario.getCrmv());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                veterinario.setCrmv(resultSet.getString("crmv"));
                veterinario.setNome(resultSet.getString("nome"));
                veterinario.setEmail(resultSet.getString("email"));
                veterinario.setEspecialidade(resultSet.getString("especialidade"));
                
                veterinario.setEndereco(resultSet.getString("endereco"));
                veterinario.setTelefone(resultSet.getString("telefone"));
                
                return true;
            }
            msg = "Veterinário não encontrado.";
            return false;
        } catch (SQLException erro) {
            msg = "Erro ao localizar veterinário: " + erro.getMessage();
            return false;
        } finally {
            if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            bd.close();
        }
    }

    // Método principal que decide se vai salvar, alterar ou apagar os dados no banco.
    public String atualizar(TipoOperacaoBD operacao) {
        msg = "Operação realizada com sucesso!";
        if (!bd.getConnection()) {
            return "Falha ao conectar ao banco de dados.";
        }
        try {
            
            // Aqui faz a INCLUSÃO: Salva um novo veterinário no banco de dados.
            if (operacao == TipoOperacaoBD.INCLUSAO) {
                sql = "INSERT INTO veterinario(crmv, nome, email, endereco, telefone, especialidade) VALUES (?, ?, ?, ?, ?, ?)";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, veterinario.getCrmv());
                statement.setString(2, veterinario.getNome());
                statement.setString(3, veterinario.getEmail());
                statement.setString(4, veterinario.getEndereco() != null ? veterinario.getEndereco() : "");
                statement.setString(5, veterinario.getTelefone() != null ? veterinario.getTelefone() : "");
                statement.setString(6, veterinario.getEspecialidade());

            // Aqui faz a ALTERAÇÃO: Atualiza os dados de um veterinário que já existe.
            } else if (operacao == TipoOperacaoBD.ALTERACAO) {
                sql = "UPDATE veterinario SET nome = ?, email = ?, endereco = ?, telefone = ?, especialidade = ? WHERE crmv = ?";
                statement = bd.connection.prepareStatement(sql);

                statement.setString(1, veterinario.getNome());
                statement.setString(2, veterinario.getEmail());
                statement.setString(3, veterinario.getEndereco() != null ? veterinario.getEndereco() : "");
                statement.setString(4, veterinario.getTelefone() != null ? veterinario.getTelefone() : "");
                statement.setString(5, veterinario.getEspecialidade());
                statement.setString(6, veterinario.getCrmv());

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
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            bd.close();
        }

        return msg;
    }

    public List<Veterinario> listarVeterinarios() {
        List<Veterinario> lista = new ArrayList<Veterinario>();
        String sqlListar = "SELECT * FROM veterinario ORDER BY nome";
        if (!bd.getConnection()) {
            return lista;
        }
        try {
            statement = bd.connection.prepareStatement(sqlListar);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Veterinario v = new Veterinario();
                v.setCrmv(resultSet.getString("crmv"));
                v.setNome(resultSet.getString("nome"));
                v.setEmail(resultSet.getString("email"));
                v.setEndereco(resultSet.getString("endereco"));
                v.setTelefone(resultSet.getString("telefone"));
                v.setEspecialidade(resultSet.getString("especialidade"));
                lista.add(v);
            }
        } catch (SQLException erro) {
            msg = "Erro ao listar veterinários: " + erro.getMessage();
        } finally {
            if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException e) {}
            }
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) {}
            }
            bd.close();
        }
        return lista;
    }
}