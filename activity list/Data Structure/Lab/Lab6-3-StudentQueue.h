#include "Lab6-2-Node.h"

class StudentQueue {
	Node* front;
	Node* rear;
public:
	StudentQueue() : front(NULL), rear(NULL) {}
	~StudentQueue() { while(!isEmpty()) delete dequeue(); }

	bool isEmpty() { return front == NULL; }

	void enqueue(Node* p) {
		if (isEmpty()) front = rear = p;
		else {
			rear->setLink(p);
			rear = p;
		}
	}

	Node* dequeue() {
		if (isEmpty()) return NULL;
		Node* p = front;
		front = front->getLink();
		if (front == NULL) rear = NULL;
		return p;
	}

	Node* peek() { return front; }

	void display() {
		printf("[전체 학생 정보]\n");
		for (Node* p = front; p != NULL; p = p->getLink()) p->display();
		printf("\n");
	}
};