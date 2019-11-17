#include "Lab6-2-LinkedStack.h"
#pragma once

LinkedStack standardStack;
LinkedStack compareStack;

class Maze {
	int width;  // 가로(행) - 10
	int height; // 세로(열) - 20
	int** map;
public:
	Maze() { init(0, 0); }
	~Maze() { reset(); }
	// 동적할당
	void init(int w, int h) {
		width = w;
		height = h;
		// 이차원 배열 동적 할당
		map = new int*[width];
		for (int i = 0; i < width; i++)
			map[i] = new int[height];
	}
	// 메모리 삭제
	void reset() {
		for (int i = 0; i < width; i++)
			delete[] map[i];
		delete[] map;
	}

	void load(char* fname) {
		FILE* fp = fopen(fname, "r");
		if (fp == NULL) {
			printf("파일 오픈 실패!\n");
			return;
		}

		char c;
		int val;
		int Line = 0;
		int w = -1;  // 가로
		int h = 0; // 세로

		while ((c = getc(fp)) != EOF) {
			// 첫 번째 라인에서 맵의 크기를 받아온다
			if (Line == 0) {
				ungetc(c, fp);
				fscanf(fp, "%d %d", &height, &width);
				init(width, height); // 받아온 가로와 세로를 이용해 초기화
				Line++; // 이 조건문을 빠져나가면 쓸모가 없다
			} else {
				// '\n'를 만나게 되기 전까지
				if(c != '\n') {
					ungetc(c,fp); // 열에서 한 번씩 이전 버퍼에 문자를 담아준다
					fscanf(fp, "%d", &val);
					map[w][h] = val;
					h = (h + 1) % height; // 원형 큐에서와 같은 원리로 h가 증가할 때 값이 범위를 넘어가지 않도록 하기 위함
				} else {
					w = (w + 1) % width;
				}
			}
		}

		fclose(fp);
	}

	void print() {
		printf("전체 미로의 크기 = %d x %d\n", height, width);
		for (int w = 0; w < width; w++) {
			 for (int h = 0; h < height; h++) {
				if (map[w][h] == 0)  printf("□");
				else if (map[w][h] == 1) printf("  ");
				else if (map[w][h] == 5) printf("○");
				else if (map[w][h] == 9) printf("◎");
				else if (map[w][h] == 777) printf("☆");
				else printf("■");
			} printf("\n");
		}
	}

	void searchExit();
	bool isValidLoc(int r, int c);
};

bool Maze::isValidLoc(int r, int c) {
	if (r < 0 || c < 0 || r >= width || c >= height) return false;
	else return map[r][c] == 1 || map[r][c] == 9;
}

bool isNeighborLoc(Location2D standardStack, Location2D compareStack) {
	int r1 = standardStack.getRow();
	int c1 = standardStack.getCol();
	int r2 = compareStack.getRow();
	int c2 = compareStack.getCol();

	if ((r1 == r2 && ((c1 == c2 + 1) || (c1 == c2 - 1))) || (c1 == c2 && ((r1 == r2 + 1) || (r1 == r2 - 1)))) return true;
	else if (r1 == r2 && c1 == c2) return true;
	else return false;
}

void popRecursive() {
	if (!standardStack.isEmpty()) {
		Location2D Loc1 = Location2D(standardStack.peek()->getData().getRow(), standardStack.peek()->getData().getCol());
		Location2D Loc2 = Location2D(compareStack.peek()->getData().getRow(), compareStack.peek()->getData().getCol());

		while (!isNeighborLoc(Loc1, Loc2) && !compareStack.isEmpty()) {
			compareStack.pop();
			Loc2 = Location2D(compareStack.peek()->getData().getRow(), compareStack.peek()->getData().getCol());
		}
	}
}

void Maze::searchExit() {
	printf("\n=============미로 탐색 시작=============\n");
	Location2D entry(2, 0);
	Location2D sample(1, 0);
	standardStack.push(&Node(Location2D(2, 0)));
	compareStack.push(&Node(sample));

	while (!standardStack.isEmpty()) {
		Location2D checkLoc = Location2D(standardStack.peek()->getData().getRow(), standardStack.peek()->getData().getCol());
		Location2D checkStack = Location2D(compareStack.peek()->getData().getRow(), compareStack.peek()->getData().getCol());

		if (isNeighborLoc(checkLoc, checkStack)) compareStack.push(new Node(checkLoc));
		else {
			popRecursive();
			compareStack.push(new Node(checkLoc));
		}

		standardStack.pop();

		int r = checkLoc.getRow();
		int c = checkLoc.getCol();

		if (map[r][c] == 9) {
			print();

			printf("\n=============최단 경로 검색=============\n");
			while(!compareStack.isEmpty()) {
				Location2D ele = Location2D(compareStack.peek()->getData().getRow(), compareStack.peek()->getData().getCol());
				compareStack.pop();
				int ele_Row = ele.getRow();
				int ele_Col = ele.getCol();
				map[ele_Row][ele_Col] = 777;
			}
			print();

			return;
		} else {
			map[r][c] = '.';
			if (isValidLoc(r, c + 1)) standardStack.push(new Node(Location2D(r, c + 1))); // 우
			if (isValidLoc(r, c - 1)) standardStack.push(new Node(Location2D(r, c - 1))); // 좌
		    if (isValidLoc(r - 1, c)) standardStack.push(new Node(Location2D(r - 1, c))); // 상
		    if (isValidLoc(r + 1, c)) standardStack.push(new Node(Location2D(r + 1, c))); // 하
		}
	}
	printf("미로 탐색 실패\n");
}
