
public enum AccountNumberGenerator {
	UNIQUE;
	public int count = 0; // 마찬가지로 static이어도되고 아니어도 된다
	public int getNext() {
		return ++count;
	}
}
