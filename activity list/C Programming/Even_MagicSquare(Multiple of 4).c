// 짝수 마방진(4의 배수)
#include <stdio.h>
#define DEBUG
#define INCLINE(i) i++ // 행, 열의 증가
#define RAISE(num) ++num // 원소의 증가

#define METHOD_ONE(row, col, p, q, r) ((row == p) && (col >= q && col <= r))
#define METHOD_TWO(row, col, p, q, r) ((row >= q && row <= r) && (col == p))

int main(void) {
    int n; // 몇 차 행렬을 만들지 정해주는 변수
	int magicSquare[100][100] = { 0 };
	int row, col; // 각각 행, 열
	int p, q, r; // 대칭을 이루어 주기 위한 범위의 변수(밑에 부가 설명)
	int magicNum = 1; // n x n
	int magicCnt = 0; // 시행 횟수 - 4x4 행렬은 8번, 8x8 행렬은 32번, 16x16 행렬은 128번 ...
	int count; // 몇 번 시행할지(while문 탈출 조건)
	int remainder; // 위의 탈출 조건에 보조 역할
	int temp; // 바꿔줄 때 필요한 변수

#ifdef DEBUG
#ifdef INCLINE
#ifdef RAISE
#ifdef METHOD_ONE
#ifdef METHOD_TWO

    // 사용자로부터 몇 차 행렬을 만들지 정하기(4의 배수 마방진만)
	printf("input(Fourth number): "); scanf("%d", &n);

	// 배열의 모든 원소는 n * n까지 순차적으로 초기화
	for(row = 0; row < n; INCLINE(row))
	{
		for(col = 0; col < n; INCLINE(col))
		{
			magicSquare[row][col] = magicNum;
			RAISE(magicNum);
		}
	}

	// 우선, 제가 생각한 알고리즘은 대칭을 이용하였습니다. 각 차수 행렬마다 쓰이는 원소가 정해진 패턴이 있고
	// 정방행렬이라는 것에 중점을 둔다면 대각선을 기준으로 대칭을 사용하면 쉽게 풀 수 있습니다.
	// 4x4 행렬의 경우, row = 0일때, 1<= col <=2의 범위와 1<= col <= 2일때, col = 0인 범위에서만 행렬의
        // 행렬의 대칭이 이루어집니다. 또, 8x8 행렬의 경우, row = 0일때, 2<= col <= 5의 범위와 row = 1일때, 2<= col <= 5의 범위와
	// 2 <= row <= 5일때, col = 0의 범위, 2 <= row <= 5일때, col = 1의 범위에서 가능합니다. 마찬가지로 12차 행렬에서도
	// 이런 식으로 규칙을 찾아볼 수 있습니다. 따라서 row나 col의 범위를 지정해주기 위해서 p와 q의 변수를 선언해주었습니다.

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
					if(METHOD_ONE(row, col, p, q, r) || METHOD_TWO(row, col, p, q, r))
					{
						printf("row:%d , col:%d, count:%d\n", row, col, magicCnt); // 현재 상황 출력
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

	printf("\n");

	for(row = 0; row < n; INCLINE(row))
	{
		for(col = 0; col < n; INCLINE(col))
		{
			printf("%3d ", magicSquare[row][col]);
		}
		printf("\n");
	}

#endif
#endif
#endif
#endif
#endif

	return 0;
}
