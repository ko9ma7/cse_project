#include "Lab5-1-Customer.h"
#include <cstdio>
#include <cstdlib>
#define MAX_QUEUE_SIZE 100

inline void error(char *s) {
	printf("%s\n", s);
	exit(1);
}

class CustomerQueue {
protected:
	int front;
	int rear;
	Customer data[MAX_QUEUE_SIZE];
public:
	CustomerQueue() { front = rear = 0; }
	bool isEmpty() { return front == rear; }
	bool isFull() { return (rear + 1) % MAX_QUEUE_SIZE == front; }

	void enqueue(Customer val) {
		if (isFull()) error("스택 포화 에러");
		else {
			rear = (rear + 1) % MAX_QUEUE_SIZE;
			data[rear] = val;
		}
	}

	Customer dequeue() {
		if (isEmpty()) error("스택 공백 에러");
		else front = (front + 1) % MAX_QUEUE_SIZE;

		return data[front];
	}

	Customer peek() {
		if (isEmpty()) error("스택 공백 에러");

		return data[(front + 1) % MAX_QUEUE_SIZE];
	}
};