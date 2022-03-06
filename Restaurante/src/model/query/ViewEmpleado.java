package model.query;

public class ViewEmpleado {

	private int id;
	private int idEmpleado;
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String domicilio;
	private String telefono;
	private String email;
	private String provincia;
	private int idProvincia;
	private String iban;
	private String nss;
	private String tipoEmp;
	private int idTipoEmp;
	private int sal;

	public ViewEmpleado(int id, String dni, String nombre, String apellido1, String apellido2, String domicilio, String telefono,
			String email, String provincia, int idProvincia, String iban, String nss, String tipoEmp, int idTipoEmp, int sal, int idEmpleado) {
		
		setId(id);
		setDni(dni);
		setNombre(nombre);		
		setApellido1(apellido1);
		setApellido2(apellido2);
		setDomicilio(domicilio);
		setTelefono(telefono);
		setEmail(email);
		setProvincia(provincia);
		setIdProvincia(idProvincia);
		setIban(iban);
		setNss(nss);
		setTipoEmp(tipoEmp);
		setIdTipoEmp(idTipoEmp);
		setSal(sal);
		setIdEmpleado(idEmpleado);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
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

	public void setNss(String nss) {
		this.nss = nss;
	}

	public String getTipoEmp() {
		return tipoEmp;
	}

	public void setTipoEmp(String tipoEmp) {
		this.tipoEmp = tipoEmp;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	@Override
	public String toString() {
		return "ViewEmpleado [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", domicilio=" + domicilio + ", telefono=" + telefono + ", email=" + email + ", provincia="
				+ provincia + ", nss=" + nss + ", tipoEmp=" + tipoEmp + ", sal=" + sal + "]";
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public int getIdTipoEmp() {
		return idTipoEmp;
	}

	public void setIdTipoEmp(int idTipoEmp) {
		this.idTipoEmp = idTipoEmp;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

}
