package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoTerminal {
	// CONEXION CON MYSQL
	private Connection conexion;
	static final String URL_CONEXION = "jdbc:mysql://localhost:33060/garajeautomatico?useSSL=false";
	static final String USUARIO = "root";
	static final String PASSWORD = "elorrieta";
	static final String FORNAME = "com.mysql.jdbc.Driver";

	public DaoTerminal() {
		// CONEXION BASE DE DATOS MYSQL
		try {
			// cargar el driver
			Class.forName(FORNAME);
			// Establecer la conexion con la bd empresa
			conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD);
			// Hay que cerrar en orden
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Imposible establecer la conexion");
			e.printStackTrace();
		}
	}

	// QUERYS MySQL 'publics final'
	public final String SQL_COMPROBARCLIETNE = "SELECT DNI FROM cliente ";
	public final String SQL_SELECTCLIENTE = "SELECT DNI,Nombre,matricula FROM cliente";
	public final String SQL_SELECTVEHICLE = "SELECT Marca,Modelo,Matricula FROM vehiculo ";
	public final String SQL_SELECTMOTOR = "SELECT matricula,LitrosAceite, Caballos FROM motor ";
	public final String SQL_INSERTCLIENTE = "INSERT INTO cliente VALUES (?,?,?)";
	public final String SQL_INSERTVEHICULO = "INSERT INTO vehiculo VALUES(?,?,?,0)";
	public final String SQL_INSERTMOTOR = "INSERT INTO motor VALUES (?,?,?)";

	// QUERYS CONSULTAS

	public boolean isRegisted(String dni) {
		// Esta funcion reciibe un dni y contrase�a y valida si se encuntra en la base
		// de datos
		Statement consulta;
		boolean usuarioRegistrado = false;
		try {
			consulta = conexion.createStatement();
			//TODO chapuzon buscar cliente por Dni, no iterrar sobre toda l atabla
			ResultSet resultado = consulta.executeQuery(SQL_COMPROBARCLIETNE);
			// Recorremos el resultado obtenido
			// Haremos un bucle mientras queden resultados
			while (resultado.next()) {
				if (resultado.getString(1).equals(dni)) {
					usuarioRegistrado = true;
				}
			}
			resultado.close();
			consulta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarioRegistrado;
	}
/**
 * Busca cliente por dni
 * @param dni es el dni del cliente 
 * @return cliente con sus datos / si no lo encuentra devuelve null
 */
	public Cliente selectClient(String dni) {
// Recoge la informacion de un usuario que ya este en la base de datos
		Statement consulta; // Consulta para la info del cliente
		Statement consulta2; // Consulta para la info del vehiculo
		Statement consulta3; // Consulta para la info del motor 
		Cliente c = new Cliente();
		Vehiculo v = new Vehiculo();
		Motor m = new Motor();
		try {
			consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(SQL_SELECTCLIENTE);
// RECOGER LOS DATOS DEL CLIENTE DE LA BASE DE DATOS MEDIANTE EL DNI QUE HA SIDO INSERTADO POR TECLADO
			while (resultado.next()) {
				if (resultado.getString(1).equals(dni)) {
					// insertamos al cliente los valores
					c.setNombre(resultado.getString(2));
					c.setDni(resultado.getString(1));
					consulta2 = conexion.createStatement();
					ResultSet resultadoVehiculo = consulta2.executeQuery(SQL_SELECTVEHICLE);
// RECOGER LOS DATOS DEL VEHICULO RELACIONADO CON EL CLIENTE MEDIANTE LA MATRICULA
					while (resultadoVehiculo.next()) {
						if (resultadoVehiculo.getString(3).equals(resultado.getString(3))) {
							// insertamos al vehiculo los valores
							v.setMarca(resultadoVehiculo.getString(1));
							v.setModelo(resultadoVehiculo.getString(2));
							v.setMatricula(resultadoVehiculo.getString(3));
							consulta3 = conexion.createStatement();
							ResultSet resultadoMotor = consulta3.executeQuery(SQL_SELECTMOTOR);
// RECOGER LOS DATOS DEL MOTOR RELACIONADO CON EL VEHICULO MEDIANTE LA MATRICULA 
							while (resultadoMotor.next()) {
								if (resultadoMotor.getString(1).equals(resultado.getString(3))) {
									m.setLitrosRestante(resultadoMotor.getInt(2));
									m.setCaballos(resultadoMotor.getInt(3));
								}
							}
						}
					}
				}else {
				//	System.out.println("no esta ");
				}
					
			}
			v.setPotencia(m);
			c.setV(v);
			System.out.print(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c;
	}

	public void insertClient(Cliente c) {
// Inserta la informacion del cliente que ha llegado nuevo y se quiere registrar 
		Vehiculo v = c.getV();
		Motor m = v.getPotencia();

		// usuario (DNI_NIE, nombre, apellidos, contrase�a)
		// Esta funcion sirve para insertar un usuario en la base de datos
		try {
// INSERTAR CLIENTE
			PreparedStatement ps = conexion.prepareStatement(SQL_INSERTCLIENTE);
			ps.setString(1, c.getDni());
			ps.setString(2, c.getNombre());
			ps.setString(3, c.getV().getMatricula());
			// el metodo para insertar se llama executeUpdate
			ps.executeUpdate();
// INSERTAR VEHICULO DEL CLIENTE
			PreparedStatement ps2 = conexion.prepareStatement(SQL_INSERTVEHICULO);
			ps2.setString(1, v.getMarca());
			ps2.setString(2, v.getModelo());
			ps2.setString(3, v.getMatricula());
			// el metodo para insertar se llama executeUpdate
			ps2.executeUpdate();
			System.out.println("Cliente insertado correctamente");
// INSERTAR MOTOR DEL VEHICULO
			PreparedStatement ps3 = conexion.prepareStatement(SQL_INSERTMOTOR);
			ps3.setString(1, v.getMatricula());
			ps3.setInt(2, m.getLitrosRestante());
			ps3.setInt(3, m.getCaballos());
			// el metodo para insertar se llama executeUpdate
			ps3.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(
					"No se ha podido insertar el Cliente (PUEDE SER QUE EL DNI YA EST� EN NUESTRA BASE DE DATOS)");
		}
	}

}
