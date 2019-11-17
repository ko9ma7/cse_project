#include "Lab13-5-Floyd.h"

void main() {
	WGraph_Floyd g;
	g.load("Lab13-1-Graph.txt");
	printf("Shortest Path By Floyd Algorithm\n");
	g.ShortestPathFloyd();
}