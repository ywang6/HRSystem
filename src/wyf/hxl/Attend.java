package wyf.hxl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.io.*;
public class Attend extends JPanel implements ActionListener
{
	private JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private JPanel jpt=new JPanel();
	String[] str=new String[8];
      DataBase db;
	String sql;
      Vector<String[]> v;
      int count=0;
	private JLabel[] jlArray=
	{
		new JLabel("     ְ �� ID"),
		new JLabel("   ��      ��"),
		new JLabel("   ��      ��"),
		new JLabel("   ��      ��"),
		new JLabel("   ��      ��"),
		new JLabel("     �� �� ��"),
	      new JLabel("   ��      ��"),
	      new JLabel("   ��      ��")
	};
	private JLabel jl=new JLabel("(��ѯ������ְ��ID������)");
	private JTextField[] jtxtArray=new JTextField[]
	{
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField()
	};
	//����JButton��ť���ı�
	private JButton[] jbArray=
	{ 
	    new JButton("��ǰ"),
	    new JButton("��һ��"),
	    new JButton("��һ��"),
	    new JButton("���"),
	    new JButton("��Ӽ�¼"),
	    new JButton("�޸ļ�¼"),
	    new JButton("��ѯ"),
	    new JButton("���")
	  
	};
		//��������
	Vector<String> head = new Vector<String>();
	{
		head.add("ְ��ID");
		head.add("����");
		head.add("����");
		head.add("�ٵ�");
		head.add("����");
		head.add("���ݼ�");
		head.add("����");
		head.add("�¼�");
	}
	//���²��Ӵ��������ñ��
	Vector<Vector> data=new Vector<Vector>();
    //�������ģ��
    DefaultTableModel dtm=new DefaultTableModel(data,head);
    //����Jtable����
	JTable jt=new JTable(dtm);
	//��JTable��װ����������
	JScrollPane jspn=new JScrollPane(jt);
	public Attend()
	{
		this.setLayout(new GridLayout(1,1));
		//���������ϲ���Ϊ�ղ��ֹ�����
		jpt.setLayout(null);
		//����jspt�зָ����ĳ�ʼλ��
		jsp.setDividerLocation(210);
		//���÷ָ����Ŀ��
		jsp.setDividerSize(4);
		jsp.setTopComponent(jpt);
		jsp.setBottomComponent(jspn);
		for(int i=0;i<8;i++)
		{
			jpt.add(jtxtArray[i]);
			jpt.add(jlArray[i]);
			if(i<4)
			{
			    jlArray[i].setBounds(15+i*160,20,70,20);
			    jtxtArray[i].setBounds(85+i*160,20,90,20);
			    jtxtArray[i].addActionListener(this);
			}
			else
			{
				jlArray[i].setBounds(15+(i-4)*160,60,70,20);
				jtxtArray[i].setBounds(85+(i-4)*160,60,90,20);
				jtxtArray[i].addActionListener(this);
			}
		}
		this.add(jsp);
		jpt.add(jl);
		jl.setBounds(325,170,150,20);
		//�����²��Ӵ���
      	jsp.setBottomComponent(jspn);
		//��JButton��ӽ�jpt,���ü�����
		for(int i=0;i<8;i++)
		{
			jpt.add(jbArray[i]);
			if(i<4)
			{
				jbArray[i].setBounds(55+150*i,100,100,25);
			}
			else
			{
				jbArray[i].setBounds(55+150*(i-4),140,100,25);
			}
			jbArray[i].addActionListener(this);
		}
		db = new DataBase();
		v=db.getMessage();
		db.dbClose();		
		//���ô���Ĵ�Сλ�ü��ɼ���
		this.setBounds(5,5,600,500);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jtxtArray[0])
	    	{
	    		jtxtArray[1].requestFocus();
	    	}
	    	if(e.getSource()==jtxtArray[1])
	    	{
	    		jtxtArray[2].requestFocus();
	    	}
	    	if(e.getSource()==jtxtArray[2])
	    	{
	    		jtxtArray[3].requestFocus();
	    	}
	    	if(e.getSource()==jtxtArray[3])
	    	{
	    		jtxtArray[4].requestFocus();
	    	}
	    	if(e.getSource()==jtxtArray[4])
	    	{
	    		jtxtArray[5].requestFocus();
	    	}   
	    	if(e.getSource()==jtxtArray[5])
	    	{
	    		jtxtArray[6].requestFocus();
	    	}
	    	if(e.getSource()==jtxtArray[6])
	    	{
	    		jtxtArray[7].requestFocus();
	    	} 
	    	if(e.getSource()==jbArray[4])
	    	{
	    		this.insert();
	    	}
	    	if(e.getSource()==jbArray[5])
	    	{
	    		this.update();
	    	}
	    	if(e.getSource()==jbArray[6])
	    	{
	    		this.select();
	    	}
	    	if(e.getSource()==jbArray[7])
	    	{
	    		for(int i=0;i<8;i++)
	    		{
	    			jtxtArray[i].setText("");
	    		}
	    	}
	    	if(e.getSource()==jbArray[0])
		{
			this.first();
		}
		
		if(e.getSource()==jbArray[1])
		{
			if(count==0)
			{
				count=v.size()-1;
			}
			else
			{
				count--;
			}
			String[] str = v.get(count);
			for(int i=0;i<8;i++)
			{
				jtxtArray[i].setText(str[i]);
			}	
		}
		if(e.getSource()==jbArray[2])
		{
			if(count==(v.size()-1))
			{
				count=0;
			}
			else
			{
				count++;
			}
			String[] str= v.get(count);
			for(int i=0;i<8;i++)
			{
				jtxtArray[i].setText(str[i]);
			}	
		}
		if(e.getSource()==jbArray[3])
		{
			this.last();
		}	
	}
	public void select()
	{ 
	      String s1=jtxtArray[0].getText().trim();
	      String s2=jtxtArray[2].getText().trim();
		if(s1.equals("")&&s2.equals(""))
		{
        	JOptionPane.showMessageDialog(this,"������ְ��ID�����£�����",
			                              "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(!s1.equals("")&&s2.equals(""))
		{
			sql="select * from Attend where EmployeeID="+Integer.parseInt(s1);
			db=new DataBase();
			db.selectDb(sql);
			try
			{
					 Vector <Vector> v=new Vector<Vector>();
			     while(db.rs.next())
			     {
			       	 Vector <String> vtemp = new Vector<String>();
				     for(int i=0;i<8;i++)
				     {
				            
				         String str1=new String(db.rs.getString(i+1).getBytes("ISO-8859-1"),"gb2312");
				         vtemp.add(str1);
				     }
				     v.add(vtemp);							
				    
			     }
			     if(v.size()==0)
			     {
			     	JOptionPane.showMessageDialog(this,"������ѯ��ְ����Ϣ�����ڣ���","��ʾ",
				     JOptionPane.INFORMATION_MESSAGE);
				    return;
			     }
			    //����table	
				dtm.setDataVector(v,head);
				jt.updateUI();
			    jt.repaint();
			    JOptionPane.showMessageDialog(this,"���Ѿ��ɹ���ѯ��ְ����Ϣ����","��ʾ",
						     JOptionPane.INFORMATION_MESSAGE);
				jtxtArray[0].setText("");
				return;        
			}
			catch(Exception ep)
			 {
			     ep.printStackTrace();
			 }
		}
		if(s1.equals("")&&!s2.equals(""))
		{
			sql="select * from Attend where Time='"+s2+"'";
		      db=new DataBase();
			db.selectDb(sql);
			try
			{
				 Vector <Vector> v=new Vector<Vector>();
			     while(db.rs.next())
			     {
			       	 Vector <String> vtemp = new Vector<String>();
				     for(int i=0;i<8;i++)
				     {
				            
				         String str1=new String(db.rs.getString(i+1).getBytes("ISO-8859-1"),"gb2312");
				         vtemp.add(str1);
				     }
				     v.add(vtemp);							
				    
			     }
			     if(v.size()==0)
			     {
			     	JOptionPane.showMessageDialog(this,"������ѯ��ְ����Ϣ�����ڣ���","��ʾ",
				     JOptionPane.INFORMATION_MESSAGE);
				    return;
			     }
			    //����table	
				dtm.setDataVector(v,head);
				jt.updateUI();
			    jt.repaint();
			    JOptionPane.showMessageDialog(this,"���Ѿ��ɹ���ѯ��ְ����Ϣ����","��ʾ",
						     JOptionPane.INFORMATION_MESSAGE);
				jtxtArray[2].setText("");
				return;        
			 }
		     catch(Exception e)
			 {
			     e.printStackTrace();
			 }
		}
		if(!s1.equals("")&&!s2.equals(""))
		{
			sql="select * from Attend where Time='"+s2+" and Attend.EmployeeID="+Integer.parseInt(s1);
		      db=new DataBase();
			db.selectDb(sql);
			try
			{
				 Vector <Vector> v=new Vector<Vector>();
			     while(db.rs.next())
			     {
			       	 Vector <String> vtemp = new Vector<String>();
				     for(int i=0;i<8;i++)
				     {
				            
				         String str1=new String(db.rs.getString(i+1).getBytes("ISO-8859-1"),"gb2312");
				         vtemp.add(str1);
				     }
				     v.add(vtemp);							
				    
			     }
			     if(v.size()==0)
			     {
			     	JOptionPane.showMessageDialog(this,"������ѯ��ְ����Ϣ�����ڣ���","��ʾ",
				     JOptionPane.INFORMATION_MESSAGE);
				    return;
			     }
			    //����table	
				dtm.setDataVector(v,head);
				jt.updateUI();
			    jt.repaint();
			    JOptionPane.showMessageDialog(this,"���Ѿ��ɹ���ѯ��ְ����Ϣ����","��ʾ",
						     JOptionPane.INFORMATION_MESSAGE);
			      jtxtArray[0].setText("");
				jtxtArray[2].setText("");
				return;        
			 }
		     catch(Exception e)
			 {
			     e.printStackTrace();
			 }
		}
	}
	public void first()
	{
		String[] str = v.get(0);
		for(int i=0;i<8;i++)
		{
			jtxtArray[i].setText(str[i]);
		}
	}
	public void last()
	{  
		String[] str = v.get(v.size()-1);
		for(int i=0;i<8;i++)
		{
			jtxtArray[i].setText(str[i]);
		}
	}
	public void insert()
	{
		for(int i=0;i<8;i++)
		{
			str[i]=jtxtArray[i].getText().trim();
		}
		if(str[0].equals("")&&str[1].equals("")&&str[2].equals("")&&str[3].equals("")&&str[4].equals("")
		&&str[5].equals("")&&str[6].equals("")&&str[7].equals(""))
		{
			JOptionPane.showMessageDialog(this,	"��Ϣ����Ϊ�գ�����",
						        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
            return;	
		}
		int in=Integer.parseInt(jtxtArray[0].getText().trim());
		if(!str[0].equals("")&&!str[1].equals("")&&!str[2].equals("")&&!str[3].equals("")&&!str[4].equals("")
		&&!str[5].equals("")&&!str[6].equals("")&&!str[7].equals(""))
		{
			sql="select EmployeeID from Person where EmployeeID="+in;
			db=new DataBase();
			db.selectDb(sql);
			try
			{
				if(db.rs.next())
				{
					int[] s=new int[6];
					for(int i=0;i<5;i++)
					{
						s[i]=Integer.parseInt(str[i+3]);
					}
					sql="select EmployeeID from Attend where Attend.EmployeeID="+in+" and Attend.Time='"+str[2]+"'";
					db=new DataBase();
					db.selectDb(sql);
					if(!db.rs.next())
					{
						sql="insert into Attend(EmployeeID,Name,Time,Late,Leaveearly,Jobwound,S_leave,Pa_leave) values("
						+in+",'"+str[1]+"','"+str[2]+"',"
						 +s[0]+","+s[1]+","+s[2]+","+s[3]+","+s[4]+")";
						db=new DataBase();
						db.updateDb(sql);
						JOptionPane.showMessageDialog(this,"��ӳɹ�����",
						                                   "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
						return;
					}	    	
					else
					{
					      JOptionPane.showMessageDialog(this,"��ְ������Ϣ�Ѿ����,�����ظ�����",
						                                   "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
						return;	
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"����˾û������ְ������",
					                                   "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
		public void update()
	{
		int in=Integer.parseInt(jtxtArray[0].getText().trim());
		for(int i=0;i<8;i++)
		{
			str[i]=jtxtArray[i].getText().trim();
		}
		if(!str[0].equals("")&&!str[1].equals("")&&!str[2].equals("")&&!str[3].equals("")
		   &&!str[4].equals("")&&!str[5].equals("")&&!str[6].equals("")&&!str[7].equals(""))
		{
			int[] s=new int[6];
			for(int i=0;i<5;i++)
			{
				s[i]=Integer.parseInt(str[i+3]);
			}
			sql="update Attend set Name='"+str[1]+"',time='"
			     +str[2]+"',Late="+s[0]+",Leaveearly="+s[1]+",Jobwound="
			     +s[2] +",S_leave="+s[3]+",Pa_leave="+s[4]+" where EmployeeID='"+str[0]+"'";	     
			db=new DataBase();
			db.updateDb(sql);
			JOptionPane.showMessageDialog(this,"�޸���Ϣ�ɹ�����",
			                                   "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		else
		{
			JOptionPane.showMessageDialog(this,"��������Ҫ�޸ĵ���Ϣ����",
			                                   "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
										
	}
	public static void main(String[]args)
	{
		new Attend();
	}
}