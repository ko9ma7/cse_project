#include <cstdio>
#include <cstdlib>
#include <ctime>

// 문제 3번
/*
   시간 복잡도는 지난 시간에 교수님의 강의 자료에 있는 실행시간 측정 코드를 이용해서
   구할 수 있다. clock함수를 사용하기 위해 <cstdlib>, <ctime> 라이브러리를 포함하고,
   처음 시작과 끝의 변수를 하나씩 정해서 함수가 작동되는 시간을 측정한다. sumAlgorithmA 함수의
   경우는 값이 증가할 수록 n제곱의 값이 더 큰 영향을 주기 때문에 빅오 표기법으로 O(n^2)으로
   표기할 수 있다. sumAlgorithmB 함수의 경우는 값이 증가할 수록 n의 값이 더 큰 영향을 주기 때문에
   빅오 표기법으로 O(n)으로 표기할 수 있다. 마지막으로 sumAlgorithmC 함수의 경우는 값이 증가할
   수록 상수의 값이 제일 큰 영향을 끼치므로 빅오 표기법으로 O(1)로 표기할 수 있다.
*/
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

int BigOnn(int n) {
	int sum = 0;
	for(int i = 1; i <= n; i++)
		for(int j = 1; j <= i; j++)
			sum += 1;

	return sum;
}

void main(void) {

	// 문제 4번
	clock_t start1, finish1;
	clock_t start2, finish2;
	clock_t start3, finish3;

	double duration1;
	double duration2;
	double duration3;

	for(int n = 0; n < 10000000; n += 2000) {
		start1 = clock();
		// 시간 재는 동안에는 printf문을 사용하지 말 것
		BigO1(n);
		finish1 = clock();

		duration1 = (double)(finish1 - start1) / CLOCKS_PER_SEC;
		////////////////////////////////////////////////////////
		start2 = clock();
		BigOn(n);
		finish2 = clock();

		duration2 = (double)(finish2 - start2) / CLOCKS_PER_SEC;
		////////////////////////////////////////////////////////
		start3 = clock();
		BigOnn(n);
		finish3 = clock();

		duration3 = (double)(finish3 - start3) / CLOCKS_PER_SEC;

		printf("%10.8f   %10.8f   %10.8f\n", duration1, duration2, duration3);
	}
}