package model.logic;

public class Categoria implements Comparable<Categoria>
{
	private String nombre;
	private int id;

	public Categoria(int pId, String pNom)
	{
		nombre = pNom;
		id = pId;
	}

	public String darNombreCat()
	{
		return nombre;
	}

	public int darIdCat()
	{
		return id;
	}

	/** Comparación natural de acuerdo a algún atributo con identificación única
	* @return valor 0 si this y otro son iguales. Numero negativo si this es menor a otro. 
	 * Numero positivo this es mayor a otro */
	@Override
	public int compareTo(Categoria o) 
	{
		return (this.id - o.id);
	}
}
