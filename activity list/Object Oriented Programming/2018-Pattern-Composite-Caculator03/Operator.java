/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Composite Pattern
 * Operator.java
 * 연산의 종류를 정의하는 열거형
 * @author 김상진
 */
public enum Operator {
	ADD{
		@Override
		public double evaluate(Node left, Node right) {
			return left.evaluate()+right.evaluate();
		}
	},
	SUBTRACT{
		@Override
		public double evaluate(Node left, Node right) {
			return left.evaluate()-right.evaluate();
		}
	},
	MULTIPLY{
		@Override
		public double evaluate(Node left, Node right) {
			return left.evaluate()*right.evaluate();
		}
	},
	DIVIDE{
		@Override
		public double evaluate(Node left, Node right) {
			return left.evaluate()/right.evaluate();
		}
	};
	public abstract double evaluate(Node left, Node right);
}
