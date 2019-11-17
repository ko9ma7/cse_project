#include "Lab12-3-SrchAdjMatGraph.h"

void main() {
	/*
	SrchAdjacentMatrixGraph g;
	g.load("Lab12-3-Graph.txt");
	printf("그래프(Lab12-3-Graph.txt)\n");
	g.display();

	printf("DFS ==> ");
	g.resetVisited();
	g.DFS(0);
	printf("\n");
	*/
	/*
	SrchAdjacentMatrixGraph g;
	g.load("Lab12-3-Practice1.txt");
	printf("그래프(Lab12-3-Practice1.txt)\n");
	g.display();

	printf("DFS ==> ");
	g.resetVisited();
	g.DFS(6);
	printf("\n");
	*/
	SrchAdjacentMatrixGraph g;
	g.load("Lab12-3-Practice2.txt");
	printf("그래프(Lab12-3-Practice2.txt)\n");
	g.display();

	printf("DFS ==> ");
	g.resetVisited();
	g.DFS(0);
	printf("\n");
}