#pragma once;
#include "Lab11-1-HeapNode.h"
#define MAX_ELEMENT 200

class MaxHeap {
	HeapNode node[MAX_ELEMENT]; // 요소의 배열
	int size; // 힙의 현재 요소의 개수
public:
	MaxHeap() : size(0) { }
	bool isEmpty() { return size == 0; }
	bool isFull() { return size == MAX_ELEMENT - 1; }

	HeapNode& getParent(int i) { return node[i / 2]; }    // 인덱스 i의 부모 노드를 구하려면 i의 2를 나눈 인덱스가 부모 노드가 된다
	HeapNode& getLeft(int i) { return node[i * 2]; }      // 인덱스 i의 왼쪽 자식 노드를 구하려면 i에 2를 곱한 인덱스가 왼쪽 자식 노드가 된다
	HeapNode& getRight(int i) { return node[i * 2 + 1]; } // 인덱스 i의 오른쪽 자식 노드를 구하려면 i에 2를 곱하고 1을 더한 인덱스가 오른쪽 자식 노드가 된다
	// 우선 말단 노드부터 위로 하나씩 부모 노드와 비교하면서 올라간다
	void insert(int key) {
		if (isFull()) return;
		int i = ++size; // 증가된 힙 크기 위치에서 시작

		// 트리를 거슬러 올라가면서 부모 노드와 비교하는 과정
		while (i != 1 && key > getParent(i).getKey()) {
			node[i] = getParent(i);
			i /= 2;
		}
		node[i].setKey(key);
	}
	// 값이 가장 큰 노드를 삭제(루트 노드 삭제)
	HeapNode remove() {
		if (isEmpty()) error();
		HeapNode item = node[1];      // 루트노드(꺼낼 노드 = 삭제할 노드)를 item이라는 변수에 저장
		HeapNode last = node[size--]; // 힙의 마지막 노드
		int parent = 1;               // 루트 노드의 위치
		int child = 2;                // 루트 노드의 왼쪽 자식 위치

		while (child <= size) {       // 힙 트리를 벗어나지 않는 동안
			/*
			   현재 노드의 자식 노드 중 더 큰 자식 노드를 찾는다.
			   왼쪽 자식 노드와 오른쪽 자식 노드를 비교했을 때, 오른쪽 자식 노드가 더 크다면 1씩 증가해준다.
			   왼쪽 자식 노드의 인덱스는 2, 4, 6, 8과 같은 짝수이지만
			   오른쪽 자식 노드의 인덱스는 3, 5, 7, 9와 같은 홀수이기 때문이다.
			*/
			if (child < size && getLeft(parent).getKey() < getRight(parent).getKey()) child++; // child : 더 큰 자식 노드의 인덱스
			// 마지막 노드의 키값이 자식 노드의 키값보다 같거나 크다면 더이상 아래로 내려올 필요가 없으므로 반복문 탈출
			if (last.getKey() >= node[child].getKey()) break;

			// 한 단계 아래로 이동
			node[parent] = node[child]; // 부모 노드를 자식 노드의 값으로 바꿔준다
			parent = child;             // 바꾼 자식 노드의 위치가 곧 다음 반복문에서의 부모 노드의 위치
			child *= 2;                 // 왼쪽 자식 노드의 인덱스는 2배씩 증가한다
		}
		node[parent] = last; // 마지막 노드를 최종 위치에 저장
		return item;
	}
	HeapNode find() { return node[1]; } // 루트 노드를 반환(루트 노드에 대응되는 배열의 인덱스는 1이다. 배열은 0을 사용하지 않는다)

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
// 최대 힙 정렬 함수(오름차순)
// ex) 오름차순: 1, 2, 3, 4, 5
void MaxHeapAscendingSort(int a[], int n) {
	MaxHeap h;

	for (int i = 0; i < n; i++) h.insert(a[i]);
	// 최대 힙에서는 삭제시 가장 큰 값이 반환되므로
	// 오름차순으로 정렬하기 위한 삭제한 항목을 배열의 끝부터 앞으로 채워 나감
	for (int i = n - 1; i >= 0; i--) a[i] = (h.remove()).getKey();
}
// 최대 힙 정렬 함수(내림차순)
// ex) 내림차순: 5, 4, 3, 2, 1
void MaxHeapDescendingSort(int a[], int n) {
	MaxHeap h;

	for (int i = 0; i < n; i++) h.insert(a[i]);
	// 최대 힙에서는 삭제시 가장 큰 값이 반환되므로
	// 내림차순으로 정렬은 그대로 쓰면 됨
	for (int i = 0; i < n; i++) a[i] = (h.remove()).getKey();
}