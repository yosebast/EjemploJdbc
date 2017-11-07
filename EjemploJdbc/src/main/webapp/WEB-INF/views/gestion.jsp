<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 Dear <strong>${user}</strong>, Welcome to Admin Page.
    <a href="<c:url value="/logout" />">Logout</a>
    
    
    <table>
		<thead>
			<tr>
				<sec:authorize access='hasRole("ROLE_ADMIN")'>
					<td><spring:message code="label.User.count" /></td>
				</sec:authorize>
				<td><spring:message code="label.User.username" /></td>
				<td><spring:message code="label.User.lastName"/></td>
				<td><spring:message code="label.User.email"/></td>
				<sec:authorize access='hasRole("ROLE_DBA")'>
				<td><spring:message code="label.User.active" /></td>
				</sec:authorize>
			</tr>
		</thead>
		<c:forEach var="user" items="${users}">
			<tr>
				<sec:authorize access='hasRole("ROLE_ADMIN")'>
					<td><spring:url var="showUrl" value="show/{id}">
							<spring:param name="id" value="${user.id}" />
						</spring:url> <a href="${showUrl}">${user.id}</a></td>
				</sec:authorize>
				<td>${user.usuario}</td>				
				<td>${user.lastName}</td>
				<td>${user.email}</td>
				<sec:authorize access='hasRole("ROLE_DBA")'>
				<td>${user.active}</td>
				</sec:authorize>
				
			</tr>
		</c:forEach>
	</table>
	
	<div>
	<!-- A continuacion la otra tabla donde se muestra la restriccion por url  es mas compleja pero muy practica   funciona asi:
	defino en SecurityConfiguration.java   lo siguiente   .antMatchers("/users/**").access("hasRole('ROLE_USER')")
	con esto lo que digo es que a la url /users/**     solo va a poder cceder los que tengan perfil User   por lo que si accedo a la pagina gestion logeandome como admin en la segunda
	tabla solo vere la columna email    por que el resto es solo para perfil user   y la ultima columna solo para los dba-->
	</div>
	
	 <table>
		<thead>
			<tr>
				<sec:authorize url='/users/show/*' >
					<td><spring:message code="label.User.count" /></td>				
				<td><spring:message code="label.User.username" /></td>
				<td><spring:message code="label.User.lastName"/></td>
				</sec:authorize>
				<td><spring:message code="label.User.email"/></td>
				<sec:authorize access='hasRole("ROLE_DBA")'>
				<td><spring:message code="label.User.active" /></td>
				</sec:authorize>
			</tr>
		</thead>
		<c:forEach var="user" items="${users}">
			<tr>
				<sec:authorize url='/users/show/*' >
					<td><spring:url var="showUrl" value="show/{id}">
							<spring:param name="id" value="${user.id}" />
						</spring:url> <a href="${showUrl}">${user.id}</a></td>
				
				<td>${user.usuario}</td>				
				<td>${user.lastName}</td>
				</sec:authorize>
				
				<td>${user.email}</td>
				<sec:authorize access='hasRole("ROLE_DBA")'>
				<td>${user.active}</td>
				</sec:authorize>				
			</tr>
		</c:forEach>
	</table>
	
	
	<div>AHOTA ESTOY DENTRO DE GESTION Y OBTENDRE UN USUARIO A TRAVES DE SU ID HACIENDO USO DEL METODO QUE ESTA SECURIZADO CON ROE_ADMIN   @Secured("ROLE_ADMIN")
	public User findUsuarioById(int id) {</div>
	
	<c:url var="UsuarioId" value="7" />	
	<a href="<spring:url value="/${UsuarioId}" />"><c:out value="ObtenerUsuario"></c:out></a>
			

</body>
</html>