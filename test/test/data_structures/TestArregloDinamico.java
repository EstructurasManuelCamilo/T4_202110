package test.data_structures;

import model.data_structures.ArregloDinamico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestArregloDinamico <T extends Comparable<T>>
{

	private ArregloDinamico arreglo;
	private static int TAMANO=100;
	
	@Before
	public void setUp1() {
		arreglo= new ArregloDinamico(TAMANO);
	}

	public void setUp2() {
		for(int i =0; i< TAMANO*2; i++)
		{
			arreglo.insertElement("" + i, i);
		}
	}
	public void setUp3() {
		arreglo.insertElement("a", 0);
		arreglo.insertElement("b", 1);
		arreglo.insertElement("c", 2);
	}
	

	@Test
	public void testArregloDinamico() {
		// TODO X
		assertTrue(arreglo != null);
		assertTrue(arreglo.darCapacidad() == TAMANO);
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
	public void invertir() {
		setUp3();
		arreglo.invertir();
	}
}
