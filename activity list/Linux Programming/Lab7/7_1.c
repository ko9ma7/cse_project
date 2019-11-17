#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <errno.h>
#include <sys/types.h>
#include <string.h>

extern const char *const sys_siglist[];

// 1. Ctrl + C 발생
void sigint_handler(int sig) {

	printf("Caught SIGINT!\n");	
	psignal(sig, "Received signal");

	// 2. x초 후 alarm이 발생하게 되도록
	alarm(5);

}

// 3. Alarm clock에서는 User defined signal1, 2가 발생한다
void sigalrm_handler(int sig) {
	
	printf("Caught SIGALRM!\n");
	psignal(sig, "Received signal");

	pid_t targetPID = (int)getpid();

	if (kill(targetPID, SIGUSR1) == -1) {
		switch (errno) {
		case EPERM:
			printf("Not enough permission!\n");
			break;
		case ESRCH:
			printf("Cannot find the process %d\n", targetPID);
			break;
		}
	} else {
		printf("Bang! -> %d\n", targetPID);
	}

	if (kill(targetPID, SIGUSR2) == -1) {
		switch (errno) {
		case EPERM:
			printf("Not enough permission!\n");
			break;
		case ESRCH:
			printf("Cannot find the process %d\n", targetPID);
			break;
		}
	} else {
		printf("Bang! -> %d\n", targetPID);
	}

}

void UserDefinedSignal1(int sig) {

	printf("Caught SIGUSR1!\n");
	psignal(sig, "Received signal");


	for (int i = 1; i < 100; i++) {

		if (sys_siglist[i] == NULL) printf("Numbers #%d = Unknown signal %d\n", i, i);
		else printf("Numbers #%d = %s\n", i, sys_siglist[i]);

	}

}

void UserDefinedSignal2(int sig) {

	printf("Caugh SIGUSR2!\n");
	psignal(sig, "Received signal");

	pid_t targetPID = (int)getpid();

	kill (targetPID, SIGKILL);

}

int main(void) {

	signal(SIGINT, sigint_handler);
	signal(SIGALRM, sigalrm_handler);
	signal(SIGUSR1, UserDefinedSignal1);
	signal(SIGUSR2, UserDefinedSignal2);

	for (;;) pause();
	
	return 0;
}
