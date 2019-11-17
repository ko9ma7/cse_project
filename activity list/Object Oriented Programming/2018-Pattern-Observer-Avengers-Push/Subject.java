// Subject 인터페이스 : Observer의 추가, 삭제, 공지 기능을 가지는 메소드를 선언
public interface Subject {

	public void addObserver(Observer observer); // 옵저버 객체 추가
	public void removeObserver(Observer observer); // 옵저버 객체 삭제
	public void notifyObserver(); // 옵저버 객체들에게 상태 표시
	
}
