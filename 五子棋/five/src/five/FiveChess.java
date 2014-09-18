 package five;

import java.util.Scanner;

//������v1.0
public class FiveChess {
	// 924674202@qq.com
	static boolean isBlack = true;// �ڷ�����
	static char[][] board = new char[16][16];

	// ��ʼ������
	public static void init() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '*';
			}
		}
	}

	// ������Ӯ
	// ���ҷ���
	public static boolean win1(int i, int j) {
		int xright = 0;
		while(true){
			if (i<board[0].length&&board[i][j] == board[i + 1][j])
			{
				xright++;
				i++;
			}
			else
				break;
		}
		int xleft = 0;
		while(true){
			if (i>0 && board[i][j] == board[i - 1][j])
			{
				xleft++;
				i--;
			}
			else
				break;
		}
		if ((xright + xleft == 5)) {
			return true;
		}
		else
			return false;
	}

	// ���·���
	public static boolean win2(int i, int j) {
		int yup = 0;
		while(true) {
			if (j<board[0].length&&board[i][j] == board[i][j+1])
			{
				yup++;
				j++;
			}
			else
				break;
		}
		int ydown = 0;
		while(true){
			if (j>0&&board[i][j] == board[i][j-1])
			{
				ydown++;
				j--;
			}
			else
				break;
		}
		if ((yup+ydown+1 ==  5)) {
			return true;
		}
		else
			return false;
	}

	// ��������
	public static boolean win3(int i, int j) {
		int rightup = 0;
		while(true) {
			if (i<board[0].length&&j<board[0].length&&board[i][j] == board[i+1][j+1])
			{
				rightup++;
				j++;
				i++;
			}
			else
				break;
		}
		int leftdown = 0;
		while(true){
			if (i>0&&j>0&&board[i][j] == board[i-1][j-1])
			{
				leftdown++;
				j--;
				i--;
			}
			else
				break;
		}
		if ((rightup+leftdown+1 ==  5)) {
			return true;
		}
		else
			return false;
	}

	// ��������
	public static boolean win4(int i, int j) {
		int rightdown = 0;
		while(true) {
			if (i<board[0].length&&j>0&&board[i][j] == board[i+1][j-1])
			{
				rightdown++;
				j--;
				i++;
			}
			else
				break;
		}
		int leftup = 0;
		while(true){
			if (i>0&&j<board[0].length&&board[i][j] == board[i-1][j+1])
			{
				leftup++;
				j++;
				i--;
			}
			else
				break;
		}
		if ((rightdown+leftup+1==  5)) {
			return true;
		}
		else
			return false;
	}// �ܷ���

	public static boolean isWin(int i, int j) {

		return win1(i, j) || win2(i, j) || win3(i, j) || win4(i, j);
	}

	//

	// ��ӡ����
	public static void print() {
		// char[] c =
		// {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		char[] c = "0123456789abcdef".toCharArray();
		System.out.print("  ");
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i] + " ");
		}

		// ����
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

	// ����
	public static void game() {
		Scanner sca = new Scanner(System.in);
		while (true) {
			System.out.println("��" + (isBlack ? "��" : "��") + "������");
			String str = sca.next();
			if (str.length() >= 2) {
				int i = foramt(str.charAt(0));
				int j = foramt(str.charAt(1));
				if (i != -1 && j != -1) {
					if (board[i][j] == '*') {
						board[i][j] = isBlack ? 'O' : '@';
						print();
						if(isWin(i,j))
						{
							System.out.println(isBlack ?"�ڷ�ʤ����":"�׷�ʤ����");
							break;
						}
						isBlack = !isBlack;
					} else
						System.out.println("������ͬһλ���ظ�����");
					;

				} else {
					System.out.println("�������겻�Ϸ�,����������");
				}
			} else {
				System.out.println("�������겻�Ϸ�,����������!");
			}
		}
	}

	// ���괦��
	public static int foramt(char c) {
		int a = -1;
		if (c >= '0' && c <= '9') {
			a = c - '0';
		} else if (c >= 'a' && c <= 'f') {
			a = c - 'a' + 10;
		}
		return a;
	}

	public static void main(String[] args) {
		init();
		print();
		game();
	}

}
