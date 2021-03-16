package model.data_structures;

public class NodoTS<K extends Comparable<K>, V> implements Comparable<NodoTS<K,V>>
{
	
	private K llave; // objeto llave (Comparable) private V valor; // objeto de informacion asociado
	
	/** La comparación de dos NodoTS depende de sus llaves */ 
	public int compareTo(NodoTS<K, V> otro)
	{
		return this.llave.compareTo( otro.llave ); 
	}
}
