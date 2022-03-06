package utils;

import java.util.List;

import model.pojo.Mensaje;

public class Pantalla {

	static int mayor = 0;
	static String cadena;

	public static void marco(String titulo, List<String> listaOpciones) {

		// determinar mayor
		for (int i = 0; i < listaOpciones.size(); i++) {
			if (listaOpciones.get(i).length() > mayor) {
				mayor = listaOpciones.get(i).length();
				cadena = listaOpciones.get(i);
			}
		}

		pintarPantalla(titulo, listaOpciones);

	}

	private static void pintarPantalla(String titulo, List<String> listaOpciones) {
		System.out.println();
		System.out.println();
		System.out.println("┌" + generarSeq("─", mayor + 5) + "┐");
		System.out.println("│" + generarSeq(" ", 1) + rellenar(titulo, mayor + 4, " ") + "│");
		System.out.println("├" + generarSeq("─", mayor + 5) + "┤");
		for (int i = 0; i < listaOpciones.size(); i++) {
			System.out.println("│" + generarSeq(" ", 1) + (i + 1) + ". " + rellenar(listaOpciones.get(i), mayor, " ")
					+ generarSeq(" ", 1) + "│");
		}
		System.out.println("└" + generarSeq("─", mayor + 5) + "┘");
	}

	public static String rellenar(String cadena, int tamaño, String car) {

		int tam;

		String strCentro;
		String strDer;

		tam = tamaño - cadena.length();

		strCentro = cadena;
		strDer = generarSeq(car, tam);

		return strCentro + strDer;
	}

	private static String generarSeq(String str, int tam) {
		String cadena = "";
		for (int i = 0; i < tam; i++) {
			cadena += str;
		}

		return cadena;
	}

	public static void mensajeError(String str) {
		System.err.println(str);
	}

	public static void mensaje(String str) {
		System.out.println(str);
	}

	public static void mensajeEspaciado(String str) {
		System.out.println();
		System.out.println(str);
		System.out.println();
	}

	public static void mensajeAtras(String str) {
		System.out.println();
		System.out.println(str);
	}
	
	public static void error20002() {
		mensajeError(Mensaje.ERROR2002);
	}
	
	public static void error20003() {
		mensajeError(Mensaje.ERROR2003);
	}

	public static void updateError() {
		mensajeError(Mensaje.UPDATE_KO);
	}

	public static void deleteError() {
		mensajeError(Mensaje.DELETE_KO);
	}

	public static void insertError() {
		mensajeError(Mensaje.INSERT_KO);
	}

	public static void selectError() {
		mensajeError(Mensaje.SELECT_KO);
	}

	public static void updateOk() {	
		mensaje("");
		mensaje(Mensaje.UPDATE_OK);
	}

	public static void insertOk() {
		mensaje("");
		mensaje(Mensaje.INSERT_OK);
	}

	public static void deleteOk() {
		mensaje("");
		mensaje(Mensaje.DELETE_OK);
	}

	public static void selectOk() {
		mensaje("");
		mensaje(Mensaje.SELECT_OK);
	}

	public static void cancelMod() {
		mensajeError("La modificación ha sido cancelada.");
	
	}

	public static void cancelDel() {
		mensajeError("La eliminación ha sido cancelada.");
	
	}

	public static void error() {
		mensajeError(Mensaje.CARTA_MENU1_ERROR3);
	
	}

}
