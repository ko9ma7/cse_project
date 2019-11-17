#include "Lab12-5-SrchAdjMatGraph.h"

void main() {
	/*
	SrchAdjacentMatrixGraph g;
	g.load("Lab12-3-Graph.txt");
	printf("그래프(Lab12-5-Graph.txt)\n");
	g.display();

	printf("BFS ==> ");
	g.resetVisited();
	g.BFS(0);
	printf("\n");
	*/
	SrchAdjacentMatrixGraph g;
	g.load("Lab12-4-Practice.txt");
	printf("그래프(Lab12-5-Practice.txt)\n");
	g.display();

	printf("BFS ==> ");
	g.resetVisited();
	g.BFS(0);
	printf("\n");
}