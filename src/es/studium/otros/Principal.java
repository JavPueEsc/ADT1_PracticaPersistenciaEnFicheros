package es.studium.otros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import es.studium.PracticaADT1.ArticuloAComprar;
import es.studium.PracticaADT1.ListaDeLaCompra;

public class Principal {
	static ArrayList<ArticuloAComprar> listaArticulos;
	
	public static void main(String[] args) {
		//Productos de la tienda
		ArticuloAComprar arroz = new ArticuloAComprar("Arroz",1,"paquete de un Kg");
		ArticuloAComprar leche = new ArticuloAComprar("Leche",6,"bricks de 1L");
		ArticuloAComprar guisantes = new ArticuloAComprar("Guisantes",2,"bolsas de 500g");
		ArticuloAComprar detergente = new ArticuloAComprar("Detergente",1,"garrafa de 5L");
		ArticuloAComprar pan = new ArticuloAComprar("Pan", 3, "barras de 250g");
		ArticuloAComprar manzanas = new ArticuloAComprar("Manzanas", 4, "kilos");
		ArticuloAComprar huevos = new ArticuloAComprar("Huevos", 1, "docena");
		ArticuloAComprar papelHigienico = new ArticuloAComprar("Papel Higiénico", 2, "paquetes de 12 rollos");
		ArticuloAComprar yogur = new ArticuloAComprar("Yogur", 8, "unidades de 125g");
		ArticuloAComprar pasta = new ArticuloAComprar("Pasta", 2, "paquetes de 500g");
		ArticuloAComprar cafe = new ArticuloAComprar("Café", 1, "paquete de 250g");
		ArticuloAComprar azucar = new ArticuloAComprar("Azúcar", 1, "bolsa de 1Kg");
		ArticuloAComprar atun = new ArticuloAComprar("Atún", 3, "latas de 80g");
		ArticuloAComprar queso = new ArticuloAComprar("Queso", 1, "pieza de 500g");
		ArticuloAComprar aceite = new ArticuloAComprar("Aceite", 1, "botella de 1L");
		
	
		Scanner teclado = new Scanner(System.in);
		boolean esOpcionValida;
		char opcionUsuario;
		String nombreLista ="";
		
		File Listas_Dir = new File("Listas de la compra");
		Listas_Dir.mkdirs();
		
		ListaDeLaCompra listaDeLaCompra = new ListaDeLaCompra();
		
		System.out.print("Introduzca un nombre para la nueva lista de la compra: ");
		nombreLista = teclado.nextLine();
		
		listaDeLaCompra.setNombreLista(nombreLista);
		System.out.println("El nomnbre de la lista es: "+listaDeLaCompra.getNombreLista());
		
		System.out.print("¿Desea añadir algún artículo a la lista? S/N ");
		opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
		esOpcionValida = false;
		validacionOpcionUsuario(teclado, esOpcionValida, opcionUsuario);
		while(opcionUsuario=='S') {
			System.out.print("Escriba el artículo que desea añadir a la lista: ");
			String descripcion = teclado.nextLine();
			System.out.print("Escriba la cantidad del artículo que desee comprar: ");
			int cantidad = teclado.nextInt();
			teclado.nextLine();
			System.out.print("¿En que formato se presenta dicho artículo? ");
			String unidad = teclado.nextLine();
			
			ArticuloAComprar articulo = new ArticuloAComprar(descripcion, cantidad, unidad);
			listaArticulos.add(articulo);
			
			System.out.print("¿Desea añadior otro artículo a la lista? S/N ");
			opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
			validacionOpcionUsuario(teclado, esOpcionValida, opcionUsuario);
		}
		if(opcionUsuario =='N') {
			mostrarLista(listaDeLaCompra);
			System.out.println("¿Desea eliminar algún artículo de la lista? S/N ");
			opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
			esOpcionValida=false;
			validacionOpcionUsuario(teclado, esOpcionValida, opcionUsuario);
		}
		
		
	}

	private static void mostrarLista(ListaDeLaCompra listaDeLaCompra) {
		if(listaArticulos.isEmpty()) {
			System.out.println(" ");
			System.out.println("***Mostrando lista: "+listaDeLaCompra.getNombreLista()+"***");
			System.out.println("La lista de la compra está vacía");
		}
		else {
			System.out.println(" ");
			System.out.println("***Mostrando lista: "+listaDeLaCompra.getNombreLista()+"***");
			for(ArticuloAComprar articulo:listaArticulos) {
				System.out.println(articulo.getDescripcion()+" - "+articulo.getCantidad()+" - "+articulo.getUnidad()+".");
			}
		}
	}

	private static void validacionOpcionUsuario(Scanner teclado, boolean esOpcionValida, char opcionUsuario) {
		while(!esOpcionValida) {
			if(opcionUsuario =='S'|| opcionUsuario =='N') {
				esOpcionValida = true;
			}
			else {
				System.out.println("La opción escogida no está disponible");
				System.out.print("¿Desea añadir algún artículo a la lista? S/N ");
				opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
			}
		}
	}
}
