package view.vwTipoEmpleado;

import java.util.ArrayList;
import java.util.List;

import model.pojo.Mensaje;
import utils.Pantalla;
import utils.Teclado;

public class TipoEmpleadoVw {
	public static void gestionarTipoEmpleado() {
		int opcMenuTipoEmpleado;
		do {
			subMenuEmpleados();

			opcMenuTipoEmpleado = pedirOpcPrincipal();
			switch (opcMenuTipoEmpleado) {

			case 1:
				controller.ctrlTipoEmpleado.TipoEmpleadoCtrl.insert();
				break;
			case 2:
				controller.ctrlTipoEmpleado.TipoEmpleadoCtrl.update();
				break;
			case 3:
				controller.ctrlTipoEmpleado.TipoEmpleadoCtrl.delete();
				break;
			case 4:
				controller.ctrlTipoEmpleado.TipoEmpleadoCtrl.list();
				break;
			case 5:
				utils.Pantalla.mensajeAtras(Mensaje.MSG_ATRAS);
				break;

			default:
				utils.Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
				break;
			}

		} while (opcMenuTipoEmpleado != 5);

	}

	public static void subMenuEmpleados() {

		List<String> listaOpciones = new ArrayList<String>();
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG1);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG2);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG3);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG4);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG5);

		Pantalla.marco(Mensaje.TIPO_EMPLEADO_MENU1_TITULO1, listaOpciones);

	}

	public static int pedirOpcPrincipal() {
		int opc;

		opc = Teclado.leerInt(Mensaje.PPAL_PEDIR_OPC);

		return opc;
	}
}
