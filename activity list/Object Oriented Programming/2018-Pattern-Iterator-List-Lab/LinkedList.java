import java.util.Iterator;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * LinkedList.java
 * 범용 List ADT: 단일 연결구조 with no tail
 * @author sangjin
 */
// 용량 걱정 없음
public class LinkedList<T> implements Iterable<T> {
	/*
	 *  static 내부 클래스 VS non-static 내부 클래스
	 *  (정적 내부 클래스는 클래스명으로도 접근이 가능하다. 그래서 바깥에 객체가 없어도 객체를 만들 수 있다.)
	 *  (비정적 내부 클래스는 내부 클래스를 둘러싼 바깥 클래스의 객체가 있어야 객체를 생성할 수 있다.)
	 *  class A 내부에 class B가 있으면, 내부클래스의 객체는 바깥 클래스의 포인터를 유지함
	 */
	private static class Node<T>{
		private T item = null;
		private Node<T> next = null;
	}
	// 단일 연결 구조에서의 Iterator
	private class LinkedListIterator implements Iterator<T>{
		Node<T> curr = head; // 현재 노드, 헤드를 가리킴
		
		@Override
		public boolean hasNext() {
			return curr != null;
		}
		@Override
		public T next() {
			T retval = curr.item; // 값을 저장하고
			curr = curr.next; // 다음 노드로 이동
			return retval;
		}
	}
	private Node<T> head = null;
	private int size = 0;
	public boolean isFull() {
		return false;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public int size() {
		return size;
	}
	public void pushFront(T item) {
		Node<T> newNode = new Node<>();
		newNode.item = item;
		newNode.next = head;
		head = newNode;
		++size;
	}
	public T popFront() {
		if(isEmpty()) throw new IllegalStateException();
		Node<T> popNode = head;
		head = head.next;
		--size;
		return popNode.item;
	}
	public T peekFront() {
		if(isEmpty()) throw new IllegalStateException();
		return head.item;
	}
	public boolean find(T item) {
		if(isEmpty()) return false;
		Node<T> curr = head;
		while(curr!=null) {
			if(curr.item.equals(item)) return true;
			curr = curr.next;
		}
		return false;
	}
	public void remove(T item) {
		if(isEmpty()) return;
		Node<T> dummy = new Node<>();
		dummy.next = head;
		Node<T> prev = dummy;
		Node<T> curr = head;
		while(curr!=null) {
			if(curr.item.equals(item)) {
				prev.next = curr.next;
				--size;
				break;
			}
			prev = curr;
			curr = curr.next;
		}
		head = dummy.next;
	}
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

}
