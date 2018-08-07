package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabricadeprogramador.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet(name = "Autenticador", urlPatterns = {"/autenticador.do"})

public class Autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Autenticador() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Acessando a sessao
		HttpSession sessao = request.getSession(false);
		// Se a sessaõa inda não foi expirada
		if (sessao != null) {
			// invalida a sessão 
			sessao.invalidate();
		}
		// Redirecioando para tela de login 
		response.sendRedirect("login.html");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1) Capturar os dados
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		// 2)Criar objeto usuário e adicionar os dados
		Usuario usu = new Usuario();
		usu.setLogin(login);
		usu.setSenha(senha);
		
		// 3) Criar objeto UsuarioDAO e Autenticar
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuAutenticado = usuarioDAO.autenticar(usu);
		// 4) Verificar se usuário foi encontrado
		if (usuAutenticado != null) {
			
			// Usuário encontrado
			// 5) Criar Sessão
			HttpSession sessao = request.getSession();
			// 6) Adicionar objeto como atributo da sessão
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			
			// Definindo um tempo para a Sessão expirar
			sessao.setMaxInactiveInterval(3000);
			// 7)Encaminhar para a tela de bem vindo
			request.getRequestDispatcher("WEB-INF/include/index.jsp").forward(request, response);
		} else {
			// Usuário não encontrado
			// 5)Mensagem e Redirecionamento para o login
			response.getWriter()
					.print("<script> alert('Usuário não encontrado!'); " + "location.href='login.html' </script>");
		}
	}

}
