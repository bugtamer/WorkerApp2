<%@ taglib tagdir="/WEB-INF/tags" prefix="wa"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
  <title>WorkerApp - Registro</title>
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
          <div id="form-register">
            <form class="col s12" method="Post">

              <div class="row">
                <div class="input-field	col s6">
                  <label for="name">Nombre</label>
                  <input type="text" name="name" id="name" value="" required>
                  <div id="resultado1"></div>
                </div>
                <div class="input-field col s6">
                  <label for="lastname">Apellido</label>
                  <input type="text" name="lastname" id="lastname" value="" required>
                  <div id="resultado2"></div>
                </div>
              </div>
              <div class="row">
                <div class="input-field col s12">
                  <label for="email">Correo</label>
                  <input type="email" name="email" id="email" value="" required>
                  <div id="resultado3"></div>
                </div>
              </div>
              <div class="row">
                <div class="input-field col s12">
                  <label for="pass">Password</label>
                  <input type="password" name="pass" id="pass" value="" required>
                  <div id="resultado4"></div>
                </div>
              </div>
              <div class="row">
                <div class="input-field col s12">
                  <label for="repass">Confirmar Password</label>
                  <input type="password" name="repass" id="repass" value="" required>
                  <div id="resultado5"></div>
                </div>
              </div>
              <div class="row">
                <div class="col s12" id="register">
                  <input type="submit" class="btn orange darken-4 waves-effect waves-light" value="Registro">
                </div>
                <div id="pass_error"></div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>

  <wa:footer></wa:footer>

<!--   <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="./js/materialize.js"></script>
  <script src="./js/init.js"></script>
 <!--  <script src="./js/register.js"></script> --> -->
</body>

</html>