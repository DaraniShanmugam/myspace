package lib;
import java.util.*;
import java.util.Date;
public class Note {
	private int id,userId;
	private String content,title,type;
	ListItem[] list;
	private Date time;
	public Note()
	{
		id=0;
		userId=0;
		content=null;
		title=null;
		type=null;
		time=null;
		list=null;
		
	}
    public void setId(int id)	
    {
    	this.id=id;
    }
    public int getId()
    {
    	return id;
    }
    public void setUserId(int userId)
    {
    	this.userId=userId;
    }
    public int getUserId()
    {
    	return userId;
    }
    public void setContent(String content)
    {
    	this.content=content;
    }
    public String getContent()
    {
    	return content;
    }
    public void setTitle(String title)
    {
    	this.title=title;
    }
    public String getTitle()
    {
    	return title;
    }
    public void setType(String type)
    {
    	this.type=type;
    }
    public String getType()
    {
    	return type;
    }
    public void setTime(Date time)
    {
    	this.time=time;
    }
    public Date getTime()
    {
    	
    	return time;
    }
    public void setList(ListItem[] list)
    {
    	this.list=list;
    }
    public ListItem[] getList()
    {
    	return list;
    }
}
