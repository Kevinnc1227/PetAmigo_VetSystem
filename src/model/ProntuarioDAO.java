package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProntuarioDAO {
	private Prontuario prontuario;
	private BD bd;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men;
	private String sql;
	
	public ProntuarioDAO() {
		this.bd = new BD();
		this.men = "";
	}
	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public String getMen() {
		return men;
	    }
	
	
	public boolean localizar() {
	    	this.sql = "SELECT * FROM Prontuario WHERE idAnimal = ? ";
	    	
	    	try {
				bd.getConnection();
				this.statement = bd.connection.prepareStatement(sql);
				this.statement.setInt(1 , prontuario.getIdAnimal());
				this.resultSet = this.statement.executeQuery();
				
				if(this.resultSet.next()) {
					prontuario.setHistorico(this.resultSet.getString("historico"));
					prontuario.setUltimaVacina(this.resultSet.getString("ultimaVacina"));
					prontuario.setObservacoes(this.resultSet.getString("observacoes"));
					return true;
				}else {
					this.men = "Prontuário não encontrado.";
			        return false;
				}
				
			} catch(SQLException e) {

		        this.men = e.getMessage();
		        return false;

		    } finally {
		        bd.close();
		    }
	    }
	    
	    public String atualizar(int operacao) {
	    	
	    	
	    	
	    	try {
				bd.getConnection();
				if(operacao == 1) { //INCLUSAO
				this.sql = "INSERT INTO Prontuario " +
                "(idAnimal, historico, ultimaVacina, observacoes) " +
                "VALUES (?, ?, ?, ?)";
				this.statement = bd.connection.prepareStatement(sql);
				 this.statement.setInt( 1,prontuario.getIdAnimal());
				 this.statement.setString( 2,prontuario.getHistorico());
				 this.statement.setString( 3,prontuario.getUltimaVacina());
				 this.statement.setString( 4,prontuario.getObservacoes());
			     this.statement.executeUpdate();
			     this.men = "Prontuário incluído com sucesso!";
	
			} else if(operacao == 2) { // ALTERA

	            this.sql =
	                "UPDATE Prontuario " +
	                "SET historico=?, " +
	                "ultimaVacina=?, " +
	                "observacoes=? " +
	                "WHERE idAnimal=?";

	            this.statement = bd.connection.prepareStatement(this.sql);

	            this.statement.setString(1,prontuario.getHistorico());
	            this.statement.setString(2,prontuario.getUltimaVacina());
	            this.statement.setString(3,prontuario.getObservacoes());
	            this.statement.setInt(4,prontuario.getIdAnimal());
	            this.statement.executeUpdate();
	            this.men = "Prontuário alterado com sucesso!";
			}else if(operacao == 3) {//DELETA
	            this.sql =
	            	"DELETE FROM Prontuario " +
	                "WHERE idAnimal=?";
	            
	            this.statement =bd.connection.prepareStatement(this.sql);
	            this.statement.setInt( 1,prontuario.getIdAnimal());
	            this.statement.executeUpdate();
	            this.men = "Prontuário excluído com sucesso!";
	        }
			else {
	            this.men = "Operação inválida.";
	        }

	    } catch(SQLException e) {
	        this.men = "Erro: " + e.getMessage();
	    } finally {
	        bd.close();
	    }
	    return this.men;
	}
}
