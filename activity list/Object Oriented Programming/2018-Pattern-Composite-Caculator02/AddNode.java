/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Composite Pattern
 * AddNode.java
 * 자식을 가지는 연산자 노드 중 덧셈 노드
 * @author 김상진
 */
public class AddNode extends OperatorNode {

	public AddNode(Node left, Node right) {
		super(left, right);
	}

	@Override
	public double evaluate() {
		return left.evaluate()+right.evaluate();
	}

}
