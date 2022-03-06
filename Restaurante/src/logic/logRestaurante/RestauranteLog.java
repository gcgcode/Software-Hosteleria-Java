package logic.logRestaurante;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbm.Oracle;
import model.pojo.Restaurante;

import oracle.jdbc.OracleTypes;

public class RestauranteLog {

	public static void insert(Restaurante r) throws SQLException {
		String nif = r.getNif();
		String nombre = r.getNombre();
		String direccion = r.getDireccion();
		String telefono = r.getTelefono();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_RESTAURANTE.CREAR(?, ?, ?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setString(1, direccion);
		orden.setString(2, nif);
		orden.setString(3, telefono);
		orden.setString(4, nombre);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static List<Restaurante> list() throws SQLException {
		Oracle.openConnection();

		List<Restaurante> listaRestaurante = new ArrayList<Restaurante>();

		// Llamada a función que no recibe parámetros y retorna un CURSOR (?)
		String sql = "{ ? = call GEST_RESTAURANTE.LISTAR	 }";
		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Indicamos que la función va a devolver un CURSOR
		orden.registerOutParameter(1, OracleTypes.CURSOR);

		orden.execute();

		// Almacenamos el CURSOR retornado (SELECT) en una variable de tipo ResultSet
		ResultSet resultado = (ResultSet) orden.getObject(1);

		// Guardamos los resultados del cursor en una lista
		while (resultado.next()) {
			listaRestaurante.add(new Restaurante(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
					resultado.getString(4), resultado.getString(5)));

		}

		Oracle.closeConnection();

		return listaRestaurante;
	}
	
	public static List<Restaurante> listarPorNombre(String nombre) throws SQLException {
		Oracle.openConnection();

		List<Restaurante> listaRestaurante = new ArrayList<Restaurante>();

		// Llamada a función que no recibe parámetros y retorna un CURSOR (?)
		String sql = "{ ? = call GEST_RESTAURANTE.LISTAR_NOMBRE(?)	 }";
		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Indicamos que la función va a devolver un CURSOR
		orden.registerOutParameter(1, OracleTypes.CURSOR);
		orden.setString(2, nombre);
		orden.execute();

		// Almacenamos el CURSOR retornado (SELECT) en una variable de tipo ResultSet
		ResultSet resultado = (ResultSet) orden.getObject(1);

		// Guardamos los resultados del cursor en una lista
		while (resultado.next()) {
			listaRestaurante.add(new Restaurante(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
					resultado.getString(4), resultado.getString(5)));

		}

		Oracle.closeConnection();

		return listaRestaurante;
	}

	public static void delete(int idRestaurante) throws SQLException {
		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_RESTAURANTE.ELIMINAR(?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, idRestaurante);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static void update(Restaurante r) throws SQLException {

		String direccion = r.getDireccion();
		String nif = r.getNif();
		String telefono = r.getTelefono();
		String nombre = r.getNombre();
		int idRestaurante = r.getIdRestaurante();
		
		
		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_RESTAURANTE.ACTUALIZAR(?, ?, ?, ?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)

		orden.setString(1, direccion);
		orden.setString(2, nif);
		orden.setString(3, telefono);
		orden.setString(4, nombre);
		orden.setInt(5, idRestaurante);
	

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();
	}

}
