/*
   인접 행렬을 이용한 그래프 클래스 구현
   간선이 존재하면 1, 간선이 존재하지 않으면 0
*/
#include <cstdio>
#define MAX_VTXS 256 // 최대 정점의 개수

class AdjMatGraph {
protected:
	int size;                    // 정점의 개수
	char vertices[MAX_VTXS];     // 정점의 데이터 정보
	int adj[MAX_VTXS][MAX_VTXS]; // 인접 행렬
public:
	AdjMatGraph() { reset(); }
	bool isEmpty() { return size == 0; }
	bool isFull() { return size >= MAX_VTXS; }
	void setEdge(int i, int j, int val) { adj[i][j] = val; } // val의 값은 0(이어져 있지 않으면) 또는 1(이어져 있으면)
	char getVertex(int i) { return vertices[i]; }
	int getEdge(int i, int j) { return adj[i][j]; }
	// 그래프 초기화 => 공백 상태의 그래프
	void reset() {
		size = 0;
		for (int i = 0; i < MAX_VTXS; i++)
			for (int j = 0; j < MAX_VTXS; j++)
				setEdge(i, j, 0);
	}
	// 정점 삽입 연산
	void insertVertex(char name) {
		if (!isFull()) vertices[size++] = name;
		else printf("Error: 그래프 정점 개수 초과\n");
	}
	// 간선 삽입 연산(무방향 그래프의 경우, 방향/가중치 그래프의 경우는 수정)
	void insertEdge(int u, int v) {
		setEdge(u, v, 1);
		setEdge(v, u, 1); // 방향 그래프에서는 삭제해야됨(<u, v>만 존재)
	}
	// 정점 삭제 연산
    void deleteVertex(int v) {

	}
	// 간선 삭제 연산
	void deleteEdge(int u, int v) {
		setEdge(u, v, 0);
		setEdge(v, u, 0);

		// 배열의 각 행이나 열의 첫 번째 주소를 이용해서 교체할 순 없나?
		/*
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

			}
		}
		*/
	}
	// 파일 입력 함수
	void load(char* filename) {
		FILE* fp = fopen(filename, "r");
		if (fp != NULL) {
			int n, val;
			fscanf(fp, "%d", &n); // 정점의 전체 개수를 파일에서 읽어들임

			for (int i = 0; i < n; i++) {
				char str[80];
				fscanf(fp, "%s", str); // 정점의 이름을 파일에서 읽어들임
				insertVertex(str[0]);  // 하나의 문자열의 첫 번째 문자를 저장

				for (int j = 0; j < n; j++) {
					int val;
					fscanf(fp, "%d", &val);
					if (val != 0) insertEdge(i, j);
				}
			}
			fclose(fp);
		}
	}
	// 파일 저장 함수
	void store(char* filename) {
		FILE* fp = fopen(filename, "w");
		if (fp != NULL) {
			display(fp);
			fclose(fp);
		}
	}
	// 그래프 정보를 출력함
	void display(FILE* fp = stdout) {
		fprintf(fp, "%d\n", size);                  // 정점의 개수 출력
		for (int i = 0; i < size; i++) {            // 각 행의 정보 출력
			fprintf(fp, "%c ", getVertex(i));       // 정점의 이름 출력
			for (int j = 0; j < size; j++)          // 간선 정보 출력
				fprintf(fp, "%3d", getEdge(i, j));
			fprintf(fp, "\n");
		}
	}
};