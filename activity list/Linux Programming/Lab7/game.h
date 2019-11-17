#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <termios.h>
#include <unistd.h>
#include <time.h>
#include <signal.h>

// 맵 기본 정보
int **map, row, col;
int userX, userY;
int zomX=-1, zomY=-1;
int shX = -1;

// 종료를 위한 변수
int gameClear = 0;
int kill_zombie();

// 맵 동적할당 및 셋팅
void init_map(int r, int c) {
	row = r; col = c;
	map = (int**)malloc(sizeof(int*) * row);
	
	userY = row-2;

	for(int i=0; i<row; i++) {
		map[i] = (int*)malloc(sizeof(int) * col);
	}

	for(int i=0; i<row; i++) {
		for(int j=0; j<col; j++) {
			if(i == 0 || j == 0 || i == row-1 || j == col-1) {
				map[i][j] = 9;
			}
			else if(i == row - 3 && (j != 0 || j != col-1)) {
				map[i][j] = 5;
			}
			else {
				map[i][j] = 0;
			} 
		}
	}
}

// 게임 스타트
void game_start() {
	map[row-2][col/2] = 3;
	userX = col/2;
	userY = row-2;
}

// 맵 출력
void print_map() {
	char ch;

	for(int i=0; i<row; i++) {
		for(int j=0; j<col; j++) {
			switch(map[i][j]) {
			case 0:
				printf("  "); break;
			case 9:
				printf("■"); break;
			
			// 캐릭터 및 좀비 설정
			case 1: 
				printf("▼"); break;
			case 3: 
				printf("凸"); break;
			case 5: 
				printf("ㅡ"); break;
			case 7: 
				printf("↑"); break;
			default: break;
			}
		}
		printf("\n");
	}
}

// 터미널 clear
void terminal_clear() {
	system("clear");
}

// 리눅스에 없는 getch 구현
int getch(void) {
	int ch;
	struct termios buf;
	struct termios save;
	
	tcgetattr(0, &save);
	buf = save;
	buf.c_lflag &= ~(ICANON | ECHO);
	buf.c_cc[VMIN] = 1;
	buf.c_cc[VTIME] = 0;
	tcsetattr(0, TCSAFLUSH, &buf);
	ch = getchar();
	tcsetattr(0, TCSAFLUSH, &save);
	return ch;
}

// 사용자가  입력한 방향키에 따라 유저 아이콘 이동
// 왼쪽 오른쪽으로만 이동
void input(int ch) {
	// 오른쪽
	if(ch == 100  && userX < col-2) {
		map[userY][userX] = 0;
		userX++;
		map[userY][userX] = 3;
	}
	// 왼쪽
	else if(ch == 97 && userX > 1) {
		map[userY][userX] = 0;
		userX--;
		map[userY][userX] = 3;
	}
	else if(ch == 119) {
		for(int i=1; i<userY-1; i++) {
			map[i][shX] = 0;
			map[i][userX] = 7;
		}
		gameClear = kill_zombie();
		shX = userX;
	} 
}

// 좀비가 생성되면 포지션 랜덤 설정
void zombie_set_position() {
	int r_col;
	srand(time(NULL));
	
	r_col = rand() % (col-2) + 1;
	map[1][r_col] = 1;
	zomX = r_col; zomY = 1;

}

// 좌우로 왔다갔다 도망가는 좀비
void zombie_move() {
	int alpha;
	srand(time(NULL));

	if(zomX != -1) {
		map[zomY][zomX] = 0;
		alpha = rand() % 2;
		if(alpha == 0) zomX -= 1;
		else zomX += 1;

		if(zomX < 1) zomX = 1;
		else if(zomX > col-2) zomX = col-2;
		map[zomY][zomX] = 1;
	}
}

// 과제 부분
int kill_zombie() {

	if (zomX == userX) {

		kill((int)getpid(), SIGKILL);
		return 1;

	} else return 0;

}

