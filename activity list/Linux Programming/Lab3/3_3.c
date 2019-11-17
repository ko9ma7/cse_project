#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <dirent.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

int main(int argc, char* argv[]) {
	
	// 1. 디렉토리 및 파일 이름의 prefix 가져오기

	char *ptr = strtok(argv[1], "/"); 
	char *buf[30];
	int i = 0;

	while (ptr != NULL) {
	
		buf[i] = ptr;
		ptr = strtok(NULL, "/");
		i++;

	}

	char *directory = buf[i - 2];
	char *prefix = buf[i - 1];

//	printf("%s\n", directory);
//	printf("%s\n", prefix);

	// 2. 입력된 디렉토리를 열고, 그 디렉토리 안에 출력할 폴더 생성

	chdir(directory);
	if (mkdir(argv[3], 0755) == -1) PRINT_ERR_EXIT("opendir");
	
	// 3. 데이터 sampling 하기 및 작업 디렉토리 경로 추출하기

	char samplePrefix[100];
	char originalPrefix[100];
	int samplingStep = atoi(argv[2]); // sampling 간격

	int sampleStep = 1;
	int symLinkStep = 1;

	char cwd[BUFSIZ]; 
	getcwd(cwd, BUFSIZ);

	chdir(argv[3]);
	while (sampleStep < 1000) {
		
		sprintf(samplePrefix, "%s/%s%03d", getcwd(NULL, BUFSIZ), prefix, sampleStep);
		sprintf(originalPrefix, "%s/%s%03d", cwd, prefix, symLinkStep);		

		sampleStep += samplingStep;
		symLinkStep += 1;

		printf("samplePrefix: %s\n", samplePrefix);
		printf("originalPrefix: %s\n", originalPrefix);

		// 4. sampling된 데이터 경로와 그 상위 폴더 경로와 symlink 해주기

		symlink(originalPrefix, samplePrefix); // 왼쪽 매개변수가 타겟 경로, 오른쪽 매개변수가 symlink
		
	}
	
	return 0;

}
