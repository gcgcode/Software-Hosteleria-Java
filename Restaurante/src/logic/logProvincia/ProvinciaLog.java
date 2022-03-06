package logic.logProvincia;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbm.Oracle;
import model.pojo.Provincia;
import oracle.jdbc.OracleTypes;

public class ProvinciaLog {

	public static List<Provincia> getProvincias() throws SQLException {
		Oracle.openConnection();

		List<Provincia> listaProvincia = new ArrayList<Provincia>();

		// Llamada a función que no recibe parámetros y retorna un CURSOR (?)
		String sql = "{ ? = call GEST_PROVINCIA.LISTAR	 }";
		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Indicamos que la función va a devolver un CURSOR
		orden.registerOutParameter(1, OracleTypes.CURSOR);

		orden.execute();

		// Almacenamos el CURSOR retornado (SELECT) en una variable de tipo ResultSet
		ResultSet resultado = (ResultSet) orden.getObject(1);

		while (resultado.next()) {
			listaProvincia.add(new Provincia(resultado.getInt(1), resultado.getString(2)));
		}

		Oracle.closeConnection();

		return listaProvincia;
	}

}
