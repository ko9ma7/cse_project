#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>

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

	// 1. 권한 확인
	uid_t uid, euid;

	// 학생 계정에서 열었다고 가정하였을 때
	uid = getuid(); // 학생 계정에서 파일을 실행하므로 당연히 uid = 학생
	euid = geteuid(); // 교수님 계정에서 만든 파일이므로 당연히 euid = 교수
	printf("Copy.out 실행 중...  uid = %d, euid = %d\n", (int)uid, (int)euid);

	// 2. 파일 읽어들이기
        rfd = open(file[1], O_RDONLY);
        if (rfd == -1) {
                perror("Open File");
                exit(1);
        }

	// 3. student 권한에서 복사할 수 있도록 euid 설정
	seteuid(getuid());

	int cur_euid = (int)geteuid();
	printf("Copy.out euid 권한 변경 후... uid = %d, euid = %d\n", (int)uid, (int)cur_euid);

	// 4. 파일 복사해서 쓰기
        mode_t mode;
        mode = S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH;

	chdir("/home/student"); // student 계정의 디렉토리로 이동
	printf("Copy.out 현재 경로 %s\n\n", getcwd(NULL, BUFSIZ));

        wfd = open(file[2], O_CREAT | O_WRONLY | O_TRUNC, mode);
        if (wfd == -1) {
                perror("Open copyFile");
                exit(1);
        }

        int chunkSize = changeInt(file[3]);

        lseek(rfd, 0, SEEK_SET);

        while ((n = read(rfd, buf, chunkSize)) > 0) if (write(wfd, buf, n) != n) perror("Write");
        if (n == -1) perror("Read");

        close(rfd);
        close(wfd);
}


int main(int argc, char* argv[]) {

        myFunction(argv);

        return 0;
}
