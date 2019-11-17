// 최소 신장 트리(MST) 기능이 추가된 가중치 그래프
// KrusKal 알고리즘
#include "Lab11-1-MinHeap.h"
#include "Lab13-1-WeightGraph.h"
#include "Lab13-2-VertexSets.h"

class WGraphMST : public WGraph {
public:
	void Kruskal() {
		MinHeap heap;
		// 힙에 모든 간선 삽입
		for (int i = 0; i < size -  1; i++)
			for (int j = i + 1; j < size; j++)
				if (hasEdge(i, j))
					heap.insert(getEdge(i, j), i, j);

		VertexSets set(size);                  // 그래프 정점의 개수만큼의 초기 집합을 갖는 정점 집합 객체 생성
		int edgeAccepted = 0;                  // 선택된 간선의 수
		while (edgeAccepted < size - 1) {      // 간선의 수 < (size - 1)
			HeapNode& e = heap.remove();       // 힙에서 최소 가중치 간선 e를 추출
			int uset = set.findSet(e.getV1()); // e의 양쪽 끝 정점이 속한 u의 집합 번호
			int vset = set.findSet(e.getV2()); // e의 양쪽 끝 정점이 속한 v의 집합 번호

			if (uset != vset) {                // 두 집합이 같은 집합이 아니면
				printf("간선 추가 : %c - %c (비용: %d)\n", getVertex(e.getV1()), getVertex(e.getV2()), e.getKey()); // 간선 추가 메시지 출력
				set.unionSets(uset, vset);     // 두 개의 집합을 합함
				edgeAccepted++;                // 선택된 간선의 수 증가
			}
		}
	}
};