package huntFish;

import java.awt.Graphics;

import javax.swing.ImageIcon;


//创建鱼的实体类
public class Fish {
	int x;//横坐标
	int y;//纵坐标
	ImageIcon[] icons;
	int step;//移动速度
	FishPanel panel;//鱼所在的面板
	int num; //鱼对应的图片下标
	int road; //鱼游动线路 1->代表向右        2->向左          3->向下
	int kind;//鱼的种类
	boolean  isMove;
	public void drawFish( Graphics g)
	{
	g.drawImage(icons[num%10].getImage(),x,y,panel);
	}
	
	//鱼游动的方法
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
