package logic.logTipoEmpleado;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbm.Oracle;
import model.pojo.TipoEmpleado;
import oracle.jdbc.OracleTypes;

public class TipoEmpleadoLog {

	public static List<TipoEmpleado> getTipoEmpleado() throws SQLException {
		Oracle.openConnection();

		List<TipoEmpleado> listaTipoEmp = new ArrayList<TipoEmpleado>();

		// Llamada a función que no recibe parámetros y retorna un CURSOR (?)
		String sql = "{ ? = call GEST_TIPO_EMPLEADO.LISTAR	 }";
		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Indicamos que la función va a devolver un CURSOR
		orden.registerOutParameter(1, OracleTypes.CURSOR);

		orden.execute();

		// Almacenamos el CURSOR retornado (SELECT) en una variable de tipo ResultSet
		ResultSet resultado = (ResultSet) orden.getObject(1);

		while (resultado.next()) {
			listaTipoEmp.add(new TipoEmpleado(resultado.getInt(1), resultado.getString(2), resultado.getInt(3)));
		}

		Oracle.closeConnection();

		return listaTipoEmp;
	}

	public static void delete(int id) throws SQLException {
		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_TIPO_EMPLEADO.ELIMINAR(?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, id);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static void insert(TipoEmpleado t) throws SQLException {

		String nombreTipo = t.getNombre();
		Float sal = t.getSal();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_TIPO_EMPLEADO.CREAR(?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setString(1, nombreTipo);
		orden.setFloat(2, sal);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static void update(TipoEmpleado t) throws SQLException {

		int id = t.getIdTipo();
		String nombre = t.getNombre();
		Float sal = t.getSal();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_TIPO_EMPLEADO.ACTUALIZAR(?, ?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, id);
		orden.setString(2, nombre);
		orden.setFloat(3, sal);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

}
