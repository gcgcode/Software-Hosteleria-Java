package model.pojo;

import java.util.List;

public class Cliente {

	private int idCliente;
	private String numTarjeta;
	private int idPersona;

	List<Cliente> listaCliente;

	public Cliente(int idCliente, String numTarjeta, int idPersona) {
		setIdCliente(idCliente);
		setNumTarjeta(numTarjeta);
		setIdPersona(idPersona);
	}

	public Cliente(String numTarjeta) {

		setNumTarjeta(numTarjeta);

	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", numTarjeta=" + numTarjeta + ", idPersona=" + idPersona
				+ ", listaCliente=" + listaCliente + "]";
	}

}
