package controller.ctrlTipoProducto;

import java.sql.SQLException;

import java.util.List;

import model.pojo.Mensaje;
import model.pojo.TipoProducto;
import model.query.ViewTipoProducto;
import utils.Pantalla;
import utils.Teclado;

public class TipoProductoCtrl {

	public static void insert() {

		String nombreTipo = pedirNombre();

		TipoProducto t = new TipoProducto(nombreTipo);

		try {
			logic.logTipoProducto.TipoProductoLog.insert(t);
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
		int idTipoProducto;

		List<ViewTipoProducto> lstTipoProducto = listarTipoProducto();

		if (lstTipoProducto.size() > 0) {

			choice = pedirElemento(Mensaje.TIPO_PROD_MENU1_INFO10, lstTipoProducto);

			idTipoProducto = lstTipoProducto.get(choice - 1).getId();
			String nombreTipo = pedirNombre();

			TipoProducto t = new TipoProducto(idTipoProducto, nombreTipo);

			do {
				confirm = Teclado.leerChar(Mensaje.CONFIRM_UPDATE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarUpdate(t);
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

	private static void ejecutarUpdate(TipoProducto t) {
		try {

			logic.logTipoProducto.TipoProductoLog.update(t);
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
		int idTipoProducto;

		List<ViewTipoProducto> lstTipoProducto = listarTipoProducto();

		if (lstTipoProducto.size() > 0) {

			choice = pedirElemento(Mensaje.TIPO_PROD_MENU1_INFO11, lstTipoProducto);

			idTipoProducto = lstTipoProducto.get(choice - 1).getId();

			do {
				Pantalla.mensaje("");

				confirm = Teclado.leerChar(Mensaje.CONFIRM_DELETE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarDelete(idTipoProducto);
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

	private static void ejecutarDelete(int idTipoProducto) {
		try {
			logic.logTipoProducto.TipoProductoLog.delete(idTipoProducto);
			Pantalla.deleteOk();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.deleteError();
			}
		}

	}

	private static List<ViewTipoProducto> listarTipoProducto() {
		List<ViewTipoProducto> lstTipoProducto = null;
		try {
			lstTipoProducto = logic.logTipoProducto.TipoProductoLog.listarVista();
			if (lstTipoProducto.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstTipoProducto);
			}
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.selectError();
			}
		}

		return lstTipoProducto;
	}

	public static void list(List<ViewTipoProducto> lstTipoProducto) {
		Pantalla.mensajeEspaciado(Mensaje.TIPO_PRODUCTO_MENU1_TITULO2);
		if (lstTipoProducto.size() == 0) {
			Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
		} else {
			for (int i = 0; i < lstTipoProducto.size(); i++) {
				System.out.println("  " + (i + 1) + ". " + lstTipoProducto.get(i).getNombre());
			}
			Pantalla.selectOk();
		}

	}

	public static void list() {

		List<ViewTipoProducto> lstTipoProducto = null;

		try {
			lstTipoProducto = logic.logTipoProducto.TipoProductoLog.listarVista();
			if (lstTipoProducto.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstTipoProducto);
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

// --------------------------------------------------------------------------------

	private static int pedirElemento(String cad, List<ViewTipoProducto> lista) {

		Pantalla.mensaje("");
		int choice;
		do {
			choice = Teclado.leerInt(cad);
		} while (choice <= 0 || choice > lista.size());
		return choice;
	}

	public static String pedirNombre() {

		Pantalla.mensaje("");
		String str;

		do {

			str = Teclado.leerString(Mensaje.TIPO_PROD_MENU1_INFO1);

			if (str.length() == 0 || str.length() > 25) {
				Pantalla.mensajeError(Mensaje.PERSONA_MENU1_ERROR4);
			}

		} while (str.length() == 0 || str.length() > 20);

		return str;

	}

}
