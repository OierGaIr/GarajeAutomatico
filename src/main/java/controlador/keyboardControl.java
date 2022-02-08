package controlador;

import java.util.InputMismatchException;
import java.util.Scanner;

public class keyboardControl {

	public static String isString() {
		Scanner sc = new Scanner(System.in);
		String dni = null;
		try {
			dni =  sc.next();
		} catch (Exception e) {
			System.out.println("No has introducido una campo valido");
			isString();
		}
		return dni;
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
		// COMPROBAR NUMERO MENÚ
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
}
