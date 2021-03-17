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
			System.out.println("1. Cargar datos");
			System.out.println("2. Conocer el número y videos que pertenecen a un país y nombre de categoría");
			System.out.println("3. Realizar consultas aleatorias");
			System.out.println("4. Exit");
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

