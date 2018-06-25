<%@ taglib tagdir="/WEB-INF/tags" prefix="wa"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
  <title>WorkerApp - Login</title>
  <link rel="shortcut icon" type="image/x-icon" href="./imgs/WorkerApp.logo.16x16.ico">

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="./css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="./css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
</head>

<body class="login">

  <wa:header mostrarBarraBusqueda="false" mostrarVideoBienvenida="false"></wa:header>

  <section>
    <div class="container">
      <div class="row">
        <div class="col s12 m12 l12">
          
          
          <div id="form-login">
            <form id="milogin" class="col s12" method="POST" action="./login">
              <wa:geolocation ubicacion="${geolocalizacion}" /> <!-- UbicacionHelper: geolocalizacion -->

              <div class="row">
                <div class="input-field col s12">
                  <label for="email">Correo</label>
                  <input type="email" name="email" value="manolo@workerapp.com" id="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
                    required>
                  <div id="mail_error">${String.format("<p class=\"error\">%s</p>", (error == null) ? "" : error )}</div>
                </div>
              </div>
              
              <div class="row">
                <div class="input-field col s12">
                  <label for="password">Contrase&ntilde;a</label>
                  <input type="password" name="password" id="password" value="123456" required>
                  <div id="pass_error"></div>
                </div>
              </div>
              
              <div class="row">
                <div class="col s12" id="submitBtn">
                  <!-- id aqui evitar que no capture el evento click erraticamente -->
                  <input type="submit" class="btn orange darken-4 waves-effect waves-light" value="Login">
                </div>
              </div>
            </form>
            
            
            <div class="row">
              <div class="col s5">
                <a href="./registro.jsp">Registrarse</a>
              </div>
              <div class="col s7">
                <a href="#">¿Has perdido tu contrase&ntilde;a?</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <wa:footer></wa:footer>

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="./js/materialize.js"></script>
  <script src="./js/init.js"></script>
  <script src="./js/login.js"></script>
  <script src="./js/geolocation.js"></script>
</body>

</html>