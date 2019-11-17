#include <queue>
#include <functional>
#include <cstdlib>
#include <ctime>
using namespace std;

// STL의 우선순위 큐를 이용한 오름차순 정렬(최대 힙 사용)
void max_HeapSortInc(int a[], int n) {
	priority_queue<int> maxHeap;

	for (int i = 0; i < n; i++) maxHeap.push(a[i]);
	// 오름차순으로 정렬하기 위한 삭제한 항목을 배열의 끝부터 앞으로 채워 나감
	for (int i = n - 1; i >= 0; i--) {
		a[i] = maxHeap.top();
		maxHeap.pop();
	}
}
// STL의 우선순위 큐를 이용한 내림차순 정렬(최대 힙 사용)
void max_HeapSortDec(int a[], int n) {
	priority_queue<int> maxHeap;

	for (int i = 0; i < n; i++) maxHeap.push(a[i]);

	for (int i = 0; i < n; i++) {
		a[i] = maxHeap.top();
		maxHeap.pop();
	}
}
///////////////////////////////////////////////////////////////////
// STL의 우선순위 큐를 이용한 오름차순 정렬(최소 힙 사용)
void min_HeapSortInc(int a[], int n) {
	priority_queue<int, vector<int>, greater<int>> minHeap;

	for (int i = 0; i < n; i++) minHeap.push(a[i]);

	for (int i = 0; i < n; i++) {
		a[i] = minHeap.top();
		minHeap.pop();
	}
}
// STL의 우선순위 큐를 이용한 내림차순 정렬(최소 힙 사용)
void min_HeapSortDec(int a[], int n) {
	priority_queue<int, vector<int>, greater<int>> minHeap;

	for (int i = 0; i < n; i++) minHeap.push(a[i]);
	// 내림차순으로 정렬하기 위한 삭제한 항목을 배열의 끝부터 앞으로 채워 나감
	for (int i = n - 1; i >= 0; i--) {
		a[i] = minHeap.top();
		minHeap.pop();
	}
}

void main() {
	srand((unsigned int)time(NULL));
	int data[20];
	for (int i = 0; i < 20; i++) data[i] = rand() % 100;

	printf("\n     힙 정렬 전\t\t : ");
	for (int i = 0; i < 20; i++) printf("[%2d] ", data[i]);

	max_HeapSortDec(data, 20);

	printf("\n최대 힙 정렬 후(내림차순): ");
	for (int i = 0; i < 20; i++) printf("[%2d] ", data[i]);

	min_HeapSortInc(data, 20);

	printf("\n최소 힙 정렬 후(오름차순): ");
	for (int i = 0; i < 20; i++) printf("[%2d] ", data[i]);
}