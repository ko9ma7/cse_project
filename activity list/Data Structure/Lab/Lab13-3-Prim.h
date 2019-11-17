// 최소 신장 트리(MST) 기능이 추가된 가중치 그래프
// Prim 알고리즘
#include "Lab11-1-MinHeap.h"
#include "Lab13-1-WeightGraph.h"
#include "Lab13-2-VertexSets.h"

class WGraphMST : public WGraph {
public:
	// 현재 트리에 인접한 정점들 중에서 가장 가까운 정점을 찾는 과정
	void Prim(int s) {
		bool selected[MAX_VTXS];         // 정점이 MST에 포함되었는지
		int dist[MAX_VTXS];              // 신장 트리에서 i번째 정점까지의 현재까지 알려진 가장 가까운 거리를 저장
		                                 // 정점들이 추가되면 각 정점의 dist 값은 변경된다
		for (int i = 0; i < size; i++) { // 배열 초기화
			dist[i] = INF;               // 처음에는 시작 정점만 0이고 나머지 정점은 무한대이다
			selected[i] = false;         // 처음에는 모든 정점은 MST에 포함되지 않았다
		}
		dist[s] = 0;                     // 시작 정점은 0

		for (int i = 0; i < size; i++) {
			// 포함되지 않은 정점들 중에서 MST와의 거리가 최소인 정점
			int u = getMinVertex(selected, dist);

			selected[u] = true;           // 최소인 정점이므로 MST에 포함시킴
			if (dist[u] == INF) return;
			printf("%c ", getVertex(u));
			// u의 인접 정점들에 대한 dist 값 갱신
			// 지금까지 선택되지 않은 정점 v에 대해 지금까지 알고 있던 최소 거리 dist[v]보다 간선 (u, v)의 가중치가 더 작으면 dist[v]를 갱신
			for (int v = 0; v < size; v++)
				if (getEdge(u, v) != INF) // 간선의 가중치가 무한대가 아니라면
					if (!selected[v] && getEdge(u, v) < dist[v])
						dist[v] = getEdge(u, v); // 최소 거리는 간선의 가중치(갱신)
		}
		printf("\n");
	}
	// MST에 포함되지 않은 정점들 중에서 MST와의 거리(dist)가 최소인 정점 선택
	int getMinVertex(bool* selected, int* dist) {
		int minV = 0;
		int minDist = INF;

		for (int v = 0; v < size; v++) {
			if (!selected[v] && dist[v] < minDist) { // MST에 포함되지 않고 이전의 최소 거리보다 작으면
				minDist = dist[v];                   // 최소 거리는 dist값으로 변경
				minV = v;                            // 최소 정점은 선택된 인덱스로 변경
			}
		}

		return minV;
	}
};