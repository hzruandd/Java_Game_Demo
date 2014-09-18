package huntFish;

import javax.swing.ImageIcon;

//添加鱼的线程类
public class AddFishThread extends Thread {

	FishPanel panel;

	public AddFishThread(FishPanel panel) {
		this.panel = panel;
	}

	public void run() {
		//int fishcount=0;
		while (true) {
			Fish f = new Fish();
			// 生成鱼的路线
			int road = (int) (Math.random() * 3 + 1);
			f.road = road;
			String path = "image/";
			switch (road) {
			case 1:
				f.x = -303;
				f.y = (int) (Math.random() * 520);
				path = path + "left_to_right/";
				break;
			case 2:
				f.x = 815;
				f.y = (int) (Math.random() * 500);
				path = path + "right_to_left/";
				break;
			case 3:
				f.y = -303;
				f.x = (int) (Math.random() * 700);
				;
				path = path + "up_to_buttom/";
				break;
			}
			
			// 生成鱼的种类
			int kind = (int) (Math.random() * 10 + 1);
			path = path + "fish" + (kind < 10 ? "0" + kind : kind)+"_";
			ImageIcon[] icons = new ImageIcon[10];
			for (int i = 1; i <= icons.length; i++) {
				ImageIcon icon = new ImageIcon(path + (i < 10 ? "0" + i : i)
						+ ".png");
				icons[i - 1] = icon;
			}
			f.icons = icons;
			switch (kind) {
			case 1:
				f.step = 3;
				break;
			case 2:
				f.step = 2;
				break;
			case 3:
				f.step = 3;
				break;
			case 4:
				f.step = 4;
				break;
			case 5:
				f.step = 5;
				break;
			case 6:
				f.step = 4;
				break;
			case 7:
				f.step = 3;
				break;
			case 8:
				f.step = 2;
				break;
			case 9:
				f.step = 3;
				break;
			case 10:
				f.step = 4;
				break;

			}
			

			f.panel = panel;
			panel.fishs.add(f);
			FishMoveThread t = new FishMoveThread(f);
			t.start();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			//fishcount++;
		}
	}
}
