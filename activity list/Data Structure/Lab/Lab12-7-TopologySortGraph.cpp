#include "Lab12-7-TopologySortGraph.h"

void main() {
	TopoSortGraph g;
	for (int i = 0; i < 6; i++) g.insertVertex('A' + i);

	g.insertDirEdge(0, 2); g.insertDirEdge(0, 3); // 정점 0의 인접 리스트 생성
	g.insertDirEdge(1, 3); g.insertDirEdge(1, 4); // 정점 1의 인접 리스트 생성
	g.insertDirEdge(2, 3); g.insertDirEdge(2, 5); // 정점 2의 인접 리스트 생성
	g.insertDirEdge(3, 5);                        // 정점 3의 인접 리스트 생성
	g.insertDirEdge(4, 5);                        // 정점 4의 인접 리스트 생성
	printf("Topology Sort\n");
	g.TopoSort();
}