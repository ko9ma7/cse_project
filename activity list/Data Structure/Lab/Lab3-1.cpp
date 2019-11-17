#include "Lab3-1.h"

bool check(char *filename) {
	FILE *fp = fopen(filename, "r");
	if (fp == NULL)
		error("파일이 존재하지 않습니다.\n");

	ArrayStack stack;

	int nLine = 1;
	int nChar = 0;

	char ch;
	char ch2;
	bool flag1 = false; // 작은 따옴표 조건 변수
	bool flag2 = false; // 큰 따옴표 조건 변수
	bool flag3 = false; // 주석 처리 '//' 조건 변수
	bool flag4 = false; // 주석 처리 '/*' 조건 변수


	while((ch = getc(fp)) != EOF) { // 파일의 끝이 보일 때까지
		ch2 = getc(fp);  // 그 다음 문자 입력
		ungetc(ch2, fp); // ungetc(되돌릴 문자, 파일이름) -> ch2를 버퍼에 넣는다
		if (ch == '\n') nLine++;
		nChar++;

		if (ch2 == 39) flag1 = !flag1; // 작은 따옴표 검사
		if (ch2 == 34) flag2 = !flag2; // 큰 따옴표 검사
		if (flag3 == false && ch == '/' && ch2 == '/') flag3 = false; // 만약 주석 처리가 나오면 무시
		if (flag3 == true && ch == '\n') flag3 = true; // 만약 '\n'이 나오면 주석 처리 무시 해제
		if (flag4 == false && ch == '/' && ch2 == '*') flag4 = false; // 만약 주석 처리가 나오면 무시
		if (flag4 == true && ch == '*' && ch2 == '/') flag4 = true; // 만약 '*/'가 나오면 주석 처리 무시 해제

		// 모든 flag 중 하나라도 false이면 !을 붙이기에 true가 되어 continue가 동작한다
		if (!flag1 || !flag2 || !flag3 || !flag4)
			continue;

		// 모든 flag 중 하나라도 true인 경우에만 스택 정상 실행
		if (flag1 || flag2 || flag3 || flag4) {
			if (ch == '[' || ch == '(' || ch == '{') { // 괄호 열기를 만나면
				stack.push(ch); // 스택에 입력
			} else if (ch == ']' || ch == ')' || ch == '}') { // 만약 괄호 닫기를 만나면
				char prev_ch = stack.pop(); // 스택에 제일 맨 위에 요소 반환

				if ((ch == ']' && prev_ch != '[')
				|| (ch == ')' && prev_ch != '(')
				|| (ch == '}' && prev_ch != '{')) break; // 서로 요소가 안맞으면 반복문 탈출
			}
		}
	}

	fclose(fp);
	printf("[%s] 파일 검사 결과:\n", filename);
	if (!stack.isEmpty())
		printf("문제 발견!(라인수 = %d, 문자수 = %d)\n\n", nLine, nChar);
	else
		printf("괄호닫기정상(라인수 = %d, 문자수 = %d)\n\n", nLine, nChar);

	return stack.isEmpty();
}

void main() {
	check("실습과제03_01_Check_JMH.cpp");
	check("실습과제03_ArrayStack_JMH.h");
}