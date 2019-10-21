#include <iostream>
using namespace std;

int main(void) {

	cin.tie(NULL);
	cout.tie(NULL);
	ios_base::sync_with_stdio(false);

	int T, a, b, c, d, k;

	// testcase
	cin >> T;
	for (int t = 0; t < T; t++) {
		/*
			a: max lecture
			b: max practical
			c: lecture per pencil
			d: practical per pen
			e: limit of pencilcase
		*/
		cin >> a >> b >> c >> d >> k;
		int x, y;
		bool isAnswer1 = false, isAnswer2 = true;

		for (int i = 1; i <= k; i++){
			if (c * i >= a && d * (k-i) >= b) {
				isAnswer1 = true;
				x = i;
				y = k-i;
			}
			else isAnswer2 = false;
		}

		if (isAnswer1 == false && isAnswer2 == false) cout << -1 << '\n';
		else cout << x << ' ' << y << '\n';
	}

	return 0;
}