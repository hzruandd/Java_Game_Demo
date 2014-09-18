package saolei;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JMine {
	static char[][] board = new char[10][10];
	static int[][] counts = new int[10][10];
	// ��ʼ����ά����
	public static void init() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '*';
			}
		}
	}

	// ��ӡ����
	public static void print() {
		char[] c = "0123456789".toCharArray();
		System.out.print("  ");
		for (int i = 0; i <c.length; i++) {
			System.out.print(c[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			System.out.print(c[i] + " ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			// ����
			System.out.println();
		}

	}
	// ��ӡ����
	public static void print1() {
		char[] c = "0123456789".toCharArray();
		System.out.print("  ");
		for (int i = 0; i <c.length; i++) {
			System.out.print(c[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			System.out.print(c[i] + " ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(counts[i][j] + " ");
			}
			// ����
			System.out.println();
		}

	}
	//�������
	public static void start(){
		for(int a=0;a<10;a++){
			int i = (int)(Math.random()*10);
			int j = (int)(Math.random()*10);
		    if(board[i][j]=='��'){
		    	a--;
		    }else{
		    	board[i][j] = '��';
		    }
		}
		//ͳ��ÿ��λ�õĵ��׸���
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[i].length;j++){
				counts[i][j]=count(i,j);
			}
		}
	}
	//ͳ��ĳ��λ�õĵ��׸���
	public static int count(int i,int j){
		int count = 0;
		for(int a=i-1;a<=i+1;a++){
			for(int b=j-1;b<=j+1;b++){
				if(a>=0 && b>=0 && a<board.length && b<board[a].length &&!(a==i && b==j)){
					if(board[a][b]=='��'){
						count++;
					}
				}
			}
		}
		return count;
	}
	//����
  public static void main(String[] args) {
	init();
	start();
	print();
	  print1();
}
}





/*public class JMine {
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		JPanel panel=new JPanel();
		GridLayout main=new GridLayout(10, 10);
		for(int i=0;i<100;i++)
		{
			JButton button=new JButton("��ť"+(i+1));
			panel.add(button);
		}
		frame.add(panel);
		frame.setSize(400, 500);
		frame.setVisible(true);
	}
	

}*/
