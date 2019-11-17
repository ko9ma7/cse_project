#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

int isExt(char* str) { // 확장자가 .c인지 구분

        int cnt = 0;
        for (int i = 0; i < strlen(str); i++) {

                if (str[i] == '.') { // 확장자가 있는 경우

                        i++;
                        if (str[i] == 'c') { // 확장자 뒤에 c가 있는 경우

                                i++;
                                if (str[i] == '\0') { // 확장자 뒤에 c말고 더 없으면

                                        cnt++;

                                }

                        }

                }

        }

        if (cnt == 0) return 0;
        else return 1;

}

int main(int argc, char* argv[]) {

        DIR *dp;
        struct dirent *dent;
	FILE *rfp, *wfp;
	int c;

        if ((dp = opendir(argv[1])) == NULL) PRINT_ERR_EXIT("opendir");
	
        while ((dent = readdir(dp))) {

                if (isExt(dent->d_name) == 1) {

                        printf("%s\n", dent->d_name);

			// 작업 위치를 sample로 변경
			chdir(argv[1]);

			if ((rfp = fopen(dent->d_name, "r")) == NULL) PRINT_ERR_EXIT("read");
			if ((wfp = fopen("sum.txt", "a")) == NULL) PRINT_ERR_EXIT("write");

			while ((c = fgetc(rfp)) != EOF) fputc(c, wfp);

			fseek(wfp, 0, SEEK_SET);

                }

        }

        closedir(dp);
	fclose(rfp);
	fclose(wfp);

        return 0;

}

