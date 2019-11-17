/*
   문제 3번. 중위 표기 수식을 후위 표기 수식으로 바꾼 것을 문자열에 저장하고
   문자열에서 후위 표기 수식을 읽어서 계산 결과를 출력하기
*/
#include <cstdio>
#include <cstdlib>
#include <stack>
#define MAX_STACK_SIZE 20
using namespace std;

static char postStr[1000] = ""; // 빈 문자열 생성

inline void error(char* str) {
	printf("%s\n", str);
	exit(1);
}
// 우선 순위 비교표
int precedence(char op) {
	switch(op) {
	case '(': case ')': return 0;
	case '+': case '-': return 1;
	case '*': case '/': return 2;
	}
	return -1;
}
// 중위 표기 수식 => 후위 표기 수식
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

			sprintf(postStr, "%s%4.1f ", postStr, val); // 문자열에 출력하는 것, postStr = 이전의 postStr + 입력받은 값
			                                            // sscanf(~~~)는 문자열에서 읽어오는 것
		}
		// '('이면 --> 스택에 삽입
		else if (c == '(') st.push(c);
		// ')'이면 --> '('가 나올때까지 연산자 출력
		else if (c == ')') {
			while(!st.empty()) {
				op = st.top();
				st.pop();

				if (op == '(') break;
				else {
					printf("%c ", op);

					sprintf(postStr, "%s%c ", postStr, op);
				}
			}
		}
		// 연산자('+', '-', '*', '/')이면 --> 우선순위 비교처리
		else if (c == '+' || c == '-' || c == '*' || c == '/') {
			while (!st.empty()) {
				op = st.top();
				if (precedence(c) <= precedence(op)) {
					printf("%c ", op);

					sprintf(postStr, "%s%c ", postStr, op);

					st.pop();
				}
				else break;
			}
			st.push(c);
		}
	}

	while (!st.empty()) {
		printf("%c ", st.top());

		sprintf(postStr, "%s%c ", postStr, st.top());

		st.pop();
	}
	printf("\n");
	sprintf(postStr, "%s\n", postStr);
}

// 후위 표기 수식 => 계산(연산항 -> 실수)
double calcPostfixExpr() {
	char *p = postStr; // postStr을 가리키고 있는 char형 포인터 p(p는 주소값을 가지고 있다)
	char ch;
	double val;
	stack<int> s;

	for ( ; *p != '\n'; p++) { // p가 가리키는 값이 '\n'가 될때까지 포인터의 주소값을 1씩 증가시켜준다
		ch = *p;
		// 문자가 연산자이면 스택안에 있는 값들을 꺼내서 계산 후 다시 스택에 넣는다
		if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
			double val2 = s.top(); s.pop();
			double val1 = s.top(); s.pop();

			switch (ch) {
			case '+': s.push(val1 + val2); break;
			case '-': s.push(val1 - val2); break;
			case '*': s.push(val1 * val2); break;
			case '/': s.push(val1 / val2); break;
			}
		}
		// 문자가 숫자이면 실수의 값으로 읽어들인다
		else if (ch >= '0' && ch <= '9') {
			sscanf(p, "%lf", &val); // fscanf나 sscanf는 파일에서 혹은 문자열에서 값을 읽어들이는 차이일 뿐
			                        // 자동적으로 가운데에 있는 형식으로 값을 읽는다
			                        // 포인터 주소를 이용하여 문자열에서 값을 읽는다
			// sscanf() 함수에서 포인터가 공백이나 '\n'가 되기 전까지 값을 읽어야 하므로
			for ( ; *p != ' ' && *p != '\n'; p++); // == for ( ; *p != ' ' && *p != '\n'; p++) { }
			s.push(val);
		}
	}

	double result = s.top(); // 모든 계산 후 남은 계산 결과값
	s.pop();
	return result;
}

void main() {
	printf("수식 입력 = ");
	infix2Postfix();
	printf("계산 결과 = %4.1f", calcPostfixExpr());
}