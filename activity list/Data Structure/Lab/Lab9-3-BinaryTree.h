// 이진트리 클래스
#include "Lab9-1-CircularQueue.h"
#include <cmath>
#include <stack>
using namespace std;

static int level = 0; // 이진트리 레벨
static int length = 0; // 이진트리 경로의 길이

class BinaryTree {
	BinaryNode* root; // 루트노드
public:
	BinaryTree() : root(NULL) { }
	~BinaryTree() { }
	void setRoot(BinaryNode* node) { root = node; }
	BinaryNode* getRoot() { return root; }
	bool isEmpty() { return root == NULL; }

	// 전위 순회
	void printPreOrder() { printf("1.  전위 순회: "); root->preOrder(); }
	// 중위 순회
	void printInOrder() { printf("\n2.  중위 순회: "); root->inOrder(); }
	// 후위 순회
	void printPostOrder() { printf("\n3.  후위 순회: "); root->postOrder(); }
	// 레벨 순회
	void printLevelOrder() {
		printf("\n4.  레벨 순회: ");

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

	// 수식트리 계산 함수
	int printEvaluate() { return evaluate(root); }
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
	// 디렉터리 용량 계산 함수
	int printCalcSize() { return calcSize(root); }
	int calcSize(BinaryNode* node) {
		if (node == NULL) return 0;
		return node->getData() + calcSize(node->getLeft()) + calcSize(node->getRight());
	}

	// 완전 이진트리 검사
	/*
	   단말노드의 개수가 2^(높이 - 1)을 한 값과 같으면 완전 이진트리
	*/
	bool isFull() { return getHeight(root) == (int)pow(2.0, getHeight(root) - 1); }
	// 임의 레벨
	int randomLevel(int node) { return root->randomNodeLevel(node, 0); }
	/*
	   교수님 해답

	int checkLevel(BinaryNode* p, BinaryNode* n, int level) {
		int ll=0, lr=0;
		if (p == n) return level;
		if (p->getLeft() != NULL)
			ll = checkLevel(p->getLeft(), n, level + 1);
		if (p->getRight()!= NULL)
			lr = checkLevel(p->getRight(), n, level + 1);

		if (ll > 0) return ll;
		else return lr;
	}
	int calcLevel(BinaryTree* t, BinaryNode* n) {
		int level = 0;
		if (t->getRoot() != NULL)
			level = checkLevel(t->getRoot(), n, 1);

		if ( level > 0 ) printf(" 노드의 레벨은 %d.\n", level);
		else printf(" 트리에 없는 노드입니다.\n");

		return level;
	}
	*/
	// 균형 검사
	bool isBalanced() { return getBalanced(root) ? true : false; }
	bool getBalanced(BinaryNode* node) {
		int lHeight, rHeight, dHeight;
		if (node == NULL) return 1;

		lHeight = getHeight(node->getLeft());
		rHeight = getHeight(node->getRight());
		dHeight = lHeight - rHeight;
		if (dHeight < -1 || dHeight > 1) return 0;

		if (getBalanced(node->getLeft()) == 0) return 0;
		return getBalanced(node->getRight());
	}
	// 경로의 길이
	int ReturnPathLength() { return isEmpty() ? 0 : getPathLength(root, 0); }
	int getPathLength(BinaryNode* node, int level) {
		int lLen = 0, rLen = 0;
		if (node == NULL) return 0;

		lLen = getPathLength(node->getLeft(), level + 1);
		rLen = getPathLength(node->getRight(), level + 1);

		return lLen + rLen + level;
	}
	// 대칭 연산
	bool reverse() {

	}
	/*
	void swapNodes(TNode* p) {
		TNode* tmp;
		if (p == NULL) return;
		if (p->left == NULL && p->right != NULL) {
			p->left = p->right;
			p->right = NULL;
		}
		else if (p->left != NULL && p->right == NULL) {
			p->right = p->left;
			p->left = NULL;
		}
		else if (p->left != NULL && p->right != NULL) {
			tmp = p->left;
			p->left = p->right;
			p->right = tmp;
		}
		swapNodes(p->left);
		swapNodes(p->right);
	}

	int reverse(BinTree* t) {
		printf("\n 트리의 좌우를 교환합니다.");
		swapNodes(t->root);
	}
	*/
};