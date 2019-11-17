#include "Maze.h"

void main() {
	Maze maze;
	maze.load("Lab6-4-Maze.txt");
	maze.print();
	maze.searchExit();
}