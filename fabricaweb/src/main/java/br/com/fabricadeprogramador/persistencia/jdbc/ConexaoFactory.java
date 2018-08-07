package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	
public static Connection getConnection() {
		
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/cjweb1?useTimezone=true&serverTimezone=UTC", "root","1234");
		} catch (SQLException e) {
			throw new RuntimeException("Não conectou com o banco! "+e);
		}
	}

}
