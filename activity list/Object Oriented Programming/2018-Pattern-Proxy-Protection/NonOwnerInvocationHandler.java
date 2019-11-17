import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 타인이 사용하는 프록시
public class NonOwnerInvocationHandler implements InvocationHandler {
	private PersonBean person;	
	public NonOwnerInvocationHandler(PersonBean person) {
		this.person = person;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException {
		try{
			if(method.getName().startsWith("get")|| // get으로 시작되는 문자열들은 모두 포함
			   method.getName().startsWith("setHotOrNotRating")) // get과 set 이외는 모두 허용
				return method.invoke(person, args);
			else if(method.getName().startsWith("set")) // set 허용 x
				throw new IllegalAccessException();
		}
		catch(InvocationTargetException e){
			e.printStackTrace();
		}
		return null;
	}
}
