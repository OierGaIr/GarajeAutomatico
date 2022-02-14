package controlador;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class keyboardControl {

	public static String isString() {
		Scanner sc = new Scanner(System.in);
		String String = null;
		try {
			String = sc.next();
		} catch (Exception e) {
			System.out.println("No has introducido una campo valido");
			isString();
		}
		return String;
	}

	public static int isNumber() {
		// Comprueba que lo que mete por teclado es un numero
		Scanner sc = new Scanner(System.in);
		boolean isNumber = true;
		int numero = 0;

		try {
			numero = sc.nextInt();
			isNumber = false;
		} catch (Exception e) {
			System.out.println("Su seleccion no se corresponde a lo que se le ha pedido");
			System.out.println();
//				esNumero();
		}
		return numero;
	}

	public static int isNumberMenu() {
		// COMPROBAR NUMERO MEN�
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la opcion deseada");
		int opcion = 0;
		try {
			opcion = sc.nextInt();
			if (!(1 <= opcion && opcion <= 4)) {
				System.out.println("El numero introducido no estaba entre las opciones posibles ( |1||2||3||4| )");
				opcion = isNumberMenu();
			}
		} catch (InputMismatchException e) {
			System.out.println();
			System.out.println("No has introducido un numero");
			System.out.println();
			opcion = isNumberMenu();
		}
		return opcion;
	}

	public static String isDni() {
		Scanner sc = new Scanner(System.in);
		Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
		String dni = sc.nextLine();
		Matcher mat = pat.matcher(dni);
			while (!mat.matches()) {
				System.out.println("El DNI introducido es incorrecto, por favor introduzca un DNI válido.");
				System.out.print("Introduce un DNI correcto: ");
				dni = sc.nextLine();
				mat = pat.matcher(dni);
		}
		return dni;

	}
}
