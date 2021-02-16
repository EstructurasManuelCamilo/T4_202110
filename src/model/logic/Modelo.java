package model.logic;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo <T extends Comparable<T>>
{
	/**
	 * Atributos del modelo del mundo
	 */
	private ArregloDinamico<T> datos;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datos = new ArregloDinamico(7);
	}
	
	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datos = new ArregloDinamico(capacidad);
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.size();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(T dato)
	{	
		datos.addLast(dato);
	}
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public T buscar(T dato)
	{
		return (T) datos.getElement(dato);
	}
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public T eliminar(T dato)
	{
		return (T) datos.deleteElement(dato);
	}
	
	public void invertir()
	{
		datos.invertir();
	}
	
	@Override 
	public String toString()
	{
		if (darTamano() == 0)
			return "[]";
		else{
			String resp = "[";
			for (int i = 0; i < darTamano() - 1; i++) 
			{
				resp += datos.getElement(i) + ",";
			}
			resp += datos.getElement(darTamano() - 1) +"]";
			return resp;
		}
	}
	
	
}
