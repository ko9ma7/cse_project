#include <sys/types.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <dirent.h>

int main(int argc, char* argv[]) {
        int rfd, wfd, n;
        char buf[256];

        rfd = open(argv[1], O_RDONLY);
        if (rfd == -1) {
                perror("Open message.txt");
                exit(1);
        }
		
	chdir("/home/professor/.secret");
	printf("Decrypty.out 현재 경로: %s\n\n", getcwd(NULL, BUFSIZ));
	
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

        return 0;
}
