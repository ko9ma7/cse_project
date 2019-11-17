// Union-Find 연산을 위한 정점 집합 클래스
#define MAX_VTXS 256

class VertexSets {
	int parent[MAX_VTXS]; // 각 정점 집합에서의 부모 정점의 인덱스
	int nSets;            // 현재의 정점 집합의 개수
public:
	VertexSets(int n) : nSets(n) {
		// 최초에는 모든 정점들이 고유의 집합에 속함. 이것은 모든 정점 집합의 원소의 개수가 1이 됨을 의미함. 그래프의 모든 정점들이 각각 고유의 집합을 이루고 있음.
		for (int i = 0; i < nSets; i++) parent[i] = -1;
	}
	// parent[i]가 -1이면 정점 i는 정점 집합의 루트가 됨. 아니면 이 정점은 다른 정점이 루트가 되는 집합에 속해있는 원소임.
	bool isRoot(int i) { return parent[i] < 0; }
	// Find 연산
	int findSet(int v) {                  // 전체 집합들 중에서 정점 v를 포함하는 집합의 루트를 찾아 반환
		while (!isRoot(v)) v = parent[v]; // v가 어떤 집합의 루트가 아니면 계속 루트를 찾아감
		return v;
	}
	// Union 연산
	void unionSets(int s1, int s2) {      // 두 집합 s1과 s2를 합하는 함수
		parent[s1] = s2;                  // parent[s1]이 이제 s2를 가리키도록 함. s1은 더 이상 루트가 아님
		nSets--;                          // 집합의 개수가 하나 줄어듦
	}
};