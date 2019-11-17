#include <stdio.h>
#include <math.h>

typedef struct Pointer1 {
	int x, y;
} Pointer1;

typedef struct Pointer2 {
	int x, y, z;
} Pointer2;

// 문제 1번. 몇 사분면에 위치해있는지 알려주는 함수
void whatDimension(Pointer1 p)
{
	if(p.x > 0 && p.y > 0)
		printf("제 1사분면\n");
	else if(p.x < 0 && p.y > 0)
		printf("제 2사분면\n");
	else if(p.x < 0 && p.y < 0)
		printf("제 3사분면\n");
	else if(p.x > 0 && p.y < 0)
		printf("제 4사분면\n");
	else
		printf("원점");
}
// 문제 2-1번. 두 점 사이의 거리
double distance(Pointer1 p1, Pointer1 p2)
{
	return sqrt(pow((p1.x - p2.x), 2.0) + pow((p1.y - p2.y), 2.0));
}
// 문제 2-2번. 벡터의 합
double vector_sum(Pointer1 p1, Pointer1 p2)
{
	return sqrt(pow((p1.x + p2.x), 2.0) + pow((p1.y + p2.y), 2.0));
}
// 문제 2-3번. 벡터의 내적
double vector_inner_product_space(Pointer1 p1, Pointer1 p2)
{
	return (p1.x * p2.x) + (p1.y * p2.y);
}
// 문제 3-1번. 벡터의 외적(좌표)
Pointer2 vector_outter_product(Pointer2 p1, Pointer2 p2)
{
	Pointer2 outter = { (p1.y * p2.z - p1.z * p2.y), (p1.z * p2.x - p1.x * p2.z), (p1.x * p2.y - p1.y * p2.x) };
	return outter;
}
// 문제 3-2번. 삼각형의 넓이
// 벡터의 외적으로 구하기
double triangleArea(Pointer1 p1, Pointer1 p2, Pointer1 p3)
{
	return 0.5 * abs(((p2.x - p1.x) * (p3.y - p1.y)) - ((p3.x - p1.x) * (p2.y - p1.y)));
}
// 문제 4번. 평면의 법선 벡터
void normal_vector(Pointer2 p1, Pointer2 p2, Pointer2 p3)
{
	Pointer2 pos1 = { (p2.x - p1.x), (p2.y - p1.y), (p2.z - p1.z) };
	Pointer2 pos2 = { (p3.x - p1.x), (p3.y - p1.y), (p3.z - p1.z) };
	Pointer2 normal_vector_pos = vector_outter_product(pos1, pos2);

	printf("법선 벡터 : (%d, %d, %d)\n", normal_vector_pos.x, normal_vector_pos.y, normal_vector_pos.z);
}
// 문제 5-1번. 직사각형인지 정사각형인지 확인하고, 사각형의 넓이, 둘레를 출력하는 함수
// 임의의 두 점을 잇고, 다른 두 점을 이으면 두개의 선분이 완성되고
// 두 선분의 벡터의 내적이 0이면 직교 & 두 선분의 길이가 같으면 정사각형
void isRectangle_Or_Square(Pointer1 p1, Pointer1 p2, Pointer1 p3, Pointer1 p4)
{
	Pointer1 pos1 = { (p1.x - p2.x), (p1.y - p2.y) };
	Pointer1 pos2 = { (p1.x - p3.x), (p1.y - p3.y) };
	Pointer1 pos3 = { (p1.x - p4.x), (p1.y - p4.y) };
	Pointer1 pos4 = { (p2.x - p3.x), (p2.y - p3.y) };
	Pointer1 pos5 = { (p2.x - p4.x), (p2.y - p4.y) };
	Pointer1 pos6 = { (p3.x - p4.x), (p3.y - p4.y) };

	double vec_dis1 = distance(p1, p2);
	double vec_dis2 = distance(p1, p3);
	double vec_dis3 = distance(p1, p4);
	double vec_dis4 = distance(p2, p3);
	double vec_dis5 = distance(p2, p4);
	double vec_dis6 = distance(p3, p4);

	// p1, p2가 대각선이라고 기준을 잡았을 때, 두 선분의 내적이 0, 두 선분의 길이가 같다면 정사각형
	if(vector_inner_product_space(pos1, pos6) == 0 && vec_dis1 == vec_dis3)
	{
		printf("정사각형\n");
		printf("사각형의 넓이: %d\n", (int)(vec_dis2 * vec_dis3));
		printf("사각형의 둘레: %d\n", (int)(vec_dis2 * 4));
	}
	// p1, p2가 대각선이라고 기준을 잡았을 때, 두 선분의 내적이 0이 아니고, 두 선분의 길이가 같다면 직사각형
	else if(vector_inner_product_space(pos1, pos6) != 0 && vec_dis1 == vec_dis3)
	{
		printf("직사각형\n");
		printf("사각형의 넓이: %d\n", (int)(vec_dis2 * vec_dis3));
		printf("사각형의 둘레: %d\n", (int)((vec_dis2 + vec_dis3) * 2));
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////


	// p1, p3가 대각선이라고 기준을 잡았을 때, 두 선분의 내적이 0, 두 선분의 길이가 같다면 정사각형
	else if(vector_inner_product_space(pos2, pos5) == 0 && vec_dis2 == vec_dis5)
	{
		printf("정사각형\n");
		printf("사각형의 넓이: %d\n", (int)(vec_dis1 * vec_dis3));
		printf("사각형의 둘레: %d\n", (int)(vec_dis1 * 4));
	}
	// p1, p3가 대각선이라고 기준을 잡았을 때, 두 선분의 내적이 0이 아니고, 두 선분의 길이가 같다면 직사각형
	else if(vector_inner_product_space(pos2, pos5) != 0 && vec_dis2 == vec_dis5)
	{
		printf("직사각형\n");
		printf("사각형의 넓이: %d\n", (int)(vec_dis1 * vec_dis3));
		printf("사각형의 둘레: %d\n", (int)((vec_dis1 + vec_dis3) * 2));
	}


	///////////////////////////////////
	// p1, p4가 대각선이라고 기준을 잡았을 때, 두 선분의 내적이 0, 두 선분의 길이가 같다면 정사각형
	else if(vector_inner_product_space(pos3, pos4) == 0 && vec_dis3 == vec_dis4)
	{
		printf("정사각형\n");
		printf("사각형의 넓이: %d\n", (int)(vec_dis1 * vec_dis2));
		printf("사각형의 둘레: %d\n", (int)(vec_dis1 * 4));
	}
	// p1, p4가 대각선이라고 기준을 잡았을 때, 두 선분의 내적이 0이 아니고, 두 선분의 길이가 같다면 직사각형
	else if(vector_inner_product_space(pos3, pos4) != 0 && vec_dis3 == vec_dis4)
	{
		printf("직사각형\n");
		printf("사각형의 넓이: %d\n", (int)(vec_dis1 * vec_dis2));
		printf("사각형의 둘레: %d\n", (int)((vec_dis1 + vec_dis2) * 2));
	}
	// 두 선분의 내적이 0이 아니고, 두 선분의 길이가 다르다면 예외
	else
		printf("예외처리\n");
}

int main(void)
{
	Pointer1 pos1 = { 3, 5 };
	Pointer1 pos2 = { 1, 5 };
	Pointer1 pos3 = { 3, 1 };
	Pointer1 pos4 = { 1, 1 };

	Pointer2 pos5 = { 1, 2, 3 };
	Pointer2 pos6 = { 2, 6, 7 };
	Pointer2 pos7 = { 3, 4, 5 };

	// 문제 1번. 몇 사분면인지 구하기
	printf("문제 1번. pos1은 몇 사분면입니까? ");
	whatDimension(pos1);
	printf("\n");

	// 문제 2번. 두 점 사이의 거리, 벡터 합, 벡터 내적
	printf("문제 2-1번. pos1과 pos2의 거리는 얼마입니까? ");
	printf("%.2f\n", distance(pos1, pos2));
	printf("\n");
	printf("문제 2-2번. pos1과 pos2의 벡터의 합은 얼마입니까? ");
	printf("%.2f\n", vector_sum(pos1, pos2));
	printf("\n");
	printf("문제 2-3번. pos1과 pos2의 벡터의 내적은 얼마입니까? ");
	printf("%.2f\n", vector_inner_product_space(pos1, pos2));
	printf("\n");

	// 문제 3번. 삼각형의 넓이
	printf("문제 3번. pos1과 pos2, pos3가 이루는 삼각형의 넓이는 얼마입니까? ");
	printf("%.2f\n", triangleArea(pos1, pos2, pos3));
	printf("\n");

	// 문제 4번. 평면의 법선 벡터
	printf("문제 4번. pos5와 pos6, pos7의 법선 벡터는 얼마입니까? ");
	normal_vector(pos5, pos6, pos7);
	printf("\n");

	// 문제 5번. 직사각형인지 정사각형인지/ 사각형의 넓이/ 사각형의 둘레
	printf("문제 5번. pos1과 pos2, pos3, pos4는 어떤 사각형이고, 넓이와 둘레는 얼마입니까? ");
	isRectangle_Or_Square(pos1, pos2, pos3, pos4);

	return 0;
}
