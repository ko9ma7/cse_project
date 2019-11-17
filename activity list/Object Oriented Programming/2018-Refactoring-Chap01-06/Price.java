
public interface Price {
	int getCharge(int daysRented);
	default int getFrequentRentalPoints(int daysRented){
		return 100;
	}
}
