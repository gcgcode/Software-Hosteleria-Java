package model.query;

public class ViewCliente {

	private int idPersona;
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String domicilio;
	private String telefono;
	private String email;
	private String numTarjeta;
	private String provincia;
	private int idProvincia;
	private int idCliente;

	public ViewCliente(int idPersona, String dni, String nombre, String apellido1, String apellido2, String domicilio,
			String telefono, String email, String provincia, int idProvincia, String numTarjeta, int idCliente) {

		setIdPersona(idPersona);
		setDni(dni);
		setNombre(nombre);
		setApellido1(apellido1);
		setApellido2(apellido2);
		setDomicilio(domicilio);
		setTelefono(telefono);
		setEmail(email);
		setNumTarjeta(numTarjeta);
		setProvincia(provincia);
		setIdProvincia(idProvincia);
		setIdCliente(idCliente);
		
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "ViewCliente [idCliente=" + idPersona + ", dni=" + dni + ", nombre=" + nombre + ", apellido1="
				+ apellido1 + ", apellido2=" + apellido2 + ", domicilio=" + domicilio + ", telefono=" + telefono
				+ ", email=" + email + ", numTarjeta=" + numTarjeta + ", provincia=" + provincia + ", idProvincia="
				+ idProvincia + "]";
	}

}
