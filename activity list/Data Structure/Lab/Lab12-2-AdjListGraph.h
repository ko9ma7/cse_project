#include "Lab12-2-Node.h"
#define MAX_VTXS 256

class AdjListGraph {
protected:
	int size;                // 정점의 개수
	char vertices[MAX_VTXS]; // 정점 정보
	Node* adj[MAX_VTXS];     // 각 정점의 인접 리스트
public:
	AdjListGraph() : size(0) { }
	~AdjListGraph() { reset(); }
	void reset(void) {
		for (int i = 0; i < size; i++) if (adj[i] != NULL) delete adj[i];
		size = 0;
	}
	bool isEmpty() { return size == 0; }
	bool isFull() { return size >= MAX_VTXS; }
	char getVertex(int i) { return vertices[i]; }
	// 정점 삽입 연산
	void insertVertex(char val) {
		if (!isFull()) {
			vertices[size] = val;
			adj[size++] = NULL;
		}
		else printf("Error: 그래프 정점 개수 초과\n");
	}
	// 간선 삽입 연산
	void insertEdge(int u, int v) {
		adj[u] = new Node(v, adj[u]);
		adj[v] = new Node(u, adj[v]); // 방향 그래프이면 주석 처리함
	}
	// 그래프 출력
	void display() {
		printf("%d\n", size);             // 정점의 개수 출력
		for (int i = 0; i < size; i++) {  // 각 행의 정보 출력
			printf("%c ", getVertex(i));
			for (Node* v = adj[i]; v != NULL; v = v->getLink())
				printf("   %c", getVertex(v->getId()));
			printf("\n");
		}
	}
	Node* adjacent(int v) { return adj[v]; }
	// 파일 입력 함수
	void load(char* filename) {
		FILE* fp = fopen(filename, "r");
		if (fp != NULL) {
			int n, val;
			fscanf(fp, "%d", &n); // 정점의 전체 개수를 파일에서 읽어들임

			for (int i = 0; i < n; i++) {
				char str[80];
				fscanf(fp, "%s", str); // 하나의 문자열을 파일에서 읽어들임
				insertVertex(str[0]);  // 하나의 문자열의 첫 번째 문자를 정점으로 저장

				for (int j = 0; j < n; j++) {
					int val;
					fscanf(fp, "%d", &val);
					if (val != 0) insertEdge(i, j);
				}
			}
			fclose(fp);
		}
	}
};