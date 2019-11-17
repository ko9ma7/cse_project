#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

int main(void) {
	int pd1, pd2, n;
	char buf[BUFSIZ];
	char answer[5];

	printf("First Program =====\n");

	if ((pd1 = open("./pipeX", O_WRONLY)) == -1) PRINT_ERR_EXIT("open");
	if ((pd2 = open("./pipeY", O_RDONLY)) == -1) PRINT_ERR_EXIT("open");

	while (1) {
	
		printf("input : ");
		scanf("%s", answer);
                write(pd1, answer, strlen(answer) + 1);

		read(pd2, buf, BUFSIZ);

		if (buf[0] == 'S') {

			printf("%s", buf);
			break;

		}
	
		printf("%s", buf);


	}

	close(pd2);
	
	return 0;
}
