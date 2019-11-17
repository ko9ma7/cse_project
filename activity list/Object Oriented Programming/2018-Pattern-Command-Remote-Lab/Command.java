/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: 만능 리모콘 예제
 * Command Interface
 * @author 김상진
 *
 */
public interface Command {
	void execute();
	void undo();
	// default boolean undoable() { return true; }
}
