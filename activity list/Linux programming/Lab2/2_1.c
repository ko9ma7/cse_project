#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>

int changeInt(char* c) { // 정수 변환 함수

        int val = 0;
        for (int i = 0; c[i] != '\0'; i++) {
                val *= 10;
                val += c[i] - '0';
        }

        return val;

}

int main(int argc, char* argv[]) {

	int rfd, n;
	FILE* wfp; 
	char buf[2];

	rfd = open(argv[1], O_RDONLY); // 파일 input은 system call을 이용
	if (rfd == -1) {
		perror("Open input");
		exit(1);
	}

	wfp = fopen(argv[2], "wb"); // 파일 output은 stand library를 이용
	if (wfp == NULL) {
		perror("Open output");
		exit(1);
	}

	int num, i = 0, j;
	int val = 0, result = 0, cnt = 0;

	while ((n = read(rfd, buf, 1)) > 0) { // 한 비트씩 읽어옴
	
		if (buf[0] == '\n') continue; // 만약 버퍼에 값이 개행이라면 조건식으로 이동

		buf[1] = '\0'; // 버퍼가 문자열이 되도록

		if (cnt < 8) { // 8비트씩 읽기 위해서 첫 번째 바이트

			if (cnt == 0) j = 1;
			i = 0; cnt++;

		} else if (cnt >= 8 && cnt < 16) { // 두 번째 바이트

			if (cnt == 8) j = 1;
			i = 1; cnt++;

		} else if (cnt >= 16 && cnt < 24) { // 세 번째 바이트

			if (cnt == 16) j = 1;
			i = 2; cnt++;

		}

		num = 8 * (3 - i) - j;
		val = changeInt(buf) * (int)pow(2.0, num); // 정수로 변환
		result += val;		

		if (cnt == 24) { // 3바이트 모두 읽혀지면

			fprintf(wfp, "%d ", result); // fprintf를 이용해 output파일에 출력
			cnt = 0;
			result = 0;			

		}

		j++;
		buf[0] = '\0'; // 버퍼의 초기화

	}

	close(rfd);
	fclose(wfp);

	return 0;

}
