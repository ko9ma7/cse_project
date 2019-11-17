#include <sys/errno.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <dirent.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

int getAscii(char *s) { // 문자열의 아스키코드 합 구하고 % 12 해주기

	int result = 0;

	for (int i = 0; i < strlen(s); i++) {

		result += s[i];

	}
	
	return result % 12;	

}

int main(int argc, char* argv[]) {

	DIR *dp;
	struct dirent *dent;
	// 1. Ubuntu 아스키코드 합 % 12 구하기 = curOffset

	int curOffset = getAscii("Ubuntu");

	// 2. 폴더 열기

	if ((dp = opendir("sample2")) == NULL) PRINT_ERR_EXIT("openDir");
	while ((dent = readdir(dp))) {
	
		printf("Read: %s\n", dent->d_name);
		printf("Cur Offset: %ld\n", telldir(dp)); 

	}

	printf("\n\n");

	// 3. cufOffset인 위치 폴더에 들어가기

	seekdir(dp, curOffset - 1); // curOffset만큼 이동
//	printf("%ld\n", telldir(dp));
//	printf("%s\n", getcwd(NULL, BUFSIZ));

	dent = readdir(dp);
	printf("CurOffset Dir Read: %s\n", dent->d_name);

	chdir("sample2");		

	DIR *rdp;
	if ((rdp = opendir(dent->d_name)) == NULL) PRINT_ERR_EXIT("openCurDir");

	chdir(dent->d_name); // "e" 폴더 안으로 작업 변경
		
	// 4. 작업 폴더 안에 디렉토리인지 디렉토리가 아닌지 선별
	
	struct stat buf;
	int kind;
	char dir[100];
	struct dirent *curDent;

	seekdir(rdp, 2); // '.', '..' 디렉토리는 제외해야 하므로
	
	while ((curDent = readdir(rdp))) { // "e" 폴더 안에 있는 모든 파일, 디렉토리 읽기

		sprintf(dir, "%s/%s", getcwd(NULL, BUFSIZ), curDent->d_name); // 현재 디렉토리 경로 설정
		stat(dir, &buf); // stat의 왼쪽 매개변수는 항상 디렉토리 경로값

       		kind = buf.st_mode & S_IFMT; // 폴더인지 아닌지 구분해주기

        	if (kind == S_IFDIR) { // 디렉토리인경우 -> "duk!", "hops", "lalala", ... 등이 있다
			
			// 4. 비어있으면 폴더를 삭제, 비어있지 않으면 폴더 속 파일의 경로 출력

			printf("검사할 디렉토리명: %s\n", curDent->d_name);
			char curDir[100];
                        sprintf(curDir, "%s/%s", getcwd(NULL, BUFSIZ), curDent->d_name);
	       	
	
			DIR *sdp;

			if ((sdp = opendir(curDent->d_name)) == NULL) PRINT_ERR_EXIT("openSampleDir");
			seekdir(sdp, 2); // '.', '..' 디렉토리는 제외해야 하므로

			if (readdir(sdp) == NULL) { // 디렉토리 안에 파일이 없으면

			//	if (rmdir(curDir) == -1) PRINT_ERR_EXIT("remove");
				printf("%s폴더를 삭제하였습니다\n\n", curDent->d_name);

			} else { // 디렉토리 안에 파일이 있으면

				printf("%s\n\n", curDir);

			}			

		}

	}

	closedir(dp);

	return 0;

}
