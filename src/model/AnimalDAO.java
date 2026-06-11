package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalDAO {

    private Animal animal;
    private BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men;
    private String sql;

    public AnimalDAO() {
        this.bd = new BD();
        this.men = "";
    }

    public boolean localizar() {
        this.sql = "SELECT * FROM Animal WHERE id = ?";
        try {

            bd.getConnection();
            this.statement = bd.connection.prepareStatement(this.sql);
            this.statement.setInt(1, animal.getId());
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {

                animal.setId(this.resultSet.getInt("id"));
                animal.setNome(this.resultSet.getString("nome"));
                String especie = this.resultSet.getString("especie");
                animal.setEspecie(TipoAnimal.valueOf(especie.toUpperCase())
                );
                animal.setPeso(this.resultSet.getFloat("peso"));
                return true;
            }
            this.men = "Animal não encontrado.";
            return false;
        } catch (SQLException e) {
            this.men = "Erro ao localizar animal: " + e.getMessage();
            return false;
        } finally {
            bd.close();
        }
    }
    public String atualizar(int operacao) {
        try {
            bd.getConnection();
            if (operacao == 1) { // INCLUSAO

                this.sql =
                        "INSERT INTO Animal(nome, especie, peso) VALUES (?, ?, ?)";

                this.statement = bd.connection.prepareStatement(this.sql);

                this.statement.setString(1, animal.getNome());
                this.statement.setString(2,animal.getEspecie().name());
                this.statement.setFloat(3, animal.getPeso());
                this.statement.executeUpdate();

                this.men = "Animal incluído com sucesso!";
            }

            else if (operacao == 2) { // ALTERACAO
                this.sql = "UPDATE Animal SET nome=?, especie=?, peso=? WHERE id=?";

                this.statement = bd.connection.prepareStatement(this.sql);

                this.statement.setString(1, animal.getNome());
                this.statement.setString(2,animal.getEspecie().name());
                this.statement.setFloat(3, animal.getPeso());
                this.statement.setInt(4, animal.getId());
                this.statement.executeUpdate();

                this.men = "Animal alterado com sucesso!";
            }

            else if (operacao == 3) { // EXCLUSAO
                this.sql = "DELETE FROM Animal WHERE id=?";

                this.statement = bd.connection.prepareStatement(this.sql);

                this.statement.setInt(1, animal.getId());
                this.statement.executeUpdate();
                this.men = "Animal excluído com sucesso!";
            }
            else {
                this.men = "Operação inválida.";
            }

        } catch (SQLException e) {
            this.men = "Erro na operação: " + e.getMessage();
        } finally {
            bd.close();
        }
        return this.men;
    }
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getMen() {
        return men;
    }
}
