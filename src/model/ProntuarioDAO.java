package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProntuarioDAO implements OperacaoBD {
    private Prontuario prontuario;
    private BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String msg;
    private String sql;
    
    public ProntuarioDAO() {
        this.bd = new BD();
        this.msg = "";
    }
    
    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public String getMsg() {
        return msg;
    }
    
    public boolean localizar() {
        this.sql = "SELECT * FROM Prontuario WHERE idAnimal = ?";
        
        if (this.prontuario == null) {
            this.msg = "O objeto prontuário está nulo.";
            return false;
        }
        
        try {
            if (!bd.getConnection()) { this.msg = "Falha ao conectar ao banco de dados."; return false; }
            this.statement = bd.connection.prepareStatement(sql);
            this.statement.setInt(1, prontuario.getIdAnimal());
            this.resultSet = this.statement.executeQuery();
            
            if (this.resultSet.next()) {
                prontuario.setHistorico(this.resultSet.getString("historico"));
                prontuario.setUltimaVacina(this.resultSet.getString("ultimaVacina"));
                prontuario.setObservacoes(this.resultSet.getString("observacoes"));
                return true;
            } else {
                this.msg = "Prontuário não encontrado.";
                return false;
            }
            
        } catch (SQLException e) {
            this.msg = "Erro ao localizar: " + e.getMessage();
            e.printStackTrace();
            return false;
        } finally {
            bd.close();
        }
    }
    
    public String atualizar(TipoOperacaoBD operacao) {
        if (this.prontuario == null) {
            return "O objeto prontuário está nulo.";
        }
        
        if (!bd.getConnection()) {
            return "Falha ao conectar ao banco de dados.";
        }
        try {
            if (operacao == TipoOperacaoBD.INCLUSAO) {
                this.sql = "INSERT INTO Prontuario (idAnimal, historico, ultimaVacina, observacoes) VALUES (?, ?, ?, ?)";
                this.statement = bd.connection.prepareStatement(sql);
                this.statement.setInt(1, prontuario.getIdAnimal());
                this.statement.setString(2, prontuario.getHistorico());
                this.statement.setString(3, prontuario.getUltimaVacina());
                this.statement.setString(4, prontuario.getObservacoes());
                this.statement.executeUpdate();
                this.msg = "Prontuário incluído com sucesso!";

            } else if (operacao == TipoOperacaoBD.ALTERACAO) {
                this.sql = "UPDATE Prontuario SET historico=?, ultimaVacina=?, observacoes=? WHERE idAnimal=?";
                this.statement = bd.connection.prepareStatement(this.sql);
                this.statement.setString(1, prontuario.getHistorico());
                this.statement.setString(2, prontuario.getUltimaVacina());
                this.statement.setString(3, prontuario.getObservacoes());
                this.statement.setInt(4, prontuario.getIdAnimal());
                this.statement.executeUpdate();
                this.msg = "Prontuário alterado com sucesso!";
                
            } else if (operacao == TipoOperacaoBD.EXCLUSAO) {
                this.sql = "DELETE FROM Prontuario WHERE idAnimal=?";
                this.statement = bd.connection.prepareStatement(this.sql);
                this.statement.setInt(1, prontuario.getIdAnimal());
                this.statement.executeUpdate();
                this.msg = "Prontuário excluído com sucesso!";
            } else {
                this.msg = "Operação inválida.";
            }

        } catch (SQLException e) {
            this.msg = "Erro na operação: " + e.getMessage();
            e.printStackTrace();
        } finally {
            bd.close();
        }
        return this.msg;
    }
}