package wyf.hxl;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
public class DataBase
{
	Connection con=null;//����Connection����
	Statement stat;
	ResultSet rs;
	int count;
	public DataBase()
	{
		try
		{//����MySQL�������࣬���������ݿ�����
			Class.forName("org.gjt.mm.mysql.Driver");	
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");	
			stat=con.createStatement();//����Statement����			
		}
		catch(Exception e)
		{//�����쳣������ӡ����
			e.printStackTrace();
		}
	}
	public void selectDb(String sql)
	{//����select����
		try
		{//��SQL������ת��
			sql=new String(sql.getBytes(),"ISO-8859-1");
			rs=stat.executeQuery(sql);
		}
		catch(Exception ei)
		{//�����쳣������ӡ����
			ei.printStackTrace();
		}
	}
	public int updateDb(String sql)
	{//����update����
		try
		{//��SQL������ת��
			sql=new String(sql.getBytes(),"ISO-8859-1");
			count=stat.executeUpdate(sql);			
		}
	    catch(Exception ei)
		{//�����쳣������ӡ����
			ei.printStackTrace();
		}
		return count;//���ز���
	}
	public void dbClose()
	{//����close����		
		try
		{
			con.close();//ִ�����ݿ�رն���
		}
		catch(Exception e)
		{//�����쳣������ӡ����
			e.printStackTrace();
	    }	
	}
	public int updatePic(String path,int eid)
	{		
		try
		{//����Ԥ�������ʵ�ֽ�ͼƬ������ݿ�Ĺ���
			PreparedStatement ps=con.prepareStatement("update Person set Photo=? where EmployeeID="+eid);
			File f=new File(path);		
			int length=(int)f.length();//��ȡͼƬ�ĳ���
			byte[] b=new byte[length];//����byte������̶�ΪͼƬ�ļ��ĳ���
			FileInputStream fin=new FileInputStream(f);//�����ļ������ֽ�������Ϊ��ָ��Դ�ļ�
			fin.read(b);//��ȡ�ļ�����byte������
			fin.close();
			ps.setBytes(1,b);//����Ԥ��������е��ֶε�ֵ
			ps.execute();
			ps.close();
		}
	    catch(Exception ei)
		{//�����쳣������ӡ����
			ei.printStackTrace();
		}
		return count;
	}
	public Vector<String[]> getPerson()
	{//��Ա����Ϣ��������
		Vector<String[]> vtemp = new Vector<String[]>();
		String sql = "select EmployeeID,Name,Sex,Date,City,Nation,Polity,"+
				"Culture,Marriage,Graduate,Spec,Speci,Wtype,Duty,Depart,"+
				"IDcard,Address,Postcode,HomePhone,Mobile,Email from Person";//ִ�в�ѯ��SQL���
		try
		{
			rs=stat.executeQuery(sql);
			while(rs.next())
			{//���������Ϊ��
				String[] str = new String[21];
				for(int i=0;i<str.length;i++)
				{//�õ�����������������ת��
					str[i] = rs.getString(i+1);
					str[i] = new String(str[i].getBytes("ISO-8859-1"),"gb2312");
				}
				vtemp.add(str);
			}
		}
		catch(Exception e)
		{//�����쳣������ӡ����
			e.printStackTrace();
		}
		return vtemp;
	}
	public Vector<Image> getRecord ()
{
	Vector<Image> image=new Vector<Image>();//������Ƭ����
	try
	{		
		String sql="select Photo from Person";//��ѯĳԱ������Ƭ
		rs=stat.executeQuery(sql);
		while(rs.next())
		{//���������Ϊ��
			byte[] buff=rs.getBytes(1);
			if(buff!=null)
			{//���������Ϊ�վͽ��������Ƭ��ʾ
			   image.add((new ImageIcon(buff)).getImage());
			}
			else
			{//������ʾĬ��ͼƬ
				image.add(new ImageIcon("ico.gif").getImage()); 
			} 				
		} 		
	}
	catch(Exception e)
	{//�����쳣������ӡ����
		e.printStackTrace();
	}
	return image; 		
}
	public Vector<Image> getPic()
	{
		Vector<Image> image=new Vector<Image>();//������Ƭ����
		Image i=new ImageIcon("ico.gif").getImage();
		try
		{		
			String sql="select Photo from Person";//��ѯĳԱ������Ƭ
			rs=stat.executeQuery(sql);
			while(rs.next())
			{//���������Ϊ��
				byte[] buff=rs.getBytes(1);
				if(buff!=null)
				{//����������Ϊ�վͽ��������Ƭ��ʾ
				   image.add((new ImageIcon(buff)).getImage());
				}
				else
				{//������ʾĬ��ͼƬ
					image.add(i); 
				} 				
			} 		
		}
		catch(Exception e)
		{//�����쳣������ӡ����
			e.printStackTrace();
		}
		return image; 		
	}
	public Vector<String[]> getMessage()
	{
		Vector<String[]> v = new Vector<String[]>();
		String sql = "select * from Attend";//ִ�ж�Attend�����Ϣ�Ĳ�ѯ
		try
		{
			rs=stat.executeQuery(sql);
			while(rs.next())
			{//���������Ϊ��
				String[] str = new String[8];
				for(int i=0;i<str.length;i++)
				{//�õ�����������������ת��
					str[i] = rs.getString(i+1);
					str[i] = new String(str[i].getBytes("ISO-8859-1"),"gb2312");
				}
				v.add(str);//����õĽ�������ݣ��浽һ��������
			}
		}
		catch(Exception eo)
		{//�����쳣������ӡ����
			eo.printStackTrace();
		}
		return v;
	}
	public Vector<String[]> getWage()
	{
		Vector<String[]> vv= new Vector<String[]>();//����һ���ַ�������
		String sql = "select * from Wage";//ִ�ж�Wage�����Ϣ�Ĳ�ѯ
		try
		{//��Ҫ�Խ���������쳣�Ĳ���
			rs=stat.executeQuery(sql);
			while(rs.next())
			{//�������Ϊ��
				String[] str = new String[10];
				for(int i=0;i<str.length;i++)
				{//�õ�����������������ת��
					str[i] = rs.getString(i+1);
					str[i] = new String(str[i].getBytes("ISO-8859-1"),"gb2312");
				}
				vv.add(str);//����õĽ�������ݣ��浽һ��������
			}
		}
		catch(Exception eov)
		{//�����쳣������ӡ����
			eov.printStackTrace();
		}
		return vv;
	}
	public static void main(String[] args)
	{
		DataBase db = new DataBase();
		Vector<String[]> vtemp = db.getPerson();
		System.out.println(db.getPic().size());
		
		for(String[] str:vtemp)
		{
			System.out.println(str[0]);
		}
	}
}