#include "Lab7-1-HeadNodeLinkedList.h"

void main() {
	LinkedList list;
	list.insert(0, new Node(2.1));
	list.insert(1, new Node(3.2));
	list.insert(2, new Node(4.5));
	list.insert(3, new Node(5.6));
	list.insert(list.size(), new Node(8.9));
	list.display();
}