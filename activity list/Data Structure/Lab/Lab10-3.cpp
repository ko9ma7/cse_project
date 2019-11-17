#include "Lab10-3-EntertainerDatabaseSystem.h"
#include <conio.h>

void help() {
	printf("[사용법] i - 추가 d - 삭제 s - 연예인검색 e - 소속사검색 p - 출력 q - 종료 => ");
}

void main() {
	char command;
	char name[20];
	char birth[20];
	int age;
	char type[20];
	char entertainment[20];
	EntertainerDatabaseSystem dbms;

	do {
		help();
		command = getche();
		printf("\n");
		switch(command) {
		case 'i':
			printf("   > 삽입 연예인 이름: "); gets(name);
			printf("   > 삽입 연예인 생일: "); gets(birth);
			printf("   > 삽입 연예인 나이: "); scanf("%d", &age); fflush(stdin);
			printf("   > 삽입 연예인 직종: "); gets(type);
			printf("   > 삽입 연예인 소속사: "); gets(entertainment);
			dbms.insert(new BinaryNode(name, birth, age, type, entertainment));
			break;
		case 'd':
			printf("   > 삭제 연예인 이름: "); gets(name);
			dbms.remove(name);
			break;
		case 's':
			printf("   > 검색 연예인 이름: "); gets(name);
			dbms.searchEntertainer(name);
			break;
		case 'e':
			printf("   > 검색 소속사: "); gets(entertainment);
			dbms.searchEntertainment(entertainment);
			break;
		case 'p':
			dbms.printAllInformation();
			printf("\n");
			break;
		}
	} while (command != 'q');
}