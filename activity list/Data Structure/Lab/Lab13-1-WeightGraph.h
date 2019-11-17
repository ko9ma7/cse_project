// 가중치 그래프
#include "Lab13-1-AdjMatGraph.h"
#define INF 9999 // 값이 INF 이상이면 간선이 없음

 class WGraph : public AdjMatGraph {
 public:
	 void insertEdge(int u, int v, int weight) {
		 if (weight > INF) weight = INF;
		 setEdge(u, v, weight);
	 }
	 // 정점 i와 j 사이에 간선이 있는지 검사. 강도가 INF 이하인 경우만 간선이 존재
	 bool hasEdge(int i, int j) { return getEdge(i, j) < INF; }

	 void load(char* filename) {
		 FILE*fp = fopen(filename, "r");
		 if (fp != NULL) {
			 int n, val;
			 fscanf(fp, "%d", &n);
			 for (int i = 0; i < n; i++) {
				 char str[80];
				 int val;
				 fscanf(fp, "%s", str);
				 insertVertex(str[0]);

				 for (int j = 0; j < n; j++) {
					 fscanf(fp, "%d", &val);
					 insertEdge(i, j, val);
				 }
			 }
			 fclose(fp);
		 }
	 }
};