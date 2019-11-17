#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

int gettimeofday(struct timeval* tv, struct timezone* tz);

int changeInt(char* c) {
	int val = 0;
	for (int i = 0; c[i] != '\0'; i++) {
		val *= 10;
		val += c[i] - '0';
	}

	return val;
}

void myFunction(char* file[]) {
        int rfd, wfd, n;
        char buf[5000];

        rfd = open(file[1], O_RDONLY);
        if (rfd == -1) {
                perror("Open File");
                exit(1);
        }

        mode_t mode;
        mode = S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH;

        wfd = open(file[2], O_CREAT | O_WRONLY | O_TRUNC, mode);
        if (wfd == -1) {
                perror("Open copyFile");
                exit(1);
        }

	int chunkSize = changeInt(file[3]);
	printf("chunkSize = %d\n", chunkSize);
	
	lseek(rfd, 0, SEEK_SET);

        while ((n = read(rfd, buf, chunkSize)) > 0) if (write(wfd, buf, n) != n) perror("Write");
        if (n == -1) perror("Read");

        close(rfd);
        close(wfd);
}


int main(int argc, char* argv[]) {
	struct timeval start_point, end_point;
	double operating_time;

	gettimeofday(&start_point, NULL);

	myFunction(argv);

	gettimeofday(&end_point, NULL);

	operating_time = (double)(end_point.tv_sec) + (double)(end_point.tv_usec) / 1000000.0
	- (double)(start_point.tv_sec) - (double)(start_point.tv_usec) / 1000000.0;

	printf("It takes %f seconds\n", operating_time);

	return 0;
}	
