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
		// Se a sessa�a inda n�o foi expirada
		if (sessao != null) {
			// invalida a sess�o 
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
		// 2)Criar objeto usu�rio e adicionar os dados
		Usuario usu = new Usuario();
		usu.setLogin(login);
		usu.setSenha(senha);
		
		// 3) Criar objeto UsuarioDAO e Autenticar
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuAutenticado = usuarioDAO.autenticar(usu);
		// 4) Verificar se usu�rio foi encontrado
		if (usuAutenticado != null) {
			
			// Usu�rio encontrado
			// 5) Criar Sess�o
			HttpSession sessao = request.getSession();
			// 6) Adicionar objeto como atributo da sess�o
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			
			// Definindo um tempo para a Sess�o expirar
			sessao.setMaxInactiveInterval(3000);
			// 7)Encaminhar para a tela de bem vindo
			request.getRequestDispatcher("WEB-INF/include/index.jsp").forward(request, response);
		} else {
			// Usu�rio n�o encontrado
			// 5)Mensagem e Redirecionamento para o login
			response.getWriter()
					.print("<script> alert('Usu�rio n�o encontrado!'); " + "location.href='login.html' </script>");
		}
	}

}
