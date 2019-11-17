#include "Lab5-1-CustomerQueue.h"

class BankSimulator {
	int nSimulation;     // 전체 시뮬레이션 횟수
	double probArrival;  // 단위시간에 도착하는 평균 고객 수
	int tMaxService;     // 한 고객에 대한 최대 서비스 시간
	int totalWaitTime;   // 고객들이 기다린 전체시간
	int nCustomers;      // 전체 고객 수
	int nServedCustomers; // 서비스 받은 고객 수
	CustomerQueue que;   // 고객 대기 큐

	// 랜덤 숫자를 생성하여 고객 도착 여부와 서비스 시간 자동 생성 보드
	double Random() { return rand() / (double)RAND_MAX; }   // 0 ~ 1 사이의 실수 난수
	bool isNewCustomer() { return Random() < probArrival; } // 랜덤 고객 발생
	int RandServiceTime() { return (int)(tMaxService * Random()) + 1; }

	// 새로 도착한 고객을 큐에 삽입
	void InsertCustomer (int arrivalTime) {
		Customer a(++nCustomers, arrivalTime, RandServiceTime());
		printf("  고객 %d 방문 (서비스 시간:%d분\n", a.id, a.tService);
		que.enqueue(a);
	}
public:
	// 생성자
	BankSimulator() : nCustomers(0), totalWaitTime(0), nServedCustomers(0) { }

	// 시뮬레이션 파라미터 입력
	void readSimulationParameters() {
		printf("시뮬레이션 할 최대 시간 (예:10) = "); scanf("%d", &nSimulation);
		printf("단위시간에 도착하는 고객 수 (예:0.7) = "); scanf("%lf", &probArrival);
		printf("한 고객에 대한 최대 서비스 시간 (예: 0.5) = "); scanf("%d", &tMaxService);
		printf("=============================================================\n");
	}

	// 시뮬레이션 실행
	void run() {
		int clock = 0;         // 현재 시각
		int serviceTime = -1;  // 처리에 걸리는 잔여 시간

		while (clock < nSimulation) {
			clock++;
			printf("현재시각=%d\n", clock);

			if (isNewCustomer()) // 새로운 고객이 도착했으면 큐에 삽입
				InsertCustomer(clock);
			if (serviceTime > 0) serviceTime--;      // 현재 고객 서비스 중
			else {
				if (que.isEmpty()) continue;         // 기다리는 고객 없음
				Customer a = que.dequeue();          // 새로 서비스 할 고객
				nServedCustomers++;                  // 서비스한 고객 수
				totalWaitTime += clock - a.tArrival; // 총 대기시간
				printf("  고객 %d 서비스 시작 (대기시간:%d분)\n", a.id, clock - a.tArrival);
				serviceTime = a.tService - 1;
			}
		}
	}

	// 시뮬레이션 출력
	void printStat() {
		printf("=============================================================\n");
		printf("  서비스 받은 고객 수  = %d\n", nServedCustomers);
		printf("  전체 대기 시간       = %d분\n", totalWaitTime);
		printf("  서비스고객 평균대기시간 = %-5.2f분\n", (double)totalWaitTime / nServedCustomers);
		printf("  현재 대기 고객 수    = %d\n", nCustomers - nServedCustomers);
	}
};