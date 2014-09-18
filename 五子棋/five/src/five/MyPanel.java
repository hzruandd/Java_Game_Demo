package five;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

import javax.swing.*;
//240238119

public class MyPanel extends JPanel{
	public void main(String[] args)
	{
		JFrame f=new JFrame();
		f.setSize(377, 377);
		MyPanel panel=new MyPanel();
		//panel.setBackground(green);
		f.add(panel);
		f.setVisible(true);
		//panel.move();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillOval(0,0,50,50);
		g.drawLine(0, 0, 500, 500);//»®Ïß
		g.setColor(Color.red);
	}
	public void move(int x,int y)
	{
		while(true)
		{
			x++;
			y++;
			
			for(int i=0;i<1000;i++)
			{
				
			}
			repaint();
		}
	}
}
