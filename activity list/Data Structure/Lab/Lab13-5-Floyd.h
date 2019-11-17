// Floyd 알고리즘
#include "Lab13-1-WeightGraph.h"

class WGraph_Floyd : public WGraph {
	int A[MAX_VTXS][MAX_VTXS]; // 최단경로 거리
public:
	void ShortestPathFloyd() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				A[i][j] = getEdge(i, j);

		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					// i -> j로 가는 경로
					// 1. k를 거치지 않는 경우 => A[i][j]
					// 2. k를 거치는 경우 => A[i][k] + A[k][j]
					// 현재 알고 있는 i에서 j로 가는 최단 경로에 비해 k를 거쳐 가는 경로의 길이가 더 짧으면 (i, j)에 대한 최단 경로 길이를 갱신함
					if (A[i][k] + A[k][j] < A[i][j])
						A[i][j] = A[i][k] + A[k][j];
				}
				printA();
			}
		}
	}
	void printA() {
		printf("==================================\n");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (A[i][j] == INF) printf(" INF ");
				else printf("%4d ", A[i][j]);
			}
			printf("\n");
		}
	}
};