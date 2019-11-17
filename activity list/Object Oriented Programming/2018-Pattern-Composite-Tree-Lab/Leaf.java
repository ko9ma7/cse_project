import java.util.Iterator;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Composite Pattern
 * Leaf.java
 * 단말 노드 처리
 * 이 클래스에 정의된 모든 메소드는 상위 클래스에 정의할 수도 있음
 * @author 김상진
 */
public class Leaf extends Node {
	public Leaf(String name) { 
		super(name);
	}
	@Override
	public void add(Node node) {
		throw new UnsupportedOperationException("단말노드");
	}
	@Override
	public void remove(Node node) {
		throw new UnsupportedOperationException("단말노드");
	}
	@Override
	public Node getChild(int index) {
		throw new UnsupportedOperationException("단말노드");
	}
	@Override
	public Iterator<Node> iterator() {
		return new NullIterator();
	}
	@Override
	public String list() {
		return getName()+"\n";
	}

}
