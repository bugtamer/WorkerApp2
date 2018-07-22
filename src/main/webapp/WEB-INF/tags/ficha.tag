<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
  <section>
    <div class="col s12 m7">
      <div class="card horizontal">
        <div class="card-image">
          <img src="${manitas.avatar}" alt="${manitas.nombre} ${manitas.apellidos} ${manitas.profesion}">
        </div>
        <div class="card-stacked">
          <div class="card-content">
            <a href="#modal1" class="secondary-content location modal-trigger">
              <h5>${distancia} Km
                <i class="material-icons">location_on</i>
              </h5>
              <wa:distancia ubicacion2="${usuarioUbicacion}" ubicacion1="${manitasUbicacion}" /> <!-- UbicacionHelper: geolocalizacion -->
            <!--
              -->
              <input type="hidden" id="manitasLatitud" value="${manitasUbicacion.latitud}">
              <input type="hidden" id="manitasLongitud" value="${manitasUbicacion.longitud}">
            </a>
            <h4>${manitas.nombre} ${manitas.apellidos}</h4>
            <p>${manitas.profesion}</p>
            <a href="#" class="secondary-content points">
            <c:forEach var="i" begin="0" end="4">
              <i class='material-icons${manitas.mediaValoraciones > i ? "" : " no_active"}'>grade</i>
            </c:forEach>
              (${manitas.valoraciones.size()})</a>
          </div>

        </div>
      </div>
    </div>
    <ul class="collapsible">
      <li>
        <div class="collapsible-header">
          <i class="material-icons">star</i>Valoraciones</div>
        <div class="collapsible-body">
        <c:forEach items="${manitas.valoraciones}" var="val">
          <span>${val.comentario}</span><br>
        </c:forEach>
        </div>
      </li>

      <li>
        <div class="collapsible-header">
          <i class="material-icons">library_books</i>Experiencia</div>
        <div class="collapsible-body">
        <c:forEach items="${manitas.experiencia}" var="exp">
          <span>${exp}</span><br>
        </c:forEach>
        </div>
      </li>

      <li>
        <div class="collapsible-header">
          <i class="material-icons">school</i>Educación</div>
        <div class="collapsible-body">
        <c:forEach items="${manitas.educacion}" var="edu">
          <span>${edu}</span><br>
        </c:forEach>
        </div>
      </li>
    </ul>
  </section>