import java.util.Iterator;

public class DinerMenuIterator implements Iterator<MenuItem> {
	MenuItem[] list;
	int position = 0;
	
	public DinerMenuIterator(MenuItem[] list) { this.list = list; }

	@Override
	public boolean hasNext() {
		if (position >= list.length || list[position] == null) return false;
		else return true;
	}

	@Override
	public MenuItem next() {
		MenuItem menuItem = list[position];
		position++;
		return menuItem;
	}
	
	@Override
	public void remove() { // 반드시 기능을 제공하지 않아도 됨
		if (position <= 0) throw new IllegalStateException("next()가 한 번도 호출되지 않음");
		if (list[position - 1] != null) {
			for (int i = position - 1; i < list.length - 1; i++) {
				list[i] = list[i + 1];
			}
			
			list[list.length - 1] = null;
		}
	}
	
}
