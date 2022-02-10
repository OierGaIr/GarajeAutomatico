package modelo;

import controlador.keyboardControl;
import vista.Main;

public class Functions {

	public static DaoTerminal conexion = new DaoTerminal();
	public static Vehiculo[][] garaje = new Vehiculo[10][10];
	
	private static final int PRECIO_ESTANCIA_DIA = 140;
	
	private static final int MENU_APARCAR = 1;
	private static final int MENU_DESAPARCAR = 2;
	private static final int MENU_REPARAR = 3;


	// TODO metodo para tener unos coches parcados
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
		String dni = keyboardControl.isString();
		// String dni = "79067576Q";

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
			case MENU_APARCAR:
				try {
					aparcar(garaje, c);
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
					
				}
				menu(c);
				break;
			case MENU_DESAPARCAR:
				try {
					desaparcar(garaje, c);
					
				} catch (Exception e) {
					System.out.println(e.getMessage());	
				}
				rellenarFactura(c);
				menu(c);
				break;
			case MENU_REPARAR:
				comprobarAceite(c);
				reparar(c);
				rellenarFactura(c);
				menu(c);
				break;
	
			default:
				System.out.println("Saliendo...");
				Main.welcome();
				isRegistered();
				break;
		}
	}

	/**
	 * Aparca el coche del cliente
	 * 
	 * @param c Cliente que contiene Coche
	 * 
	 * @return true si aparca, false si no hay sitio
	 */
	public static void aparcar(Vehiculo[][] garaje, Cliente c) throws Exception {
		boolean aparcado = false;
		Vehiculo v = c.getV();
		int i = 0;
		int j = 0;
		if (!estaAparcado(garaje, c)) {
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
		}	
	}

	/**
	 * Comprueba que el coche esta aparcado o no
	 * 
	 * @throws Exception si el coche ya esta aparcado
	 * @return aparcado true si el coche esta en el garaje y false si no lo esta
	 */
	public static boolean estaAparcado(Vehiculo[][] garaje, Cliente c) throws Exception {
		boolean aparcado = false;
		Vehiculo v = c.getV();
		int i = 0;
		int j = 0;
		String vActual = null;
		while (i < garaje.length && !aparcado) {
			while (j < garaje[i].length && !aparcado) {
				if (garaje[i][j] != null) {
					vActual = garaje[i][j].getMatricula();
				} else if (v.getMatricula().equals(vActual)) {
					throw new Exception("¡ El coche ya esta aparcado !");
				}
				j++;
			}
			i++;
		}
		return aparcado;
	}

	/**
	 * Recorre el array y comprueba que el vehiculo aparcado concuerda con la
	 * matricula
	 * 
	 * @param c
	 * @return
	 */
	public static boolean desaparcar(Vehiculo[][] garaje, Cliente c) throws Exception {
		boolean desaparcado = false;
		Vehiculo v = c.getV();
		int i = 0;
		int j = 0;
		while (i < garaje.length && !desaparcado) {
			while (j < garaje[i].length && !desaparcado) {
				if (garaje[i][j] != null) {
					String vActual = garaje[i][j].getMatricula();
					if (v.getMatricula().equals(vActual)) {
						System.out.println("Desaparcado en: " + i + j);
						garaje[i][j] = null;
						desaparcado = true;
						rellenarFactura(c);
					} 
					} else {
						throw new Exception("¡ El coche no esta aparcado !");
				}

				j++;
			}
			i++;
		}
		
		return desaparcado;
	}

	// TODO comprobar Aceite si tiene aceite se pasa a reparar sino se suman 10
	// litros al vehiculo
	public static void comprobarAceite(Cliente c) {
		Vehiculo v = c.getV();
		Motor m = v.getPotencia();
		int motorSeco = 0;
		if (m.getLitrosRestante() == motorSeco) {
			System.out.println("Su vehiculo no tiene aceite");
			int botellaAceite = 10;
			double costeAceite = 150;
			System.out.println("¡¡ Aceite recargado !!");
			m.setLitrosRestante(botellaAceite);
			v.setUltimoArreglo(costeAceite);
			 	
		} else {
			System.out.println("Esta full de aceite");		
		}
	
	}

	public static void reparar(Cliente c) {

		Vehiculo v = c.getV();
		System.err.println(" Su vehiculo se esta arreglando... Espere");
		
		double ultimoArreglo = v.getUltimoArreglo(); 
		double arregloRandom = ( Math.round((Math.random() * (400 - 150 + 1) + 150) * 100.0) / 100.0);
		v.setImporteAveriasAcumulado( v.getImporteAveriasAcumulado()  + ultimoArreglo + arregloRandom);
		v.setUltimoArreglo(ultimoArreglo + arregloRandom);
		System.out.println("Su vehiculo se ha reparado");	
		
	}

	public static void rellenarFactura(Cliente c) {
		Vehiculo v = c.getV();

		if (v.getUltimoArreglo() > 0) {
			FacturaConReparacion fcr = new FacturaConReparacion();
			fcr.setMatricula(v.getMatricula());
			fcr.setNombre(c.getNombre());
			fcr.setImporteEstancia(PRECIO_ESTANCIA_DIA);
			fcr.setImporteRealizado(v.getUltimoArreglo());
			fcr.setImporteAcumulado(v.getUltimoArreglo() + PRECIO_ESTANCIA_DIA);
			System.out.println("FACTURA DE LA REPARACION");
			System.out.println(fcr.toString());
		} else {
			FacturaSinReparacion fsr = new FacturaSinReparacion();
			fsr.setMatricula(v.getMatricula());
			fsr.setNombre(c.getNombre());
			fsr.setImporteEstancia(PRECIO_ESTANCIA_DIA);
			System.out.println("FACTURA DE LA REPARACION");
			System.out.println(fsr.toString());
		}
		conexion.updateCliente(c);
		

	}

}
