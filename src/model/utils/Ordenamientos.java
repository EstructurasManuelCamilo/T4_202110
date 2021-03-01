package model.utils;

import java.util.Comparator;

import model.data_structures.ILista;

public class Ordenamientos <T extends Comparable<T>> 
{
	public final void ordenarSeleccion(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
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
	}
	
	/* Ordenamiento de N elementos en posiciones [1, N], con criterio de comparacion,
	ascendentemente o descendentemente */
	public final void ordenarInsercion(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
	int n = lista.size();
		for (int i = 1+1; i <= n; i++)
		{
			 boolean enPosicion = false;
			 for (int j = i; j > 1 && !enPosicion; j -= 1)
			{
				 int factorComparacion = (ascendente?1:-1) * criterio.compare(lista.getElement(j), lista.getElement(j-1));
				 if (factorComparacion < 0)
			 		lista.exchange(j, j-1);
				 else
					 enPosicion = true;
			 }
		}
	 }
	public final void ordenarShell(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	 {
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
				 for (int j = i; j > h && !enPosicion; j -= h)
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
	 }
	
	public final void ordenarGnomo(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	 {
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
	 }
	
}
