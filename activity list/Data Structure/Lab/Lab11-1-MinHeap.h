#pragma once;
#include "Lab11-1-HeapNode.h"
#define MAX_ELEMENT 200

class MinHeap {
	HeapNode node[MAX_ELEMENT];
	int size;
public:
	MinHeap() : size(0) { }
	bool isEmpty() { return size == 0; }
	bool isFull() { return size == MAX_ELEMENT - 1; }

	HeapNode& getParent(int i) { return node[i / 2]; }
	HeapNode& getLeft(int i) { return node[i * 2]; }
	HeapNode& getRight(int i) { return node[i * 2 + 1]; }

	void insert(int key) {
		if (isFull()) return;
		int i = ++size;
		while (i != 1 && key < getParent(i).getKey()) {
			node[i] = getParent(i);
			i /= 2;
		}
		node[i].setKey(key);
	}

	HeapNode remove() {
		if (isEmpty()) error();
		HeapNode item = node[1];
		HeapNode last = node[size--];
		int parent = 1;
		int child = 2;
		while (child <= size) {
			/*
			   현재 노드의 자식 노드 중 더 작은 노드를 찾는다. child는 더 작은 자식 노드의 인덱스
			   왼쪽 자식 노드보다 오른쪽 자식 노드가 더 작으면 오른쪽 자식 노드의 인덱스는 홀수이므로
			   child의 값을 1씩 증가시켜준다.
			*/
			if (child < size && getLeft(parent).getKey() > getRight(parent).getKey()) child++;
            // 마지막 노드가 더 작은 자식 노드보다 키 값이 작으면 이동이 완료
 			if (last.getKey() <= node[child].getKey()) break;

			node[parent] = node[child];
			parent = child;
			child *= 2;
		}
		node[parent] = last;
		return item;
	}

	HeapNode find(int key) { return node[1]; }

	void display() {
		for (int i = 1, level = 1; i <= size; i++) {
			if (i == level) {
				printf("\n");
				level *= 2;
			}
			node[i].display();
		}
		printf("\n-------------------------------------");
	}
};
// 최소 힙 정렬(오름차순)
// ex) 오름차순: 1, 2, 3, 4, 5
void MinHeapAscendingSort(int a[], int n) {
	MinHeap h;
	for (int i = 0; i < n; i++) h.insert(a[i]);
	// 최소 힙에서는 삭제시 가장 작은 값이 반환되므로
	// 오름차순으로 정렬은 그대로 쓰면 됨
	for (int i = 0; i < n; i++) a[i] = (h.remove()).getKey();
}
// 최소 힙 정렬(내림차순)
// ex) 내림차순: 5, 4, 3, 2, 1
void MinHeapDescendingSort(int a[], int n) {
	MinHeap h;
	for (int i = 0; i < n; i++) h.insert(a[i]);
	// 최소 힙에서는 삭제시 가장 작은 값이 반환되므로
	// 내림차순으로 정렬하기 위한 삭제한 항목을 배열의 끝부터 앞으로 채워 나감
	for (int i = n - 1; i >= 0; i--) a[i] = (h.remove()).getKey();
}