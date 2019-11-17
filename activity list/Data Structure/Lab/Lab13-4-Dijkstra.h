// Dijkstra 알고리즘
#include "Lab13-1-WeightGraph.h"

class WGraph_Dijkstra : public WGraph {
	int dist[MAX_VTXS];    // 시작노드로부터의 최단경로 거리
	bool found[MAX_VTXS];  // 방문한 정점 표시
public:
	// 방문하지 않은 정점들 중에서 최단경로 거리가 가장 작은 정점을 찾아 반환
	// S(시작 정점으로부터의 최단 경로가 이미 발견된 정점들의 집합)에 포함시키기 위해서
	// S에 포함되면 포함되지 않은 정점들의 dist의 값을 조정해야 한다
	// 우선순위 큐로 대치하면 더 빠르게 수행가능하다
	int chooseVertex() {
		int min = INF;
		int minPos = -1;

	    for (int i = 0; i < size; i++) {
			if (dist[i] < min && !found[i]) {
				min = dist[i];
				minPos = i;
			}
		}
		return minPos;
	}
	// Dijkstra의 최단 경로 알고리즘: start 정점에서 시작함
	void ShortestPathDijkstra(int start) {
		// 초기화
		for (int i = 0; i < size; i++) {
			dist[i] = getEdge(start, i);     // 시작노드에서부터 모든 경로의 최소 거리
			found[i] = false;                // 처음에는 모든 정점을 방문하지 않았으므로 false
		}

		found[start] = true;                 // 시작노드의 방문 완료
		dist[start] = 0;                     // 시작노드의 최소거리는 0(자기 자신)

		for (int i = 0; i < size; i++) {
			printf("Step%2d:", i + 1);
			printDistance();
			int u = chooseVertex();          // S에 포함되지 않은 정점들 중에서 dist가 가장 작은 정점 u를 찾음
			found[u] = true;                 // u를 S에 포함시킨다

			for (int w = 0; w < size; w++) { // S에 포함되지 않은 모든 정점 w에 대해서
				if (found[w] == false)
					// 조건 (dist[u] + getEdge(u, w) < dist[w])를 만족하면 dist[w]]를 갱신. dist[w] = dist[u] + getEdge(u, w)
					if (dist[u] + getEdge(u, w) < dist[w])
						dist[w] = dist[u] + getEdge(u, w);
			}
		}
	}
	// dist 상태를 출력하는 함수
	void printDistance() {
		for (int i = 0; i < size; i++)
			printf("%5d", dist[i]);
		printf("\n");
	}
};