#include <iostream>
#include <algorithm>
using namespace std;

int main(void) {
	cin.tie(NULL);
	cout.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	cin >> n;

	int *a = new int[n];
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}

	sort(a, a + n);

	long long x_pos = 0, y_pos = 0;
	// 나무의 개수가 짝수인 경우
	if (n % 2 == 0) {
		for (int i = 0; i < n; i++) {
			if (i < n/2) y_pos += a[i];
			else x_pos += a[i];
		}
	}
	else {
		for (int i = 0; i < n; i++) {
			if (i < (int)(n / 2)) y_pos += a[i];
			else x_pos += a[i];
		}
	}

	cout << x_pos * x_pos + y_pos * y_pos << '\n';

	return 0;
}