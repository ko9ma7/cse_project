// 이진노드 클래스 upgrade
#include <cstdio>
#include <cstdlib>

static int flag = false; // 순환 호출을 탈출하기 위한 flag
static int answer = 0;   // 임의 노드의 레벨의 값

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
	bool isLeaf() { return left == NULL && right == NULL; }
	// 전위 순회
	void preOrder() {
		printf(" [%c] ", data);
		if (left != NULL) left->preOrder();
		if (right != NULL) right->preOrder();
	}
	// 중위 순회
	void inOrder() {
		if (left != NULL) left->inOrder();
		printf(" [%c] ", data);
		if (right != NULL) right->inOrder();
	}
	// 후위 순회
	void postOrder() {
		if (left != NULL) left->postOrder();
		if (right != NULL) right->postOrder();
		printf(" [%c] ", data);
	}
	// 임의 노드의 레벨
	/*
	   찾고자 하는 값이 있는지 검사하기 위해 전위 순회를 이용한다. 순환 호출을 탈출하기 위해서 flag를 사용하였다.
	*/
	int randomNodeLevel(int random_data, int level) {
		if (flag == false) {
			level += 1;

			if (random_data == data) {
				flag = true;
				answer = level;
			}
			if (random_data != data) {
				if (left != NULL) left->randomNodeLevel(random_data, level);
				if (right != NULL) right->randomNodeLevel(random_data, level);
			}
		}

		if (flag == true) return answer;
		else return 0; // 찾고자 하는 값이 없을 경우 0을 반환
	}
};