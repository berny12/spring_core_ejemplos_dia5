<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/css/style.css'/>" type="text/css" rel="stylesheet"></link>
<title>Autores</title>
</head>
<body>
  Capturar los datos de nuevo cliente.
  <br>
  <br>
  <c:url value ="/bookmule/cliente/creaCliente" var="url"/>
  <!-- TODO  H) Completar los atributos del tag form -->
  <form:form method="POST" action="${url}" modelAttribute="cliente">
    Nombre*     : <form:input path="nombre"/> <form:errors cssClass="error" path="nombre"/><br>
    Ap Paterno* : <form:input path="apellidoPaterno"/> <form:errors cssClass="error" path="apellidoPaterno"/><br>
    Ap Materno  : <form:input path="apellidoMaterno"/> <form:errors cssClass="error" path="apellidoMaterno"/><br>
    Username*   : <form:input path="usuario"/> <form:errors cssClass="error" path="usuario"/><br>
    Password*   : <form:input path="password"/> <form:errors cssClass="error" path="password"/><br>
    Confirmar*  : <form:input path="confirmacionPassword"/> <form:errors cssClass="error" path="confirmacionPassword"/><br>
    RFC*        : <form:input path="rfc"/> <form:errors cssClass="error" path="rfc"/><br>
    Email*      : <!-- TODO I) Agregar el campo para el email -->
    			 <form:input path="email"/> <form:errors cssClass="error" path="email"/><br>
    Dirección*  : <form:input path="direccion"/> <form:errors cssClass="error" path="direccion"/><br>
    Telefono    : <form:input path="telefono"/> <form:errors cssClass="error" path="telefono"/><br>
    Tipo*       : <!-- J) Agregar el campo para el tipo de cliente, observar el manejo de Enums -->
                  <form:select path="tipoCliente">
                  
                  	<form:option value="SELECCIONE">SELECCIONE</form:option>
                  	<form:options/>
                  
                  </form:select>
                  
                  <input type="submit" value="Crear cliente"/>
  </form:form>
  
</body>
</html>