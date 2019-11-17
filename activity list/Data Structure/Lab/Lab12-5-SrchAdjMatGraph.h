// 인접 행렬로 구현한 그래프의 너비 우선 탐색
#include "Lab12-1-AdjMatGraph.h"
#include "Lab12-5-CircularQueue.h"

class SrchAdjacentMatrixGraph : public AdjMatGraph {
	bool visited[MAX_VTXS];
public:
	void resetVisited() {
		for (int i = 0; i < size; i++) visited[i] = false;
	}
	bool isLinked(int u, int v) { return getEdge(u, v) != 0; }
	// 너비 우선 탐색
	void BFS(int v) {
		visited[v] = true;
		printf("%c  ", getVertex(v));

		CircularQueue que;
		que.enqueue(v);

		while (!que.isEmpty()) {
			int v = que.dequeue();
			for (int w = 0; w < size; w++) {
				if (isLinked(v, w) && visited[w] == false) {
					visited[w] = true;
					printf("%c  ", getVertex(w));
					que.enqueue(w);
				}
			}
		}
	}
};