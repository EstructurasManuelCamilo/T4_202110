package model.logic;

import java.util.Date;

public class Video implements Comparable<Video>
{
	
	private String id;
	
	private String trendingDate;
	
	private String title;
	
	private String channel;
	
	private int categoryId;
	
	private String categoria;
	
	private String publishTime;
	
	public Video(String pId, String fecha1, String pTitle, String pChannel, int pCat, String fecha2)
	{
		id = pId;
		trendingDate = fecha1;
		title = pTitle;
		channel = pChannel;
		categoryId = pCat;
		publishTime = fecha2;
	}
	
	public int compareTo(Video v) 
	{
		return 0;
	}

	public String getId() 
	{
		return id;
	}

	public String getTrendingDate() 
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

	public String getPublishTime() 
	{
		return publishTime;
	}
	
	
}
