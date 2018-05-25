<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
  <title>Resultados de Búsqueda WorkerAPP</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="./css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="./css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="./css/search_result.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="./css/search.css" type="text/css" rel="stylesheet" media="screen,projection" />
</head>

<body>

  <header aria-header role="contentinfo">

    <nav class="orange darken-4" role="navigation">
      <div class="nav-wrapper container">
        <a id="logo-container" href="./index.html" class="brand-logo">WorkerAPP</a>
        <ul class="right user_header">
          <li>
            <a href="#">
              <img src="./imgs/no_user.png" alt="user" class="circle">
            </a>
          </li>
        </ul>

        <ul class="right hide-on-med-and-down" aria-label="Menú Desktop">

          <li>
            <a href="./index.html">Inicio</a>
          </li>
          <li>
            <a class="conmutadorLog" href="./login.html">Login</a>
          </li>
          <li>
            <a href="./registro.html">Registro</a>
          </li>
          <li>
            <a href="./ticket.html">Último Ticket</a>
          </li>
          <li>
            <a href="#">Histórico Tickets</a>
          </li>
          <li>
            <a href="#">Mis Favoritos</a>
          </li>
        </ul>

        <ul id="nav-mobile" class="sidenav" aria-label="Menú Mobile">

          <li>
            <a href="./index.html">Inicio</a>
          </li>
          <li>
            <a class="conmutadorLog" href="./login.html">Login</a>
          </li>
          <li>
            <a href="./registro.html">Registro</a>
          </li>
          <li>
            <a href="./ticket.html">Último Ticket</a>
          </li>
          <li>
            <a href="#">Histórico Tickets</a>
          </li>
          <li>
            <a href="#">Mis Favoritos</a>
          </li>

        </ul>

        <a href="#" data-target="nav-mobile" class="sidenav-trigger">
          <i class="material-icons">menu</i>
        </a>
      </div>
    </nav>

    <div class="row search" role="search">
      <form class="col s12" id="search_form">
        <div class="row">
          <div class="input-field col s12">
            <input id="icon_prefix2" type="search" required autofocus>
            <label for="icon_prefix2">Buscar</label>
            <a href="#" id="search_button">
              <i class="material-icons prefix">search</i>
            </a>
          </div>
        </div>
      </form>
    </div>
    <div id="sugerencias"></div>
  </header>

  <section>
    <div>
      <p id="resumenResultado" class="result"></p>
    </div>
    <ul id="listaResultados" class="collection"></ul>
  </section>

  <footer class="page-footer orange darken-4" aria-footer role="contentinfo">

    <div class="footer-copyright">
      <div class="container">
        Made by
        <a class="white-text text-lighten-3" href="#">BIG BANG Team</a>
      </div>
    </div>
  </footer>

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