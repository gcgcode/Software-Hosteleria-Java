package logic.logProducto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbm.Oracle;
import model.pojo.Producto;
import model.query.ViewProducto;

import oracle.jdbc.OracleTypes;

public class ProductoLog {

	public static void insert(Producto p) throws SQLException {
		String nombre = p.getNombre();
		int idTipoProducto = p.getIdTipo();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_PRODUCTO.CREAR(?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setString(1, nombre);
		orden.setInt(2, idTipoProducto);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();
	}

	public static List<ViewProducto> getProductos() throws SQLException {
		Oracle.openConnection();

		List<ViewProducto> listaProducto = new ArrayList<ViewProducto>();

		// Llamada a función que no recibe parámetros y retorna un CURSOR (?)
		String sql = "{ ? = call GEST_PRODUCTO.LISTAR	 }";
		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Indicamos que la función va a devolver un CURSOR
		orden.registerOutParameter(1, OracleTypes.CURSOR);

		orden.execute();

		// Almacenamos el CURSOR retornado (SELECT) en una variable de tipo ResultSet
		ResultSet resultado = (ResultSet) orden.getObject(1);

		// Guardamos los resultados del cursor en una lista
		while (resultado.next()) {
			listaProducto.add(new ViewProducto(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
					resultado.getInt(4)));

		}

		Oracle.closeConnection();

		return listaProducto;
	}

	public static void update(Producto p) throws SQLException {

		int idProducto = p.getIdProducto();
		String nombre = p.getNombre();
		int idTipoProducto = p.getIdTipo();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_PRODUCTO.ACTUALIZAR(?, ?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, idTipoProducto);
		orden.setInt(2, idProducto);
		orden.setString(3, nombre);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static void delete(int id) throws SQLException {
		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_PRODUCTO.ELIMINAR(?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada
		
		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, id);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

}
