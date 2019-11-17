import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Composite Pattern
 * BFSIterator.java
 * 너비 우선 탐색 반복자: 큐 이용
 * 각 노드의 반복자를 이용하지 않음
 * @author 김상진
 */
public class BFSIterator implements Iterator<Node> {
	Queue<Node> queue = new ArrayDeque<>();
	public BFSIterator(Node node){ // 노드를 입력한다
		queue.add(node);
	}
	@Override
	public boolean hasNext() {
		if(queue.isEmpty()) return false;
		else{
			Node node = queue.peek();
	 		if(node instanceof NonLeaf) {
	 			for(int i=0; i<node.numberOfChilds(); i++) {
	 				queue.add(node.getChild(i));
	 			}
	 		}
	 		return true;
 		} 
	}
	@Override
	public Node next() { // Leaf Node들만 출력될 수 있도록, 중간 노드들은 출력 안됨
		Node node = queue.peek();
		queue.poll();
		return node;
	}
}
