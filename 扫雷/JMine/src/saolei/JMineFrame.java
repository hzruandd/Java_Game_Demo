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
	//保存于buts对应的位置是否是地雷
	char[][] lei = new char[10][10];
	//保存与
	int[][] counts = new int[10][10];
	
	public void initFrame()
	{
		frame=new JFrame("扫雷");
		//设置界面的大小
		frame.setSize(500, 500);
		frame.setLocation(200, 100);
		//设置5个布局
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		frame.add(mainPanel);
		//上部_按钮面板
		JPanel northPanel=new JPanel();
		//添加开始按钮
		start=new JButton("重新开始");
		northPanel.add(start);
		//给开始按钮添加时间
		
		MyClick click = new MyClick();
		start.addMouseListener(click);
		mainPanel.add(northPanel,BorderLayout.NORTH);
		//中间_面板扫雷面板
		JPanel centerPanel =createCenter();
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		//右侧_时间面板
		JPanel rightPanel=createSouth();
		
		mainPanel.add(rightPanel,BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	public JPanel createSouth()
	{
		JPanel south=new JPanel();
		JPanel timePanel=new JPanel();
		JPanel minePanel=new JPanel();
		JLabel timeLable=new JLabel("已用时：\n");
		JLabel time=new JLabel("60");
		JLabel leftMine=new JLabel("剩余地雷数：\n");
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
		//设置createCenter布局
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
		    if(lei[i][j]=='雷'){
		    	a--;
		    }else{
		    	lei[i][j] = '雷';
		    }
		}
		//统计每个位置的地雷个数
		for(int i=0;i<lei.length;i++){
			for(int j=0;j<lei[i].length;j++){
				counts[i][j]=count(i,j);
			}
		}
	}
	//统计某个位置的地雷个数
	public int count(int i,int j){
		int count = 0;
		for(int a=i-1;a<=i+1;a++){
			for(int b=j-1;b<=j+1;b++){
				if(a>=0 && b>=0 && a<lei.length && b<lei[a].length &&!(a==i && b==j)){
					if(lei[a][b]=='雷'){
						count++;
					}
				}
			}
		}
		return count;
	}
	//排雷
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
			if(e.getButton()==MouseEvent.BUTTON1)//鼠标点击左键
			{
				
				for(int i=0;i<buts.length;i++)
				{
					for(int j=0;j<buts[i].length;j++)
					{
						if(e.getSource()==buts[i][j])
						{
							if(lei[i][j]=='雷')
							{
								for(int a=0;a<lei.length;a++)
								{
									for(int b=0;b<lei[a].length;b++)
									{
										if(lei[a][b]=='雷')
										{
											//buts[a][b].setIcon(defaultIcon);
											buts[a][b].setText("雷");
											buts[a][b].setEnabled(false);
										}
									}
								}
								JOptionPane.showConfirmDialog(frame, "您被炸身亡了，哀吊....");
							}
							else{
								//没有地雷
								if(counts[i][j]==0)
								{
									//周围没有地雷
									buts[i][j].setEnabled(false);
									//依次展开周围8个方向
									unfold(i,j);
								}
								else
								{
									//周围有地雷
									buts[i][j].setText(counts[i][j]+"");
									buts[i][j].setEnabled(false);
								}
								if(isWin())
								{
									JOptionPane.showConfirmDialog(frame, "您成功过关了！");
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
			// TODO 自动生成的方法存根
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
	
	//判断输赢
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
	//计时器
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
