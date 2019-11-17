#include <cstdio>
#include <cstdlib>
#define MAX 20

inline void error(char *message) {
	printf("%s\n", message);
	exit(1);
}

class ArrayStack {
	char data[MAX]; // 요소의 배열
	int top;       // 요소의 개수
public:
	ArrayStack() { top = -1; }
	~ArrayStack() {}
	bool isEmpty() { return top == -1; }
	bool isFull() { return top == MAX - 1; }

	void push(int e) { // 맨 위에 항목 삽입
		if(isFull())
			error("스택 포화 에러");

		data[++top] = e;
	}

	char pop() {        // 맨 위에 요소 삭제 후 반환
		if(isEmpty())
			error("스택 공백 에러");

		return data[top--];
	}

	char peek() {       // 삭제하지 않고 요소 반환
		if(isEmpty())
			error("스택 공백 에러");

		return data[top];
	}

	void display() {   // 스택 내용 화면 출력
		printf("[스택 항목의 수 = %2d] ==> ", top + 1);
		for (int i = 0; i <= top; i++)
			printf("<%c> ", data[i]);
		printf("\n");
	}
};