#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#define BUF_SIZE 1000

int gettimeofday(struct timeval* tv, struct timezone* tz);

int getLength(char* str) {

        int cnt = 0;
        for (int i = 0; str[i] != '\0'; i++) cnt++;

        return cnt;

}

int strCmp(char* str1, char* str2, int len) {

        if (getLength(str1) != len) return 0;

        int cnt = 0;
        for (int i = 0; i < len; i++) {
                if (str1[i] == str2[i]) {
                        cnt++;
                }
                else break;
        }

        if (cnt == len) return 1;
        else return 0;

}

int main(int argc, char* argv[]) {
        int fd;
        int cnt = 0;
        char buf[BUF_SIZE];

	struct timeval start_point, end_point;
        double operating_time;

        gettimeofday(&start_point, NULL);

        fd = open(argv[1], O_RDONLY);
        if (fd == -1) {
                perror("Open unix.txt");
                exit(1);
        }

        int chunkSize = getLength(argv[2]);
        char cmpBuf[10];
        int startOffset = 0;


        printf("The target word is '%s'(size = %d)\n", argv[2], chunkSize);



        read(fd, buf, BUF_SIZE);

        for (int i = 0; buf[i] != '\0'; i++) {

		int k = 0;
		
		if (buf[i] == 32 || buf[i] == '\n') {

			for (int j = startOffset; j < i; j++) {

				cmpBuf[k] = buf[j]; k++;

			}

			cmpBuf[k] = '\0';
			startOffset = i + 1;

			printf("Compare %s : %s", argv[2], cmpBuf);
		
			if (strCmp(cmpBuf, argv[2], chunkSize)) {
				
				printf(" - Matched!\n");
				cnt++;

			} else {
					
				printf("\n");		
				cmpBuf[0] = '\0';

			}

		}
		
        }
       

        printf("# of '%s' in the text file is %d\n", argv[2], cnt);

        close(fd);
	
	gettimeofday(&end_point, NULL);

        operating_time = (double)(end_point.tv_sec) + (double)(end_point.tv_usec) / 1000000.0
        - (double)(start_point.tv_sec) - (double)(start_point.tv_usec) / 1000000.0;

        printf("It takes %f seconds\n", operating_time);

        return 0;
}
