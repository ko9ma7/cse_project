// 이진트리 노드 클래스
#include <cstdio>

class BinaryNode {
protected:
	int data; // 트리에 저장할 데이터
	BinaryNode* left; // 왼쪽 자식 노드의 포인터
	BinaryNode* right; // 오른쪽 자식 노드의 포인터
public:
	BinaryNode(int val = 0, BinaryNode* l = NULL, BinaryNode* r = NULL) : data(val), left(l), right(r) { }
	void setData(int val) { data = val; }
	void setLeft(BinaryNode* l) { left = l; }
	void setRight(BinaryNode* r) { right = r; }
	int getData() { return data; }
	BinaryNode* getLeft() { return left; }
	BinaryNode* getRight() { return right; }
	bool isLeaf() { return left == NULL && right == NULL; } // 현재 노드가 단말 노드인지를 검사하는 함수
	// 단말노드란 자식이 없는 노드
};