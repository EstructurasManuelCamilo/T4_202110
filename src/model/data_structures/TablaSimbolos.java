package model.data_structures;

public class TablaSimbolos<K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolos <K,V>
{

	/**
	 * Capacidad maxima de la tabla
	 */
	private int size;
	
	/**
	 * Lista con las llaves
	 */
	private ILista<K> keys;
	
	/**
	 * Retorna la lista con las llaves
	 */
	public ILista<K> keySet()
	{
		return keys;
	}
	
	/**
	 * Lista con los valores
	 */
	private ILista<V> vals;
	
	/**
	 * Retorna la lista de valores
	 */
	public ILista<V> valueSet()
	{
		return vals;
	}
	/**
	 * Retorna la capacidad maxima del arreglo
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * Primer nodo de la lista enlazada
	 */
	private Node first;
	
	/**
	 * Clase privada del nodo
	 */
	private class Node
	{
		K key;
		V val;
		Node next;
		public Node(K key, V val, Node next)
		{
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	/**
	 * Retorna el valor de la Key o null si no existe
	 * Entra como parámetro la key
	 */
	public V get(K key)
	{
		for (Node x = first; x != null; x = x.next)
			if (key.equals(x.key))
				return x.val;
		return null;
	}
	
	/**
	 * Inserta una key y su valor dentro de la tabla
	 * Entra como parámetro la llave y su valor
	 */
	public void put(K key, V val)
	{ 
		for (Node x = first; x != null; x = x.next)
			if (key.equals(x.key))
			{ x.val = val; return; } 
		first = new Node(key, val, first); 
	}
	
	/**
	 * Elimina una llave
	 */
	public void remove (K key)
	{
		put(key,null);
	}
	
	/**
	 * Retorna un boolean si la llave existe o no
	 */
	public boolean contains(K key )
	{
		return get(key) != null;
	}
	
	/**
	 * Retorn un boolean si hay llaves o no
	 */
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
}
	


