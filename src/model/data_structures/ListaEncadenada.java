package model.data_structures;

import com.sun.xml.internal.ws.api.policy.PolicyResolver;

public class ListaEncadenada <T extends Comparable<T>> implements ILista<T>
{
	private Nodo <T> first;
	public int tamanio;
	private Nodo <T> last;
	
	/**
	 * Agrega un elemento al inicio de la lista
	 * @param element que se va a agregar
	 */
	@Override
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
	@Override
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
	 * Agrega un elemento en la posición pos si la posición es una posición válida. 
	 * Los elementos que estén a partir de la 
	 * posición dada deben correrse una posición a la derecha.
	 * Las posiciones válidas son posiciones donde ya hay un
	 * elemento en la lista. La posición del primer elemento es 1, 
	 * la del segundo es 2 y así sucesivamente
	 * @param element
	 * @param pos
	 */
	@Override
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
	@Override
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
	 * Elimina el último elemento. Se retorna el elemento eliminado.
	 * @return el elemento eliminado
	 */
	@Override
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
	 * Elimina el elemento de una posición válida (mayor que -1). Se retorna el elemento eliminado.
	 * @param la posición del elemento a eliminar
	 * @return elemento eliminado
	 */
	@Override
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
	@Override
	public T firstElement() 
	{
		return first.darElemento();
	}

	/**
	 * Retorna el último elemento
	 * @return el último elemento
	 */
	@Override
	public T lastElement()
	{
		return last.darElemento();
	}

	/**
	 * Retorna el elemento en una posición válida. La posición del
	 * primer elemento es 1, la del segundo es 2 y así sucesivamente
	 * @param la posición del elemento que se busca
	 * @return el elemento en la posición especificada
	 */
	@Override
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
	 * Retorna el número de datos en el arreglo
	 * @return el tamanio del arreglo
	 */
	@Override
	public int size() 
	{
		return tamanio;
	}

	
	/**
	 * Retorna true si el arreglo No tiene datos. false en caso contrario.
	 * @return si el arreglo tiene datos o no
	 */
	@Override
	public boolean isEmpty() 
	{
		return tamanio == 0? true: false;
	}

	/**
	 * Retorna la posición válida de un elemento. Si no se 
	 * encuentra el elemento, el valor retornado es -1
	 * @param element para consultar
	 * @return la posicion del elemento
	 */
	@Override
	public int isPresent(T element)
	{
		return 0;
	}

	/**
	 * Intercambia la información de los elementos en dos posiciones válidas.
	 * @param la posición primera posición
	 * @param la posición segunta posición
	 */
	@Override
	public void exchange(int pos1, int pos2) 
	{
		Nodo<T> elementoAnterior1 = first;
		Nodo<T> elementoAnterior2 = first;
		int posElement1 = 1;
		int posElement2 = 1;
		boolean enct1 = false;
		boolean enct2 = false;
		if (pos1 > 0 && pos2 > 0 && pos1 < tamanio +1 && pos2 < tamanio +1) 
		{
			while(!enct1 || !enct2)
			{
				if(pos1 != posElement1 -1 )
				{
					elementoAnterior1 = elementoAnterior1.darSiguiente();
					posElement1 ++;
				}
				else
					enct1 = true;
				if (pos2 != posElement2 -1) //obtengo el elemento anterior al de la posicion del parametro 
				{
					elementoAnterior2 = elementoAnterior2.darSiguiente();
					posElement2 ++;
				}
				else
					enct2 = true;
			}
			Nodo<T> copiaE1 = new Nodo<T>(elementoAnterior1.darSiguiente().darElemento());
			Nodo<T> copiaE2 = new Nodo<T>(elementoAnterior1.darSiguiente().darElemento());
			//eliminar elemento 1 y 2 de la lista
			copiaE1.cambiarSiguiente(elementoAnterior1.darSiguiente().darSiguiente());
			copiaE2.cambiarSiguiente(elementoAnterior2.darSiguiente().darSiguiente());
			elementoAnterior1.cambiarSiguiente(copiaE1);
			elementoAnterior2.cambiarSiguiente(copiaE2);
			// no funciona para el primero
		}
	}

	/**
	 * Cambia la información del elemento especificado por parámetro
	 * @param pos
	 * @param elem
	 */
	@Override
	public void changeInfo(int pos, T elem) 
	{
		Nodo<T> nuevo = new Nodo<T> (elem);
		
	}
	
}
