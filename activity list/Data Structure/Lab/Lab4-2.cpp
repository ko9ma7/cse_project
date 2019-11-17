/*
   문제 2번. 중위 표기 수식을 후위 표기 수식으로 바꾼 것을 파일에 저장하고
   저장된 파일에서 후위 표기 수식을 읽어서 계산 결과를 출력하기
*/
#include <cstdio>
#include <stack>
#define MAX_STACK_SIZE 20
using namespace std;

// 중위 표기 수식 => 후위 표기 수식 => 후위 표기 수식 계산

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
	stack<char> s;

	FILE* ofp = fopen("JMH.txt", "w"); // 저장할 파일을 열어준다

	if (ofp == NULL) {
		printf("File Open Fail!");
		return;
	}

	while ((c = getc(fp)) != '\n') {
		// 피연산자이면 그대로 출력
		if ((c >= '0' && c <= '9')) {
			ungetc(c, fp);
			fscanf(fp, "%lf", &val);     // fscanf(~~~)는 파일에서 읽어오는 것
			printf("%4.1f ", val);

			fprintf(ofp, "%4.lf ", val); // f를 붙이면 문자열 자체를 파일에 저장

		}
		// '('이면 --> 스택에 삽입
		else if (c == '(') s.push(c);
		// ')'이면 --> '('가 나올때까지 연산자 출력
		else if (c == ')') {
			while(!s.empty()) {
				op = s.top();
				s.pop();

				if (op == '(') break;
				else {
					printf("%c ", op);

					fprintf(ofp, "%c ", op); // 파일에 저장
				}
			}
		}
		// 연산자('+', '-', '*', '/')이면 --> 우선순위 비교처리
		else if (c == '+' || c == '-' || c == '*' || c == '/') {
			while (!s.empty()) {
				op = s.top();
				// 만약 입력된 문자가 스택에 제일 위에 있는 문자보다 우선순위가 작을 경우
				if (precedence(c) <= precedence(op)) {
					printf("%c ", op); // 스택에 있는 문자를 출력

					fprintf(ofp, "%c ", op); // 파일에 저장

					s.pop();
				} else break;          // 반대의 경우는 그냥 스택에 넣는다
			}
			s.push(c); // 우선순위가 낮은 문자를 스택에 넣어준다
		}
	}
	// 스택 안에 문자가 남아 있다면(우선순위가 가장 낮은 문자)
	while (!s.empty()) {
		printf("%c", s.top());

		fprintf(ofp, "%c", s.top()); // 파일에 저장

		s.pop();
	}
	printf("\n");
	fclose(ofp); // 파일을 닫아준다
}
// 후위 표기 수식 => 계산(연산항 -> 실수)
double calcPostfixExpr() {
	char c;
	double val;
	stack<int> s;

	FILE *ifp = fopen("JMH.txt", "r");

	if (ifp == NULL) {
		printf("File Open Fail!\n");
		return -1;
	}

	while ((c = getc(ifp)) != EOF) {
		// 문자가 연산자이면 스택안에 있는 값들을 꺼내서 계산 후 다시 스택에 넣는다
		if (c == '+' || c == '-' || c == '*' || c == '/') {
			double val2 = s.top(); s.pop();
			double val1 = s.top(); s.pop();

			switch (c) {
			case '+': s.push(val1 + val2); break;
			case '-': s.push(val1 - val2); break;
			case '*': s.push(val1 * val2); break;
			case '/': s.push(val1 / val2); break;
			}
		}
		// 문자가 숫자이면 실수의 값으로 읽어들인다
		else if (c >= '0' && c <= '9') {
			ungetc(c, ifp);
			fscanf(ifp, "%lf ", &val);
			s.push(val);
		}
	}

	fclose(ifp);

	double result = s.top(); // 모든 계산 후 남은 계산 결과값
	s.pop();
	return result;
}

void main() {
	printf("수식 입력 = ");
	infix2Postfix();
	printf("계산 결과 = %4.1f", calcPostfixExpr());
}