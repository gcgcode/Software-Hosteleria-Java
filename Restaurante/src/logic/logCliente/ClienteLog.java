package logic.logCliente;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbm.Oracle;
import model.pojo.Cliente;
import model.pojo.Persona;
import model.query.ViewCliente;
import oracle.jdbc.OracleTypes;

public class ClienteLog {

	public static void insert(Persona p, Cliente c) throws SQLException {

		String dni = p.getDni();
		String nombre = p.getNombre();
		String apellido1 = p.getApellido1();
		String apellido2 = p.getApellido2();
		String domicilio = p.getDomicilio();
		String telefono = p.getTelefono();
		String email = p.getEmail();
		int idProvincia = p.getIdProvincia();

		String numTarjeta = c.getNumTarjeta();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_CLIENTE.CREAR(?, ?, ?, ?, ?, ?, ?, ?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setString(1, dni);
		orden.setString(2, nombre);
		orden.setString(3, apellido1);
		orden.setString(4, apellido2);
		orden.setString(5, domicilio);
		orden.setString(6, telefono);
		orden.setString(7, email);
		orden.setInt(8, idProvincia);
		orden.setString(9, numTarjeta);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static List<ViewCliente> getClientes() throws SQLException {
		Oracle.openConnection();

		List<ViewCliente> listaCliente = new ArrayList<ViewCliente>();

		// Llamada a función que no recibe parámetros y retorna un CURSOR (?)
		String sql = "{ ? = call GEST_CLIENTE.LISTAR	 }";
		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Indicamos que la función va a devolver un CURSOR
		orden.registerOutParameter(1, OracleTypes.CURSOR);

		orden.execute();

		// Almacenamos el CURSOR retornado (SELECT) en una variable de tipo ResultSet
		ResultSet resultado = (ResultSet) orden.getObject(1);

		// Guardamos los resultados del cursor en una lista
		while (resultado.next()) {
			listaCliente.add(new ViewCliente(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
					resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7),
					resultado.getString(8), resultado.getString(9), resultado.getInt(10), resultado.getString(11),
					resultado.getInt(12)));

		}

		Oracle.closeConnection();

		return listaCliente;
	}
	
	public static List<ViewCliente> listarPorNombre(String nombre) throws SQLException {
		Oracle.openConnection();

		List<ViewCliente> listaCliente = new ArrayList<ViewCliente>();

		// Llamada a función que no recibe parámetros y retorna un CURSOR (?)
		String sql = "{ ? = call GEST_CLIENTE.LISTAR_NOMBRE(?)	 }";
		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Indicamos que la función va a devolver un CURSOR
		orden.registerOutParameter(1, OracleTypes.CURSOR);
		orden.setString(2, nombre);
		orden.execute();

		// Almacenamos el CURSOR retornado (SELECT) en una variable de tipo ResultSet
		ResultSet resultado = (ResultSet) orden.getObject(1);

		// Guardamos los resultados del cursor en una lista
		while (resultado.next()) {
			listaCliente.add(new ViewCliente(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
					resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7),
					resultado.getString(8), resultado.getString(9), resultado.getInt(10), resultado.getString(11),
					resultado.getInt(12)));

		}

		Oracle.closeConnection();

		return listaCliente;
	}

	public static void update(Persona p, Cliente c) throws SQLException {
		int idPersona = p.getIdPersona();
		String dni = p.getDni();
		String nombre = p.getNombre();
		String apellido1 = p.getApellido1();
		String apellido2 = p.getApellido2();
		String domicilio = p.getDomicilio();
		String telefono = p.getTelefono();
		String email = p.getEmail();
		int idProvincia = p.getIdProvincia();
		String numTarjeta = c.getNumTarjeta();

		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_CLIENTE.ACTUALIZAR(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setString(1, dni);
		orden.setString(2, nombre);
		orden.setString(3, apellido1);
		orden.setString(4, apellido2);
		orden.setString(5, domicilio);
		orden.setString(6, telefono);
		orden.setString(7, email);
		orden.setInt(8, idProvincia);
		orden.setString(9, numTarjeta);
		orden.setInt(10, idPersona);

		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

	public static void delete(int idPersona) throws SQLException {
		// Instrucción para llamar al PROCEDIMIENTO
		String sql = "{ call GEST_CLIENTE.ELIMINAR(?) }";

		Oracle.openConnection();

		CallableStatement orden = Oracle.conn.prepareCall(sql); // Preparamos la orden de llamada

		// Asignamos valores a cada interrogante (?)
		orden.setInt(1, idPersona);
		
		// Ejecutar orden
		orden.execute();

		Oracle.closeConnection();

	}

}
