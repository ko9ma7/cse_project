/* 
	1. 고전적인 싱글톤패턴 구현법
	
public class Singleton {
	// 인스턴스를 저장하기 위한 변수
	// 외부에서 직접 호출할 수 없도록 private으로 선언
	private static Singleton uniqueInstance;
	
	// 생성자도 private으로 선언하여 외부에서 호출할 수 없도록 함
	private Singleton() {}
	
	// 클래스의 유일한 인스턴스를 반환하는 메소드
	public static Singleton getInstance() {
		// 인스턴스가 존재하지 않는다면 생성
		if (uniqueInstance == null) uniqueInstance = new Singleton();
		
		return uniqueInstance;
	}
}
*/
/*	
 	2. 멀티스레드 프로그램에서 싱글톤패턴 구현법
	2-1. synchronized 이용
	
public class Singleton {
	private static Singleton uniqueInstance;
	
	public Singleton() {}
	
	// synchronized 추가
	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null) uniqueInstance = new Singleton();
		
		return uniqueInstance;
	} 
}
*/
/*
 	2-2. 정적 초기화 시 생성
 	
public class Singleton {
	// 정적 초기화 시 인스턴스 생성
	private static Singleton uniqueInstance = new Singleton();
	
	private Singleton() {}
	
	public static synchronized Singleton getInstance() {
		return uniqueInstance;
	}
}
*/
/*
 	2-3. DCL(Double-checking Locking)

public class Singleton {
	// volatile 추가
	private volatile static Singleton uniqueInstance;
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		if (uniqueInstance == null) { // 인스턴스가 있는지 확인
			synchronized (Singleton.class) { // 최초에만 동기화
				if (uniqueInstance == null) { // 다시 한번 확인
					uniqueInstance = new Singleton();
				}
			}
		}
		
		return uniqueInstance;
	}
}
*/
/*
	2-4. Initialization on demand holder idiom
	
public class Singleton {
	private Singleton() {}
	
	private static class LazyHolder {
		static final Singleton INSTANCE = new Singleton();
	}
	
	public static Singleton getInstance() {
		return LazyHolder.INSTANCE;
	}
}
*/
/*
	2-5. enum initialization
	
public enum Singleton {
	INSTANCE;
	public static Singleton getInstance() {
		return INSTANCE;
	}
}
*/