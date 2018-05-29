<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
  <title>WorkerAPP</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="./css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="./css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="./css/search.css" type="text/css" rel="stylesheet" media="screen,projection" />
</head>

<body class="home">
  <header>
    <video autoplay loop id="video-background" muted plays-inline>
      <source src="./multimedia/Chess_Street.mp4" type="video/mp4">
    </video>

    <div class="title">
      <img src="./imgs/hand_logo.svg" alt="WorkerAPP">
      <h1>Worker APP</h1>
      <p>(Made by
        <a href="#" target="_blank">Big Bang Team</a>,
        <br> para hacerte la vida más fácil)</p>
    </div>

    <nav class="orange darken-4" role="navigation">
      <div class="nav-wrapper container">
        <a id="logo-container" href="./index.jsp" class="brand-logo">WorkerAPP</a> 
        <ul class="right user_header">
          <li>
            <a href="#">
              <img src="./imgs/no_user.png" alt="User" class="circle">
            </a>
          </li>
        </ul>

        <ul class="right hide-on-med-and-down"  aria-label="Menú Desktop">

          <li>
            <a href="#">Inicio</a>
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
            <a href="./index.jsp">Inicio</a> 
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

    <div class="row search">
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

  <section></section>

  <footer class="page-footer orange darken-4">
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
  <script src="./js/sugerencias.js"></script>
  <script src="./js/search.js"></script>
</body>

</html>