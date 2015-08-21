<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/css/style.css'/>" type="text/css" rel="stylesheet"></link>
<title>Cliente</title>
</head>
<body>
  Cliente creado con exito
  <br>
  <br>
  <ul>
    <li>id: ${cliente.id}</li>
    <li>Nombre: ${cliente.nombre}</li>
    <li>Apellido Paterno: ${cliente.apellidoPaterno}</li>
    <li>Apellido Materno: ${cliente.apellidoMaterno}</li>
    <li>Email: ${cliente.email}</li>
    <li>Username: ${cliente.usuario}</li>
    <li>RFC: ${cliente.rfc}</li>
  </ul>
  <p>
    <a href="<c:url value='/'/>">Inicio</a>
  </p>
</body>
</html>