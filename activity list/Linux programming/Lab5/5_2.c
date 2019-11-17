#include <dirent.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <pwd.h>

const char *path = "/proc";

int main(void) {

	pid_t pid = getpid();
	pid_t ppid = getppid();

	printf("[+] Current PID : %d PPID : %d\n", pid, ppid);
        printf("UID	PID     PGID    STATE   CMD\n");

	DIR *dp;
	char *cwd;
	chdir(path);
	cwd = getcwd(NULL, BUFSIZ);

	struct dirent *dent;
	if ((dp = opendir(path)) == NULL) { perror("open"); exit(1); }

	while ((dent = readdir(dp))) { 
		DIR *dp2;
		if (dent->d_ino >= 25) {
			char cmd[20];
			char state[20];
			char tgid[20];
			char pid[20];
			char ppid[20];
			char tracerpid[20];
			char uid[20];
			char gid[20];

			char filename[BUFSIZ];
			sprintf(filename, "/proc/%s/status", dent->d_name);
	
			FILE *proc = fopen(filename, "r");
			char buf[256];

			fscanf(proc,"Name: %s\n",cmd);
         		fscanf(proc, "State: %s\n",state);
       			fgets(buf,256,proc);
         		fscanf(proc, "Tgid: %s\n",tgid);
         		fscanf(proc, "Pid: %s\n",pid);
         		fscanf(proc, "PPid: %s\n",ppid);
         		fscanf(proc, "TracerPid: %s\n",tracerpid);
         		fscanf(proc, "Uid: %s\n",uid);	
			fgets(buf,256,proc);
			fscanf(proc, "GID: %s\n", gid);
			printf("%s	%s	%s	%s	%s\n", getpwuid(atoi(uid))->pw_name, dent->d_name, ppid, state, cmd);
		}
	}

	return 0;

}
