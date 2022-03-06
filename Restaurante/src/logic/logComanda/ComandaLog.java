package logic.logComanda;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbm.Oracle;
import model.pojo.Comanda;
import model.query.ViewComanda;
import oracle.jdbc.OracleTypes;

public class ComandaLog {

	public static void insert(Comanda c) throws SQLException {

		int idCliente = c.getIdCliente();
		int idCarta = c.getIdCarta();
		int idEmpleado = c.getIdEmpleado();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_COMANDA.CREAR(?, ?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, idCliente);
		orden.setInt(2, idCarta);
		orden.setInt(3, idEmpleado);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static List<ViewComanda> getComandas() throws SQLException {
		Oracle.openConnection();

		List<ViewComanda> listaComanda = new ArrayList<ViewComanda>();

		// Llamada a función que no recibe parámetros y retorna un CURSOR (?)
		String sql = "{ ? = call GEST_COMANDA.LISTAR	 }";
		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Indicamos que la función va a devolver un CURSOR
		orden.registerOutParameter(1, OracleTypes.CURSOR);

		orden.execute();

		// Almacenamos el CURSOR retornado (SELECT) en una variable de tipo ResultSet
		ResultSet resultado = (ResultSet) orden.getObject(1);

		// Guardamos los resultados del cursor en una lista
		while (resultado.next()) {
			listaComanda.add(new ViewComanda(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
					resultado.getInt(4), resultado.getInt(5), resultado.getString(6), resultado.getInt(7),
					resultado.getFloat(8), resultado.getString(9), resultado.getInt(10), resultado.getString(11),
					resultado.getInt(12)));

		}

		Oracle.closeConnection();

		return listaComanda;
	}

	public static void update(Comanda c) throws SQLException {

		int idComanda = c.getIdComanda();
		int idCliente = c.getIdCliente();
		int idCarta = c.getIdCarta();
		int idEmpleado = c.getIdEmpleado();
		
		
		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_COMANDA.ACTUALIZAR(?, ?, ?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, idComanda);
		orden.setInt(2, idCliente);
		orden.setFloat(3, idCarta);
		orden.setInt(4, idEmpleado);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static void delete(int id) throws SQLException {
		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_COMANDA.ELIMINAR(?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, id);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();
	}

}
