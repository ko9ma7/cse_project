#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NUM 10 // Random 배열의 인덱스 변수

void merge_Upper(int *A, int *B, int *C) // 오름차순 정렬
{
	int i = 0, j = 0, k = 0;

	while(1)
	{
		if(A[i] < B[j]) // 만약 A의 원소가 작다면
		{
			C[k] = A[i];  // C 배열에 A 원소 대입
			k++;
			i++;
		}
		else // 만약 B의 원소가 작다면
		{
			C[k] = B[j]; // C 배열에 B 원소 대입
			k++;
			j++;
		}

		if(i == 5) // A 배열의 인덱스 값이 5이면(모두 사용했을 때)
		{
			for( ; k < 10; k++)
			{
				C[k] = B[j]; // C 배열의 나머지 부분을 B 배열의 원소로 채운다
				j++;
			}
			break;
		}

		if(j == 5) // B 배열의 인덱스 값이 5이면(모두 사용했을 때)
		{
			for( ; k < 10; k++)
			{
				C[k] = A[i]; // C 배열의 나머지 부분을 A 배열의 원소로 채운다
				i++;
			}
			break;
		}

	}
}

void merge_Lower(int *A, int *B, int *C) // 내림차순 정렬
{
	int i = 0, j = 0, k = 0;

	while(1)
	{
		if(A[i] > B[j]) // 만약 B의 원소가 작다면
		{
			C[k] = A[i];  // C 배열에 A 원소 대입
			k++;
			i++;
		}
		else // 만약 A의 원소가 작다면
		{
			C[k] = B[j]; // C 배열에 B 원소 대입
			k++;
			j++;
		}

		if(i == 5) // A 배열의 인덱스 값이 5이면(모두 사용했을 때)
		{
			for( ; k < 10; k++)
			{
				C[k] = B[j]; // C 배열의 나머지 부분을 B 배열의 원소로 채운다
				j++;
			}
			break;
		}

		if(j == 5) // B 배열의 인덱스 값이 5이면(모두 사용했을 때)
		{
			for( ; k < 10; k++)
			{
				C[k] = A[i]; // C 배열의 나머지 부분을 A 배열의 원소로 채운다
				i++;
			}
			break;
		}

	}
}


int main(void)
{
	int Random[NUM] = { 0 };
	int check[10] = { 0 }; // check[10] 배열로 Random[NUM] 배열의 원소가 중복되는지 확인
	int A[5] = { 0 };
	int B[5] = { 0 };
	int C[10] ={ 0 };
	int i = 0, j = 0, temp, random; // i와 j는 인덱스 변수, temp는 버블 정렬을 위한 swap 변수, random은 Random[NUM] 배열의 원소를 정하기 위한 변수
	int choice; // 오름차순 또는 내림차순
	srand((unsigned int)time(NULL));

	// Random[NUM] 배열의 원소 정하기(0부터 9까지 중복 없는 난수 배열)
	for(i = 0; i < NUM; )
	{
		random = rand() % 10; // 0부터 9까지 난수 발생
		if(check[random] == 0)
		{
			check[random] = 1; // 1의 값을 넣어주어 중복되지 않게 해줌
			Random[i] = random;
			i++;
		}
	}

	// Random[NUM] 배열을 반으로 잘라 A[5]와 B[5] 배열에 각각 원소를 넣어주기

	for(i = 0; i < 5; i++)
	{
		A[j] = Random[i];
		j++;
	}

	j = 0;

	for(i = 5; i < 10; i++)
	{
		B[j] = Random[i];
		j++;
	}

	// Random, A, B 배열 출력하기

	printf("Step 1. Random 배열 출력\n");
	printf("→ ");

	for(i = 0; i < 10; i++)
		printf("%d ",Random[i]);
	printf("\n\n");

	printf("Step 2. A 배열 출력\n");
	printf("→ ");

	for(i = 0; i < 5; i++)
		printf("%d ",A[i]);
	printf("\n\n");

	printf("Step 3. B 배열 출력\n");
	printf("→ ");

	for(i = 0; i < 5; i++)
		printf("%d ",B[i]);
	printf("\n\n");

	printf("어떤 정렬을 원하시나요?\n");
	printf("1.오름차순 정렬\n");
	printf("2.내림차순 정렬\n");
	printf("→ ");
	scanf("%d",&choice);
	printf("\n");

	switch(choice)
	{
	case 1:
		printf("*****오름차순 정렬*****\n\n");
		printf("Step 4. 정렬된 A 배열 출력}\n");
		printf("→ ");

		for(i = 0; i < 5; i++)
		{
			for(j = i; j < 5; j++)
			{
				if(A[i] > A[j])
				{
					temp = A[i];
					A[i] = A[j];
					A[j] = temp;
				}
				else
					continue;
			}
		}

		for(i = 0; i< 5; i++)
			printf("%d ",A[i]);
		printf("\n\n");

		printf("Step 5. 정렬된 B 배열 출력\n");
		printf("→ ");

		for(i = 0; i < 5; i++)
		{
			for(j = i; j < 5; j++)
			{
				if(B[i] > B[j])
				{
					temp = B[i];
					B[i] = B[j];
					B[j] = temp;
				}
				else
					continue;
			}
		}

		for(i = 0; i< 5; i++)
			printf("%d ",B[i]);
		printf("\n\n");

		// merge_Upper 함수를 이용해 C[10] 배열 출력하기

		printf("Step 6. 정렬된 C 배열 출력\n");
		printf("→ ");
		merge_Upper(A,B,C);

		for(i = 0; i < 10; i++)
			printf("%d ",C[i]);
		printf("\n\n");

		break;

	case 2:
		printf("*****내림차순 정렬*****\n\n");
		printf("Step 4. 정렬된 A 배열 출력}\n");
		printf("→ ");

		for(i = 0; i < 5; i++)
		{
			for(j = i; j < 5; j++)
			{
				if(A[i] < A[j])
				{
					temp = A[i];
					A[i] = A[j];
					A[j] = temp;
				}
				else
					continue;
			}
		}

		for(i = 0; i< 5; i++)
			printf("%d ",A[i]);
		printf("\n\n");

		printf("Step 5. 정렬된 B 배열 출력\n");
		printf("→ ");

		for(i = 0; i < 5; i++)
		{
			for(j = i; j < 5; j++)
			{
				if(B[i] < B[j])
				{
					temp = B[i];
					B[i] = B[j];
					B[j] = temp;
				}
				else
					continue;
			}
		}

		for(i = 0; i< 5; i++)
			printf("%d ",B[i]);
		printf("\n\n");

		// merge_Lower 함수를 이용해 C[10] 배열 출력하기

		printf("Step 6. 정렬된 C 배열 출력\n");
		printf("→ ");
		merge_Lower(A,B,C);

		for(i = 0; i < 10; i++)
			printf("%d ",C[i]);
		printf("\n\n");

		break;
	}

	return 0;
}