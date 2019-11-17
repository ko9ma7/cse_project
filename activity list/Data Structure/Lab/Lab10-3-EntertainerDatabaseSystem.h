#include "Lab10-1-BinarySearchTree.h"

class EntertainerDatabaseSystem : public BinSrchTree {
public:
	void printAllInformation() {
		printf("    >> 연예인 정보\n");
		if (!isEmpty()) this->printInorder();
	}
	void searchEntertainer(char* name) {
		BinaryNode* node = searchNameForInformation(name);
		if (node != NULL) {
			printf("    >> ");
			node->getInformation().display();
		} else printf("    >> 등록되지 않은 연예인입니다: %s\n", name);
	}
	void searchEntertainment(char* enter) { searchEntertainmentForInformation(enter); }
};
