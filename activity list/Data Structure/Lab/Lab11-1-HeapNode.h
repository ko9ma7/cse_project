#pragma once;
#include <cstdio>
#include <cstdlib>

void error() {
	printf("error!\n");
	exit(1);
}

class HeapNode {
	int key;
public:
	HeapNode(int k = 0) : key(k) { }
	void setKey(int k) { key = k; }
	int getKey() { return key; }
	void display() { printf("%4d", key); }
};