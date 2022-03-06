package dbm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import model.pojo.Config;
import model.pojo.Mensaje;


public class Oracle {

	// Declaración datos de conexión

	public static Connection conn;

	public static Config conf;

	public static void openConnection() {

		try {
			conn = DriverManager.getConnection(conf.getURL(), conf.getUSER(), conf.getPASS());
		} catch (SQLException e) {
			conn = null;
			System.err.println(Mensaje.ORACLE_ERROR_1 + e.getMessage());
		}

	}

	public static void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(Mensaje.ORACLE_ERROR_2 + e.getMessage());
		}

	}

	public static void leerConf() {

		String path = "database.cfg";

		try {

			FileReader fch = new FileReader(path);
			BufferedReader flujoR = new BufferedReader(fch);

			String DB_HOST = flujoR.readLine();
			String DB_PORT = flujoR.readLine();
			String DB_SSID = flujoR.readLine();
			String DB_USER = flujoR.readLine();
			String DB_PASS = flujoR.readLine();

			flujoR.close();
			fch.close();

			String URL = "jdbc:oracle:thin:@" + DB_HOST + ":" + DB_PORT + ":" + DB_SSID;

			dbm.Oracle.conf = new Config(URL, DB_USER, DB_PASS);
		} catch (FileNotFoundException fnfe) {
			System.err.println(Mensaje.ORACLE_ERROR_4);
		} catch (IOException ioe) {
			System.err.println(Mensaje.ORACLE_ERROR_3 + path);
		}

	}

	public static boolean pruebaConexionado() {
		boolean conectado;

		openConnection();
		conectado = (conn != null);
		closeConnection();

		return conectado;
	}

	

}
