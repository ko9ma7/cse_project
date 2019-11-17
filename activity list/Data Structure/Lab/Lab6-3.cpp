#include "Lab6-3-StudentQueue.h"

void main() {
	StudentQueue que;
	que.enqueue(new Node(2014136124, "지명화", "컴퓨터공학과"));
	que.enqueue(new Node(2014123453, "박경화", "전자공학과"));
	que.display();

	Node* node = que.dequeue();
	printf("[pop항목]\n");
	node->display();
	printf("\n");
	delete node;
	que.display();
}
