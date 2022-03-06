package controller.ctrlEmpleado;

import java.sql.SQLException;

import java.util.List;

import model.pojo.Empleado;
import model.pojo.Mensaje;
import model.pojo.Persona;
import model.pojo.Provincia;
import model.pojo.TipoEmpleado;
import model.query.ViewEmpleado;
import utils.Pantalla;
import utils.Teclado;

public class EmpleadoCtrl {

	public static void insert() {

		int tipo = pedirTipoEmpleado();
		if (tipo != -1) {
			String dni = pedirDni();
			String nombre = pedirNombre();
			String apellido1 = pedirApellido1();
			String apellido2 = pedirApellido2();
			String domicilio = pedirDomicilio();
			String telefono = pedirTelefono();
			String email = pedirEmail();
			Integer idProvincia = pedirProvincia();

			String iban = pedirIban();
			String nss = pedirNss();

			Persona p = new Persona(dni, nombre, apellido1, apellido2, domicilio, telefono, email, idProvincia);
			Empleado emp = new Empleado(iban, nss, tipo);

			try {
				logic.logEmpleado.EmpleadoLog.insert(p, emp);
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
	}

	public static void update() {
		char confirm;

		List<ViewEmpleado> lstEmpleado = listarEmpleados();

		if (lstEmpleado.size() > 0) {

			int choice = pedirElementoEmpleado(Mensaje.EMPLEADO_MENU1_INFO10, lstEmpleado);

			int idPersona = lstEmpleado.get(choice - 1).getId();
			String dni = pedirDni();
			String nombre = pedirNombre();
			String apellido1 = pedirApellido1();
			String apellido2 = pedirApellido2();
			String domicilio = pedirDomicilio();
			String telefono = pedirTelefono();
			String email = pedirEmail();
			Integer idProvincia = pedirProvincia();

			String iban = pedirIban();
			String nss = pedirNss();
			int tipo = pedirTipoEmpleado();

			Persona p = new Persona(idPersona, dni, nombre, apellido1, apellido2, domicilio, telefono, email,
					idProvincia);
			Empleado e = new Empleado(iban, nss, tipo);

			do {
				confirm = Teclado.leerChar(Mensaje.CONFIRM_UPDATE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarUpdate(p, e);
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

	private static void ejecutarUpdate(Persona p, Empleado e) {
		try {

			logic.logEmpleado.EmpleadoLog.update(p, e);
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
		List<ViewEmpleado> lstEmpleado = listarEmpleados();
		if (lstEmpleado.size() > 0) {

			int choice = pedirElementoEmpleado(Mensaje.EMPLEADO_MENU1_INFO10, lstEmpleado);

			int idPersona = lstEmpleado.get(choice - 1).getId();

			do {
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

	private static void ejecutarDelete(int id) {
		try {
			logic.logEmpleado.EmpleadoLog.delete(id);
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

	private static List<ViewEmpleado> listarEmpleados() {
		List<ViewEmpleado> lstEmpleado = null;
		try {
			lstEmpleado = logic.logEmpleado.EmpleadoLog.getEmpleados();
			if (lstEmpleado.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstEmpleado);
			}

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}

		}

		return lstEmpleado;
	}

	public static void list() {
		List<ViewEmpleado> lstEmpleado = null;
		try {

			lstEmpleado = logic.logEmpleado.EmpleadoLog.getEmpleados();
			if (lstEmpleado.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstEmpleado);
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

	public static void listarPorNombre() {

		String nombre = pedirNombre();

		List<ViewEmpleado> lstEmpleado = null;
		try {

			lstEmpleado = logic.logEmpleado.EmpleadoLog.listarPorNombre(nombre);
			if (lstEmpleado.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstEmpleado);
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

	public static void list(List<ViewEmpleado> lstEmpleado) {
		Pantalla.mensajeEspaciado(Mensaje.EMPLEADO_MENU1_TITULO2);
		if (lstEmpleado.size() == 0) {
			Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
		} else {
			for (int i = 0; i < lstEmpleado.size(); i++) {
				System.out.println(
						"  " + (i + 1) + ". " + lstEmpleado.get(i).getDni() + "\t" + lstEmpleado.get(i).getNombre()
								+ "\t" + lstEmpleado.get(i).getApellido1() + "\t" + lstEmpleado.get(i).getApellido2()
								+ "\t" + lstEmpleado.get(i).getDomicilio() + "\t" + lstEmpleado.get(i).getTelefono()
								+ "\t" + lstEmpleado.get(i).getEmail() + "\t" + lstEmpleado.get(i).getProvincia() + "\t"
								+ lstEmpleado.get(i).getIban() + "\t" + lstEmpleado.get(i).getNss() + "\t"
								+ lstEmpleado.get(i).getTipoEmp() + "\t" + lstEmpleado.get(i).getSal());
			}
			Pantalla.selectOk();
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

	private static List<TipoEmpleado> listarTipoEmpleado() {
		List<TipoEmpleado> lstTipos = null;
		try {
			lstTipos = logic.logTipoEmpleado.TipoEmpleadoLog.getTipoEmpleado();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}
		return lstTipos;
	}

	// -----------------------------------------------------------------------------------------

	private static String pedirNss() {
		String str;
		char c;
		boolean correcto = false;

		do {

			str = Teclado.leerString(Mensaje.EMPLEADO_MENU1_INFO9);
			if (str.length() == 12) {
				for (int i = 0; i < 12; i++) {
					c = str.charAt(i);
					if (c >= 48 && c <= 57) {
						correcto = true;
					} else {
						correcto = false;
						Pantalla.mensajeError(Mensaje.EMPLEADO_MENU1_ERROR10);
						break;
					}
				}
			}
		} while (str.length() != 12 || !correcto);

		return str;
	}

	private static String pedirIban() {
		String str;
		char c;
		boolean correcto = false;

		do {
			str = Teclado.leerString(Mensaje.EMPLEADO_MENU1_INFO8);

			if (str.length() == 24) {

				for (int i = 0; i < 24; i++) {
					c = str.charAt(i);

					if (i < 2) {
						if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
							correcto = true;
						} else {
							correcto = false;
							Pantalla.mensajeError(Mensaje.PERSONA_MENU1_ERROR7);
						}
					} else {
						if (c >= 48 && c <= 57) {
							correcto = true;
						} else {
							correcto = false;
							Pantalla.mensajeError(Mensaje.EMPLEADO_MENU1_ERROR9);
							break;
						}
					}
				}
			} else {
				Pantalla.mensajeError(Mensaje.EMPLEADO_MENU1_ERROR8);
			}
		} while (str.length() != 24 || !correcto);

		return str;
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
		} while (idProvincia <= 0 || idProvincia > 52);

		return idProvincia;
	}

	private static int pedirTipoEmpleado() {
		int idTipoEmpleado;
		int choice;
		List<TipoEmpleado> lstTipoEmpleado = listarTipoEmpleado();

		controller.ctrlTipoEmpleado.TipoEmpleadoCtrl.list(lstTipoEmpleado);
		if (lstTipoEmpleado.size() == 0) {
			Pantalla.mensajeError(Mensaje.SUGERENCIA_LST_TIPO_EMP);
			return -1;
		} else {
			Pantalla.mensaje("");

			choice = pedirElementoTipoEmpleado(Mensaje.EMPLEADO_MENU1_INFO15, lstTipoEmpleado);

			idTipoEmpleado = lstTipoEmpleado.get(choice - 1).getIdTipo();
		}

		return idTipoEmpleado;
	}

	private static int pedirElementoTipoEmpleado(String cad, List<model.pojo.TipoEmpleado> lista) {
		int choice;

		do {
			choice = Teclado.leerInt(cad);
			if (choice <= 0 || choice > lista.size()) {
				Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
			}
		} while (choice <= 0 || choice > lista.size());

		return choice;
	}

	private static int pedirElementoEmpleado(String cad, List<ViewEmpleado> lista) {
		int choice;
		Pantalla.mensaje("");
		do {
			choice = Teclado.leerInt(cad);
		} while (choice <= 0 || choice > lista.size());

		return choice;
	}

}
