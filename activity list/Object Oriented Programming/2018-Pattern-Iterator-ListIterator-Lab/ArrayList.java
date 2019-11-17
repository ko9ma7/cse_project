import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class ArrayList<T> implements Iterable<T> {
	private int capacity = 5;
	private Object[] list = new Object[capacity];
	private int size = 0;
	private class SimpleIterator implements Iterator<T>{
		private int index = 0;
		@Override
		public boolean hasNext() {
			return index<size;
		}
		@Override
		public T next() {
			++index;
			return elementData(index-1);
		}
	}
	private class ComplexIterator implements ListIterator<T>{
		private int index = 0;
		private boolean remove_add_flag = false;
		@Override
		public boolean hasNext() {
			return index<size;
		}
		@Override
		public T next() {
			remove_add_flag = false;
			T retval = elementData(index);
			++index;
			return retval;
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
			if(remove_add_flag) throw new IllegalStateException();
			remove_add_flag = true;
			for(int i=index; i<size; i++)
				list[i-1] = list[i];
			--index;
			--size;
		}
		@Override
		public void set(T e) {
			if(remove_add_flag||index==0) 
				throw new IllegalStateException();
			list[index-1] = e;
		}
		@Override
		public void add(T e) {
			remove_add_flag = true;
			for(int i=size; i>index; i--)
				list[i] = list[i-1];
			list[index] = e;
			++index;
			++size;
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
		return new SimpleIterator();
	}
	public ListIterator<T> listIterator() {
		return new ComplexIterator();
	}
}
