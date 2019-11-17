#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

int main(int argc, char *argv[]) {

	// 1. 권한을 확인해본다

        uid_t uid, euid;

	// 학생 계정에서 열었다고 가정하였을 때
        uid = getuid(); // 학생 계정에서 파일을 실행하므로 당연히 uid = 학생
        euid = geteuid(); // 교수님 계정에서 만든 파일이므로 당연히 euid = 교수
        printf("uid = %d, euid = %d\n", (int)uid, (int)euid);

	FILE *rfp, *wfp;
	
	// 교수님에게 있는 grade.db 파일을 절대경로를 통해 읽어온다
  	// db 파일은 읽기만 가능하다
	if ((rfp = fopen("/home/professor/grade.db", "r")) == NULL) PRINT_ERR_EXIT("open");

	// 2. student 권한에서 작성할 수 있도록

	seteuid(getuid()); // 학생계정에서 성적을 읽기는 하였으나 쓸 수 있도록 권한을 주어야 한다
        int cur_euid = (int)geteuid(); // 현재 euid를 학생으로 바꿔준다
        printf("uid = %d, euid = %d\n", (int)uid, (int)cur_euid); // uid는 당연히 학생의 것, euid는 위에서 고친 학생의 것

	if ((wfp = fopen(argv[2], "w")) ==  NULL) PRINT_ERR_EXIT("write");

	int n;
	int checkStID = atoi(argv[1]);
	int studentID;
	char *grade;

	while ((n = fscanf(rfp, "%d %s", &studentID, &(*grade))) != EOF) {
		
		if (studentID == checkStID) {
			
			printf("my Grade = %s\n", grade);		
			fprintf(wfp, "%s\n", grade); // 학번이 같으면 텍스트 파일에 출력
		
		}

	}

	printf("Write the grade to %s\n", argv[2]);
	
	fclose(rfp);
	fclose(wfp);

	return 0;

}
