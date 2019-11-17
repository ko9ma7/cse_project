// Subject 인터페이스 : 특정 Observer에게만 상태를 전달하는 메소드 추가
public interface Subject {

	public void addObserver(Observer observer); // 옵저버 객체 추가
	public void removeObserver(Observer observer); // 옵저버 객체 삭제
	public void notifyObserver(); // 옵저버 객체들에게 상태 푸시
	public void notifyObserver(Observer observer); // 특정객체에게 상태 푸시
	
}
