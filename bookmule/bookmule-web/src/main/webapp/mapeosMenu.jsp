<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>

  <h3>Menu Mapeos</h3>

  <p>
   <!-- el tag c pone el contextroot en -->
    <a href="<c:url value='/bookmule/mapeos/path'/>">Match por path</a>
  </p>
  <p>
    <a href="<c:url value='/bookmule/mapeos'/>">Match por método http</a>
  </p>
  <p>
    <a href="<c:url value='/bookmule/mapeos?foo=fooValue&bar=barValue'/>">Match por parametros </a>
  </p>
  <p>
    <a href="<c:url value='/bookmule/mapeos/headers?headers=true'/>">Mapeo por header </a>
  </p>
  <p>
    <a href="<c:url value='/bookmule/mapeos/variables/1/2.0/x'/>">Mapeo por uri template </a>
  </p>
  <p>
    <a href="<c:url value='/bookmule/mapeos/matrix/x=20;y=8;'/>">Mapeo por matrix variable </a>
  </p>
  <p>
    <a href="<c:url value='/bookmule/mapeos/matrixMap/x=20;x=-20;x=-50;y=8;z=20;'/>">Mapeo por
      marix variable wih a map </a>
  </p>
  <p>
    <a href="<c:url value='/'/>">Inicio</a>
  </p>
</body>
</html>
