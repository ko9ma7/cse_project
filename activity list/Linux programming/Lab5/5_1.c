#include <sys/time.h>
#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>

char *output = { // 출력 포맷 설정

	"%Gyear %mmonth %dday %Hhour %Mminute %Ssecond"
};

int main(int argc, char** argv) {

	// 1. 파이 값 근사 시작

	struct tm *s_tm, *e_tm;
	time_t start_t;
	char buf[BUFSIZ];
	int n = atoi(argv[1]);

	time(&start_t);
	s_tm = localtime(&start_t);
	strftime(buf, sizeof(buf), output, s_tm);
	printf("start time: %s\n", buf);

	printf("Number of polynomials = %d\n", n);
	
	// 2. 라이프니츠의 방식

	struct timeval startT, endT, resultT;
	long double approximate_value;
	long double PI;

	gettimeofday(&startT, NULL);
	for (int i = 0; i < n; i++) approximate_value += pow(-1.0, (double)i) / ((2 * i) + 1);
	PI = approximate_value * 4;
	gettimeofday(&endT, NULL);	
	resultT.tv_sec = endT.tv_sec - startT.tv_sec;
	
	printf("Leibniz formula for PI(to 20 decimal place): %.20Lf\n", PI);
	printf("processing time: %.2f\n", (double)resultT.tv_sec);

	// 3. 오일러의 방식
	
	startT.tv_sec = 0, endT.tv_sec = 0, resultT.tv_sec = 0, approximate_value = 0.0, PI = 0.0;
			
	gettimeofday(&startT, NULL);
	for (int i = 1; i < n; i++) approximate_value += 1.0 / pow((double)i, 2.0);
	PI = sqrt(6 * approximate_value);
	gettimeofday(&endT, NULL);
	resultT.tv_sec = endT.tv_sec - startT.tv_sec;

	printf("Euiler formula for PI(to 20 decimal place): %.20Lf\n", PI);
	printf("processing time: %.2f\n", (double)resultT.tv_sec);

	// 4. 파이 값 근사 끝

	time_t end_t;
	time(&end_t);
	e_tm = localtime(&end_t);
	strftime(buf, sizeof(buf), output, e_tm);
	printf("end time: %s\n", buf);

	return 0;

}
