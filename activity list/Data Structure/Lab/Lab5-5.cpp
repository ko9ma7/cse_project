#include <cstdio>
#include <deque>
#include <stack>
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
// deque와 stack은 전역변수로 설정해주어야 한다.
// 함수의 매개변수로 넣는 경우 call-by-value로 인해 값이 들어가지 않기 때문이다.
deque<Location2D> standardDeq;
stack<Location2D> compareStack; // 최단 거리 스택 설정

bool isValidLoc(int r, int c) {
	if (r < 0 || c < 0 || r >= MAZE_SIZE || c >= MAZE_SIZE)
		return false;
	else
		return map[r][c] == '0' || map[r][c] == 'x';
}

bool isNeighborLoc(Location2D deque, Location2D stack) {
	int dRow = deque.getRow();
	int dCol = deque.getCol();
	int sRow = stack.getRow();
	int sCol = stack.getCol();
	// 인접해있는 경우
	if (((dRow == sRow) && ((dCol == sCol - 1) || (dCol == sCol + 1))) || ((dCol == sCol) && ((dRow == sRow + 1) || (dRow == sRow - 1)))) return true;
	// 동일한 위치인 경우도 인접한 경우이지만 위의 조건과 합치면 여러 오류가 생기므로 따로 빼주었다
	else if ((dRow == sRow) && (sCol == dCol)) return true;
	// 인접하지 않은 경우
	else return false;
}
// 인접하지 않았을 경우 계속 pop을 해주는 함수
void popRecursive() {
	if (!standardDeq.empty()) {
		Location2D dLoc = standardDeq.back(); // 덱은 back()이 스택의 top()과 같다
		Location2D sLoc = compareStack.top();
		// compareStack이 비어있는 경우도 있다(아래에서 설명)
		while (!isNeighborLoc(dLoc, sLoc) && !compareStack.empty()) {
			compareStack.pop(); // 인접하지 않은 경우이므로 계속 pop을 해준다
			sLoc = compareStack.top();
		}
	}
}

void main() {
	printf("최단거리 구현 미로탐색\n");

	Location2D entry(1, 0);
	standardDeq.push_back(entry);
	// 처음에 compareStack에는 (0, 0)이라는 좌표를 초기화 시킨다
	// (0, 0)이라는 좌표를 넣어준 이유는 만약 아무런 초기화를 하지 않는다면
	// while문 안에 들어가서는 compareStack의 값이 아무것도 없으므로 top을 해줄 수 없다.
	// 또 다른 이유는 만약 standardDeq과 같은 (1, 0)이라는 위치를 넣어주면
	// while문 안에 들어가서 두 좌표가 같기 때문에 또 다시 (1, 0)이 들어가게 된다.
	// 그래서 차라리 (0, 0)을 넣어 주었다.
	compareStack.push(Location2D(0, 0));

	while (standardDeq.empty() == false) {
		Location2D checkLoc = standardDeq.back();
		Location2D checkStack = compareStack.top();

		if (isNeighborLoc(checkLoc, checkStack)) compareStack.push(checkLoc); // 인접하면 push를 해준다
		else {
			// 인접하지 않을 경우이다. 위의 미로를 예를 들어서, 현재 standardDeq에는 (2, 2)가 있고, compareStack에는 (4, 1)이 있다.
			// 이 두 좌표는 인접하지 않으므로 이 조건식에 들어가게 된다.
			// popRecursive() 함수를 호출하게 되면 (2, 2)가 인접하는 좌표가 나올 때까지 compareStack에 있는 좌표를 pop해준다.
			popRecursive();
			// 인접한 좌표가 나오게 되면 while문에서 빠져나와 popRecursive() 함수 호출이 종료된다.
			// 종료되고 나서 바로 (2, 2)의 값을 compareStack에 넣어준다.
			compareStack.push(checkLoc);
		}
		// 인접한 경우와 인접하지 않은 경우를 모두 검사하고 나서야
		// standardDeq에 있는 원소를 pop해준다.
		standardDeq.pop_back();

		int r = checkLoc.getRow();
		int c = checkLoc.getCol();

		if (map[r][c] == 'x') {
			printf("미로 탐색 성공\n");
			printf("최단 경로 인출\n");
			// 미로 탐색이 성공되고 최적의 경로를 출력해주기 위해서 스택의 값을 출력해준다.
			// 처음 스택의 top이 0인 경우는 (0, 0)으로 설정해주었기 때문에 스택의 원소가 (0, 0)인 경우는 출력하지 않는다.p
			while(!compareStack.empty()) {
				Location2D ele=compareStack.top();
				compareStack.pop();
				if (ele.getRow() == 0 && ele.getCol() == 0) break;
				else printf("(%d,%d)\n",ele.getRow(),ele.getCol());
			}

			return;
		}
		else {
			map[r][c] = '.';
			if (isValidLoc(r, c + 1)) standardDeq.push_back(Location2D(r, c + 1)); // 우
			if (isValidLoc(r, c - 1)) standardDeq.push_back(Location2D(r, c - 1)); // 좌
			if (isValidLoc(r - 1, c)) standardDeq.push_back(Location2D(r - 1, c)); // 상
			if (isValidLoc(r + 1, c)) standardDeq.push_back(Location2D(r + 1, c)); // 하
		}
	}
	printf("미로 탐색 실패\n");
}