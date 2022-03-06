package model.pojo;

import java.util.List;

public class Comanda {

	private int idComanda;
	private int idCliente;
	private int idEmpleado;
	private int idCarta;

	List<Comanda> listaComanda;

	public Comanda(int idComanda, int idCliente, int idCarta, int idEmpleado) {

		setIdComanda(idComanda);
		setIdCliente(idCliente);
		setIdCarta(idCarta);
		setIdEmpleado(idEmpleado);
	}

	public Comanda(int idCliente, int idCarta, int idEmpleado) {

		setIdCliente(idCliente);
		setIdCarta(idCarta);
		setIdEmpleado(idEmpleado);
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getIdCarta() {
		return idCarta;
	}

	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}

	public List<Comanda> getListaComanda() {
		return listaComanda;
	}

	public void setListaComanda(List<Comanda> listaComanda) {
		this.listaComanda = listaComanda;
	}

	@Override
	public String toString() {
		return "Comanda [idComanda=" + idComanda + ", idCliente="
				+ idCliente + ", idEmpleado=" + idEmpleado + ", idCarta=" + idCarta + ", listaComanda=" + listaComanda
				+ "]";
	}

}
