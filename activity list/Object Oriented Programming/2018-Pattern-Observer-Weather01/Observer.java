
public interface Observer {
	void update(float temperature, float humidity, float pressure); // 세 개의 정보를 주고 있는 update -> 문제점이 하나 있다면 모든 Observer들이 세 개의 정보가 필요하지 않을 수 있다
	/* pull 방식 - public void update(Subject s); */
}
