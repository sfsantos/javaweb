<%@page import="javax.swing.border.Border"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="br.com.fabricadeprogramador.entidade.Usuario"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Usuario</title>
</head>
<body>
<%@include file="include/menu.jsp" %>
	<%
		//Capturando a lista do request

		List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");
	%>

	<table Border="1">
		<tr bgcolor="#eaeaea">
			<td>ID</td>
			<td>NOME</td>
			<td>LOGIN</td>
			<td>SENHA</td>
			<td>SELECIONAR</td>

		</tr>

		<%
			for (Usuario u : lista) {
		%>

		<tr>
			<td><%=u.getId()%></td>
			<td><%=u.getNome()%></td>
			<td><%=u.getLogin()%></td>
			<td><%=u.getSenha()%></td>
			<td><input type="checkbox" name="id" value="<%=u.getId() %>">
				<!--  <a href="usucontroller.do?acao=exc&id=<%=u.getId() %>">Excluir</a>-->
				<a href="usucontroller.do?acao=alt&id=<%=u.getId() %>">Alterar</a>
		</tr>

		<%
			}
		%>


	</table>
	
	<input type="button" value="EXCLUIR" disabled="disabled"
		onclick="location.href='usucontroller.do?acao=exc'"  />
	<input type="button" value="NOVO"
		onclick="location.href='usucontroller.do?acao=cad'" />
</body>
</html>