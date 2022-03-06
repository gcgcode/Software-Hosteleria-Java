package view.vwRestaurante;

import java.util.ArrayList;
import java.util.List;

import model.pojo.Mensaje;
import utils.Pantalla;
import utils.Teclado;

public class RestauranteVw {

	public static void gestionarRestaurantes() {
		int opcMenuEmpleados;
		int opcSubMenuMostrar;
		do {
			subMenuRestaurante();

			opcMenuEmpleados = pedirOpcPrincipal();
			switch (opcMenuEmpleados) {

			case 1:
				controller.ctrlRestaurante.RestauranteCtrl.insert();
				break;
			case 2:
				controller.ctrlRestaurante.RestauranteCtrl.update();
				break;
			case 3:
				controller.ctrlRestaurante.RestauranteCtrl.delete();
				break;
			case 4:
				do {
					subMenuMostrar();
					opcSubMenuMostrar = pedirOpcPrincipal();

					switch (opcSubMenuMostrar) {
					case 1:
						controller.ctrlRestaurante.RestauranteCtrl.listarPorNombre();
						break;
					case 2:
						controller.ctrlRestaurante.RestauranteCtrl.list();
						break;
					case 3:
						utils.Pantalla.mensajeAtras(Mensaje.MSG_ATRAS);
						break;
					default:
						utils.Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
						break;
					}
				} while (opcSubMenuMostrar != 3);

				
				break;
			case 5:
				utils.Pantalla.mensajeAtras(Mensaje.MSG_ATRAS);
				break;

			default:
				utils.Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
				break;
			}

		} while (opcMenuEmpleados != 5);

	}

	public static void subMenuRestaurante() {

		List<String> listaOpciones = new ArrayList<String>();
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG1);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG2);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG3);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG4);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG5);

		Pantalla.marco(Mensaje.RESTAURANTE_MENU1_TITULO1, listaOpciones);

	}
	
	public static void subMenuMostrar() {

		List<String> listaOpciones = new ArrayList<String>();
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG8);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG7);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG5);

		Pantalla.marco(Mensaje.RESTAURANTE_MENU1_TITULO1, listaOpciones);

	}

	public static int pedirOpcPrincipal() {
		int opc;

		opc = Teclado.leerInt(Mensaje.PPAL_PEDIR_OPC);

		return opc;
	}

}
