#include <iostream>
#include <string>
#include <cstdlib>
using namespace std;

int main(void) {

	cin.tie(NULL);
	cout.tie(NULL);
	ios_base::sync_with_stdio(false);

	int l, x, r;
	string result;
	int num = 0;
	int answer = 0;

	cin >> l >> r;



	for (int x = l; x <= r; x++) {
		int count[10] = { 0 };
		bool isDifferent = true;

		result = to_string(x);

		for (int i = 0; i < result.length(); i++) {
			num = result[i] - '0';
			for (int j = 0; j < 10; j++) {
				if (num == j) {
					count[j] += 1;
				}
			}
		}

		for (int c = 0; c < 10; c++) {
			if (count[c] > 1) {
				isDifferent = false;
				break;
			}
		}

		if (isDifferent) {
			answer = atoi(result.c_str());
			break;
		}
		else answer = -1;
	}

	cout << answer << '\n';

}