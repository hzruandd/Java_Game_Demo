package five;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ChessPanel extends JPanel {
	public static final int upSpace = 20;
	public static final int leftSpace = 20;
	public static final int row = 16;
	public static final int col = 16;
	public static final int space = 40;
	public static final int r = 15;
	static char[][] board = new char[row][col];
	ArrayList<String> list = new ArrayList();
	int a = -1;
    int b = -1;

	public void paint(Graphics g)

	{
		super.paint(g);
		for (int i = 0; i < row; i++) {
			g.drawLine(leftSpace, upSpace + i * space, leftSpace + space
					* (col - 1), upSpace + i * space);
		}
		for (int i = 0; i < col; i++) {
			g.drawLine(leftSpace + i * space, upSpace, leftSpace + i * space,
					upSpace + space * (row - 1));
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// 判断是否画圆
				if (board[i][j] == 'o') {
					g.setColor(Color.black);
					g.fillOval((leftSpace - r) + j * space, (upSpace - r) + i
							* space, 2 * r, 2 * r);
					if(isWin(i,j))
					{
						g.setColor(Color.green);
						g.fillOval((leftSpace-r/2) + j * space, (upSpace-r/2) + i
								* space,r,r);
						//repaint();
					}
					
				} else if (board[i][j] == '@') {
					g.setColor(Color.white);
					g.fillOval((leftSpace - r) + j * space, (upSpace - r) + i
							* space, 2 * r, 2 * r);
					if(isWin(i,j))
					{
						g.setColor(Color.green);
						g.fillOval((leftSpace-r/2) + j * space, (upSpace-r/2) + i
								* space,r,r);
						//repaint();
					}
				}

			}
		}
		g.setColor(Color.red);
		g.fillOval(leftSpace-r/2+b*space, upSpace-r/2+a*space, r, r);
		
	}
	//判断是会否是最后一步
	
	// 悔棋的步骤
	public char backChess() {
		if ((list.size() - 1) >= 0) {
			String str = list.get(list.size() - 1);
			String[] strs = str.split(":");
			int i = Integer.parseInt(strs[0]);
			int j = Integer.parseInt(strs[1]);
			char c= (strs[2]).charAt(0);
			board[i][j] = '*';
			list.remove(list.size() - 1);
			repaint();
			return c;
		}else
			return 'o';
	}

	public void init() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '*';
			}
		}
	}

	// 集合保存棋子的先后顺序

	// 讨论输赢
	// 左右方向
	public boolean isWin(int i, int j) {
		return win1(i, j) || win2(i, j) || win3(i, j) || win4(i, j);
	}

	// 左右
	public boolean win1(int i, int j) {

		int j1 = j - 1;
		int count = 0;
		while (true) {
			if (j1 >= 0 && board[i][j1] == board[i][j]) {
				count++;
				j1--;
			} else {
				break;
			}
		}
		j1 = j + 1;
		while (true) {
			if (j1 < board[0].length && board[i][j1] == board[i][j]) {
				count++;
				j1++;
			} else {
				break;
			}
		}
		count++;
		return count >= 5;
	}

	// 上下
	public boolean win2(int i, int j) {
		int i1 = i - 1;
		int count = 0;
		while (true) {
			if (i1 >= 0 && board[i1][j] == board[i][j]) {
				count++;
				i1--;
			} else {
				break;
			}
		}
		i1 = i + 1;
		while (true) {
			if (i1 < board[0].length && board[i1][j] == board[i][j]) {
				count++;
				i1++;
			} else {
				break;
			}
		}
		count++;
		return count >= 5;
	}

	// 左下右上
	public boolean win3(int i, int j) {
		// 先向左下 i++ j--
		int i1 = i + 1;
		int j1 = j - 1;
		int count = 0;
		while (true) {
			if (i1 < board.length && j1 >= 0 && board[i1][j1] == board[i][j]) {
				count++;
				i1++;
				j1--;
			} else {
				break;
			}
		}
		i1 = i - 1;
		j1 = j + 1;
		while (true) {
			if (i1 >= 0 && j1 < board[0].length && board[i1][j1] == board[i][j]) {
				i1--;
				j1++;
				count++;
			} else {
				break;
			}
		}
		count++;
		return count >= 5;
	}

	// 左上右下
	public boolean win4(int i, int j) {
		// 先向左下 i++ j--
		int i1 = i - 1;
		int j1 = j - 1;
		int count = 0;
		while (true) {
			if (i1 >= 0 && j1 >= 0 && board[i1][j1] == board[i][j]) {
				count++;
				i1--;
				j1--;
			} else {
				break;
			}
		}
		i1 = i + 1;
		j1 = j + 1;
		while (true) {
			if (i1 < board[0].length && j1 < board[0].length
					&& board[i1][j1] == board[i][j]) {
				i1++;
				j1++;
				count++;
			} else {
				break;
			}
		}
		count++;
		return count >= 5;
	}

}
