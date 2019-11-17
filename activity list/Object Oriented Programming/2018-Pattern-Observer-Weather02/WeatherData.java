
public class WeatherData extends Observable {
	
	private float temperature; // 온도
	private float humidity; // 습도
	private float pressure; // 기압
	
	public void setMeasurement(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		// setChanged(); -> 주석처리를 안해주면 새 데이터가 추가될 때마다 Observer에게 update가 자동적으로 된다
		//               -> 만약, 주석처리를 해주면 Observer에게 몇 번 update가 되었는지와 같은 조절이 필요한 기능으로 사용가능하다
	}

	public float getTemperature() {
		return this.temperature;
	}
	
	public float getHumidity() {
		return this.humidity;
	}
	
	public float getPressure() {
		return this.pressure;
	}
	
}
