package wyf.hxl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
public class Introduce extends JPanel implements ActionListener
{
	private JTextArea[] jta=
	{//�����ı���
		new JTextArea(),
		new JTextArea(),
		new JTextArea()	
	};
	private JScrollPane jsp1=new JScrollPane(jta[0]);//��jta[0]�����������
    private JScrollPane jsp2=new JScrollPane(jta[1]);
	private JScrollPane jsp3=new JScrollPane(jta[2]);
	DataBase db;
	String sql;
	private JButton[] jb=
	{//������ť���ò�Ϊ��ָ���ı�
	      new JButton("��Ӽ���"),	      
	      new JButton("��    ѯ"),
	      new JButton("��    ��")	
	};
	private JLabel jl=new JLabel("������ְ��ID");
	private JLabel[] jl1=
	{//���ñ�ǩ��Ϊ��ָ���ı�
		new JLabel("���˽���"),
		new JLabel("��������"),
		new JLabel("��    ��")
	};
	private JTextField jtf=new JTextField();//�����ı���
	public Introduce()
	{
		this.setLayout(null);//����JPanelΪ�ղ���
		for(int i=0;i<3;i++)
		{//��ʼ���ı���
			jta[i].setLineWrap(true);//�������Զ�����              
			this.add(jl1[i]);
			jta[i].append("");
		}
		jsp1.setBounds(20,30,500,155);//���õ�һ����������Ĵ�С��λ��
		jsp2.setBounds(20,225,500,155);
		jsp3.setBounds(20,420,500,155);
		this.add(jsp1);//����һ������������ӽ�����
		this.add(jsp2);
		this.add(jsp3);
		for(int i=0;i<3;i++)
		{//��ʼ����ť��Ϊ����Ӽ�����
			this.add(jb[i]);
			jb[i].addActionListener(this);
			jb[i].setBounds(230+i*100,600,90,25);			
		}
		for(int i=0;i<3;i++)
		{//Ϊ��ǩ���ô�С����λ��
			jl1[i].setBounds(5,5+i*190,80,20);
		}
		this.add(jl);	
		jl.setBounds(20,600,80,25);
		this.add(jtf);
		jtf.setBounds(100,600,100,25);//�����ı���Ĵ�С��λ��
		//���ô���Ĵ�Сλ�ü��ɼ���		
		this.setBounds(10,10,550,700);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jb[0])
		{//ʵ�ָ��˼��������
			this.insert();
		}
		if(e.getSource()==jb[1])
		{//ʵ�ָ��˼����Ĳ�ѯ����ʾ
			this.search();
		}
		if(e.getSource()==jb[2])
		{
			for(int i=0;i<3;i++)
			{//����ı���
			    jta[i].setText("");				
			}
		}
	}
	public void insert()
	{
		try
		{
			String[] s1=new String[3];
			for(int i=0;i<3;i++)
			{//�õ��ı�������
				s1[i]=jta[i].getText().trim();
			}
			String s=s1[0]+"|"+s1[1]+"|"+s1[2];
			if(jtf.getText().length()!=0)
			{//�ı�����Ϊ��
				if(! (jtf.getText().trim().matches("^\\d+$")))
	 			{//�жϹ���Ա���ĸ�ʽ
					JOptionPane.showMessageDialog(this,	"����Ա��ֻ��Ϊ���֣�����",
					        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
					return;							
				}
				int in=Integer.parseInt(jtf.getText().trim());
				sql="select EmployeeID from Person where EmployeeID="+in;//ȷ�ϸ�ְ��ID��ְ���Ƿ����
			    db=new DataBase();
				db.selectDb(sql);
				if(db.rs.next())
				{
					if(s1[0].equals("")||s1[1].equals("")||s1[2].equals(""))
					{//�ı���Ϊ��
						JOptionPane.showMessageDialog(this,"���������ı����������Ҫ��Ϣ������",
						          "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(!s.trim().equals("||"))
					{//�ı�����Ϊ��ִ�в������
						sql="update person set Resume='"+s+"' where EmployeeID="+in;
						db=new DataBase();
						db.updateDb(sql);
						JOptionPane.showMessageDialog(this,"��ӳɹ�����",
						       "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);//��ӳɹ���ʾ
						return;
					}
				}
				else
				{//������ְ����������ʾ
					JOptionPane.showMessageDialog(this,"����˾û������ְ������",
					                 "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
			else
			{//ְ��ID�ı�������Ϊ�ս�����ʾ
				JOptionPane.showMessageDialog(this,"������ְ��ID����",
						  "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}					
		catch(Exception e0)
		{//�����쳣
			e0.printStackTrace();
		}		
	}
	public void search()
	{
		try
		{
			if(jtf.getText().length()!=0)
			{//���ְ��ID�ı���Ϊ��
				if(! (jtf.getText().trim().matches("^\\d+$")))
	 			{//�жϹ���Ա���ĸ�ʽ
					JOptionPane.showMessageDialog(this,	"����Ա��ֻ��Ϊ���֣�����",
					        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
					return;							
				}
				int in=Integer.parseInt(jtf.getText().trim());
				sql="select EmployeeID from Person where EmployeeID="+in;//ȷ��ְ����ݵĺϷ���
			    db=new DataBase();
				db.selectDb(sql);
				if(db.rs.next())
				{//��ְ������
						sql="select Resume from person where EmployeeID="+in;//��ѯ����˼���
						db=new DataBase();
						db.selectDb(sql);
						if(db.rs.next())
						{//���������
							String ss=db.rs.getString(1);
							String[] s2=ss.split("\\|");//�������"|"�ָ�
							for(int i=0;i<3;i++)
							{//�����˼�鲻ͬ��������ӽ����Ե�����
								s2[i]=new String(s2[i].getBytes("ISO-8859-1"),"gb2312");
								jta[i].setText(s2[i]);
							}
							JOptionPane.showMessageDialog(this,"��ѯ�ɹ�����",
					               "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
			                return;
						}
						else
						{//��ְ�����˼���������ʾ
							JOptionPane.showMessageDialog(this,"��ְ�����˼������꣡��",
						          "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
						      return;
					    }	
				}
				else
				{//ְ����ݲ��Ϸ���ʾ
					JOptionPane.showMessageDialog(this,"����˾û������ְ������",
					                 "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
			else
			{//ְ��ID�ı�������Ϊ��
				JOptionPane.showMessageDialog(this,"������ְ��ID����",
						  "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}					
		catch(Exception e0)
		{//�����쳣
			e0.printStackTrace();
		}		
	}
	public static void main(String[]args)
	{
		new Introduce();
	}
	
}
