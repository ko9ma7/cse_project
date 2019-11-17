
public class NewReleasePrice implements Price {

	@Override
	public int getCharge(int daysRented) {
		return daysRented*2000;
	}
	@Override
	public int getFrequentRentalPoints(int daysRented){
		return daysRented>1 ? 200: Price.super.getFrequentRentalPoints(daysRented);
	}
}
