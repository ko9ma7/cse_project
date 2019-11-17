#include <ctime>
#include "Lab5-1-BankSimulator.h"

void main() {
	srand((unsigned int)time(NULL));
	BankSimulator sim;
	sim.readSimulationParameters();
	sim.run();
	sim.printStat();
}