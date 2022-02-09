package modelo;

public class FacturaSinReparacion {
		
		private String matricula;
		private String nombre;
		private double importeEstancia;
		
		// constructor sin nada
		public FacturaSinReparacion() {
		
		}
		
		// constructor con cosas
		public FacturaSinReparacion(String matricula, String nombre, double importeEstancia) {
			super();
			this.matricula = matricula;
			this.nombre = nombre;
			this.importeEstancia = importeEstancia;
		}


		@Override
		public String toString() {
			return "FACTURA: \nMatricula = " + matricula + "\nNombre = " + nombre + "\nImporte de la estancia = "
					+ importeEstancia + "€";
		}
		
		
		/**
		 * @return the matricula
		 */
		public String getMatricula() {
			return matricula;
		}
		/**
		 * @return the nombre
		 */
		public String getNombre() {
			return nombre;
		}
		/**
		 * @return the importeEstancia
		 */
		public double getImporteEstancia() {
			return importeEstancia;
		}
		/**
		 * @param matricula the matricula to set
		 */
		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}
		/**
		 * @param nombre the nombre to set
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		/**
		 * @param importeEstancia the importeEstancia to set
		 */
		public void setImporteEstancia(double importeEstancia) {
			this.importeEstancia = importeEstancia;
		}
		
		
}
