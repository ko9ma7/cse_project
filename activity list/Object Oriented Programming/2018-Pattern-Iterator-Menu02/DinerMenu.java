import java.util.Iterator;

public class DinerMenu implements Menu {
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;
	MenuItem[] menuItems;
	
	public DinerMenu() {
		this.menuItems = new MenuItem[MAX_ITEMS];
	}
	
	public void addItem(String name, String description, boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		
		if (numberOfItems >= MAX_ITEMS) {
			System.out.println("죄송합니다. 메뉴가 꽉 찼습니다., 더 이상 추가할 수 없습니다.");
		} else {
			menuItems[numberOfItems] = menuItem;
			numberOfItems = numberOfItems + 1;
		}
	}
	
	public MenuItem[] getMenuItem() { return menuItems; }

	@Override
	public Iterator<MenuItem> createIterator() {
		// TODO Auto-generated method stub
		return new DinerMenuIterator(menuItems);
	}
}
