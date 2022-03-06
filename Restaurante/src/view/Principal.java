package view;

import java.util.ArrayList;
import java.util.List;

import dbm.Oracle;
import model.pojo.Mensaje;
import utils.Pantalla;
import utils.Teclado;

public class Principal {

	public static void aplicacion() {

		int opcPrincipal;
		int opcSubMenuConfEmpresa;
		int opcSubMenuProducto;
		int opcSubMenuEmpleado;
		int opcSubMenuGesRestaurante;

		Oracle.leerConf();

		if (Oracle.pruebaConexionado()) {

			mensajeBienvenida();

			do {

				menuPrincipal();

				opcPrincipal = pedirOpc();

				switch (opcPrincipal) {
				case 1: // GESTION EMPRESARIAL
					do {

						subMenuGestEmpresarial();
						opcSubMenuConfEmpresa = pedirOpc();

						switch (opcSubMenuConfEmpresa) {
						case 1:
							view.vwRestaurante.RestauranteVw.gestionarRestaurantes();
							break;

						case 2:
							do {
								subMenuProducto();
								opcSubMenuProducto = pedirOpc();
								switch (opcSubMenuProducto) {
								case 1:
									view.vwTipoProducto.TipoProductoVw.gestionarTipoProducto();
									break;
								case 2:
									view.vwProducto.ProductoVw.gestionarProductos();
									break;
								case 3:
									utils.Pantalla.mensajeAtras(Mensaje.MSG_ATRAS);
									break;
								default:
									utils.Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
									break;
								}
							} while (opcSubMenuProducto != 3);

							break;

						case 3:
							do {
								subMenuEmpleado();
								opcSubMenuEmpleado = pedirOpc();
								switch (opcSubMenuEmpleado) {
								case 1:
									view.vwTipoEmpleado.TipoEmpleadoVw.gestionarTipoEmpleado();
									break;
								case 2:
									view.vwEmpleado.EmpleadoVw.gestionarEmpleados();
									break;
								case 3:
									utils.Pantalla.mensajeAtras(Mensaje.MSG_ATRAS);
									break;
								default:
									utils.Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
									break;
								}

							} while (opcSubMenuEmpleado != 3);

							break;
						case 4:
							utils.Pantalla.mensajeAtras(Mensaje.MSG_ATRAS);
							break;
						default:
							utils.Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
							break;
						}

					} while (opcSubMenuConfEmpresa != 4);

					break;
				case 2: // GESTION RESTAURANTE
					do {
						subMenuGestRestaurante();
						opcSubMenuGesRestaurante = pedirOpc();
						switch (opcSubMenuGesRestaurante) {
						case 1:
							view.vwCliente.ClienteVw.gestionarClientes();
							break;
						case 2:
							view.vwCarta.CartaVw.gestionarCarta();
							break;
						case 3:
							view.vwComanda.ComandaVw.gestionarComanda();
							break;
						case 4:
							utils.Pantalla.mensajeAtras(Mensaje.MSG_ATRAS);
							break;
						default:
							utils.Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
							break;
						}
					} while (opcSubMenuGesRestaurante != 4);

				case 3: // SALIR
					finPrograma();
					break;
				default:
					utils.Pantalla.mensajeError(Mensaje.PPAL_ERROR_2);
				}

			} while (opcPrincipal != 3);
		} else {
			errorConexion();
		}

	}

	private static void subMenuEmpleado() {
		List<String> listaOpciones = new ArrayList<String>();

		listaOpciones.add(Mensaje.MENU1_MSG6);
		listaOpciones.add(Mensaje.MENU1_MSG1);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG5);

		Pantalla.marco(Mensaje.EMPLEADO_MENU1_TITULO1, listaOpciones);

	}

	private static void subMenuGestEmpresarial() {
		List<String> listaOpciones = new ArrayList<String>();

		listaOpciones.add(Mensaje.MENU1_MSG3);
		listaOpciones.add(Mensaje.MENU1_MSG4);
		listaOpciones.add(Mensaje.MENU1_MSG1);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG5);

		Pantalla.marco(Mensaje.MENU1_MSG9, listaOpciones);

	}

	private static void subMenuGestRestaurante() {
		List<String> listaOpciones = new ArrayList<String>();

		listaOpciones.add(Mensaje.MENU1_MSG2);
		listaOpciones.add(Mensaje.MENU1_MSG7);
		listaOpciones.add(Mensaje.MENU1_MSG8);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG5);

		Pantalla.marco(Mensaje.MENU1_MSG10, listaOpciones);

	}

	private static void subMenuProducto() {
		List<String> listaOpciones = new ArrayList<String>();

		listaOpciones.add(Mensaje.MENU1_MSG5);
		listaOpciones.add(Mensaje.MENU1_MSG4);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG5);

		Pantalla.marco(Mensaje.MENU1_MSG10, listaOpciones);

	}

	private static void mensajeBienvenida() {
		System.out.println();
		System.out.println();
		System.out.println(Mensaje.PPAL_MSG_1);
		System.out.println();
		System.out.println(Mensaje.PPAL_MSG_3);
		
	}

	private static void menuPrincipal() {

		List<String> listaOpciones = new ArrayList<String>();
		listaOpciones.add(Mensaje.CONFIG_EMP);
		listaOpciones.add(Mensaje.GEST_REST);
		listaOpciones.add(Mensaje.GENERIC_MENU1_MSG6);

		Pantalla.marco(Mensaje.MENU1_MSG0, listaOpciones);

	}

	private static void finPrograma() {
		System.out.println();
		System.out.println(Mensaje.PPAL_MSG_2);
	}

	private static void errorConexion() {
		System.err.println(Mensaje.PPAL_ERROR_1);
	}

	public static int pedirOpc() {
		int opc;
		opc = Teclado.leerInt(Mensaje.PPAL_PEDIR_OPC);

		return opc;
	}

}
