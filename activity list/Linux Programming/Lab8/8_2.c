#include <unistd.h>
#include <signal.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>

sigset_t new;
int flag = 0; // 알람이 켜졌는지 꺼졌는지에 대한 플래그 변수

void handler_SIGUSR1(int signo) { // 방해 금지 설정 및 해제

	if (flag == 0) {
		
		printf("Do Not Disturb mode on!\n");
		sigfillset(&new);         // 모든 시그널 집합의 시그널 비트 1로 설정
		sigdelset(&new, SIGUSR1); // SIGUSR1의 비트 0으로 설정
		sigdelset(&new, SIGKILL); // SIGKILL의 비트 0으로 설정
		sigdelset(&new, SIGSTOP); // SIGSTOP의 비트 0으로 설정

		sigprocmask(SIG_BLOCK, &new, NULL); // 설정되어준 비트들에 대해 BLOCK
		flag++;

	} else {
			
		flag = 0;
		printf("Do Not Disturb mode off!\n");
		printf("[During the DND mode]\n");
		sigprocmask(SIG_UNBLOCK, &new, NULL); // BLOCK되어진 비트들을 UNBLOCK
		
	}

	sigsuspend(&new); // 시그널들이 처리될때까지 시그널 집합 대기

}

void handler_ALL(int signo) { // 시그널 핸들러

        psignal(signo, "Received signal ");

}

int main(void) {

	for (int i = 1; i <= 34; i++) { // SIGUSR1, SIGKILL, SIGSTOP을 제외한 나머지 시그널 호출
                if (i != 9 && i != 10 && i != 19) {
                        signal(i, handler_ALL);
                }
        }

	signal(SIGUSR1, handler_SIGUSR1); // SIGUSR1 시그널 호출

	for (;;) pause();
	
	return 0;

}
