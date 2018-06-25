<%@ attribute name="ubicacion1" required="true" type="com.worker.models.Ubicacion" %>
<%@ attribute name="ubicacion2" required="true" type="com.worker.models.Ubicacion" %>

<h5>${(ubicacion1 != null) && (ubicacion2 != null) ? String.format("%,.1fm", 1000 * ubicacion1.getDistanciaKilometrica( ubicacion2 )) : "Geo no habilitada"}</h5>
