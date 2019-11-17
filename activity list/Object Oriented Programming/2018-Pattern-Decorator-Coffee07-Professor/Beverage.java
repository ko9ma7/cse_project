import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
	// 장식대상과 장식자들의 생성자를 protected로 한 이유는?
	// protected인 경우 생성자를 가지고 올 때는 getConstructor 대신에 getDeclaredConstructor 사용
	// reflection 사용하면 접근권한을 우회할 수 있음
	public static Beverage createCoffee(String coffee, String... list) 
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<? extends Beverage> decorateeClass 
			= Class.forName(coffee).asSubclass(Beverage.class);
		if(decorateeClass.getSuperclass()!=Beverage.class||decorateeClass==CondimentDecorator.class)
			throw new IllegalArgumentException("Must use Concrete Decoretee");
		Constructor<? extends Beverage> decorateeConstructor = decorateeClass.getDeclaredConstructor();
		//decorateeConstructor.setAccessible(true);
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
				= decoratorClass.getDeclaredConstructor(Beverage.class);
			// decoratorConstructor.setAccessible(true);
			Beverage tmp = (Beverage)decoratorConstructor.newInstance(beverage);
			Method check = decoratorClass.getMethod("doubleAllowed");
			if(!(Boolean)check.invoke(tmp) && 
				beverage.getDescription().contains(decoratorClass.getName()))
				throw new IllegalArgumentException("You cannot use multiple "+decoratorClass.getName());
			else beverage = tmp;
		}
		return beverage;
	}
	public boolean doubleAllowed() {
		return false;
	}
}
