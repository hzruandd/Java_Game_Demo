package huntFish;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FishPanel extends JPanel {
	/**
	 * 
	 */
	Vector<Fish> fishs=new Vector<Fish>();
		@Override
		public void paint(Graphics g) {
			// TODO �Զ����ɵķ������
			super.paint(g);
			//������
			g.drawImage(new ImageIcon("image/bg_03.png").getImage(), 0,0,this);
			
			for(int i=0;i<fishs.size();i++){
				Fish f=fishs.get(i);
				f.drawFish(g);
			}
			g.drawImage(new ImageIcon("image/bg1.jpg").getImage(),10,415,this);
		}
}
