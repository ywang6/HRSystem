package wyf.hxl;
import javax.swing.*;import java.awt.*;
import java.awt.event.*;import javax.swing.table.*;
import java.sql.*;import java.util.*;
import java.util.Date;import java.io.*;
public class Person extends JPanel implements ActionListener{	
	JFileChooser jfc=new JFileChooser();//�����ļ�ѡ����	
	Vector listData=new Vector();//������������б����ݵ�����
    String[] str=new String[21];
    DataBase db;String sql;
    Vector<String[]> vtemp;//�����ַ�������
    Vector<Image> image;//����image����
    int count=0;
	private JLabel[] jlArray={//������ǩ��Ϊ��ָ���ı�
		new JLabel("ְ��ID"),new JLabel("��    ��"),new JLabel("��    ��"),
		new JLabel("��������"),new JLabel("��    ��"),new JLabel("��    ��"),
		new JLabel("������ò"),new JLabel("�Ļ��̶�"),new JLabel("����״��"),
		new JLabel("��ҵԺУ"),new JLabel("��ѧרҵ"),new JLabel("�����س�"),
		new JLabel("��    ��"),new JLabel("ְ    ��"),new JLabel("��������"),
		new JLabel("���֤��"),new JLabel("��ͥסַ"),new JLabel("��������"),
		new JLabel("��ϵ�绰"),new JLabel("Email"),new JLabel("�������ϵ���"),
		new JLabel("������Ƭ"),new JLabel("(סլ�绰)"),new JLabel("(��    ��)"),
		new JLabel("�����ť"),new JLabel("���ܰ�ť"),new JLabel("��Ƭ·��")
	};
	private JButton[] jbArray=new JButton[]{//������ť��Ϊ��ָ���ı�
		new JButton("��ǰ"),new JButton("��һ��"),new JButton("��һ��"),
		new JButton("���"),new JButton("���"),new JButton("ɾ��"),
		new JButton("�޸�"),new JButton("��ѯ"),new JButton("�����Ƭ")
	};
	private JTextField[] jtxt={//�����ı���
		new JTextField(),new JTextField(),new JTextField(),new JTextField(),
		new JTextField(),new JTextField(),new JTextField(),new JTextField(),
		new JTextField(),new JTextField(),new JTextField(),new JTextField(),
		new JTextField(),new JTextField(),new JTextField(),new JTextField(),
		new JTextField(),new JTextField(),new JTextField(),new JTextField(),
		new JTextField(),new JTextField()
	};
	private JLabel jl=new JLabel();
	public Person(){
		this.setLayout(null);//���ñ�����Ϊ�ղ���
		for(int i=0;i<23;i++){//��ʼ����ǩ���ı���
			this.add(jlArray[i]);
			if(i<3){//���õ�һ�б�ǩ���ı���Ĵ�С��λ��
				jlArray[i].setBounds(50+i*200,60,60,25);
				jtxt[i].setBounds(110+i*200,60,130,25);
			}
			if(i>=3&&i<6){//���õڶ��б�ǩ���ı���Ĵ�С��λ��
				jlArray[i].setBounds(50+(i-3)*200,100,60,25);
				jtxt[i].setBounds(110+(i-3)*200,100,130,25);
			}
			if(i>=6&&i<9){//���õ����б�ǩ���ı���Ĵ�Сλ��
				jlArray[i].setBounds(50+(i-6)*200,140,60,25);
				jtxt[i].setBounds(110+(i-6)*200,140,130,25);
			}
			if(i>=9&&i<11){//����4~6�е�һ�б�ǩ���ı���Ĵ�Сλ��
				jlArray[i].setBounds(50,180+(i-9)*40,60,25);
				jtxt[i].setBounds(110,180+(i-9)*40,530,25);
			}
			jlArray[11].setBounds(50,260,100,25);
			jtxt[11].setBounds(110,260,530,65);
			if(i>=12&&i<16){//���õ����б�ǩ���ı���Ĵ�Сλ��
				jlArray[i].setBounds(50+(i-12)*200,340,60,25);
				jtxt[i].setBounds(110+(i-12)*200,340,130,25);
			}	
		}
		for(int i=0;i<21;i++){jtxt[i].addActionListener(this);}
		this.add(jl);//�����Ƭ���ڵı�ǩ
		jl.setBounds(645,60,194,250);//������Ƭ���ڵı�ǩ�Ĵ�Сλ��
		jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));//��JLbel�ı߿������ֳ���
		//�����Ƕ�һЩ�ֲ��Ƚ���ɢ�İ�ť���ı��������еĳ�ʼ��
		jlArray[16].setBounds(50,380,60,25);
		jtxt[16].setBounds(110,380,530,25);
		jlArray[17].setBounds(650,380,60,25);
		jtxt[17].setBounds(710,380,130,25);
		jlArray[18].setBounds(50,435,60,25);
		jtxt[18].setBounds(110,420,130,25);
		jtxt[19].setBounds(110,448,130,25);
		jlArray[21].setBounds(725,310,60,25);
		jlArray[22].setBounds(250,420,80,25);
		jlArray[23].setBounds(250,448,80,25);
		jlArray[19].setBounds(350,420,60,25);
		jtxt[20].setBounds(410,420,433,25);
		jlArray[20].setBounds(5,5,100,25);
		this.add(jlArray[23]);
		this.add(jlArray[24]);
		this.add(jlArray[25]);
		this.add(jlArray[26]);
		this.add(jbArray[8]);
		jbArray[8].addActionListener(this);
		jbArray[8].setBounds(750,448,93,25);
		jlArray[26].setBounds(350,448,60,25);
		jtxt[21].setBounds(410,448,330,25);
		jlArray[24].setBounds(10,505,100,20);
		jlArray[25].setBounds(10,555,100,20);
		for(int i=0;i<22;i++){this.add(jtxt[i]);}
		for(int i=0;i<8;i++){//��ʼ����ť
			this.add(jbArray[i]);
			jbArray[i].addActionListener(this);//Ϊ��ť�����¼�������
			if(i<4){//���õ�һ�а�ť�Ĵ�Сλ��
				jbArray[i].setBounds(100+i*200,530,100,25);
			}
			else{//���õڶ��а�ť�Ĵ�Сλ��
				jbArray[i].setBounds(100+(i-4)*200,580,100,25);
			}
		}
		db=new DataBase();
		vtemp=db.getPerson();//����getPerson�����Ի��ְ����Ϣ	
	    image=db.getPic();//�����Ƭ��Ϣ
		db.dbClose();
		this.setBounds(5,5,650,500);//���ô���Ĵ�Сλ��
		this.setVisible(true);		//���ô���Ŀɼ���
	}
	//��ȡѡ��ͼƬ�ļ��ķ���
	public File getPic()
	{//��������Ի���
		int i=jfc.showSaveDialog(this);
		if(i==JFileChooser.APPROVE_OPTION)
		{//���±��水Ť
			return jfc.getSelectedFile();	
		}
		return null;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jbArray[8])
		{ //ʵ�������Ƭ�Ĺ���  
		   jfc.showOpenDialog(this);
			if(jfc.getSelectedFile()!=null)
			{//����Ƭ����һ��ǩ��
				jtxt[21].setText(""+jfc.getSelectedFile());
			}
		}
		//����20�д���ʵ���˼���������	
		if(e.getSource()==jtxt[0]){jtxt[1].requestFocus();}
		if(e.getSource()==jtxt[1]){jtxt[2].requestFocus();}
		if(e.getSource()==jtxt[2]){jtxt[3].requestFocus();}
		if(e.getSource()==jtxt[3]){jtxt[4].requestFocus();}
		if(e.getSource()==jtxt[4]){jtxt[5].requestFocus();}   
		if(e.getSource()==jtxt[5]){jtxt[6].requestFocus();}
		if(e.getSource()==jtxt[6]){jtxt[7].requestFocus();} 
		if(e.getSource()==jtxt[7]){jtxt[8].requestFocus();}
		if(e.getSource()==jtxt[8]){jtxt[9].requestFocus();}
		if(e.getSource()==jtxt[9]){jtxt[10].requestFocus();}
    	if(e.getSource()==jtxt[10]){jtxt[11].requestFocus();}
    	if(e.getSource()==jtxt[11]){jtxt[12].requestFocus();}
    	if(e.getSource()==jtxt[12]){jtxt[13].requestFocus();}
    	if(e.getSource()==jtxt[13]){jtxt[14].requestFocus();}
    	if(e.getSource()==jtxt[14]){jtxt[15].requestFocus();}   
    	if(e.getSource()==jtxt[15]){jtxt[16].requestFocus();}
    	if(e.getSource()==jtxt[16]){jtxt[17].requestFocus();} 
    	if(e.getSource()==jtxt[17]){jtxt[18].requestFocus();}
    	if(e.getSource()==jtxt[18]){jtxt[19].requestFocus();}  
    	if(e.getSource()==jtxt[19]){jtxt[20].requestFocus();}   
		if(e.getSource()==jbArray[4])
		{//ʵ�����ְ����Ϣ�Ĺ���
			this.insertPerson();
		}
		if(e.getSource()==jbArray[5])
		{//ʵ��ɾ��ְ����Ϣ�Ĺ���
			this.deletePerson();
		}
		if(e.getSource()==jbArray[6])
		{//ʵ���޸�ְ����Ϣ�Ĺ���
			this.updatePerson();
		}
		if(e.getSource()==jbArray[7])
		{//ʵ��"��ѯ"��ť����ʾ
			JOptionPane.showMessageDialog(this,"��ʹ����������ӽڵ� '��ѯ' ����",
			                           "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(e.getSource()==jbArray[0])
		{//��ʾ���ݿ��е�һ��ְ����Ϣ��¼
			this.first();
		}
		if(e.getSource()==jbArray[3])
		{//��ʾ���ݿ������һ��ְ����Ϣ��¼
			this.last();
		}
		if(e.getSource()==jbArray[1])
		{//��ʾ��ǰ��ʾ��Ϣ��ǰһ����Ϣ��¼
			this.before();
		}
		if(e.getSource()==jbArray[2])
		{//��ʾ��ǰ��ʾ��Ϣ����һ����Ϣ��¼
			this.next();
		}		
	}
	public void before()
	{
		if(count==0)
		{//���������ݿ��һ����Ϣ�������������һ����Ϣ��
			count=vtemp.size()-1;
			String[] str = vtemp.get(count);				
		}
		else
		{//���򣬵�����һ����¼��
			count--;
		}
		String[] str = vtemp.get(count);
		for(int i=0;i<21;i++)
		{//����Ϣ���������ı�����
			jtxt[i].setText(str[i]);
		}			
		this.setPic(image.get(count));//��ʾ��ְ������Ƭ
	}
	public void next()
	{
		if(count==(vtemp.size()-1))
		{//���������ݿ����һ����Ϣ������������һ����Ϣ��
			count=0;
			String[] str = vtemp.get(count);
		}
		else
		{//���򵽴���һ����¼��
			count++;
		}
		String[] str = vtemp.get(count);
		for(int i=0;i<21;i++)
		{//�������ݿ��ѯ����Ϣ���������ı�����
			jtxt[i].setText(str[i]);				
		}			
		this.setPic(image.get(count));//��ʾְ����Ƭ
	}
	public void first()
	{//��ʾ���ݿ��е�һ����¼
		String[] str = vtemp.get(0);
		for(int i=0;i<21;i++)
		{//��ʾ���ı���
			jtxt[i].setText(str[i]);
		}
		this.setPic(image.get(0));//��ʾְ����Ƭ	
	}
	public void last()
	{//��ʾ���ݿ����һ����¼
		String[] str = vtemp.get(vtemp.size()-1);
		for(int i=0;i<21;i++)
		{//����Ϣ��ʾ�ڴ�����ı�����
			jtxt[i].setText(str[i]);
		}
		this.setPic(image.get(image.size()-1));//��ʾ��Ƭ	
	}
	public void setPic(Image Pic)
	{		
		final int width=150;//������ƬĬ�Ͽ��
		final int height=170;//	������ƬĬ�ϸ߶�
		if(Pic!=null)
		{
			int pw=Pic.getWidth(Person.this);//�õ���Ƭ��ʵ�ʿ��
			int ph=Pic.getHeight(Person.this);//�õ���Ƭ��ʵ�ʸ߶�
			if(pw>ph)
			{//���ͼƬ��ȴ��ڸ߶Ȱ��������
				Pic=Pic.getScaledInstance(width,width*ph/pw,Image.SCALE_SMOOTH);
			}
			else
			{//���ͼƬ���С�ڸ߶Ȱ��߶�����
				Pic=Pic.getScaledInstance(height*pw/ph,height,Image.SCALE_SMOOTH);
			}
			jl.setIcon(new ImageIcon(Pic));//����Ƭ��ӽ���Ƭ��ǩ
			jl.setHorizontalAlignment(JLabel.CENTER);//��Ƭˮƽ����
			jl.setVerticalAlignment(JLabel.CENTER);//��Ƭ��ֱ����
		}
		else
		{//����Ƭ��������Ƭ��ǩ����ʾĬ��ͼƬ
			jl.setIcon(null);
		}		
	}
	public void insertPerson()
	{		
		for(int i=0;i<21;i++)
		{//����ı������������Ϣ
			str[i]=jtxt[i].getText().trim();
		}
		if(str[0].equals("")&&str[1].equals("")&&str[2].equals("")&&str[3].equals("")
		             &&str[4].equals("")&&str[5].equals(""))
		{//��Ҫ��Ϣ����Ϊ����ʾ
			JOptionPane.showMessageDialog(this,	"ְ����Ϣ����Ϊ�գ�����",
						        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
	        return;	
		}
		if(!str[0].equals("")&&!str[1].equals("")&&!str[2].equals("")
		           &&!str[3].equals("")&&!str[4].equals("")&&!str[5].equals(""))
		{//���ְ����Ϣ�����ݿ�
		    int in=Integer.parseInt(jtxt[0].getText().trim());
			sql="insert into Person(EmployeeID,Name,Sex,Date,City,Nation,Polity,"
		      +"Culture,Marriage,Graduate,Spec,Speci,Wtype,Duty,Depart,IDcard,"+
		      "Address,Postcode,HomePhone,Mobile,Email) values("+in+",'"+str[1]+"','"
			  +str[2]+"','"+str[3]+"','"+str[4]+"','"+str[5]+"','"+str[6]+"','"
			  +str[7]+"','"+str[8]+"','"+str[9]+"','"+str[10]+"','"+str[11]+"','"
			  +str[12]+"','"+str[13]+"','"+str[14]+"','"+str[15]+"','"+str[16]+"','"
			  +str[17]+"','"+str[18]+"','"+str[19]+"','"+str[20]+"')";
			db=new DataBase();
			int i=db.updateDb(sql);
			String path=jtxt[21].getText();
			if(!path.equals("")&&i==1)
			{//����Ƭ��ӽ���ǩ
			    db.updatePic(path,in);				
			}
			JOptionPane.showMessageDialog(this,"��ӳɹ�����","��Ϣ!!",
			                   JOptionPane.INFORMATION_MESSAGE);//��ӳɹ���ʾ
			vtemp = db.getPerson();//���µõ���ϵ����Ϣ			                                   
			return;
		}
	}
	public void deletePerson()
	{
		String s=jtxt[0].getText().trim();//�õ�ְ��ID
		if(s.equals(""))
		{//δ��ְ��ID
			JOptionPane.showMessageDialog(this,	"ְ����Ų���Ϊ�գ�����",
						        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	    else if(!s.equals(""))
		{	int cd=Integer.parseInt(s);
			sql="select * from Person where EmployeeID="+cd+"";//��ѯ��ְ����Ϣ�Ƿ����
			db=new DataBase();
			db.selectDb(sql);
			try
			{
				if(db.rs.next())
				{//�������Ϊ��ִ��ɾ������
			    	sql="delete from Person where EmployeeID="+cd+"";
			    	db=new DataBase();
		        	db.updateDb(sql);
		        	JOptionPane.showMessageDialog(this,	"�ɹ�ɾ����ְ����Ϣ��¼����",
								        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
					vtemp = db.getPerson();//���µõ���ϵ����Ϣ
		        	return;	
				}
				else
				{//������ʾ�Ի���
					JOptionPane.showMessageDialog(this,	"û�и�ְ����",
								        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
					return;			        
				}
			}
	        catch(Exception e)
	        {//�����쳣
	    	   e.printStackTrace();
	        }		
		}	
	}
	public void updatePerson()
	{
		int in=Integer.parseInt(jtxt[0].getText().trim());//�õ�ְ��ID�ı�������ݲ�����תΪint��
		for(int i=0;i<21;i++)
		{//����ı������������Ϣ
			str[i]=jtxt[i].getText().trim();
		}
		if(!str[0].equals("")&&!str[1].equals("")&&!str[2].equals("")&&!str[3].equals("")
		   &&!str[4].equals("")&&!str[5].equals(""))
		{//ʵ��ְ����Ϣ�ĸ���
			sql="update person set Name='"+str[1]+"',Sex='"
			     +str[2]+"',Date='"+str[3]+"',City='"+str[4]+"',Nation='"
			     +str[5] +"',Polity='"+str[6]+"',Culture='"+str[7]+"',Marriage='" +str[8] 
			     +"',Graduate='"+str[9]+"',Spec='"+str[10]+"',Speci='"+str[11]+
			     "',Wtype='"+str[12]+"',Duty='" +str[13]+"',Depart='"+str[14] 
			     +"',IDcard='"+str[15] +"',Address='"+str[16]+"',Postcode='"+str[17]
			     +"',HomePhone='"+str[18]+"',Mobile='"+str[19]+"',Email='"
			     +str[20] +"' where EmployeeID='"+str[0]+"'";	     
			db=new DataBase();
			db.updateDb(sql);
			JOptionPane.showMessageDialog(this,"�޸���Ϣ�ɹ�����","��Ϣ!!",
			             JOptionPane.INFORMATION_MESSAGE);//�޸ĳɹ���ʾ				                               
			return;
		}
		else
		{//����Ϊ�յ�����ʾ�Ի���
			JOptionPane.showMessageDialog(this,"��������Ҫ�޸ĵ���Ϣ����",
			                      "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
			return;
		}								
	}	
	public static void main(String[]args)
	{
		new Person();
	}
}
