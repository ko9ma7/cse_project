
public class WeatherApp {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		weatherData.registerObserver(new CurrentConditionDisplay()); // 등록
		weatherData.registerObserver(new StatisticDisplay()); // 등록
		
		weatherData.setMeasurement(30, 65, 30.4f); // 새 데이터 등록
		weatherData.measurementChanged(); // 변화가 있을 때마다 통보되는 시점이나 횟수를 조절할 수 있음
		weatherData.setMeasurement(28, 55, 29.2f); // 새 데이터 등록
		weatherData.measurementChanged();
		weatherData.setMeasurement(29, 50, 30.8f); // 새 데이터 등록
		weatherData.measurementChanged();
		weatherData.setMeasurement(28, 55, 30.2f); // 새 데이터 등록
		weatherData.measurementChanged();
		weatherData.setMeasurement(31, 55, 29.2f); // 새 데이터 등록
		weatherData.measurementChanged();
	}

}
