package controller;

import java.util.Iterator;
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
	private Modelo<String, Video> modelo;

	/* Instancia de la Vista*/
	private View view;

	private Ordenamientos<Video> ordenamientos;

	private ComparadorXLikes comparar;

	private boolean cargados;
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo<String, Video>();
		ordenamientos = new Ordenamientos<Video>();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String n = "";
		String cat = "";
		String pa = "";
		String et = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){

			case 1:
				if(!cargados)
				{
					view.printMessage("Inicio de lectura de los archivos.\n----------------"); 
					modelo.leerDatosTablaSimbolos();
					view.printMessage("El total de videos cargados es: ");
					view.printMessage("El total de duplas es: ");
					view.printMessage("El tiempo de ejecución promedio del método put() es: ");
				}

				view.printMessage(""); 
				modelo.leerDatosVideosArregloDinamico();
				view.printMessage("El total de videos cargados es: " + modelo.darArreglo().size());
				view.printMessage("La información del primer video es: ");
				view.printMessage("Title: " + modelo.darArreglo().getElement(0).getTitle());
				view.printMessage("Trending date: " + modelo.darArreglo().getElement(0).getTrendingDate());
				view.printMessage("Country: " + modelo.darArreglo().getElement(0).darPais());
				view.printMessage("Views: " + modelo.darArreglo().getElement(0).darVistas());
				view.printMessage("Likes: " + modelo.darArreglo().getElement(0).darLikes());
				view.printMessage("Dislikes: " + modelo.darArreglo().getElement(0).darDisLikes());
				
				view.printMessage("Fecha publicacion en local ");
				view.printMessage("Fecha publicacion en local ");

				for(int i = 0; i < modelo.darArreglo().getElement(0).darTags().size(); i++)
					view.printMessage("Tag: " + i + " "+ modelo.darArreglo().getElement(0).darTags().getElement(i));

				cargados = true;
				break;

			case 2:
				//REQ 1
				view.printMessage("El título es: ");
				view.printMessage("El número de views son: ");
				view.printMessage("El número de likes son: ");
				view.printMessage("El número de dislikes son: ");
				view.printMessage("No se puede reportar una respuesta ");

				break;

				
			case 5:

				view.printMessage("Inserte la cantidad de videos que quiere conocer. ");
				while(n.equals(""))
				{
					n = lector.nextLine();
				}
				view.printMessage("Inserte la etiqueta entre \"\" de los videos que quiere conocer. ");
				while(et.equals(""))
				{
					et = lector.nextLine();
				}
				view.printMessage("Inserte el pais que desea consultar. ");
				while(pa.equals(""))
				{
					pa = lector.nextLine();
				}

				ArregloDinamico<Video> sol4 = modelo.masGustados(Integer.parseInt(n), et, pa);
			
				for(int i = 0; i < sol4.size();i++)
				{
					view.printMessage("Video " + i);
					view.printMessage("Title: " + sol4.getElement(i).getTitle());
					view.printMessage("Channel title: " + sol4.getElement(i).getChannel());
					view.printMessage("Publish time: " + sol4.getElement(i).darPublishTime());
					view.printMessage("Views: " + sol4.getElement(i).darVistas());
					view.printMessage("Likes: " + sol4.getElement(i).darLikes());
					view.printMessage("Dislikes: " + sol4.getElement(i).darDisLikes());
					view.printMessage("Tags: " + sol4.getElement(i).darListags());
					view.printMessage("");
				}
				n = "";
				et = "";
				pa = "";

				break;

			case 3:
				view.printMessage("El tiempo promedio es:");
					
				break;
				
			case 4: 
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
