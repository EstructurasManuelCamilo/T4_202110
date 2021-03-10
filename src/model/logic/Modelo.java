package model.logic;

import model.data_structures.*;
import model.logic.Video.ComparadorXLikes;
import model.utils.Ordenamientos;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

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

	private ComparadorXLikes comparar;

	private ArregloDinamico<Categoria> categorias; 

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

		try 
		{
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/videos-small.csv")),"UTF-8");
			final CSVParser separador = new CSVParser(pDatos, CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(','));
			for(final CSVRecord excel : separador)
			{

				String id = excel.get("video_id");
				String fechaTrending = excel.get("trending_date");		
				String titulo = excel.get("title");
				String canal = excel.get("channel_title");
				String categoria = excel.get("category_id");
				String publicacion = excel.get("publish_time");
				String tags = excel.get("tags");
				String vistas = excel.get("views");
				String likes =excel.get("likes");
				String dislikes = excel.get("dislikes");
				String pais = excel.get("country");
				Video nuevo = new Video(id, fecha1(fechaTrending), titulo, canal, Integer.valueOf(categoria), fecha2(publicacion), publicacion, tags, Integer.valueOf(vistas), likes, dislikes, darNomCat(Integer.valueOf(categoria),categorias), pais);
				datosArreglo.addLast(nuevo);
				datosLista.addLast(nuevo);
			}
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

	public ArregloDinamico<Categoria> leerCategorias()
	{ 
		ArregloDinamico<Categoria> resp = new ArregloDinamico<>(50);
		try 
		{
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/category-id.csv")),"UTF-8");
			final CSVParser separador = new CSVParser(pDatos, CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(','));
			for(final CSVRecord excel : separador)
			{
				String id = excel.get("id");
				String name = excel.get("name");
				Categoria cat = new Categoria(Integer.valueOf(id), name);
				resp.addLast(cat);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		categorias = resp;
		return resp;
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

	public String darNomCat(int pId, ArregloDinamico<Categoria> categorias2)
	{
		String resp = "";
		for(int i = 0; i < categorias2.size() & resp.equals(""); i++)
		{
			if(pId == categorias2.getElement(i).darIdCat())
			{
				resp = categorias2.getElement(i).darNombreCat();
			}

		}
		return resp;
	}

	//Requerimiento1
	public void mejoresVideosCatPa(int n, String pCat, String pPa)
	{	 
		//Primero se ordena por cantidadVistas

		ArregloDinamico<Video>  arregloSolucion = new ArregloDinamico<>(n);
		int j = 0;
		for(int i = 0; i < n && i < datosArreglo.size(); i++)
		{
			Video actual = datosArreglo.getElement(i);
			if(actual.darPais().equals(pPa) && actual.darNombreCategoria().equals(pCat))
			{
				arregloSolucion.insertElement(actual, j);
				System.out.println("Video " + i + " es " + actual.getTitle());
				j++;
			}
			else
			{
				n++;
			}
		}


	}
	// Requerimiento 2. Video con más días como tendencia dado el país 
	public ILista <Video> videoTendenciaPais(String pPais)
	{
		ILista<Video> resp = null;
		if (datosArreglo.isEmpty()) 
		{
			return resp;
		}
		else
		{
			ArregloDinamico<Video> videoPorPais = new ArregloDinamico<>(0);
			ArregloDinamico<Video> respArrg = new ArregloDinamico<>(0);
			Video.ComparadorXPais compararPais = new Video.ComparadorXPais();
			Video videoTendencia = datosArreglo.getElement(0);
			ordenamientos.ordenarInsercion(datosArreglo, compararPais, true);
			int i = 0;
			while(i < datosArreglo.size())
			{
				if (datosArreglo.getElement(i).darPais().equals(pPais)) 
				{
					respArrg.addLast(datosArreglo.getElement(i)); //tengo todos los video de un país
				}
				i ++;
			}
		}
	}

	//Requerimiento4
	public ILista<Video> masGustados(int n, String pTag, String pPa)
	{
		//Primero se ordena por cantidadLikes
		
			ArregloDinamico<Video>  solucion = new ArregloDinamico<>(n);
			int j = 0;
			for(int i = 0; i < n; i++)
			{
				try
				{
					Video actual = datosArreglo.getElement(i);
					if(actual.darPais().equals(pPa) && actual.buscarEtiqueta(pTag))
					{
						solucion.insertElement(actual, j);
						System.out.println("Video " + i + " es " + actual.getTitle());
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
		
		return solucion;
	}
}
