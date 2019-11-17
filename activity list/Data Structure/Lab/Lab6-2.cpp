#include "Lab6-2-LinkedStack.h"

void main() {
	LinkedStack stack;
	stack.push(new Node(2014136124, "지명화", "컴퓨터공학과"));
	stack.push(new Node(2014230504, "박경화", "전자공학과"));

	stack.display();

	Node* node = stack.pop();
	printf("[pop항목]\n");
	node->display();
	printf("\n");
	delete node;
	stack.display();
}