class Location2D {
	int row;
	int col;
public:
	Location2D(int r, int c) {
		row = r;
		col = c;
	}

	int getRow() {
		return row;
	}

	int getCol() {
		return col;
	}

	bool isSameLoction(Location2D p) {
		return row == p.row && col == p.col;
	}
};