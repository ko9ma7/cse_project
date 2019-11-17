/*
#include <stdio.h>

int main(void)
{
	int magicSquare[100][100] = { 0 };
	int row, col;
	int n;
	int k;
	int temp;
	int magicNum = 1;

	printf("input(odd number): "); scanf("%d", &n);

	for(row = 0; row < n; row++)
	{
		for(col = 0; col < n; col++)
		{
			magicSquare[row][col] = magicNum;
			magicNum++;
		}
	}

	// k : 1 : 2k : 1 : k로 행과 열의 선을 구분

	k = (n - 2) / 4;

	// B와 H, D와 F의 원점 대칭

	for(row = 0; row  < n; row++)
	{
		for(col = 0; col < n; col++)
		{
			if(((row >= 0 && row <= k - 1) && (col >= k + 1 && col <= 3 * k)) || ((col >= 0 && col <= k - 1) && (row >= k+1 && row <= 3 * k)))
			{
				temp = magicSquare[row][col];
				magicSquare[row][col] = magicSquare[(n - row) - 1][(n - col) - 1];
				magicSquare[(n - row) - 1][(n - col) - 1] = temp;
			}
		}
	}

	// 세로 경계선의 상하 반전(경계선 제외)

	col = 3 * k + 1;

	for(row = 0; row < n / 2; row++)
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

	for(row = 0; row < n; row++)
	{
		for(col = 0; col < n / 2; col++)
		{
			if((row >= k + 1 && row <= 3 * k) && col == k)
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

	for(row = 0; row < n; row++) // 행은 1행부터 n행까지 모두 쓰기에
	{
		for(col = 0; col < n / 2; col++) // 열은 반만 필요하므로
		{
			if(row == k || row == 3 * k + 1)
			{
				if(col == k || col == 3 * k + 1) // 경계선을 제외하고
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

	for(row = 0; row < n / 2; row++) // 행은 반만 필요하므로
	{
		for(col = 0; col < n; col++) // 열은 1열부터 n열까지 모두 쓰기에
		{
			if(row == k || row == 3 * k + 1)
			{
				if(col == k || (col >= 3 * k + 1 && col <= n - 1))
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

	for(row = 0; row < n / 2; row++)
	{
		for(col = 0; col < n / 2; col++)
		{
			if((row == 0 && (col >= k + 1 && col <= 2 * k)) || (row == k + 1 && (col >= 0 && col <= k - 1)))
			{
				temp = magicSquare[row][col];
				magicSquare[row][col] = magicSquare[row][(n - col) - 1];
				magicSquare[row][(n - col) - 1] = temp;
			}
		}
	}

	// 출력

	for(row = 0; row < n; row++)
	{
		for(col = 0; col < n; col++)
		{
			printf("%3d ", magicSquare[row][col]);
		}
		printf("\n");
	}

	return 0;
}
*/

#include <stdio.h>
#define DEBUG
#define INCLINE(i) i++ // 행, 열의 증가
#define RAISE(num) ++num // 원소 증가

#define METHOD_ONE(row, col, k) ((row >= 0 && row <= k - 1) && (col >= k + 1 && col <= 3 * k))
#define METHOD_TWO(row, col, k) ((col >= 0 && col <= k - 1) && (row >= k+1 && row <= 3 * k))
#define METHOD_THREE(row, col, k) (row >= k + 1 && row <= 3 * k) && col == k
#define METHOD_FOUR(row, col, k) row == k || row == 3 * k + 1
#define METHOD_FIVE(row, col, k) col == k || col == 3 * k + 1
#define METHOD_SIX(row, col, k) col == k || (col >= 3 * k + 1 && col <= n - 1)
#define METHOD_SEVEN(row, col, k) (row == 0 && (col >= k + 1 && col <= 2 * k))
#define METHOD_EIGHT(row, col, k) (row == k + 1 && (col >= 0 && col <= k - 1))

int main(void)
{
	int magicSquare[100][100] = { 0 };
	int row, col;
	int n;
	int k;
	int temp;
	int magicNum = 1;

#ifdef DEBUG
#ifdef INCLINE
#ifdef RAISE
#ifdef METHOD_ONE
#ifdef METHOD_TWO
#ifdef METHOD_THREE
#ifdef METHOD_FOUR
#ifdef METHOD_FIVE
#ifdef METHOD_SIX
#ifdef METHOD_SEVEN
#ifdef METHOD_EIGHT

	printf("input(odd number): "); scanf("%d", &n);

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
			if(METHOD_ONE(row, col, k) || METHOD_TWO(row, col, k))
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
			if(METHOD_THREE(row, col, k))
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
			if(METHOD_FOUR(row, col,k))
			{
				if(METHOD_FIVE(row, col, k)) // 경계선을 제외하고
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
			if(METHOD_FOUR(row, col, k))
			{
				if(METHOD_SIX(row, col, k))
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
			if(METHOD_SEVEN(row, col, k) || METHOD_EIGHT(row, col, k))
			{
				temp = magicSquare[row][col];
				magicSquare[row][col] = magicSquare[row][(n - col) - 1];
				magicSquare[row][(n - col) - 1] = temp;
			}
		}
	}

	// 출력

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
#endif
#endif
#endif
#endif
#endif
#endif
	return 0;
}