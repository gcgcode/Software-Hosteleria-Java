package view.vwCliente;

import java.util.ArrayList;
import java.util.List;

import model.pojo.Mensaje;
import utils.Pantalla;
import utils.Teclado;

public class ClienteVw {

	public static void gestionarClientes() {
		int opcMenuCliente;
		int opcSubMenuCliente;
		do {
			MenuClientes();

			opcMenuCliente = pedirOpcPrincipal();
			switch (opcMenuCliente) {

			case 1:
				controller.ctrlCliente.ClienteCtrl.insert();
				break;
			case 2:
				controller.ctrlCliente.ClienteCtrl.update();
				break;
			case 3:
				controller.ctrlCliente.ClienteCtrl.delete();
				break;
			case 4:
				do {
					subMenuClientes();
					opcSubMenuCliente = pedirOpcPrincipal();

					switch (opcSubMenuCliente) {
					case 1:
						controller.ctrlCliente.ClienteCtrl.listPorNombre();
						break;
					case 2:
						controller.ctrlCliente.ClienteCtrl.list();
						break;
					case 3: 
						utils.Pantalla.mensajeAtras(Mensaje.MSG_ATRAS);
						break;
					default:
						utils.Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
						break;
					}
				} while (opcSubMenuCliente != 3);
				break;
			case 5:
				utils.Pantalla.mensajeAtras(Mensaje.MSG_ATRAS);
				break;

			default:
				utils.Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
				break;
			}

		} while (opcMenuCliente != 5);

	}

	public static void MenuClientes() {

		List<String> listaOpciones = new ArrayList<String>();
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG1);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG2);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG3);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG4);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG5);

		Pantalla.marco(Mensaje.CLIENTE_MENU1_TITULO1, listaOpciones);

	}

	public static void subMenuClientes() {

		List<String> listaOpciones = new ArrayList<String>();
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG8);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG7);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG5);

		Pantalla.marco(Mensaje.CLIENTE_MENU1_TITULO1, listaOpciones);

	}

	public static int pedirOpcPrincipal() {
		int opc;

		opc = Teclado.leerInt(Mensaje.PPAL_PEDIR_OPC);

		return opc;
	}

}
