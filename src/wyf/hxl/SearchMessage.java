package wyf.hxl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
public class SearchMessage extends JPanel implements ActionListener{
	int flag;
	String sql;
	DataBase db;
    //�����ָ��Ϊ���µ�JSplitePane����
    private JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);
	private JPanel jpt=new JPanel();//����JPanel����
	private JPanel jpb=new JPanel();
	//������ʾ�����б������ģ�͵��ַ�������
	private String[] str={"ְ��ID","����","����","���֤��"};
	private JComboBox jcb=new JComboBox(str);//���������б��
	private JButton jb=new JButton("�ύ");	//������ť
	private JButton jb1=new JButton("���");	
	private JLabel[] jlArray=new JLabel[]{
		new JLabel("       ��    ��"),
		new JLabel("     ��    ��"),
		new JLabel("��ƾ")
	};	
	private JTextField[] jtxtArray=new JTextField[]{//�����ı���
		new JTextField(),new JTextField(),
	    new JTextField(),new JTextField()
	};
	private JRadioButton[] jrbArray={//������ѡ��ť
		new JRadioButton("�򵥲�ѯ",true),
		new JRadioButton("�߼���ѯ")
	};	
	private ButtonGroup bg=new ButtonGroup();//������ť��
	Vector<String> head = new Vector<String>();
	{//�����ͷ
		head.add("ְ��ID");head.add("����");
		head.add("�Ա�");head.add("��������");
		head.add("����");head.add("����");
		head.add("������ò");head.add("��ƾ");
		head.add("����״��");head.add("��ҵѧУ");
		head.add("��ѧרҵ");head.add("�����س�");
		head.add("����");head.add("ְ��");
		head.add("��������");head.add("���֤��");
		head.add("��ͥסַ");head.add("��������");
		head.add("סլ�绰");head.add("�ֻ�");
		head.add("Email");
	}
	Vector<Vector> data=new Vector<Vector>();//����������ĵĻ�����Ϣ
    DefaultTableModel dtm=new DefaultTableModel(data,head);	//�������ģ��
	JTable jt=new JTable(dtm); //����Jtable����
	JScrollPane jspn=new JScrollPane(jt);//��JTable��װ����������
	public SearchMessage(){
		this.setLayout(new GridLayout(1,1));//���ý���Ϊ���񲼾�
		jpt.setLayout(null);//���������������²��־�Ϊ�ղ��ֹ�����
		jpb.setLayout(null);
		jpt.add(jcb);
		jcb.setBounds(160,20,150,20);//���õ�ѡ��Ĵ�С��λ��	
	    jcb.addActionListener(this);//Ϊ��ѡ������¼�������
		jpt.add(jb);
		jb.setBounds(510,20,80,20); //����JButton���Сλ��
		jb.addActionListener(this);
		jpt.add(jb1);
		jb1.setBounds(600,20,80,20);
		jb1.addActionListener(this);
		for(int i=0;i<2;i++){//�Ե�ѡ��ť��������
			jrbArray[i].setBounds(20,20+i*40,100,20);
			jpt.add(jrbArray[i]);
			jrbArray[i].addActionListener(this);
			bg.add(jrbArray[i]);
		}
		for(int i=0;i<3;i++){//���ñ�ǩ���ı�������꣬��������ӽ�JPanel
			jlArray[i].setBounds(120+i*200,60,80,20);
			jtxtArray[i].setBounds(200+i*180,60,120,20);
			jpt.add(jtxtArray[i]);	
			jpt.add(jlArray[i]);
		}
		for(int i=0;i<3;i++){//�����ı���Ϊ������
			jtxtArray[i].setEditable(false);
		}
    	//�����ı��������,����ӽ�jpt
		jtxtArray[3].setBounds(350,20,120,20);
		jpt.add(jtxtArray[3]);
        jsp.setTopComponent(jpt);//��jpt���õ�jsp���ϲ�����
        jsp.setBottomComponent(jspn);
        jsp.setDividerSize(4);
       	this.add(jsp);
    	jsp.setDividerLocation(100);//����jsp�зָ����ĳ�ʼλ��
		//���ô���Ĵ�Сλ�ü��ɼ���
        this.setBounds(3,10,600,400);
        this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb1)
		{
			for(int i=0;i<4;i++)
			{//����ı���
				jtxtArray[i].setText("");
			}
	        int r=dtm.getRowCount();
	        for(int p=0;p<r;p++)
	        {//��ձ��
	        	dtm.removeRow(p);
	        }
		}
	    if(jrbArray[0].isSelected())
	    {//"�򵥲�ѯ"��ѡ��ť��ѡ��
	    	jtxtArray[3].setEditable(true);
	    	for(int i=0;i<jtxtArray.length-1;i++)
	    	{//�����ı���Ϊ���ɱ༭
	    		jtxtArray[i].setEditable(false);
	    	}
	    	if(jcb.getSelectedIndex()>=0&&jcb.getSelectedIndex()<4)
	    	{
		    	jtxtArray[3].requestFocus();	    
			    if(e.getSource()==jb)
			    {
			    	this .simplesearch();
					this.table();//��ʾ��ѯ���			
				}
	        }
	    }
		if(jrbArray[1].isSelected())
		{//"�߼���ѯ"��ѡ��ť��ѡ��
			 jtxtArray[0].requestFocus(); //������뽹��
			 jtxtArray[3].setEditable(false);
	       	 for(int i=0;i<jtxtArray.length-1;i++)
	       	 {//���߼���ѯ���漰���ı�����Ϊ�ɱ༭
	    		jtxtArray[i].setEditable(true);
	    	 }
			 if(e.getSource()==jb)
			 {//���"�ύ"��ť
			 	int bz=this.seniorSearch();
			 	if(bz!=0){return;}
			 	db=new DataBase();
				db.selectDb(sql);
				this.table();		
			 } 	
		}    
	}
	public void simplesearch()
	{//����¼�ԴΪ"�ύ"��ť����ִ�м���
		String str=jtxtArray[3].getText().trim();
		if(str.equals(""))
		{
			JOptionPane.showMessageDialog(this,"�������Ҫ����Ϣ������",
							"��Ϣ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	    if(jcb.getSelectedIndex()==0)
	    {//����ְ��ID���в�ѯ
			sql="select * from Person where EmployeeID='"+str+"'";
	    	jtxtArray[3].setText("");
		}
		else if(jcb.getSelectedIndex()==1)
		{//�����������в�ѯ
			sql="select * from Person where Name='"+str+"'";
			jtxtArray[3].setText("");
		}
		else if(jcb.getSelectedIndex()==2)
		{//���ݹ��ֽ��в�ѯ
			sql="select * from Person where Wtype='"+str+"'";
			jtxtArray[3].setText("");
		}
		else
		{//�������֤�Ž��в�ѯ
			sql="select * from Person where IDcard='"+str+"'";
			jtxtArray[3].setText("");
		}
		db=new DataBase();
		try
		{//����ת��
			sql = new String(sql.getBytes(),"ISO-8859-1");
		}
		catch(Exception ae){ae.printStackTrace();}
		db.selectDb(sql);
	}
	public int seniorSearch()
	{
		int flag=0;//���ñ�־λ
		String str0=jtxtArray[0].getText().trim();
		String str1=jtxtArray[1].getText().trim();
		String str2=jtxtArray[2].getText().trim();
		if(str0.equals("")&&str1.equals("")&&str2.equals("")){//�ı�������Ϊ��
			JOptionPane.showMessageDialog(this,"�������Ҫ����Ϣ������",
								"��Ϣ",JOptionPane.INFORMATION_MESSAGE);
			flag++;
		}
		if(((!str0.equals(""))&&(str1.equals(""))&&(str2.equals("")))
		     ||((str0.equals(""))&&(!str1.equals(""))&&(str2.equals("")))
		     ||((str0.equals(""))&&(str1.equals(""))&&(!str2.equals(""))))
		{
			JOptionPane.showMessageDialog(this,"��ʹ�ü򵥲�ѯ������",
								"��Ϣ",JOptionPane.INFORMATION_MESSAGE);
			flag++;
		}
	    if((!str0.equals(""))&&(!str1.equals(""))&&(str2.equals("")))
	    {//�����͹������
			sql="select * from Person where Name='"+str0+"' and Wtype='"+str1+"'";
			jtxtArray[0].setText("");jtxtArray[1].setText("");
		}
		if((!str0.equals(""))&&(str1.equals(""))&&(!str2.equals("")))
		{//�������Ļ��̶����
			sql="select * from Person where Name='"+str0+"' and Culture='"+str2+"'";
			jtxtArray[0].setText("");jtxtArray[2].setText("");
		}
		if((str0.equals(""))&&(!str1.equals(""))&&(!str2.equals("")))
		{//���ֺ��Ļ��̶����
			sql="select * from Person where Wtype='"+str1+"' and Culture='"+str2+"'";
			jtxtArray[1].setText("");jtxtArray[2].setText("");
		}
		if((!str0.equals(""))&&(!str1.equals(""))&&(!str2.equals("")))
		{//�������
			sql="select * from Person where Name='"+str0
						+"' and Culture='"+str2+"' and Wtype='"+str1+"'";
			jtxtArray[0].setText("");jtxtArray[1].setText("");jtxtArray[2].setText("");
		}
		return flag;
	}
	public void table()
	{//�ӱ��м����ɹ��󣬰Ѳ鵽�ĵ�������Ϣ��ʾ�ڽ����²��ֵı���     
		try
		{
			 Vector <Vector> v=new Vector<Vector>();
		     while(db.rs.next())
		     {
		       	 Vector <String> vtemp = new Vector<String>();
			     for(int i=0;i<21;i++)
			     {//ȡ�������   
			         String str1=new String(db.rs.getString(i+1).getBytes("ISO-8859-1"),"gb2312");
			         vtemp.add(str1);//��ÿ����ӵ���ʱ����v
			     }
			     v.add(vtemp);//��������¼��ӵ�����							
		     }
		     if(v.size()==0)
		     {//��ѯʧ�ܣ��򵯳���ʾ�Ի���
		     	JOptionPane.showMessageDialog(this,"������ѯ��ְ����Ϣ�����ڣ���","��ʾ",
			     JOptionPane.INFORMATION_MESSAGE);
			    return;
		     }
			dtm.setDataVector(v,head);//����table
			jt.updateUI();
		    jt.repaint();
		    JOptionPane.showMessageDialog(this,"���Ѿ��ɹ���ѯ��ְ����Ϣ����","��ʾ",
					     JOptionPane.INFORMATION_MESSAGE);//��ѯ�ɹ����򵯳���ʾ�Ի���
			return;        
		 }
	     catch(Exception e)
	     {//�����쳣
		     e.printStackTrace();
	     }
	}
	public static void main(String[]args)
	{
		new SearchMessage();
	}
}