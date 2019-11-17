import java.util.HashMap;

public class Multiton {
	
	private static final HashMap<String, Multiton> registry = new HashMap<>();
	private static final int numberOfInstance = 3;
	public static Multiton getInstance(String key) {
		
		if (registry.containsKey(key)) return registry.get(key);
		
		else { 
			if (registry.size() < numberOfInstance) {
				Multiton o = new Multiton();
				registry.put(key, o);
				return o;
			}
			else throw new IllegalArgumentException("");
		}
	}

}
