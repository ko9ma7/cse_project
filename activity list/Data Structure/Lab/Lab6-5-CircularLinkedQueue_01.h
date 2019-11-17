#include "Lab6-2-Node.h"
#pragma once

/*
   ver_01

   연결리스트로 원형 큐를 구현하려고 생각을 많이 해보았다.
   생각 끝에 내린 결론은 기존에 원형 큐에선 들어오는 배열의 값을
   전단과 후단에서 각각 삭제 및 삽입이 이루어지기 때문에
   front와 rear라는 변수를 사용했었지만 연결리스트로 구현하고자
   하는 원형 큐의 경우는 그 안에 있는 노드들이 결국 헤드포인터와
   끝에 있는 마지막 포인터가 연결되기 때문에 변수를 하나만 생각해도 된다.
*/

class CircularLinkedQueue_ver01 {
	int count; // 원형 큐에 들어가 있는 큐의 개수
	Node* front;
public:
	CircularLinkedQueue_ver01() : count(0), front(NULL) { }
	~CircularLinkedQueue_ver01() { while(!isEmpty()) delete dequeue(); }
	bool isEmpty() { return front == NULL; }

	void enqueue(Node* next) {
		if (isEmpty() || count == 0) front = next; // 큐에 비어있으면 front가 next를 가리키면 된다
		else {
			// 1. front가 가리키고 있는 큐의 링크 주소를 임시 저장
			Node** temp = &(front->link);
			// 2. front가 가리키고 있는 큐의 링크가 next를 가리키게 한다
			front->link = next;
			// 3. next가 가리키고 있는 큐의 링크가 temp가 된다
			next->link = *temp;
		}

		count++; // 큐에 하나가 늘어났으므로 count를 증가
	}

	Node* dequeue() {
		if (isEmpty() || count == 0) return NULL;

		Node* next;
		Node* temp;

		if (count == 1) { // 만약 큐에 하나만 있을 경우
			next = front;
			front = NULL;
		} else { // 큐가 2개 이상인 경우
			// 1. front가 가리키고 있는 큐를 빼기 위해 next로 지정
			next = front->link;
			// 2. next가 가리키고 있는 큐의 링크를 임시 저장
			temp = next->link;
			// 3. front가 임시 저장된 temp가 된다
			front = temp;
		}

		return next;
		count--; // 큐가 하나 빠지므로 count 감소
	}

	Node* peek() { return front;}

	void display() {
		printf("[CircularLinkedQueue]\n");
		for (int i = 0; i < count; i++) front->display();
		printf("\n");
	}
};