package controller.ctrlCliente;

import java.sql.SQLException;

import java.util.List;

import model.pojo.Cliente;
import model.pojo.Mensaje;
import model.pojo.Persona;
import model.pojo.Provincia;
import model.query.ViewCliente;
import utils.Pantalla;
import utils.Teclado;

public class ClienteCtrl {

	public static void insert() {
		String dni = pedirDni();
		String nombre = pedirNombre();
		String apellido1 = pedirApellido1();
		String apellido2 = pedirApellido2();
		String domicilio = pedirDomicilio();
		String telefono = pedirTelefono();
		String email = pedirEmail();
		Integer idProvincia = pedirProvincia();

		String numTarjeta = pedirNumTarjeta();

		Persona p = new Persona(dni, nombre, apellido1, apellido2, domicilio, telefono, email, idProvincia);
		Cliente c = new Cliente(numTarjeta);

		try {
			logic.logCliente.ClienteLog.insert(p, c);
			Pantalla.insertOk();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2003) {
				Pantalla.error20003();
			} else {
				Pantalla.insertError();
			}
		}

	}

	public static void update() {
		char confirm;

		List<ViewCliente> lstCliente = listarClientes();

		if (lstCliente.size() > 0) {

			int choice = pedirElementoCliente(Mensaje.CLIENTE_MENU1_INFO2, lstCliente);

			int idPersona = lstCliente.get(choice - 1).getIdPersona();

			String dni = pedirDni();
			String nombre = pedirNombre();
			String apellido1 = pedirApellido1();
			String apellido2 = pedirApellido2();
			String domicilio = pedirDomicilio();
			String telefono = pedirTelefono();
			String email = pedirEmail();
			int idProvincia = pedirProvincia();

			String numTarjeta = pedirNumTarjeta();

			Persona p = new Persona(idPersona, dni, nombre, apellido1, apellido2, domicilio, telefono, email,
					idProvincia);
			Cliente c = new Cliente(numTarjeta);

			do {
				confirm = Teclado.leerChar(Mensaje.CONFIRM_UPDATE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarUpdate(p, c);
					break;
				case 'N':
				case 'n':
					Pantalla.cancelMod();
					break;
				default:
					Pantalla.error();
					break;
				}

			} while (confirm != 'S' && confirm != 's' && confirm != 'N' && confirm != 'n');
		}
	}

	private static void ejecutarUpdate(Persona p, Cliente c) {
		try {

			logic.logCliente.ClienteLog.update(p, c);
			Pantalla.updateOk();
		} catch (SQLException sqle) {
			int errorCode = sqle.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.updateError();
			}
		}
	}

	public static void delete() {
		char confirm;
		int choice;
		int idPersona;
		List<ViewCliente> lstCliente = listarClientes();
		if (lstCliente.size() > 0) {

			choice = pedirCliente(lstCliente);

			idPersona = lstCliente.get(choice - 1).getIdPersona();

			do {
				
				Pantalla.mensajeError(Mensaje.AVISO_DELETE_CLIENTE);
				confirm = Teclado.leerChar(Mensaje.CONFIRM_DELETE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarDelete(idPersona);
					break;
				case 'N':
				case 'n':
					Pantalla.cancelDel();
					break;
				default:
					Pantalla.error();
					break;
				}
			} while (confirm != 'S' && confirm != 's' && confirm != 'N' && confirm != 'n');
		}
	}

	private static void ejecutarDelete(int idPersona) {
		try {
			logic.logCliente.ClienteLog.delete(idPersona);
			Pantalla.deleteOk();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.deleteError();
			}
		}

	}

	private static List<ViewCliente> listarClientes() {
		List<ViewCliente> lstCliente = null;

		try {
			lstCliente = logic.logCliente.ClienteLog.getClientes();
			if (lstCliente.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstCliente);
			}

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}

		return lstCliente;
	}

	public static void list(List<ViewCliente> lstCliente) {

		Pantalla.mensajeEspaciado(Mensaje.CLIENTE_MENU1_TITULO2);
		if (lstCliente.size() == 0) {
			Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);

		} else {
			for (int i = 0; i < lstCliente.size(); i++) {
				System.out.println("  " + (i + 1) + ". " + lstCliente.get(i).getDni() + "\t"
						+ lstCliente.get(i).getNombre() + "\t" + lstCliente.get(i).getApellido1() + "\t"
						+ lstCliente.get(i).getApellido2() + "\t" + lstCliente.get(i).getDomicilio() + "\t"
						+ lstCliente.get(i).getTelefono() + "\t" + lstCliente.get(i).getEmail() + "\t"
						+ lstCliente.get(i).getProvincia() + "\t" + lstCliente.get(i).getNumTarjeta());
			}
			Pantalla.selectOk();
		}
	}

	public static void list() {
		List<ViewCliente> lstCliente = null;

		try {
			lstCliente = logic.logCliente.ClienteLog.getClientes();
			if (lstCliente.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstCliente);
			}

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}

	}

	public static void listPorNombre() {

		String nombre = pedirNombre();

		List<ViewCliente> lstCliente = null;

		try {
			lstCliente = logic.logCliente.ClienteLog.listarPorNombre(nombre);
			if (lstCliente.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstCliente);
			}

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}
	}

	private static List<Provincia> listarProvincias() {
		List<Provincia> lstProvincia = null;
		try {
			lstProvincia = logic.logProvincia.ProvinciaLog.getProvincias();

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}
		return lstProvincia;
	}

	// --------------------------------------------------------------------------------

	private static int pedirCliente(List<ViewCliente> lstCliente) {
		int choice;
		Pantalla.mensaje("");
		do {
			choice = Teclado.leerInt(Mensaje.CLIENTE_MENU1_INFO3);
		} while (choice <= 0 || choice > lstCliente.size());
		return choice;
	}

	private static int pedirElementoCliente(String cad, List<ViewCliente> lista) {
		int choice;
		Pantalla.mensaje("");
		do {
			choice = Teclado.leerInt(cad);
		} while (choice <= 0 || choice > lista.size());
		return choice;
	}

	private static String pedirDni() {

		String str;

		boolean correcto = false;
		char c;

		do {

			Pantalla.mensaje("");
			str = Teclado.leerString(Mensaje.PERSONA_MENU1_INFO1);
			if (str.length() == 9) {

				// Comprobar 8 primeros digitos del DNI (deben ser numéricos)
				for (int i = 0; i < 8; i++) {
					c = str.charAt(i);

					if (c >= 48 && c <= 57) {
						correcto = true;
					} else {
						correcto = false;
						break;
					}
				}

				// Comprobación último digito del DNI (debe ser caracter)
				c = str.charAt(8);

				if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
					correcto = true;
				} else {
					correcto = false;
					Pantalla.mensajeError(Mensaje.PERSONA_MENU1_ERROR5);
					break;
				}
			} else {
				Pantalla.mensajeError(Mensaje.PERSONA_MENU1_ERROR6);
			}

		} while (str.length() != 9 || !correcto);

		return str;

	}

	private static String pedirNombre() {

		String str;

		do {

			str = Teclado.leerString(Mensaje.PERSONA_MENU1_INFO2);

			if (str.length() == 0 || str.length() > 25) {
				Pantalla.mensajeError(Mensaje.PERSONA_MENU1_ERROR1);
			}

		} while (str.length() == 0 || str.length() > 25);

		return str;
	}

	private static String pedirApellido1() {

		String str;

		do {

			str = Teclado.leerString(Mensaje.PERSONA_MENU1_INFO3);

			if (str.length() == 0 || str.length() > 25) {
				Pantalla.mensajeError(Mensaje.PERSONA_MENU1_ERROR1);
			}

		} while (str.length() == 0 || str.length() > 25);

		return str;

	}

	private static String pedirApellido2() {

		String str;

		do {

			str = Teclado.leerString(Mensaje.PERSONA_MENU1_INFO4);

			if (str.length() == 0 || str.length() > 25) {
				Pantalla.mensajeError(Mensaje.PERSONA_MENU1_ERROR1);
			}

		} while (str.length() == 0 || str.length() > 25);

		return str;
	}

	private static String pedirDomicilio() {

		String str;

		do {

			str = Teclado.leerString(Mensaje.PERSONA_MENU1_INFO5);

			if (str.length() > 20) {
				Pantalla.mensaje(Mensaje.PERSONA_MENU1_ERROR4);
			}

		} while (str.length() > 20);

		return str;

	}

	private static String pedirTelefono() {

		String tel;
		char c;
		boolean correcto = false;

		do {

			tel = Teclado.leerString(Mensaje.PERSONA_MENU1_INFO6);

			if (tel.length() == 9) {

				for (int i = 0; i < 9; i++) {
					c = tel.charAt(i);

					if (c >= 48 && c <= 57) {
						correcto = true;
					} else {
						correcto = false;
						Pantalla.mensajeError(Mensaje.PERSONA_MENU1_ERROR2);
						break;
					}
				}
			} else {
				Pantalla.mensajeError(Mensaje.PERSONA_MENU1_ERROR3);
			}
		} while (tel.length() != 9 || !correcto);

		return tel;

	}

	private static String pedirEmail() {

		String str;

		do {
			str = Teclado.leerString(Mensaje.PERSONA_MENU1_INFO7);
			if (str.length() == 0 || str.length() > 30) {
				Pantalla.mensajeError(Mensaje.EMPLEADO_MENU1_ERROR12);
			}
		} while (str.length() == 0 || str.length() > 30);

		return str;
	}

	private static int pedirProvincia() {
		int idProvincia;
		int choice;
		List<Provincia> lstProvincia = listarProvincias();

		controller.ctrlProvincia.ProvinciaCtrl.list(lstProvincia);

		Pantalla.mensaje("");

		choice = pedirIdProvincia();

		idProvincia = lstProvincia.get(choice - 1).getIdProvincia();

		return idProvincia;
	}

	private static int pedirIdProvincia() {

		int idProvincia = 0;

		do {
			idProvincia = Teclado.leerInt(Mensaje.CLIENTE_MENU1_INFO4);
			if (idProvincia <= 0 || idProvincia > 52) {
				Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
			}
		} while (idProvincia <= 0 || idProvincia > 52);

		return idProvincia;
	}

	private static String pedirNumTarjeta() {
		String str;
		char c;
		boolean correcto = false;

		do {
			str = Teclado.leerString(Mensaje.CLIENTE_MENU1_INFO1);

			if (str.length() == 16) {

				for (int i = 0; i < 16; i++) {
					c = str.charAt(i);

					if (c >= 48 && c <= 57) {
						correcto = true;
					} else {
						correcto = false;
						Pantalla.mensajeError(Mensaje.CLIENTE_MENU1_ERROR16);
						break;
					}
				}
			} else {
				Pantalla.mensajeError(Mensaje.CLIENTE_MENU1_ERROR17);
			}
		} while (str.length() != 16 || !correcto);

		return str;
	}

}
