package model.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

}
