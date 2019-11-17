import java.util.Iterator;
import java.util.ListIterator;

public class LinkedList<T> implements Iterable<T> {
	private static class Node<T>{
		private T item = null;
		private Node<T> next = null;
	}
	private class SimpleIterator implements Iterator<T>{
		Node<T> curr = head;
		@Override
		public boolean hasNext() {
			return curr!=null;
		}
		@Override
		public T next() {
			T retval = curr.item;
			curr = curr.next;
			return retval;
		}
	}
	private class ComplexIterator implements ListIterator<T>{
		private Node<T> prev = null;
		private Node<T> curr = head;
		private boolean remove_add_flag = false;
		
		@Override
		public boolean hasNext() {
			return curr!=null;
		}

		@Override
		public T next() {
			
			remove_add_flag = false;
			Node<T> retNode =curr;
			prev = curr;
			curr = curr.next;
			
			return retNode.item;
			
		}

		@Override
		public boolean hasPrevious() {
			throw new UnsupportedOperationException();
		}

		@Override
		public T previous() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void remove() {
			
			if (remove_add_flag) throw new IllegalStateException();
			remove_add_flag = true;
			
			
			if (prev == head) {
				
				head = head.next;
				prev = null;
				curr = head;
				
			} else {
			
				Node<T> pre_prev = head;
				while (pre_prev.next != prev) {
					pre_prev = pre_prev.next;
				}
				pre_prev.next = prev.next;
				prev = pre_prev;
				
			}
			--size;

		}
		
		@Override
		public void set(T e) {
			
			if(remove_add_flag||prev==null) 
				throw new IllegalStateException();
			/*
			Node<T> set = prev;
			set.item = e;
			set.next = curr;
			prev.next = null;
			*/
			prev.item = e;
			
		}
		
		@Override
		public void add(T e) { // 데이터 추가
			
			remove_add_flag = true;
			Node<T> add = new Node<>();
			add.item = e;
			add.next = curr;
			
			if (prev == null) head = add;
			else prev.next = add;
			++size;
			
		}
		
		
	}
	private Node<T> head = null;
	private int size = 0;
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
		return new SimpleIterator();
	}
	public ListIterator<T> listIterator() {
		return new ComplexIterator();
	}
}
