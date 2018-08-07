package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroAutenticador
 */
@WebFilter(filterName = "Autenticador",dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/FiltroAutenticador" })
public class FiltroAutenticador implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroAutenticador() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		///Casting do HttpServelt Request
		HttpServletRequest httpServletRequest = (HttpServletRequest)
		request;
		String url = httpServletRequest .getRequestURI();
		//Capturando Sessao
		HttpSession sessao = httpServletRequest .getSession();
		//Está logado ou tentando acessar uma recurso que é publico permito		sem estar logado? 
				if (sessao.getAttribute("usuAutenticado")!=null ||
		url.lastIndexOf("login.html")>-1 || url.lastIndexOf("autenticador.do") >-1 ){ chain.doFilter(request, response);
		//Permite o fluxo darequisicao
		}else{
		//redireciona para login
		((HttpServletResponse) response).sendRedirect("login.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
