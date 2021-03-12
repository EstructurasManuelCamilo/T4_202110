package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Leer datos de archivos.");
			System.out.println("2. Conocer n videos con más vistas por categoria de un pais");
			System.out.println("3. Conocer video con más días como tendencia de un país");
			System.out.println("4. Conocer video con más días como tendencia de una categoria");
			System.out.println("5. Conocer n videos con mas likes por etiqueta de un pais");
			System.out.println("6. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
			System.out.println(modelo.toString());
		}
		
}

