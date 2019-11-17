#include <cstdio>
#include <cstring>

int palindrome(char* str, int len);

void main() {
	char str[100];
	printf("문자열 입력: ");
	scanf("%s", str);
	if (palindrome(str, strlen(str))) printf("회문입니다\n");
	else printf("회문이 아닙니다\n");

}

int palindrome(char* str, int len) {
	char first = *str; // 시작 문자
	char last = *(str + (len - 1)); // 마지막 문자

	if (first == last) {
		if (len <= 1) return 1;
		return palindrome(str + 1, len - 2);
	}
	return 0;
}