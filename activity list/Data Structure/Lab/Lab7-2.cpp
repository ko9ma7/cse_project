#include "Lab7-2-HeadPointerNodeLinkedList.h"

void main() {
	HeadPointerNodeLinkedList list;
	list.insert(0, new Node(2.3));
	list.insert(1, new Node(3.4));
	list.insert(2, new Node(4.5));
	list.insert(3, new Node(5.6));
	printf("입력 완료\n\n");
	printf("현재 리스트 안에 항목의 개수: %d\n\n", list.size());
	list.display();

	list.remove(2);
	list.remove(1);
	printf("제거 완료\n\n");
	printf("현재 리스트 안에 항목의 개수: %d\n", list.size());
	list.display();

	printf("찾고자 하는 값: 5.6, 값의 위치: %x\n", list.find(5.6));
	printf("검색 완료\n\n");
	list.display();

	list.replace(0, new Node(6.7));
	printf("교체 완료\n\n");
	list.display();
}