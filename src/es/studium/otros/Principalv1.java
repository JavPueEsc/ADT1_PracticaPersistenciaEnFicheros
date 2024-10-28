package es.studium.otros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import es.studium.PracticaADT1.ArticuloAComprar;

public class Principalv1 {
	static Scanner teclado = new Scanner(System.in);
	static File Listas_Dir = new File("Listas de la compra");
	static String[] listaDeListasDeLaCompra;
	static int i;
	static int listaEscogida;
	static int opcionMenu;
	static int opcionAdd;
	static int opcionEscogida;
	static boolean esOpcionValida;
	
	
	public static void main(String[] args) {
		/*Scanner teclado = new Scanner(System.in);
		File Listas_Dir = new File("Listas de la compra");
		String[] listaDeListasDeLaCompra;
		int i;
		int listaEscogida;
		int opcionMenu;
		int opcionAdd;
		int opcionEscogida;
		boolean esOpcionValida;*/
		
		Listas_Dir.mkdirs();
		
		//Menu principal
		
		opcionMenu = mostrarMenuPrincipal(teclado);
		
		// 1. Elige opción de abrir lista existente
		if(opcionMenu==1) {
			System.out.println(" ");
			System.out.println("**LISTAS DE LA COMPRA DISPONIBLES**");
			System.out.println("---------------------------------------------------------");
			listaDeListasDeLaCompra = Listas_Dir.list();
			i = mostrarListadoDeListasDeLaCompra(listaDeListasDeLaCompra);
			System.out.println("---------------------------------------------------------");
			System.out.print("Escoja una lista de la compra: ");
			listaEscogida = teclado.nextInt();
			teclado.nextLine();
			
			esOpcionValida = false;
			while(!esOpcionValida) {
				if(listaEscogida>=0 && listaEscogida<i) {
					//System.out.println("Opcion valida");
					esOpcionValida=true;
				}
				else {
					System.err.println("La opción seleccionada no se encuentra disponible.");
					System.out.println(" ");
					System.out.println("**LISTAS DE LA COMPRA DISPONIBLES**");
					System.out.println("---------------------------------------------------------");
					listaDeListasDeLaCompra = Listas_Dir.list();
					i = mostrarListadoDeListasDeLaCompra(listaDeListasDeLaCompra);
					System.out.println("---------------------------------------------------------");
					System.out.print("Escoja una lista de la compra: ");
					listaEscogida = teclado.nextInt();
					teclado.nextLine();
				}
			}
			System.out.println("Opción escogida: "+listaDeListasDeLaCompra[listaEscogida]);
			//System.out.println("Valor de i:"+i);
			System.out.println("**MOSTRANDO LISTA DE LA COMPRA SELECCIONADA**");
			
			mostrarListaPorConsola(listaDeListasDeLaCompra, listaEscogida);
			
			opcionEscogida = mostrarOpcionesLista(teclado);
			
			while(opcionEscogida!=3) {
				if(opcionEscogida==1) {
					adicionDeArtículoALaLista(teclado, listaDeListasDeLaCompra, listaEscogida);
					opcionEscogida = mostrarOpcionesLista(teclado);
				}
				if(opcionAdd==2) {
					try {
						FileReader fr = new FileReader("Listas de la compra\\"+listaDeListasDeLaCompra[listaEscogida]);
						BufferedReader entrada = new BufferedReader(fr);
						String s;
						while ((s = entrada.readLine()) != null) {
							String[] atributosArticulo= s.split(" - ");
							
						}
						entrada.close();
						fr.close();
					}
					catch (FileNotFoundException e) {
						System.out.println("Archivo NO encontrado");
					} catch (IOException i) {
						System.out.println("Se produjo un error de Archivo");
					}
					
				}
			}
			if(opcionEscogida==3) {
				System.out.println("Adios");
				System.exit(0);
			}
			
		}	
	}

	//METODOS
	/*private static void preguntarSiSeAnadeOtroArticulo() {
		System.out.println("Si desea añadir otro artículo pulse 1\nEn caso contrario pulse 2");
		System.out.print("¿Que desea hacer?");
		opcionAdd = teclado.nextInt();
		teclado.nextLine();
		esOpcionValida = false;
		while(!esOpcionValida) {
			if(opcionAdd==1 ||opcionAdd==2) {
				esOpcionValida = true;
			}
			else {
				System.err.println("La opción seleccionada no se encuentra disponible.");
				System.out.println("Si desea añadir otro artículo pulse 1\nEn caso contrario pulse 2");
				System.out.print("¿Que desea hacer?");
				opcionAdd = teclado.nextInt();
				teclado.nextLine();
			}
		}
	}*/

	private static int mostrarOpcionesLista(Scanner teclado) {
		int opcionEscogida;
		boolean esOpcionValida;
		System.out.println("---------------------------------------------------------");
		System.out.println("Para añadir un artículo pulse 1\nPara eliminar un artículo pulse 2\nPara salir pulse 3");
		System.out.print("¿Que desea hacer? ");
		opcionEscogida =teclado.nextInt();
		teclado.nextLine();
		esOpcionValida = false;
		while(!esOpcionValida) {
			if((opcionEscogida==1)||(opcionEscogida==2)||(opcionEscogida==3)) {
				esOpcionValida = true;
			}
			else {
				System.err.println("La opción seleccionada no se encuentra disponible.");
				System.out.println("---------------------------------------------------------");
				System.out.println("Para añadir un artículo pulse 1\nPara eliminar un artículo pulse 2\nPara salir pulse 3");
				System.out.print("¿Que desea hacer? ");
				opcionEscogida =teclado.nextInt();
				teclado.nextLine();
			}
			
		}
		return opcionEscogida;
	}

	private static int mostrarListadoDeListasDeLaCompra(String[] listaDeListasDeLaCompra) {
		int i;
		i=0;
		for(String listaDeLaCompra: listaDeListasDeLaCompra) {
			
			System.out.println(i+"."+listaDeLaCompra);
			i++;
		}
		return i;
	}

	private static int mostrarMenuPrincipal(Scanner teclado) {
		int opcionMenu;
		boolean esOpcionValida;
		System.out.println("**MENU PRINCIPAL**");
		System.out.println("---------------------------------------------------------");
		System.out.println("Buscar una lista - 1\nCrear una nueva lista - 2\nSalir -3");
		System.out.println("---------------------------------------------------------");
		System.out.print("Seleccione una opción: ");
		opcionMenu = teclado.nextInt();
		teclado.nextLine();
		
		esOpcionValida = false;
		
		while(!esOpcionValida) {
			if((opcionMenu==1)||(opcionMenu==2)||(opcionMenu==3)) {
				esOpcionValida = true;
			}
			else {
				System.err.println("La opción seleccionada no se encuentra disponible.");
				
				System.out.println("**MENU PRINCIPAL**");
				System.out.println("---------------------------------------------------------");
				System.out.println("Buscar una lista - 1\nCrear una nueva lista - 2\nSalir -3");
				System.out.println("---------------------------------------------------------");
				System.out.print("Seleccione una opción: ");
				opcionMenu = teclado.nextInt();
				teclado.nextLine();
			}
		}
		return opcionMenu;
	}

	private static void adicionDeArtículoALaLista(Scanner teclado, String[] listaDeListasDeLaCompra,
			int listaEscogida) {
		System.out.print("¿Qué artículo desea incluir en la lista?");
		String descripcion = teclado.nextLine();
		
		System.out.print("¿Qué cantidad de dicho artículo desea incluir en la lista?");
		int cantidad = teclado.nextInt();
		teclado.nextLine();
		
		System.out.print("¿En qué formato se presenta dicho artículo?");
		String unidad = teclado.nextLine();
		
		ArticuloAComprar articulo = new ArticuloAComprar(descripcion,cantidad,unidad);
		
		try {
			FileWriter fw = new FileWriter("Listas de la compra\\"+listaDeListasDeLaCompra[listaEscogida], true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println(articulo.getDescripcion()+" - "+articulo.getCantidad()+" - "+articulo.getUnidad()+".");
			salida.close();
			bw.close();
			fw.close();
			System.out.println("La lista de la compra se ha modificado correctamente");
			mostrarListaPorConsola(listaDeListasDeLaCompra, listaEscogida);
			System.out.println("---------------------------------------------------------");
		}
		catch (IOException k) {
			System.out.println("Se produjo un error de Archivo");
		}
	}

	
	private static void mostrarListaPorConsola(String[] listaDeListasDeLaCompra, int listaEscogida) {
		try {
			FileReader fr = new FileReader("Listas de la compra\\"+listaDeListasDeLaCompra[listaEscogida]);
			BufferedReader entrada = new BufferedReader(fr);
			String s;
			while ((s = entrada.readLine()) != null) {
				System.out.println(s);
			}
			entrada.close();
			fr.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Archivo NO encontrado");
		} catch (IOException j) {
			System.out.println("Se produjo un error de Archivo");
		}
	} 

	private static void mostrarContenido(ArrayList<ArticuloAComprar> listaArticulos) {
		if(listaArticulos.isEmpty()) {
			System.out.println("La lista de la compra está vacía.");
		}
		else {
			for(ArticuloAComprar articulo:listaArticulos) {
				System.out.println(articulo.getDescripcion()+" - "+articulo.getCantidad()+" - "+articulo.getUnidad()+".");
			}
		} 
	}
}
