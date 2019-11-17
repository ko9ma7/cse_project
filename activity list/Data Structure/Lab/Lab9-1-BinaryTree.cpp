#include "Lab9-1-BinaryTree.h"

void main() {
	/*
	BinaryTree tree;
	/*
	              A
			  /       \
		     B         C
		   /   \      /
		  D     E    F

	BinaryNode* d = new BinaryNode('D', NULL, NULL);
	BinaryNode* e = new BinaryNode('E', NULL, NULL);
	BinaryNode* b = new BinaryNode('B', d, e);
	BinaryNode* f = new BinaryNode('F', NULL, NULL);
	BinaryNode* c = new BinaryNode('C', f, NULL);
	BinaryNode* a = new BinaryNode('A', b, c);
	tree.setRoot(a);
	tree.printPreOrder();
	tree.printInOrder();
	tree.printPostOrder();
	tree.levelOrder();
	printf("\n");
	printf("노드의 개수 = %d\n", tree.ReturnCount());
	printf("단말의 개수 = %d\n", tree.ReturnLeafCount());
	printf("트리의 높이 = %d\n", tree.ReturnHeight());
	*/

	BinaryTree tree;
	/*
	              A
			  /       \
		     B         C
		   /          /  \
		  D          E     F
    */
	BinaryNode* d = new BinaryNode('D', NULL, NULL);
	BinaryNode* e = new BinaryNode('E', NULL, NULL);
	BinaryNode* f = new BinaryNode('F', NULL, NULL);
	BinaryNode* b = new BinaryNode('B', d, NULL);
	BinaryNode* c = new BinaryNode('C', e, f);
	BinaryNode* a = new BinaryNode('A', b, c);
	tree.setRoot(a);
	tree.printPreOrder();
	tree.printInOrder();
	tree.printPostOrder();
	tree.levelOrder();
	printf("\n");
	printf("노드의 개수 = %d\n", tree.ReturnCount());
	printf("단말의 개수 = %d\n", tree.ReturnLeafCount());
	printf("트리의 높이 = %d\n", tree.ReturnHeight());

	/*
	BinaryTree tree;

	              A
			  /       \
		     B         C
		   /          /  \
		  D          E     F
		           /   \
				 G       H

	BinaryNode* g = new BinaryNode('G', NULL, NULL);
	BinaryNode* h = new BinaryNode('H', NULL, NULL);
	BinaryNode* d = new BinaryNode('D', NULL, NULL);
	BinaryNode* e = new BinaryNode('E', g, h);
	BinaryNode* f = new BinaryNode('F', NULL, NULL);
	BinaryNode* b = new BinaryNode('B', d, NULL);
	BinaryNode* c = new BinaryNode('C', e, f);
	BinaryNode* a = new BinaryNode('A', b, c);
	tree.setRoot(a);
	tree.printPreOrder();
	tree.printInOrder();
	tree.printPostOrder();
	tree.levelOrder();
	printf("\n");
	printf("노드의 개수 = %d\n", tree.ReturnCount());
	printf("단말의 개수 = %d\n", tree.ReturnLeafCount());
	printf("트리의 높이 = %d\n", tree.ReturnHeight());
	*/
	/*
	BinaryTree tree;

	                        +
				          /   \
					    *       E
				       /  \
					  *     D
					 /  \
				   '/'   C
				   /  \
				 A     B

	BinaryNode* n1 = new BinaryNode('A', NULL, NULL);
	BinaryNode* n2 = new BinaryNode('B', NULL, NULL);
	BinaryNode* n3 = new BinaryNode('/', n1, n2);
	BinaryNode* n4 = new BinaryNode('C', NULL, NULL);
	BinaryNode* n5 = new BinaryNode('*', n3, n4);
	BinaryNode* n6 = new BinaryNode('D', NULL, NULL);
	BinaryNode* n7 = new BinaryNode('*', n5, n6);
	BinaryNode* n8 = new BinaryNode('E', NULL, NULL);
	BinaryNode* n9 = new BinaryNode('+', n7, n8);
	tree.setRoot(n9);
	tree.printPostOrder();
	*/
}