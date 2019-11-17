/*
   문제 1번. 중위 표기 수식을 후위 표기 수식으로 바꾸는 것을 STL stack 이용하기
*/
#include <cstdio>
#include <stack>
using namespace std;

// 객체의 우선순위 계산
inline int precedence(char op) {
	switch (op) {
	case '(': case ')': return 0;
	case '+': case '-': return 1;
	case '*': case '/': return 2;
	}
	return -1;
}
// 중위 표기 수식을 후위 표기 수식으로 변환하는 함수
void infix2Postfix(FILE *fp = stdin) {
	char c, op;
	double val;
	stack<int> st;

	while ((c = getc(fp)) != '\n') {
		// 피연산자이면 그대로 출력
		if ((c >= '0' && c <= '9')) {
			ungetc(c, fp);
			fscanf(fp, "%lf", &val);
			printf("%4.1f ", val);
		}
		// '('이면 --> 스택에 삽입
		else if (c == '(') st.push(c);
		// ')'이면 --> '('가 나올때까지 연산자 출력
		else if (c == ')') {
			while(!st.empty()) {
				op = st.top();
				st.pop();
				if (op == '(') break;
				else printf("%c ", op);
			}
		}
		// 연산자('+', '-', '*', '/')이면 --> 우선순위 비교처리
		else if (c == '+' || c == '-' || c == '*' || c == '/') {
			while (!st.empty()) {
				op = st.top();
				if (precedence(c) <= precedence(op)) {
					printf("%c ", op);
					st.pop();
				}
				else break;
			}
			st.push(c);
		}
	}

	while (!st.empty()) {
		printf("%c ", st.top());
		st.pop();
	}
	printf("\n");
}

void main() {
	printf("수식 입력(Infix) = ");
	infix2Postfix();
}