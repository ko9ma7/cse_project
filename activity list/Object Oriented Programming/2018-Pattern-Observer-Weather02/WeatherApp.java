
public class WeatherApp {

	public static void main(String[] args) {
		Observable o = new Observable();
		o.addObserver(new CurrentConditionDisplay()); // 등록
		o.addObserver(new StatisticDisplay()); // 등록
		
		WeatherData w = new WeatherData();
		w.setMeasurement(30, 65, 30.4f); // 새 데이터 등록
		o.update(w.getTemperature(), w.getHumidity(), w.getPressure());
		o.setChanged(); o.notifyObserver(w);
		w.setMeasurement(28, 55, 29.2f); // 새 데이터 등록
		o.update(w.getTemperature(), w.getHumidity(), w.getPressure());
		o.setChanged(); o.notifyObserver(w);
		w.setMeasurement(29, 50, 30.8f); // 새 데이터 등록
		o.update(w.getTemperature(), w.getHumidity(), w.getPressure());
		o.setChanged(); o.notifyObserver(w);
		w.setMeasurement(28, 55, 30.2f); // 새 데이터 등록
		o.update(w.getTemperature(), w.getHumidity(), w.getPressure());
		o.setChanged(); o.notifyObserver(w);
		w.setMeasurement(31, 55, 29.2f); // 새 데이터 등록
		o.update(w.getTemperature(), w.getHumidity(), w.getPressure());
		o.setChanged(); o.notifyObserver(w);
	}

}
