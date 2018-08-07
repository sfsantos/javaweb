package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.entidade.Usuario;

public class UsuarioDAO {
	
private Connection con = ConexaoFactory.getConnection();
	
	public void cadastrar(Usuario usuario) {
		
		String sql = "INSERT INTO usuario (nome, login, senha) VALUES (?,?,md5(?))";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			
			preparador.execute();
			System.out.println("Cadastrado com sucesso");
		} catch (SQLException e) {
			
			throw new RuntimeException("Erro ao cadastrar"+ e);
		}
	}
	
	public void alterar(Usuario usuario) {
		String sql = "UPDATE usuario SET nome=?, login=?, senha=md5(?) WHERE id=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setLong(4, usuario.getId());
			
			preparador.execute();
			System.out.println("Alterado com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao alterar o registro "+ e);
		}
	}
	
	public void excluir(Usuario usuario) {
		
		String sql="DELETE FROM usuario WHERE id=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setLong(1, usuario.getId());
			
			preparador.execute();
			System.out.println("Excluido com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao deletar" + e);
		}
	}

	public void salvar(Usuario usuario) {
		
		if(usuario.getId()!=null && usuario.getId()>0) {
			alterar(usuario);
		}else {
			cadastrar(usuario);
		}
	}
	
	public Usuario buscar(Integer id) {
		
		Usuario usuRetorno = null;
		String sql = "SELECT *FROM usuario WHERE id=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()) {
				
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
			}
			if (resultado.first()) {
				System.out.println("Encontrado com sucesso!");
			}else{
				System.out.println("Não Encontrado !");
			}
			
			
		} catch (SQLException e) {
			
			throw new RuntimeException();
		}
		
		return usuRetorno;
	}
	
	public Usuario buscar(String nome) {
		
		Usuario usuRetorno = null;
		String sql = "SELECT *FROM usuario WHERE nome=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, nome);
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()) {
				
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
			}
			
		} catch (SQLException e) {
			
			throw new RuntimeException();
		}
		
		return usuRetorno;
	
	}
	
	public List<Usuario> buscarTodos(){
		//Objeto de retorno do método
		List<Usuario> listaRetorno = new ArrayList<Usuario>();
		String sql = "SELECT *FROM usuario ORDER BY id";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			//Retorno da consulta em resultset
			
			ResultSet resultado = preparador.executeQuery();
			//Navega nos registros
			while(resultado.next()) {
				// instancia o objeto Usuario
				Usuario usu = new Usuario();
				
				//Carga de dados no usuario
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));
				//Adiciona na lista
				listaRetorno.add(usu);
								
				
			}
			System.out.println("Busca com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return listaRetorno;
	}
	
	public Usuario autenticar(Usuario usuConsulta) {
		
		//Objeto de retorno do método
		Usuario usuRetorno = null;
		String sql="SELECT *FROM usuario WHERE login=? and senha=md5(?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
				
				usuRetorno = new Usuario();
				//usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
				
				System.out.println("Usuário Autenticado");
			}
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
		return usuRetorno;
	}

}
