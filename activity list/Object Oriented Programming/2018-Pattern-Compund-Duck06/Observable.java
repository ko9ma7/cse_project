import java.util.ArrayList;

public class Observable implements QuackObservable {
	private ArrayList<Observer> observers = new ArrayList<>();
	private QuackObservable duck;
	public Observable(QuackObservable duck){
		this.duck = duck;
	}
	@Override
	public void registerObserver(Observer observer){
		observers.add(observer);
	}
	@Override
	public void notifyObservers(){
		for(Observer o: observers)
			o.update(duck);
	}


}
