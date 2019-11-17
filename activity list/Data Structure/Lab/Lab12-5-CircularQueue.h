#include <cstdio>
#include <cstdlib>
#define MAX_QUEUE_SIZE 20

void error(char* s) {
	printf("%s\n");
	exit(1);
}

class CircularQueue {
	int front;
	int rear;
	int data[MAX_QUEUE_SIZE];
public:
	CircularQueue() { front = rear = 0; }
	bool isEmpty() { return front == rear; }
	bool isFull() { return front == (rear + 1) % MAX_QUEUE_SIZE; }
	void enqueue(int val) {
		if (isFull()) error("스택 포화 에러");
		rear = (rear + 1) % MAX_QUEUE_SIZE;
		data[rear] = val;
	}
	int dequeue() {
		if (isEmpty()) error("스택 공백 에러");
		front = (front + 1) % MAX_QUEUE_SIZE;
		return data[front];
	}
};