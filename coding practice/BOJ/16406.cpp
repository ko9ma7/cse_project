#include <iostream>
#include <cmath>
#include <string>
using namespace std;

int main(void) {

	cin.tie(NULL);
	cout.tie(NULL);
	ios_base::sync_with_stdio(false);

	int k;
	string f;
	string m;

	cin >> k;
	cin.ignore(256, '\n');
	getline(cin, f);
	getline(cin, m);

	int cnt = 0;
	int questions = f.length();

	for (int i = 0; i < questions; i++) {
		if (f[i] == m[i]) cnt++;
	}

	// 최대한 친구가 맞은 개수 안에 자신과 같은 개수를 포함해야 한다.
	// 모든 문제 수 - |친구가 맞은 개수 - 같은 개수|
	cout << questions - abs(k - cnt);

	return 0;
}