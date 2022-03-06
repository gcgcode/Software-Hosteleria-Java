package controller.ctrlTipoEmpleado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.pojo.Mensaje;
import model.pojo.TipoEmpleado;
import utils.Pantalla;
import utils.Teclado;

/**
 * 
 * @author Gnz
 *
 */
public class TipoEmpleadoCtrl {

	static List<TipoEmpleado> lstTipoEmp = new ArrayList<TipoEmpleado>();

	public static void insert() {

		String nombreTipo = pedirNombre();
		Float sal = pedirSal();

		TipoEmpleado t = new TipoEmpleado(nombreTipo, sal);

		try {
			logic.logTipoEmpleado.TipoEmpleadoLog.insert(t);
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

		List<TipoEmpleado> lstTipoEmp = listarTipoEmpleado();
		if (lstTipoEmp.size() > 0) {

			int choice = pedirElementoTipoEmpleado(Mensaje.TIPO_EMP_MENU1_INFO10, lstTipoEmp);

			int idTipo = lstTipoEmp.get(choice - 1).getIdTipo();
			String nombreTipo = pedirNombre();
			Float sal = pedirSal();

			TipoEmpleado t = new TipoEmpleado(idTipo, nombreTipo, sal);

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

	private static void ejecutarUpdate(TipoEmpleado t) {
		try {

			logic.logTipoEmpleado.TipoEmpleadoLog.update(t);
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
		List<TipoEmpleado> lstEmpleado = listarTipoEmpleado();
		if (lstEmpleado.size() > 0) {

			int choice = pedirElementoTipoEmpleado(Mensaje.TIPO_EMP_MENU1_INFO11, lstEmpleado);

			int idTipo = lstEmpleado.get(choice - 1).getIdTipo();

			do {
				Pantalla.mensajeError(Mensaje.AVISO_DELETE_TIPO_EMP);
				confirm = Teclado.leerChar(Mensaje.CONFIRM_UPDATE);
				switch (confirm) {
				case 'S':
				case 's':
					ejecutarDelete(idTipo);
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

	private static void ejecutarDelete(int idTipo) {
		try {
			logic.logTipoEmpleado.TipoEmpleadoLog.delete(idTipo);
			Pantalla.deleteOk();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			}
		}
	}

	public static void list() {
		List<TipoEmpleado> lstTipoEmpleado = null;

		try {
			lstTipoEmpleado = logic.logTipoEmpleado.TipoEmpleadoLog.getTipoEmpleado();
			if (lstTipoEmpleado.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstTipoEmpleado);
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

	public static void list(List<TipoEmpleado> lstTipoEmpleado) {
		Pantalla.mensajeEspaciado(Mensaje.TIPO_EMPLEADO_MENU1_TITULO2);
		if (lstTipoEmpleado.size() == 0) {
			Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
		} else {
			for (int i = 0; i < lstTipoEmpleado.size(); i++) {
				System.out.println("  " + (i + 1) + ". " + lstTipoEmpleado.get(i).getNombre() + "\t"
						+ lstTipoEmpleado.get(i).getSal()+"€");
			}
			Pantalla.selectOk();
		}

	}

	public static List<TipoEmpleado> listarTipoEmpleado() {
		List<TipoEmpleado> lstTipoEmpleado = null;

		try {
			lstTipoEmpleado = logic.logTipoEmpleado.TipoEmpleadoLog.getTipoEmpleado();
			if (lstTipoEmpleado.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				list(lstTipoEmpleado);
			}
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}

		return lstTipoEmpleado;

	}

	// ---------------------------------------------------------------------------------------

	private static Float pedirSal() {

		Float sal;

		do {
			sal = Teclado.leerFloat(Mensaje.TIPO_EMP_MENU1_INFO2);
			if (sal < 900 || sal > 9999.99) {
				Pantalla.mensajeError(Mensaje.TIPO_EMPLEADO_MENU1_ERROR1);
			}
		} while (sal < 900 || sal > 9999.99);

		return sal;

	}

	private static String pedirNombre() {
		String str;

		Pantalla.mensaje("");
		do {

			str = Teclado.leerString(Mensaje.TIPO_EMP_MENU1_INFO1);

			if (str.length() == 0 || str.length() > 25) {
				Pantalla.mensajeError(Mensaje.PERSONA_MENU1_ERROR4);
			}

		} while (str.length() == 0 || str.length() > 20);

		return str;
	}

	private static int pedirElementoTipoEmpleado(String cad, List<model.pojo.TipoEmpleado> lista) {
		int choice;

		Pantalla.mensaje("");

		do {
			choice = Teclado.leerInt(cad);
		} while (choice <= 0 || choice > lista.size());

		return choice;
	}

}
