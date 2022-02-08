package modelo;

public class Vehiculo {
	private String marca;
	private String modelo;
	private String matricula;
	private double ultimoArreglo;
	private boolean cobrar;
	private double importeAveriasAcumulado; 
	private Motor potencia;
	
	public Vehiculo() {
		
	}
	
	public Vehiculo(String marca, String modelo, String matricula, double ultimoArreglo, boolean cobrar,
			double importeAveriasAcumulado, Motor potencia) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
		this.ultimoArreglo = ultimoArreglo;
		this.cobrar = cobrar;
		this.importeAveriasAcumulado = importeAveriasAcumulado;
		this.potencia = potencia;
	}
	
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * @return the ultimoArreglo
	 */
	public double getUltimoArreglo() {
		return ultimoArreglo;
	}
	/**
	 * @return the cobrar
	 */
	public boolean isCobrar() {
		return cobrar;
	}
	/**
	 * @return the importeAveriasAcumulado
	 */
	public double getImporteAveriasAcumulado() {
		return importeAveriasAcumulado;
	}
	/**
	 * @return the potencia
	 */
	public Motor getPotencia() {
		return potencia;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * @param ultimoArreglo the ultimoArreglo to set
	 */
	public void setUltimoArreglo(double ultimoArreglo) {
		this.ultimoArreglo = ultimoArreglo;
	}
	/**
	 * @param cobrar the cobrar to set
	 */
	public void setCobrar(boolean cobrar) {
		this.cobrar = cobrar;
	}
	/**
	 * @param importeAveriasAcumulado the importeAveriasAcumulado to set
	 */
	public void setImporteAveriasAcumulado(double importeAveriasAcumulado) {
		this.importeAveriasAcumulado = importeAveriasAcumulado;
	}
	/**
	 * @param potencia the potencia to set
	 */
	public void setPotencia(Motor potencia) {
		this.potencia = potencia;
	}

	@Override
	public String toString() {
		return " Marca = " + marca +"\n"+"| Modelo = " + modelo +"\n" + "| Matricula = " + matricula +"\n" + "| Potencia :" + potencia  ;
	}
	
	
}
