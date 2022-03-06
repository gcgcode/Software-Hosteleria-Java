package model.query;

public class ViewCarta {

	private int idCarta;
	private String nombreRestaurante;
	private int idRestaurante;
	private String nombreProducto;
	private int idProducto;
	private int disponible;
	private float pvp;

	public ViewCarta(int idCarta, String nombreRestaurante, int idRestaurante, String nombreProducto, int idProducto,
			int disponible, float pvp) {
		setIdCarta(idCarta);
		setDisponible(disponible);
		setPvp(pvp);
		setNombreProducto(nombreProducto);
		setIdProducto(idProducto);
		setNombreRestaurante(nombreRestaurante);
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

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String idProducto) {
		this.nombreProducto = idProducto;
	}

	public String getNombreRestaurante() {
		return nombreRestaurante;
	}

	public void setNombreRestaurante(String idRestaurante) {
		this.nombreRestaurante = idRestaurante;
	}

	public int getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(int idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

}
