#include "Lab13-3-Prim.h"

void main() {
	/*
	WGraphMST g;
	g.load("Lab13-1-Graph.txt");
	printf("MST By Prim's Algorithm\n");
	g.Prim(0);
	*/
	WGraphMST g;
	g.load("Lab13-2-Graph2.txt");
	printf("MST By Prim's Algorithm\n");
	g.Prim(0);
	/*
	WGraphMST g;
	g.load("graph3.txt");
	printf("MST By Prim's Algorithm\n");
	g.Prim(0);
	*/
}