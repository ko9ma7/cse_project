#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char **argv) {

	pid_t pid, childPID;
	int status;

	switch (pid = fork()) {

	case -1: // fork fail
		perror("fork fail");
		exit(1);
		break;
	
	case 0: // child process
		// Decrypt.out 실행(해독하기)
		// 외부의 파일을 실행하는 것이므로 execl 함수 사용

                printf("해독 execl 실행\n");
                printf("Parent --> uid = %d, euid = %d\n", (int)getuid(), (int)geteuid());
                printf("해독 현재 경로: %s\n\n", getcwd(NULL, BUFSIZ));

                if (execl("/home/professor/Decrpyt.out", "/home/professor/Decrypt.out", "/home/professor/6_3_message.txt", "de.txt", NULL) == -1) {
                        perror("fail decrypt");
                        exit(1);
                }

                printf("해독 execl 종료\n\n");
		break;

	default: // parent process
		 // Copy.out 실행(복사하기)
		 // 마찬가지로 외부의 파일을 실행하는 것이므로 execl 함수 사용

		if ((childPID = wait(&status)) < 0) {
			perror("wait");
			exit(1);
		}
		
		printf("복사 execl 실행\n");
                printf("Child --> uid = %d, euid = %d\n", (int)getuid(), (int)geteuid());
                printf("복사 현재 경로: %s\n\n", getcwd(NULL, BUFSIZ));

                if (execl("/home/professor/Copy.out", "/home/professor/Copy.out", "/home/professor/.secret/de.txt", argv[1], "1024", NULL) == -1) {
                        perror("fail copy");
                        exit(1);
                }

                printf("복사 execl 종료\n\n");
		break;
	
	}

	return 0;

}
