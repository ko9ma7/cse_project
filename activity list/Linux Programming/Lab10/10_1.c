#include <sys/msg.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }

// 메시지를 담고 있는 버퍼 구조체
struct msgbuf {
        long mtype;
        char mtext[80];
};

int main(int argc, char** argv) {

        key_t key;
        struct msgbuf message;
        int msgid;

        // 명령어가 전송일 경우
        if (strcmp(argv[1], "-s") == 0) {

                // IPC 통신 1단계 - Key 생성
                key = ftok("messageFile1", 1);

                // IPC 통신 2단계 - 객체 생성
                msgid = msgget(key, IPC_CREAT | 0644);
                if (msgid == -1) PRINT_ERR_EXIT("msgget");
                message.mtype = 1;

                // IPC 통신 3단계 - 메시지 전달
                strcpy(message.mtext, argv[2]);
                if (msgsnd(msgid, (void *)&message, 80, IPC_NOWAIT) == -1) PRINT_ERR_EXIT("msgsnd");

        }

        // 명령어가 삭제일 경우
        else if (strcmp(argv[1], "-r") == 0) {

                key = ftok("messageFile", 1);
                msgid = msgget(key, IPC_CREAT | 0644);
                if (msgid == -1) PRINT_ERR_EXIT("msgget");

                // 저금통 안에 있는 모든 메시지를 받아옴
                while (msgrcv(msgid, &message, 80, 0, IPC_NOWAIT) != -1) { printf("%s\n", message.mtext); }
                // 메시지 삭제
                msgctl(msgid, IPC_RMID, (struct msqid_ds *)NULL);

                printf("Remove All\n");

        }

        return 0;

}
