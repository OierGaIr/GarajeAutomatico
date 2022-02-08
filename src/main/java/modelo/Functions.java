package modelo;

import controlador.keyboardControl;
import vista.Main;

public class Functions {
	public static DaoTerminal conexion = new DaoTerminal();
	private static Vehiculo[][] garaje = new Vehiculo[10][10];
	
	
	//TODO metodo para tener unos coches parcados
	/**
	 * 
	 */
	private static void rellenarGarage() {
		garaje[0][0] = new Vehiculo();
		garaje[0][1] = new Vehiculo();
		garaje[1][0] = new Vehiculo();
		garaje[2][2] = new Vehiculo();
	}
	
	
	

	public static void isRegistered() {
		Cliente c = new Cliente();
		System.out.println(" Comprobemos si usted esta registrado: ");
		System.out.println(" INTRODUZCA SU DNI ");
		
		// TODO luego kitar
		// String dni = keyboardControl.isString();
		String dni = "79067576Q"; 
				
		if (conexion.isRegisted(dni)) {
			c = conexion.selectClient(dni);
			System.out.println("\n ---> BIENVENIDO A NUESTRO GARAJE <---");
			System.out.println(" Que va a hacer?");
			menu(c);

		} else {
			System.out.println(" Necesita registrarse ");
			toRegister();

		}
	}

	public static void toRegister() {
		// nuevos objetos
		Cliente c = new Cliente();
		Vehiculo v = new Vehiculo();
		Motor m = new Motor();
		// coger el dni
		System.out.println(" Tu Dni:");
		String dni = keyboardControl.isString();
		c.setDni(dni);
		// coger el nombre
		System.out.println(" Tu nombre:");
		String nombre = keyboardControl.isString();
		c.setNombre(nombre);
		// cojer la marca
		System.out.println(" La marca del coche: ");
		String marca = keyboardControl.isString();
		v.setMarca(marca);
		// coger el modelo
		System.out.println(" Modelo del vehiculo: ");
		String modelo = keyboardControl.isString();
		v.setModelo(modelo);
		// coger la matricula
		System.out.println(" La matricula: ");
		String matricula = keyboardControl.isString();
		v.setMatricula(matricula);
		// cojer la cantidad de caballos
		System.out.println(" La cantidad de caballos: ");
		int caballos = keyboardControl.isNumber();
		m.setCaballos(caballos);
		System.out.println(" Litros de aceite restantes: ");
		int litrosRestante = keyboardControl.isNumber();
		m.setLitrosRestante(litrosRestante);

		v.setPotencia(m);
		c.setV(v);

		conexion.insertClient(c);
		menu(c);
	}

	public static void menu(Cliente c) {
		Main.showMenu();
		rellenarGarage();
		int opcion = keyboardControl.isNumberMenu();
		switch (opcion) {
		case 1:
			System.out.println("Aparcar");
			System.out.println("HOLAMUNDO");
			try {
				aparcar(c);
			} catch (Exception e) {
				System.out.println( e.getMessage() );
				menu(c);
			}
			break;
		case 2:
			System.out.println("Desaparcar");
			desaparcar(c);
			break;
		case 3:
			System.out.println("Reparar");
			break;

		default:
			System.out.println("Saliendo...");
			break;
		}
	}

	/**
	 * Aparca el coche del cliente
	 * 
	 * @param c Cliente que contiene Coche
	 * @throws Exception si el coche ya esta aparcado
	 * @return true si aparca, false si no hay sitio
	 */
	public static boolean aparcar(Cliente c) throws Exception {
		boolean aparcado = false;
		Vehiculo v = c.getV();
		int i = 0;
		int j = 0;
		while (i < garaje.length && !aparcado) {
			while (j < garaje[i].length && !aparcado) {

				Vehiculo vActual = garaje[i][j];
				if (v.equals(vActual)) {
					throw new Exception("El ya esta aparcado el coche");
					
				}

				if (vActual == null) {
					System.out.println("Aparcado en: " + i + j);
					garaje[i][j] = v;
					aparcado = true;
				}
				j++;
			}
			i++;
		}
		menu(c);
		return aparcado;
	
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static boolean desaparcar(Cliente c) {
		boolean desaparcado = false;
		Vehiculo v = c.getV();
		int i = 0;
		int j = 0;
		while (i < garaje.length && !desaparcado) {
			while (j < garaje[i].length && !desaparcado) {

				Vehiculo vActual = garaje[i][j];

				if (v.equals(vActual)) {
					System.out.println("Desaparcado en: " + i + j);
					garaje[i][j] = null;
					desaparcado = true;
				}
				j++;
			}
			i++;
		}
		menu(c);
		return desaparcado;
	}

	public static void parking(Cliente c) {
		Vehiculo v = c.getV();
		int i = 0;
		int j = 0;
		boolean aparcado = false;
		while (i < garaje.length && !aparcado) {
			while (j < garaje[i].length && !aparcado) {
				if (garaje[i][j] == null) {
					System.out.println("Aparcado en: " + i + j);
					garaje[i][j] = v;
					aparcado = true;
				}
				j++;
			}
			i++;
		}
		menu(c);

	}
}
