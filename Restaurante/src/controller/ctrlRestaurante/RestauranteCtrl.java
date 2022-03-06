package controller.ctrlRestaurante;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.pojo.Mensaje;
import model.pojo.Restaurante;
import utils.Pantalla;
import utils.Teclado;

public class RestauranteCtrl {

	public static List<Restaurante> lstRestaurante = new ArrayList<Restaurante>();

	public static void insert() {

		String nif = pedirNif();
		String nombre = pedirNombre();
		String direccion = pedirDireccion();
		String telefono = pedirTelefono();

		Restaurante r = new Restaurante(nif, nombre, direccion, telefono);

		try {
			logic.logRestaurante.RestauranteLog.insert(r);
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
		int choice;
		int idRestaurante;

		List<Restaurante> lstRestaurante = listarRestaurantes();

		if (lstRestaurante.size() > 0) {
			choice = pedirElementoRestaurante(Mensaje.RESTAURANTE_MENU1_INFO1, lstRestaurante);

			idRestaurante = lstRestaurante.get(choice - 1).getIdRestaurante();
			String nif = pedirNif();
			String nombre = pedirNombre();
			String direccion = pedirDireccion();
			String telefono = pedirTelefono();

			Restaurante r = new Restaurante(idRestaurante, nif, nombre, direccion, telefono);

			do {
				confirm = Teclado.leerChar(Mensaje.CONFIRM_UPDATE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarUpdate(r);
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

	private static void ejecutarUpdate(Restaurante r) {
		try {

			logic.logRestaurante.RestauranteLog.update(r);
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
		int idRestaurante;

		List<Restaurante> lstRestaurante = listarRestaurantes();
		if (lstRestaurante.size() == 0) {
			Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
		} else {
			choice = pedirElementoRestaurante(Mensaje.RESTAURANTE_MENU1_INFO2, lstRestaurante);

			idRestaurante = lstRestaurante.get(choice - 1).getIdRestaurante();

			do {
				Pantalla.mensaje("");

				confirm = Teclado.leerChar(Mensaje.CONFIRM_DELETE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarDelete(idRestaurante);
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

	private static void ejecutarDelete(int idRestaurante) {
		try {
			logic.logRestaurante.RestauranteLog.delete(idRestaurante);
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

	private static List<Restaurante> listarRestaurantes() {
		List<Restaurante> lstRestaurante = null;

		try {
			lstRestaurante = logic.logRestaurante.RestauranteLog.list();
			if (lstRestaurante.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstRestaurante);
			}
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}

		return lstRestaurante;
	}

	public static void list(List<Restaurante> lstRestaurante) {
		Pantalla.mensajeEspaciado(Mensaje.RESTAURANTE_MENU1_TITULO2);
		if (lstRestaurante.size() == 0) {
			Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
		} else {
			for (int i = 0; i < lstRestaurante.size(); i++) {
				System.out.println("  " + (i + 1) + ". " + lstRestaurante.get(i).getNif() + "\t"
						+ lstRestaurante.get(i).getNombre() + "\t" + lstRestaurante.get(i).getDireccion() + "\t"
						+ lstRestaurante.get(i).getTelefono());
			}
			Pantalla.selectOk();
		}

	}

	public static void list() {

		List<Restaurante> lstRestaurante = null;
		try {
			lstRestaurante = logic.logRestaurante.RestauranteLog.list();
			if (lstRestaurante.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstRestaurante);
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
		List<Restaurante> lstRestaurante = null;
		try {
			lstRestaurante = logic.logRestaurante.RestauranteLog.listarPorNombre(nombre);
			if (lstRestaurante.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstRestaurante);
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
	
	// -----------------------------------------------------------------------------

	private static int pedirElementoRestaurante(String cad, List<Restaurante> lista) {
		int choice;
		Pantalla.mensaje("");
		do {
			choice = Teclado.leerInt(cad);
		} while (choice <= 0 || choice > lista.size());
		return choice;
	}

	private static String pedirNif() {

		String str;

		boolean correcto = false;
		char c;

		do {

			Pantalla.mensaje("");
			str = Teclado.leerString(Mensaje.RESTAURANTE_MENU1_INFO3);
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

	private static String pedirDireccion() {

		String str;

		do {

			str = Teclado.leerString(Mensaje.RESTAURANTE_MENU1_INFO4);

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

}
