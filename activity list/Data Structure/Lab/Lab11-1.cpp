#pragma once;
#include "Lab11-1-MaxHeap.h"
#include "Lab11-1-MinHeap.h"
#include <ctime>

void main() {
	/*
	MaxHeap heap;

	heap.insert(9); heap.insert(7);
	heap.insert(6); heap.insert(5);
	heap.insert(4); heap.insert(3);
	heap.insert(2); heap.insert(2);
	heap.insert(1); heap.insert(3);
	heap.display();

	heap.remove(); heap.display();
	heap.remove(); heap.display();
	printf("\n");
	*/

	////////////////////////////////////////////////////////////////
	/*
	int data1[20];

	srand((unsigned int)time(NULL));

	// 랜덤으로 데이터 생성
	for (int i = 0; i < 20; i++) data1[i] = rand() % 100; // 0 ~ 99

	// 정렬 전 결과 출력
	printf("\n최대 힙 정렬 전\t\t : ");
	for (int i = 0; i < 20; i++) printf("[%2d] ", data1[i]);

	// 힙 정렬(오름차순)
	MaxHeapAscendingSort(data1, 20);

	// 정렬 후 결과 출력
	printf("\n최대 힙 정렬 후(오름차순): ");
	for (int i = 0; i < 20; i++) printf("[%2d] ", data1[i]);

	// 힙 정렬(내림차순)
	MaxHeapDescendingSort(data1, 20);

	// 정렬 후 결과 출력
	printf("\n최대 힙 정렬 후(내림차순): ");
	for (int i = 0; i < 20; i++) printf("[%2d] ", data1[i]);
	printf("\n\n");

	////////////////////////////////////////////////////////////////

	int data2[20];

	// 랜덤으로 데이터 생성
	for (int i = 0; i < 20; i++) data2[i] = rand() % 100; // 0 ~ 99

	// 정렬 전 결과 출력
	printf("\n최소 힙 정렬 전\t\t : ");
	for (int i = 0; i < 20; i++) printf("[%2d] ", data2[i]);

	// 힙 정렬(오름차순)
	MinHeapAscendingSort(data2, 20);

	// 정렬 후 결과 출력
	printf("\n최소 힙 정렬 후(오름차순): ");
	for (int i = 0; i < 20; i++) printf("[%2d] ", data2[i]);

	// 힙 정렬(내림차순)
	MinHeapDescendingSort(data2, 20);

	// 정렬 후 결과 출력
	printf("\n최소 힙 정렬 후(내림차순): ");
	for (int i = 0; i < 20; i++) printf("[%2d] ", data2[i]);
	printf("\n\n");
	*/
	/*
	MinHeap heap;
	heap.insert(2); heap.insert(5);
	heap.insert(6); heap.insert(8);
	heap.insert(9); heap.insert(10);
	heap.display();
	heap.remove();
	heap.display();
	heap.insert(7);
	heap.display();
	*/
	/*
	MinHeap heap;
	heap.insert(3); heap.insert(6); heap.insert(7); heap.insert(12);
	heap.insert(13); heap.insert(15); heap.insert(20);
	heap.display();
	heap.insert(2);
	heap.display();
	heap.remove();
	heap.display();
	*/
	MaxHeap heap;
	heap.insert(10); heap.insert(40); heap.insert(30); heap.insert(5);
	heap.insert(12); heap.insert(6); heap.insert(15); heap.insert(9); heap.insert(60);
	heap.display();
}