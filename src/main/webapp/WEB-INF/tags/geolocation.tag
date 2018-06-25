<%@ attribute name="ubicacion" required="true" type="com.worker.models.Ubicacion" %>

<div>
	<input id="latitud"  name="latitud"  type="hidden" value="${(ubicacion == null) ? "" : ubicacion.latitud}" />
	<input id="longitud" name="longitud" type="hidden" value="${(ubicacion == null) ? "" : ubicacion.longitud}" />
</div>
