// 이진트리 클래스
#include "Lab9-1-CircularQueue.h"

class BinaryTree {
	BinaryNode* root; // 루트 노드 포인터
public:
	BinaryTree() : root(NULL) { }
	~BinaryTree() { }
	void setRoot(BinaryNode* node) { root = node; }
	BinaryNode* getRoot() { return root; }
	bool isEmpty() { return root == NULL; }

	// 1. 전위 순회
	void printPreOrder() { printf("전위 순회: "); preOrder(root); }
	void preOrder(BinaryNode* node) {
		if (node != NULL) {
			printf(" [%c] ", node->getData());
			preOrder(node->getLeft());
			preOrder(node->getRight());
		}
	}
	// 2. 중위 순회
	void printInOrder() { printf("\n중위 순회: "); inOrder(root); }
	void inOrder(BinaryNode* node) {
		if (node != NULL) {
			inOrder(node->getLeft());           // 왼쪽 자식 처리
			printf(" [%c] ", node->getData()); // 자신을 처리
			inOrder(node->getRight());          // 오른쪽 자식 처리
		}
	}
	// 3. 후위 순회
	void printPostOrder() { printf("\n후위 순회: "); postOrder(root); }
	void postOrder(BinaryNode* node) {
		if (node != NULL) {
			postOrder(node->getLeft());
			postOrder(node->getRight());
			printf(" [%c] ", node->getData());
		}
	}
	// 4. 레벨 순회
	void levelOrder() {
		printf("\n레벨 순회: ");

		if (!isEmpty()) {
			CircularQueue q;
			q.enqueue(root);

			while (!q.isEmpty()) {
				BinaryNode* now = q.dequeue();
				if (now != NULL) {
					printf(" [%c] ", now->getData());
					q.enqueue(now->getLeft());
					q.enqueue(now->getRight());
				}
			}
		}

		printf("\n");
	}
	// 트리의 노드 개수
	int ReturnCount() { return isEmpty() ? 0 : getCount(root); }
	int getCount(BinaryNode* node) {
		if (node == NULL) return 0;
		return 1 + getCount(node->getLeft()) + getCount(node->getRight());
	}
	// 트리의 단말노드 개수
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
	// 수식 연산
	int ReturnEvaluate() { return evaluate(root); }
	int evaluate(BinaryNode* node) {
		if (node == NULL) return 0;
		if (node->isLeaf()) return node->getData();
		else {
			int op1 = evaluate(node->getLeft());
			int op2 = evaluate(node->getRight());

			switch(node->getData()) {
			case '+': return op1 + op2;
			case '-': return op1 - op2;
			case '*': return op1 * op2;
			case '/': return op1 / op2;
			}
			return 0;
		}
	}
};