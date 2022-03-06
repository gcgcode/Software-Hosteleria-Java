package model.query;

public class ViewComanda {

	private int idComanda;
	private String fecha;
	private String nombreProducto;
	private int idProducto;
	private int idCarta;
	private String nombreTipo;
	private int idTipo;
	private float precio;
	private String nombreCliente;
	private int idCliente;
	private String nombreEmpleado;
	private int idEmpleado;

	public ViewComanda(int idComanda, String fecha, String nombreProducto, int idProducto, int idCarta,
			String nombreTipo, int idTipo, float precio, String nombreCliente, int idCliente, String nombreEmpleado,
			int idEmpleado) {

		setIdComanda(idComanda);
		setFecha(fecha);
		setNombreProducto(nombreProducto);
		setIdProducto(idProducto);
		setIdCarta(idCarta);
		setNombreTipo(nombreTipo);
		setIdTipo(idTipo);
		setPrecio(precio);
		setNombreCliente(nombreCliente);
		setIdCliente(idCliente);
		setNombreEmpleado(nombreEmpleado);
		setIdEmpleado(idEmpleado);
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
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

	@Override
	public String toString() {
		return "ViewComanda [idComanda=" + idComanda + ", fecha=" + fecha + ", nombreProducto=" + nombreProducto
				+ ", idProducto=" + idProducto + ", nombreTipo=" + nombreTipo + ", idTipo=" + idTipo + ", precio="
				+ precio + ", nombreCliente=" + nombreCliente + ", idCliente=" + idCliente + ", nombreEmpleado="
				+ nombreEmpleado + ", idEmpleado=" + idEmpleado + "]";
	}

}
