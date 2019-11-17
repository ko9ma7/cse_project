// 자바에서 제공하는 Observer 패턴
/*
 *  문제점 : 자바는 다중상속을 제공하지 않으므로 다른 클래스를 상속받아야 하는 클래스는 subject가 될 수 없음 
 *        -> 만약, 구독자들이 WeatherData가 아닌 다른 정보를 원할 경우, 상속을 해주어야 하는데 다중상속 불가
 */
public interface Observer {
	void update(float temperature, float humidity, float pressure); // 세 개의 정보를 주고 있는 update -> 문제점이 하나 있다면 모든 Observer들이 세 개의 정보가 필요하지 않을 수 있다
}
