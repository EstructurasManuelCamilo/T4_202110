package model.data_structures;

public class Nodo <T extends Comparable<T>>
{
	private T elemento;
	
	private Nodo <T> next;
	
	public Nodo<T> darSiguiente()
	{
		return next;
	}
	
	public T darElemento()
	{
		return elemento;
	}
	
	public void cambiarElemento(T pElemento)
	{
		elemento = pElemento;
	}
	
	public void cambiarSiguiente(Nodo pNodo)
	{
		next = pNodo;
	}
	
	public Nodo(T elemento)
	{
		this.elemento = elemento;
	}
}
