package model.logic;

import java.util.Date;

public class Video implements Comparable<Video>
{
	
	private String id;
	
	private Date trendingDate;
	
	private String title;
	
	private String channel;
	
	private int categoryId;
	
	private Date publishTime;
	
	public Video(String pId, Date pDate1, String pTitle, String pChannel, int pCat, Date pDate2)
	{
		id = pId;
		trendingDate = pDate1;
		title = pTitle;
		channel = pChannel;
		categoryId = pCat;
		publishTime = pDate2;
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

	public Date getPublishTime() 
	{
		return publishTime;
	}

	
}
