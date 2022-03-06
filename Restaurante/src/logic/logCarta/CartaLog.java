package logic.logCarta;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbm.Oracle;
import model.pojo.Carta;
import model.query.ViewCarta;
import oracle.jdbc.OracleTypes;

public class CartaLog {

	public static void insert(Carta c) throws SQLException {

		int disponible = c.getDisponible();
		float pvp = c.getPvp();
		int idProducto = c.getIdProducto();
		int idRestaurante = c.getIdRestaurante();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_CARTA.CREAR(?, ?, ?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, disponible);
		orden.setFloat(2, pvp);
		orden.setInt(3, idProducto);
		orden.setInt(4, idRestaurante);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static List<ViewCarta> getCarta() throws SQLException {
		Oracle.openConnection();

		List<ViewCarta> listaCarta = new ArrayList<ViewCarta>();

		// Llamada a función que no recibe parámetros y retorna un CURSOR (?)
		String sql = "{ ? = call GEST_CARTA.LISTAR	 }";
		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Indicamos que la función va a devolver un CURSOR
		orden.registerOutParameter(1, OracleTypes.CURSOR);

		orden.execute();

		// Almacenamos el CURSOR retornado (SELECT) en una variable de tipo ResultSet
		ResultSet resultado = (ResultSet) orden.getObject(1);

		// Guardamos los resultados del cursor en una lista
		while (resultado.next()) {
			listaCarta.add(new ViewCarta(resultado.getInt(1), resultado.getString(2), resultado.getInt(3),
					resultado.getString(4), resultado.getInt(5), resultado.getInt(6), resultado.getFloat(7)));

		}

		Oracle.closeConnection();

		return listaCarta;
	}
	
	public static List<ViewCarta> listarPorDisponibilidad(int disp) throws SQLException {
		Oracle.openConnection();

		List<ViewCarta> listaCarta = new ArrayList<ViewCarta>();

		// Llamada a función que no recibe parámetros y retorna un CURSOR (?)
		String sql = "{ ? = call GEST_CARTA.LISTAR_DISPONIBLE(?)	 }";
		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Indicamos que la función va a devolver un CURSOR
		orden.registerOutParameter(1, OracleTypes.CURSOR);
		orden.setInt(2, disp);
		orden.execute();

		// Almacenamos el CURSOR retornado (SELECT) en una variable de tipo ResultSet
		ResultSet resultado = (ResultSet) orden.getObject(1);

		// Guardamos los resultados del cursor en una lista
		while (resultado.next()) {
			listaCarta.add(new ViewCarta(resultado.getInt(1), resultado.getString(2), resultado.getInt(3),
					resultado.getString(4), resultado.getInt(5), resultado.getInt(6), resultado.getFloat(7)));

		}

		Oracle.closeConnection();

		return listaCarta;
	}

	public static void update(Carta c) throws SQLException {

		int idCarta = c.getIdCarta();
		int disponible = c.getDisponible();
		float pvp = c.getPvp();
		int idProducto = c.getIdProducto();
		int idRestaurante = c.getIdRestaurante();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_CARTA.ACTUALIZAR(?, ?, ?, ?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, idCarta);
		orden.setInt(2, disponible);
		orden.setFloat(3, pvp);
		orden.setInt(4, idProducto);
		orden.setInt(5, idRestaurante);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static void delete(int id) throws SQLException {
		
		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_CARTA.ELIMINAR(?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, id);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

}
