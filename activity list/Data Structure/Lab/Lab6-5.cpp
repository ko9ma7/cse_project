#include "Lab6-5-CircularLinkedQueue_02.h"

void main() {
	CircularLinkedQueue_ver02 que;

	for (int i = 1; i <= 10; i++) que.enqueue(new Node(i));

	que.dequeue();
	que.dequeue();
	que.dequeue();
	que.dequeue();
	que.dequeue();
}