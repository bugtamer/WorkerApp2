<%@ taglib tagdir="/WEB-INF/tags" prefix="wa"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
  <title>WorkerApp - Chat</title>
  <link rel="shortcut icon" type="image/x-icon" href="./imgs/WorkerApp.logo.16x16.ico">

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="css/chat.css" type="text/css" rel="stylesheet" media="screen,projection" />
</head>

<body>

  <wa:header mostrarBarraBusqueda="false" mostrarVideoBienvenida="false"></wa:header>
  <jsp:directive.include file="/WEB-INF/jspf/chat.jspf" />

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
  <script src="./js/sesion.js"></script>
  <script src="./js/chat.js"></script>
</body>

</html>