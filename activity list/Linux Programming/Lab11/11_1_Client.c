#include <sys/types.h> 
#include <sys/mman.h> 
#include <signal.h> 
#include <sys/ipc.h> 
#include <sys/shm.h> 
#include <unistd.h> 
#include <stdio.h> 
#include <string.h>
#include <stdlib.h>
#include <time.h>

// 문제를 푸는 Client 
int main(void) {

	key_t key;
	int shmid;
	void *shmaddr;
	int try = 4;
	int randNum1;
	int randNum2;
	int answer;
	
	srand((unsigned int)time(NULL));

	randNum1 = rand() % 10 + 1;
	randNum2 = rand() % 10 + 1;

	answer = randNum1 * randNum2;

	// 1. 공유 메모리 할당
	key = ftok("Game", 1);
	shmid = shmget(key, 1024, 0); // 반환값은 공유 메모리 객체의 identifier
	
	// 2. 공유 메모리 부착
	shmaddr = shmat(shmid, NULL, 0);

	// 3. 공유 메모리에서 Server와 통신
	printf("Game Start ==========\n");
	printf("%d x %d = ?\n", randNum1, randNum2);
	int n;

	while (1) {

		printf("현재 남은 시간(초): %s\n", (char *)shmaddr);
		printf("정답 입력: "); scanf("%d", &n);

		if (n == answer) {

			printf("정답입니다\n");
			break;

		} else {
   	
			printf("틀렸습니다\n");

		}

		if (try == 1) break;

		try--;

	}
	
	// 4. 공유 메모리 탈착
	shmdt(shmaddr);
	shmctl(shmid, IPC_RMID, NULL);
	
	return 0;

}

