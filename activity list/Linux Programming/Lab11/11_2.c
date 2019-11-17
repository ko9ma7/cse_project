#include <errno.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

union semun semunarg;
pthread_t thread1;
pthread_t thread2;

int cnt = 0; // 카운트 연산 변수
int semid; // semaphore ID

// Semaphore(1/3) - Lock
union semun{

        int val;
        struct semid_ds *buf;
        unsigned short *array;

};

int initsem(key_t semkey) {

        int status = 0;

        semid = semget(IPC_PRIVATE, 1, IPC_CREAT | IPC_EXCL | 0600);

        if (semid == -1) {

               if (errno == EEXIST) semid = semget(semid, 1, 0);

        } else {

                semunarg.val = 1;
                status = semctl(semid, 0, SETVAL, semunarg); // 1로 초기화

        }

        if (semid == -1 || status == -1) PRINT_ERR_EXIT("initsem failed");

        return semid;

}

// Semaphore(2/3) - Lock
int semlock() {

        struct sembuf buf;

        buf.sem_num = 0;
        buf.sem_op = -1;
        buf.sem_flg = SEM_UNDO;

        if(semop(semid, &buf, 1) == -1) PRINT_ERR_EXIT("semlock failed");

        return 0;

}

int semunlock(){

        struct sembuf buf;

        buf.sem_num = 0;
        buf.sem_op = 1;
        buf.sem_flg = SEM_UNDO;

        if(semop(semid, &buf, 1) == -1) PRINT_ERR_EXIT("semunlock failed");

        return 0;

}

// Commentary에서 주어진 카운팅 함수
void counting(){

        while(1){

                semlock(); // P 연산
                printf("Thread1 실행\n");

                if(cnt > 5) {

                        printf("Thread1 완전 종료\n");
                        semunlock();
                        break;

                } else {

                        cnt++;
                        sleep(1); // 1초 단위로 카운트
                        printf("Thread1 완료\n");

                }

                semunlock(); // V 연산

        }

}

// Commentary에서 주어진 프린팅 함수
void printing(){

        while(1){

                semlock(); // P 연산
                printf("Thread2 실행\n");

                if(cnt > 5) {

                        printf("Thread2 완전 종료\n");
                        semunlock();
                        break;

                } else {

                        printf("카운터 = %d\n", cnt);
                        sleep(1); // 1초 단위로 카운트
                        printf("Thread2 완료\n");

                }

                semunlock(); // V 연산

        }

}

// Semaphore(3/3) - Lock
void semhandle() {

        if ((semid = initsem(1)) < 0) exit(1);
        printf("Semaphore Start =============\n");

        pthread_create(&thread1, NULL, (void*)counting, NULL);
        pthread_create(&thread2, NULL, (void*)printing, NULL);
        pthread_join(thread1, NULL);
        pthread_join(thread2, NULL);

        printf("Semaphore Finish =============\n");

}

int main(int argc, char **argv){

        semhandle();

        return 0;

}
