package five;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessFrame {
	JFrame frame;
	boolean isBlack = false;
	JButton start,back,exit;
	JLabel tishi;
	ChessPanel panel;
	Image img;  
	Image shadows;  
	Color colortemp;  

	public ChessFrame() {
			frame = new JFrame("五子棋v1.0");
			frame.setSize(ChessPanel.leftSpace + ChessPanel.space
					* (ChessPanel.col - 1) + ChessPanel.leftSpace + 10,
					ChessPanel.upSpace + (ChessPanel.space * ChessPanel.row - 1)
							+ ChessPanel.upSpace+40);
			panel = new ChessPanel();
			JPanel mainPanel = new JPanel();
		   //设置mainPanel的布局
		    mainPanel.setLayout(new BorderLayout());
		    //把主面板添加在frame上
		    frame.add(mainPanel);
		    //把棋盘面板panel添加在主面板的中间
		    mainPanel.add(panel,BorderLayout.CENTER);
		  //三个按钮
		    start =new JButton("重新开始");
		    back = new JButton("悔棋");
		    exit = new JButton("退出");
	        //包装三个按钮的面板
		    JPanel butPanel = new JPanel();
	        butPanel.add(start);
	        butPanel.add(back);
	        butPanel.add(exit);
	        
	        
	        mainPanel.add(butPanel,BorderLayout.SOUTH);
	        tishi = new JLabel("请黑方落子");
	        mainPanel.add(tishi,BorderLayout.NORTH);
	        frame.setVisible(true);
	        frame.setResizable(false);

			panel.setBackground(new Color(235, 123, 75));
			//shadows=Toolkit.getDefaultToolkit().getImage("bg.jpg"); 
	
			frame.setVisible(true);
			panel.init();
			// 创建事件
			MyClick click = new MyClick();
			// 给panel添加鼠标事件
			panel.addMouseListener(click);
			
			//按钮事件
			start.addActionListener(click);
			back.addActionListener(click);
			exit.addActionListener(click);
		
		

	}

	class MyClick extends MouseAdapter implements ActionListener{
		public void mouseClicked(MouseEvent e) {
			// TODO 自动生成的方法存根
			super.mouseClicked(e);
			int x = e.getX();
			int y = e.getY();
			int i = Math.round((float) (y - ChessPanel.upSpace)
					/ ChessPanel.space);
			int j = Math.round((float) (x - ChessPanel.leftSpace)
					/ ChessPanel.space);
		
			if (i<ChessPanel.row && j<ChessPanel.col) {
				if (ChessPanel.board[i][j] == '*') {
					
					ChessPanel.board[i][j] = isBlack ? 'o' : '@';
					panel.a = i;
					panel.b = j;
					panel.repaint();
					panel.list.add(i+":"+j+":"+(isBlack?"0":"@"));
					if (panel.isWin(i, j)) {
						JOptionPane.showMessageDialog(frame, (isBlack ? "黑方胜利！": "白方胜利"));
					}

					isBlack = !isBlack;
					tishi.setText("请"+(isBlack?"黑":"白")+"方落子");
					
				} else {
					JOptionPane.showMessageDialog(panel, "请勿在同一位置再次下子");
				}
			}

		}

		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if(e.getSource()==start)
			{
				panel.init();
				panel.list.clear();
				isBlack=false;
				panel.repaint();
			}else if(e.getSource()==back)
			{
				char c=panel.backChess();
				if(c=='o')
				{
					isBlack=true;
					tishi.setText("请黑方落子");
				}
				else if(c=='@')
				{
					isBlack=false;
					tishi.setText("请白方落子");
				}
				panel.repaint();
				
			}else if(e.getSource()==exit)
			{
				System.exit(0);
			}
		}
	}
	

	public static void main(String[] args) {
		ChessFrame f = new ChessFrame();
	}

}
