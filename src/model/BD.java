// Autor: Lucas
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
	public Connection connection = null;
	final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DBNAME = "PetAmigo_VetSystem";
	private final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
	private final String LOGIN = "root";
	private final String SENHA = "10G@briel1001";

	/**
	 * Metodo que faz conexao com o banco de dados retorna true se houve sucesso, ou
	 * false em caso negativo
	 */
	public boolean getConnection() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, LOGIN, SENHA);
			System.out.println("Conectou");
			return true;
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver nao encontrado " + erro.toString());
			return false;
		} catch (SQLException erro) {
			System.out.println("Falha ao conectar " + erro.toString());
			return false;
		}
	}

	public void close() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("Desconectou");
			}
		} catch (SQLException erro) {
			System.out.println("Falha ao fechar conexao " + erro.toString());
		}
	}
}