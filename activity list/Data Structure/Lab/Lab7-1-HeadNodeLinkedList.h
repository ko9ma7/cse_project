// 단순 연결 리스트 클래스(헤드 노드)
#include "Lab6-2-Node.h"

class LinkedList {
	Node org; // 헤드 노드
public:
	LinkedList() : org(0) { } // Node의 생성자에서 link의 값이 NULL로 초기화 되므로 그냥 두면 된다
	~LinkedList() { clear(); }
	void clear() { while (!isEmpty()) delete remove(0); } // 0번 위치(헤드 노드 다음이 실질적인 노드의 시작)에서 계속 삭제
	Node* getHead() { return org.getLink(); } // 헤드 노드의 링크 값을 반환(헤드 노드가 다음 노드를 가리키는 링크의 값)
	bool isEmpty() { return getHead() == NULL; } // 헤드 노드의 링크가 NULL이면 비어 있음

	// pos번째 노드의 주소를 반환함
	// pos가 0인 노드가 리스트의 첫 번째 노드이므로, getEntry(0)와 getHead()는 동일한 값을 반환
	// getEntry(-1)이 의도적으로 NULL이 아니라 헤드 노드의 주소를 반환하도록 설계
	Node* getEntry(int pos) {
		Node* n = &org;
		for (int i = -1; i < pos; i++, n = n->getLink()) // 헤드 노드가 -1 위치에 있다는 것을 생각하자
			if (n == NULL)
				break;
		return n;
	}
	// 리스트의 pos번째 위치에 n을 삽입(ex: 3번째 위치를 넣으면 3번째 위치의 노드를 가리키는 주소 반환
	void insert(int pos, Node* n) {
		Node* prev = getEntry(pos - 1);

		if (prev != NULL) prev->insertNext(n);
	}
	// 리스트의 pos번째 위치의 항목 삭제
	Node* remove(int pos) {
		Node* prev = getEntry(pos - 1);

		if (prev != NULL) return prev->removeNext();
		else return NULL;
	}
	// 탐색 함수
	 Node* find(double val) {
		 for (Node *p = getHead(); p != NULL; p = p->getLink())
			 if (p->hasData(val)) return p;
		 return NULL;
	 }
	 // 리스트의 pos번째 위치에 항목과 대체
	 void replace(int pos, Node* n) {
		 Node* prev = getEntry(pos - 1);

		 if (prev != NULL) {
			 delete prev->removeNext();
			 prev->insertNext(n);
		 }
	 }
	 // 리스트 항목 개수를 반환
	 int size() {
		 int count = 0;

		 for (Node* p = getHead(); p != NULL; p = p->getLink()) count++;

		 return count;
	 }

	 void display() {
		 printf("[전체 항목 수 = %2d] : ", size());
		 for (Node* p = getHead(); p != NULL; p = p->getLink()) p->display();
		 printf("\n");
	 }
};