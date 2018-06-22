<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
  <title>WorkerApp - Confirmación ${titulo}</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="./css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="./css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
</head>
<body class="orange darken-4 message_page">

  <section>
    <p class="center-align">
      <i class="large material-icons white-text">${icono}</i>
    </p>
    <h4 class="center-align white-text">${xTuvoExito}</h4>
    <h5 class="center-align white-text">WorkerAPP</h5>
  </section>
  
  <input type="hidden" id="urlDestino" value="${urlDestino}">

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="./js/materialize.js"></script>
  <script src="./js/init.js"></script>
  <script src="./js/redireccionPostConfirmacion.js"></script>
</body>
</html>