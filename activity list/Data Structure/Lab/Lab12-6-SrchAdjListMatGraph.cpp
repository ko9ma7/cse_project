#include "Lab12-6-SrchAdjListMatGraph.h"

void main() {
	SrchAdjacentListGraph g;
	g.load("Lab12-3-Graph.txt");
	printf("그래프(Lab12-6-Graph.txt)\n");
	g.display();

	printf("BFS ==> ");
	g.resetVisited();
	g.BFS(0);
	printf("\n");
}