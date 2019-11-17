#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/time.h>
#include <fcntl.h>
#include <unistd.h>
#define PRINT_ERR_EIXT(_msg) { perror(_msg); exit(1); }

struct user_tm { // 나만의 타이머를 만들기 위한 구조체
	
	double timer_sec; // 초
	double timer_usec; // 밀리초

};

struct user_tm *myTimer; // 전역변수 선언
int nTm; // 타이머 개수

int myTimer_init(int numTimers) {

	nTm = numTimers;
        myTimer = malloc(sizeof(struct tm) * numTimers); // 타이머 개수만큼  동적 할당

        for (int i = 0; i < numTimers; i++) {
                myTimer[i].timer_sec = 0.0;
		myTimer[i].timer_usec = 0.0;
	}

}

int myTimer_finalize(void) {

        free(myTimer);	// 동적할당 해제

}

int myTimer_on(int timerID) { // Timer On
	
	struct timeval myTm;

        gettimeofday(&myTm, NULL); // 시작 시간 설정
	double sec = (double)myTm.tv_sec; // 초 가져오기
	double usec = (double)myTm.tv_usec; // 밀리초 가져오기
	
	if (myTimer[timerID].timer_sec != 0) { // 기존에 있는 초가 있는 경우 시간 누적

		myTimer[timerID].timer_sec += sec;
		myTimer[timerID].timer_usec += usec;

	} else { // 기존에 초가 없으면 누적할 필요가 없으므로 그냥 설정

		myTimer[timerID].timer_sec = sec;
		myTimer[timerID].timer_usec = usec;

	}

}

int myTimer_off(int timerID) { // Timer Off

	struct timeval myTm;

        gettimeofday(&myTm, NULL); // 끝 시간 설정

	double sec = (double)myTm.tv_sec; // 초 가져오기
        double usec = (double)myTm.tv_usec; // 밀리초 가져오기

        myTimer[timerID].timer_sec = sec - myTimer[timerID].timer_sec; // 결과 초 저장
	myTimer[timerID].timer_usec = usec - myTimer[timerID].timer_usec; // 결과 밀리초 저장

}

int myTimer_print(void) {

	printf("[DSTimer]\n");
	for (int i = 0; i < nTm; i++) 
        	printf("Timer %d : %.2f ms\n", i, myTimer[i].timer_sec + myTimer[i].timer_usec / 1000000);

}
