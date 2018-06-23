<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="wa"%>
<%@ page import="com.worker.models.Manitas" %>
<%@ page import="com.worker.models.Ubicacion" %>
<%
	boolean sonResultados = (boolean)request.getAttribute("sonResultados");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>WorkerApp - ${sonResultados ? "Resultados" : "Home"}</title>
<link rel="shortcut icon" type="image/x-icon" href="./imgs/WorkerApp.logo.16x16.ico">

<!-- CSS -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="./css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
<link href="./css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
<c:if test="sonResultados"><link href="./css/search_result.css" type="text/css" rel="stylesheet" media="screen,projection" /></c:if>
<link href="./css/search.css" type="text/css" rel="stylesheet" media="screen,projection" />
</head>

<body class="home">
	<!-- Mostrar el vidéo -->
	<wa:header mostrarBarraBusqueda="true" mostrarVideoBienvenida="${!sonResultados}"></wa:header>

	<!-- mostrar la búsqueda -->
	<% if(sonResultados == true){ %>
	<section>
		<div>
			<p id="resumenResultado" class="result">
			Se ${listaManitas.size() > 1 ? "han" : "ha"} encontrado 
            ${listaManitas.size()} ${listaManitas.size() > 1 ? "resultados": "resultado"} sobre
            <br><span>"${terminoBusqueda}"</span>
			</p>
		</div>
		<ul id="listaResultados" class="collection">
		
		
		<c:forEach var="manitas" items="${listaManitas}">
            <li class="collection-item avatar itemResultado">
                <div class="listaClickable">
                    <img src="${manitas.avatar}" alt="${manitas.nombre}, ${manitas.profesion}" class="circle">
                    <!-- <span class="title">${manitas.nombre}</span> -->
                    <p><b>${manitas.nombre}</b></p>
                    <p>${manitas.profesion}</p>
                    <a href="./ficha?id=${manitas.id}" class="secondary-content">
                        <h5>${String.format("%,.1f", 1000 * ubicacionUsuario.getDistanciaKilometrica( manitas.ubicacion ))}m</h5>
                        <i class="${manitas.mediaValoraciones > 0 ? "material-icons" : "material-icons no_active"}">grade</i>
                        <i class="${manitas.mediaValoraciones > 1 ? "material-icons" : "material-icons no_active"}">grade</i>
                        <i class="${manitas.mediaValoraciones > 2 ? "material-icons" : "material-icons no_active"}">grade</i>
                        <i class="${manitas.mediaValoraciones > 3 ? "material-icons" : "material-icons no_active"}">grade</i>
                        <i class="${manitas.mediaValoraciones > 4 ? "material-icons" : "material-icons no_active"}">grade</i>
                    </a>
                </div>
            </li>
		</c:forEach>
		
		
		</ul>
	</section>
	<%}else{ %>
	<section></section>
	<%} %>
	<wa:footer></wa:footer>
	
	
  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="./js/materialize.js"></script>
  <script src="./js/init.js"></script>
  <script src="./js/search.js"></script>
  <c:if test="sonResultados"><script src="./js/search_result.js"></script></c:if>
  <script src="./js/sugerencias.js"></script>
</body>
</html>