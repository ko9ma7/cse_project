#include <sys/mman.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

#define CHECK_MMAP_SUCCESS(_addr)\
if(_addr == MAP_FAILED) {perror("mmap"); exit(1);}
#define MAX_VOCA_SIZE 3000
#define DRAW_VOCA_SIZE 100
#define random() rand() % 3000

static char* drawVoca[DRAW_VOCA_SIZE];
static int score = 0;
void RandomShuffleAnotherFIle(char* readFileName, char* drawVoca[]);
void printDrawVoca();
void addr2prep(char* s, char* add);
void IsEqual(char* add);
int main(int argc, char *argv[]) {
	 
	int fd, fd2;
	char* addr;
	char* addr2;
	
	if ((fd = open("word/IsStart.txt", O_RDWR | O_CREAT)) == -1) {	perror("open"); exit(1);}
	addr = mmap(NULL, getpagesize(), PROT_READ | PROT_WRITE, MAP_SHARED, fd, (off_t)0);
	close(fd);

	if ((fd2 = open("word/DuringGame.txt", O_RDWR)) == -1) { perror("open"); exit(1);}
	addr2 = mmap(NULL, getpagesize(), PROT_READ | PROT_WRITE, MAP_SHARED, fd2, (off_t)0);
	close(fd2);
	RandomShuffleAnotherFIle("word/word.txt", drawVoca);
	while(1) {
		if(addr[0] == 'y') break;
	}

	while(1) {
		printf("\033c");
		printf("===============================================================\n");
		printDrawVoca();
		printf("\nscore = %d\n",score);
		sleep(1);
		//addr2prep(s, addr2);
		IsEqual(addr2);
		if(score == 100) break;
	}

	printf("good!\n");

	return 0;
}
void IsEqual(char* add) {
	char s[16];
	addr2prep(s, add);
	for(int i = 0; i < 100; i++) {
		if(strcmp(drawVoca[i],s) == 0) {
			drawVoca[i] = "           ";
			score++;
		}
	}
	for(int i = 0; i < 15; i++) add[i] = '?';
	msync(add, getpagesize(), MS_SYNC);
}

void addr2prep(char* s, char* add) {
	for(int i = 0; i < 15; i++) {
		if(add[i] == '?') {
			s[i] = '\0';
			break;
		}
		s[i] = add[i];
	}
	for(int i = 0; i < 15; i++) add[i] = '?';
	msync(add, getpagesize(), MS_SYNC);
}

void printDrawVoca() {
	for(int i = 0; i < 100; i++) {
		if(i != 0 && i % 4 == 0) printf("\n");
		printf("%15s ",drawVoca[i]);
	}
}

void RandomShuffleAnotherFIle(char* readFileName, char* drawVoca[]) {
	FILE *rfp;
	char buf[BUFSIZ];
	char* maxVoca[MAX_VOCA_SIZE];
	srand(time(NULL));
	int i = 0, l = 0;
	if ((rfp = fopen(readFileName, "r")) == NULL) { perror("rfp_fopen"); exit(1);}
	while(fgets(buf,BUFSIZ,rfp) != NULL) {
		l = strlen(buf);
  		for(int i = l; i >= 0; i--) {
  			if(buf[i] == '\n') {
  				buf[i] = '\0';
  				break;
  			}
  		}
		char* vocastr = (char*)malloc(sizeof(char)*(l+1));
		strcpy(vocastr,buf);
		maxVoca[i] = vocastr;
		i++; 
	}
	for(int j = 0; j < DRAW_VOCA_SIZE; j++) {
		char* s = maxVoca[random()];
		l = strlen(s);
		char* drawvocastr = (char*)malloc(sizeof(char)*(l+1));
		strcpy(drawvocastr,s);
		drawVoca[j] = drawvocastr;
	}
	
	fclose(rfp);

	for(int i = 0; i <MAX_VOCA_SIZE; i++) free(maxVoca[i]);
}

