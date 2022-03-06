package controller.ctrlCarta;

import java.sql.SQLException;
import java.util.List;

import model.pojo.Carta;
import model.pojo.Mensaje;
import model.pojo.Restaurante;
import model.query.ViewProducto;
import model.query.ViewCarta;
import utils.Pantalla;
import utils.Teclado;

public class CartaCtrl {

	public static void insert() {

		int idRestaurante = pedirIdRestaurante();
		if (idRestaurante != -1) {
			int idProducto = pedirIdProducto();
			if (idProducto != -1) {
				float pvp = pedirPvp();
				int disponible = pedirDisponibilidad();

				Carta c = new Carta(disponible, pvp, idProducto, idRestaurante);

				try {
					logic.logCarta.CartaLog.insert(c);
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
	}

	public static void update() {
		char confirm;
		List<ViewCarta> lstCarta = listarCartas();

		if (lstCarta.size() > 0) {

			int choice = pedirElementoCarta(Mensaje.CARTA_MENU1_INFO5, lstCarta);

			int idCarta = lstCarta.get(choice - 1).getIdCarta();
			int idRestaurante = pedirIdRestaurante();
			int idProducto = pedirIdProducto();
			float pvp = pedirPvp();
			int disponible = pedirDisponibilidad();

			Carta c = new Carta(idCarta, disponible, pvp, idProducto, idRestaurante);

			do {
				confirm = Teclado.leerChar(Mensaje.CONFIRM_UPDATE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarUpdate(c);
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

	private static void ejecutarUpdate(Carta c) {
		try {
			logic.logCarta.CartaLog.update(c);
			Pantalla.updateOk();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
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
		int id;
		List<ViewCarta> lstCarta = listarCartas();
		if (lstCarta.size() > 0) {

			choice = pedirElementoCarta(Mensaje.CARTA_MENU1_INFO6, lstCarta);

			id = lstCarta.get(choice - 1).getIdCarta();

			do {
				Pantalla.mensaje("");
				confirm = Teclado.leerChar(Mensaje.CONFIRM_DELETE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarDelete(id);
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
			logic.logCarta.CartaLog.delete(id);
			Pantalla.deleteOk();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.deleteError();
			}
		}

	}

	public static List<ViewCarta> listarCartas() {

		List<ViewCarta> lstCarta = null;
		try {
			lstCarta = logic.logCarta.CartaLog.getCarta();
			if (lstCarta.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstCarta);
			}
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}

		return lstCarta;
	}

	public static void list(List<ViewCarta> lstCarta) {

		String disponible = "Disponible";
		String noDisponible = "No disponible";

		String disponibilidad;

		Pantalla.mensajeEspaciado(Mensaje.CARTA_MENU1_TITULO2);
		if (lstCarta.size() == 0) {
			Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
		} else {
			for (int i = 0; i < lstCarta.size(); i++) {
				if (lstCarta.get(i).getDisponible() == 1) {
					disponibilidad = disponible;
				} else {
					disponibilidad = noDisponible;
				}

				System.out.println("  " + (i + 1) + ". " + lstCarta.get(i).getNombreRestaurante() + "\t"
						+ lstCarta.get(i).getNombreProducto() + "\t" + lstCarta.get(i).getPvp() + "€\t"
						+ disponibilidad);
			}
			Pantalla.selectOk();
		}

	}

	public static void list() {
		List<ViewCarta> lstCarta = null;
		try {
			lstCarta = logic.logCarta.CartaLog.getCarta();
			if (lstCarta.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstCarta);
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

	public static void listarPorDisponibilidad() {
		int disponible = pedirDisponibilidad();
		List<ViewCarta> lstCarta = null;
		try {
			lstCarta = logic.logCarta.CartaLog.listarPorDisponibilidad(disponible);
			if (lstCarta.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstCarta);
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

	// ----------------------------------------------------------------------------------

	private static int pedirDisponibilidad() {
		char choice;
		int disponible = 1;
		int noDisponible = 0;

		int idDisponible = 0;

		do {
			choice = Teclado.leerChar(Mensaje.CARTA_MENU1_INFO4);

			switch (choice) {
			case 'S':
			case 's':
				idDisponible = disponible;
				break;
			case 'N':
			case 'n':
				idDisponible = noDisponible;
				break;

			default:
				Pantalla.mensajeError(Mensaje.CARTA_MENU1_ERROR3);
				break;
			}
		} while (choice != 's' && choice != 'S' && choice != 'N' && choice != 'n');

		return idDisponible;
	}

	private static float pedirPvp() {
		Float pvp;

		do {
			pvp = Teclado.leerFloat("Introduce un PVP: ");
			if (pvp > 999.99) {
				Pantalla.mensajeError(Mensaje.CARTA_MENU1_ERROR2);
			} else if (pvp <= 0) {
				Pantalla.mensajeError(Mensaje.CARTA_MENU1_ERROR1);
			}
		} while (pvp <= 0 || pvp > 999.99);

		return pvp;
	}

	private static int pedirIdProducto() {
		int idProducto;
		int choice;
		List<ViewProducto> lstProducto = null;

		try {
			lstProducto = logic.logProducto.ProductoLog.getProductos();

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}

		controller.ctrlProducto.ProductoCtrl.list(lstProducto);
		if (lstProducto.size() == 0) {
			Pantalla.mensajeError(Mensaje.SUGERENCIA_LST_RESTAURANTE);
			return -1;
		} else {
			Pantalla.mensaje("");

			choice = pedirElementoProducto(Mensaje.COMANDA_MENU1_INFO4, lstProducto);

			idProducto = lstProducto.get(choice - 1).getId();
		}

		return idProducto;
	}

	private static int pedirIdRestaurante() {
		int idRestaurante;
		int choice;

		List<Restaurante> lstRestaurante = null;

		try {
			lstRestaurante = logic.logRestaurante.RestauranteLog.list();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}

		controller.ctrlRestaurante.RestauranteCtrl.list(lstRestaurante);

		if (lstRestaurante.size() == 0) {
			Pantalla.mensajeError(Mensaje.SUGERENCIA_LST_RESTAURANTE);
			return -1;
		} else {
			Pantalla.mensaje("");

			choice = pedirElementoRestaurante(Mensaje.CARTA_MENU1_INFO1, lstRestaurante);

			idRestaurante = lstRestaurante.get(choice - 1).getIdRestaurante();

		}

		return idRestaurante;
	}

	public static int pedirElementoProducto(String cad, List<model.query.ViewProducto> lista) {
		int choice;
		do {
			choice = Teclado.leerInt(cad);
			if (choice <= 0 || choice > lista.size()) {
				Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
			}
		} while (choice <= 0 || choice > lista.size());
		return choice;
	}

	public static int pedirElementoRestaurante(String cad, List<model.pojo.Restaurante> lista) {
		int choice;
		do {
			choice = Teclado.leerInt(cad);
			if (choice <= 0 || choice > lista.size()) {
				Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
			}
		} while (choice <= 0 || choice > lista.size());
		return choice;
	}

	public static int pedirElementoCarta(String cad, List<model.query.ViewCarta> lista) {
		int choice;
		Pantalla.mensaje("");
		do {
			choice = Teclado.leerInt(cad);
			if (choice <= 0 || choice > lista.size()) {
				Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
			}
		} while (choice <= 0 || choice > lista.size());
		return choice;
	}

}
