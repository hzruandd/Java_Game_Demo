package huntFish;


//���ζ����߳���
public class FishMoveThread extends Thread{
	
	Fish fish;
	
	public FishMoveThread(Fish fish) {
		// TODO �Զ����ɵĹ��캯�����
		this.fish=fish;
	}
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
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
