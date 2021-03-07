package model.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;

public class Video implements Comparable<Video>
{

	private String id;

	private Date trendingDate;

	private String title;

	private String channel;

	private int categoryId;

	private String categoria;

	private LocalDateTime publishTime;
	
	private String fechaPublicacion;
	
	private String likes;
	
	private String disLikes;
	
	private String pais; // Agrego el pais
	
	private int views; //Agrego las vistas+

	public Video(String pId, Date date, String pTitle, String pChannel, int pCat, LocalDateTime fecha2, String pFpub, int pViews, String pLikes, String pDislikes, String pPais)
	{
		id = pId;
		trendingDate = date;
		title = pTitle;
		channel = pChannel;
		categoryId = pCat;
		publishTime = fecha2;
		categoria = ""; //tags??
		fechaPublicacion = pFpub;
		likes = pLikes;
		disLikes = pDislikes;
		views = pViews;
		pais = pPais;
		
	}
	
	/** Comparación natural de acuerdo a algún atributo con identificación única
	* @return valor 0 si this y otro son iguales. Numero negativo si this es menor a otro. 
	 * Numero positivo this es mayor a otro */
	public int compareTo(Video v) 
	{
		if (v.getId().equals(this.getId()) )
			return 0;
		else if (v.getId().compareTo(this.getId()) > 0)
			return -1;
		else
			return 1;
	}

	public String getId() 
	{
		return id;
	}

	public Date getTrendingDate() 
	{
		return trendingDate;
	}

	public String getTitle() 
	{
		return title;
	}

	public String getChannel() 
	{
		return channel;
	}

	public int getCategoryId() 
	{
		return categoryId;
	}

	public LocalDateTime getPublishTime() 
	{
		return publishTime;
	}
	
	public String darPublishTime()
	{
		return fechaPublicacion;
	}

	public String darNombreCategoria()
	{
		return "";
	}
	
	public String darLikes()
	{
		return likes;
	}
	
	public String darDisLikes()
	{
		return disLikes;
	}
	
	public static class ComparadorXLikes implements Comparator<Video> 
	{

		/** Comparador alterno de acuerdo al número de likes
		* @return valor 0 si video1 y video2 tiene los mismos likes.
		 valor negativo si video1 tiene menos likes que video2.
		 valor positivo si video1 tiene más likes que video2. */
		 public int compare(Video video1, Video video2) 
		 {
			 int lksV1 = Integer.parseInt(video1.darLikes());
			 int lksV2 = Integer.parseInt(video2.darLikes());
			 return lksV2 - lksV1;
		 }
	}

}
