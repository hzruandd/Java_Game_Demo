package huntFish;

import java.awt.Graphics;

import javax.swing.ImageIcon;


//�������ʵ����
public class Fish {
	int x;//������
	int y;//������
	ImageIcon[] icons;
	int step;//�ƶ��ٶ�
	FishPanel panel;//�����ڵ����
	int num; //���Ӧ��ͼƬ�±�
	int road; //���ζ���· 1->��������        2->����          3->����
	int kind;//�������
	boolean  isMove;
	public void drawFish( Graphics g)
	{
	g.drawImage(icons[num%10].getImage(),x,y,panel);
	}
	
	//���ζ��ķ���
	public void move()
	{
		switch(road){
		case 1:
			x+=step;
			if(x>panel.getWidth())
			{
				panel.fishs.remove(this);
				isMove=false;
			}
			break;
		case 2:
			x-=step;
			if(x<-303)
			{
				panel.fishs.remove(this);
				isMove=false;
			}
			break;
		case 3:
			y+=step;
			if(y>panel.getHeight())
			{
				panel.fishs.remove(this);
				isMove=false;
			}
			break;
		}
		num++;
		panel.repaint();
	}

}
