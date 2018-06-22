<%@ taglib tagdir="/WEB-INF/tags" prefix="wa"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
  <title>WorkerApp - Búsqueda</title>
  <link rel="shortcut icon" type="image/x-icon" href="./imgs/WorkerApp.logo.16x16.ico">

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="./css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="./css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="./css/search_result.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="./css/search.css" type="text/css" rel="stylesheet" media="screen,projection" />
</head>

<body>

  <wa:header mostrarBarraBusqueda="true" mostrarVideoBienvenida="false"></wa:header>

  <section>
    <div>
      <p id="resumenResultado" class="result"></p>
    </div>
    <ul id="listaResultados" class="collection"></ul>
  </section>

  <wa:footer></wa:footer>

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="./js/materialize.js"></script>
  <script src="./js/init.js"></script>
  <script src="./js/sesion.js"></script>
  <script src="./js/search.js"></script>
  <script src="./js/search_result.js"></script>
  <script src="./js/sugerencias.js"></script>
</body>

</html>