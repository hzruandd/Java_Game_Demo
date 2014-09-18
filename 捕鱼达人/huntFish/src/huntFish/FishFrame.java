package huntFish;

import javax.swing.JFrame;

public class FishFrame {
	JFrame frame;
	FishPanel panel;
	public FishFrame()
	{
		frame=new JFrame("≤∂”„¥Ô»Àv1.0");
		frame.setSize(815, 520);
		frame.setLocation(200,100);
		panel =new FishPanel();
		frame.add(panel);
		
		
		
		frame.setVisible(true);
		AddFishThread t = new AddFishThread(panel);
		t.start();
	}
	
	public static void main(String[] args) {
		FishFrame f=new FishFrame();
	}
}
