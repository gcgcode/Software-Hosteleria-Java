package model.pojo;

import java.util.List;

public class TipoEmpleado {

	private int idTipo;
	private String nombre;
	private float sal;

	List<TipoEmpleado> listaTipoEmpleado;

	public TipoEmpleado(int idTipo, String nombre, float sal) {
		setIdTipo(idTipo);
		setNombre(nombre);
		setSal(sal);
	}

	public TipoEmpleado(String nombre, float sal) {
		setNombre(nombre);
		setSal(sal);
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idEmpleado) {
		this.idTipo = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<TipoEmpleado> getListaTipoEmpleado() {
		return listaTipoEmpleado;
	}

	public void setListaTipoEmpleado(List<TipoEmpleado> listaTipoEmpleado) {
		this.listaTipoEmpleado = listaTipoEmpleado;
	}

	@Override
	public String toString() {
		return "TipoEmpleado [idTipo=" + idTipo + ", nombre=" + nombre + ", salario " + sal + ", listaTipoEmpleado="
				+ listaTipoEmpleado + ", " + "]";
	}

	public float getSal() {
		return sal;
	}

	public void setSal(float sal2) {
		this.sal = sal2;
	}

}
