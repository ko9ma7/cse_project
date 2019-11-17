#include <stdio.h>

int main(void)
{
	int Snail_Array[50][50] = {0}; //아직 동적할당을 안배웠기에 정적할당을 해줌
	int num, size, origin_num;
	// i와 j는 배열의 인덱스
	// k는 배열의 정수 값(1부터 num까지의 수)
	// t는 아래쪽으로 가는 코드의 인덱스 변수
	// s는 왼쪽으로 가는 코드의 인덱스 변수
	// u는 오른쪽으로 가는 코드의 인덱스 변수
	// w는 위쪽으로 가는 코드의 인덱스 변수
	int i = 0, j, k = 1, t = 0,s = 0, u = 0, w = 0;

	printf("숫자를 입력 : ");
	scanf("%d",&num);

	size = num * num; // size에 num*num의 값을 할당하여 증감연산으로 while문 탈출 조건을 생성해준다.
	origin_num = num;

	for(j=0; j<num; j++) //처음 오른쪽
	{
		Snail_Array[i][j] = k++;
		printf("%3d ", Snail_Array[i][j]);
		size--;
	}
	j--;k--;
	printf("\n");
	while(1)
	{
		for(i=t; i<num; i++) //아래쪽
		{
			Snail_Array[i][j] = k++;
			printf("%3d ", Snail_Array[i][j]);
			size--;
		}
		i--;k--;t++;size++;
		printf("\n");
		if(size == 0)
			break;

		for(j=(num-1); j>=s; j--) //왼쪽
		{
			Snail_Array[i][j] = k++;
			printf("%3d ", Snail_Array[i][j]);
		        size--;
		}
		j++;k--;s++;size++;num--;
		printf("\n");
		if(size == 0)
			break;

		for(i=num; i>w; i--) //위쪽
		{

			Snail_Array[i][j] = k++;
			printf("%3d ", Snail_Array[i][j]);
			size--;
		}
		i++;k--;w++;size++;
		printf("\n");
		if(size == 0)
			break;

		for(j=u; j<num; j++) //오른쪽
		{
			Snail_Array[i][j] = k++;
			printf("%3d ", Snail_Array[i][j]);
			size--;
		}
		j--;k--;u++;size++;
		printf("\n");
		if(size == 0)
			break;
	}

	printf("\n");

	for(i=0; i<origin_num; i++)
	{
		for(j=0; j<origin_num; j++)
		{
			printf("%3d ", Snail_Array[i][j]);
		}
		printf("\n");
     }

	return 0;
}

/*
#include <stdio.h>
#include <Windows.h>

void Print(char arr[50][50],int length);

int main(void)
{
	char Snail_Array[50][50] = {" "}; //아직 동적할당을 안배웠기에 정적할당을 해줌
	int num, size, origin_num;
	// i와 j는 배열의 인덱스
	// k는 배열의 정수 값(1부터 num까지의 수)
	// t는 아래쪽으로 가는 코드의 인덱스 변수
	// s는 왼쪽으로 가는 코드의 인덱스 변수
	// u는 오른쪽으로 가는 코드의 인덱스 변수
	// w는 위쪽으로 가는 코드의 인덱스 변수
	int i = 0, j, t = 0,s = 0, u = 0, w = 0;

	printf("숫자를 입력 : ");
	scanf("%d",&num);

	size = num * num; // size에 num*num의 값을 할당하여 증감연산으로 while문 탈출 조건을 생성해준다.
	origin_num = num;

	for(j=0; j<num; j++) //처음 오른쪽
	{
		Snail_Array[i][j] = '*';
		Print(Snail_Array, origin_num);
		size--;
	}
	j--;

	while(1)
	{
		for(i=t; i<num; i++) //아래쪽
		{
			Snail_Array[i][j] = '*';
			Print(Snail_Array, origin_num);
			size--;
		}
		i--;t++;size++;
		printf("\n");
		if(size == 0)
			break;

		for(j=(num-1); j>=s; j--) //왼쪽
		{
			Snail_Array[i][j] = '*';
			Print(Snail_Array, origin_num);
		    size--;
		}
		j++;s++;size++;num--;
		printf("\n");
		if(size == 0)
			break;

		for(i=num; i>w; i--) //위쪽
		{

			Snail_Array[i][j] = '*';
			Print(Snail_Array, origin_num);
			size--;
		}
		i++;w++;size++;
		printf("\n");
		if(size == 0)
			break;

		for(j=u; j<num; j++) //오른쪽
		{
			Snail_Array[i][j] = '*';
			Print(Snail_Array, origin_num);
			size--;
		}
		j--;u++;size++;
		printf("\n");
		if(size == 0)
			break;
	}

	return 0;
}

void Print(char arr[50][50], int length)
{
        int i, j;

	for(i = 0; i < length; i++)
	{
		for(j = 0; j < length; j++)
		{
			printf("%3c ", arr[i][j]);
		}
		printf("\n");
	}

	Sleep(1000);
	system("cls");
}
*/