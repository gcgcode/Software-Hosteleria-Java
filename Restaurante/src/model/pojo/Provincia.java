package model.pojo;

import java.util.List;

public class Provincia {
	
	private int idProvincia;
	private String nombre;
	
	List<Provincia> listaProvincias;

	public Provincia(int idProvincia, String nombre) {
		setIdProvincia(idProvincia);
		setNombre(nombre);
	}
	
	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Provincia> getListaProvincia() {
		return listaProvincias;
	}

	public void setListaProvincia(List<Provincia> listaProvincia) {
		this.listaProvincias = listaProvincia;
	}

	@Override
	public String toString() {
		return "Provincia [idProvincia=" + idProvincia + ", nombre=" + nombre
				+ "]";
	}
}
