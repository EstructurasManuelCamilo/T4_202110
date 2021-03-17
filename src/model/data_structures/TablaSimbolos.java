package model.data_structures;

public class TablaSimbolos<K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolos <K,V>
{

	private ILista <NodoTS<K, V>> listaNodos;
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
	
	public ILista darLlaves()
	{
		return keys;
	}
	
	public ILista<NodoTS<K,V>> darListaNodos()
	{
		return listaNodos;
	}
	
	public TablaSimbolos()
	{
		listaNodos = new ArregloDinamico<NodoTS<K,V>>(7);
	}
	
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
	 * Retorna el valor de la Key o null si no existe
	 * Entra como parámetro la key
	 */
	public V get(K key)
	{
		NodoTS<K,V> resp = null;
		int i = 0;
		while( listaNodos.size() > i && resp == null)
		{
			if( listaNodos.getElement(i).getKey().compareTo(key)  == 0)
			{
				resp = listaNodos.getElement(i);
			}
			i ++;
		}
		return resp != null? resp.getValue(): null;
	}
	
	/**
	 * Inserta una key y su valor dentro de la tabla
	 * Entra como parámetro la llave y su valor
	 */
	public void put(K key, V val)
	{ 
		//if(!contains(key))
		{
			NodoTS<K,V> agregar = new NodoTS<K,V>(key, val);
			listaNodos.addLast(agregar);
		}
//		else
//		{
//			V viejo = get(key);
//			remove(key);
//			NodoTS<K,V> agregar = new NodoTS<K,V>(key, viejo);
//			listaNodos.addLast(agregar);
//		}
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
	


