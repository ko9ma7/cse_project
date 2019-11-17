#include <cstdio>
#include "Lab5-3-Location2D.h"

// 1. 반복적인 팩토리얼 계산 프로그램 ==> O(n)
int factorialIter(int n) {
	int result = 1;
	for (int k = n; k > 0; k--) result = result * k;
	return result;
}
// 2. 순환적인 팩토리얼 계산 프로그램 ==> O(n)
int factorial(int n) {
	printf("factorial(%d)\n", n);
	if (n == 1) return 1;
	else return n * factorial(n - 1);
}
// 3. 반복적인 거듭제곱 계산 프로그램 ==> O(n)
double slowPower(double x, int n) {
	double result = 1.0;
	for (int i = 0; i < n; i++) result = result * x;
	return result;
}
// 4. 순환적인 거듭제곱 계산 프로그램 ==> O(log2(n))
double power(double x, int n) {
	if (n == 0) return 1;
	else if (n % 2 == 0)              // n이 짝수인 경우
		return power(x * x, n / 2);
	else                              // n이 홀수인 경우
		return x * power(x * x, (n - 1) / 2);
}
// 5. 반복적인 피보나치수열 계산 프로그램 ==> O(n)
int fibIter(int n) {
	if (n < 2) return n;
	else {
		int temp, current = 1, last = 0;
		for (int i = 2; i <= n; i++) {
			temp = current;
			current += last;
			last = temp;
		}
		return current;
	}
}
// 6. 순환적인 피보나치수열 계산 프로그램 ==> O(2^n)
int fib(int n) {
	if (n == 0) return 0;
	if (n == 1) return 1;
	return (fib(n - 1) + fib(n - 2));
}
// 7. 하노이 탑
// n-1개의 원판을 A에서 B로 옮기고, n번째 원판을 A에서 C로 옮긴 다음,
// n-1개의 원판을 B에서 C로 옮긴다
void hanoiTower(int n, char from, char tmp, char to) {
	if (n == 1) printf("원판 1을 %c에서 %c으로 옮긴다.\n", from, to);
	else {
		hanoiTower(n - 1, from, to, tmp);
		printf("원판 %d을 %c에서 %c으로 옮긴다.\n", n, from, to);
		hanoiTower(n - 1, tmp, from, to);
	}
}
// 8. 순환을 이용한 미로 탐색 프로그램
const int MAZE_SIZE = 6;
char map[MAZE_SIZE][MAZE_SIZE] = {
	{ '1', '1', '1', '1', '1', '1' },
	{ 'e', '0', '1', '0', '0', '1' },
	{ '1', '0', '0', '0', '1', '1' },
	{ '1', '0', '1', '0', '1', '1' },
	{ '1', '0', '1', '0', '0', 'x' },
	{ '1', '1', '1', '1', '1', '1' }
};

bool isValidLoc(int r, int c) {
	if (r < 0 || c < 0 || r >= MAZE_SIZE || c >= MAZE_SIZE) return false;
	else return map[r][c] == '0' || map[r][c] == 'x';
}

Location2D locEntry, locExit;
bool done = false;

// 순환으로 구현한 미로 탐색 함수
void searchRecur(Location2D pt) {
	printf("(%d, %d) ", pt.getRow(), pt.getCol()); // 현재 위치 화면 출력
	if (done) return; // 이미 탐색이 성공했으면 return
	if ((pt.getRow() == locExit.getRow()) && (pt.getCol() == locExit.getCol())) { // 현재 위치가 출구이면 => 성공
		done = true;
		return;
	}

	int r = pt.getRow();
	int c = pt.getCol();
	map[r][c] = '.';

	if (isValidLoc(r - 1, c)) searchRecur(Location2D(r - 1, c));
	if (isValidLoc(r + 1, c)) searchRecur(Location2D(r + 1, c));
	if (isValidLoc(r, c - 1)) searchRecur(Location2D(r, c - 1));
	if (isValidLoc(r, c + 1)) searchRecur(Location2D(r, c + 1));
}