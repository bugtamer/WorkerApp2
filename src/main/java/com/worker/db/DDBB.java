package com.worker.db;

import java.util.ArrayList;
import java.util.List;

import com.worker.models.Manitas;
import com.worker.models.Mensaje;
import com.worker.models.Usuario;
import com.worker.util.database.DataRetriever;


public class DDBB {
	
	// ATRIBUTOS
	
	private static DDBB singletonDatabase;
	private static List<Usuario> usuarios;
	private static List<Manitas> manitas;
	private static List<Mensaje> mensajes;
	
	
	
	// INSTANCIACION
	
	private DDBB () {
		usuarios = DataRetriever.getUsuarios();
		manitas  = DataRetriever.getManitas();
		mensajes = new ArrayList<>(); // FIX verificar JSON: DataRetriever.getMensajes();
	}
	
	
	public static DDBB getInstance() {
		if (singletonDatabase == null) {
			singletonDatabase = new DDBB();
		}
		return singletonDatabase;
	}
	
	
	
	// GETTERS
	
	public List<Manitas> getResultadosBusqueda(String query) {
		List<Manitas> resultados = new ArrayList<>();
		if (query != null  &&  !query.equals("")) {
			for(Manitas u : manitas ) {
				if (u.getProfesion().toUpperCase().contains(query.toUpperCase())) {
					resultados.add(u);
				}
			}
		}
		return resultados;
	}
	
	public Usuario getUsuario(int id) {
		Usuario encontrado = null;
		for (Usuario u : usuarios) {
			if (u.getId() == id) {
				encontrado = u;
				break;
			}
		}
		return encontrado;
	}
	
	public Usuario getUsuarios(String email, String pass) {
		Usuario unUsuario = null;
		for (Usuario user : usuarios) {
			if (user.getEmail().equals(email) && user.getPassword().equals(pass)) {
				unUsuario = user;
				break;
			}
		}
		System.out.println("getUsuarios(String '"+email+"', String '"+pass+"')=" + unUsuario);
		return unUsuario;
	}
	
	

	
	public Manitas getManitas(int id) {
		Manitas encontrado = null;
		for (Manitas m : manitas) {
			if (m.getId() == id) {
				encontrado = m;
				break;
			}
		}
		return encontrado;
	}
	
	
	public List<Mensaje> getConversacion(Usuario emisor, Usuario receptor, int nUltimos) {
		List<Mensaje> conversacion = new ArrayList<>();
		for (Mensaje mnjCandidato : mensajes) {
			boolean esElEmisorDeseado   = (mnjCandidato.getEmisor().getId()   == emisor.getId());
			boolean esElReceptorDeseado = (mnjCandidato.getReceptor().getId() == receptor.getId());
			if (esElEmisorDeseado && esElReceptorDeseado) {
				conversacion.add(mnjCandidato);
			}
		}
		int totalMensajes = conversacion.size();
		int inicio = totalMensajes - nUltimos;
		return conversacion.subList(inicio, totalMensajes);
	}
	
	
	
	// ADDERS
	
	public boolean addManitas(Manitas nuevoManitas) {
		boolean outcome = false;
		if (nuevoManitas != null) {
			manitas.add(nuevoManitas);
		}
		return outcome;
	}
	
	public boolean addMensaje(Mensaje nuevoMensaje) {
		System.out.println("DDBB.addMensaje()=" + nuevoMensaje);
		return mensajes.add(nuevoMensaje);
	}
	
}
