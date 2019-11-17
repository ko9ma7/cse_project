import java.util.LinkedList;
import java.util.Queue;

class Location2D {
	private int row;
	private int col;

	// create constructor
	public Location2D(int r, int c) {
		row = r;
		col = c;
	}
	// create copy constructor
	public Location2D(Location2D copy) {
		this.row = copy.row;
		this.col = copy.col;
	}

	public int getRow() { return row; }
	public int getCol() { return col; }
}

public class Main {
	final static int MAZE_SIZE = 6;
	public static char[][] map = { { '1', '1', '1', '1', '1', '1' },
			                       { 'e', '0', '1', '0', '0', '1' },
			                       { '1', '0', '0', '0', '1', '1' },
			                       { '1', '0', '1', '0', '1', '1' },
			                       { '1', '0', '1', '0', '0', 'x' },
			                       { '1', '1', '1', '1', '1', '1' }
	};

	public static boolean isValidLoc(int r, int c) {
		if (r < 0 || c < 0 || r >= MAZE_SIZE || c >= MAZE_SIZE)
			return false;
		else
			return map[r][c] == '0' || map[r][c] == 'x';
	}

	public static void main(String[] args) {
		Queue<Location2D> locQueue = new LinkedList<>(); // Queue must use LinkedList to Instance
		Location2D entry = new Location2D(1, 0);
		locQueue.add(entry); // role in push from stack

		while (locQueue.isEmpty() == false) {
			Location2D here = new Location2D(locQueue.peek());
			locQueue.poll(); // role in pop from stack, delete front

			int r = here.getRow();
			int c = here.getCol();
			System.out.printf("(%d, %d) ", r, c);

			if (map[r][c] == 'x') {
				System.out.println("Escape maze success!");
				return;
			} else {
				map[r][c] = '.';

				if (isValidLoc(r - 1, c)) locQueue.add(new Location2D(r - 1, c)); // check up
				if (isValidLoc(r + 1, c)) locQueue.add(new Location2D(r + 1, c)); // check down
				if (isValidLoc(r, c - 1)) locQueue.add(new Location2D(r, c - 1)); // check left
				if (isValidLoc(r, c + 1)) locQueue.add(new Location2D(r, c + 1)); // check right
			}
		}

		System.out.println("Escape maze fail!");
	}
}
