package model.logic;

public class Categoria 
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

	public int darId()
	{
		return id;
	}
}
