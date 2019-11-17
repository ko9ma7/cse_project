#include "game.h"

int main(int argc, char **argv) {

	char ch; // 사용자 입력키
	
	init_map(atoi(argv[1]), atoi(argv[2])); // 맵 초기화
	zombie_set_position(); // 좀비 프로세스 생성
	game_start(); // 게임 시작

	while (1) {

		print_map();

		ch = getch();
		input(ch);

		zombie_move();
		
		terminal_clear();
	
	}

	
	return 0;

}
