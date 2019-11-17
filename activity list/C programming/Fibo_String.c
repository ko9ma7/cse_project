#include <stdio.h>
#include <string.h>

int main(void)
{
	char string1[1000] = "ab"; //  첫 번째 문자열 ab
	char string2[1000] = "xyy"; //  두 번째 문자열 xyy
	char string3[1000];

	int n;
	int i;

	printf("몇 번째 줄까지 생성하시겠습니까?: ");
	scanf("%d",&n);

	printf("[%d]%s\n", strlen(string1), string1); // 첫 번째 문자열 출력
	printf("[%d]%s\n", strlen(string2), string2); // 두 번째 문자열 출력

	for(i = 0; i <= n; i++)
	{
		strcat(string1, string2);
		// strcat(dst, src) 함수는 dst에 src의 값을 더해준다.
		// 따라서 string1에는 현재 string2가 덧붙여져있다.
		// -> i=0; string1 = abxyy, string2 = xyy
		// -> i=1; string1 = xyyabxyy, string2 = abxyy

		strcpy(string3, string1);
		// strcpy(dst, src) 함수는 dst에 src의 값을 복사해준다.
		// 따라서 string3에는 현재 string1의 값이 복사되었다.
		// -> i=0; string3 = abxyy
		// -> i=1; string3 = xyyabxyy

		strcpy(string1, string2);
		// string1에는 현재 string2의 값이 복사되었다.
		// -> i=0; string1 = xyy, string2 = xyy
		// -> i=1; string1 = abxyy, string2 = abxyy

		strcpy(string2, string3);
		// string2에는 현재 string3의 값이 복사되었다.
		// -> i=0; string2 = xyyabxyy, string3 = abxyy
		// -> i=1; string2 = xyyabxyy, string3 = xyyabxyy

		printf("[%d]%s\n", strlen(string3), string3);
		// string3의 현재 값을 출력해준다.
		// -> string1 = xyy, string2 = abxyy, string3 = abxyy
		// -> string1 = abxyy, string2 = xyyabxyy, string3 = xyyabxyy
	}
}