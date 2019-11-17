// 마방진
// 가로, 세로, 대각선 모두 각각 더했을 때 같은 것
// 짝수 마방진 혹은 홀수 마방진만 하기
// 예시) 홀수 마방진
// 홀수 input : 3 -> 3 x 3 마방진

// 홀수 마방진 푸는 법
// 첫 번째 행의 한가운데 열에 1을 넣는다.
// 다음 숫자를 대각선 방향으로 오른쪽 위 칸에 넣는다.
// 이때 해당하는 칸이 마방진의 위쪽으로 벗어난 경우에는 반대로 가장 아래쪽의 칸으로,
// 마방진의 오른쪽으로 벗어나는 경우는 가장 왼쪽의 칸으로 각각 한번 더 이동한다.
// 그리고 오른쪽인 동시에 위쪽으로 벗어나는 경우 및 오른쪽 위에 다른 숫자가 이미 있는 경우에는 오른쪽위 대신 원래 칸의 한 칸 아래에 넣는다

// input : 3
// 8 1 6
// 3 5 7
// 4 9 2

// input : 5
// 17  24   1   8  15
// 23   5   7  14  16
//  4   6  13  20  22
// 10  12  19  21   3
// 11  18  25   2   9

// 예시) 짝수 마방진
// 짝수 input : 4 -> 4 x 4 마방진

// 짝수 마방진 푸는 법
// input이 4라면 1:2:1로, 8이라면 2:4:2로 설정(4의 배수만, 그 이외의 배수는 다른 방법으로)
// 가로, 세로를 1:2:1로 나누어서 순차적으로 넣은 수에 대해서 가장 끝에 숫자들만 냅두고 고정

// input : 4
//  1)  2   3  (4
//  5  (6   7)   8
//  9 (10  11)  12
// 13) 14  15 (16

// 위와 같이 고정한 후, 2를 15와 바꾸고, 2을 14와, 5를 12와, 9를 8과 바꿔주면 된다



// 홀수 마방진
#include <stdio.h>
#define DEBUG
#define DECLINE(i) i-- // 행, 열의 증감
#define INCLINE(i) i++ // 행, 열의 증가
#define RAISE(num) ++num // 원소의 증가

#define METHOD_ONE(n, row, col) row < 0 || col > n - 1 // 행의 값이나 열의 값이 범위를 넘어갈 경우
#define METHOD_TWO(n, row, col) row < 0 && col < n // 행의 값만 범위를 넘어갈 경우
#define METHOD_THREE(n, row, col) row >= 0 && col > n - 1 // 열의 값만 범위를 넘어갈 경우
#define METHOD_FOUR(n, row, col) row < 0 && col > n - 1 // 행과 열의 값 모두 범위를 넘어갈 경우

int main(void) {
	int n; // 몇 차 행렬을 만들지 정해주는 변수
	int magicSquare[10][10] = { 0 };
	int row, col;
	int magicNum = 1; // n x n

    // 사용자로부터 몇 차 행렬을 만들지 정하기(홀수 마방진만)
	printf("input(odd number): "); scanf("%d", &n);
	// 첫 번째 행의 가운데 원소는 1
	row = 0;
	col = n / 2;
	magicSquare[row][col] = magicNum;

	while(magicNum <= n * n) // magicNum > 9
	{
#ifdef DECLINE
#ifdef INCLINE
#ifdef RAISE
		DECLINE(row); INCLINE(col);
		if(METHOD_ONE(n, row, col) || magicSquare[row][col] != 0) // 매크로 METHOD_ONE 실행 혹은 숫자가 채워져 있는 경우
		{
			if(METHOD_TWO(n, row, col)) // 매크로 METHOD_TWO 실행
			{
				row = n - 1; // 행이 가장 마지막으로 이동
			    magicSquare[row][col] = RAISE(magicNum);
			}
			else if(METHOD_THREE(n, row, col)) // 매크로 METHOD_THREE 실행
			{
				col = 0;
				magicSquare[row][col] = RAISE(magicNum);
			}
			else if(magicSquare[row][col] != 0) // 숫자가 채워져 있는 경우
			{
				INCLINE(row); INCLINE(row); DECLINE(col); // 바로 밑에 칸에 숫자를 넣어줌
				magicSquare[row][col] = RAISE(magicNum);
			}
			else if(METHOD_FOUR(n, row, col)) // 매크로 METHOD_FOUR 실행
			{
				INCLINE(row); INCLINE(row); DECLINE(col); // 바로 밑에 칸에 숫자를 넣어줌
				magicSquare[row][col] = RAISE(magicNum);
			}
			else
			{
				continue;
			}
		}
		else
		{
			magicSquare[row][col] = RAISE(magicNum);
		}
	}
#endif
#endif
#endif

#ifdef DEBUG
	for(row = 0; row < n; INCLINE(row))
	{
		for(col = 0; col < n; INCLINE(col))
		{
			printf("%3d ", magicSquare[row][col]);
		}
		printf("\n");
	}
#endif

	return 0;
}