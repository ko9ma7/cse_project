#include "Lab13-1-WeightGraph.h"

void main() {
	WGraph g;
	g.load("Lab13-1-Graph.txt");
	printf("가중치 그래프(Lab13-1-Graph.txt)\n");
	g.display();
}