<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="br.com.fabricadeprogramador.entidade.Usuario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Usuário</title>
</head>
<body>

	<% Usuario u = (Usuario)request.getAttribute("usu"); %>
<%@include file="include/index.jsp" %>
	<form method="post" action="usucontroller.do">
		Id: <input size="5" type="text" name="id" value="<%=u.getId()%>">
		Nome: <input type="text" name="nome" value="<%=u.getNome()%>">
		Login: <input type="text" name="login" value="<%=u.getLogin()%>">
		Senha: <input type="password" name="senha" value="<%=u.getSenha()%>">
		<input type="submit" value="SALVAR">

	</form>
</body>
</html>