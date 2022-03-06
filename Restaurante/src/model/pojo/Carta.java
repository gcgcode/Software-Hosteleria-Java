package model.pojo;

import java.util.List;

public class Carta {

	private int idCarta;
	private int disponible;
	private float pvp;
	private int idProducto;
	private int idRestaurante;

	List<Carta> listaCarta;

	public Carta(int idCarta, int disponible, float pvp, int idProducto, int idRestaurante) {
		setIdCarta(idCarta);
		setDisponible(disponible);
		setPvp(pvp);
		setIdProducto(idProducto);
		setIdRestaurante(idRestaurante);
	}

	public Carta(int disponible, float pvp, int idProducto, int idRestaurante) {
		setIdCarta(idCarta);
		setDisponible(disponible);
		setPvp(pvp);
		setIdProducto(idProducto);
		setIdRestaurante(idRestaurante);
	}

	public int getIdCarta() {
		return idCarta;
	}

	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}

	public int getDisponible() {
		return disponible;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	public float getPvp() {
		return pvp;
	}

	public void setPvp(float pvp) {
		this.pvp = pvp;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(int idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public List<Carta> getListaCarta() {
		return listaCarta;
	}

	public void setListaCarta(List<Carta> listaCarta) {
		this.listaCarta = listaCarta;
	}

	@Override
	public String toString() {
		return "Carta [idCarta=" + idCarta + ", disponible=" + disponible + ", pvp=" + pvp + ", idProducto="
				+ idProducto + ", idRestaurante=" + idRestaurante + ", listaCarta=" + listaCarta + "]";
	}

}
