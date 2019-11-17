#include <cstdio>
#include <cstdlib>
#include <ctime>

int BigO1(int n) {
	int sum = 0;
	sum = n * (n + 1) / 2;

	return sum;
}

int BigOn(int n) {
	int sum = 0;
	for(int i = 1; i <= n; i++) sum += i;

	return sum;
}

void main(void) {

	// 문제 5번
	clock_t start1, finish1;
	clock_t start2, finish2;

	double duration1;
	double duration2;

	for(int n = 0; n < 10000000000; n += 9000000) {
		start1 = clock();
		BigO1(n);
		finish1 = clock();

		duration1 = (double)(finish1 - start1) / CLOCKS_PER_SEC;
		////////////////////////////////////////////////////////
		start2 = clock();
		BigOn(n);
		finish2 = clock();

		duration2 = (double)(finish2 - start2) / CLOCKS_PER_SEC;

		printf("%10.8f   %10.8f\n", duration1, duration2);
	}
}