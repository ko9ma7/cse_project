#include <cstdio>
// 문제 1번
int sumAlgorithmA(int n) {
	int sum = 0;
	sum = n * (n + 1) / 2;

	return sum;
}

int sumAlgorithmB(int n) {
	int sum = 0;
	for(int i = 1; i <= n; i++) sum += i;

	return sum;
}

int sumAlgorithmC(int n) {
	int sum = 0;
	for(int i = 1; i <= n; i++)
		for(int j = 1; j <= i; j++)
			sum += 1;

	return sum;
}

void main(void) {
	// 문제 2번
	int ex_num = 30;
	printf("%d\n", sumAlgorithmA(ex_num));
	printf("%d\n", sumAlgorithmB(ex_num));
	printf("%d\n", sumAlgorithmC(ex_num));
}