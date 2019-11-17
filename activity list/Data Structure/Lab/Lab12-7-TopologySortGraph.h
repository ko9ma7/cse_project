// 위상 정렬 기능이 추가된 인접 리스트 기반 그래프
#include "Lab12-2-AdjListGraph.h"
#include "Lab12-7-ArrayStack.h"

class TopoSortGraph : public AdjListGraph {
	int inDeg[MAX_VTXS]; // 정점의 진입차수
	                     // 0이 되면 후보 정점이 된다(그 다음 위상 정렬 번호)
public:
	void insertDirEdge(int u, int v) { // 방향성 간선 삽입 연산(한 쪽만 삽입)
		adj[u] = new Node(v, adj[u]);
	}
	// 위상정렬을 수행한다
	void TopoSort() {
		// 모든 정점의 진입 차수를 계산
		for (int i = 0; i < size; i++) inDeg[i] = 0; // 초기화
		for (int i = 0; i < size; i++) { // 인덱스가 0번부터 (size - 1)번까지의
			Node* node = adj[i];         // 정점 i에서 나오는 간선들(링크)
			while (node != NULL) {       // 다음 노드의 링크가 NULL값이 되기 전까지
				inDeg[node->getID()]++;  // 정점의 ID에 대한 진입차수배열을 하나씩 증가
				node = node->getLink();  // 이어져있으면 하나씩 증가됨
			}
		}
		// 진입 차수가 0인 정점을 스택에 삽입
		ArrayStack s;
		for (int i = 0; i < size; i++) if (inDeg[i] == 0) s.push(i);

		// 위상 순서를 생성
		while (s.isEmpty() == false) {
			int w = s.pop(); // 진입 차수가 0인 인덱스값 추출
			printf(" %c ", getVertex(w));
			// w와 인접하는 모든 정점의 진입 차수를 감소시킴
			// 감소된 진입 차수가 0이 되는 정점은 다시 스택에 삽입
			Node* node = adj[w];  // 진입 차수가 0인 정점을 처음 시작 노드로 설정
			while (node != NULL) {
				int u = node->getID();
				inDeg[u]--; // 진입 차수를 감소하여 간선들을 하나씩 없애줌
				if (inDeg[u] == 0) s.push(u); // 진입 차수가 0인 정점을 push(이 부분이 다시 위상정렬 노드의 시작부분)
				node = node->getLink();
			}
		}
		printf("\n위상 정렬 완료!\n");
	}
};