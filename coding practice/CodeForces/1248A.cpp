#include <iostream>
using namespace std;

int main(void) {
	cin.tie(NULL);
	cout.tie(NULL);
	ios_base::sync_with_stdio(false);

	int T, N, M, p, q;

	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> N;
		int *P = new int[N];
		for (int n = 0; n < N; n++) {
			cin >> P[n];
		}

		cin >> M;
		int *Q = new int[M];
		for (int m = 0; m < M; m++) {
			cin >> Q[m];
		}

		long long even_count1 = 0, odd_count1 = 0;
		for (int n = 0; n < N; n++) {
			if (P[n] % 2 == 0) even_count1++;
			else odd_count1++;
		}

		long long even_count2 = 0, odd_count2 = 0;
		for (int m = 0; m < M; m++) {
			if (Q[m] % 2 == 0) even_count2++;
			else odd_count2++;
		}

		cout << even_count1 * even_count2 + odd_count1 * odd_count2 << '\n';
	}

	return 0;
}