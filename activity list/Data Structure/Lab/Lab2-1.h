// 방법 2를 이용해서 문제 1번부터 문제 4번까지 풀었습니다.
// vector를 사용하지 않은 버전
#include <cstdio>

#define MAX_DEGREE 80
class Polynomial {
private:
	int degree; // 차수
	float coef[MAX_DEGREE]; // 각 항에 대한 계수
public:
	Polynomial() { degree = 0; } // 생성자

	void read() {
		printf("다항식의 최고 차수를 입력하시오: ");
		scanf("%d", &degree);
		printf("각 항의 계수를 입력하시오(총 %d개): ", degree + 1);
		for(int i = 0; i <= degree; i++)
			//			scanf("%f", coef + i);
			scanf("%f", &coef[i]);
		printf("\n");
	}

	void add(Polynomial a, Polynomial b) {
		if(a.degree >= b.degree) {
			*this = a;
			for(int i = 0; i <= b.degree; i++)
				coef[i] += b.coef[i];
		} else {
			*this = b;
			for(int i = 0; i <= a.degree; i++)
				coef[i] += a.coef[i];
		}
	}
	// 모든 계수의 부호를 바꾸는 함수
	void negate() {
		for(int i = 0; i <= degree; i++)
			coef[i] = -coef[i];
	}
	// 문제 1번
	/*
	두 다항식의 뺄셈을 구하는 함수. negate() 함수를 이용해서
	구했습니다.
	*/
	void sub(Polynomial a, Polynomial b) {
		if(a.degree >= b.degree) {
			*this = a;

			b.negate(); // b의 계수를 모두 음수로

			for(int i = 0; i <= b.degree; i++)
				coef[i] += b.coef[i];
		} else {
			*this = b;

			a.negate(); // a의 계수를 모두 음수로

			for(int i = 0; i <= a.degree; i++)
				coef[i] -= a.coef[i];
		}
	}
	// 문제 2번
	/*
	두 다항식의 곱셈을 구하는 함수. 차수가 같을 경우와 다를 경우를 나눠서 생각했습니다.
	*/
	void mult(Polynomial a, Polynomial b) {
		/*
		mult() 멤버 함수를 호출할 경우 따로 차수 변수와 계수 변수를 만들어서
		클래스의 멤버 변수에 넣는 것으로 코드를 짰습니다.
		*/
		int max_degree = a.degree + b.degree;          // 곱했을 때 최고 차수
		float *mult_coef = new float[max_degree + 1];  // 계수의 가변 배열 생성
		int i = 0, idx = 0; // 인덱스 변수
		int p = 1, q = 1;

		// 가변 배열 초기화
		for(int k = 0; k <= max_degree; k++) {
			mult_coef[k] = 0;
		}

		if(a.degree == b.degree) {
			for(int k = 0; k <= max_degree; k++) { // k는 곱셈 다항식의 차수 인덱스
				if(k > a.degree || k > b.degree) { // 곱셈의 차수가 a의 차수 또는 b의 차수보다 커질 경우
					for(i = p; i <= idx; i++)
						mult_coef[k] += a.coef[i] * b.coef[idx - i + q];
					p++;
					q++;
				} else {
					for(i = 0; i <= idx; i++)
						mult_coef[k] += a.coef[i] * b.coef[idx - i];
					idx++;
				}

				if(idx > a.degree || idx > b.degree) // 마지막에 나오는 idx의 값이 a의 차수나 b의 차수보다 클 경우
					idx--;
			}
		} else if(a.degree > b.degree) { // a의 최고차수가 b의 최고차수보다 큰 경우
			q = 0;

			for(int k = 0; k <= max_degree; k++) {
				if(k > b.degree) { // 곱셈의 차수가 b의 차수보다 커지는 경우(b를 기준으로 해야함)
					for(i = p; i <= idx; i++)
						mult_coef[k] += a.coef[i] * b.coef[idx - i + q];
					p++;
					q++;
				} else { // a의 차수와 b의 차수가 같을 경우와 동일
					for(i = 0; i <= idx; i++)
						mult_coef[k] += a.coef[i] * b.coef[idx - i];
					idx++;
				}
			}
		} else { // b의 최고차수가 a의 최고차수보다 큰 경우(조건문 2번과 반대로 생각합니다)
			q = 0;

			for(int k = 0; k <= max_degree; k++) {
				if(k > a.degree) { // 곱셈의 차수가 a의 차수보다 커지는 경우(a를 기준으로 해야함)
					for(i = p; i <= idx; i++)
						mult_coef[k] += b.coef[i] * a.coef[idx - i + q];
					p++;
					q++;
				} else { // a의 차수와 b의 차수가 같을 경우와 동일
					for(i = 0; i <= idx; i++)
						mult_coef[k] += b.coef[i] * a.coef[idx - i];
					idx++;
				}
			}
		}

		// 차수와 계수의 변수를 클래스의 멤버 변수에 복사
		degree = max_degree;

		for(int i = 0; i < max_degree + 1; i++) {
			coef[i] = mult_coef[i]; // 배열이므로 각각 복사 붙이기
		}

		delete []mult_coef; // 동적 메모리 반환
	}
	// 문제 3번
	// 최고차항의 계수가 0이 아닌 값이 나오도록 변경하는 함수
	/*
	trim() 함수는 최고차항의 계수가 0이 아닌 나머지는 모두
	출력하도록 하므로 계수가 0인 최고차항부터 구해야 합니다.
	따라서 낮은 차수부터 높은 차수 순서로 비교해보면서
	만약 0이 아닌 계수가 나오면 저장하고, 0이면 넘어가는 식으로
	알고리즘을 짰습니다.
	*/
	void trim() {
		int zero_Index_trim; // 계수가 0인 인덱스
		for(int i = 0; i <= degree; i++) { // 낮은 차수부터 시작해서
			if(coef[i] != 0.0)             // 계수가 0이 아닌 인덱스를 찾으면
				zero_Index_trim = i;       // zeor_Index에 저장(계수가 0이 아닌 최고차수)
		}

		printf("\tTrim  = ");

		// 최고차항 출력
		printf("(%4.1f )x^%d", coef[zero_Index_trim], zero_Index_trim);

		// 그 다음 차항부터 상수항 전까지 출력
		for(int i = zero_Index_trim - 1; i > 0; i--) {
			printf(" + (%4.1f )x^%d", coef[i], i);
		}

		// 상수항 출력(인덱스가 0)
		printf(" + (%4.1f )\n\n", coef[0]);
	}
	// 문제 4번
	/*
	코드를 짜다보니 문제 4번은 문제 3번을 이용해서 풀면 간단해집니다. 단순히
	계수가 1인 경우의 조건만 추가해주면 됩니다.
	*/
	void display(char *str =" Poly = ") {
		printf("\t%s", str);

		/*
		계수가 0인 항을 출력하지 않기 위해서 낮은 차수의 계수부터 시작해서
		높은 차수의 계수를 비교합니다. trim() 함수와 같은 방법으로 높은
		차수를 구해야 합니다.
		*/

		int i; // 인덱스 변수
		int zero_Index; // 계수가 0이 아닌 최고 차수

		for(i = 0; i <= degree; i++) {
			if(coef[i] != 0.0)   // 만약 계수가 0이 아닌 차수가 나오면
				zero_Index = i;  // 인덱스 저장
		}

		// 최고차항 출력
		// 위에서 구한 zero_Index가 저장이 되었다면 계수가 0임을 고려해줄 필요가 없다.
		if(coef[zero_Index] == 1.0)
			printf("x^%d", zero_Index);
		else
			printf("(%4.1f )x^%d", coef[zero_Index], zero_Index);

		// 그 다음 차항부터 상수항 전까지 출력
		for(int i = zero_Index - 1; i > 0; i--) {
			if(coef[i] == 0.0)
				continue;
			else if(coef[i] == 1.0)
				printf(" + x^%d", i);
			else
				printf(" + (%4.1f )x^%d", coef[i], i);
		}

		// 상수항 출력(인덱스가 0)
		if(coef[0] != 0.0)
			printf(" + (%4.1f )\n\n", coef[0]);
		else if(coef[0] == 1.0)
			printf(" + 1.0\n\n");
		else {
			printf("\n\n");
			return;
		}
	}
};

void showMenu() {
	printf("어떤 연산을 하시겠습니까?\n");
	printf("1. 덧셈\n");
	printf("2. 뺄셈\n");
	printf("3. 곱셈\n");
	printf("4. 종료\n");
}