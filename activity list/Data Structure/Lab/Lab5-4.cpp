#include <cstdio>
#include <deque>
#include "Lab5-3-Location2D.h"
using namespace std;

#define MAZE_SIZE 6
char map[MAZE_SIZE][MAZE_SIZE] = {
	{ '1', '1', '1', '1', '1', '1' },
	{ 'e', '0', '1', '0', '0', '1' },
	{ '1', '0', '0', '0', '1', '1' },
	{ '1', '0', '1', '0', '1', '1' },
	{ '1', '0', '1', '0', '0', 'x' },
	{ '1', '1', '1', '1', '1', '1' }
};

bool isValidLoc(int r, int c) {
	if (r < 0 || c < 0 || r >= MAZE_SIZE || c >= MAZE_SIZE)
		return false;
	else
		return map[r][c] == '0' || map[r][c] == 'x';
}

void main() {
	printf("BFS 구현 미로탐색\n");
	deque<Location2D> standardDeq;

	Location2D entry(1, 0);
	standardDeq.push_back(entry);

	while (standardDeq.empty() == false) {
		Location2D checkLoc = standardDeq.front();
		standardDeq.pop_front();

		int r = checkLoc.getRow();
		int c = checkLoc.getCol();
		printf("(%d, %d)\n", r, c);

		if (map[r][c] == 'x') {
			printf("미로 탐색 성공\n");
			return;
		} else {
			map[r][c] = '.';

			if (isValidLoc(r - 1, c)) standardDeq.push_back(Location2D(r - 1, c));
			if (isValidLoc(r + 1, c)) standardDeq.push_back(Location2D(r + 1, c));
			if (isValidLoc(r, c - 1)) standardDeq.push_back(Location2D(r, c - 1));
			if (isValidLoc(r, c + 1)) standardDeq.push_back(Location2D(r, c + 1));
		}
	}
	printf("미로 탐색 실패\n");
}
