#include "Lab9-1-BinaryTree.h"

void main() {
	BinaryTree tree;
	/*
	              A
			  /       \
		     B         C
		   /   \     /   \
		  D     E   F     G
		/  \            /   \
	   H    I          J     K

	*/
	BinaryNode* h = new BinaryNode('H', NULL, NULL);
	BinaryNode* i = new BinaryNode('I', NULL, NULL);
	BinaryNode* d = new BinaryNode('D', h, i);
	BinaryNode* e = new BinaryNode('E', NULL, NULL);
	BinaryNode* b = new BinaryNode('B', d, e);
	BinaryNode* j = new BinaryNode('J', NULL, NULL);
	BinaryNode* k = new BinaryNode('K', NULL, NULL);
	BinaryNode* f = new BinaryNode('F', NULL, NULL);
	BinaryNode* g = new BinaryNode('G', j, k);
	BinaryNode* c = new BinaryNode('C', f, g);
	BinaryNode* a = new BinaryNode('A', b, c);
	tree.setRoot(a);
	tree.printPreOrder();
	tree.printInOrder();
	tree.printPostOrder();
	tree.printLevelOrder();
	printf("\n");
	printf("노드의 개수 = %d\n", tree.ReturnCount());
	printf("단말의 개수 = %d\n", tree.ReturnLeafCount());
	printf("트리의 높이 = %d\n", tree.ReturnHeight());
}