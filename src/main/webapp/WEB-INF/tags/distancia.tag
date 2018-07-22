<%@ attribute name="ubicacion1" required="true" type="com.worker.models.Ubicacion" %>
<%@ attribute name="ubicacion2" required="true" type="com.worker.models.Ubicacion" %>

<h5>
	${(ubicacion1 != null) && (ubicacion2 != null) ? String.format("%,.1f km", ubicacion1.getDistanciaKilometrica( ubicacion2 )) : "Geo no habilitada"}
	<i class="material-icons">${(ubicacion1 != null) && (ubicacion2 != null) ? "location_on" : "location_off"}</i>
</h5>
