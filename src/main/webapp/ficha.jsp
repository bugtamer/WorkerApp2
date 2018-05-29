<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.worker.models.Manitas" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="wa"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
  <title>Ficha Profesional WorkerAPP</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="./css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="./css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

</head>

<body class="ficha">

  <wa:header mostrarBarraBusqueda="false" mostrarVideoBienvenida="false"></wa:header>
  <wa:ficha-mapa-modal />
  <wa:ficha />
  <wa:ficha-footer />

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="./js/materialize.js"></script>
  <script src="./js/init.js"></script>

  <script src="./js/map.js"></script>
  <script src="./js/script.js"></script>
  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAa4qz8zEC6ckcB9AnKDrwNZ-6mK4t2-4g&callback=initMap"></script>

</body>

</html>