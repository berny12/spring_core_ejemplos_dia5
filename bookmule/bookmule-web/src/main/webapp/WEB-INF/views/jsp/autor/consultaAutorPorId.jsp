<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/css/style.css'/>" type="text/css" rel="stylesheet"></link>
<title>Lista de autores</title>
</head>
<body>
  Consulta autor por id.
  <br>
  <br>
  <ul>
    <li>id: ${autor.id}</li>
    <li>Nombre: ${autor.nombre}</li>
    <li>Apellido Paterno: ${autor.apellidoPaterno}</li>
    <li>Apellido Materno: ${autor.apellidoMaterno}</li>
  </ul>
  <p>
    <a href="<c:url value='/'/>">Inicio</a>
  </p>
</body>
</html>