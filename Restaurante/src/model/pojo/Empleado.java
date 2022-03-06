package model.pojo;

import java.util.List;

public class Empleado  {
	
	private int idEmpleado;
	private String iban;
	private String nss;
	private int idPersona;
	private int idTipo;

	List<Empleado> listaEmpleado;

	public Empleado(String iban, String nss2, int tipo) {
		setIban(iban);
		setNss(nss2);
		setIdTipo(tipo);
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss2) {
		this.nss = nss2;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public List<Empleado> getListaEmpleado() {
		return listaEmpleado;
	}

	public void setListaEmpleado(List<Empleado> listaEmpleado) {
		this.listaEmpleado = listaEmpleado;
	}

	@Override
	public String toString() {
		return "Empleado [idEmpleado=" + idEmpleado + ", iban=" + iban + ", nss=" + nss + ", idPersona=" + idPersona
				+ ", idTipo=" + idTipo + ", listaEmpleado=" + listaEmpleado + "]";
	}

}
