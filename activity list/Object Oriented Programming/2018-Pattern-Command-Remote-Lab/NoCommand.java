/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: 만능 리모콘 예제
 * NoCommand: Null 객체
 * @author 김상진
 * 
 */
public enum NoCommand implements Command { // 싱글톤 방식
	UNIQUE;
	@Override
	public void execute() {}
	@Override
	public void undo() {}
}
/*
public class NoCommand implements Command {
	@Override
	public void execute() {}
	@Override
	public void undo() {}
}
*/