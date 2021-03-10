package controller;

import java.util.Scanner;

import model.logic.Modelo;
import model.logic.Video;
import model.utils.Ordenamientos;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo<Video> modelo;

	/* Instancia de la Vista*/
	private View view;

	private Ordenamientos<Video> ordenamientos;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo<Video>();
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
		String dato = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){

			case 1:
				view.printMessage("Inicio de lectura de los archivos."); 
				view.printMessage("Las categorías cargargadas son:");
				for( int i = 1; i < modelo.leerCategorias().size(); i++ )
				{
					view.printMessage("Id: "+ modelo.leerCategorias().getElement(i).darIdCat() + " Nombre: "+ modelo.leerCategorias().getElement(i).darNombreCat());
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
				for(int i = 0; i < modelo.darArreglo().getElement(0).darTags().size(); i++)
					view.printMessage("Tag: " + i + " "+ modelo.darArreglo().getElement(0).darTags().getElement(i));
				break;

			case 2:
				 // REQUERIMIENTO 1
				Video.ComparadorXLikes compardorXLikes = new Video.ComparadorXLikes();
				ordenamientos.ordenarShell(modelo.darArreglo(), compardorXLikes, true);
				
				view.printMessage("Inserte la cantidad de videos que quiere conocer. ");
				while(n.equals(""))
				{
					n = lector.nextLine();
				}
				view.printMessage("Inserte la categoría de los videos que quiere conocer. ");
				while(cat.equals(""))
				{
					cat = lector.nextLine();
				}
				view.printMessage("Inserte el pais que desea consultar. ");
				while(pa.equals(""))
				{
					pa = lector.nextLine();
				}

				modelo.mejoresVideosCatPa(Integer.parseInt(n), cat, pa);
				n = "";
				cat = "";
				pa = "";
				break;

			case 3: 
				// REQUERIMIENTO 2
				view.printMessage("Ingrese el país del cual quiere obtener la información");
				dato = lector.next();
				Video vid = modelo.videoTendenciaPais(dato);
				view.printMessage("El título del video es: " + vid.getTitle());
				view.printMessage("El nombre del canal es: " + vid.getChannel());
				view.printMessage("El país del video es: " + vid.darPais());
				view.printMessage("El número de días como tendencia es: " + vid.getTrendingDate());
				break;
			case 4:
				// REQUERIMIENTO 3
				
				break;
			case 5:
				// REQUERIMIENTO 4
				Video.ComparadorXLikes compardorXlikes = new Video.ComparadorXLikes();
				ordenamientos.ordenarShell(modelo.darArreglo(), compardorXlikes, true);
				
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

				modelo.masGustados(Integer.parseInt(n), et, pa);
				n = "";
				et = "";
				pa = "";
				break;
				
			case 7: 
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

//view.printMessage("Inserte el tamanio deseado de la muestra."); 
//while(dato.equals(""))
//{
//	dato = lector.nextLine();
//}
//if(esArreglo)
//{
//	modelo.muestraArreglo(Integer.valueOf(dato));
//	for(int i = 1; i < modelo.muestraArreglo(Integer.valueOf(dato)).size(); i++)
//	{
//		try
//		{
//			System.out.println("El titulo del video " + i + " es: " + ((Video) modelo.muestraArreglo(Integer.valueOf(dato)).getElement(i-1)).getTitle());
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//}
//else 
//{
//	for(int i = 1; i < modelo.muestraLista(Integer.valueOf(dato)).size(); i++)
//	{
//		try
//		{
//			System.out.println("El titulo del video " + i + " es: " + ((Video) modelo.muestraLista(Integer.valueOf(dato)).getElement(i)).getTitle());
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//}
//
//view.printMessage("");
//

