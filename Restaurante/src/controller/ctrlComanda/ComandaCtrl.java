package controller.ctrlComanda;

import java.sql.SQLException;
import java.util.List;

import model.pojo.Comanda;
import model.pojo.Mensaje;
import model.query.ViewCliente;
import model.query.ViewComanda;
import model.query.ViewEmpleado;
import model.query.ViewCarta;
import utils.Pantalla;
import utils.Teclado;

public class ComandaCtrl {

	public static void insert() {

		int idCliente = pedirCliente();
		if (idCliente != -1) {
			int idCarta = pedirCarta();
			if (idCarta != -1) {
				int idEmpleado = pedirEmpleado();
				if (idEmpleado != -1) {
					Comanda c = new Comanda(idCliente, idCarta, idEmpleado);

					try {
						logic.logComanda.ComandaLog.insert(c);
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
	}

	public static void update() {
		char confirm;

		List<ViewComanda> lstComanda = listarComandas();
		if (lstComanda.size() > 0) {

			int choice = pedirElementoComanda(Mensaje.CARTA_MENU1_INFO5, lstComanda);

			int idComanda = lstComanda.get(choice - 1).getIdComanda();
			int idCliente = pedirCliente();
			int idCarta = pedirCarta();
			int idEmpleado = pedirEmpleado();

			Comanda c = new Comanda(idComanda, idCliente, idCarta, idEmpleado);

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

	private static void ejecutarUpdate(Comanda c) {
		try {
			logic.logComanda.ComandaLog.update(c);
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
		int id;

		List<ViewComanda> lstComanda = listarComandas();

		if (lstComanda.size() > 0) {

			choice = pedirElementoComanda(Mensaje.CARTA_MENU1_INFO6, lstComanda);

			id = lstComanda.get(choice - 1).getIdComanda();

			do {
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
			logic.logComanda.ComandaLog.delete(id);
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

	private static List<ViewComanda> listarComandas() {
		List<ViewComanda> lstComanda = null;

		try {
			lstComanda = logic.logComanda.ComandaLog.getComandas();
			if (lstComanda.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstComanda);
			}
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}

		return lstComanda;
	}

	public static void list(List<ViewComanda> lstComanda) {
		Pantalla.mensajeEspaciado(Mensaje.COMANDA_MENU1_TITULO2);
		if (lstComanda.size() == 0) {
			Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
		} else {
			for (int i = 0; i < lstComanda.size(); i++) {

				System.out.println("  " + (i + 1) + ". " + lstComanda.get(i).getFecha() + "\t"
						+ lstComanda.get(i).getNombreProducto() + "\t" + lstComanda.get(i).getPrecio() + "€" + "\t"
						+ lstComanda.get(i).getNombreTipo() + "\t" + lstComanda.get(i).getNombreCliente());
			}
			Pantalla.selectOk();
		}

	}

	public static void list() {

		List<ViewComanda> lstComanda = null;
		try {

			lstComanda = logic.logComanda.ComandaLog.getComandas();
			if (lstComanda.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstComanda);
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

	// -------------------------------------------------------------------------------------------------

	private static int pedirEmpleado() {
		int idEmpleado;
		int choice;
		List<ViewEmpleado> lstEmpleado = null;

		try {
			lstEmpleado = logic.logEmpleado.EmpleadoLog.getEmpleados();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.selectError();
			}
		}
		controller.ctrlEmpleado.EmpleadoCtrl.list(lstEmpleado);
		
		if (lstEmpleado.size() == 0) {
			Pantalla.mensajeError(Mensaje.SUGERENCIA_LST_EMPLEADO);
			return -1;
		} else {

			Pantalla.mensaje("");

			choice = pedirElementoEmpleado(Mensaje.COMANDA_MENU1_INFO5, lstEmpleado);

			idEmpleado = lstEmpleado.get(choice - 1).getIdEmpleado();
		}
		return idEmpleado;
	}

	private static int pedirCarta() {
		int idCarta;
		int choice;
		List<ViewCarta> lstCarta = null;
		try {
			lstCarta = logic.logCarta.CartaLog.getCarta();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.selectError();
			}
		}
		controller.ctrlCarta.CartaCtrl.list(lstCarta);

		if (lstCarta.size() == 0) {
			Pantalla.mensajeError(Mensaje.SUGERENCIA_LST_CARTA);
			return -1;
		} else {
			Pantalla.mensaje("");

			choice = pedirElementoCarta(Mensaje.COMANDA_MENU1_INFO4, lstCarta);

			idCarta = lstCarta.get(choice - 1).getIdCarta();

			return idCarta;
		}
	}

	public static int pedirElementoCarta(String cad, List<model.query.ViewCarta> lista) {
		int choice;
		do {
			choice = Teclado.leerInt(cad);
		} while (choice <= 0 || choice > lista.size());
		return choice;
	}

	private static int pedirCliente() {
		int idCliente;
		int choice;
		List<ViewCliente> lstCliente = null;
		try {
			lstCliente = logic.logCliente.ClienteLog.getClientes();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}
		controller.ctrlCliente.ClienteCtrl.list(lstCliente);

		if (lstCliente.size() == 0) {
			Pantalla.mensajeError(Mensaje.SUGERENCIA_LST_CLIENTE);
			return -1;
		} else {
			Pantalla.mensaje("");

			choice = pedirElementoCliente(Mensaje.COMANDA_MENU1_INFO3, lstCliente);

			idCliente = lstCliente.get(choice - 1).getIdCliente();
		}

		return idCliente;
	}

	public static int pedirElementoCliente(String cad, List<model.query.ViewCliente> lista) {
		int choice;
		do {
			choice = Teclado.leerInt(cad);
			if (choice <= 0 || choice > lista.size()) {
				Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
			}
		} while (choice <= 0 || choice > lista.size());
		return choice;
	}

	public static int pedirElementoEmpleado(String cad, List<model.query.ViewEmpleado> lista) {
		int choice;
		do {
			choice = Teclado.leerInt(cad);
			if (choice <= 0 || choice > lista.size()) {
				Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
			}
		} while (choice <= 0 || choice > lista.size());
		return choice;
	}

	public static int pedirElementoComanda(String cad, List<model.query.ViewComanda> lista) {
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
