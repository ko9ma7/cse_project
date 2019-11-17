/*
   허프만 코드의 장점

   => 메모리 양도 줄여주고 찾는 속도도 빨라진다

   cf) 머클 트리
*/

#include <queue>
#include <functional>
using namespace std;

void MakeTree(int freq[], int n) {
	priority_queue<int, vector<int>, greater<int>> minHeap;

	for (int i = 0; i < n; i++) minHeap.push(freq[i]);

	for (int i = 1; i < n; i++) { // n - 1번을 루프를 돌려야 한다
		int e1 = minHeap.top(); minHeap.pop();
		int e2 = minHeap.top(); minHeap.pop();
		minHeap.push(e1 + e2);
		printf(" (%d + %d)\n", e1, e2);
	}
}

void main() {
	int freq[] = { 17, 3, 6, 9, 27, 5, 4, 13, 15, 1 };
	MakeTree(freq, 10);
}