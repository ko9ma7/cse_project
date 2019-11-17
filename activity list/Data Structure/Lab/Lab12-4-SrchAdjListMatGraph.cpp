#include "Lab12-4-SrchAdjListMatGraph.h"

void main() {
	/*
	SrchAdjacentListGraph g;

	g.load("Lab12-3-Graph.txt");
	printf("그래프(Lab12-4-Graph.txt)\n");
	g.display();

	printf("DFS ==> ");
	g.resetVisited();
	g.DFS(0);
	printf("\n");
	*/
	SrchAdjacentListGraph g;

	g.load("Lab12-4-Practice.txt");
	printf("그래프(Lab12-4-Practice.txt)\n");
	g.display();

	printf("DFS ==> ");
	g.resetVisited();
	g.DFS(0);
	printf("\n");
}