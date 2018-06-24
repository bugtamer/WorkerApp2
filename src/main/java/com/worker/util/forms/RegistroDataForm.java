package com.worker.util.forms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.worker.models.Ubicacion;
import com.worker.models.Usuario;
import com.worker.persistence.UsuarioEM;

/**
 * Helper para tratar los datos del formulario de alta
 * de un nuevo usuario a partir del objeto request.
 * 
 * @author bugtamer
 */
public class RegistroDataForm {
	
	// ATRIBUTOS
	
	private Usuario nuevoUsuario;
	private String repass;
	private HttpServletRequest request;
	private boolean persistenciaNuevoUsuarioRechazada;
	
	
	
	// CONSTANTES
	
	// default values
	private static final String DEFAULT_AVATAR = "./imgs/hand_logo.svg";
	// regex
	public static final String EMAIL_REGEX = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
	public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,16})";
	// form errors
	public static final String NO_NOMBRE = "Introduce tu nombre";
	public static final String NO_APELLIDOS = "Introduce tus apellidos";
	public static final String NO_EMAIL = "Introduce un email";
	public static final String NO_PASSWORD = "Introduce un password";
	public static final String NO_REPASSWORD = "Confirma tu password";
	public static final String PASSWORD_MISMATCH = "Los passwords no coinciden";
	public static final String PASSWORD_WRONG_FORMAT = "Formato del password:<br>Entre 8 y 16 carácteres<br>Minúsculas<br>Mayúsculas<br>Números<br>Símbolos admitidos: !@#$%^&*";
	public static final String EMAIL_WRONG_FORMAT = "Introduce un email Válido";
	public static final String EMAIL_YA_REGISTRADO = "Ya existe un usuario registrado con el email proporcionado";
	
	
	
	// INSTANCIACION
	
	public RegistroDataForm(HttpServletRequest request) {
		this.request = request;
		nuevoUsuario = new Usuario();
		nuevoUsuario.setNombre( request.getParameter("name") );
		nuevoUsuario.setApellidos( request.getParameter("lastname") );
		nuevoUsuario.setEmail( request.getParameter("email") );
		nuevoUsuario.setPassword( request.getParameter("pass") );
		nuevoUsuario.setAvatar( request.getParameter("avatar") );
		repass = request.getParameter("repass");
		persistenciaNuevoUsuarioRechazada = false;
	}
	
	
	
	// SERVICIOS

	/**
	 * Devuelve la confirmación del password
	 * 
	 * @return String con la confirmación del password
	 */
	public String getRepassword() {
		return repass;
	}
	
	
	
	/**
	 * Convierte los datos del formulario de alta
	 * de un nuevo usuario a un modelo Usuario.
	 * 
	 * @return un Usuario con los datos proporcionados por el formulario
	 */
	public Usuario parseToUsuario() {
		setUbicacionMock(); // FIX cuando la app pueda adquirir la ubicacion
		String avatar = nuevoUsuario.getAvatar();
		if ((avatar == null) || avatar.isEmpty()) {
			nuevoUsuario.setAvatar(DEFAULT_AVATAR);
		}
		return nuevoUsuario;
	}
	
	
	
	/**
	 * Indica si hay errores en los datos proporcionados
	 * por el formulario de alta de un nuevo usuario
	 * 
	 * @return un booleano para indicar dicha condición 
	 */
	public boolean isValid() {
		boolean hasErrors = false;
		hasErrors |= nuevoUsuario.getNombre().isEmpty();
		hasErrors |= nuevoUsuario.getApellidos().isEmpty();
		hasErrors |= nuevoUsuario.getEmail().isEmpty();
		hasErrors |= nuevoUsuario.getPassword().isEmpty();
		hasErrors |= repass.isEmpty();
		hasErrors |= ! hasPattern(nuevoUsuario.getEmail(), EMAIL_REGEX);
		hasErrors |= ! nuevoUsuario.getPassword().equals(repass);
		hasErrors |= ! hasPattern(nuevoUsuario.getPassword(), PASSWORD_REGEX);
		hasErrors |= ! hasPattern(repass, PASSWORD_REGEX);
		return hasErrors;
	}
	
	
	
	/**
	 * Persiste los datos de un nuevo usuario en la base de datos.
	 * 
	 * @throws RuntimeException si ya existe un usuario registrado con el email proporcionado.
	 */
	public void persist() {
		boolean registrado = UsuarioEM.getInstance().save( parseToUsuario() );
		if (registrado == false) {
			persistenciaNuevoUsuarioRechazada = true;
			throw new RuntimeException( EMAIL_YA_REGISTRADO );
		}
	}
	
	
	
	/**
	 * Establece, en el objeto request, los mensajes de error
	 * para el formulario de alta de un nuevo usuario.
	 */
	public void setErrors() {
		// NOMBRE
		String sut = nuevoUsuario.getNombre(); // Subject Under Test
		String key = "errorNombre";
		if (sut.isEmpty()) {
			request.setAttribute(key, NO_NOMBRE);
		}
		// APELLIDOS
		sut = nuevoUsuario.getApellidos();
		key = "errorApellidos";
		if (sut.isEmpty()) {
			request.setAttribute(key, NO_APELLIDOS);
		}
		// EMAIL
		sut = nuevoUsuario.getEmail();
		key = "errorEmail";
		if (sut.isEmpty()) {
			request.setAttribute(key, NO_EMAIL);
		} else if (hasPattern(sut, EMAIL_REGEX) == false) {
			request.setAttribute(key, EMAIL_WRONG_FORMAT);
		}
		if (persistenciaNuevoUsuarioRechazada == true) {
			request.setAttribute(key, EMAIL_YA_REGISTRADO);
		}
		// PASSWORDS
		sut = nuevoUsuario.getPassword();
		key = "errorPass";
		if (sut.isEmpty()) {
			request.setAttribute(key, NO_PASSWORD);
		} else if (hasPattern(sut, PASSWORD_REGEX) == false) {
			request.setAttribute(key, PASSWORD_WRONG_FORMAT);
		}
		key = "errorRepass";
		if (repass.isEmpty()) {
			request.setAttribute(key, NO_REPASSWORD);
		} else if (repass.equals(sut) == false) {
			request.setAttribute(key, PASSWORD_MISMATCH);
		} else if (hasPattern(repass, PASSWORD_REGEX) == false) {
			request.setAttribute(key, PASSWORD_WRONG_FORMAT);
		}
	}
	
	
	
	// METODOS SOBREESCRITOS
	
	@Override
	public String toString() {
		String representacion = nuevoUsuario.toString();
		representacion = representacion.substring(0, representacion.length() - 1); // eliminar el carácter: '}'
		return String.format("%s, repass=%s}", representacion, repass);
	}
	
	
	
	// DETALLES DE IMPLEMENTACION
	
	/**
	 * <a href="http://www.regexplanet.com/advanced/java/index.html">Java regex method tester</a><br>
	 * <a href="https://java-regex-tester.appspot.com/">Java REGEX tester</a>
	 */
	private boolean hasPattern(String input, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(input);
		return m.find();
	}
	
	
	private void setUbicacionMock() {
		Ubicacion ubiMock = new Ubicacion(41.3, 2.4);
		nuevoUsuario.setUbicacion(ubiMock);
	}
	
}
