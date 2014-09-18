package saolei;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class JMineFrame {
	JFrame frame;
	JButton start;
	JButton[][] buts=new JButton[10][10];
	//������buts��Ӧ��λ���Ƿ��ǵ���
	char[][] lei = new char[10][10];
	//������
	int[][] counts = new int[10][10];
	
	public void initFrame()
	{
		frame=new JFrame("ɨ��");
		//���ý���Ĵ�С
		frame.setSize(500, 500);
		frame.setLocation(200, 100);
		//����5������
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		frame.add(mainPanel);
		//�ϲ�_��ť���
		JPanel northPanel=new JPanel();
		//��ӿ�ʼ��ť
		start=new JButton("���¿�ʼ");
		northPanel.add(start);
		//����ʼ��ť���ʱ��
		
		MyClick click = new MyClick();
		start.addMouseListener(click);
		mainPanel.add(northPanel,BorderLayout.NORTH);
		//�м�_���ɨ�����
		JPanel centerPanel =createCenter();
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		//�Ҳ�_ʱ�����
		JPanel rightPanel=createSouth();
		
		mainPanel.add(rightPanel,BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	public JPanel createSouth()
	{
		JPanel south=new JPanel();
		JPanel timePanel=new JPanel();
		JPanel minePanel=new JPanel();
		JLabel timeLable=new JLabel("����ʱ��\n");
		JLabel time=new JLabel("60");
		JLabel leftMine=new JLabel("ʣ���������\n");
		JLabel mine=new JLabel("10");
		timePanel.add(timeLable);
		timePanel.add(time);
		minePanel.add(leftMine);
		minePanel.add(mine);
		south.add(timePanel);
		south.add(minePanel);
		return south;
	}
	public JPanel createCenter()
	{
		JPanel center =new JPanel();
		//����createCenter����
		center.setLayout(new GridLayout(10,10));
		MyClick click =new MyClick();
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++){
					
				JButton but =new JButton();
				//but.setBackground(new Color(86, 192, 120));
				//but.setForeground(Color.white);
				buts[i][j]=but;
				but.addMouseListener(click);
				
			
			
				center.add(but);
			}
		}
		return center;
	}
	
	public void start(){
		for(int i=0;i<lei.length;i++)
		{
			for(int j=0;j<lei[i].length;j++)
			{
				lei[i][j]=' ';
			}
		}
		for(int a=0;a<10;a++){
			int i = (int)(Math.random()*10);
			int j = (int)(Math.random()*10);
		    if(lei[i][j]=='��'){
		    	a--;
		    }else{
		    	lei[i][j] = '��';
		    }
		}
		//ͳ��ÿ��λ�õĵ��׸���
		for(int i=0;i<lei.length;i++){
			for(int j=0;j<lei[i].length;j++){
				counts[i][j]=count(i,j);
			}
		}
	}
	//ͳ��ĳ��λ�õĵ��׸���
	public int count(int i,int j){
		int count = 0;
		for(int a=i-1;a<=i+1;a++){
			for(int b=j-1;b<=j+1;b++){
				if(a>=0 && b>=0 && a<lei.length && b<lei[a].length &&!(a==i && b==j)){
					if(lei[a][b]=='��'){
						count++;
					}
				}
			}
		}
		return count;
	}
	//����
	public void unfold(int i,int j){
		for(int a=i-1;a<=i+1;a++){
			for(int b=j-1;b<=j+1;b++){
				if(a>=0 && b>=0 && a<lei.length && b<lei[a].length &&!(a==i && b==j)){
					if(buts[a][b].isEnabled())
					{
						if(counts[a][b]==0)
						{
							buts[a][b].setEnabled(false);
							unfold(a,b);
						}else
						{
							buts[a][b].setText(counts[a][b]+"");
							buts[a][b].setEnabled(false);
						}
					}
				}
			}
		}
	}
	
	class MyClick extends MouseAdapter implements ActionListener{
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if(e.getButton()==MouseEvent.BUTTON1)//��������
			{
				
				for(int i=0;i<buts.length;i++)
				{
					for(int j=0;j<buts[i].length;j++)
					{
						if(e.getSource()==buts[i][j])
						{
							if(lei[i][j]=='��')
							{
								for(int a=0;a<lei.length;a++)
								{
									for(int b=0;b<lei[a].length;b++)
									{
										if(lei[a][b]=='��')
										{
											//buts[a][b].setIcon(defaultIcon);
											buts[a][b].setText("��");
											buts[a][b].setEnabled(false);
										}
									}
								}
								JOptionPane.showConfirmDialog(frame, "����ը�����ˣ�����....");
							}
							else{
								//û�е���
								if(counts[i][j]==0)
								{
									//��Χû�е���
									buts[i][j].setEnabled(false);
									//����չ����Χ8������
									unfold(i,j);
								}
								else
								{
									//��Χ�е���
									buts[i][j].setText(counts[i][j]+"");
									buts[i][j].setEnabled(false);
								}
								if(isWin())
								{
									JOptionPane.showConfirmDialog(frame, "���ɹ������ˣ�");
								}
								
							}
						}
					}
				}
				
			}else if(e.getButton()==MouseEvent.BUTTON3)
			{
				
				
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO �Զ����ɵķ������
			if(e.getSource()==start)
			{
				for(int i=0;i<10;i++)
				{
					for(int j=0;j<10;j++){
						buts[i][j].setText("");
							buts[i][j].setEnabled(true);;
					}
				}
				initFrame();
				start();
			}
		}
	}
	
	//�ж���Ӯ
	public boolean isWin()
	{
		int count=0;
		for(int i=0;i<lei.length;i++)
		{
			for(int j=0;j<lei[i].length;j++)
			{
				if(buts[i][j].isEnabled())
				{
					count++;
				}
			}
		}
		return count==10;
	}
	class LeiTime extends Thread{
		
	}
	//��ʱ��
	public void countTime()
	{
		int count=0;
		while(true)
		try{
			Thread.sleep(1000);
			count++;
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static void main(String[] args) {
		JMineFrame f=new JMineFrame();
		f.initFrame();
		f.start();
	}

}
