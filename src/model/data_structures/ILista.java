package model.data_structures;

public interface ILista <T extends Comparable<T>>
{
	/**
	 * agrega un elemento al inicio de la lista
	 * @param element que se va a agregar
	 */
	void addFirst(T element);
	
	/**
	 * Agraga un elemento al final de la lista
	 * @param element
	 */
	void addLast(T element);
	
	/**
	 * Agrega un elemento en la posición pos si la posición es una posición válida. 
	 * Los elementos que estén a partir de la 
	 * posición dada deben correrse una posición a la derecha.
	 * Las posiciones válidas son posiciones donde ya hay un
	 * elemento en la lista. La posición del primer elemento es 1, 
	 * la del segundo es 2 y así sucesivamente
	 * @param element
	 * @param pos
	 */
	void insertElement(T element, int pos);
	
	/**
	 * Elimina el primer elemento. Se retorna el elemento eliminado.
	 * @return el elemento eliminado
	 */
	T removeFirst( );
	
	/**
	 * Elimina el último elemento. Se retorna el elemento eliminado.
	 * @return el elemento eliminado
	 */
	T removeLast( ); 
	
	/**
	 * Elimina el elemento de una posición válida (mayor que -1). Se retorna el elemento eliminado.
	 * @param la posición del elemento a eliminar
	 * @return elemento eliminado
	 */
	T deleteElement( int pos);
	
	/**
	 * Retorna el primer elemento
	 * @return el primer elemento
	 */
	T firstElement( );
	
	/**
	 * Retorna el último elemento
	 * @return el último elemento
	 */
	T lastElement();
	
	/**
	 * Retorna el elemento en una posición válida. La posición del
	 * primer elemento es 1, la del segundo es 2 y así sucesivamente
	 * @param la posición del elemento que se busca
	 * @return el elemento en la posición especificada
	 */
	T getElement( int pos) ;
	
	/**
	 * Retorna el número de datos en el arreglo
	 * @return el tamanio del arreglo
	 */
	int size( );
	
	/**
	 * Retorna true si el arreglo No tiene datos. false en caso contrario.
	 * @return si el arreglo tiene datos o no
	 */
	boolean isEmpty( );
	
	/**
	 * Retorna la posición válida de un elemento. Si no se 
	 * encuentra el elemento, el valor retornado es -1
	 * @param element para consultar
	 * @return la posicion del elemento
	 */
	int isPresent (T element);
	
	/**
	 * Intercambia la información de los elementos en dos posiciones válidas.
	 * @param la posición primera posición
	 * @param la posición segunta posición
	 */
	void exchange (int pos1, int pos2);
	
	/**
	 * Cambia la información del elemento especificado por parámetro
	 * @param pos
	 * @param elem
	 */
	void changeInfo (int pos, T elem);

}
