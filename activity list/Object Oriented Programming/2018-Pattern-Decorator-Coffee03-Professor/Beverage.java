import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


public abstract class Beverage{
	private String description = "Unknown Beverage";
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	public abstract int cost();
	public static Beverage createCoffee(String coffee, String... list) 
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<? extends Beverage> decorateeClass 
			= Class.forName(coffee).asSubclass(Beverage.class);
		if(decorateeClass.getSuperclass()!=Beverage.class||decorateeClass==CondimentDecorator.class)
			throw new IllegalArgumentException("Must use Concrete Decoretee");
		Constructor<? extends Beverage> decorateeConstructor = decorateeClass.getConstructor();
		Beverage beverage = (Beverage)decorateeConstructor.newInstance();
		
		Arrays.sort(list);
		// 보다 효과적으로 할 수 있을까? Mocha가 여러 번 사용되면 이 과정이 반복됨
		// Decorator들을 대표하는 CondimentDeocorator 클래스가 없을 경우, 아래처럼 강건하게 작성할 수 없
		for(String s: list){
			Class<? extends CondimentDecorator> decoratorClass 
				= Class.forName(s).asSubclass(CondimentDecorator.class);
			if(decoratorClass.getSuperclass()!=CondimentDecorator.class)
				throw new IllegalArgumentException("Must use decorator which extends Condiment Decorator");
			Constructor<? extends CondimentDecorator> decoratorConstructor 
				= decoratorClass.getConstructor(Beverage.class);
			beverage = (Beverage)decoratorConstructor.newInstance(beverage);
		}
		return beverage;
	}
}
