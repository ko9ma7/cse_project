#include <cstdio>
#include <cstring>

int checkMatching(char* str, int len, int pos);
int getClosePos(char* str, int len, int pos);
bool count(char* str);
bool isMatching(char b, char e);

void main() {
	char* parenthesis = "(())()";

	if (count(parenthesis)) {
		printf("괄호 검사 시작\n");
		if (checkMatching(parenthesis, strlen(parenthesis), 0)) printf("회문입니다\n");
		else printf("괄호 검사 실패\n");
	} else {
		printf("괄호 검사 실패\n");
	}
}

bool count(char* str) {
	int openBraket = 0;
	int closeBraket = 0;
	char c = *str;

	while (c != NULL) {
		if (c == '(' || c == '{' || c == '[') openBraket++;
		if (c == ')' || c == '}' || c == ']') closeBraket++;
		c = *(++str);
	}

	printf("%d %d\n", openBraket, closeBraket);
	if (openBraket == closeBraket) return true;
	else return false;
}

bool isMatching(char b, char e) {
	if ((b == '(' && e == ')') || (b == '{' && e == '}') || (b == '[' && e == ']'))  {
		printf("짝이 맞습니다\n\n");
		return true;
	} else {
		printf("짝이 맞지 않습니다\n\n");
		return false;
	}
}

char begin = NULL;
char end = NULL;
int endPos = 0;

int checkMatching(char* str, int len, int pos) {
	begin = *(str + pos);

	printf("%c %c\n", begin, end);
	while (len != 0) {
		end = *(str + getClosePos(str, len, pos));
		printf("%c %c\n", begin, end);
		if (isMatching(begin, end)) {
			len--;
			continue;
		} else break;
	}

	if (len == 0) return 1;
	else return 0;
}

int getClosePos(char* str, int len, int pos) {
	if (begin == ')') return pos;
	if (begin == '(') {
		endPos = getClosePos(str, len - 1, pos + 1);
		begin = begin + pos;
		end = begin + endPos;

		printf("len: %d\n", len);
		printf("pos: %d, endPos: %d\n", pos, endPos);
		printf("begin: %c, end: %c\n\n", begin, end);

		if (isMatching(begin, end)) {
			printf("괄호 검사\n\n");
			return ++endPos;
		} else return 0;
	}
}
/*
int getClosePos(char* str, int pos) {
	begin = *(str + pos);

	if (begin == ')') return pos;
	if (begin == '(') {
		printf("재귀함수 호출\n");
		endPos = getClosePos(str, pos + 1);
		begin = *(str + pos);
		end = *(str + endPos);

		printf("pos: %d, endPos: %d\n", pos, endPos);
		printf("begin: %c, end: %c\n\n", begin, end);

		if (isMatching(begin, end)) {
			printf("괄호 검사\n\n");
			return ++endPos;
		}
	}

	printf("아직 안끝남\n");
	if (end != NULL) {
		pos = endPos;
		return getClosePos(str, pos);
	}
}
*/