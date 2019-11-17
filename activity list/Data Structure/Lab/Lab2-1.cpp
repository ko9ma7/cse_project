#include "Lab2-1.h"

int main() {
	while(1) {
		showMenu();
		int choose;
		scanf("%d", &choose);

		Polynomial a, b, c; // 객체 생성

		switch(choose) {
		case 1:
			printf("덧셈 연산을 선택하셨습니다\n");

			a.read();
		    a.display("A = ");
		    b.read();
		    b.display("B = ");
		    c.add(a, b);
	     	c.trim();
	     	c.display("A + B = ");
			break;
		case 2:
			printf("뺄셈 연산을 선택하셨습니다\n");

			a.read();
		    a.display("A = ");
		    b.read();
		    b.display("B = ");
		    c.sub(a, b);
	     	c.trim();
	     	c.display("A - B = ");
			break;
		case 3:
			printf("곱셈 연산을 선택하셨습니다\n");

			a.read();
		    a.display("A = ");
		    b.read();
		    b.display("B = ");
		    c.mult(a, b);
	     	c.trim();
	     	c.display("A * B = ");
			break;
		case 4:
			printf("종료합니다\n");
			return 0;
		}
	}
}