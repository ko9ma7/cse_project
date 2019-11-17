#include <iostream>
using namespace std;

int main(void) {
	int A, B, C;
	cin >> A >> B >> C;

	printf("%d\n", (A + B) % C);
	printf("%d\n", (A % C + B % C) % C);
	printf("%d\n", (A * B) % C);
	printf("%d\n", (A % C * B % C) % C);

	return 0;
}