package model.utils;

import java.util.Comparator;

import model.data_structures.ILista;

public class Ordenamientos <T extends Comparable<T>> 
{
	public Ordenamientos() 
	{
		
	}
	long TInicio, TFin, tiempo;
	
	public final void ordenarSeleccion(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		TInicio = System.currentTimeMillis();
		for (int i = 1; i < lista.size(); i ++)
		 {
			 int posMayorMenor = i;
			 for (int j = i+1; j <= lista.size(); j++)
			 	{
					 int factorComparacion = (ascendente?1:-1) * criterio.compare(lista.getElement(j), lista.getElement(posMayorMenor));
					 	if (factorComparacion < 0)
					 		posMayorMenor = j;
			 	}
			 lista.exchange(posMayorMenor, i);
		 }
		 TFin = System.currentTimeMillis();
		 tiempo = TFin - TInicio;
		 System.out.println("Tiempo de ejecucion en milisegundos: " + tiempo);
	}
	
	/* Ordenamiento de N elementos en posiciones [1, N], con criterio de comparacion,
	ascendentemente o descendentemente */
	public final void ordenarInsercion(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		TInicio = System.currentTimeMillis();
		int n = lista.size();
		for (int i = 1+1; i <= n; i++)
		{
			 boolean enPosicion = false;
			 for (int j = i-1; j > 1 && !enPosicion; j -= 1)
			{
				 int factorComparacion = (ascendente?1:-1) * criterio.compare(lista.getElement(j), lista.getElement(j-1));
				 if (factorComparacion < 0)
			 		lista.exchange(j, j-1);
				 else
					 enPosicion = true;
			 }
		}
		TFin = System.currentTimeMillis();
		 tiempo = TFin - TInicio;
		 System.out.println("Tiempo de ejecucion en milisegundos: " + tiempo);
	 }
	public final void ordenarShell(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	 {
		TInicio = System.currentTimeMillis();
		 int n = lista.size();
		 int h = 1;
		 while (h < n/3)
			 h = 3 * h + 1;
		
		 while (h >=1)
		 {
			 // generalizacion del alg. Insertion sort con un valor h >= 1
			 for (int i = h+1; i <= n; i++)
			 {
				 boolean enPosicion = false;
				 for (int j = i-1; j > h && !enPosicion; j -= h)
				 {
					 int factorComparacion = (ascendente?1:-1) * criterio.compare(lista.getElement(j), lista.getElement(j-h));
					 if (factorComparacion < 0)
						 lista.exchange(j, j-h);
					 else
						 enPosicion = true;
				 }
			 }
			 h /= 3;
		 }
		 TFin = System.currentTimeMillis();
		 tiempo = TFin - TInicio;
		 System.out.println("Tiempo de ejecucion en milisegundos: " + tiempo);
	 }
	
	public final void ordenarSort(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	 {
		TInicio = System.currentTimeMillis();
		 int i = 2;
		 while (i <= lista.size())
		 {
			 int factorComparacion = (ascendente?1:-1) * criterio.compare(lista.getElement(i),
			lista.getElement(i-1));
			 if (factorComparacion >= 0)
				 i++;
			 else
			 {
				 lista.exchange(i, i-1);
				 i--;
				 if (i == 1)
					 i = 2;
			 }
		 }
		 TFin = System.currentTimeMillis();
		 tiempo = TFin - TInicio;
		 System.out.println("Tiempo de ejecucion en milisegundos: " + tiempo);
	 }
	public void ordenarMerge(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		System.out.println(lista.firstElement());
		int size = lista.size();
		if(size>1)
		{
			int mid = size/2;
			
			ILista<T> leftList = lista.subList(1, mid);
			ILista<T> rightList = lista.subList(mid+1, size-mid);
			
			ordenarMerge(leftList, criterio, ascendente);
			ordenarMerge(rightList, criterio, ascendente);
			
			int i = 1;
			int j = 1;
			int k = 1;
			
			int leftElements = leftList.size();
			int rightElements = rightList.size();
			
			while(i<=leftElements && j<=rightElements)
			{
				T elemi = leftList.getElement(i);
				T elemj = rightList.getElement(j);
				
				int comparacion = (ascendente?1:-1)*criterio.compare(elemi, elemj);
				if(comparacion<=0)
				{
					lista.changeInfo(k, elemi);	
					i++;
				}
				else
				{
					lista.changeInfo(k, elemj);
					j++;
				}
				k++;
			}
			
			while(i<=leftElements)
			{
				lista.changeInfo(k, leftList.getElement(i));
				i++;
				k++;
			}
			
			while(j<=rightElements)
			{
				lista.changeInfo(k, rightList.getElement(j));
				j++;
				k++;
			}
		}	 
	}
	
	public void ordenarQuickSort(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		TInicio = System.currentTimeMillis();
		sort(lista, criterio, ascendente, 1, lista.size());
		TFin = System.currentTimeMillis();
		 tiempo = TFin - TInicio;
		 System.out.println("Tiempo de ejecucion en milisegundos: " + tiempo);
	 
	}
	
	private void sort(ILista<T> lista, Comparator<T> criterio, boolean ascendente, int lo, int hi)
	{
		if(lo>=hi)
		{
			return;
		}
		int pivot = partition(lista,criterio,ascendente,lo,hi);
		sort(lista, criterio, ascendente, lo, pivot-1);
		sort(lista, criterio, ascendente, pivot+1, hi);
	}
	
	/**
	* Método que va dejando el pivot en su lugar, mientras mueve elementos menores
	* a la izquierda del pivot y elementos mayores a la derecha del pivot.
	*/
	
	private final int partition(ILista<T> lista, Comparator<T> criterio, boolean ascendente, int lo, int hi)
	{
		int follower = lo;
		int leader = lo;
		while(leader<hi)
		{
			int comparacion = (ascendente?1:-1)*criterio.compare(lista.getElement(leader), lista.getElement(hi));
			if(comparacion<0)
			{
				lista.exchange(follower, leader);
				follower++;
			}
			leader++;
		}
		lista.exchange(follower, hi);
		return follower;
	}
}
