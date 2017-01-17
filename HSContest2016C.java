import java.util.*;

public class HSContest2016C {
	static int[][] board;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		board = new int[8][8];
		for (int r = 7; r >= 0; r--)
		{
			char[] line = sc.next().toCharArray();
			for (int c = 0; c < 8; c++)
			{
				switch(line[c])
				{
				case '.':
				case 'T':
					board[r][c] = 0;
					break;
				case 'C':
					board[r][c] = 1;
					break;
				case 'I':
					board[r][c] = 2;
					break;
				case 'D':
					board[r][c] = 3;
					break;				
				}
			}			
		}
		State[][] states = new State[8][8];
		for (int r = 0; r < 8; r++)
		{
			for (int c = 0; c < 8; c++)
			{
				states[r][c] = new State(Integer.MAX_VALUE, "", r, c, 0, 1);
			}
		}
		PriorityQueue<State> pq = new PriorityQueue<State>();
		pq.add(new State(0, "", 0, 0, 0, 1));
		while (!pq.isEmpty())
		{
			State s = pq.poll();
			//System.out.println(s.x + " " +s.y);
			if (board[s.x][s.y] == 3)	//found a diamond
			{
				System.out.print(s.str);
				System.exit(0);
			}
			if (states[s.x][s.y].dist == Integer.MAX_VALUE)  //unvisited
			{
				states[s.x][s.y].dist = s.dist;
				states[s.x][s.y].str = s.str;
				for (int[] dir : dirs)
				{
					int x = dir[0] + s.x;
					int y = dir[1] + s.y;
					if (inBounds(x, y) && states[x][y].dist == Integer.MAX_VALUE)
					{
						int dd = 1;
						StringBuilder sb = new StringBuilder();
						int dx = s.dx;
						int dy = s.dy;
						if (dir[0] != s.dx && dir[1] != s.dy)
						{
							int i;
							for (i = 1; i <= 3; i++) {
								if (dx == 0)
								{
									dx = dy;
									dy = 0;
								}
								else
								{
									dy = -1 *dx;
									dx = 0;
								}
								if (dx == dir[0] && dy == dir[1])
									break;
							}
							if (i == 1)
							{
								sb.append("L");
								dd++;
							}
							else if (i == 2) {
								sb.append("LL");
								dd += 2;
							}
							else	{
								sb.append("R");
								dd++;
							}
						}
						if (board[x][y] == 2)
						{
							dd++;
							sb.append("X");
						}

						sb.append("F");
						pq.add(new State(s.dist + dd, s.str + sb.toString(), x, y, dir[0], dir[1] ));
					}
				}
			}

			states[s.x][s.y].dist = s.dist;

		}
		System.out.print("no solution");
	}

	static int[][] dirs = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
	static boolean inBounds(int r, int c)
	{
		return r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c] != 1;
	}
	static class State implements Comparable<State>
	{
		int dist;
		String str;
		int x, y;
		int dx, dy;
		public State (int d, String s, int x, int y, int dx, int dy)
		{
			dist = d; str = s;
			this.x = x; this.y = y;
			this.dx = dx; this.dy = dy;
		}
		public int compareTo(State other)
		{
			return Integer.compare(str.length(), other.str.length());
		}
	}
}