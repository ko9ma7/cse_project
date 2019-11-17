#include <sys/msg.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

// 메시지를 담고 있는 버퍼
struct msgbuf {
        long mtype;
        char mtext[80];
};

int main(int argc, char** argv) {

        key_t key;
        pid_t pid;
        struct msgbuf message;
        int msgid;
        int userA, userB;
        char buf[80];

        userA = atoi(argv[1]);
        userB = atoi(argv[2]);

        printf("IPC start!\n");

        // IPC 통신 1단계 - Key 생성
        key = ftok("messgeFile2", 1);

        // IPC 통신 2단계 - 객체 생성
        msgid = msgget(key, IPC_CREAT | 0644);

        // 한 프로그램에서 두 개의 프로세스를 생성하여 통신하기 위해 fork 사용
        switch (pid = fork()) {
        case -1:
                PRINT_ERR_EXIT("fork");
                break;
        case 0: // Child Process(쓰기만)
                while (1) {

                        // IPC 통신 3단계 - 메시지 통신(자신의 타입)
                        msgrcv(msgid, &message, 80, userA, 0);

                        // 표준 입력으로 메시지 작성하기
                        write(1, "[other] :", 10);
                        write(1, message.mtext, strlen(message.mtext));

                }
                break;
        default: // Parent Process(읽기만)
                // 메시지를 보내기 전 userB 타입으로 바꿔준다
                message.mtype = userB;

                while (1) {

                        // 표준 출력으로 메시지 읽어오기
                        read(2, buf, 80);
                        strcpy(message.mtext, buf);
                        msgsnd(msgid, (void *)&message, 80, IPC_NOWAIT);

                }
                break;
        }

        return 0;

}
