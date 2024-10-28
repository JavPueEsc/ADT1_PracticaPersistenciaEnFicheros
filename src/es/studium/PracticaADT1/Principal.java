package es.studium.PracticaADT1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// Productos de la tienda
		ArticuloAComprar arroz = new ArticuloAComprar("Arroz", 1, "paquete de un Kg");
		ArticuloAComprar leche = new ArticuloAComprar("Leche", 6, "bricks de 1L");
		ArticuloAComprar guisantes = new ArticuloAComprar("Guisantes", 2, "bolsas de 500g");
		ArticuloAComprar detergente = new ArticuloAComprar("Detergente", 1, "garrafa de 5L");
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
		String nombreLista = "";

		File Listas_Dir = new File("Listas de la compra");
		Listas_Dir.mkdirs();

		ListaDeLaCompra listaDeLaCompra = new ListaDeLaCompra();

		System.out.print("Introduzca un nombre para la nueva lista de la compra: ");
		nombreLista = teclado.nextLine();

		listaDeLaCompra.setNombreLista(nombreLista);
		System.out.println("El nombre de la lista es: *** " + listaDeLaCompra.getNombreLista() + " ***");
		
		System.out.println("-------------------------------------------------------------------------");
		System.out.println(
				"Si desea anadir un artículo a la lista pulse 'A'.\nSi desea eliminar un artículo de la lista pulse 'E'.\nPara guardar la lista y salir de la aplicación pulse 'G'. ");
		System.out.print("Opción escogida: ");
		opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
		System.out.println("-------------------------------------------------------------------------");
		esOpcionValida = false;
		opcionUsuario= validacionOpcionUsuario(teclado, esOpcionValida, opcionUsuario);
		while (opcionUsuario != 'G') {
			if (opcionUsuario == 'A') {
				System.out.println("*** Ha escogido: AÑADIR ARTÍCULO ***");
				System.out.print("Escriba el artículo que desea añadir a la lista: ");
				String descripcion = teclado.nextLine();
				int cantidad = 0;
				boolean cantidadValida = false;
				while (!cantidadValida) {
					try {
						System.out.print("Escriba la cantidad del artículo que desee comprar: ");
						cantidad = teclado.nextInt();
						cantidadValida = true;
					} catch (InputMismatchException e) {
						System.err.println("Error: Por favor, ingrese un número entero válido.");
					}
					teclado.nextLine();
				}
				System.out.print("¿En que formato se presenta dicho artículo? ");
				String unidad = teclado.nextLine();

				ArticuloAComprar articulo = new ArticuloAComprar(descripcion, cantidad, unidad);
				listaDeLaCompra.listaArticulos.add(articulo);
				System.out.println(" ");
				System.out.println("Se ha añadido "+articulo.getDescripcion()+" a la lista de la compra.");
				System.out.println("La lista de la compra ha quedado de la siguiente manera:");
				mostrarLista(listaDeLaCompra);
				System.out.println(" ");
				System.out.println("-------------------------------------------------------------------------");
				System.out.println(
						"Si desea anadir otro artículo a la lista pulse 'A'.\nSi desea eliminar un artículo de la lista pulse 'E'.\nPara mostrar la lista y salir de la aplicación pulse 'S'. ");
				System.out.print("Opción escogida: ");
				opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
				opcionUsuario= validacionOpcionUsuario(teclado, esOpcionValida, opcionUsuario);
				System.out.println("-------------------------------------------------------------------------");
			}
			if (opcionUsuario == 'E') {
				System.out.println("*** Ha escogido: ELIMINAR ARTÍCULO ***");
				if(listaDeLaCompra.listaArticulos.isEmpty()) {
					System.out.println(" ");
					System.out.println("No es posible eliminar ningún artículo porque la lista está vacía.");
					System.out.println("¿Desea realizar alguna otra acción?");
					System.out.println(" ");
					System.out.println("-------------------------------------------------------------------------");
					System.out.println(
							"Si desea anadir otro artículo a la lista pulse 'A'.\nSi desea eliminar un artículo de la lista pulse 'E'.\nPara mostrar la lista y salir de la aplicación pulse 'S'. ");
					System.out.print("Opción escogida: ");
					opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
					validacionOpcionUsuario(teclado, esOpcionValida, opcionUsuario);
					System.out.println("-------------------------------------------------------------------------");
				}
				else {
					System.out.println("Escoja el número del artículo que desea eliminar");
					int j=0;
					for (ArticuloAComprar articulo : listaDeLaCompra.listaArticulos) {
						System.out.println(articulo.getDescripcion() + " - " + articulo.getCantidad() + " - "
								+ articulo.getUnidad() + ". ("+j+")");
						j++;
					}
					//System.out.println("Valor de j: "+j);
					System.out.print("¿Qué artículo desea eliminar? ");
					int articuloEliminado = teclado.nextInt();
					teclado.nextLine();
					System.out.println(" ");
					System.out.println("Borrando...."+listaDeLaCompra.listaArticulos.get(articuloEliminado).getDescripcion()+" de la lista de la compra.");
					listaDeLaCompra.listaArticulos.remove(articuloEliminado);
					System.out.println("La lista de la compra ha quedado de la siguiente manera:");
					mostrarLista(listaDeLaCompra);
					System.out.println(" ");
					System.out.println("-------------------------------------------------------------------------");
					System.out.println(
							"Si desea anadir un artículo a la lista pulse 'A'.\nSi desea eliminar otro artículo de la lista pulse 'E'.\nPara mostrar la lista y salir de la aplicación pulse 'S'. ");
					System.out.print("Opción escogida: ");
					opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
					validacionOpcionUsuario(teclado, esOpcionValida, opcionUsuario);
					System.out.println("-------------------------------------------------------------------------");
				}
			}
		}
		if (opcionUsuario == 'G') {
			mostrarLista(listaDeLaCompra);
			System.exit(0);
		}

	}

	private static void mostrarLista(ListaDeLaCompra listaDeLaCompra) {
		if (listaDeLaCompra.listaArticulos.isEmpty()) {
			System.out.println(" ");
			System.out.println("*** " + listaDeLaCompra.getNombreLista() + "***");
			System.out.println("La lista de la compra está vacía");
		} else {
			System.out.println("*** " + listaDeLaCompra.getNombreLista() + "***");
			for (ArticuloAComprar articulo : listaDeLaCompra.listaArticulos) {
				System.out.println(articulo.getDescripcion() + " - " + articulo.getCantidad() + " - "
						+ articulo.getUnidad() + ".");
			}
		}
	}

	private static char validacionOpcionUsuario(Scanner teclado, boolean esOpcionValida, char opcionUsuario) {
		while (!esOpcionValida) {
			if (opcionUsuario == 'A' || opcionUsuario == 'E' || opcionUsuario == 'G') {
				esOpcionValida = true;
			} else {
				System.err.println("La opción escogida no está disponible");
				System.out.println("-------------------------------------------------------------------------");
				System.out.println(
						"Si desea anadir un artículo a la lista pulse 'A'.\nSi desea eliminar un artículo de la lista pulse 'E'.\nPara mostrar la lista y salir de la aplicación pulse 'S'. ");
				System.out.print("Opción escogida: ");
				opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
				System.out.println("-------------------------------------------------------------------------");
			}
		}
		return opcionUsuario;
	}
}
