#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>

// 시간 측정해주는 타이머(Server)
int main(void) {

	key_t key;
	int shmid;
	void *shmaddr;
	
	// 1. 공유 메모리 할당
	key = ftok("Game", 1);
	shmid = shmget(key, 1024, IPC_CREAT | 0666); // 반환값은 공유 메모리 객체의 identifier 
	
	printf("Timer Check Start ==========\n");

	// 2. 공유 메모리 부착
	shmaddr = shmat(shmid, NULL, 0);

	// 3. 공유 메모리에서 Client와 통신
	int timer = 10;
	
	while(1) { 
	
		if (timer == 0) {
			
			// Hints - sprintf를 이용해서 공유 메모리에 데이터 저장 
			sprintf((char *)shmaddr, "%s", "Time Over");
			break;

		} else {

			printf("%d초 시간이 지났습니다\n", timer);
			sprintf((char *)shmaddr, "%d", timer--);

		}
		
		sleep(1); // 1초가 지났음을

	}

	printf("Timer Check Finish==========\n");
	// 4. 공유 메모리 탈착
	shmdt(shmaddr);
	
	return 0;	

}
