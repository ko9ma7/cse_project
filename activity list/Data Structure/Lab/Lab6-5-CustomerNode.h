#include "Lab5-1-Customer.h"
#pragma once;

class Node {
public:
	Customer c;
	Node* link;
	Node(Customer person) : c(person), link(NULL) { }
	~Node() { }
	void display() { display(); }
};