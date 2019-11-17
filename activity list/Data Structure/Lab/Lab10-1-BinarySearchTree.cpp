#include "Lab10-1-BinarySearchTree.h"

void main() {
	/*
	BinSrchTree tree;

	printf("[삽입 연산]: 35 18 7 26 12 3 68 22 30 99\n");
	tree.insert(new BinaryNode(35));
	tree.insert(new BinaryNode(18));
	tree.insert(new BinaryNode(7));
	tree.insert(new BinaryNode(26));
	tree.insert(new BinaryNode(12));
	tree.insert(new BinaryNode(3));
	tree.insert(new BinaryNode(68));
	tree.insert(new BinaryNode(22));
	tree.insert(new BinaryNode(30));
	tree.insert(new BinaryNode(99));

	printf("노드의 개수 = %d\n", tree.ReturnCount());
	printf("단말의 개수 = %d\n", tree.ReturnLeafCount());
	printf("트리의 높이 = %d\n\n", tree.ReturnHeight());

	tree.printPreOrder();
	tree.printInOrder();
	tree.printPostOrder();
	tree.printLevelOrder();

	tree.search(26);
	tree.search(25);

	printf("original bintree"); tree.printLevelOrder();
	printf("case 1 ==> [3] 삭제");
	tree.remove(3);
	tree.printLevelOrder();
	printf("case 2 ==> [68] 삭제");
	tree.remove(68);
	tree.printLevelOrder();
	printf("case 3 ==> [18] 삭제");
	tree.remove(18);
	tree.printLevelOrder();
	printf("case 3 ==> [35] 삭제");
	tree.remove(35);
	tree.printLevelOrder();

	printf("노드의 개수 = %d\n", tree.ReturnCount());
	printf("단말의 개수 = %d\n", tree.ReturnLeafCount());
	printf("트리의 높이 = %d\n\n", tree.ReturnHeight());
	*/
	BinSrchTree tree;

	printf("[삽입 연산]: 5 7 2 8 3\n");
	tree.insert(new BinaryNode(5));
	tree.insert(new BinaryNode(7));
	tree.insert(new BinaryNode(2));
	tree.insert(new BinaryNode(8));
	tree.insert(new BinaryNode(3));

	printf("노드의 개수 = %d\n", tree.ReturnCount());
	printf("단말의 개수 = %d\n", tree.ReturnLeafCount());
	printf("트리의 높이 = %d\n\n", tree.ReturnHeight());

	tree.printPreOrder();
	tree.printInOrder();
	tree.printPostOrder();
	tree.printLevelOrder();

	printf("original bintree"); tree.printLevelOrder();
	printf("case 1 ==>  [3] 삭제");
	tree.remove(3);
	tree.printLevelOrder();
	printf("case 2 ==>  [4] 삽입");
	tree.insert(new BinaryNode(4));
	tree.printLevelOrder();
	printf("case 3 ==>  [3] 삽입");
	tree.insert(new BinaryNode(3));
	tree.printLevelOrder();
	printf("case 4 ==>  [7] 삭제");
	tree.remove(7);
	tree.printLevelOrder();
	printf("case 5 ==>  [5] 삭제");
	tree.remove(5);
	tree.printLevelOrder();

	printf("노드의 개수 = %d\n", tree.ReturnCount());
	printf("단말의 개수 = %d\n", tree.ReturnLeafCount());
	printf("트리의 높이 = %d\n\n", tree.ReturnHeight());
}