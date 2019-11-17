import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 자신만이 사용하는 프록시
public class OwnerInvocationHandler implements InvocationHandler {
	private PersonBean person;	
	public OwnerInvocationHandler(PersonBean person) {
		this.person = person;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException {
		try{
			if(method.getName().startsWith("get")) // get 허용
				return method.invoke(person, args);
			else if(method.getName().startsWith("setHotOrNotRating")) // get, set 이외의 모두 허용 x
				throw new IllegalAccessException();
			else if(method.getName().startsWith("set")) // set 허용
				return method.invoke(person, args);
		}
		catch(InvocationTargetException e){
			e.printStackTrace();
		}
		return null;
	}

}
