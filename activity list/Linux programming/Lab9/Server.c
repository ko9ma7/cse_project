#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

int main(void) {
	
	int pd1, pd2, n;
	int answer;
	char buf[BUFSIZ];
	char Msg[50];
	srand(time(NULL));

	answer = rand() % 50 + 1;
	
	printf("Second Program =====\n");
	printf("Correct Answer: %d\n", answer);
	if ((pd1 = open("./pipeX", O_RDONLY)) == -1) PRINT_ERR_EXIT("open");
	if ((pd2 = open("./pipeY", O_WRONLY)) == -1) PRINT_ERR_EXIT("open");

	while (1) {

		read(pd1, buf, BUFSIZ);
		printf("input Value: %d\n", atoi(buf));

		if (atoi(buf) == answer) {
		
			sprintf(Msg, "Success, %d is correct\n", atoi(buf));
			write(pd2, Msg, strlen(Msg) + 1);
			break;
	
		} else if (atoi(buf) > answer) {

			sprintf(Msg, "Up, %d is too high\n", atoi(buf));
			write(pd2, Msg, strlen(Msg) + 1);

		} else {

			sprintf(Msg, "Down, %d is too low\n", atoi(buf));
			write(pd2, Msg, strlen(Msg) + 1);

		}
		
	}

	close(pd2);

	return 0;
}
