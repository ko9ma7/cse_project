#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

char* encoding(char*);
int main(int argc, char *argv[]){

	pid_t pid;
	int fd, n;
	char writeTxt[BUFSIZ];
	char readTxt;
	char* newStr;
	
        char pipeName1[20];
	char pipeName2[20];

	int readFlag = 0;  // read에 대한 flag값
	int writeFlag = 0; // write에 대한 flag값

	char whoWrite[10];


	// 하나의 프로그램에서 두 개의 프로세스가 동작해야 하므로 fork 사용
	switch(pid = fork()){
	case -1:
		PRINT_ERR_EXIT("fork");
		break;
	case 0: // Child Process(Only read)
		printf("=== Child Process[%d] Open ===\n", (int)getpid());
		sprintf(pipeName1, "./%s", argv[1]);
	        if((fd = open(pipeName1, O_RDONLY | O_NONBLOCK)) == -1) PRINT_ERR_EXIT("open");	

      	        while(1){
			// 읽어들이기 시작
			readFlag = 0;

			if (strcmp(argv[1], "pipeX") == 0) sprintf(whoWrite, "[X]: ");
			else sprintf(whoWrite, "[Y]: ");

         		while((n = read(fd, &readTxt, 1)) > 0){

            			if(writeFlag == 0){

               				writeFlag = 1;
               				write(1, whoWrite, strlen(whoWrite) + 1);

            			}

            			if(readFlag == 0){
				
					// Lab2-3과 같은 복호화
               				if(readTxt >= 32 && readTxt < 128) write(1, &readTxt, 1);
               				else readFlag = ((int)readTxt % 32);

          	  		} else readFlag--;

         		}
			// 읽어들이기 끝
        		if(writeFlag == 1){

            			writeFlag = 0;
            			write(1, "\n", 1); // 띄어쓰기를 위해서

         		}

      	      }

      	      break;	
	default: // Parent Process(Only write)
		printf("=== Parent Process[%d] Open ===\n", (int)getpid());
		sprintf(pipeName2, "./%s", argv[2]);	
		if((fd = open(pipeName2, O_WRONLY)) == -1) PRINT_ERR_EXIT("open");

      		while(1){

         		fgets(writeTxt, BUFSIZ, stdin);
         		newStr = encoding(writeTxt);
         		write(fd, newStr, strlen(newStr));

      		}
      		break;
   	}

   	return 0;

}

// 암호화
char* encoding(char* old){

	// Lab2-3의 복호화는 offset이 32씩 떨어지는 구조이다.
	// 암호화는 이에 반대로 offset이 32를 제외한 나머지는 랜덤으로 문자를 만들어준다.
	int len = strlen(old);
        int randNum;
	srand(time(NULL));

	// 처음 암호화할 문자열을 만들어주기 위해 암호화할 문자열의 길이만큼 동적할다을 해준다.
        char *new = (char *)malloc(sizeof(char) * (++len));
        char buf[2];

        for(int i = 0; i < strlen(old); i++){

                randNum = rand() % 32; // 32를 제외한 나머지를 랜덤으로 만들어주기 위해 0~31까지 랜덤 숫자를 가져온다.
                new = (char *)realloc(new, sizeof(char) * (++len)); // 새로 만든 문자열에 재할당한다.
                buf[0] = randNum; // buf[0]에 들어가는 랜덤 값이 나중에 복호화할 때 사용될 숫자이다.
                strcat(new, buf); // new의 가장 뒤에 buf를 붙여준다.

                while(randNum--){
                        new = (char *)realloc(new, sizeof(char) * (++len)); // 새로 만든 문자열에 재할당한다.
                        buf[0] = 1 + rand() % 127; // Lab 2-3에서 주어진 조건과 같이 아스키코드 32부터 127까지이므로
                        strcat(new, buf);
                }

                buf[0] = old[i];
                strcat(new, buf);
        }

        return new;

}
