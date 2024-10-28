package es.studium.PracticaADT1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	private static final String RUTA = "Listas de la compra";
	private static ListaDeLaCompra listaDeLaCompra;
	private static ArticuloAComprar articulo;

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		boolean esOpcionValida;
		char opcionUsuarioMenuPrincipal;
		char opcionUsuario;
		char opcionUsuarioSobreescribir = ' ';
		String nombreLista = "";

		File Listas_Dir = new File(RUTA);
		Listas_Dir.mkdirs();

		listaDeLaCompra = new ListaDeLaCompra();

		System.out.println("*** BIENVENID@ A LA APLICACIÓN PARA GESTIONAR TICKETS DE LA COMPRA ***");
		mostrarOpcionesMenuPrincipal();
		System.out.print("Opción escogida: ");
		opcionUsuarioMenuPrincipal = teclado.nextLine().toUpperCase().charAt(0);
		esOpcionValida = false;
		opcionUsuarioMenuPrincipal = validacionOpcionUsuarioMenuPrincipal(teclado, esOpcionValida,
				opcionUsuarioMenuPrincipal);
		System.out.println("-------------------------------------------------------------------------");
		if (opcionUsuarioMenuPrincipal == 'L') {
			//---> CONSULTAS");
			File[] listaFicheros = Listas_Dir.listFiles();
			String[] listaNombresFicheros = Listas_Dir.list();
			System.out.println("Las listas de la compra guardadas son:");
			int l =0;
			for(String nombreFichero :  listaNombresFicheros) {
				System.out.println(nombreFichero+" ("+l+").");
				l++;
			}
			System.out.println(" ");
			System.out.print("Escoja el número de la lista que desea consultar: ");
			int eleccionLista = teclado.nextInt();
			teclado.nextLine();
			
			System.out.println(" ");
			System.out.println("*** "+listaNombresFicheros[eleccionLista]+" ***");
			try {
				FileReader fr = new FileReader(listaFicheros[eleccionLista]);
	            BufferedReader br = new BufferedReader(fr);
	            
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                System.out.println(linea);
	            }
			}
			catch (IOException e) {
	            System.out.println("Error al leer el archivo: " + e.getMessage());
	        }
		}

		if (opcionUsuarioMenuPrincipal == 'C') {
			System.out.print("Introduzca un nombre para la nueva lista de la compra: ");
			nombreLista = teclado.nextLine();

			listaDeLaCompra.setNombreLista(nombreLista);
			System.out.println("El nombre de la lista es: *** " + listaDeLaCompra.getNombreLista() + " ***");
			System.out.println(" ");
			System.out.println("*** MENÚ CREACIÓN DE NUEVAS LISTAS ***");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println(
					"Si desea anadir un artículo a la lista pulse 'A'.\nSi desea eliminar un artículo de la lista pulse 'E'.\nPara guardar la lista y salir de la aplicación pulse 'G'. ");
			System.out.print("Opción escogida: ");
			opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
			System.out.println("-------------------------------------------------------------------------");
			esOpcionValida = false;
			opcionUsuario = validacionOpcionUsuario(teclado, esOpcionValida, opcionUsuario);
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

					articulo = new ArticuloAComprar(descripcion, cantidad, unidad);
					listaDeLaCompra.agregarProductoAComprar(articulo);
					System.out.println(" ");
					System.out.println("Se ha añadido " + articulo.getDescripcion() + " a la lista de la compra.");
					System.out.println("La lista de la compra ha quedado de la siguiente manera:");
					mostrarLista(listaDeLaCompra);
					System.out.println(" ");
					System.out.println("*** MENÚ CREACIÓN DE NUEVAS LISTAS ***");
					System.out.println("-------------------------------------------------------------------------");
					System.out.println(
							"Si desea anadir otro artículo a la lista pulse 'A'.\nSi desea eliminar un artículo de la lista pulse 'E'.\nPara mostrar la lista y salir de la aplicación pulse 'G'. ");
					System.out.print("Opción escogida: ");
					opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
					opcionUsuario = validacionOpcionUsuario(teclado, esOpcionValida, opcionUsuario);
					System.out.println("-------------------------------------------------------------------------");
				}
				if (opcionUsuario == 'E') {
					System.out.println("*** Ha escogido: ELIMINAR ARTÍCULO ***");
					if (listaDeLaCompra.listaArticulos.isEmpty()) {
						System.out.println(" ");
						System.out.println("No es posible eliminar ningún artículo porque la lista está vacía.");
						System.out.println("¿Desea realizar alguna otra acción?");
						System.out.println(" ");
						System.out.println("-------------------------------------------------------------------------");
						System.out.println(
								"Si desea anadir otro artículo a la lista pulse 'A'.\nSi desea eliminar un artículo de la lista pulse 'E'.\nPara mostrar la lista y salir de la aplicación pulse 'G'. ");
						System.out.print("Opción escogida: ");
						opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
						validacionOpcionUsuario(teclado, esOpcionValida, opcionUsuario);
						System.out.println("-------------------------------------------------------------------------");
					} else {
						System.out.println("Escoja el número del artículo que desea eliminar");
						int j = 0;
						for (ArticuloAComprar articulo : listaDeLaCompra.listaArticulos) {
							System.out.println(articulo.getDescripcion() + " - " + articulo.getCantidad() + " - "
									+ articulo.getUnidad() + ". (" + j + ")");
							j++;
						}
						// System.out.println("Valor de j: "+j);
						System.out.print("¿Qué artículo desea eliminar? ");
						int articuloEliminado = teclado.nextInt();
						teclado.nextLine();
						System.out.println(" ");
						System.out.println(
								"Borrando...." + listaDeLaCompra.listaArticulos.get(articuloEliminado).getDescripcion()
										+ " de la lista de la compra.");
						listaDeLaCompra.eliminarArticulo(
								listaDeLaCompra.listaArticulos.get(articuloEliminado).getDescripcion()); // .listaArticulos.remove(articuloEliminado);
						System.out.println("La lista de la compra ha quedado de la siguiente manera:");
						mostrarLista(listaDeLaCompra);
						System.out.println(" ");
						System.out.println("*** MENÚ CREACIÓN DE NUEVAS LISTAS ***");
						System.out.println("-------------------------------------------------------------------------");
						System.out.println(
								"Si desea anadir un artículo a la lista pulse 'A'.\nSi desea eliminar otro artículo de la lista pulse 'E'.\nPara mostrar la lista y salir de la aplicación pulse 'G'. ");
						System.out.print("Opción escogida: ");
						opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
						validacionOpcionUsuario(teclado, esOpcionValida, opcionUsuario);
						System.out.println("-------------------------------------------------------------------------");
					}
				}
			}
			if (opcionUsuario == 'G') {

				File coincidencia;
				do {
					coincidencia = new File("Listas de la compra\\" + listaDeLaCompra.getNombreLista() + ".txt");

					if (coincidencia.exists()) {
						System.out.println("Ya existe un archivo con ese nombre");
						System.out.print("¿Desea sobreescribirlo? S/N ");
						opcionUsuarioSobreescribir = teclado.nextLine().toUpperCase().charAt(0);
						opcionUsuarioSobreescribir = validacionOpcionUsuarioSobreescribir(teclado, esOpcionValida,
								opcionUsuarioSobreescribir);

						if (opcionUsuarioSobreescribir == 'N') {
							System.out.print("Introduzca un nuevo nombre para la lista de la compra: ");
							nombreLista = teclado.nextLine();

							listaDeLaCompra.setNombreLista(nombreLista);
						}
					}
				} while (coincidencia.exists() && opcionUsuarioSobreescribir == 'N');

				try {
					FileWriter fw = new FileWriter("Listas de la compra\\" + listaDeLaCompra.getNombreLista() + ".txt",
							false);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter salida = new PrintWriter(bw);
					for (ArticuloAComprar articulo : listaDeLaCompra.listaArticulos) {
						salida.println(articulo.getDescripcion() + " - " + articulo.getCantidad() + " - "
								+ articulo.getUnidad() + ".");
					}
					salida.close();
					bw.close();
					fw.close();
					// System.out.println("La lista de la compra
					// '"+listaDeLaCompra.getNombreLista()+"' se ha guardado correctamente");
				} catch (IOException k) {
					System.out.println("Se produjo un error de Archivo");
				}

				System.out.println("La lista de la compra '" + listaDeLaCompra.getNombreLista()
						+ "' se ha guardado correctamente");
				System.exit(0);
			}
		}

	}

	private static void mostrarOpcionesMenuPrincipal() {
		System.out.println(" ");
		System.out.println("*** MENÚ PRINCIPAL ***");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Para consultar una lista creada pulse 'L'.\nPara crear una nueva lista pulse 'C'");
	}

	private static void mostrarLista(ListaDeLaCompra listaDeLaCompra) {
		if (listaDeLaCompra.listaArticulos.isEmpty()) {
			System.out.println(" ");
			System.out.println("*** " + listaDeLaCompra.getNombreLista() + "***");
			System.out.println("La lista de la compra está vacía");
		} else {
			System.out.println("*** " + listaDeLaCompra.getNombreLista() + "***");
			for (ArticuloAComprar articulo : listaDeLaCompra.getListaArticulos()) {
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
				System.out.println(" ");
				System.out.println("*** MENÚ CREACIÓN DE NUEVAS LISTAS ***");
				System.out.println("-------------------------------------------------------------------------");
				System.out.println(
						"Si desea anadir un artículo a la lista pulse 'A'.\nSi desea eliminar otro artículo de la lista pulse 'E'.\nPara mostrar la lista y salir de la aplicación pulse 'G'. ");
				System.out.print("Opción escogida: ");
				opcionUsuario = teclado.nextLine().toUpperCase().charAt(0);
				System.out.println("-------------------------------------------------------------------------");
			}
		}
		return opcionUsuario;
	}

	private static char validacionOpcionUsuarioSobreescribir(Scanner teclado, boolean esOpcionValida,
			char opcionUsuarioSobreescribir) {
		while (!esOpcionValida) {
			if (opcionUsuarioSobreescribir == 'S' || opcionUsuarioSobreescribir == 'N') {
				esOpcionValida = true;
			} else {
				System.err.println("La opción escogida no está disponible");
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("¿Desea sobreescribirlo? S/N");
				System.out.print("Opción escogida: ");
				opcionUsuarioSobreescribir = teclado.nextLine().toUpperCase().charAt(0);
				System.out.println("-------------------------------------------------------------------------");
			}
		}
		return opcionUsuarioSobreescribir;
	}

	private static char validacionOpcionUsuarioMenuPrincipal(Scanner teclado, boolean esOpcionValida,
			char opcionUsuarioMenuPrincipal) {
		while (!esOpcionValida) {
			if (opcionUsuarioMenuPrincipal == 'L' || opcionUsuarioMenuPrincipal == 'C') {
				esOpcionValida = true;
			} else {
				System.err.println("La opción escogida no está disponible");
				System.out.println("*** MENÚ PRINCIPAL ***");
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("Para consultar una lista creada pulse 'L'.\nPara crear una neuva lista pulse 'C'");
				System.out.print("Opción escogida: ");
				opcionUsuarioMenuPrincipal = teclado.nextLine().toUpperCase().charAt(0);
				System.out.println("-------------------------------------------------------------------------");
			}
		}
		return opcionUsuarioMenuPrincipal;
	}
}
