#include <sys/types.h>
#include <sys/times.h>
#include <time.h>
#include <limits.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

// 1. 소수를 구하는 함수
int* getPrimeNum(int srange, int erange) {

        int *primeNum = (int *)malloc(sizeof(int) * erange);

        for (int i = 0; i < erange; i++) primeNum[i] = 0;

        for (int i = srange; i <= erange; i++) {

                int count = 0;

                for (int j = 2; j <= erange; j++) {

                        if (i % j == 0) count++;

                }

                if (count == 1) primeNum[i] = 1;

        }

        return primeNum;

}

// 2. 단일 프로세스 수행
void serialProcessManagement(int s, int e, char *filename) {

        // 파일에 쓰는 데 필요한 변수
        FILE *fp = fopen(filename, "w");
        if (fp == NULL) { perror("write"); exit(1); }

        // 시간 측정에 필요한 변수
        time_t checkT;
        struct tms mytms;
        clock_t serial_St, serial_Et;

        // 단일 프로세스로 소수 구하기 측정
        if ((serial_St = times(&mytms)) == -1) PRINT_ERR_EXIT("serial start times");

        int count = 0;
        for (int i = 0; i < e; i++) {

                if (getPrimeNum(s, e)[i] == 1) {
                        fprintf(fp, "%d ", i);
                        count++;

                }

        }

        fprintf(fp, "\n");

        printf("[P0] found %d primes\n", count);

        if ((serial_Et = times(&mytms)) == -1) PRINT_ERR_EXIT("serial end times");

        printf("[Serial] takes %.2f sec\n", (double)(serial_Et - serial_St) / sysconf(_SC_CLK_TCK));
        fclose(fp);

}

void parallelProcessManagement(int s, int e, int n, char *filename) {

        // 파일에 쓰는 데 필요한 변수
        FILE *fp = fopen(filename, "w");
        if (fp == NULL) { perror("write"); exit(1); }

        // 시간 측정에 필요한 변수
        time_t checkT;
        struct tms mytms;
        clock_t parallel_St, parallel_Et;

	double Tm = 0;

        pid_t pid;
        pid_t *pids = (pid_t *)malloc(sizeof(pid_t) * n);
        int status;
        int runProcess = 0;
	
	if ((parallel_St = times(&mytms)) == -1) PRINT_ERR_EXIT("parallel start times");

	while(runProcess < n) {
		
                pids[runProcess] = fork(); //fork 생성

                if(pids[runProcess] < 0) {
			
			PRINT_ERR_EXIT("fork");
                        return;

                } else if(pids[runProcess] == 0) { //자식 프로세스
			int count = 0;

                        for (int i = (int)e/n * runProcess; i < (int)e/n * (runProcess + 1); i++) {

                                if (getPrimeNum(s, e)[i] == 1) {

					fprintf(fp, "%d ", i);
                                        count++;

                                }

                        }

			printf("[P%d] found %d primes\n", runProcess, count);
                        exit(0);

                } else { // 부모 프로세스

			wait(&status);

                }

                runProcess++;

        }

	if ((parallel_Et = times(&mytms)) == -1) PRINT_ERR_EXIT("parallel end times");

	Tm = (double)(parallel_Et - parallel_St) / sysconf(_SC_CLK_TCK); // 전체 걸린 시간	
	printf("[Parallel] takes %f sec\n", Tm);

	fprintf(fp, "\n");
	fclose(fp);
	
}

int main(int argc, char **argv) {

	if (atoi(argv[3]) == 1) serialProcessManagement(atoi(argv[1]), atoi(argv[2]), argv[4]);
	else parallelProcessManagement(atoi(argv[1]), atoi(argv[2]), atoi(argv[3]), argv[4]);

	return 0;

}
