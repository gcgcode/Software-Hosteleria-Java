package logic.logTipoProducto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbm.Oracle;
import model.pojo.TipoProducto;
import model.query.ViewTipoProducto;
import oracle.jdbc.OracleTypes;

public class TipoProductoLog {

	public static void insert(TipoProducto t) throws SQLException {

		String nombreTipo = t.getNombre();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_TIPO_PRODUCTO.CREAR(?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setString(1, nombreTipo);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static List<ViewTipoProducto> listarVista() throws SQLException {
		Oracle.openConnection();

		List<ViewTipoProducto> listaTipoProducto = new ArrayList<ViewTipoProducto>();

		// Llamada a función que no recibe parámetros y retorna un CURSOR (?)
		String sql = "{ ? = call GEST_TIPO_PRODUCTO.LISTAR	 }";
		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Indicamos que la función va a devolver un CURSOR
		orden.registerOutParameter(1, OracleTypes.CURSOR);

		orden.execute();

		// Almacenamos el CURSOR retornado (SELECT) en una variable de tipo ResultSet
		ResultSet resultado = (ResultSet) orden.getObject(1);

		// Guardamos los resultados del cursor en una lista
		while (resultado.next()) {
			listaTipoProducto.add(new ViewTipoProducto(resultado.getInt(1), resultado.getString(2)));

		}

		Oracle.closeConnection();

		return listaTipoProducto;

	}

	public static void update(TipoProducto t) throws SQLException {

		int id = t.getIdProducto();
		String nombre = t.getNombre();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_TIPO_PRODUCTO.ACTUALIZAR(?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, id);
		orden.setString(2, nombre);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static void delete(int idTipoProducto) throws SQLException {
		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_TIPO_PRODUCTO.BORRAR(?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, idTipoProducto);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

}
