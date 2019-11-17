import java.util.ArrayList;

/*
 *  1. printMenu() - 메뉴에 있는 모든 항목을 출력
 *  2. printBreakfastMenu() - 아침 식사 항목만 출력
 *  3. printLunchMenu() - 점심 식사 항목만 출력
 *  4. printVegetarianMenu() - 채식주의자용 메뉴 항목만 출력
 *  5. isItemVegetarian(name) - name 항목이 채식주의자용이면 true, 그렇지 않으면 false
 */

// 첫 번째 방법 - 반복적인 for문 사용
public class MenuTest {

	public static void main(String[] args) {
		PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
		ArrayList<MenuItem> breakfastItems = pancakeHouseMenu.getMenuItem();
		
		DinerMenu dinerMenu = new DinerMenu();
		MenuItem[] lunchItems = dinerMenu.getMenuItem();
		
		for (int i = 0; i < breakfastItems.size(); i++) {
			MenuItem menuItem = breakfastItems.get(i);
			System.out.println(menuItem.getName());
			System.out.println(menuItem.getDescription());
			System.out.println(menuItem.getPrice());
			System.out.println(menuItem.isVegetarian());
		}
		
		for (int i = 0; i < lunchItems.length; i++) {
			MenuItem menuItem = lunchItems[i];
			System.out.println(menuItem.getName());
			System.out.println(menuItem.getName());
			System.out.println(menuItem.getPrice());
			System.out.println(menuItem.isVegetarian());
		}
	}

}
