package controller;

import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ListaEncadenada;
import model.logic.Modelo;
import model.logic.Video;
import model.logic.Video.ComparadorXLikes;
import model.utils.Ordenamientos;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	private Ordenamientos ordenamientos;
	
	private ListaEncadenada<Video> lista;
	
	private ArregloDinamico<Video> arreglo;
	
	private ComparadorXLikes comparar;
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
		ordenamientos = new Ordenamientos();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		boolean esArreglo = false;
		boolean esLista = false;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){

			case 1:
				view.printMessage("Inicio de lectura de los archivos."); 
				modelo.leerDatosVideosArregloDinamico();
				view.printMessage("Lectura completa y Arreglo Dinamico creado."); 
				esArreglo = true;
				break;
				
			case 2:
				view.printMessage("Inicio de lectura de los archivos."); 
				modelo.leerDatosVideosListaEncadenada();
				view.printMessage("Lectura completa y Lista Encadenada creada.");
				esLista = true;
				break;
			
			case 3:
				view.printMessage("Inserte el tama�o deseado de la muestra."); 
				while(dato.equals(""))
				{
					dato = lector.nextLine();
				}
				if(esArreglo)
				{
					arreglo = modelo.muestraArreglo(Integer.valueOf(dato));
					System.out.println(arreglo.size());
					for(int i = 0; i < arreglo.size(); i++)
						System.out.println("El titulo del video" + i+1 + "es: " + arreglo.getElement(i).getTitle());
				}
				else 
				{
					 lista = (ListaEncadenada<Video>) modelo.mostrar(Integer.valueOf(dato));
					 for(int i = 0; i < lista.size(); i++)
							System.out.println("El titulo del video" + i+1 + "es: " + lista.getElement(i).getTitle());
				}
				
				view.printMessage("");
				break;
				
			case 4:
				view.printMessage("Lista ordenada por insercion"); 
				Video.ComparadorXLikes compardorXLikes = new Video.ComparadorXLikes();
				ordenamientos.ordenarInsercion(modelo.darArreglo(), compardorXLikes, true);
				view.printMessage("");
				break;

			case 5: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	
				
			
				
			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
