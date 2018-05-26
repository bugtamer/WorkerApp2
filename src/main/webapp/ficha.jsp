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

    <div id="modal1" class="modal">
      <div class="modal-content">
        <div id="map"></div>
      </div>
      <div class="modal-footer">
        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Cerrar</a>
      </div>
    </div>

  <section>
    <div class="col s12 m7">
      <div class="card horizontal">
        <div class="card-image">
          <img src="./imgs/Fina-entrenadora-personal.jpg" alt="Entrenadora Personal Fina">
        </div>
        <div class="card-stacked">
          <div class="card-content">
            <a href="#modal1" class="secondary-content location modal-trigger">
              <h5>0,3Km
                <i class="material-icons">location_on</i>
              </h5>
            </a>
            <h4>Fina Martí­nez</h4>
            <p>Entrenador Personal</p>
            <a href="#" class="secondary-content points">
              <i class="material-icons">grade</i>
              <i class="material-icons">grade</i>
              <i class="material-icons">grade</i>
              <i class="material-icons">grade</i>
              <i class="material-icons  no_active">grade</i>(20)</a>
          </div>

        </div>
      </div>
    </div>
    <ul class="collapsible">
      <li>
        <div class="collapsible-header">
          <i class="material-icons">directions_run</i>Actual</div>
        <div class="collapsible-body">
          <span>Entrenadora personal, estudiante y emprendedora en Uno mismo, Emprendedora en Proyecto nuevo, Entrenador personal
            de fitness en Entrenadora...</span>
        </div>
      </li>
      <li>
        <div class="collapsible-header">
          <i class="material-icons">history</i>Anterior</div>
        <div class="collapsible-body">
          <span>Monitora de sala, monitora de clases colectivas, recepcionista en Holidaygym, Staff en Virgin Active, Invitada
            en BNIÂ® Business Network...</span>
        </div>
      </li>
      <li>
        <div class="collapsible-header">
          <i class="material-icons">school</i>EducaciÃ³n</div>
        <div class="collapsible-body">
          <span>APECED, AGENCIA LAIN ENTRALGO, APECED, APECED, APECED, APECED, APECED, Cruz Roja, APECED, FORMATIK, FORMATIK, IES
            infanta Elena, Euroinnova...</span>
        </div>
      </li>
      <li>
        <div class="collapsible-header">
          <i class="material-icons">library_books</i>Extracto</div>
        <div class="collapsible-body">
          <span>Actualmente me dedico por las maÃ±anas en el proyecto de TUSECTORES y por las tardes y los fines de semana doy SESIONES
            DE ENTRENAMIENTO a...</span>
        </div>
      </li>
    </ul>
  </section>

  <!-- FOOTER PERSONALIZADO -->
  
  <footer>
    <div class="row">
      <div class="input-field col s12">
        <a href="./chat.jsp" class="waves-effect waves-light amber darken-2 btn-large">Contactar</a>
      </div>
    </div>
  </footer>

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="./js/materialize.js"></script>
  <script src="./js/init.js"></script>
  <script src="./js/sesion.js"></script>

  <script src="./js/map.js"></script>
  <script src="./js/script.js"></script>
  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAa4qz8zEC6ckcB9AnKDrwNZ-6mK4t2-4g&callback=initMap"></script>

</body>

</html>