#include <iostream>
#include <string>
using namespace std;

int main(void) {
	string s;
	while (1) {
		getline(cin, s);
		// 입력으로 공백이 들어오면 탈출
		if (s == "") break;
		cout << s << endl;
	}

	return 0;
}