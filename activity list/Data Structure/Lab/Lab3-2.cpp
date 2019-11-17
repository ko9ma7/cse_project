#include <cstdio>
#include <cstdlib>
#include <stack>
using namespace std;

inline void error(char *message) {
	printf("%s\n", message);
	exit(1);
}

bool check(char *filename) {
	FILE *fp = fopen(filename, "r");
	if (fp == NULL)
		error("파일이 존재하지 않습니다.\n");

	stack<int> s;

	int nLine = 1;
	int nChar = 0;

	char ch;
	char ch2;
	bool flag1 = false; // 작은 따옴표 조건 변수
	bool flag2 = false; // 큰 따옴표 조건 변수
	bool flag3 = false; // 주석 처리 '//' 조건 변수
	bool flag4 = false; // 주석 처리 '/*' 조건 변수


	while((ch = getc(fp)) != EOF) {
		ch2 = getc(fp);
		ungetc(ch2, fp);
		if (ch == '\n') nLine++;
		nChar++;

		if (ch2 == 39) flag1 = !flag1;
		if (ch2 == 34) flag2 = !flag2;
		if (flag3 == false && ch == '/' && ch2 == '/') flag3 = false;
		if (flag3 == true && ch == '\n') flag3 = true;
		if (flag4 == false && ch == '/' && ch2 == '*') flag4 = false;
		if (flag4 == true && ch == '*' && ch2 == '/') flag4 = true;

		if (!flag1 || !flag2 || !flag3 || !flag4)
			continue;

		if (flag1 || flag2 || flag3 || flag4) {
			if (ch == '[' || ch == '(' || ch == '{') {
				s.push(ch);
			} else if (ch == ']' || ch == ')' || ch == '}') {
				char prev_ch = s.top();
					s.pop();
				if ((ch == ']' && prev_ch != '[')
				|| (ch == ')' && prev_ch != '(')
				|| (ch == '}' && prev_ch != '{')) break;
			}
		}
	}

	fclose(fp);
	printf("[%s] 파일 검사 결과:\n", filename);
	if (!s.empty())
		printf("문제 발견!(라인수 = %d, 문자수 = %d)\n\n", nLine, nChar);
	else
		printf("괄호닫기정상(라인수 = %d, 문자수 = %d)\n\n", nLine, nChar);

	return s.empty();
}

void main() {
	check("실습과제03_02_Check_JMH.cpp");
}