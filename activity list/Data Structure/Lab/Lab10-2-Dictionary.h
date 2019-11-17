#include "Lab10-1-BinarySearchTree.h"

class Dictionary : public BinSrchTree {
public:
	void printAllWords() {
		printf("    >> 나의 단어장:\n");
		if (!isEmpty()) inOrder(root);
	}
	void searchWord(char* word) {
		BinaryNode* node = search(word);
		if (node != NULL) {
			printf("    >> ");
			node->display();
		} else printf("    >> 등록되지 않은 단어: %s\n", word);
	}
	void searchMeaning(char* m) {
		BinaryNode* node = (isEmpty()) ? NULL : searchByMeaning(root, m);
		if (node != NULL) {
			printf("    >> ");
			node->display();
		} else printf("    >> 등록되지 않은 의미: %s\n", m);
	}
	BinaryNode* searchByMeaning(BinaryNode* node, char* m) {
		if (node == NULL || node->compareMeaning(m) == 0) return node;
		BinaryNode* theNode = searchByMeaning(node->getLeft(), m);
		if (theNode != NULL) return theNode;
		return searchByMeaning(node->getRight(), m);
	}
};