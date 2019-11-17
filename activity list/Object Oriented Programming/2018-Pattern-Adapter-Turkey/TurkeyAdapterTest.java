
public class TurkeyAdapterTest {
	public static void duckTest(Duck duck) {
		duck.quack();
		duck.fly();
	}
	public static void main(String[] args) {
		MallardDuck duck = new MallardDuck();
		WildTurkey turkey = new WildTurkey();
		Duck turkeyObjectAdapter = new TurkeyObjectAdapter(turkey);
//		Duck turkeyClassAdapter = new TurkeyClassAdapter();
//		Duck turkey2WayAdapter = new TurkeyTwoWayAdapter(turkey);
		duckTest(duck);
		duckTest(turkeyObjectAdapter);
//		duckTest(turkeyClassAdapter);
//		duckTest(turkey2WayAdapter);
	}

}
