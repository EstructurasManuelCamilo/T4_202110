package model.logic;

import model.data_structures.*;
import model.logic.Video.ComparadorXLikes;
import model.logic.Video.ComparadorXVistas;
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
public class Modelo <K extends Comparable<K>, V extends Comparable<V>>
{
	/**
	 * Atributos del modelo del mundo
	 */
	long TInicio, TFin, tiempo;


	private ArregloDinamico<Video> datosArreglo; 

	private ListaEncadenada<Video> datosLista;
	
	private TablaSimbolos<String,Video> datosTablaSimbolos;

	private Ordenamientos<Video> ordenamientos;

	private ArregloDinamico<Categoria> categorias; 
	
	private int diasTendencia;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datosArreglo = new ArregloDinamico<Video>(501);
		datosLista = new ListaEncadenada<Video>();
		ordenamientos = new Ordenamientos<>();
		diasTendencia = 0;
		
		datosTablaSimbolos = new TablaSimbolos<>();
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
	
	public int darDiasTendencia()
	{
		return diasTendencia;
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
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/videos-all.csv")),"UTF-8");
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
		String anio  = "20"+partes[0];
		String dia  = partes[1];
		String mes = partes[2];

		String fecha = dia + "/" + mes + "/" + anio;
		Date date =new SimpleDateFormat("dd/MM/yyyy").parse(fecha);  

		return date;
	}
	public Date fecha2(String pFecha) throws ParseException
	{
		String[] partes = pFecha.split("T");
		String anioMesDia  = partes[0];
		String [] partes2 = anioMesDia.split("-");
		String anio = partes2[0];
		String mes = partes2[1];
		String dia = partes2[2];
		String fecha = dia + "/" + mes + "/" + anio;
		Date date =new SimpleDateFormat("dd/MM/yyyy").parse(fecha);  

		return date;
	}
	
	public Date cambiarFormato(LocalDateTime pFecha) throws ParseException
	{
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicio      = date.parse(pFecha.toString());
		return fechaInicio;
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
	public ArregloDinamico<Video> mejoresVideosCatPa(int n, String pCat, String pPa)
	{	 
		//Primero se ordena por cantidadVistas
		Video.ComparadorXVistas comp = new Video.ComparadorXVistas();
		ordenamientos.ordenarShell(datosArreglo, comp, true);

		ArregloDinamico<Video>  arregloSolucion = new ArregloDinamico<>(n);
		int j = 0;
		for(int i = 0; i < n && i < datosArreglo.size(); i++)
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
		return arregloSolucion;

	}
	// Requerimiento 2. Video con más días como tendencia dado el país 
	public Video videoTendenciaPais(String pPais)
	{
		Video.ComparadorXId comp2 = new Video.ComparadorXId();
		ArregloDinamico<Video> videosPais = new ArregloDinamico<>(20);
		Video videoTendencia = null;
		int masDias = 0;
		int contador = 1;
		
		int i = 0;
		Video actual = null;
		while(i < datosArreglo.size())
		{
			actual = datosArreglo.getElement(i);
			if (actual.darPais().equals(pPais)) 
			{
				videosPais.addLast(actual);
			}
			i++;
		}
		ordenamientos.ordenarShell(videosPais, comp2, true);
		Video act = videosPais.getElement(0);
		
		for(int j = 1; j < videosPais.size(); j++)
		{
			if(videosPais.getElement(j).getId().equals(act.getId()))
			{
				contador++;
				
			}
			else if(contador > masDias)
			{
				videoTendencia = act;
				masDias = contador;
				contador = 1;
			}
			else
			{
				contador = 1;
			}
			act = videosPais.getElement(j);
		}
		
		diasTendencia = masDias;
		return videoTendencia;
	}

	/**
	 * Requerimiento 3
	 * @param pCategoria
	 * @return el video con el mayor numero de dias en la categoria dada 
	 */
	public Video videoTendenciaCategoría(String pCat)
	{
		Video.ComparadorXId comp2 = new Video.ComparadorXId();
		ArregloDinamico<Video> videosCat = new ArregloDinamico<>(20);
		Video videoTendencia = null;
		int masDias = 0;
		int contador = 1;
		
		int i = 0;
		Video actual = null;
		while(i < datosArreglo.size())
		{
			actual = datosArreglo.getElement(i);
			if (actual.darNombreCategoria().equals(pCat)) 
			{
				videosCat.addLast(actual);
			}
			i++;
		}
		ordenamientos.ordenarShell(videosCat, comp2, true);
		Video act = videosCat.getElement(0);
		
		for(int j = 1; j < videosCat.size(); j++)
		{
			if(videosCat.getElement(j).getId().equals(act.getId()))
			{
				contador++;
				
			}
			else if(contador > masDias)
			{
				videoTendencia = act;
				masDias = contador;
				contador = 1;
			}
			else
			{
				contador = 1;
			}
			act = videosCat.getElement(j);
		}
		
		diasTendencia = masDias;
		return videoTendencia;
	}

	//Requerimiento4
	public ArregloDinamico<Video> masGustados(int n, String pTag, String pPa)
	{
		//Primero se ordena por cantidadLikes
		Video.ComparadorXLikes comp = new Video.ComparadorXLikes();
		ordenamientos.ordenarShell(datosArreglo, comp, true);

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
	

	@SuppressWarnings("unchecked")
	public void leerDatosTablaSimbolos()
	{
		// TODO Leer datos con tabla simbolo
		try 
		{
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/videos-all.csv")),"UTF-8");
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
				
				
				String llave = pais +"-" +darCategoria(categoria);
				if (!datosTablaSimbolos.contains(llave))
				{
					ArregloDinamico<NodoTS<K, V>> lista = new ArregloDinamico<>(1);
					NodoTS<String, Video> node = new NodoTS<String, Video>(llave, nuevo);
					lista.addLast((NodoTS<K, V>) node);
					datosTablaSimbolos.put(llave, nuevo);
				}
				else
				{
					for(int i = 0; i < datosTablaSimbolos.darListaNodos().size(); i++)
					{
						if (datosTablaSimbolos.darListaNodos().getElement(i).getKey().compareTo((K) llave) == 0) 
						{
							datosTablaSimbolos.darListaNodos().getElement(i).setValue(datosTablaSimbolos.darListaNodos().getElement(i).getValue());
						}
					}
					datosTablaSimbolos.darListaNodos().getElement(diasTendencia);
					//datosTablaSimbolos.put((K)llave, datosTablaSimbolos.get((K)llave));
				}
				
				
//				if (pais.equals(pPais) && category_id.equals(pCategoria)) 
//				{
//					NodoTS<K, V> nodo = new NodoTS(pais + "-" + darCategoria(category_id), titulo);
//					datosTablaSimbolos.put();
//				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Informa el número y los videos que pertenecen a un mismo país y nombre de categoría
	 * @param pPais
	 * @param pCategoria
	 * @return Lista con el número y los videos
	 */
	public ILista informarVideosPorPaisCategoria(String pPais, String pCategoria)
	{
		return null;
	}

	public String darCategoria(String pId)
	{
		String resp = null;
		for(int i = 0; i < categorias.size(); i ++)
		{
			if (categorias.getElement(i).darIdCat() == Integer.parseInt(pId)) 
			{
				resp = categorias.getElement(i).darNombreCat();
			}
		}
		return resp;
	}
	
}
