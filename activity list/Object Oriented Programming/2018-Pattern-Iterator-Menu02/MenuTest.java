import java.util.ArrayList;
import java.util.Iterator;

/*
 *  1. printMenu() - 메뉴에 있는 모든 항목을 출력
 *  2. printBreakfastMenu() - 아침 식사 항목만 출력
 *  3. printLunchMenu() - 점심 식사 항목만 출력
 *  4. printVegetarianMenu() - 채식주의자용 메뉴 항목만 출력
 *  5. isItemVegetarian(name) - name 항목이 채식주의자용이면 true, 그렇지 않으면 false
 */

// 두 번째 방법 - Iterator 인터페이스 사용
public class MenuTest {

	public static void main(String[] args) {
		ArrayList<Menu> menuList = new ArrayList<>();
		menuList.add(new PancakeHouseMenu());
		menuList.add(new DinerMenu());
		
		Waitress waitress = new Waitress(menuList);
		waitress.printMenu();
	}

}
