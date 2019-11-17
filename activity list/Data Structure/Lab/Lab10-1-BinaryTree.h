#include "Lab9-1-CircularQueue.h"

class BinaryTree {
protected:
	BinaryNode* root;
public:
	BinaryTree(BinaryNode* r = NULL) : root(r) { }
	~BinaryTree() { }
	void setRoot(BinaryNode* r) { root = r; }
	BinaryNode* getRoot() { return root; }
	bool isEmpty() { return root == NULL; }
	void printPreOrder() { printf("전위 순회: "); root->preOrder(); }
	void printInOrder() { printf("\n중위 순회: "); root->inOrder(); }
	void printPostOrder() { printf("\n후위 순회: "); root->postOrder(); }
	void printLevelOrder() {
		printf("\n레벨 순회: ");

		if (!isEmpty()) {
			CircularQueue q;
			q.enqueue(root);

			while (!q.isEmpty()) {
				BinaryNode* now = q.dequeue();
				if (now != NULL) {
					printf(" [%d] ", now->getData());
					q.enqueue(now->getLeft());
					q.enqueue(now->getRight());
				}
			}
		}

		printf("\n\n");
	}

	// 트리 노드의 개수
	int ReturnCount() { return isEmpty() ? 0 : getCount(root); }
	int getCount(BinaryNode* node) {
		if (node == NULL) return 0;
		return 1 + getCount(node->getLeft()) + getCount(node->getRight());
	}
	// 트리의 단말노드의 개수
	int ReturnLeafCount() { return isEmpty() ? 0 : getLeafCount(root); }
	int getLeafCount(BinaryNode* node) {
		if (node == NULL) return 0;
		if (node->isLeaf()) return 1;
		else return getLeafCount(node->getLeft()) + getLeafCount(node->getRight());
	}
	// 트리의 높이
	int ReturnHeight() { return isEmpty() ? 0 : getHeight(root); }
	int getHeight(BinaryNode* node) {
		if (node == NULL) return 0;
		int hLeft = getHeight(node->getLeft());
		int hRight = getHeight(node->getRight());
		return (hLeft > hRight) ? hLeft + 1 : hRight + 1;
	}
};
