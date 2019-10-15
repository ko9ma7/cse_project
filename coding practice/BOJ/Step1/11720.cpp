#include <iostream>
#include <string>
using namespace std;

int main(void) {
	int N, temp;
	int sum = 0;
	char num;

	cin >> N;
	cin.get(); // 마지막에 개행문자를 없애기 위해

	for (int i = 0; i < N; i++) {
		cin.get(num);	  // 문자로 입력받은 숫자를 하나씩 변수에 저장
		temp = num - '0'; // 문자를 숫자로 변환해주는 방법
		sum += temp;
	}

	cout << sum << endl;

	return 0;
}