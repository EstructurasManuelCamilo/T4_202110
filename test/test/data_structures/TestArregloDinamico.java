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
		for(int i =0; i< TAMANO*2; i++){
			arreglo.agregar(""+i);
		}
	}
	public void setUp3() {
		arreglo.agregar("q");
		arreglo.agregar("w");
		arreglo.agregar("e");
	}
	

	@Test
	public void testArregloDinamico() {
		// TODO X
		assertTrue(arreglo != null);
		assertTrue(arreglo.darCapacidad() == TAMANO);
		assertTrue(arreglo.darTamano() == 0);
		assertTrue(arreglo.darElemento(0) == null);
	}

	@Test
	public void testDarElemento() {
		setUp2();
		// TODO X
		assertTrue(arreglo.darElemento(-1) == null);
		assertTrue(arreglo.darElemento(1000) == null);
		assertEquals("1", arreglo.darElemento(1));
	}
	
	@Test
	public void invertir() {
		setUp3();
		arreglo.invertir();
	}
}
