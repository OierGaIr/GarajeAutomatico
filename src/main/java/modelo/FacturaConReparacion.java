package modelo;

public class FacturaConReparacion extends FacturaSinReparacion {

	private double importeRealizado;
	private double importeAcumulado;
	
	public FacturaConReparacion() {
		super();
		
	}

	public FacturaConReparacion(double importeRealizado,double importeAcumulado , String matricula, String nombre, double importeEstancia) {
		super();
		this.importeRealizado = importeRealizado;
		this.importeAcumulado = Math.round(((importeRealizado+importeEstancia)*100.0)/100.0);
	}

	@Override
	public String toString() {
		return super.toString()+ "FacturaConReparacion [importeRealizado=" + importeRealizado + ", importeAcumulado=" + importeAcumulado
				+ "]";
	}

	/**
	 * @return the importeRealizado
	 */
	public double getImporteRealizado() {
		return importeRealizado;
	}

	/**
	 * @return the importeAcumulado
	 */
	public double getImporteAcumulado() {
		return importeAcumulado;
	}

	/**
	 * @param importeRealizado the importeRealizado to set
	 */
	public void setImporteRealizado(double importeRealizado) {
		this.importeRealizado = importeRealizado;
	}

	/**
	 * @param importeAcumulado the importeAcumulado to set
	 */
	public void setImporteAcumulado(double importeAcumulado) {
		this.importeAcumulado = importeAcumulado;
	}
	
	
	
}
