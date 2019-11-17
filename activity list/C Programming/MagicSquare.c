#include <stdio.h>

#define DEBUG
#define DECLINE(i) i-- // 행, 열의 증감
#define INCLINE(i) i++ // 행, 열의 증가
#define RAISE(num) ++num // 원소의 증가
// 홀수 마방진 메소드
#define METHOD_ONE(n, row, col) row < 0 || col > n - 1 // 행의 값이나 열의 값이 범위를 넘어갈 경우
#define METHOD_TWO(n, row, col) row < 0 && col < n // 행의 값만 범위를 넘어갈 경우
#define METHOD_THREE(n, row, col) row >= 0 && col > n - 1 // 열의 값만 범위를 넘어갈 경우
#define METHOD_FOUR(n, row, col) row < 0 && col > n - 1 // 행과 열의 값 모두 범위를 넘어갈 경우
// 4의 배수 짝수 마방진 메소드
#define METHOD_FIVE(row, col, p, q, r) ((row == p) && (col >= q && col <= r))
#define METHOD_SIX(row, col, p, q, r) ((row >= q && row <= r) && (col == p))
// 4의 배수가 아닌 짝수 마방진 메소드
#define METHOD_SEVEN(row, col, k) ((row >= 0 && row <= k - 1) && (col >= k + 1 && col <= 3 * k))
#define METHOD_EIGHT(row, col, k) ((col >= 0 && col <= k - 1) && (row >= k+1 && row <= 3 * k))
#define METHOD_NINE(row, col, k) (row >= k + 1 && row <= 3 * k) && col == k
#define METHOD_TEN(row, col, k) row == k || row == 3 * k + 1
#define METHOD_ELEVEN(row, col, k) col == k || col == 3 * k + 1
#define METHOD_TWELVE(row, col, k) col == k || (col >= 3 * k + 1 && col <= n - 1)
#define METHOD_THIRTEEN(row, col, k) (row == 0 && (col >= k + 1 && col <= 2 * k))
#define METHOD_FOURTEEN(row, col, k) (row == k + 1 && (col >= 0 && col <= k - 1))

void oddMagicSquare(int m[][100], int, int, int, int);
void fourthEvenMagicSquare(int m[][100], int, int, int, int);
void notFourthEvenMagicSquare(int m[][100], int, int, int, int);
void PrintMagicSquare(int m[][100], int, int, int);

int main(void) {
	int n; // 몇 차 행렬을 만들지 정해주는 변수
	int magicSquare[100][100] = { 0 };
	int row = 0, col = 0; // 각각 행, 열
	int magicNum = 1; // n x n


    // 사용자로부터 몇 차 행렬을 만들지 정하기
	printf("input number: "); scanf_s("%d", &n);

	if(n % 2 != 0) {
		oddMagicSquare(magicSquare, row, col, n, magicNum);
		PrintMagicSquare(magicSquare, row, col, n);
	}
	else if(n % 4 == 0) {
		fourthEvenMagicSquare(magicSquare, row, col, n, magicNum);
		PrintMagicSquare(magicSquare, row, col, n);
	}
	else {
		notFourthEvenMagicSquare(magicSquare, row, col, n, magicNum);
		PrintMagicSquare(magicSquare, row, col, n);
	}

	return 0;
}
// 홀수 마방진
void oddMagicSquare(int magicSquare[][100], int row, int col, int n, int magicNum) {
	row = 0; // 첫 번째 행의 가운데 원소는 1
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
}
// 4의 배수 짝수 마방진
void fourthEvenMagicSquare(int magicSquare[][100], int row, int col, int n, int magicNum) {
	int p, q, r; // 대칭을 이루어 주기 위한 범위의 변수(밑에 부가 설명)
	int magicCnt = 0; // 시행 횟수 - 4x4 행렬은 8번, 8x8 행렬은 32번, 16x16 행렬은 128번 ...
	int count; // 몇 번 시행할지(while문 탈출 조건)
	int remainder; // 위의 탈출 조건에 보조 역할
	int temp; // 바꿔줄 때 필요한 변수

#ifdef DEBUG
#ifdef INCLINE
#ifdef RAISE
#ifdef METHOD_FIVE
#ifdef METHOD_SIX

	// 배열의 모든 원소는 n * n까지 순차적으로 초기화
	for(row = 0; row < n; INCLINE(row))
	{
		for(col = 0; col < n; INCLINE(col))
		{
			magicSquare[row][col] = magicNum;
			RAISE(magicNum);
		}
	}

	row = 0;
	col = 0;
	p = 0;
	q = n / 4; // 규칙을 통해 알아냄
	r = n - (q + 1); // 규칙을 통해 알아냄
	remainder = n / 4; // 규칙을 통해 알아냄
	count = n * remainder; // 규칙을 통해 알아냄

	while(magicCnt != count)
	{
		for(p = 0; p < n / 4; RAISE(p))
		{
			for(row = 0; row < n; INCLINE(row))
			{
				for(col = 0; col < n; INCLINE(col))
				{
					if(METHOD_FIVE(row, col, p, q, r) || METHOD_SIX(row, col, p, q, r))
					{
						// swap하기
						temp = magicSquare[row][col];
						magicSquare[row][col] = magicSquare[(n - row) - 1][(n - col) - 1];
						magicSquare[(n - row) - 1][(n - col) - 1] = temp;
						RAISE(magicCnt);
					}
				}
			}
		}
	}
#endif
#endif
#endif
#endif
#endif
}
// 4의 배수가 아닌 짝수 마방진
void notFourthEvenMagicSquare(int magicSquare[][100], int row, int col, int n, int magicNum) {
	int k;
	int temp;

#ifdef INCLINE
#ifdef RAISE
#ifdef METHOD_SEVEN
#ifdef METHOD_EIGHT
#ifdef METHOD_NINE
#ifdef METHOD_TEN
#ifdef METHOD_ELEVEN
#ifdef METHOD_TWELVE
#ifdef METHOD_THIRTEEN
#ifdef METHOD_FOURTEEN

	for(row = 0; row < n; INCLINE(row))
	{
		for(col = 0; col < n; INCLINE(col))
		{
			magicSquare[row][col] = magicNum;
			RAISE(magicNum);
		}
	}

	// k : 1 : 2k : 1 : k로 행과 열의 선을 구분

	k = (n - 2) / 4;

	// B와 H, D와 F의 원점 대칭

	for(row = 0; row  < n; INCLINE(row))
	{
		for(col = 0; col < n; INCLINE(col))
		{
			if(METHOD_SEVEN(row, col, k) || METHOD_EIGHT(row, col, k))
			{
				temp = magicSquare[row][col];
				magicSquare[row][col] = magicSquare[(n - row) - 1][(n - col) - 1];
				magicSquare[(n - row) - 1][(n - col) - 1] = temp;
			}
		}
	}

	// 세로 경계선의 상하 반전(경계선 제외)

	col = 3 * k + 1;

	for(row = 0; row < n / 2; INCLINE(row))
	{
		if(row == k)
		{
			continue;
		}

		temp = magicSquare[row][col];
		magicSquare[row][col] = magicSquare[(n - row) - 1][col];
		magicSquare[(n - row) - 1][col] = temp;
	}

	// 세로 경계선의 교차 지점의 안쪽 숫자들 좌우 반전

	for(row = 0; row < n; INCLINE(row))
	{
		for(col = 0; col < n / 2; INCLINE(col))
		{
			if(METHOD_NINE(row, col, k))
			{
				if(row == 2 * k + 1) // 경계선의 바로 밑에 칸만 제외
				{
					continue;
				}
				temp = magicSquare[row][col];
				magicSquare[row][col] = magicSquare[row][(n - col) - 1];
				magicSquare[row][(n - col) - 1] = temp;
			}
		}
	}

	// 가로 경계선 교차점 제외하고 좌우 반전

	for(row = 0; row < n; INCLINE(row)) // 행은 1행부터 n행까지 모두 쓰기에
	{
		for(col = 0; col < n / 2; INCLINE(col)) // 열은 반만 필요하므로
		{
			if(METHOD_TEN(row, col,k))
			{
				if(METHOD_ELEVEN(row, col, k)) // 경계선을 제외하고
				{
					continue;
				}

				temp = magicSquare[row][col];
				magicSquare[row][col] = magicSquare[row][(n - col) - 1];
				magicSquare[row][(n - col) - 1] = temp;
			}
		}
	}

	// k개의 칸만 제외하고 나머지 모두 상하 반전(맨 오른쪽 칸들을 기준으로 잡음)

	for(row = 0; row < n / 2; INCLINE(row)) // 행은 반만 필요하므로
	{
		for(col = 0; col < n; INCLINE(col)) // 열은 1열부터 n열까지 모두 쓰기에
		{
			if(METHOD_TEN(row, col, k))
			{
				if(METHOD_TWELVE(row, col, k))
				{
					continue;
				}

				temp = magicSquare[row][col];
				magicSquare[row][col] = magicSquare[(n - row) - 1][col];
				magicSquare[(n - row) - 1][col] = temp;
			}
		}
	}

	// B영역의 첫 행을 좌우 반전, D영역과 F영역의 첫 행을 좌우 반전

	for(row = 0; row < n / 2; INCLINE(row))
	{
		for(col = 0; col < n / 2; INCLINE(col))
		{
			if(METHOD_THIRTEEN(row, col, k) || METHOD_FOURTEEN(row, col, k))
			{
				temp = magicSquare[row][col];
				magicSquare[row][col] = magicSquare[row][(n - col) - 1];
				magicSquare[row][(n - col) - 1] = temp;
			}
		}
	}

#endif
#endif
#endif
#endif
#endif
#endif
#endif
#endif
#endif
#endif
}
// 출력 함수
void PrintMagicSquare(int magicSquare[][100], int row, int col, int n) {
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
}