// 인접 행렬로 구현한 그래프의 깊이 우선 탐색
#include "Lab12-2-AdjMatGraph.h"

class SrchAdjacentMatrixGraph : public AdjMatGraph {
	bool visited[MAX_VTXS]; // 정점의 방문 정보(방문했으면 true, 방문하지 않았으면 false)
public:
	void resetVisited() {
		for(int i = 0; i < size; i++) visited[i] = false;
	}
	bool isLinked(int u, int v) { return getEdge(u, v) != 0; } // u번째 정점과 v번째 정점이 연결되었는지 검사
	// 깊이 우선 탐색 함수
	void DFS(int v) {
		visited[v] = true;
		printf("%c ", getVertex(v));

		for (int w = 0; w < size; w++)
			if (isLinked(v, w) && visited[w] == false)
				DFS(w); // 연결되어있으면서 방문한적이 없으면 순환호출로 방문
	}
};