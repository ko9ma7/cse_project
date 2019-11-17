#include "Lab6-2-Student.h"
class Node : public Student {
	Node* link;
public:
	Node(int id = 0, char* name = "", char* dept = "") : Student(id, name, dept) { link = NULL; }
	~Node() {}
	Node* getLink() { return link; }
	void setLink(Node* p) { link = p; }
};