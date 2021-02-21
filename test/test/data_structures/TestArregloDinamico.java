package test.data_structures;

import model.data_structures.ArregloDinamico;
import model.data_structures.ListaEncadenada;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestArregloDinamico <T extends Comparable<T>>
{

	private ArregloDinamico<T> arreglo;
	private static int TAMANO=100;
	
	private ListaEncadenada listaEncadenada;
	private T elemento = (T) "Hola";
	
	@Before
	public void setUp1() 
	{
		arreglo= new ArregloDinamico(TAMANO);
		listaEncadenada = new ListaEncadenada<T>(elemento);
	}


	public void setUp2() 
	{
		for(int i =0; i< TAMANO*2; i++)
		{
			arreglo.insertElement((T) (""+i), i);;
		}
	}
	
	public void setUp3() 
	{
		arreglo.insertElement((T) "q", 0);
		arreglo.insertElement((T) "w", 1);
		arreglo.insertElement((T) "e", 2);
	}
	
	
	public void setUp4()
	{
		for(int i =1; i< 10; i++)
		{
			listaEncadenada.insertElement(""+i, i);
		}
		
	}

	@Test
	public void testArregloDinamico() 
	{
		
		setUp3();
		assertTrue(arreglo != null);
		assertTrue(arreglo.darCapacidad() == TAMANO);
		assertTrue(arreglo.size() == 3);
		assertTrue(arreglo.getElement(1) == "w");
	}
	
	@Test
	public void testListaEncadenada()
	{
		assertTrue(listaEncadenada != null);
		assertTrue(listaEncadenada.size() == 1);
		assertTrue(listaEncadenada.getElement(1) == elemento);
	}

	@Test
	public void testGetElementArreglo() 
	{
		setUp2();
		assertTrue(arreglo.getElement(-1) == null);
		assertTrue(arreglo.getElement(1000) == null);
		assertEquals("1", arreglo.getElement(1));
	}
	
	@Test
	public void testGetElementListaEncadenada() 
	{
		setUp4();
		assertTrue(listaEncadenada.getElement(-1) == null);
		assertTrue(listaEncadenada.getElement(1000) == null);
		assertEquals("1", arreglo.getElement(1));
	}
	
	
	@Test
	public void testAddFirstListaEncadenada()
	{
		T elementico = (T) "";
		listaEncadenada.addFirst(elementico);
		
		assertTrue(listaEncadenada.getElement(1).compareTo("") ==0);
		assertTrue(arreglo.size() == 0);
		assertTrue(arreglo.getElement(0) == null);
	}

	@Test
	public void testDarElemento() 
	{
		setUp2();
		assertTrue(arreglo.getElement(-1) == null);
		assertTrue(arreglo.getElement(1000) == null);
		assertEquals("" + 1, arreglo.getElement(1));
	}
	
	@Test
	public void testAddFirstArreglo()
	{
		T elementico = (T) "";
		arreglo.addFirst(elementico);
		
		assertTrue(arreglo.getElement(0).compareTo((T) "") == 0);
	}
	
	@Test
	public void testAddLastListaEncadenada()
	{
		T elementico = (T) "";
		listaEncadenada.addLast(elementico);
		
		assertTrue(listaEncadenada.getElement(listaEncadenada.size()).equals(""));
	}
	
	@Test
	public void testAddLastArreglo()
	{
		T elementico = (T) "";
		arreglo.addLast(elementico);
		
		assertTrue(arreglo.getElement(arreglo.size() - 1).equals(""));
	}
	
	
	
	
	
	
	
	@Test
	public void invertir() 
	{
		setUp3();
		arreglo.invertir();
	}
}
