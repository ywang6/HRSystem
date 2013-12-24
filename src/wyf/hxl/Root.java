package wyf.hxl;
import java.awt.*;import java.awt.event.*;
import javax.swing.event.*;import javax.swing.*;
import javax.swing.tree.*;import javax.xml.parsers.*;
import org.w3c.dom.*;import java.io.*;import java.net.*;
public class Root extends JFrame{
    DefaultMutableTreeNode[]  dmtn={new DefaultMutableTreeNode(new NodeValue("���¹���ϵͳ")),
	new DefaultMutableTreeNode(new NodeValue("�������ϵ���")),new DefaultMutableTreeNode(new NodeValue("���˼���")),
	new DefaultMutableTreeNode(new NodeValue("��ѯְ������")),new DefaultMutableTreeNode(new NodeValue("���ʹ���")),
	new DefaultMutableTreeNode(new NodeValue("���ڹ���")),new DefaultMutableTreeNode(new NodeValue("�˳�"))};//�����ڵ�����
    DefaultTreeModel dtm=new DefaultTreeModel(dmtn[0]);//������ģ�ͣ�ָ�����ڵ�Ϊ"���¹���ϵͳ"
    JTree jt=new JTree(dtm);//��������dtm��ģ�͵�JTree����
    JScrollPane jsp=new JScrollPane(jt); //ΪJTree������������
    private JSplitPane jsplr=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    private JPanel jp=new JPanel();//����JPanel����
	private JLabel jlRoot=new JLabel();
	CardLayout cl=new CardLayout();//��ȡ��Ƭ���ֹ���������
    public Root(){
        for(int i=1;i<7;i++){dtm.insertNodeInto(dmtn[i],dmtn[0],i-1);}//����ڵ�����ӽڵ�	
		jt.setEditable(false);//���ø����нڵ��ǲ��ɱ༭��
		this.add(jsplr);//���������Ĺ���������ӽ�����
		jsplr.setLeftComponent(jt);//���������Ĺ���������ӽ���ߵ��Ӵ���
		jsplr.setRightComponent(jp);        
        jsplr.setDividerLocation(200);//���÷ָ����ĳ�ʼλ��    
        jsplr.setDividerSize(4); //���÷ָ����Ŀ�� 
        jlRoot.setFont(new Font("Courier",Font.PLAIN,30));
		jlRoot.setHorizontalAlignment(JLabel.CENTER);
		jlRoot.setVerticalAlignment(JLabel.CENTER);	
		jt.setShowsRootHandles(true);//������ʾ���ڵ�Ŀ���ͼ��
    	jp.setLayout(cl);
    	jp.add(jlRoot,"root");//��ʼ�����ڵ�
    	jp.add(new Person(),"person");
    	jp.add(new Introduce(),"introduce");
    	jp.add(new SearchMessage(),"Search");
    	jp.add(new Wage(),"wage");
    	jp.add(new Attend(),"attend");
    	jt.addTreeSelectionListener(new TreeSelectionListener(){//���ڲ�����ʾ���ĸ�ѡ��ڵ�
			public void valueChanged(TreeSelectionEvent e){
				DefaultMutableTreeNode cdmtn=(DefaultMutableTreeNode)e.getPath().getLastPathComponent();				
				NodeValue cnv=(NodeValue)cdmtn.getUserObject();
				if(cnv.value.equals("���¹���ϵͳ")){cl.show(jp,"root");}//��ʾ������
                if(cnv.value.equals("�������ϵ���")){cl.show(jp,"person");}
				if(cnv.value.equals("���˼���")){cl.show(jp,"introduce");}
				if(cnv.value.equals("��ѯְ������")){cl.show(jp,"Search");}//��ʾ��ѯְ�����Ͻ���
				if(cnv.value.equals("���ʹ���")){cl.show(jp,"wage");}
				if(cnv.value.equals("���ڹ���")){cl.show(jp,"attend");}
				else if(cnv.value.equals("�˳�")){//��ѡ��˵���ʾ�Ƿ��˳�ϵͳ
					int i=JOptionPane.showConfirmDialog(Root.this,"�Ƿ��˳�ϵͳ?",
							"��Ϣ",JOptionPane.YES_NO_OPTION);
					if(i==JOptionPane.YES_OPTION){System.exit(0);}}								
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô���Ĺرն���	
 		Image image=new ImageIcon("ico.gif").getImage();//��logoͼƬ���г�ʼ��	 
 		this.setIconImage(image);
 		Image icon = Toolkit.getDefaultToolkit().getImage("rs.jpg");//����������ͼƬ
		ImageIcon ic = new ImageIcon(icon);//��ʼ��ͼƬ
		jlRoot.setIcon(ic);
		this.setTitle("����ʵҵ���¹���ϵͳ");	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;//���ô����״γ��ֵĴ�С��λ��--�Զ�����
		int centerY=screenSize.height/2;
		int w=800;//��������
		int h=600;//������߶�
		this.setBounds(centerX-w/2,centerY-h/2-100,w,h);//���ô����������Ļ����
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);//����ȫ��
		this.setVisible(true);
    }
    public static void main(String[]args){new Root();}
}
class NodeValue{//�Զ���ĳ�ʼ�����ڵ�����ݶ������
	String value;	
	public NodeValue(String value){this.value=value;}//������
	public String getValue(){return this.value;}
	@Override
	public String toString(){return value;}//��д�ķ���
}