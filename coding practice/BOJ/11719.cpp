#include <iostream>
#include <string>
using namespace std;

int main(void) {
	string s;
	// getline()은 공백을 포함해서 한줄 한줄 입력받은대로 출력해줌
	while (getline(cin, s)) {
		cout << s << endl;
	}

	return 0;
}