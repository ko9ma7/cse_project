import java.util.ArrayList; // 날씨 정보를 ArrayList에 넣어 주고 있다 -> 1.정보의 중복이 필요 없으면 Set을 사용하자  2.Observer에게 순서를 부여하고자 하면 PriorityQueue을 사용하자
                            // 자료구조에 따라 코드의 활용도가 높아진다

public class WeatherData implements Subject {
	private ArrayList<Observer> observers = new ArrayList<>();
	private float temperature; // 온도
	private float humidity; // 습도
	private float pressure; // 기압
	
	// 중복 검사가 필요하면 ArrayList 대신에 Set 사용 가능
	@Override
	public void registerObserver(Observer o) {
		if(o!=null) observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		observers.forEach(o->o.update(temperature,humidity,pressure)); // 람다 표현식(반복문)
	}
	
	/*
	@Override
	public void notifyObserver() {
		for(Observer o : observers)
		{	
			//pull 방식
			o.update(this);
		}
	}
	*/
	public void measurementChanged() { // 새로운 측정 결과가 있을 때마다 자동으로 호출됨. 이 메소드에서는 이 정보를 기다리는 측에게 정보를 전달해야 함
		notifyObservers();
	}
	
	public void setMeasurement(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		// measurementChanged(); -> 주석처리를 안해주면 새 데이터가 추가될 때마다 Observer에게 update가 자동적으로 된다
		//                       -> 만약, 주석처리를 해주면 Observer에게 몇 번 update가 되었는지와 같은 조절이 필요한 기능으로 사용가능하다
	}

}
