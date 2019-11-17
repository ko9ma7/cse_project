#include "Lab13-2-KrusKal.h"

void main() {
	/*
	WGraphMST g;
	g.load("Lab13-1-Graph.txt");
	printf("MST By Kruskal's Algorithm\n");
	g.Kruskal();
	*/
	WGraphMST g;
	g.load("Lab13-2-Graph2.txt");
	printf("MST By Kruskal's Algorithm\n");
	g.Kruskal();
}