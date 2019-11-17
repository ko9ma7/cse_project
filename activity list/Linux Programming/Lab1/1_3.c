#include <sys/types.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

int gettimeofday(struct timeval* tv, struct timezone* tz);

int main(int argc, char* argv[]) {
	int rfd, wfd, n;
	char buf[256];

	struct timeval start_point, end_point;
        double operating_time;

	gettimeofday(&start_point, NULL);

	rfd = open(argv[1], O_RDONLY);
 	if (rfd == -1) {
		perror("Open message.txt");
		exit(1);
	}

	wfd = open(argv[2], O_CREAT | O_WRONLY | O_TRUNC, 0644);
	if (wfd == -1) {
		perror("Open Professor_message.txt");
		exit(1);
	}

	printf("읽어들이기 시작!\n");

	while ((n = read(rfd, buf, 1)) > 0) {
		if (buf[0] >= 32 && buf[0] <= 127) {	
			write(wfd, buf, n);
		}
		else {	
			lseek(rfd, buf[0] % 32, SEEK_CUR);
		}
	}

	if (n == -1) perror("read");
	
	printf("읽어들이기 끝!\n");

	close(rfd);
	close(wfd);

	gettimeofday(&end_point, NULL);

        operating_time = (double)(end_point.tv_sec) + (double)(end_point.tv_usec) / 1000000.0
        - (double)(start_point.tv_sec) - (double)(start_point.tv_usec) / 1000000.0;

        printf("It takes %f seconds\n", operating_time);

        return 0;
}
