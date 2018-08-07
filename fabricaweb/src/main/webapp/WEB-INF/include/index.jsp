<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import ="br.com.fabricadeprogramador.entidade.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>home</title>
</head>
<body>
<a href="usucontroller.do?acao=ini">Página inicial</a>
<a href="usucontroller.do?acao=lis">Usuários </a>
<a href="#">Sair </a>

<%
Usuario usuario = (Usuario)session.getAttribute("usuAutenticado") ;
%>

Bem Vindo  <%=  usuario.getNome() %>
</body>
</html>