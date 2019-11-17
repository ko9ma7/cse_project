#include "Lab6-5-CustomerNode.h"
#pragma once

class CircularLinkedQueue {
	Node* front;
	Node* rear;
	int count;
public:
	CircularLinkedQueue() : count(0), front(NULL), rear(NULL) { }
	~CircularLinkedQueue() { while(front != rear) delete dequeue(); }
	bool isEmpty() { return front == NULL; }

	void enqueue(Node* next) {
		if (isEmpty()) { // 처음에 비어있을 때 next로 들어온 큐는 자기 자신을 가리키고 있어야 된다
			rear = next;
			front = rear;
			rear = front;
		}
		else {
			// 1. rear가 가리키고 있는 큐의 링크가 next를 가리킨다
			rear->link = next;
			// 2. next가 가리키고 있는 큐의 링크가 front를 가리킨다
			next->link = front;
			// 3. rear가 next를 가리키게 된다
			rear = next;
		}

		count++;
	}

	Node* dequeue() {
		Node* next = front;
		if (count == 0) {
			return NULL;
		} else if (count == 1) { // 큐의 개수가 1이어도 안에서 front와 rear가 연결된다고 가정한다
			// 1. next가 front가 된다
			next = front;
			// 2. front가 front 다음 노드를 가리킨다
			front = front->link;
			// 3. rear는 큐가 한 개가 남아 있으면 NULL값이 된다
			rear->link = NULL;
		} else {
			 // 1. next가 front가 된다
			next = front;
			// 2. front가 front 다음 노드를 가리킨다
			front = front->link;
			// 3. rear가 front를 가리킨다
			rear->link = front;
		}

		count--;
		return next;
	}

	Node* peek() { return front; }

	void display() {
		printf("[CircularLinkedQueue]\n");
		printf("count: %d\n\n", count);

		for (Node* p = front; p != rear; p = p->link)
			p->display();
		rear->display();
		printf("\n");
	}
};