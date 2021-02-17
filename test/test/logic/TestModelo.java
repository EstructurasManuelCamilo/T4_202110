package test.logic;

import static org.junit.Assert.*;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {
	
	private Modelo modelo;
	private Modelo modelo2;
	private static int CAPACIDAD=100;
	
	@Before
	public void setUp1() {
		modelo= new Modelo(CAPACIDAD);
	}

	public void setUp2() {
		for(int i =0; i< CAPACIDAD;i++){
			//modelo.agregar(""+i);
		}
	}

	public void setUp3() {
		modelo2= new Modelo(CAPACIDAD);
		for (int i = 0; i < 98; i++) {
			//modelo2.agregar("" + i);
		}
	}
	@Test
	public void testModelo() {
		assertTrue(modelo!=null);
		assertEquals(0, modelo.darTamano());  // Modelo con 0 elementos presentes.
	}

	@Test
	public void testDarTamano() {
		// TODO X
		setUp2();
		assertEquals(100, modelo.darTamano());
	}

	@Test
	public void testAgregar() {
		setUp2();
		//modelo.agregar("");
		assertEquals(101, modelo.darTamano());
		setUp3();
		//modelo2.agregar("");
		assertEquals(99, modelo2.darTamano());
		// TODO Completar la prueba X
	}

	@Test
	public void testBuscar() {
		setUp2();
		// TODO Completar la prueba X
		//modelo.agregar("prueba");
		//assertEquals("prueba", modelo.buscar("prueba"));
		//assertEquals(null, modelo.buscar("esto no existe"));
	}

	@Test
	public void testEliminar() {
		setUp2();
		// TODO Completar la prueba X
		//modelo.agregar("prueba");
		//assertEquals("prueba", modelo.eliminar("prueba"));
		assertEquals(100, modelo.darTamano());
	}

}
