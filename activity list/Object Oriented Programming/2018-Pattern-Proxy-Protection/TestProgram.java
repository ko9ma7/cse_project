import java.lang.reflect.Proxy;


public class TestProgram {
	// owner 프록시를 만드는 코드
	public static PersonBean getOwnerProxy(PersonBean person){
		return (PersonBean)Proxy.newProxyInstance(
				person.getClass().getClassLoader(),
				person.getClass().getInterfaces(),
				new OwnerInvocationHandler(person));
	}
	// owner가 아닌 프록시를 만드는 코드
	/*
	public static PersonBean getNonOwnerProxy(PersonBean person){
		return (PersonBean)Proxy.newProxyInstance(
				person.getClass().getClassLoader(),
				person.getClass().getInterfaces(),
				new NonOwnerInvocationHandler(person));
	}
	*/
	public static void main(String[] args) {
		PersonBean sangjin = new PersonBeanImpl();
		PersonBean ownerProxy = getOwnerProxy(sangjin);
		try{
			ownerProxy.setName("김상진");
			ownerProxy.setGender(PersonBean.Gender.MALE);
			ownerProxy.setInterest("음악");
			//ownerProxy.setHotOrNotRating(10);
		}
		catch(Exception e){
			System.out.println("본인의 평판을 설정할 수 없음");
		}
		/*
		PersonBean nonOwnerProxy = getNonOwnerProxy(sangjin);
		try{
			nonOwnerProxy.setHotOrNotRating(10);
			nonOwnerProxy.setInterest("축구");
		}
		catch(Exception e){
			System.out.println("다른 사용자의 관심사항을 수정할 수 없음");
		}
		*/
	}
}
