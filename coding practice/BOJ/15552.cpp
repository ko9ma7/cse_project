#include <iostream>
using namespace std;

int main(void) {
	int T, A, B;

	cin.tie(NULL);
	cout.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> T;

	for (int i = 0; i < T; i++) {
		cin >> A >> B;
		cout << A + B << '\n';
	}

	return 0;
}