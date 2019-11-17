#include <sys/types.h>
#include <sys/times.h>
#include <time.h>
#include <limits.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

int getPrimeNum(int, int);
void serialProcessManagement(int, int);
void parallelProcessManagement(int, int);

// 1. 소수를 구하는 함수
int getPrimeNum(int srange, int erange) {

        int result = 0;
	
	for (int i = srange; i <= erange; i++) {

		int count = 0;

		for (int j = 2; j <= erange; j++) {

			if (i % j == 0) count++;

		}

		if (count == 1) result++;
	
	}

	return result;
	
}

// 2. 시간 출력 포맷
char * output = { "%A %B %d %T %G" };

// 3. 단일 프로세스 수행
void serialProcessManagement(int s, int e) {

	// 시간 측정에 필요한 변수
        time_t checkT;
        struct tms mytms;
        clock_t serial_St, serial_Et;

        // 날짜 출력에 필요한 변수
        struct tm *tm;
        time_t printT;
        char buf[BUFSIZ];

        // 3-1. 단일 프로세스로 소수 구하기 측정
        time(&printT);
        tm = localtime(&printT);
        strftime(buf, sizeof(buf), output, tm);

        if ((serial_St = times(&mytms)) == -1) PRINT_ERR_EXIT("serial start times");

        printf("[Serial start] %s\n", buf);

        printf("[Serial] found %d primes\n", getPrimeNum(s, e));
        if ((serial_Et = times(&mytms)) == -1) PRINT_ERR_EXIT("serial end times");

        time(&printT);
        tm = localtime(&printT);
        strftime(buf, sizeof(buf), output, tm);
        printf("[Serial] takes %.2f sec\n", (double)(serial_Et - serial_St) / sysconf(_SC_CLK_TCK));
        printf("[Serial end] %s\n", buf);

}

// 4. 병렬 프로세스 수행
void parallelProcessManagement(int s, int e) {
	
	// 시간 측정에 필요한 변수
        time_t checkT;
        struct tms mytms;
        clock_t parallel_St, parallel_Et;

        // 날짜 출력에 필요한 변수
        struct tm *tm;
        time_t printT;
        char buf[BUFSIZ];

	pid_t pid;
	
	switch (pid = fork()) {

	case -1:
		PRINT_ERR_EXIT("fork");
		break;
	// 4-1. 병렬 프로세스로 소수 구하기 측정
	case 0: // child process - srange ~ erange/2 범위 안의 소수 찾기
		
		time(&printT);
       		tm = localtime(&printT);
        	strftime(buf, sizeof(buf), output, tm);

		if ((parallel_St = times(&mytms)) == -1) PRINT_ERR_EXIT("parallel start times");		

        	printf("[pid = %d] found %d primes numbers between[%d ~ %d]\n", (int)getpid(), getPrimeNum(s, (int)e/2), s, (int)e/2);

        	if ((parallel_Et = times(&mytms)) == -1) PRINT_ERR_EXIT("parallel end times");
		
        	time(&printT);
        	tm = localtime(&printT);
       	 	strftime(buf, sizeof(buf), output, tm);
		
        	printf("[pid = %d] takes %.2f sec\n", (int)getpid(), (double)(parallel_Et - parallel_St) / sysconf(_SC_CLK_TCK));
        	printf("[Proc.%d end] %s\n", (int)getpid(), buf);

		break;
	
	default: // parent process - erange/2+1 ~ erange 범위 안의 소수 찾기
		
		time(&printT);
                tm = localtime(&printT);
                strftime(buf, sizeof(buf), output, tm);
		
                if ((parallel_St = times(&mytms)) == -1) PRINT_ERR_EXIT("parallel start times");
		
                printf("[Parallel start] %s\n", buf);
		
                printf("[pid = %d] found %d primes numbers between[%d ~ %d]\n", (int)getpid(), getPrimeNum((int)e/2+1, e), (int)e/2+1, e);

                if ((parallel_Et = times(&mytms)) == -1) PRINT_ERR_EXIT("parallel end times");

                time(&printT);
                tm = localtime(&printT);
                strftime(buf, sizeof(buf), output, tm);

                printf("[pid = %d] takes %.2f sec\n", (int)getpid(), (double)(parallel_Et - parallel_St) / sysconf(_SC_CLK_TCK));
                printf("[Proc.%d end] %s\n", (int)getpid(), buf);
		
		break;

	}

}

int main(int argc, char** argv) {

	serialProcessManagement(atoi(argv[1]), atoi(argv[2]));
	parallelProcessManagement(atoi(argv[1]), atoi(argv[2]));

	return 0;

}

