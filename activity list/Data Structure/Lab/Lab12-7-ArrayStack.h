#include <cstdio>
#include <cstdlib>
#define MAX_STACK_SIZE 10

void error(char* str) {
	printf("%s\n", str);
	exit(1);
}

class ArrayStack {
	int top;
	int data[MAX_STACK_SIZE];
public:
	ArrayStack() { top = -1; }
	~ArrayStack() { }
	bool isEmpty() { return top == -1; }
	bool isFull() { return top == MAX_STACK_SIZE - 1; }
	void push(int val) {
		if (isFull()) error("스택 포화 에러");
		data[++top] = val;
	}
	int pop() {
		if (isEmpty()) error("스택 공백 에러");
		return data[top--];
	}
};