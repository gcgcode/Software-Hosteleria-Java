package model.pojo;

import java.util.List;

public class Restaurante {
	
	private int idRestaurante;
	private String nif;
	private String nombre; 
	private String direccion; 
	private String telefono;
	
	List<Comanda> listaRestaurante;
	

	public Restaurante(String nif, String nombre, String direccion, String telefono) {
		setNif(nif);
		setNombre(nombre);
		setDireccion(direccion);
		setTelefono(telefono);
	}
	public Restaurante(int id, String nif, String nombre, String direccion, String telefono) {
		setIdRestaurante(id);
		setNif(nif);
		setNombre(nombre);
		setDireccion(direccion);
		setTelefono(telefono);
	}

	public int getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(int idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Comanda> getListaRestaurante() {
		return listaRestaurante;
	}

	public void setListaRestaurante(List<Comanda> listaRestaurante) {
		this.listaRestaurante = listaRestaurante;
	}

	
}
