#include <sys/stat.h>
#include <unistd.h>
#include <sys/sysinfo.h>
#include <sys/utsname.h>
#include <stdlib.h>
#include <stdio.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

int main(void) {

	struct utsname uts;
	char *cwd; // 경로 저장 버퍼
	
	if (uname(&uts) == -1) PRINT_ERR_EXIT("uname");
	
 	// 1. 시스템에 설치된 운영체제와 호스트명으로 폴더를 만들기
	
	if (mkdir(uts.sysname, 0755) == -1) PRINT_ERR_EXIT("os");
	if (mkdir(uts.nodename, 0755) == -1) PRINT_ERR_EXIT("host");
	
	cwd = getcwd(NULL, BUFSIZ);

	// 2. 운영체제 폴더 <- sysinfo() 파일

	struct sysinfo info;
	sysinfo(&info);
	
	chdir(uts.sysname); // sysname으로 작업 디렉토리 변경
	
	FILE *osFP;

	if ((osFP = fopen("sysinfo.txt", "w")) == NULL) PRINT_ERR_EXIT("fopen");
	
	fprintf(osFP, "sysinfo()\n");
	fprintf(osFP, "Total usable memory : %lu KB\n", info.totalram / 1024); // KB로 만들어주어야 하므로 1024로 나눈다
	fprintf(osFP, "Available memory : %ld KB\n", (unsigned long)info.freeram / 1024);
	fprintf(osFP, "Now Using memory : %ld KB\n", (unsigned long)info.totalram / 1024 - (unsigned long)info.freeram / 1024);
	fprintf(osFP, "Total Swap space size : %ld KB\n", (unsigned long)info.totalswap / 1024);
	fprintf(osFP, "Swap space still available : %ld KB\n", (unsigned long)info.freeswap / 1024);
	fprintf(osFP, "Now Using swap space : %ld KB\n", (unsigned long)info.totalswap / 1024 - (unsigned long)info.freeswap / 1024);
	 
	// 3. 호스트명 폴더 <- sysconf() 파일

	chdir(cwd);
	chdir(uts.nodename); // nodename으로 작업 디렉토리 변경

	FILE *hostFP;
	
	if ((hostFP = fopen("sysconf.txt", "w")) == NULL) PRINT_ERR_EXIT("fopen");

	fprintf(hostFP, "sysconf()\n");
	fprintf(hostFP, "Max UID process : %ld\n", sysconf(_SC_CHILD_MAX));
	fprintf(hostFP, "Max Open File : %ld\n", sysconf(_SC_OPEN_MAX));
	fprintf(hostFP, "Max UID * Open File : %ld\n", sysconf(_SC_CHILD_MAX) * sysconf(_SC_OPEN_MAX));

	fclose(osFP);
	fclose(hostFP);

	return 0;

}
	
