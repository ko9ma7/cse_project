#include <sys/mman.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }
#define CHECK_MMAP_SUCCESS(_addr) if (_addr == MAP_FAILED) { perror("mmap"); exit(1); }

int main(int argc, char **argv) {
	int fd;
	char* addr;
	char fileName[255] = "word.txt";
	
	if ((fd = open(fileName, O_RDWR)) == -1) { perror("open"); exit(1); }

	addr = mmap(NULL, getpagesize(), PROT_READ | PROT_WRITE, MAP_SHARED, fd, (off_t)0);
	CHECK_MMAP_SUCCESS(addr);
	close(fd);

	char YOrN;
	printf("Are you want to start? [y/n] "); scanf("%c", &YOrN);

	if (YOrN == 'y') {
	
		while (1) {

			char answer[20];
			printf("Print answer!\n"); scanf("%s", answer);

			if (!strcmp(answer, addr)) addr = " ";	

		}

	} 
	
	return 0;

}
