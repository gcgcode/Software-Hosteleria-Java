package view.vwComanda;

import java.util.ArrayList;
import java.util.List;

import model.pojo.Mensaje;
import utils.Pantalla;
import utils.Teclado;

public class ComandaVw {

	public static void gestionarComanda() {
		int opcMenuComanda;
		do {
			subMenuCarta();

			opcMenuComanda = pedirOpcPrincipal();
			switch (opcMenuComanda) {

			case 1:
				controller.ctrlComanda.ComandaCtrl.insert();
				break;
			case 2:
				controller.ctrlComanda.ComandaCtrl.update();
				break;
			case 3:
				controller.ctrlComanda.ComandaCtrl.delete();
				break;
			case 4:
				controller.ctrlComanda.ComandaCtrl.list();
				break;
			case 5:
				utils.Pantalla.mensajeAtras(Mensaje.MSG_ATRAS);
				break;
			default:
				utils.Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
				break;
			}

		} while (opcMenuComanda != 5);

	}

	public static void subMenuCarta() {

		List<String> listaOpciones = new ArrayList<String>();
		listaOpciones.add(Mensaje.CARTA_COMANDA_MENU1_MSG1);
		listaOpciones.add(Mensaje.CARTA_COMANDA_MENU1_MSG2);
		listaOpciones.add(Mensaje.CARTA_COMANDA_MENU1_MSG3);
		listaOpciones.add(Mensaje.CARTA_COMANDA_MENU1_MSG4);
		listaOpciones.add(Mensaje.CARTA_COMANDA_MENU1_MSG5);

		Pantalla.marco(Mensaje.COMANDA_MENU1_TITULO1, listaOpciones);

	}

	public static int pedirOpcPrincipal() {
		int opc;

		opc = Teclado.leerInt(Mensaje.PPAL_PEDIR_OPC);

		return opc;
	}

}
