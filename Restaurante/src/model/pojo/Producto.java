package model.pojo;

import java.util.List;

public class Producto {

	private int idProducto;
	private String nombre;
	private int idTipo;

	public Producto(String nombre, int idTipo) {
		setNombre(nombre);
		setIdTipo(idTipo);
	}

	public Producto(int idProducto, String nombre, int idTipoProducto) {
		setIdProducto(idProducto);
		setNombre(nombre);
		setIdTipo(idTipoProducto);
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

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	List<Producto> listaProducto;

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", idTipo=" + idTipo + ", listaProducto="
				+ listaProducto + "]";
	}

}
