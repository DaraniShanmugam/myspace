package model;
//import java.util.*;
//import java.util.Date;
import view.App;
import lib.AESEncryption;
import lib.Note;
import lib.ListItem;
import java.sql.*;

import lib.Database;
import java.util.Date;
public class NotesModel {
	
	public void insert(Note note)
	{
		//String arr[]=new String[100];
		//System.out.println("insert into notes(null,'"+user_id+"',null,'"+content+"',null,now()')");
		//int Id=id.setId();
		AESEncryption aes=new AESEncryption();
		int userId=note.getUserId();
		String content=note.getContent();
		ListItem[] list=note.getList();
		String title=note.getTitle();
		aes.setValue(content);
		
		aes.encrypt();
		content=aes.getValue();
		String initVector=aes.getInitVector();
		if (list==null)
		{
		try {
			
			App.database.executeUpdate("insert into notes values(null,'"+userId+"','"+title+"','"+content+"',null,now(),'"+initVector+"')");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else
		{
			try {
				App.database.executeUpdate("insert into notes values(null,'"+userId+"','"+title+"','"+content+"','list',now(),'"+initVector+"')");
				App.database.execute("select last_insert_id()");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ResultSet result=App.database.fetchResult();
			int noteId =0;
			try {
				while(result.next())
				{
					 try {
						noteId=(result.getInt(1));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(noteId != 0)
				insertlist(list,noteId);
		}
	}
		
	
	public void insertlist(ListItem[] list,int noteId)
	{
		
	     int note_id=noteId;
         for(ListItem item : list)
         {
        	 AESEncryption aes=new AESEncryption();
        	 String content=item.getContent();
             boolean checked=item.getChecked();
             aes.setValue(content);
     		
     		aes.encrypt();
     		content=aes.getValue();
     		String initVector=aes.getInitVector();
     	
		try {
			App.database.executeUpdate("insert into list values('"+note_id+"',null,'"+content+"','"+((checked)?1:0)+"','"+initVector+"')");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         }
	}
          public Note[] fetchEntries()
          {
        	  int count=0;
        	  Note[] note=null;
        	  try {
				App.database.execute("select * from notes");
				note=new Note[App.database.getRowCount()];
				ResultSet result=App.database.fetchResult();
				while(result.next())
				{
					AESEncryption aes=new AESEncryption(result.getString("iv"));
					aes.setValue(result.getString("content"));
					aes.decrypt();
					note[count]=new Note();
					note[count].setId(result.getInt("id"));
					note[count].setUserId(result.getInt("user_Id"));
					note[count].setTitle(result.getString("title"));
					note[count].setContent(aes.getValue());
					note[count].setType(result.getString("type"));
					if(note[count].getType()=="list")
					{
						fetchList();
					}
					note[count].setTime(result.getDate("time"));
					count++;
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	  return note;
          }
          public ListItem[] fetchList()
          {
        	  int count=0;
        	  ListItem[] item=null;
        	  try {
				App.database.execute("select * from list");
				item=new ListItem[App.database.getRowCount()];
				ResultSet result=App.database.fetchResult();
				int noteId=0;
				while(result.next())
				{
					AESEncryption aes=new AESEncryption(result.getString("iv"));
					aes.setValue(result.getString("content"));
					aes.decrypt();
					item[count]=new ListItem();
					noteId=result.getInt(1);
					item[count].setListId(result.getInt("id"));
					item[count].setContent(aes.getValue());
					item[count].setChecked(result.getBoolean("checked"));
	                count++;			
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	  return item;
        	  
          }
          public void checkList(int L_id)
          {
        	 try {
				App.database.executeUpdate("update list set checked=1 where id='"+L_id+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }
          public Note[] searchNote(String searchString)
          {
        	  int count=0;
        	  Note[] note=null;
        	  String[] splitted=searchString.split("\\s+");
        	  String stringSearch="";
        	  for(int i=0;i<splitted.length;i++)
        	  {
        		  if(i<splitted.length-1)
        		  {
        			  stringSearch += "title Like '%" + splitted[i] + "%' OR " +"content Like '%" + splitted[i] + "%' ";
        		  }
        		  if(i==splitted.length-1)
        		  {
        			  stringSearch += "title Like '%" + splitted[i] + "%' OR " +"content Like '%" + splitted[i] + "%'";
        		  }
        		  
        		  }
        	  
        	  try {
				App.database.execute("select * from notes where " + stringSearch);
				note=new Note[App.database.getRowCount()];
				ResultSet result=App.database.fetchResult();
				while(result.next())
				{
					note[count]=new Note();
					note[count].setId(result.getInt("id"));
					note[count].setUserId(result.getInt("user_Id"));
					note[count].setTitle(result.getString("title"));
					note[count].setContent(result.getString("content"));
					note[count].setType(result.getString("type"));
					if(note[count].getType()=="list")
					{
						searchList(searchString);
					}
					note[count].setTime(result.getDate("time"));
					count++;
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	  return note;
          }
          public ListItem[] searchList(String searchString)
          {
        	  int count=0;
        	 ListItem[] item=null;
        	  String[] splitted=searchString.split("\\s+");
        	  String stringSearch="";
        	  for(int i=0;i<splitted.length;i++)
        	  {
        		  if(i<splitted.length-1)
        		  {
        			stringSearch += "title Like '%" + splitted[i] + "%' OR " + "content Like '%" + splitted[i] + "%' ";
        		  }
        		  if(i==splitted.length-1)
        		  {
        			  stringSearch +=  "title Like '%" + splitted[i] + "%' OR " +"content Like '%" + splitted[i] + "%' ";
        		  }
        		  
        		  }
        	  try {
  				App.database.execute("select * from list where "+ stringSearch);
  				item=new ListItem[App.database.getRowCount()];
  				ResultSet result=App.database.fetchResult();
  				int noteId=0;
  				while(result.next())
  				{
  					item[count]=new ListItem();
  					noteId=result.getInt(1);
  					item[count].setListId(result.getInt("id"));
  					item[count].setContent(result.getString("content"));
  					item[count].setChecked(result.getBoolean("checked"));
  	                count++;			
  				}
  			} catch (SQLException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          	  return item;
          	  
            }
          
    
          public void DeleteNote(Note note,int d_id)
          {
            ListItem[] list=note.getList();
      		String type=note.getType();
      		//String title=note.getTitle();
      		if (type==null)
      		{
      			try {
				App.database.executeUpdate("delete * from notes where id="+d_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
      		else
      		{
      		  DeleteList(list,d_id);
      		}
          }
          public void DeleteList(ListItem[] list,int d_id)
          {
        	  for(ListItem item : list)
        	  {
        		  try {
					App.database.executeUpdate("delete * from list where id="+d_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	  }
          }
	public static void main(String[] args)
	{
		NotesModel n = new NotesModel();
		/*Note n1=new Note();
		ListItem[] n2=new ListItem[5];
		n2[0] = new ListItem();
		n2[0].setContent("a");
		n2[1] = new ListItem();
		n2[1].setContent("b");
		n2[2] = new ListItem();
		n2[2].setContent("c");
		n2[3] = new ListItem();
		n2[3].setContent("d");
		n2[4] = new ListItem();
		n2[4].setContent("e");
		
		
		
		n1.setContent("java");
		n1.setUserId(1);
		n1.setList(n2);*/
		try {
			App.database=new Database();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//n.insert(n1);
		/*Note[] x=n.fetchEntries();
		for(int i=0;i<x.length;i++)
		{
		System.out.println(x[i].getId());
		System.out.println(x[i].getUserId());
		System.out.println(x[i].getTitle());
		System.out.println(x[i].getContent());
		System.out.println(x[i].getType());
		System.out.println(x[i].getTime());
		
		
		}
		ListItem[] y=n.fetchList();
		for(int j=0;j<y.length;j++)
		{
			//System.out.println(y[j].noteId());
			System.out.println(y[j].getListId());
			//System.out.println(y[j].getTitle());
			System.out.println(y[j].getContent());
			System.out.println(y[j].getChecked());
				
		}*/
		n.checkList(1);
		//n.inserttnotes("database", "hello");
		//n.DeleteNote(4);
		
	}

}