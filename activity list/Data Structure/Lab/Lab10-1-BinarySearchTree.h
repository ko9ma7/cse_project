#include "Lab10-1-BinaryTree.h"

class BinSrchTree : public BinaryTree
{
public:
	BinSrchTree() { }
	~BinSrchTree() { }
	// 이진 탐색 트리의 탐색 연산
	BinaryNode* search(int key) {
		BinaryNode* node = searchRecur(root, key);
		if (node != NULL) printf("[탐색 연산]: 성공: [%d] = 0x%x\n", node->getData(), node);
		else printf("[탐색 연산]: 실패: No %d!\n\n", key);
		return node;
	}
	// 이진 탐색 트리의 탐색 연산 방법(3가지)
	// 1. 순환적인 일반함수 구현
	BinaryNode* searchRecur(BinaryNode* n, int key) {
		if (n == NULL) return NULL;
		if (key == n->getData()) return n;
		else if (key < n->getData()) return searchRecur(n->getLeft(), key); // key < 현재 노드의 data -> 왼쪽 자식 검색
		else return searchRecur(n->getRight(), key);                        // key > 현재 노드의 data -> 오른쪽 자식 검색
	}
	// 2. 반복적인 탐색함수 구현
	BinaryNode* searchIter(BinaryNode* n, int key) {
		while (n != NULL) {
			if (key == n->getData()) return n;
			else if (key < n->getData()) n = n->getLeft();
			else n = n->getRight();
		}
		return NULL;
	}
	// 이진 탐색 트리의 삽입 연산
	void insert(BinaryNode* n) {
		if (n == NULL) return;
		if (isEmpty()) root = n;
		else insertRecur(root, n);
	}
	// 반복적인 삽입함수 구현
	void insertRecur(BinaryNode* r, BinaryNode* n) { // r은 root노드부터 시작, n은 찾고자하는 노드
		if (n->getData() == r->getData()) return;
		else if (n->getData() < r->getData()) {
			if (r->getLeft() == NULL) r->setLeft(n);
			else insertRecur(r->getLeft(), n);
		} else {
			if (r->getRight() == NULL) r->setRight(n);
			else insertRecur(r->getRight(), n);
		}
	}
	// 이진 탐색 트리의 삭제 연산
	void remove(int key) {
		if (isEmpty()) return;

		// 없앨 노드와 그 노드의 부모를 찾는다
		BinaryNode* parent = NULL; // 없앨 노드의 부모
		BinaryNode* node = root;   // 없앨 노드

		while ((node != NULL) && (node->getData() != key)) {
			parent = node;
			node = (key < node->getData()) ? node->getLeft() : node->getRight();
		}
		// 없앨 노드가 트리에 없음
		if (node == NULL) {
			printf("Error: key is not int the tree!\n");
			return;
		}
		// 없앨 노드가 트리에 있음
		else removeNode(parent, node);
	}
	// 이진 탐색 트리의 삭제 연산(3가지 경우)
	void removeNode(BinaryNode* parent, BinaryNode* node) {
		// case 1: 단말노드 삭제
		if (node->isLeaf()) {
			if (parent == NULL) root = NULL; // node == root인 경우, 공백상태가 된다
			else {
				if (parent->getLeft() == node) parent->setLeft(NULL);
				else parent->setRight(NULL);
			}
		}
		// case 2: 자식이 하나인 노드 삭제
		else if (node->getLeft() == NULL || node->getRight() == NULL) {
			// 삭제할 노드의 유일한 자식 노드 => child
			BinaryNode* child = (node->getLeft() != NULL) ? node->getLeft() : node->getRight();
			// 삭제할 노드가 루트이면 => child가 새로운 root가 됨
			if (node == root) root = child;
			// 아니면 부모 노드의 자식으로 자식 노드를 child를 직접 연결
			else {
				if (parent->getLeft() == node) parent->setLeft(child);
				else parent->setRight(child);
			}
		}
		// case 3: 두 개의 자식을 가진 노드 삭제
		else {
			// 삭제하려는 노드의 오른쪽 서브트리에서 가장 큰 노드를 탐색
			// succ => 후계 노드: 오른쪽 서브트리에서 가장 key가 작은 노드(오른쪽 서브트리의 가장 왼쪽의 단말 노드)
			// succp => 후계 노드의 부모 노드
			BinaryNode* succp = node;
			BinaryNode* succ = node->getRight();
			while (succ->getLeft() != NULL) { // 후계 노드 탐색
				succp = succ;                 // 후계 노드의 부모 노드
				succ = succ->getLeft();
			}
			// 탐색이 완료되고 후계 노드의 부모와 후계 노드의 오른쪽 자식을 직접 연결
			if (succp->getLeft() == succ) succp->setLeft(succ->getRight());
			else succp->setRight(succ->getLeft());
			// 후계 노드 정보를 삭제할 노드에 복사
			node->setData(succ->getData());
			// 삭제할 노드를 후계 노드로 변경
			node = succ;
		}
		delete node;
	}
};