package model.query;

public class ViewProducto {

	private int id;
	private String nombreProducto;
	private String nombreTipo;
	private int idTipo;

	public ViewProducto(int id, String nombreProducto, String nombreTipo, int idTipo) {
		setId(id);
		setNombreProducto(nombreProducto);
		setNombreTipo(nombreTipo);
		setIdTipo(idTipo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	@Override
	public String toString() {
		return "ViewProducto [id=" + id + ", nombreProducto=" + nombreProducto + ", nombreTipo=" + nombreTipo
				+ ", idTipo=" + idTipo + "]";
	}
}
