<%@ page import="com.worker.util.Notificacion" %>

<%
	request.setAttribute("titulo",       "Error");
	request.setAttribute("materialIcon", "sentiment_very_dissatisfied");
	request.setAttribute("mensaje",      "¡Oops!<br><b>Error</b>");
	request.setAttribute("urlDestino",   "buscar");
	request.setAttribute("javascript",   Notificacion.JAVASCRIPT);
%>

<jsp:directive.include file="/WEB-INF/jspf/notificacion.jspf" />