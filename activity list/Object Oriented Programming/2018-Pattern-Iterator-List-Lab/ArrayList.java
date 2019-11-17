import java.util.Arrays;
import java.util.Iterator;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * ArrayList.java
 * 범용 List ADT: 동적 배열 기법 사용
 * @author sangjin
 */
// 용량 고정이 됨
// for-each 구문을 사용하기 위해 Iterable<T> 선언
public class ArrayList<T> implements Iterable<T> {
	private int capacity = 5;
	private Object[] list = new Object[capacity];
	private int size = 0;
	
	private class ArrayListIterator implements Iterator<T>{ // 내부 클래스로 구현되어 있음
		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < size;
		}
		@Override
		public T next() {
			++index;
			return elementData(index - 1);
		}
	}
	
	public boolean isFull(){
		return false;
	}
	public boolean isEmpty(){
		return size==0;
	}
	public int size() {
		return size;
	}
	@SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T)list[index];
    }
	public T peekBack() {
		if(isEmpty()) throw new IllegalStateException();
		return elementData(size-1);
	}
	public void pushBack(T item){
		if(size==capacity) {
			capacity *= 2;
			list = Arrays.copyOf(list, capacity);
		}
		list[size] = item;
		++size;
	}
	public T popBack() {
		if(isEmpty()) throw new IllegalStateException();
		T retval = elementData(size-1);
		--size;
		return retval;
	}
	public T get(int index){
		if(index>=0 && index<size) return elementData(index);
		else throw new IndexOutOfBoundsException("유효하지 않은 색인 사용");
	}
	public void remove(T item) {
		if(isEmpty()) throw new IllegalStateException();
		for(int i=0; i<size; i++)
			if(elementData(i).equals(item)) {
				list[i] = list[size-1];
				--size;
				break;
			}
	}
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

}

// for(Integer n : list) System.out.println(n); -> 사용 가능해짐