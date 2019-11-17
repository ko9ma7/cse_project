import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Composite Pattern
 * TreeIteratorBFS.java
 * 너비 우선 탐색 반복자: 큐 이용
 * 각 노드의 반복자를 이용함
 * @author 김상진
 */
public class TreeIteratorBFS implements Iterator<Node> {
	Queue<Iterator<Node>> queue = new ArrayDeque<>();
	public TreeIteratorBFS(Iterator<Node> iterator){
		queue.add(iterator);
	}
	@Override
	public boolean hasNext() {
		if (queue.isEmpty()) return false;
		else {
			Iterator<Node> iterator = queue.peek();
			if (iterator.hasNext()) return true;
			else {
				queue.poll();
				return hasNext();
			}
		}
	}
	@Override
	public Node next() {
		Iterator<Node> iterator = queue.peek();
		Node node = iterator.next();
		if (node instanceof NonLeaf) {
			queue.add(node.iterator());
			if (!iterator.hasNext()) queue.poll();
			return node;
		}
		else return node;
	}
}
