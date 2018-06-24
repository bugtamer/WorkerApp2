<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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


            <form id="registroForm" class="col s12" method="Post">

              <div class="row">
                <div class="input-field	col s6">
                  <label for="name">Nombre</label>
                  <input type="text" name="name" id="name" value="${revisaUsuario.nombre}" required>
                  <p id="nombre_msg" class="error">${errorNombre}</p>
                </div>
                
                <div class="input-field col s6">
                  <label for="lastname">Apellidos</label>
                  <input type="text" name="lastname" id="lastname" value="${revisaUsuario.apellidos}" required>
                  <p id="apellidos_msg" class="error">${errorApellidos}</p>
                </div>
              </div>

              <div class="row">
                <div class="input-field col s12">
                  <label for="email">Correo</label>
                  <input type="email" name="email" id="email" value="${revisaUsuario.email}" required>
                  <p id="email_msg" class="error">${errorEmail}</p>
                </div>
              </div>

              <div class="row">
                <div class="input-field col s12">
                  <label for="pass">Password</label>
                  <input type="password" name="pass" id="pass" value="${(revisaUsuario.password == null) || revisaUsuario.password.isEmpty() ? "@Ab12345" : revisaUsuario.password}" required>
                  <p id="pass_msg" class="error">${errorPass}</p>
                </div>
              </div>

              <div class="row">
                <div class="input-field col s12">
                  <label for="repass">Confirmar Password</label>
                  <input type="password" name="repass" id="repass" value="${(revisaRepass == null) || revisaRepass.isEmpty() ? "@Ab12345" : revisaRepass}" required>
                  <p id="repass_msg" class="error">${errorRepass}</p>
                </div>
              </div>
              
              <div class="row">
                <div class="col s12" id="register">
                  <input type="submit" class="btn orange darken-4 waves-effect waves-light" value="Registro">
                </div>
              </div>
            </form>


          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- <wa:footer></wa:footer> -->

<!-- Scripts -->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="./js/materialize.js"></script>
  <script src="./js/init.js"></script>
  <script src="./js/register.js"></script>
</body>

</html>