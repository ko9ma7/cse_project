import java.util.ArrayDeque; 
import java.util.Comparator;
import java.util.Queue;

public class StatisticDisplay implements Observer {
	private Queue<Float> temperatureLog = new ArrayDeque<>(); // 데이터를 삭제하고 추가하는 것을 자유롭게 하기위해 Queue을 사용
	private Queue<Float> humidityLog = new ArrayDeque<>();
	private Queue<Float> pressureLog = new ArrayDeque<>();
	
	public void display() {
		System.out.printf("최근 최대 온도: %.2f%n", 
				temperatureLog.stream().max(Comparator.comparing(Float::valueOf)).get());
		System.out.printf("최근 최대 습도: %.2f%n", 
				humidityLog.stream().max(Comparator.comparing(Float::valueOf)).get());
		System.out.printf("최근 최대 기압: %.2f%n", 
				pressureLog.stream().max(Comparator.comparing(Float::valueOf)).get());
	}

	@Override
	public void update(float temperature, float humidity, float pressure) { // 최신 5개의 데이터의 평균값을 내주는 메소드
		if(temperatureLog.size()==5) temperatureLog.poll(); // 사이즈가 5개라면 이미 꽉 차있으므로 맨 앞의 데이터를 밖으로 내보낸다
		if(humidityLog.size()==5) humidityLog.poll();
		if(pressureLog.size()==5) pressureLog.poll();
		temperatureLog.add(temperature);
		humidityLog.add(humidity);
		pressureLog.add(pressure);
		if(temperatureLog.size()==5) display();
	}
}
