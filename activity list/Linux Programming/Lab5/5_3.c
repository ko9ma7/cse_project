#include "6_3.h"

int main(int argc, char** argv) {

	myTimer_init(2);

	myTimer_on(0);
	for (int i = 0; i < 9999; i++) for (int j = 0; j < 9999; j++);
	myTimer_off(0);

	myTimer_on(1);
        for (int i = 0; i < 999; i++) for (int j = 0; j < 999; j++) for (int k = 0; k < 999; k++);
	myTimer_off(1);

	myTimer_print();
	myTimer_finalize();

	return 0;

}
	
