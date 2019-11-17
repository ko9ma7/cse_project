/*
   인접 리스트를 이용한 그래프 클래스의 구현
   그래프의 각 정점에 인접한 정점들을 연결 리스트로 표현하는 방법
   연결 리스트의 노드들은 인접 정점 정보를 저장하는데, 그래프에는 이러한
   각 인접 리스트에 대한 헤더 포인터를 배열로 갖는다
*/
#include <cstdio>

class Node {
protected:
	int id;     // 정점의 id
	Node* link; // 다음 노드의 포인터
public:
	Node(int i, Node* l = NULL) : id(i), link(l) { }
	~Node() { if (link != NULL) delete link; }
	int getId() { return id; }
	Node* getLink() { return link; }
	void setLink(Node* l) { link = l; }
};