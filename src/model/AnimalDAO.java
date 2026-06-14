package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                animal.setEspecie(
                    TipoAnimal.valueOf(especie.toUpperCase())
                );

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

                this.sql =
                    "INSERT INTO Animal(nome, especie, cpfCliente) " +
                    "VALUES (?, ?, ?)";

                this.statement =
                    bd.connection.prepareStatement(this.sql);

                this.statement.setString(1, animal.getNome());
                this.statement.setString(2, animal.getEspecie().name());
                this.statement.setString(3, animal.getCliente().getCpf()
                );
                this.statement.executeUpdate();
                this.msg = "Animal incluído com sucesso!";
            }

            else if (operacao == TipoOperacaoBD.ALTERACAO) {
                this.sql =
                    "UPDATE Animal " +
                    "SET nome=?, especie=?, cpfCliente=? " +
                    "WHERE id=?";

                this.statement =
                    bd.connection.prepareStatement(this.sql);

                this.statement.setString(1, animal.getNome());
                this.statement.setString(2, animal.getEspecie().name());
                this.statement.setString(3, animal.getCliente().getCpf()
                );
                this.statement.setInt(4, animal.getId());
                this.statement.executeUpdate();

                this.msg = "Animal alterado com sucesso!";
            }

            else if (operacao == TipoOperacaoBD.EXCLUSAO) {

                this.sql =
                    "DELETE FROM Animal WHERE id=?";

                this.statement =
                    bd.connection.prepareStatement(this.sql);

                this.statement.setInt(1, animal.getId());

                this.statement.executeUpdate();

                this.msg = "Animal excluído com sucesso!";
            }

            else {

                this.msg = "Operação inválida.";
            }

        } catch (SQLException e) {

            this.msg = "Erro na operação: " + e.getMessage();

        } finally {

            bd.close();
        }

        return this.msg;
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
}