package es.studium.PracticaADT1;

public class ArticuloAComprar {

	private String descripcion;
	private int cantidad;
	private String unidad;
	
	// Constructor vacío.
	public ArticuloAComprar() {
		this.descripcion ="";
		this.cantidad =0;
		this.unidad="";
	}
	
	//Constructor por parámetros.
	public ArticuloAComprar(String descripcion, int cantidad, String unidad) {
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.unidad= unidad;
	}
	
	//Métodos Get & Set
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
}
