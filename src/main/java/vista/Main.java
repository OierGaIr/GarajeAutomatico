package vista;

import modelo.Functions;

public class Main {

	
	public static void main(String[] args) {
		welcome();
		Functions.isRegistered();
		
 	}
	
	public static void welcome() {
		System.out.println(" |     BIENVENIDO    |");
		System.out.println(" |         AL        |");
		System.out.println(" | GARAJE AUTOMATICO |");
		System.out.println(" |    DE KABIECES    |");
		System.out.println("  ------------------- ");
	}
	public static void showMenu() {
		
		System.out.println(" OPCIONES : \n Opcion 1 : Aparcar \n Opcion 2 : Desaparcar \n Opcion 3 : Reparar vehiculo \n Opcion 4 : Salir ");
		
	}
	
}
