
public interface Subject {
	void registerObserver(Observer o); // 구독자 등록
	void removeObserver(Observer o); // 구독자 취소
	void notifyObservers(); // 구독자 알림
}
