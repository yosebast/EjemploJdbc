<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<%@ page session="false" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	<c:url var="loginUrl" value="pagGestion" />	
	<a href="<spring:url value="gestion/${loginUrl}" />"><c:out value="Gestiones"></c:out></a>	   
</h1>
	<sec:authorize access='hasRole("ROLE_ADMIN")'>
	<P>The time on the server is ${serverTime}. No se tiene que ver para nadie  por que a esta pagina se accede son logearse  y funciona correctamente </P>
	</sec:authorize>


	
</body>
</html>
