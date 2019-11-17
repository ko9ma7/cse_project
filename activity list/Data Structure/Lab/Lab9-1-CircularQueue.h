#include "Lab9-1-BinaryNode.h"
#include <cstdlib>
#define MAX_QUEUE_SIZE 100

void error(char* str) {
	printf("%s\n", str);
	exit(1);
}

class CircularQueue {
	int front;
	int rear;
	BinaryNode* data[MAX_QUEUE_SIZE];
public:
	CircularQueue() { front = rear = 0; }
	~CircularQueue() { }
	bool isEmpty() { return front == rear; }
	bool isFull() { return front % MAX_QUEUE_SIZE == (rear + 1) % MAX_QUEUE_SIZE; }

	void enqueue(BinaryNode* node) {
		if (isFull()) error("큐 포화 상태");
		else {
			rear = (rear + 1) % MAX_QUEUE_SIZE;
			data[rear] = node;
		}
	}

	BinaryNode* dequeue() {
		if (isEmpty()) error("큐 공백 상태");
		else {
			front = (front + 1) % MAX_QUEUE_SIZE;
			return data[front];
		}
	}
};