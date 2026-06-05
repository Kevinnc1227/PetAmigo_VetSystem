package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class AnimalDAO {
	
	
	public void Salvar(Animal animal) {	
		String sql = "INSERT INTO Animal(nome , especie , peso) VALUES (? , ? , ?)";
		try {
			BD bd = new BD();
			bd.getConnection();
			PreparedStatement stmt = bd.connection.prepareStatement(sql);			
			
			stmt.setString(1 , animal.getNome());
			stmt.setString(2 , animal.getEspecie().toString());
			stmt.setDouble(3 , animal.getPeso());
			
			stmt.execute();
			stmt.close();
			bd.close();
			
			JOptionPane.showMessageDialog(null, "Animal Cadastrado");
		} catch (SQLException e) {
			
		}
	}
	
	
}
