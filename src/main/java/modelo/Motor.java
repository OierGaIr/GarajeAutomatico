package modelo;

public class Motor {
	
	private int litrosRestante=0;
	private int caballos;
	
	public Motor(){
		
	}
	

	/**
	 * @return the litrosRestante
	 */
	public int getLitrosRestante() {
		return litrosRestante;
	}

	/**
	 * @return the caballos
	 */
	public int getCaballos() {
		return caballos;
	}

	/**
	 * @param litrosRestante the litrosRestante to set
	 */
	public void setLitrosRestante(int litrosRestante) {
		this.litrosRestante = litrosRestante;
	}

	/**
	 * @param caballos the caballos to set
	 */
	public void setCaballos(int caballos) {
		this.caballos = caballos;
	}

	@Override
	public String toString() {
		return " Caballos = " + caballos +"\n|_____________________________"  ;
	}
	
	
}
