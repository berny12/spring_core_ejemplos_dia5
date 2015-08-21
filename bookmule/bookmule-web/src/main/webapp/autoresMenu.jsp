<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
  
   <h3>Menu Autores</h3>
    <p>
    <a href="<c:url value='/bookmule/autor/consultaAutores'/>">Consulta todos los autores</a>
  </p>
  <p> Consulta autor por id. </p>
  <form method="post" action="<c:url value='/bookmule/autor/consultaAutorPorId'/>">
   id: <input type="text" name="id"/><br/>
   <input type="submit" value="Consultar"/>
  </form>
  <p> Crea autor. </p>
   <form method="post" action="<c:url value='/bookmule/autor/creaAutor'/>">
   Nombre: <input type="text" name="nombre"/><br/>
   Apellido paterno:<input type="text" name="apellidoPaterno"/><br/>
   Apellido materno:<input type="text" name="apellidoMaterno"/><br/>
   <input type="submit" value="Crear"/>
  </form>
  
  <p>
    <a href="<c:url value='/'/>">Inicio</a>
  </p>
</body>
</html>
