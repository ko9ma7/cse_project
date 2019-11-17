#include <cstdio>
#include <cstring>
// 연예인 정보 검색 프로그램
// 연예인 정보 - 이름, 출생년도, 나이, 직종, 소속사
class Entertainer {
	char name[20];
	char birth[20];
	int age;
	char type[20];
	char entertainment[20];
public:
	Entertainer(char* n, char* b, int a, char* t, char* e) { setInformation(n, b, a, t, e); }
	~Entertainer() { }
	void setInformation(char* n, char* b, int a, char* t, char* e) {
		strcpy(name, n);
		strcpy(birth, b);
		age = a;
		strcpy(type, t);
		strcpy(entertainment, e);
	}
	char* getName() { return name; }
	char* getEntertainment() { return entertainment; }
	int searchName(char* n) { return strcmp(name, n); } // 이름이 같으면 0을 출력
	int searchEntertainment(char* e) { return strcmp(entertainment, e); }
	void display() {
		printf("========프로필 정보========\n");
		printf("   [  이름  ] %s\n", name);
		printf("   [출생년도] %s\n", birth);
		printf("   [  나이  ] %d\n", age);
		printf("   [  직종  ] %s\n", type);
		printf("   [ 소속사 ] %s\n\n", entertainment);
	}
};