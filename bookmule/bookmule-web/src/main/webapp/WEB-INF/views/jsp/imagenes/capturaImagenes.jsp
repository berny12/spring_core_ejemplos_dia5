<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Imagenes</title>
</head>
<body>
  <h3>Guardar imágenes</h3>
  <form action="<c:url value='/bookmule/imagenes/guardaImagenes'/>" enctype="multipart/form-data"
    method="post">
    Archivo: <input type="file" name="file" /> <br> <input type="submit" value="Guardar" /><br>
  </form>
</body>
</html>