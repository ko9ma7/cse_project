// 이진노드 클래스 upgrade
#include <cstdio>
#include <cstdlib>

class BinaryNode {
protected:
	int data;
	BinaryNode* left;
	BinaryNode* right;
public:
	BinaryNode(int val = 0, BinaryNode* l = NULL, BinaryNode* r = NULL) : data(val), left(l), right(r) { }
	~BinaryNode() { }
	void setData(int val) { data = val; }
	void setLeft(BinaryNode* node) { left = node; }
	void setRight(BinaryNode* node) { right = node; }
	int getData() { return data; }
	BinaryNode* getLeft() { return left; }
	BinaryNode* getRight() { return right; }
	bool isLeap() { return left == NULL && right == NULL; }
	// 1. 전위 순회
	void preOrder() {
		printf(" [%c] ", data);
		if (left != NULL) left->preOrder();
		if (right != NULL) right->preOrder();

	}
	// 2. 중위 순회
	void inOrder() {
		if (left != NULL) left->inOrder();
		printf(" [%c] ", data);
		if (right != NULL) right->inOrder();
	}
	// 3. 후위 순회
	void postOrder() {
		if (left != NULL) left->postOrder();
		if (right != NULL) right->postOrder();
		printf(" [%c] ", data);
	}
};