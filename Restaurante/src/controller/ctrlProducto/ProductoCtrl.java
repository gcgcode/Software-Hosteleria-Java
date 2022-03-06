package controller.ctrlProducto;

import java.sql.SQLException;
import java.util.List;

import model.pojo.Mensaje;
import model.pojo.Producto;
import model.query.ViewProducto;
import model.query.ViewTipoProducto;
import utils.Pantalla;
import utils.Teclado;

public class ProductoCtrl {

	public static void insert() {

		String nombre = pedirNombre();
		int idTipoProducto = pedirTipoProducto();

		if (idTipoProducto != -1) {
			Producto p = new Producto(nombre, idTipoProducto);

			try {
				logic.logProducto.ProductoLog.insert(p);
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
		int choice;
		int idProducto;

		List<ViewProducto> lstProducto = listarProductos();
		if (lstProducto.size() > 0) {

			choice = pedirElementoProducto(Mensaje.PRODUCTO_MENU1_INFO1, lstProducto);

			idProducto = lstProducto.get(choice - 1).getId();
			String nombre = pedirNombre();
			int idTipoProducto = pedirTipoProducto();

			Producto p = new Producto(idProducto, nombre, idTipoProducto);

			do {
				confirm = Teclado.leerChar(Mensaje.CONFIRM_UPDATE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarUpdate(p);
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

	private static void ejecutarUpdate(Producto p) {
		try {
			logic.logProducto.ProductoLog.update(p);
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
		int idProducto;

		List<ViewProducto> lstProducto = listarProductos();

		if (lstProducto.size() > 0) {

			choice = pedirElementoProducto(Mensaje.PRODUCTO_MENU1_INFO2, lstProducto);

			idProducto = lstProducto.get(choice - 1).getId();
		
			do {
				Pantalla.mensajeError(Mensaje.AVISO_DELETE_PRODUCTO);
				confirm = Teclado.leerChar(Mensaje.CONFIRM_DELETE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarDelete(idProducto);
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

	private static void ejecutarDelete(int idProducto) {
		try {
			
			logic.logProducto.ProductoLog.delete(idProducto);
			
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

	private static List<ViewTipoProducto> listarTipoProducto() {
		List<ViewTipoProducto> lstTipoProducto = null;
		try {
			lstTipoProducto = logic.logTipoProducto.TipoProductoLog.listarVista();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}
		return lstTipoProducto;
	}

	public static void list() {
		List<ViewProducto> lstProducto = null;
		try {

			lstProducto = logic.logProducto.ProductoLog.getProductos();
			if (lstProducto.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstProducto);
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

	public static void list(List<ViewProducto> lstProducto) {
		Pantalla.mensajeEspaciado(Mensaje.PRODUCTO_MENU1_TITULO2);
		if (lstProducto.size() == 0) {
			Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
		} else {
			for (int i = 0; i < lstProducto.size(); i++) {
				System.out.println("  " + (i + 1) + ". " + lstProducto.get(i).getNombreProducto() + "\t"
						+ lstProducto.get(i).getNombreTipo());
			}
			Pantalla.selectOk();
		}

	}

	private static List<ViewProducto> listarProductos() {
		List<ViewProducto> lstProducto = null;
		try {
			lstProducto = logic.logProducto.ProductoLog.getProductos();
			if (lstProducto.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstProducto);
			}

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}

		return lstProducto;
	}

	// --------------------------------------------------------------------------------------

	private static int pedirElementoProducto(String cad, List<ViewProducto> lista) {
		int choice;
		Pantalla.mensaje("");
		do {
			choice = Teclado.leerInt(cad);
		} while (choice <= 0 || choice > lista.size());
		return choice;
	}

	private static int pedirElementoTipoProducto(String cad, List<ViewTipoProducto> lista) {
		int choice;
		do {
			choice = Teclado.leerInt(cad);
			if (choice <= 0 || choice > lista.size()) {
				Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
			}
		} while (choice <= 0 || choice > lista.size());
		return choice;
	}

	private static String pedirNombre() {
		String str;
		Pantalla.mensaje("");
		do {

			str = Teclado.leerString(Mensaje.PERSONA_MENU1_INFO2);

			if (str.length() == 0 || str.length() > 25) {
				Pantalla.mensajeError(Mensaje.PERSONA_MENU1_ERROR1);
			}

		} while (str.length() == 0 || str.length() > 25);

		return str;
	}

	private static int pedirTipoProducto() {
		int idTipoProducto;
		int choice;

		List<ViewTipoProducto> lstTipoProducto = listarTipoProducto();
		if (lstTipoProducto.size() == 0) {
			Pantalla.mensajeError(Mensaje.SUGERENCIA_LST_TIPO_PROD);
			return -1;
		} else {
			controller.ctrlTipoProducto.TipoProductoCtrl.list(lstTipoProducto);

			Pantalla.mensaje("");

			choice = pedirElementoTipoProducto(Mensaje.TIPO_PROD_MENU1_INFO2, lstTipoProducto);

			idTipoProducto = lstTipoProducto.get(choice - 1).getId();
		}

		return idTipoProducto;
	}

}
