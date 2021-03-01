package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico <T extends Comparable<T>> implements ILista <T>
{
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos = (T[]) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}


	public void addLast(T element) 
	{
		insertElement(element, tamanoAct);
	}

	public int darCapacidad() 
	{
		return tamanoMax;
	}


	public T getElement(int i) 
	{
		if(i < 0 || i > tamanoMax)
			return null; 
		return elementos[i];
	}

	public T getElement(T dato) 
	{
		T resp = null;
		for (int j = 0; j < tamanoAct; j++) 
		{
			if( elementos[j].equals(dato))
			{
				resp = elementos[j];
			}

			resp = elementos[j];
		}

		return resp;
	}

	public T deleteElement(T dato) 
	{
		T elim = null;
		boolean termino = false;
		for ( int i = 0; i < tamanoAct && !termino; i++)
		{
			if(elementos[i].compareTo(dato)==0)
			{
				elim = elementos[i];
				for (int j = i; j < tamanoAct; j++) 
				{
					elementos[j] = elementos[j+1];
				}
				tamanoAct--;
				termino = true;
			}
		}
		return elim;
	}


	public void invertir()
	{
		T[] invertido = (T[])new Comparable [tamanoMax];

		for (int i=0, j = tamanoAct-1; i < tamanoAct; i++, j--)
		{
			invertido[j] = elementos[i];
		}

		elementos = invertido;
	}


	public void addFirst(T element) 
	{
		insertElement(element, 0);
	}

	public void insertElement(T element, int pos) 
	{
		T [ ] copia = elementos;
		if ( tamanoAct == tamanoMax )
		{  
			// caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			elementos = (T[])new Comparable [tamanoMax];

			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}

		for ( int i = 0, j = 0; i < tamanoAct; i++, j++)
		{
			if(pos == i)
			{
				elementos[i] = element;
				j--;
				System.out.println("Algo");
			}
			else
				elementos[i] = copia[j];
		} 
		tamanoAct++;
	}

	
	public T removeFirst() 
	{
		return deleteElement(0);
	}


	public T removeLast() 
	{
		return deleteElement(tamanoAct);
	}

	public T deleteElement(int pos) 
	{
		T elim = elementos[pos];

		for (int j = pos; j < tamanoAct; j++) 
		{
			elementos[j] = elementos[j+1];
		}
		tamanoAct--;

		return elim;
	}

	public T firstElement() 
	{
		return getElement(0);
	}

	
	public T lastElement() 
	{
		return getElement(tamanoAct);
	}

	public int size() 
	{
		return tamanoAct;
	}

	public boolean isEmpty() 
	{
		return tamanoAct == 0? true: false;
	}

	public int isPresent(T element)
	{
		int resp = -1;
		for (int j = 0; j < tamanoAct; j++) 
		{
			if( elementos[j].compareTo(element) == 0) 
			{
				resp = j;
			}
		}
		return resp;
	}

	public void exchange(int pos1, int pos2) 
	{
		T segundo = getElement(pos2);
		changeInfo(pos2, getElement(pos1));
		changeInfo(pos1, segundo);
	}

	public void changeInfo(int pos, T elem) 
	{
		if(getElement(pos) != null)
		{
			elementos[pos] = elem;
		}
	}


	@Override
	public ILista<T> sublista(int numElementos) 
	{
		ILista<T> lista = new ILista<ArregloDinamico.T>() {
		};
		return invertido;
	}


}
