package wyf.hxl;
import javax.swing.*;import java.awt.*;
import java.awt.event.*;import javax.swing.table.*;
import javax.swing.event.*;import java.sql.*;
import java.util.*;import java.util.Date;
public class Login extends JFrame implements ActionListener{
	private JPanel jp=new JPanel();//����JPanel����
    private JLabel []jlArray={new JLabel("�û���"),new JLabel("��  ��")}; //������ǩ����
    private JButton[] jbArray={new JButton("��¼"),new JButton("���")};//������ť����
    private JTextField jtxt=new JTextField("wyf");//�����ı��� 
    private JPasswordField jpassword=new JPasswordField("hxl");//���������
    String sql;
    DataBase db;
    public Login(){
    	jp.setLayout(null);	
    	for(int i=0;i<2;i++){//���ñ�ǩ�Ĵ�С��λ��
        	jlArray[i].setBounds(70,20+i*50,80,26);
        	jp.add(jlArray[i]);
        }
        for(int i=0;i<2;i++){
        	jbArray[i].setBounds(80+i*120,130,100,26);//���ð�ť�Ĵ�Сλ��
        	jp.add(jbArray[i]);	
        	jbArray[i].addActionListener(this);//Ϊ��ťע�ᶯ���¼�������       
        }  
        jtxt.setBounds(130,20,180,30);//�����ı���Ĵ�Сλ��
        jp.add(jtxt);//���ı�����ӽ�JPanel���� 
        jtxt.addActionListener(this);//Ϊ�ı���ע���¼������� 
        jpassword.setBounds(130,70,180,30);//���������Ĵ�Сλ��
        jp.add(jpassword);//���������ӽ�JPanel���� 
        jpassword.setEchoChar('*');//���������� �����ַ� 
        jpassword.addActionListener(this);//Ϊ�����ע�������
        this.add(jp);
 		Image image=new ImageIcon("ico.gif").getImage(); //��logoͼƬ���г�ʼ��	  
 		this.setIconImage(image);
        this.setTitle("��¼");
        this.setResizable(false);
        this.setBounds(100,100,400,300);//���ô���Ĵ�Сλ��
        this.setVisible(true);//���ô���Ŀɼ���
    }
	public void actionPerformed(ActionEvent e){
		String mgno=jtxt.getText().trim();
	    if(e.getSource()==jtxt){//�л����뽹�㵽�����
			jpassword.requestFocus();
		}
		else if(e.getSource()==jbArray[1]){//����"���"��ť������ս����ϵ�������Ϣ
			jtxt.setText("");
			jpassword.setText("");
			jtxt.requestFocus();//�����뽹�����õ��ı���
		}
		else if(e.getSource()==jbArray[0]){//�¼�ԴΪ����Ա��¼��ť
	    	String no=jtxt.getText().trim();
		    if(jtxt.getText().trim().equals("")){//�û�����ʾΪ��
				JOptionPane.showMessageDialog(this,"�û�������Ϊ�գ�","��Ϣ",JOptionPane.INFORMATION_MESSAGE);
		    	return;
			}
			if(jpassword.getText().trim().equals("")){//�û�����Ϊ����ʾ
				JOptionPane.showMessageDialog(this,"�û����벻��Ϊ�գ�","��Ϣ",JOptionPane.INFORMATION_MESSAGE);
		    	return;
			}
	        sql="select mgNo,password from man where mgNo='"+no+"'";//��֤��¼�ߵ���ݺ�Ȩ��
	        db=new DataBase();
			db.selectDb(sql);
			try{
				String mgNo="wyf";
				String password="hxl";
				while(db.rs.next()){//��ý������Ϣ
				     mgNo=db.rs.getString(1).trim();
			         password=db.rs.getString(2).trim();					
				}
	        	if(jtxt.getText().trim().equals(mgNo)&&
	    		String.valueOf(jpassword.getPassword()).equals(password)){//��¼�ɹ���ʾ
	    			JOptionPane.showMessageDialog(this,"��ϲ������¼�ɹ�������","��Ϣ",
	    			     JOptionPane.INFORMATION_MESSAGE);
	    			new Root();
	    			this.dispose();   			
	    		}
	    		else{//��¼ʧ����ʾ
	    			JOptionPane.showMessageDialog(this,"��¼ʧ�ܣ�","��Ϣ",
	    			     JOptionPane.INFORMATION_MESSAGE);
	    	        return;  
	    		}
			}
		    catch(Exception e1){e1.printStackTrace();}  
		    db.dbClose();//�ر����ݿ�����   
		}
	}
    public static void main(String[]args)
    {
    	new Login();
    }
}