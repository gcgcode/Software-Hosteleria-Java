package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Teclado {

	public static Integer leerInt(String sPregunta, String cadena, Integer valor) {
		BufferedReader buffer1 = new BufferedReader(new InputStreamReader(System.in));
		boolean fallo;
		Integer iLectura = 0;

		do {
			System.out.print(sPregunta + "[ Enter: " + cadena + " ]: ");
			String respuesta = "";
			;
			try {
				respuesta = buffer1.readLine();
				if (respuesta.length() > 0) {
					iLectura = Integer.parseInt(respuesta);
				} else if (respuesta.length() == 0) {
					iLectura = valor;
				}
				fallo = false;
			} catch (Exception e) {
				fallo = true;
			}

			if (iLectura == 0) {
				fallo = true;
			}
		} while (fallo);

		return iLectura;
	}

	public static Float leerFloat(String sPregunta, Float valor) {
		BufferedReader buffer1 = new BufferedReader(new InputStreamReader(System.in));
		boolean fallo;
		Float iLectura = null;

		do {
			System.out.print(sPregunta + "[ Enter: " + valor + "€" + " ]: ");
			String respuesta = "";
			;
			try {
				respuesta = buffer1.readLine();
				if (respuesta.length() > 0) {
					iLectura = Float.parseFloat(respuesta);
				} else if (respuesta.length() == 0) {
					iLectura = valor;
				}
				fallo = false;
			} catch (Exception e) {
				fallo = true;
			}

			if (iLectura == 0) {
				fallo = true;
			}
		} while (fallo);

		return iLectura;
	}

	public static int leerInt(String sPregunta) {
		BufferedReader buffer1 = new BufferedReader(new InputStreamReader(System.in));
		boolean fallo;
		int iNumero = 0;

		do {
			System.out.print(sPregunta);

			try {
				iNumero = Integer.parseInt(buffer1.readLine());
				fallo = false;
			} catch (Exception e) {
				fallo = true;
			}

		} while (fallo);

		return iNumero;
	}

	public static char leerChar(String sPregunta) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		boolean fallo;
		char chTexto = '\0';

		do {
			System.out.print(sPregunta);

			try {
				chTexto = (input.readLine()).charAt(0);
				fallo = false;
			} catch (Exception e) {
				fallo = true;
			}

		} while (fallo);

		return chTexto;

	}

	public static byte leerByte(String sPregunta) {
		BufferedReader buffer1 = new BufferedReader(new InputStreamReader(System.in));
		boolean fallo;
		Byte iNumero = 0;

		do {
			System.out.print(sPregunta);

			try {
				iNumero = Byte.parseByte(buffer1.readLine());
				fallo = false;
			} catch (Exception e) {
				fallo = true;
			}

		} while (fallo);

		return iNumero;
	}

	public static short leerShort(String sPregunta) {
		BufferedReader buffer1 = new BufferedReader(new InputStreamReader(System.in));
		boolean fallo;
		Short iNumero = 0;

		do {
			System.out.print(sPregunta);

			try {
				iNumero = Short.parseShort(buffer1.readLine());
				fallo = false;
			} catch (Exception e) {
				fallo = true;
			}

		} while (fallo);

		return iNumero;
	}

	public static float leerFloat(String sPregunta) {
		BufferedReader buffer1 = new BufferedReader(new InputStreamReader(System.in));
		boolean fallo;
		float iNumero = 0;

		do {
			System.out.print(sPregunta);

			try {
				iNumero = Float.parseFloat(buffer1.readLine());
				fallo = false;
			} catch (Exception e) {
				fallo = true;
			}

		} while (fallo);

		return iNumero;
	}

	public static double leerDouble(String sPregunta) {
		BufferedReader buffer1 = new BufferedReader(new InputStreamReader(System.in));
		boolean fallo;
		double iNumero = 0;

		do {
			System.out.print(sPregunta);

			try {
				iNumero = Float.parseFloat(buffer1.readLine());
				fallo = false;
			} catch (Exception e) {
				fallo = true;
			}

		} while (fallo);

		return iNumero;
	}

	public static String leerString(String sPregunta) {
		BufferedReader buffer1 = new BufferedReader(new InputStreamReader(System.in));
		boolean fallo;
		String lectura = "";

		do {
			System.out.print(sPregunta);

			try {
				lectura = (buffer1.readLine());
				fallo = false;
			} catch (Exception e) {
				fallo = true;
			}

		} while (fallo);

		return lectura;
	}

	public static String leerString(String sPregunta, String valor) {
		BufferedReader buffer1 = new BufferedReader(new InputStreamReader(System.in));
		boolean fallo;
		String lectura = "";

		do {
			System.out.print(sPregunta + "[ Enter: " + valor + " ]: ");

			try {
				lectura = (buffer1.readLine());
				if (lectura.length() == 0) {
					lectura = valor;

				}
				fallo = false;
			} catch (Exception e) {
				fallo = true;
			}

		} while (fallo);

		return lectura;
	}

	public static String leerString(String sPregunta, int valor) {
		BufferedReader buffer1 = new BufferedReader(new InputStreamReader(System.in));
		boolean fallo;
		String lectura = "";

		do {
			System.out.print(sPregunta + "[ Enter: " + valor + " ]: ");

			try {
				lectura = (buffer1.readLine());
				if (lectura.length() == 0) {
					lectura += valor;

				}
				fallo = false;
			} catch (Exception e) {
				fallo = true;
			}

		} while (fallo);

		return lectura;
	}

}
