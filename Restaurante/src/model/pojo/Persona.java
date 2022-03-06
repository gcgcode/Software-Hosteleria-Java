package model.pojo;

import java.util.List;

public class Persona {

	private int idPersona;
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String domicilio;
	private String telefono;
	private String email;
	private int idProvincia;

	List<Persona> listaPersona;

	public Persona(String dni, String nombre, String apellido1, String apellido2, String domicilio, String telefono,
			String email, int idProvincia) {
		setDni(dni);
		setNombre(nombre);
		setApellido1(apellido1);
		setApellido2(apellido2);
		setDomicilio(domicilio);
		setTelefono(telefono);
		setEmail(email);
		setIdProvincia(idProvincia);
	}

	public Persona(int idPersona, String dni, String nombre, String apellido1, String apellido2, String domicilio,
			String telefono, String email, int idProvincia) {
		setIdPersona(idPersona);
		setDni(dni);
		setNombre(nombre);
		setApellido1(apellido1);
		setApellido2(apellido2);
		setDomicilio(domicilio);
		setTelefono(telefono);
		setEmail(email);
		setIdProvincia(idProvincia);
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono2) {
		this.telefono = telefono2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public List<Persona> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(List<Persona> listaPersona) {
		this.listaPersona = listaPersona;
	}
}
