package model.data_structures;

public class ListaEncadenada <T extends Comparable<T>> implements ILista<T>
{
	private Nodo <T> first;
	private Nodo <T> last;

	public int tamanio;

	/**
	 * Agrega un elemento al inicio de la lista
	 * @param element que se va a agregar
	 */

	public ListaEncadenada()
	{
		tamanio = 0;
		first = null;
		last = null;
	}
	
	public ListaEncadenada(T elemento)
	{
		first = new Nodo<T> (elemento);
		last = first;
		tamanio = 1;
	}

	public void addFirst(T element)
	{
		Nodo <T> nuevo = new Nodo <T> (element);
		if(isEmpty())
		{
			first = nuevo;
			last = nuevo;
		}
		else
		{
			nuevo.cambiarSiguiente(first);
			first = nuevo;
		}
		tamanio ++;
	}

	/**
	 * Agraga un elemento al final de la lista
	 * @param element
	 */

	public void addLast(T element)
	{
		Nodo<T> nuevo = new Nodo<T>(element);

		if (isEmpty()) 
		{
			first = nuevo;
			last = nuevo;
		}
		else
		{
			last.cambiarSiguiente(nuevo);
			last = nuevo;
		}
		tamanio ++;
	}

	/**
	 * Agrega un elemento en la posici�n pos si la posici�n es una posici�n v�lida. 
	 * Los elementos que est�n a partir de la 
	 * posici�n dada deben correrse una posici�n a la derecha.
	 * Las posiciones v�lidas son posiciones donde ya hay un
	 * elemento en la lista. La posici�n del primer elemento es 1, 
	 * la del segundo es 2 y as� sucesivamente
	 * @param element
	 * @param pos
	 */

	public void insertElement(T element, int pos) 
	{

		Nodo <T> nuevo = new Nodo<T>(element);
		Nodo <T> anterior = first;
		Nodo<T> actual = anterior.darSiguiente();

		int contador = 1;
		if (isEmpty()) 
		{
			first = nuevo;
			last = nuevo;
		}
		else if(pos > 0 && pos < tamanio +1)
		{
			while(contador < tamanio + 1 && pos != contador)
			{
				actual = actual.darSiguiente();
			}
			nuevo.cambiarSiguiente(actual);
			anterior.cambiarSiguiente(nuevo);	
		}
		tamanio ++;
	}



	/**
	 * Elimina el primer elemento. Se retorna el elemento eliminado.
	 * @return el elemento eliminado
	 */
	
	public T removeFirst() 
	{
		Nodo<T> viejo = first;
		if (isEmpty()) 
			return null;
		else
		{
			first = first.darSiguiente();
			tamanio --;
			return viejo.darElemento();
		}
	}
	
	/**
	 * Elimina el �ltimo elemento. Se retorna el elemento eliminado.
	 * @return el elemento eliminado
	 */
	
	public T removeLast() 
	{
		Nodo<T> viejo = last;
		Nodo<T> actual = first;
		if (isEmpty()) 
			return null;
		else
		{
			while(actual.darSiguiente() != last)
			{
				actual = actual.darSiguiente();
			}
			actual.cambiarSiguiente(null);
			last = actual;
			tamanio --;
			return viejo.darElemento();
		}
	}
	
	/**
	 * Elimina el elemento de una posici�n v�lida (mayor que -1). Se retorna el elemento eliminado.
	 * @param la posici�n del elemento a eliminar
	 * @return elemento eliminado
	 */
	
	public T deleteElement(int pos) 
	{
		if(pos > 0 || pos < tamanio +1 || !isEmpty())
			return null;
		else
		{
			Nodo<T> anterior = first;
			Nodo<T> actual = anterior.darSiguiente();
			int contador = 1;
			while(contador != pos)
			{
				anterior = actual;
				actual = actual.darSiguiente();
			}
			anterior.cambiarSiguiente(actual.darSiguiente());
			tamanio --;
			return actual.darElemento();
		}
	}
	
		/**
		 * Retorna el primer elemento
		 * @return el primer elemento
		 */
		
		public T firstElement() 
		{
			return isEmpty()? null: first.darElemento();
		}
	
		/**
		 * Retorna el �ltimo elemento
		 * @return el �ltimo elemento
		 */
		@Override
		public T lastElement()
		{
			return isEmpty()? null: last.darElemento();
		}
	
	
	
	/**
	 * Retorna el elemento en una posici�n v�lida. La posici�n del
	 * primer elemento es 1, la del segundo es 2 y as� sucesivamente
	 * @param la posici�n del elemento que se busca
	 * @return el elemento en la posici�n especificada
	 */
	
	public T getElement(int pos) 
	{
		if(pos < 0 || pos > tamanio +1 || isEmpty())
			return null;
		else
		{
			Nodo<T> actual = first;
			int contador = 1;
			while(contador < tamanio + 1 && contador != pos)
			{
				actual = actual.darSiguiente();
				contador ++;
			}
			return actual.darElemento();
		}
	}
	
	/**
	 * Retorna el n�mero de datos en el arreglo
	 * @return el tamanio del arreglo
	 */
	
	public int size() 
	{
		return tamanio;
	}
	
		/**
		 * Retorna la posici�n v�lida de un elemento. Si no se 
		 * encuentra el elemento, el valor retornado es -1
		 * @param element para consultar
		 * @return la posicion del elemento
		 */
		
		public int isPresent(T element)
		{
			int contador = 1;
			Nodo<T> actual = first;
			while(actual.darElemento().compareTo(element) != 0)
			{
				actual = actual.darSiguiente();
				contador ++;
			}
			if (actual.darElemento().compareTo(element) == 0) 
				return contador;
			else
				return -1;
		}
	
	
	/**
	 * Retorna true si el arreglo No tiene datos. false en caso contrario.
	 * @return si el arreglo tiene datos o no
	 */
	
	public boolean isEmpty() 
	{
		return tamanio == 0? true: false;
	}
	
	/**
	 * Retorna la posici�n v�lida de un elemento. Si no se 
	 * encuentra el elemento, el valor retornado es -1
	 * @param element para consultar
	 * @return la posicion del elemento
	 */
	
	/**
	 * Intercambia la informaci�n de los elementos en dos posiciones v�lidas.
	 * @param la posici�n primera posici�n
	 * @param la posici�n segunta posici�n
	 */
	
	public void exchange(int pos1, int pos2) 
	{
		Nodo<T> elementoActual1 = first;
		Nodo<T> elementoActual2 = first;
		int posElement1 = 1;
		int posElement2 = 1;
		boolean enct1 = false;
		boolean enct2 = false;
		if (pos1 > 0 && pos2 > 0 && pos1 < tamanio +1 && pos2 < tamanio +1) 
		{
			while(!enct1 || !enct2)
			{
				if(pos1 != posElement1 )
				{
					elementoActual1 = elementoActual1.darSiguiente();
					posElement1 ++;
				}
				else
					enct1 = true;
				if (pos2 != posElement2) //obtengo el nodo de la posicion del parametro 
				{
					elementoActual2 = elementoActual2.darSiguiente();
					posElement2 ++;
				}
				else
					enct2 = true;
			}
			T copiaE1 = elementoActual1.darElemento();
			T copiaE2 = elementoActual1.darElemento();
			elementoActual1.cambiarElemento(copiaE1);
			elementoActual2.cambiarElemento(copiaE2);
		}
	}
	
	
	
	
	/**
	 * Cambia la informaci�n del elemento especificado por par�metro
	 * @param pos
	 * @param elem
	 */
	
	public void changeInfo(int pos, T elem) 
	{
		if(pos > 0  && pos < tamanio +1)
		{
			Nodo<T> actual = first;
			int contador = 1;
			while(pos != contador)
			{
				actual.darSiguiente();
				contador ++;
			}
			if(actual.darElemento() == elem)
				actual.cambiarElemento(elem);
		}
	}
	
	@Override
	public ListaEncadenada<T> sublista(int numElementos) 
	{
		// TODO implementar codigo subLista en ListaE
		Nodo<T> actual = first;
		int contador = 0;
		ListaEncadenada<T> copia = new ListaEncadenada<T>();
		while(actual != null || numElementos != contador)
		{
			copia.addLast(actual.darElemento());
			actual = actual.darSiguiente();
			contador ++;
		}
		return copia;
		
	}

}
