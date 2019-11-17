import java.util.Iterator;
import java.util.Stack;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Composite Pattern
 * TreeIteratorDFS.java
 * 깊이 우선 탐색 반복자: Stack 이용
 * 각 노드의 반복자를 이용
 * @author 김상진
 */
public class TreeIteratorDFS implements Iterator<Node> {
	Stack<Iterator<Node>> stack = new Stack<>();
	public TreeIteratorDFS(Iterator<Node> iterator){
		stack.push(iterator);
	}
	@Override
	public boolean hasNext() {
		if(stack.empty()) return false;
		else{
			Iterator<Node> iterator = stack.peek();
	 		if(iterator.hasNext()) return true;
			else{ 
				stack.pop(); 
				return hasNext(); 
			}
 		} 
	}
	@Override
	public Node next() {
		Iterator<Node> iterator = stack.peek();
		Node node = iterator.next();
		if(node instanceof NonLeaf){
			stack.push(node.iterator());
			return next();
		}
		else return node;
	}
}
