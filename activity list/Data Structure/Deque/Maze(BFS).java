import java.util.LinkedList;
import java.util.Deque;

class Location2D {
	private int row;
	private int col;

	public Location2D(int r, int c) {
		row = r;
		col = c;
	}

	public Location2D(Location2D copy) {
		this.row = copy.row;
		this.col = copy.col;
	}

	public int getRow() { return row; }
	public int getCol() { return col; }

	public boolean isSameLoction(Location2D p) { return row == p.row && col == p.col; }
}

public class Main {
	final static int MAZE_SIZE = 6;
	public static char[][] map = { { '1', '1', '1', '1', '1', '1' },
			               { 'e', '0', '1', '0', '0', '1' },
			               { '1', '0', '0', '0', '1', '1' },
			               { '1', '0', '1', '0', '1', '1' },
			               { '1', '0', '1', '0', '0', 'x' },
			               { '1', '1', '1', '1', '1', '1' } };

	public static boolean isValidLoc(int r, int c) {
		if (r < 0 || c < 0 || r >= MAZE_SIZE || c >= MAZE_SIZE)
			return false;
		else
			return map[r][c] == '0' || map[r][c] == 'x';
	}

	public static void main(String[] args) {
		System.out.println("Deque(BFS) - using Queue\n")
		Deque<Location2D> locDeque = new LinkedList<>(); // Instance in safe because of LinkedList
		Location2D entry = new Location2D(1, 0);
		locDeque.push(entry); // no matter what add, push, offer, put

		while (locDeque.isEmpty() == false) {
			Location2D here = new Location2D(locDeque.peek()); // copy value of first array element
			locDeque.poll(); // inorder to role in queue, insert it from the back and delete it from the front

			int r = here.getRow();
			int c = here.getCol();
			System.out.printf("(%d, %d) ", r, c);

			if (map[r][c] == 'x') {
				System.out.println("Escape maze success!");
				return;
			} else {
				map[r][c] = '.';

				// inorder to role in queue, using command add
				if (isValidLoc(r - 1, c)) locDeque.add(new Location2D(r - 1, c));
				if (isValidLoc(r + 1, c)) locDeque.add(new Location2D(r + 1, c));
				if (isValidLoc(r, c - 1)) locDeque.add(new Location2D(r, c - 1));
				if (isValidLoc(r, c + 1)) locDeque.add(new Location2D(r, c + 1));
			}
		}

		System.out.println("Escape maze fail!");
	}
}
