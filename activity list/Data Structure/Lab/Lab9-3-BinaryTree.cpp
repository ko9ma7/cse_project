#include "Lab9-3-BinaryTree.h"

void main() {
	/*
	BinaryTree tree;
	/*
	              A
                /   \
               /      \
			  /         \
		     B            C
		   /   \        /   \
		  D     E      F     G
		 / \   / \    / \
		H   I J   K  L   M

	BinaryNode* m = new BinaryNode('M', NULL, NULL);
	BinaryNode* l = new BinaryNode('L', NULL, NULL);
	BinaryNode* k = new BinaryNode('K', NULL, NULL);
	BinaryNode* j = new BinaryNode('J', NULL, NULL);
	BinaryNode* i = new BinaryNode('I', NULL, NULL);
	BinaryNode* h = new BinaryNode('H', NULL, NULL);
	BinaryNode* g = new BinaryNode('G', NULL, NULL);
	BinaryNode* f = new BinaryNode('F', l, m);
	BinaryNode* e = new BinaryNode('E', j, k);
	BinaryNode* d = new BinaryNode('D', h, i);
	BinaryNode* c = new BinaryNode('C', f, g);
	BinaryNode* b = new BinaryNode('B', d, e);
	BinaryNode* a = new BinaryNode('A', b, c);
	tree.setRoot(a);
	tree.printPreOrder();
	tree.printInOrder();
	tree.printPostOrder();
	tree.printLevelOrder();
	printf("\n\n");


	printf("5.  노드의 개수 = %d\n", tree.ReturnCount());
	printf("6.  단말의 개수 = %d\n", tree.ReturnLeafCount());
	printf("7.  트리의 높이 = %d\n", tree.ReturnHeight());

	if (tree.isFull()) printf("8. [완전 이진트리]\n\n");
	else printf("8.  [불완전 이진트리]\n\n");

	char search;
	printf("	찾고자 하는 값은? "); scanf("%c", &search);
	printf("9.  [%c - Level]: %d\n", search, tree.randomLevel(search));
	printf("10. [Tree's pathLength]: %d\n", tree.ReturnPathLength());
	if (tree.isBalanced()) printf("11. [Balanced Tree]\n");
	else printf("11. [Unbalanced tree]\n");

	BinaryTree tree2;
	BinaryNode* n1 = new BinaryNode(3, NULL, NULL);
	BinaryNode* n2 = new BinaryNode(2, NULL, NULL);
	BinaryNode* n3 = new BinaryNode('*', n1, n2);
	BinaryNode* n4 = new BinaryNode(5, NULL, NULL);
	BinaryNode* n5 = new BinaryNode(6, NULL, NULL);
	BinaryNode* n6 = new BinaryNode('-', n4, n5);
	BinaryNode* n7 = new BinaryNode('+', n3, n6);
	tree2.setRoot(n7);
	printf("계산 결과 = %d\n\n", tree2.printEvaluate());


	BinaryTree tree3;
	BinaryNode* m4 = new BinaryNode(200, NULL, NULL);
	BinaryNode* m5 = new BinaryNode(500, NULL, NULL);
	BinaryNode* m3 = new BinaryNode(100, m4, m5);
	BinaryNode* m2 = new BinaryNode(50, NULL, NULL);
	BinaryNode* m1 = new BinaryNode(0, m2, m3);
	tree3.setRoot(m1);
	printf("디렉터리 용량 계산 결과 = %d KB\n\n", tree3.printCalcSize());
	*/
	BinaryTree tree;
	/*
	              A
                /   \
               B      C
			  /      /
		     D      F
		   /       /  \
		  E      G      H
    */
	BinaryNode* e = new BinaryNode('E', NULL, NULL);
	BinaryNode* g = new BinaryNode('G', NULL, NULL);
	BinaryNode* h = new BinaryNode('H', NULL, NULL);
	BinaryNode* d = new BinaryNode('D', e, NULL);
	BinaryNode* f = new BinaryNode('F', g, h);
	BinaryNode* b = new BinaryNode('B', d, NULL);
	BinaryNode* c = new BinaryNode('C', f, NULL);
	BinaryNode* a = new BinaryNode('A', b, c);

	tree.setRoot(a);
	tree.printPreOrder();
	tree.printInOrder();
	tree.printPostOrder();
	tree.printLevelOrder();
	printf("\n\n");
}