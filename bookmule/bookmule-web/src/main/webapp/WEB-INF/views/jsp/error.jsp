<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página de error</title>
</head>
<body>
  Lo sentimos mucho, ha ocurrido un error no esperado en nuestro sistema.
  <br /> Contante al administrador para mayores detalles.
  <br /> Mensaje del error:
  <b>${message} </b>
  <br />
  <p>
    <a href="<c:url value='/'/>">Inicio</a>
  </p>
</body>
</html>