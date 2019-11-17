#include "Lab13-4-Dijkstra.h"

void main() {
	WGraph_Dijkstra g;
	g.load("Lab13-1-Graph.txt");
	printf("Shortes Path By Dijkstra Algorithm\n");
	g.ShortestPathDijkstra(0);
}