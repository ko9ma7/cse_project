import java.util.ArrayList;

public class Observable implements Observer {

	private ArrayList<Observer> observers = new ArrayList<>();
	private boolean changed = false;
	
	public void addObserver(Observer o) { observers.add(o); }
	public void deleteObserver(Observer o) { observers.remove(o); }
	public void setChanged() { changed = true; } // 정보가 새로 들어왔는지 확인
	public void notifyObserver(WeatherData w) { 
		
		if (changed) {
			
			observers.forEach(o->o.update(w.getTemperature(), w.getHumidity(), w.getPressure())); 
			changed = false;

		} 
		
	}
	
	@Override
	public void update(float temperature, float humidity, float pressure) {
		// TODO Auto-generated method stub
		
	} 
	
}
