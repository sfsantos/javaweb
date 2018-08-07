package br.com.fabricadeprogramador;

import br.com.fabricadeprogramador.persistencia.jdbc.ConexaoFactory;

public class TestConexaoFactory {

	public static void main(String[] args) {
		//Testando a classe Conexao
		ConexaoFactory.getConnection();

	}

}
