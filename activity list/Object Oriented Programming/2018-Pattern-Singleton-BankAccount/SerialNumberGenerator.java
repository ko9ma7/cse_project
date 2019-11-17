// 내부 클래스 사용
public class SerialNumberGenerator {
	private static class Holder{
		private static final SerialNumberGenerator unique =
			new SerialNumberGenerator();
	}
	public int count = 0; // static으로 만들어도 되고 안해도 크게 문제가 되지 않음 -> 클래스 내부에서 세어지나 외부로부터 세어지나 같은 의미
	private SerialNumberGenerator(){}
	public static SerialNumberGenerator getInstance(){
		return Holder.unique;
	}
	public int getNext(){
		++count;
		return count;
	}
	
}
