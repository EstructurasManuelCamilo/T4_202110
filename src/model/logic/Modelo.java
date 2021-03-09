package model.logic;

import model.data_structures.*;
import model.logic.Video.ComparadorXLikes;
import model.utils.Ordenamientos;

import com.opencsv.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
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

	private ILista<Video> videos; // TODO cambio ILista

	private ArregloDinamico<Video> datosArreglo; 

	private ListaEncadenada<Video> datosLista;

	private Ordenamientos ordenamientos;

	private ListaEncadenada<Video> lista;

	private ArregloDinamico<Video> arreglo;

	private ComparadorXLikes comparar;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datosArreglo = new ArregloDinamico<Video>(501);
		datosLista = new ListaEncadenada<Video>();
	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datosArreglo = new ArregloDinamico(capacidad);
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoArreglo()
	{
		return datosArreglo.size();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(Video element)
	{	
		datosArreglo.addLast(element); // es O(1)
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public Video buscar(Video dato)
	{
		if(datosArreglo.isPresent(dato)!= -1)
			return datosArreglo.getElement(dato);
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
		return datosArreglo.deleteElement(dato);
	}

	public void invertir()
	{
		datosArreglo.invertir();
	}

	@Override 
	public String toString()
	{
		if (darTamanoArreglo() == 0)
			return "[]";
		else{
			String resp = "[";
			for (int i = 0; i < darTamanoArreglo() - 1; i++) 
			{
				resp += datosArreglo.getElement(i) + ",";
			}
			resp += datosArreglo.getElement(darTamanoArreglo() - 1) +"]";
			return resp;
		}
	}

	public void leerDatosVideosArregloDinamico()
	{
		ArrayList<Categoria> categorias = leerCategorias();
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
			Video prim = new Video(primera[0], fecha1(primera[1]) , primera[2], primera[3], Integer.valueOf(primera[4]), fecha2(primera[5]), primera[5], Integer.valueOf(primera[7]), primera[8], primera[9], darNomCat(Integer.valueOf(primera[4]), categorias), primera[16]);
			datosArreglo.insertElement(prim, 0);

			// Podemos quitar todo esto 
			System.out.println("La informacion del primer video es: " );
			System.out.println("Id video: " + prim.getId());
			System.out.println("Trending_Date: " + prim.getTrendingDate() );
			System.out.println("Titulo: " + prim.getTitle());
			System.out.println("Titulo del canal: " + prim.getChannel());
			System.out.println("Id de categoria: " + prim.getCategoryId());
			System.out.println("Nombre de categoria: " + prim.darNombreCategoria());
			System.out.println("Fecha de publicacion: " + prim.getPublishTime());

			Video ultimo = prim;
			int j = 1;

			try 
			{

				while((fila = reader.readNext()) != null)
				{

					Video nuevo = new Video(fila[0], fecha1(fila[1]), fila[2], fila[3], Integer.valueOf(fila[4]), fecha2(fila[5]),fila[5], Integer.valueOf(fila[7]), fila[8], fila[9], darNomCat(Integer.valueOf(fila[4]), categorias), fila[16]);
					datosArreglo.insertElement( nuevo, j);
					j++;

					ultimo = nuevo;
				}
			}
			catch(Exception e) 
			{

			}
			System.out.println("La informacion del ultimo video es: " );
			System.out.println("Id video: " + ultimo.getId());
			System.out.println("Trending_Date: " + ultimo.getTrendingDate());
			System.out.println("Titulo: " + ultimo.getTitle());
			System.out.println("Titulo del canal: " + ultimo.getChannel());
			System.out.println("Id de categoria: " + ultimo.getCategoryId());
			System.out.println("Nombre de categoria: " + ultimo.darNombreCategoria());
			System.out.println("Fecha de publicacion: " + ultimo.getPublishTime());

			System.out.println("El total de video encontrados fue de: " + j);

			TFin = System.currentTimeMillis();
			tiempo = TFin - TInicio;
			System.out.println("Tiempo de ejecucion en milisegundos: " + tiempo);

		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}


	public void leerDatosVideosListaEncadenada()
	{
		ArrayList<Categoria> categorias = leerCategorias();
		FileReader pDatos = null;
		CSVReader reader = null;
		TInicio = System.currentTimeMillis();

		try 
		{

			pDatos = new FileReader("data/videos-small.csv");
			CSVParser separador = new CSVParserBuilder().withSeparator(',').build();
			reader = new CSVReaderBuilder(pDatos).withCSVParser(separador).build();
			String[] fila = reader.readNext();

			ListaEncadenada<String> columnas = new ListaEncadenada<>();
			for(int i = 0; i < 16; i++)
			{
				columnas.insertElement(fila[i], i);
			}

			String[] primera = reader.readNext();

			Video prim = new Video(primera[0], fecha1(primera[1]) , primera[2], primera[3], Integer.valueOf(primera[4]), fecha2(primera[5]), primera[5], Integer.valueOf(primera[7]), primera[8], primera[9], darNomCat(Integer.valueOf(primera[4]), categorias), primera[16]);

			datosLista.insertElement( prim, 0);
			System.out.println("La informacion del primer video es: " );
			System.out.println("Id video: " + prim.getId());
			System.out.println("Trending_Date: " + prim.getTrendingDate() );
			System.out.println("Titulo: " + prim.getTitle());
			System.out.println("Titulo del canal: " + prim.getChannel());
			System.out.println("Id de categoria: " + prim.getCategoryId());
			System.out.println("Nombre de categoria: " + prim.darNombreCategoria());
			System.out.println("Fecha de publicacion: " + prim.getPublishTime());

			Video ultimo = prim;
			int j = 1;

			try 
			{

				while((fila = reader.readNext()) != null)
				{
					Video nuevo = new Video(fila[0], fecha1(fila[1]), fila[2], fila[3], Integer.valueOf(fila[4]), fecha2(fila[5]),fila[5], Integer.valueOf(fila[7]), fila[8], fila[9], darNomCat(Integer.valueOf(fila[4]), categorias), fila[16]);
					datosLista.insertElement( nuevo, j);
					j++;

					ultimo = nuevo;
				}
			}
			catch(Exception e) 
			{

			}
			System.out.println("La informacion del utimo video es: " );
			System.out.println("Id video: " + ultimo.getId());
			System.out.println("Trending_Date: " + ultimo.getTrendingDate());
			System.out.println("Titulo: " + ultimo.getTitle());
			System.out.println("Titulo del canal: " + ultimo.getChannel());
			System.out.println("Id de categoria: " + ultimo.getCategoryId());
			System.out.println("Nombre de categoria: " + ultimo.darNombreCategoria());
			System.out.println("Fecha de publicacion: " + ultimo.getPublishTime());

			System.out.println("El total de videos encontrados fue de: " + j);

			TFin = System.currentTimeMillis();
			tiempo = TFin - TInicio;
			System.out.println("Tiempo de ejecucion en milisegundos: " + tiempo);

		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}


	public Date fecha1(String pFecha) throws ParseException
	{
		String[] partes = pFecha.split("\\.");
		String anio  = partes[0];
		String dia  = partes[1];
		String mes = partes[2];

		String fecha = dia + "/" + mes + "/" + anio;
		Date date =new SimpleDateFormat("dd/MM/yyyy").parse(fecha);  

		return date;
	}
	public LocalDateTime fecha2(String pFecha) throws ParseException
	{
		String[] partes = pFecha.split("\\.");
		String fechaHora  = partes[0];
		LocalDateTime fecha = LocalDateTime.parse(fechaHora);
		return fecha;
	}

	public ArrayList<Categoria> leerCategorias()
	{

		System.out.println("Se inicio la lectura de categorias");
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();

		FileReader pDatos = null;
		CSVReader reader = null;
		try 
		{

			pDatos = new FileReader("data/category-id.csv");
			CSVParser separador = new CSVParserBuilder().withSeparator('	').build();
			reader = new CSVReaderBuilder(pDatos).withCSVParser(separador).build();
			String[] fila = reader.readNext();

			String[] columnas = new String[2];
			for(int i = 0; i < 2; i++)
			{
				columnas[i] = fila[i];
			}

			System.out.println("Las categorias tienen: " + columnas[0] + " y " + columnas[1]);

			try 
			{

				while((fila = reader.readNext()) != null)
				{
					Categoria nueva = new Categoria(Integer.valueOf(fila[0]), fila[1]);
					categorias.add(nueva);
					System.out.println("Categoria: " + nueva.darIdCat() + " se llama " + nueva.darNombreCat());
				}
			}
			catch(Exception e) 
			{

			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		return categorias;

	}

	public ILista<Video> mostrar(int tamanio)
	{
		if(!datosArreglo.isEmpty()) 
		{
			ArregloDinamico<Video> muestra = (ArregloDinamico<Video>) muestraArreglo(tamanio);
			return muestra;
		}
		else
		{
			ListaEncadenada<Video> muestra = (ListaEncadenada<Video>) muestraLista(tamanio);
			Video actual = muestra.firstElement();
			int i = 1;
			while(actual != null)
			{
				System.out.println("El titulo del video " + i + " es: " + muestra.getElement(i).getTitle());
				i++;
				actual = muestra.getElement(i);
			}
			return muestra;
		}

	}
	public ArregloDinamico<Video> muestraArreglo(int tamanio)
	{
		return (ArregloDinamico<Video>) datosArreglo.sublista(tamanio);

	}
	public ListaEncadenada<Video> muestraLista(int tamanio)
	{
		return (ListaEncadenada<Video>) datosLista.sublista(tamanio) ;

	}
	public ArregloDinamico<Video> darArreglo()
	{
		return (ArregloDinamico<Video>) datosArreglo;
	}

	public ListaEncadenada<Video> darLista()
	{
		return (ListaEncadenada<Video>) datosLista;
	}

	public String darNomCat(int pId, ArrayList<Categoria> pCategorias)
	{
		String resp = "";
		for(int i = 0; i < pCategorias.size() & resp.equals(""); i++)
		{
			if(pId == pCategorias.get(i).darIdCat())
			{
				resp = pCategorias.get(i).darNombreCat();
			}

		}
		return resp;
	}

	//Requerimiento1
	public ILista<Video> mejoresVideosCatPa(int n, String pCat, String pPa)
	{
		ILista<Video> solucion = null; 
		//Primero se ordena por cantidadVistas
		
		if(!datosArreglo.isEmpty())
		{
			ArregloDinamico<Video>  arregloSolucion = new ArregloDinamico<>(n);
			int j = 0;
			for(int i = 0; i < n; i++)
			{
				try
				{
					Video actual = datosArreglo.getElement(i);
					if(actual.darPais().equals(pPa) && actual.darNombreCategoria().equals(pCat))
					{
						arregloSolucion.insertElement(actual, j);
						j++;
					}
					else
					{
						n++;
					}
				}
				catch(Exception e)
				{

				}
			}
			solucion = arregloSolucion;
		}
		else
		{
			ListaEncadenada<Video>  listaSolucion = new ListaEncadenada<>();
			Video actual = datosLista.firstElement();
			int i = 1;
			while(actual != null || i < n)
			{
				if(actual.darPais().equals(pPa) && actual.darNombreCategoria().equals(pCat))
				{
					listaSolucion.addLast(actual);
					actual = datosLista.getElement(i);
					i++;
				}
				else
				{
					n++;
				}
			}
		}
		return solucion;
	}

	//Requerimiento4
	public ILista<Video> Req2(int n, String pTag, String pPa)
	{
		ILista<Video> solucion = null; 
		//Primero se ordena por cantidadLikes
		
		
		if(!datosArreglo.isEmpty())
		{
			ArregloDinamico<Video>  arregloSolucion = new ArregloDinamico<>(n);
			int j = 0;
			for(int i = 0; i < n; i++)
			{
				try
				{
					Video actual = datosArreglo.getElement(i);
					if(actual.darPais().equals(pPa) && actual.darEtiqueta().equals(pTag))
					{
						arregloSolucion.insertElement(actual, j);
						j++;
					}
					else
					{
						n++;
					}
				}
				catch(Exception e)
				{

				}
			}
			solucion = arregloSolucion;
		}
		else
		{
			ListaEncadenada<Video>  listaSolucion = new ListaEncadenada<>();
			Video actual = datosLista.firstElement();
			int i = 1;
			while(actual != null || i < n)
			{
				if(actual.darPais().equals(pPa) && actual.darEtiqueta().equals(pTag))
				{
					listaSolucion.addLast(actual);
					actual = datosLista.getElement(i);
					i++;
				}
				else
				{
					n++;
				}
			}
		}
		return solucion;
	}
	//RETORNA LA 794
	//ERROR EN 998
}
