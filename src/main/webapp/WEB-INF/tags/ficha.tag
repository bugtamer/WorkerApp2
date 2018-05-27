<%@ attribute name="manitas" required="true" type="com.worker.models.Manitas" %>
  <section>
    <div class="col s12 m7">
      <div class="card horizontal">
        <div class="card-image">
          <img src="${manitas.avatar}" alt="${manitas.nombre} ${manitas.apellidos} ${manitas.profesion}">
        </div>
        <div class="card-stacked">
          <div class="card-content">
            <a href="#modal1" class="secondary-content location modal-trigger">
              <h5>${manitas.mediaValoraciones}Km
                <i class="material-icons">location_on</i>
              </h5>
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
            en BNI® Business Network...</span>
        </div>
      </li>
      <li>
        <div class="collapsible-header">
          <i class="material-icons">school</i>Educación</div>
        <div class="collapsible-body">
          <span>APECED, AGENCIA LAIN ENTRALGO, APECED, APECED, APECED, APECED, APECED, Cruz Roja, APECED, FORMATIK, FORMATIK, IES
            infanta Elena, Euroinnova...</span>
        </div>
      </li>
      <li>
        <div class="collapsible-header">
          <i class="material-icons">library_books</i>Extracto</div>
        <div class="collapsible-body">
          <span>Actualmente me dedico por las mañanas en el proyecto de TUSECTORES y por las tardes y los fines de semana doy SESIONES
            DE ENTRENAMIENTO a...</span>
        </div>
      </li>
    </ul>
  </section>