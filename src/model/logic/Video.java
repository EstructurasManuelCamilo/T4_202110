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

	public Video(String pId, Date date, String pTitle, String pChannel, int pCat, LocalDateTime fecha2, String pFpub)
	{
		id = pId;
		trendingDate = date;
		title = pTitle;
		channel = pChannel;
		categoryId = pCat;
		publishTime = fecha2;
		categoria = "";
		fechaPublicacion = pFpub;
	}

	public int compareTo(Video v) 
	{
		// TODO implementar método comparar
		return 0;
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
	
	
	public static class ComparadorXLikes implements Comparator<Video> 
	{

		/** Comparador alterno de acuerdo al número de likes
		* @return valor 0 si video1 y video2 tiene los mismos likes.
		 valor negativo si video1 tiene menos likes que video2.
		 valor positivo si video1 tiene más likes que video2. */
		 public int compare(Video video1, Video video2) 
		 {
			 //TODO implementar método
			 return 0;
		 }
		}

}
