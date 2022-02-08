package modelo;

public class Cliente {
	private String dni;
	private String nombre;
	private Vehiculo v;
	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the v
	 */
	public Vehiculo getV() {
		return v;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @param v the v to set
	 */
	public void setV(Vehiculo v) {
		this.v = v;
	}
	@Override
	public String toString() {
		return "$_____________________________\n" + "| DNI = " + dni +"\n" + "| Nombre = " + nombre +"\n" + "| Vehiculo :" + v ;
	}
	
	
}
