package es.studium.PracticaADT1;

import java.util.ArrayList;


public class ListaDeLaCompra {

	String nombreLista;
	ArrayList<ArticuloAComprar>listaArticulos;
	
	//Constructor vacío.
	public ListaDeLaCompra() {
		nombreLista ="";
		listaArticulos = new ArrayList<>();
	}
	
	//Constructor por parámetros
	public ListaDeLaCompra(String nombreLista, ArrayList<ArticuloAComprar>listaArticulos) {
		this.nombreLista = nombreLista;
		this.listaArticulos = listaArticulos;
	}

	//Métodos Get & Set
	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}
	
	public void agregarProductoAComprar(ArticuloAComprar articulo) {
		listaArticulos.add(articulo);
	}

	public void eliminarArticulo (String descripcionArticulo) {
		for (int i = 0; i < listaArticulos.size(); i++) {
	        if (listaArticulos.get(i).getDescripcion().equals(descripcionArticulo)) {
	            listaArticulos.remove(i);
	        }
	    }
	}

	public ArrayList<ArticuloAComprar> getListaArticulos() {
		return listaArticulos;
	}
	
}
