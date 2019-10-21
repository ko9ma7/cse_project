#include <iostream>
using namespace std;

int main(void) {
	int A, B;
	cin.tie(NULL);
	cout.tie(NULL);
	ios_base::sync_with_stdio(false);

	while (1) {
		cin >> A >> B;
		if (A == 0 && B == 0) break;
		else cout << A + B << '\n';
	}
}