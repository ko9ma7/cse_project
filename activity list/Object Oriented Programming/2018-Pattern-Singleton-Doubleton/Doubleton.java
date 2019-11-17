
public enum Doubleton {
	FIRST, SECOND;
	private static int count = 0;
	
	public static Doubleton getInstance() {
		if (count == 0) {
			count++;
			return FIRST;
		} else if (count == 1) {
			count++;
			return SECOND;
		} else {
			count++;
			return null;
		}
	}
}
