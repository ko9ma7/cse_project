#include <sys/types.h>
#include <pwd.h>
#include <grp.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

int main(void) {

	struct passwd *pw;
	struct group *grp;

	// 1. user info에서 GID와 group info에서 GID를 찾아 이름을 비교한다

	int pwGID, grpGID;
	char *pwName, *grpName;

	FILE *wfp;
	if ((wfp = fopen("different.txt", "w")) == NULL) PRINT_ERR_EXIT("open");
	
	fprintf(wfp, "====== Same GID, Different Name ======\n");

	pw = getpwent();

	while (1) {
		
		grp = getgrent();

		if (grp == NULL) { // grp가 NULL이면

			pw = getpwent(); // pw는 다음 값을 받아오고
			if (pw == NULL) break; // pw가 NULL이 되면 더이상 비교할 이유가 없다

			setgrent(); // grp는 다시 0으로 돌아간다
			grp = getgrent(); // 다시 grp의 값을 받아온다
	
		}

		pwGID = (int)pw->pw_gid;
		grpGID = (int)grp->gr_gid;

		// 2. 같은 GID를 찾았다면 이름을 비교해준다

		if (pwGID == grpGID) {

			pwName = pw->pw_name;
			grpName = grp->gr_name;
			
			// 3. 이름이 다른 경우만 출력해준다

			if (strcmp(pwName, grpName) != 0) {

				fprintf(wfp, "GID: %d GName: %s Pname: %s\n", pwGID, grpName, pwName);

			}
			

		} 
	

	}

	fclose(wfp);

	return 0;


}
