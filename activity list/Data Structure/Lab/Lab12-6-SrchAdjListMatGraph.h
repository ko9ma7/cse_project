// 인접 리스트로 구현한 그래프의 너비 우선 탐색
#include "Lab12-5-CircularQueue.h"
#include "Lab12-2-AdjListGraph.h"

class SrchAdjacentListGraph : public AdjListGraph {
	bool visited[MAX_VTXS];
public:
	void resetVisited() { for (int i = 0; i < size; i++) visited[i] = false; }
	bool isLinked(int u, int v) {
		for (Node* p = adj[u]; p != NULL; p = p->getLink())
			if (p->getID() == v) return true;
		return false;
	}
	void BFS(int v) {
		visited[v] = true;
		printf("%c  ", getVertex(v));

		CircularQueue que;
		que.enqueue(v);

		while (!isEmpty()) {
			int v = que.dequeue();
			for (Node* w = adj[v]; w != NULL; w = w->getLink()) {
				int id = w->getID();
				if (!visited[id]) {
					visited[id] = true;
					printf("%c ", getVertex(id));
					que.enqueue(id);
				}
			}
		}
	}
};