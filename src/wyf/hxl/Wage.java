package wyf.hxl;
import javax.swing.*;import java.awt.*;
import java.awt.event.*;import javax.swing.table.*;
import java.sql.*;import java.util.*;
import java.util.Date;import java.io.*;
public class Wage extends JPanel implements ActionListener
{
	private JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT);//���÷ָ��
	private JPanel jpt=new JPanel();
	String[] str=new String[10];//�����ַ�������
    DataBase db;
	String sql;//����SQL����
    Vector<String[]> vv;//�����ַ�������
    int count=0;
	private JLabel[] jlArray={//������ǩ��Ϊ��ָ���ı�
		new JLabel("   ְ �� ID"),new JLabel("    ��    ��"),
		new JLabel("  ��������"),new JLabel("  ��������"),
		new JLabel("  Ч�潱��"),new JLabel("  ���ϱ���"),
	    new JLabel("  ҽ�Ʊ���"),new JLabel("  �۷�����"),
	    new JLabel("  �۷�����"),  new JLabel("  ���ڿ۷�")
	};
	private JLabel jl=new JLabel("(��ѯ������ְ��ID������)");
	private JTextField[] jtxtArray=new JTextField[]{//�����ı�������
		new JTextField(),new JTextField(),
		new JTextField(),new JTextField(),
		new JTextField(),new JTextField(),
		new JTextField(),new JTextField(),
		new JTextField(),new JTextField()
	};
	private JButton[] jbArray={//����JButton��ť�����ı�
	    new JButton("��ǰ"),new JButton("��һ��"),
	    new JButton("��һ��"),new JButton("���"),
	    new JButton("��Ӽ�¼"),new JButton("�޸ļ�¼"),
	    new JButton("��ѯ"),new JButton("���") 
	};
	Vector<String> head = new Vector<String>();{//��������
		head.add("ְ��ID");head.add("����");
		head.add("��������");head.add("��������");
		head.add("Ч�潱��");head.add("���ϱ���");
		head.add("ҽ�Ʊ���");head.add("�۷�����");
		head.add("�۷�����");head.add("���ڿ۷�");
	}
	Vector<Vector> data=new Vector<Vector>();//���²��Ӵ��������ñ�����
    DefaultTableModel dtm=new DefaultTableModel(data,head);//�������ģ��
	JTable jt=new JTable(dtm);//����Jtable����
	JScrollPane jspn=new JScrollPane(jt);//��JTable��װ����������
	public Wage(){
		this.setLayout(new GridLayout(1,1));
		jpt.setLayout(null);//���������ϲ���Ϊ�ղ��ֹ�����
		jsp.setDividerLocation(205);//����jsp�зָ����ĳ�ʼλ��
		jsp.setDividerSize(4);//���÷ָ����Ŀ��
		jsp.setTopComponent(jpt);
		jsp.setBottomComponent(jspn);
		for(int i=0;i<10;i++){//��ʼ����ǩ���ı���
			jpt.add(jtxtArray[i]);
			jpt.add(jlArray[i]);
			if(i<5){//���õ�һ�б�ǩ���ı���Ĵ�Сλ�ò�Ϊ����Ӽ�����
			    jlArray[i].setBounds(15+i*160,20,70,20);
			    jtxtArray[i].setBounds(85+i*160,20,90,20);
			    jtxtArray[i].addActionListener(this);
			}
			else{//���õڶ��б�ǩ���ı���Ĵ�Сλ�ò�Ϊ����Ӽ�����
				jlArray[i].setBounds(15+(i-5)*160,60,70,20);
				jtxtArray[i].setBounds(85+(i-5)*160,60,90,20);
				jtxtArray[i].addActionListener(this);
			}
		}
		this.add(jsp);
		jpt.add(jl);
		jl.setBounds(405,175,150,20);//���ñ�ǩ�Ĵ�Сλ��
      	jsp.setBottomComponent(jspn);//�����²��Ӵ���
		for(int i=0;i<8;i++){
			jpt.add(jbArray[i]);//��JButton��ӽ�jpt
			if(i<4){//���õ�һ�а�ť�Ĵ�Сλ��
				jbArray[i].setBounds(125+150*i,105,100,25);
			}
			else{//���õڶ��а�ť�Ĵ�Сλ��
				jbArray[i].setBounds(125+150*(i-4),145,100,25);
			}
			jbArray[i].addActionListener(this);//Ϊ��ť��Ӽ�����
		}
		db = new DataBase();
		vv=db.getWage();//����DataBase���е�getWage����ʵ��wage��Ϣ������
		db.dbClose();		
		//���ô���Ĵ�Сλ�ü��ɼ���
		this.setBounds(5,5,600,500);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jtxtArray[0]){//��������jtxtArray[0]�ı���ʱ�������س�����������ת��jtxtArray[1]
	    	jtxtArray[1].requestFocus();
		}
		if(e.getSource()==jtxtArray[1]){//��������jtxtArray[1]�ı���ʱ�������س�����������ת��jtxtArray[2]
			jtxtArray[2].requestFocus();
		}
		if(e.getSource()==jtxtArray[2]){//��������jtxtArray[2]�ı���ʱ�������س�����������ת��jtxtArray[3]
			jtxtArray[3].requestFocus();
		}
		if(e.getSource()==jtxtArray[3]){//��������jtxtArray[3]�ı���ʱ�������س�����������ת��jtxtArray[4]
			jtxtArray[4].requestFocus();
		}
		if(e.getSource()==jtxtArray[4]){//��������jtxtArray[4]�ı���ʱ�������س�����������ת��jtxtArray[5]
			jtxtArray[5].requestFocus();
		}   
		if(e.getSource()==jtxtArray[5]){//��������jtxtArray[5]�ı���ʱ�������س�����������ת��jtxtArray[6]
			jtxtArray[6].requestFocus();
		}
		if(e.getSource()==jtxtArray[6]){//��������jtxtArray[6]�ı���ʱ�������س�����������ת��jtxtArray[7]
			jtxtArray[7].requestFocus();
		} 
		if(e.getSource()==jtxtArray[7]){//��������jtxtArray[7]�ı���ʱ�������س�����������ת��jtxtArray[8]
			jtxtArray[8].requestFocus();
		}
		if(e.getSource()==jtxtArray[8]){//��������jtxtArray[8]�ı���ʱ�������س�����������ת��jtxtArray[9]
			jtxtArray[9].requestFocus();
		}   
	    if(e.getSource()==jbArray[4]){//������"��Ӽ�¼"��ť��ִ�в������
			this.insert();
		}
		if(e.getSource()==jbArray[5]){//������"�޸�"��ť����ִ����Ϣ�ĸ��²���
			this.update();
		}if(e.getSource()==jbArray[6]){//������"��ѯ"��ť����ִ�в�ѯ����
			this.select();
		}
		if(e.getSource()==jbArray[7]){//������"���"��ť�����ı������
			for(int i=0;i<10;i++){//ִ����ղ���
				jtxtArray[i].setText("");
			}
		}
		if(e.getSource()==jbArray[0]){//������"��ǰ"��ť����ʾ���ݿ��еĵ�һ��wage��Ϣ
			this.first();
		}
		
		if(e.getSource()==jbArray[1]){//������"��һ��"��ť������ѭ��ִ����ʾ���ݿ�����һ����Ϣ
			this.before();
		}
		if(e.getSource()==jbArray[2]){//������"��һ��"��ť��
			this.next();
		}
		if(e.getSource()==jbArray[3]){//������"���"��ť����ʾ���һ����¼
			this.last();
		}	
	}
	public void first(){//��ʾ��һ����Ϣ
		String[] str = vv.get(0);//�õ���һ����Ϣ
		for(int i=0;i<10;i++){//����һ����Ϣ��ʾ���ı���
			jtxtArray[i].setText(str[i]);
		}
	}
	public void last(){//��ʾ���һ����Ϣ
		String[] str = vv.get(vv.size()-1);//�õ����һ����Ϣ
		for(int i=0;i<10;i++){//�����һ����Ϣ��ʾ
			jtxtArray[i].setText(str[i]);
		}
	}
	public void before(){
		if(count==0){//�������ݿ�ĵ�һ����¼�����������һ��ѭ����ʾ
			count=vv.size()-1;
		}
		else{//�����ǵ�һ����¼������ʾ��һ����¼
			count--;
		}
		String[] str = vv.get(count);//������ݿ���Ϣ
		for(int i=0;i<10;i++){//�����ݿ���Ϣ��ʾ���ı�����
			jtxtArray[i].setText(str[i]);
		}
	}
	public void next(){
		if(count==(vv.size()-1)){//�������ݿ�����һ����¼����������һ��ѭ����ʾ
				count=0;
		}
		else{//�粻�����һ����¼������ʾ��һ��
			count++;
		}
		String[] str= vv.get(count);//������ݿ���Ϣ
		for(int i=0;i<10;i++){//�����ݿ���Ϣ��ʾ���ı�����
			jtxtArray[i].setText(str[i]);
		}	
	}
	public void select(){
		String s1=jtxtArray[0].getText().trim();//�õ�ְ��ID
	    String s2=jtxtArray[1].getText().trim();//�õ�ְ������
	    if(!s1.equals("")&&!s2.equals("")){//���ְ��ID�������ı���Ϊ�գ���Ը�Ա������Ϣ���в�ѯ
			sql="select * from Wage where Name='"+s2+"' and EmployeeID="+Integer.parseInt(s1);
		    db=new DataBase();
			db.selectDb(sql);
			try{
				Vector <Vector> v=new Vector<Vector>();//��������
			    while(db.rs.next()){//�������Ϊ��
			       	Vector <String> vtemp = new Vector<String>();
				    for(int i=0;i<10;i++){//�Դӽ�����еĵ�����Ϣ����ת��  
				         String str1=new String(db.rs.getString(i+1).getBytes("ISO-8859-1"),"gb2312");
				         vtemp.add(str1);//���������Ϣ��ӵ���ʱ����
				    }
				    v.add(vtemp);//������������Ϣ������ʾ							
				    
			    }
			    if(v.size()==0){//�����Ϊ�ս�����ʾ
			     	JOptionPane.showMessageDialog(this,"������ѯ��ְ����Ϣ�����ڣ���","��ʾ",
				     JOptionPane.INFORMATION_MESSAGE);
				    return;
			    }
				dtm.setDataVector(v,head);//����table	
				jt.updateUI();
			    jt.repaint();
			    JOptionPane.showMessageDialog(this,"���Ѿ��ɹ���ѯ��ְ����Ϣ����","��ʾ",
						     JOptionPane.INFORMATION_MESSAGE);//
			    jtxtArray[0].setText("");
				jtxtArray[2].setText("");
				return;        
			 }
		     catch(Exception e){e.printStackTrace();}
		}
	}
	public void insert(){//��ӹ��ܵĿ���
		for(int i=0;i<10;i++){//����ı����е�����
			str[i]=jtxtArray[i].getText().trim();
		}
		if(str[0].equals("")&&str[1].equals("")&&str[2].equals("")&&str[3].equals("")&&str[4].equals("")
		&&str[5].equals("")&&str[6].equals("")&&str[7].equals("")&&str[8].equals("")&&str[9].equals(""))
		{//���ı����Ϊ�ս�����ʾ
			JOptionPane.showMessageDialog(this,	"��Ϣ����Ϊ�գ�����",
						        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
	        return;	
		}
		int in=Integer.parseInt(jtxtArray[0].getText().trim());//���õ�ְ��IDתΪint��
		if(!str[0].equals("")&&!str[1].equals("")&&!str[2].equals("")&&!str[3].equals("")&&!str[4].equals("")
		   &&!str[5].equals("")&&!str[6].equals("")&&!str[7].equals("")&&!str[8].equals("")&&!str[9].equals(""))
		{//���ı������Ϊ�գ�������Ϣ���
			sql="select EmployeeID from Person where EmployeeID="+in;//������ְ���Ƿ����
			db=new DataBase();
			db.selectDb(sql);
			try{
				if(db.rs.next()){//��ְ������
					int[] s=new int[8];
					for(int i=0;i<8;i++){//�õ����������Ϣ
						s[i]=Integer.parseInt(str[i+2]);
					}
					sql="select EmployeeID from Wage where EmployeeID="+in;//��ѯ��ְ����ְ��ID
					db=new DataBase();
					db.selectDb(sql);	
					if(!db.rs.next()){//��������ھͽ�ְ������Ϣ��ӽ����ݿ�
						sql="insert into Wage(EmployeeID,Name,Base_pay,Baseprize,Benifitprize,Insurance"
						+",Medicare,Deprivepay,Depriveprize,Depriveattend) values("+in+",'"+str[1]+	
						"',"+s[0]+","+s[1]+","+s[2]+","+s[3]+","+s[4]+","+s[5]+","+s[6]+","+s[7]+")";
						db=new DataBase();
						db.updateDb(sql);
						JOptionPane.showMessageDialog(this,"��ӳɹ�����",
						           "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);//��ӳɹ���ʾ
						return;
					}    	
					else{//�����ظ���ʾ
					      JOptionPane.showMessageDialog(this,"��ְ������Ϣ�Ѿ����,�����ظ�����",
						                  "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
						return;	
					}
				}
				else{//�ù�˾�޴�Ա������ʾ
					JOptionPane.showMessageDialog(this,"����˾û������ְ������",
					                                   "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
					return;
				}	
			}
			catch(Exception e){e.printStackTrace();}
		}
	}
	public void update(){
		int in=Integer.parseInt(jtxtArray[0].getText().trim());//�õ�ְ��ID
		for(int i=0;i<10;i++){//�õ��ı�������������
			str[i]=jtxtArray[i].getText().trim();
		}
		if(!str[0].equals("")&&!str[1].equals("")&&!str[2].equals("")&&!str[3].equals("")&&!str[4].equals("")
		&&!str[5].equals("")&&!str[6].equals("")&&!str[7].equals("")&&!str[8].equals("")&&!str[9].equals("")){
			int[] s=new int[9];//������������
			for(int i=0;i<8;i++){//�����������и�ֵ
				s[i]=Integer.parseInt(str[i+2]);
			}
			sql="update Attend set Name='"+str[1]+"',Basepay="+s[0]+",Baseprize="+s[1]+",Benifitprize="
			     +s[2] +",Insurance="+s[3]+",Medicare="+s[4]+",Deprivepay="+s[5]+",Depriveprize="+s[6]
			     +",Depriveattend="+s[7]+" where EmployeeID='"+str[0]+"'";//���¸�����Ϣ	     
			db=new DataBase();
			db.updateDb(sql);
			JOptionPane.showMessageDialog(this,"�޸���Ϣ�ɹ�����",
			               "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);//�޸���Ϣ�ɹ���ʾ
			return;
		}
		else{//�ı�������Ϊ����ʾ
			JOptionPane.showMessageDialog(this,"��������Ҫ�޸ĵ���Ϣ����",
			                    "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
										
	}
	public static void main(String[]args)
	{
		new Wage();
	}
}