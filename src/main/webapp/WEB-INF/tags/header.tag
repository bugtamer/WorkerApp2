<%@ attribute name="mostrarBarraBusqueda" required="true" type="java.lang.Boolean" %>
<%@ attribute name="mostrarVideoBienvenida" required="true" type="java.lang.Boolean" %>
  <header>
  
	<% if(mostrarVideoBienvenida) { %>
		<jsp:directive.include file = "/WEB-INF/jspf/video-bienvenida.jspf" />
	<% } %>

    <!-- Navegación (INI) -->
    
    <nav  class="orange darken-4" role="navigation">
      <div class="nav-wrapper container">
      
        <a id="logo-container" href="./index.jsp" class="brand-logo">WorkerAPP</a>
        <ul class="right user_header">
          <li>
            <a href="#">
              <img src="${usuario == null ? "./imgs/no_user.png" : "./imgs/Alexandra-entrenadora-personal.jpg"}" alt="user" class="circle">
            </a>
          </li>
        </ul>
        <ul class="right hide-on-med-and-down" aria-label="Menú Desktop">
          <li>
            <a href="./index.jsp">Inicio</a>
          </li>
          <li>
            <a href="${usuario == null ? "./login" : "./logout"} ">${usuario == null ? "Login":"Logout"}</a>
          </li>
          <li>
            <a href="./registro.jsp">Registro</a>
          </li>
          <li>
            <a href="./ticket.jsp">Último Ticket</a>
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
            <a href="${usuario == null ? "./login" : "./logout"} ">${usuario == null ? "Login":"Logout"}</a>
          </li>
          <li>
            <a href="./registro.jsp">Registro</a>
          </li>
          <li>
            <a href="./ticket.jsp">Último Ticket</a>
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
    
    <!-- Navegación (FIN) -->
	
	
	<% if(mostrarBarraBusqueda) { %>
		<jsp:directive.include file = "/WEB-INF/jspf/search.jspf" />
	<% } %>

  </header>
