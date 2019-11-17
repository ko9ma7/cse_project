import java.util.Iterator;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Composite Pattern, Null Object Pattern
 * NullIterator
 * 반복자
 * @author 김상진
 */
public class NullIterator implements Iterator<Node> {
	@Override
	public boolean hasNext() {
		return false;
	}
	@Override
	public Node next() {
		throw new UnsupportedOperationException("이것 호출되면 곤란");
	}
}
