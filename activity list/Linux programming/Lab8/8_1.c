#include "myTimer.h"
#include <sys/mman.h>
#include <string.h>

#define PRINT_ERR_EXIT(_msg) { perror(_msg); exit(1); }
#define CHECK_MMAP_SUCCESS(_addr) { perror("mmap"); exit(1); }

int main(int argc, char **argv) {

	// 1. 파일 입출력으로 복사하기 및 시간 측정
	FILE *rfp, *wfp;
	int pageSize = getpagesize();
	char buf1[pageSize];
	int n;

	myTimer_init(2); // 타이머 2개로 설정(파일 입출력, 메모리 매핑)
	
	if ((rfp = fopen(argv[1], "r")) == NULL) PRINT_ERR_EXIT("fopen: rfp");
	if ((wfp = fopen(argv[2], "w")) == NULL) PRINT_ERR_EXIT("fopen: wfp");

	// 2. 파일 입출력으로 파일 읽기
	fread(buf1, sizeof(char), pageSize, rfp);

	// 3. 파일 입출력으로 파일 쓰기(복사) 및 시간 측정
	myTimer_on(0);
	fwrite(buf1, sizeof(char), pageSize, wfp);
	myTimer_off(0);

	fclose(rfp);
	fclose(wfp);

	/////////////////////////////////////////////////////////////////////////////////////////////////

	// 4. 메모리 매핑으로 복사하기 및 시간 측정
	int rfd, wfd;
	caddr_t addr;

	if ((rfd = open(argv[1], O_RDONLY)) == -1) PRINT_ERR_EXIT("open: rfd");
	if ((wfd = open(argv[3], O_CREAT | O_WRONLY | O_TRUNC, 0644)) == -1) PRINT_ERR_EXIT("open: wfd");

	// 독립적인 파일 복사이므로 MAP_PRIVATE을 사용해야 한다
	addr = mmap(NULL, pageSize, PROT_READ | PROT_WRITE, MAP_PRIVATE, rfd, (off_t)0);

	// 5. 메모리 매핑으로 파일 쓰기(복사) 및 시간 측정
	myTimer_on(1);
	write(wfd, addr, pageSize);
	myTimer_off(1);

	close(rfd);
	close(wfd);

	// 6. 시간 측정 결과 출력
	myTimer_print();

	return 0;
	
}
