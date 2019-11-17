
public enum PriceCode {
	CHILDRENS{
		@Override
		public int getCharge(int daysRented) {
			int price = 1500;
			if(daysRented>3)
				price += (daysRented-3)*1500;
			return price;
		}		
	}, 
	REGULAR{
		@Override
		public int getCharge(int daysRented) {
			int price = 2000;
			if(daysRented>2)
				price += (daysRented-2)*1500;
			return price;
		}		
	}, 
	NEW_RELEASE{
		@Override
		public int getCharge(int daysRented) {
			return daysRented*2000;
		}
		@Override
		public int getFrequentRentalPoints(int daysRented){
			return daysRented>1 ? 200: super.getFrequentRentalPoints(daysRented);
		}		
	};
	public abstract int getCharge(int daysRented);
	public int getFrequentRentalPoints(int daysRented){
		return 100;
	}
}
