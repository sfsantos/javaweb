package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet(name = "UsuarioControler",displayName ="UsuarioControler", urlPatterns = {"/usucontroller.do"})
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsuarioController() {
		System.out.println("Chamando o Construtor do servlet");

	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		System.out.println("Iniciando o servlet");

	}
	
	@Override
	public void destroy() {
		System.out.println("Finaliza o servlet");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Captura a ação
		String acao = request.getParameter("acao");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		//Testa se a ação foi informada
		if(acao !=null && !acao.isEmpty()) {
						
			
			if(acao.equals("exc")) {
				//Captura 0 id a ser excluido
				String ids[] = request.getParameterValues("id");
				for(String id:ids) {
				                                    //Valida se o id for informado
				                                //if(id != null && !id.isEmpty()) {
					
					//Cria o Objeto Usuario para passar
					//ao método do excluir do UsuarioDao
					
					Usuario usuExc = new Usuario();
	                // Convertendo e setando o id		
					usuExc.setId(Integer.parseInt(id));
					usuarioDAO.excluir(usuExc);
					
					
					response.sendRedirect("usucontroller.do?acao=lis");
					
				}
			}else if( acao.equals("lis")) {
				
				
				//Captura a lista de usuários do banco
				List<Usuario> lista = usuarioDAO.buscarTodos();
				
				// Adiciona a lista no request
				request.setAttribute("lista", lista);
				
				//Encaminha o request e o response para o JSP
				request.getRequestDispatcher("WEB-INF/listausuario.jsp").forward(request, response);
				
				
			}else if(acao.equals("alt")) {
				
				//Captura o parametro da tela
				String id = request.getParameter("id");
				// busca do banco o usuario pelo id vindo do parametro
				Usuario usuAlt = usuarioDAO.buscar(Integer.parseInt(id));
				
				// Adcionando objeto usuario no request para encaminhar para tela
				request.setAttribute("usu", usuAlt);
				//Encaminhar para tela de alteração
				request.getRequestDispatcher("WEB-INF/frmusuario.jsp").forward(request, response);
			}else if(acao.equals("cad")) {
				Usuario usuCad =new Usuario();
				
				usuCad.setId(0);
				usuCad.setNome("");
				usuCad.setLogin("");
				usuCad.setSenha("");
				
				request.setAttribute("usu", usuCad);
				
				request.getRequestDispatcher("WEB-INF/formusuario.jsp").forward(request, response);
			}else if(acao.equals("ini")) {
				
				
				request.getRequestDispatcher("WEB-INF/include/index.jsp").forward(request, response);
		}
		
		
		
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Capturando os parametros da tela
		        String id = request.getParameter("id");
				String nome = request.getParameter("nome");
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				
				Usuario usuario = new Usuario();
				if(id != null && id !="0" && !id.isEmpty()) {
					usuario.setId(Integer.parseInt(id));
					
				}
				
				usuario.setNome(nome);
				usuario.setLogin(login);
				usuario.setSenha(senha);
				
				UsuarioDAO usuDao = new UsuarioDAO();
				
				usuDao.salvar(usuario);
				String menssagem ="<script>"+
				"alert('Salvo com sucesso!'); "+
						"location.href='usucontroller.do?acao=lis';"+
						"</script>";
				response.getWriter().print(menssagem);
				
		
	}

}
