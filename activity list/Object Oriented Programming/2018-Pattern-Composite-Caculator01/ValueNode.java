/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Composite Pattern
 * ValueNode.java
 * 값만 가지는 단말 노드
 * @author 김상진
 */
public class ValueNode extends Node {
	private double value;
	public ValueNode(double value) {
		this.value = value;
	}
	@Override
	public double evaluate() {
		return value;
	}

}
