package model.pojo;

import java.util.List;

public class TipoProducto {

	private int idProducto;
	private String nombre;

	List<TipoProducto> listaTipoProducto;

	public TipoProducto(String nombre) {
		setNombre(nombre);
	}

	public TipoProducto(int id, String nombre) {
		setIdProducto(id);
		setNombre(nombre);
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<TipoProducto> getListaTipoProducto() {
		return listaTipoProducto;
	}

	public void setListaTipoProducto(List<TipoProducto> listaTipoProducto) {
		this.listaTipoProducto = listaTipoProducto;
	}

	@Override
	public String toString() {
		return "TipoProducto [idProducto=" + idProducto + ", nombre=" + nombre + ", listaTipoProducto="
				+ listaTipoProducto + "]";
	}

}
