#include <stdlib.h>
#include <stdio.h>
#include <string.h>

extern char **environ;

int main(int argc, char **argv) {

	FILE *fp = fopen("env.txt", "w");
	if (fp == NULL) {
		
		perror("write");
		exit(1);
	
	}

	char **env = environ;

	// 1. 입력받은 환경변수 5개가 현재 존재하는 환경변수인지 아닌지 확인하기	
	for (int i = 1; i < argc; i++) {
	
		char *val = getenv(argv[i]); // 이름이 argv[i]인 환경변수 값

		// 2. 존재한다면 환경변수의 값을 exist로 변경해주기
		if  (val != NULL) {

			printf("환경변수 %s는 이미 존재하는 환경변수입니다.\n", argv[i]);
                        printf("환경변수 값을 exist로 변경해주세요.\n");
			
			setenv(argv[i], "exit", 1); // exit으로 값을 설정해주고, 1은 변경을 하겠다는 의미
			val = getenv(argv[i]);
			printf("%s=%s\n", argv[i], val);

		} else { // 3. 존재하지 않는다면 환경변수의 값을 create로 추가해주기

			printf("환경변수 %s를 추가합니다.\n", argv[i]);
                        printf("환경변수 값을 create로 추가해주세요.\n");

                        char envName[BUFSIZ];
                        sprintf(envName, "%s=create", argv[i]); // 원하는 형식의 환경변수
			printf("%s\n", envName);
                        putenv(envName);

			fprintf(fp, "%s\n", envName);

		}

	}

	env = environ;

	for (int i = 0; env[i]; i++) {

		fprintf(fp, "%s\n", env[i]);

	}

	fclose(fp);
	return 0;

}
