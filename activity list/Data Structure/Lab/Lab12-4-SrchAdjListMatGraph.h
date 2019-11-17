// 인접 리스트로 구현한 그래프의 깊이 우선 탐색
#include "Lab12-2-AdjListGraph.h"

class SrchAdjacentListGraph : public AdjListGraph {
	bool visited[MAX_VTXS]; // 정점의 방문 정보
public:
	void resetVisited() {
		for (int i = 0; i < size; i++) visited[i] = false;
	}
	bool isLinked(int u, int v) {
		for (Node* p = adj[u]; p != NULL; p = p->getLink())
			if (p->getId() == v) return true;
		return false;
	}
	// 깊이 우선 탐색 함수
	void DFS(int v) {
		visited[v] = true;                                  // 현재 정점을 방문함
		printf("%c ", getVertex(v));                        // 정점의 이름을 출력

		for (Node* p = adj[v]; p != NULL; p = p->getLink())
			if (visited[p->getId()] == false)               // 방문하지 않았다면
				DFS(p->getId());                            // 정점 w에서 DFS 새로 시작
	}
};