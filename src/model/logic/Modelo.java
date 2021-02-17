package model.logic;

import model.data_structures.*;
import com.opencsv.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo <T extends Comparable<T>>
{
	/**
	 * Atributos del modelo del mundo
	 */

	long TInicio, TFin, tiempo;

	private ArregloDinamico<Video> datos;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datos = new ArregloDinamico(501);
	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datos = new ArregloDinamico(capacidad);
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.size();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(Video element)
	{	
		datos.addLast(element);
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public Video buscar(Video dato)
	{
		if(datos.isPresent(dato)!= -1)
			return datos.getElement(dato);
		else
			return null;
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public Video eliminar(Video dato)
	{
		return datos.deleteElement(dato);
	}

	public void invertir()
	{
		datos.invertir();
	}

	@Override 
	public String toString()
	{
		if (darTamano() == 0)
			return "[]";
		else{
			String resp = "[";
			for (int i = 0; i < darTamano() - 1; i++) 
			{
				resp += datos.getElement(i) + ",";
			}
			resp += datos.getElement(darTamano() - 1) +"]";
			return resp;
		}
	}

	public void leerDatosVideos()
	{
		FileReader pDatos = null;
		CSVReader reader = null;
		TInicio = System.currentTimeMillis();

		try 
		{

			pDatos = new FileReader("data/videos-small.csv");
			CSVParser separador = new CSVParserBuilder().withSeparator(',').build();
			reader = new CSVReaderBuilder(pDatos).withCSVParser(separador).build();
			String[] fila = reader.readNext();

			ArregloDinamico<String> columnas = new ArregloDinamico<String>(17);
			for(int i = 0; i < 16; i++)
			{
				columnas.insertElement(fila[i], i);
			}

			String[] primera = reader.readNext();

			Video prim = new Video(primera[0], primera[1] , primera[2], primera[3], Integer.valueOf(primera[4]), primera[5]);
			datos.insertElement(prim, 0);
			System.out.println("La información del primer video es: " );
			System.out.println("Id video: " + prim.getId());
			System.out.println("Trending_Date: " + prim.getTrendingDate() );
			System.out.println("Título: " + prim.getTitle());
			System.out.println("Título del canal: " + prim.getChannel());
			System.out.println("Id de categoría: " + prim.getCategoryId());
			System.out.println("Fecha de publicación: " + prim.getPublishTime());

			Video ultimo = prim;
			int j = 1;

			while((fila = reader.readNext()) != null)
			{

				Video nuevo = new Video(fila[0], fila[1], fila[2], fila[3], Integer.valueOf(fila[4]), fila[5]);
				datos.insertElement(nuevo, j);

				ultimo = nuevo;
			}

			System.out.println("La información del último video es: " );
			System.out.println("Id video: " + ultimo.getId());
			System.out.println("Trending_Date: " + ultimo.getTrendingDate() );
			System.out.println("Título: " + ultimo.getTitle());
			System.out.println("Título del canal: " + ultimo.getChannel());
			System.out.println("Id de categoría: " + ultimo.getCategoryId());
			System.out.println("Fecha de publicación: " + ultimo.getPublishTime());

			System.out.println("El total de video encontrados fue de: " + j);

			TFin = System.currentTimeMillis();
			tiempo = TFin - TInicio;
			System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);

		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}


	}
	
	public Date fecha(String pFecha) throws ParseException
	{
		Date fecha = new SimpleDateFormat("yyyy.dd.MM").parse(pFecha);
		return fecha;
	}

}
