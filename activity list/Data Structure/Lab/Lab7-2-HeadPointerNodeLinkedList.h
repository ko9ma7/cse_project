// 단순 연결 리스트 클래스(헤드 포인터 노드)
#include "Lab6-2-Node.h"

class HeadPointerNodeLinkedList {
	Node* head; // 헤드 포인터 노드
public:
	HeadPointerNodeLinkedList() { head = NULL;} // 생성자(꼭 넣어주어야 한다)
	~HeadPointerNodeLinkedList() { clear(); } // 소멸자

	// 삭제 함수
	void clear() { while(!isEmpty()) delete remove(0); }

	// 헤드 포인터 getter 함수
	Node* getHead() { return head; }

	// 리스트에 비어있는지 아닌지 확인하는 함수
	bool isEmpty() { return getHead() == NULL; }


	// pos번째의 노드를 가리키는 값(주소) 반환
	Node* getEntry(int pos) {
		Node* p = head;
		for (int i = 0; i < pos; i++, p = p->getLink())
			if (p == NULL) break;

		return p;
	}

	// pos번째의 항목을 삽입
	void insert(int pos, Node* n) {
		if(pos == 0) { // pos가 0번째 위치에 있을 경우
			if(isEmpty()) { // 리스트가 비어 있을 때(head가 NULL을 가리키고 있을 때)
				// 1. head가 가리키는 곳을 n이 가리킨다
				n->setLink(head);
				// 2. head가 n을 가리킨다
				head = n;
			}
			else { // 리스트가 비어있지 않을 때
				// 1. n이 가리키는 노드의 링크가 head가 가리키는 노드를 가리킨다
				n->setLink(head);

				// 2. head가 n이 가리키는 노드를 가리킨다
				head = n;
			}
		} else { // pos가 0번째 위치에 있지 않을 경우
			Node* prev = getEntry(pos-1);

			if(prev!= NULL) prev -> insertNext(n);
		}
	}
	/*
	   insert 함수 ver2
	void insert(int pos, Node* n) {
		if (pos == 0) {
			if (isEmpty()) {
				org = n;
			} else {
				n->setLink(org);
				org = n;
			}
		} else {
			Node* prev = getEntry(pos - 1);
			if (prev != NULL) prev->insertNext(n);
		}
	}
	*/
	// pos번째의 항목을 삭제
	Node* remove(int pos) {
		if (pos == 0) { // pos가 0번째 위치에 있을 경우
			Node* n = head; // n이 head가 가리키고 있는 곳을 가리킨다

			if (n != NULL) {
				// n은 이미 삭제할 노드를 가리키고 있으므로 head가 가리키고 있는 노드의 다음 값을 가리키면 된다
				head = n->getLink(); // head는 그 자체가 포인터 링크이므로 setLink() 함수를 사용할 수 없다

			    return n;
			} else return NULL;

		} else { // pos가 0번째 위치에 있지 않을 경우
			Node* prev = getEntry(pos - 1);

			if (prev != NULL) return prev->removeNext();
			else return NULL;
		}
	}
	/*
	   remove 함수 ver2
	Node* remove(int pos) {
		if (pos == 0) {
			if (isEmpty()) {
				return NULL;
			} else {
				Node* removed = head;
				head = removed->getLink();
				return removed;
			}
		} else {
			Node* prev = getEntry(pos - 1);
			if (prev != NULL) return prev->removeNext();
			return NULL;
		}
	}
	*/
	// val의 값이 어느 위치에 있는지 확인하는 함수
	Node* find(double val) {
		for (Node* p = getHead(); p != NULL; p = p->getLink()) {
			if (p->hasData(val)) {
				printf("값을 찾았습니다!\n");
				return p;
			}
		}
		printf("값을 찾지 못했습니다\n");
		return NULL;
	}

	// 리스트의 pos번째 위치의 항목을 교환
	void replace(int pos, Node* n) {
		Node* prev = getEntry(pos - 1);

		if (prev != NULL) {
			printf("교체 하겠습니다\n");
		    delete prev->removeNext();
		    prev->insertNext(n);
		} else return;
	}

	// 리스트 항목의 개수를 반환
	int size() {
		 int count = 0;

		 for (Node* p = getHead(); p != NULL; p = p->getLink()) count++;

		 return count;
	 }

	// 리스트 출력
	void display() {
		printf("[전체 항목 수 = %2d] : ", size());

		for (Node* p = head; p != NULL; p = p->getLink())
			p->display();
		printf("\n");
	}
};