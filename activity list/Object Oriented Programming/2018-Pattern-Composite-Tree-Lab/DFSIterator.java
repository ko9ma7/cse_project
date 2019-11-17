import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Stack;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Composite Pattern
 * TreeIteratorDFS.java
 * 깊이 우선 탐색 반복자: Stack 이용
 * 각 노드의 반복자를 이용하지 않음
 * @author 김상진
 */
public class DFSIterator implements Iterator<Node> {
	Stack<Node> stack = new Stack<>();
	public DFSIterator(Node node){
		stack.push(node);
	}
	@Override
	public boolean hasNext() {
		if(stack.isEmpty()) return false;
		else{
			Node node = stack.peek();
			if(node instanceof NonLeaf) return true;
			else{ 
				stack.pop();
				return true;
			}
 		}
	}
	@Override
	public Node next() {
		
		Node node = stack.peek();
		stack.pop();
		return node;
		
		/*
		Node node = stack.pop();
		if(node instanceof NonLeaf){
			stack.push(node);
			return next();
		}
		else return node;
		*/
	}
}
