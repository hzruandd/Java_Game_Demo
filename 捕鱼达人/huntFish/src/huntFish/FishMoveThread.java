package huntFish;


//鱼游动的线程类
public class FishMoveThread extends Thread{
	
	Fish fish;
	
	public FishMoveThread(Fish fish) {
		// TODO 自动生成的构造函数存根
		this.fish=fish;
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true)
		{
			fish.move();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
}
