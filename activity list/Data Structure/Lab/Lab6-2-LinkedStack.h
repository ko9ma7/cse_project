#include "Lab6-2-Node.h"
#pragma once

class LinkedStack {
	Node* top;
public:
	LinkedStack() : top(NULL) { }
	~LinkedStack() { while(!isEmpty()) delete pop(); } // 동적 할당된 포인터들을 pop을 하여 빼내면서 주소를 없애준다
	bool isEmpty() { return top == NULL; }

	void push(Node* p) {
		if (isEmpty()) top = p; // 스택이 비어있으면 top은 NULL을 가리키고 있으므로 top이 새로 들어온 p가 가리키고 있는
		                        // 스택을 가리키면 된다
		else {
			p->setLink(top);    // p = top이 아닌 이유는 p = top은 단순히 p가 top이 가리키고 있는 스택의 주소를 가지는 것이다
			                    // p가 가리키고 있는 스택의 링크가 top이 가리키고 있는 스택을 가리키게 해야 하므로
			                    // p->setLink(top)가 되어야 한다
			top = p;            // 그 다음에 top이 p를 가리키면 된다
		}
	}

	Node* pop() {
		if (isEmpty()) return NULL; // 스택이 비어있으면 top이 가리키고 있는 것은 NULL이기 때문에 NULL을 반환
		Node* p = top;              // 새로운 p라는 링크를 생성해서 top이 가리키고 있는 스택을 가리키게 한다
		top = top->getLink();       // top은 그 다음 스택을 가리키게 해주는 getLink함수를 이용해서 주소를 받아온다
		                            // getLink함수에서 반환되는 link는 현재 스택에서 다음 스택을 가리키는 주소를 가지고 있다
		return p;
	}

	Node* peek() { return top; }

	void display() {
		printf("[LinkedStack]\n");
		for (Node* p = top; p != NULL; p = p->getLink()) // p가 헤드 포인터 top부터 시작해서 마지막 NULL포인터를 만날 때까지
			p->display();
		printf("\n");
	}
};

