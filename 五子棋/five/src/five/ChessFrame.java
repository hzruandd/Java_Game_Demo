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
			frame = new JFrame("������v1.0");
			frame.setSize(ChessPanel.leftSpace + ChessPanel.space
					* (ChessPanel.col - 1) + ChessPanel.leftSpace + 10,
					ChessPanel.upSpace + (ChessPanel.space * ChessPanel.row - 1)
							+ ChessPanel.upSpace+40);
			panel = new ChessPanel();
			JPanel mainPanel = new JPanel();
		   //����mainPanel�Ĳ���
		    mainPanel.setLayout(new BorderLayout());
		    //������������frame��
		    frame.add(mainPanel);
		    //���������panel������������м�
		    mainPanel.add(panel,BorderLayout.CENTER);
		  //������ť
		    start =new JButton("���¿�ʼ");
		    back = new JButton("����");
		    exit = new JButton("�˳�");
	        //��װ������ť�����
		    JPanel butPanel = new JPanel();
	        butPanel.add(start);
	        butPanel.add(back);
	        butPanel.add(exit);
	        
	        
	        mainPanel.add(butPanel,BorderLayout.SOUTH);
	        tishi = new JLabel("��ڷ�����");
	        mainPanel.add(tishi,BorderLayout.NORTH);
	        frame.setVisible(true);
	        frame.setResizable(false);

			panel.setBackground(new Color(235, 123, 75));
			//shadows=Toolkit.getDefaultToolkit().getImage("bg.jpg"); 
	
			frame.setVisible(true);
			panel.init();
			// �����¼�
			MyClick click = new MyClick();
			// ��panel�������¼�
			panel.addMouseListener(click);
			
			//��ť�¼�
			start.addActionListener(click);
			back.addActionListener(click);
			exit.addActionListener(click);
		
		

	}

	class MyClick extends MouseAdapter implements ActionListener{
		public void mouseClicked(MouseEvent e) {
			// TODO �Զ����ɵķ������
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
						JOptionPane.showMessageDialog(frame, (isBlack ? "�ڷ�ʤ����": "�׷�ʤ��"));
					}

					isBlack = !isBlack;
					tishi.setText("��"+(isBlack?"��":"��")+"������");
					
				} else {
					JOptionPane.showMessageDialog(panel, "������ͬһλ���ٴ�����");
				}
			}

		}

		public void actionPerformed(ActionEvent e) {
			// TODO �Զ����ɵķ������
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
					tishi.setText("��ڷ�����");
				}
				else if(c=='@')
				{
					isBlack=false;
					tishi.setText("��׷�����");
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
