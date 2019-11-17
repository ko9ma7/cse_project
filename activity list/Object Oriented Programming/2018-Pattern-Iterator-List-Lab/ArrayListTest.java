import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ArrayListTest {
	@Test
	void pushBack_popBackTest() {
		ArrayList<Integer> list = new ArrayList<>();
		list.pushBack(10);
		list.pushBack(5);
		list.pushBack(3);
		list.pushBack(7);
		list.pushBack(9);
		list.pushBack(11);
		String output = "";
		while(!list.isEmpty()) {
			output += list.popBack()+",";
		}
		assertEquals(output,"11,9,7,3,5,10,");
	}
	@Test
	void removeTest() {
		ArrayList<Integer> list = new ArrayList<>();
		list.pushBack(10);
		list.pushBack(5);
		list.pushBack(3);
		list.remove(3);
		assertEquals(Integer.valueOf(5),list.peekBack());
		list.remove(10);
		assertEquals(1,list.size());
	}
	@Test
	void iteratorTest() {
		ArrayList<Integer> list = new ArrayList<>();
		list.pushBack(10);
		list.pushBack(5);
		list.pushBack(3);
		String output = "";
		for(var n: list) // 자바 10부터는 var를 사용
			output += n+",";
		assertEquals(3,list.size());
		assertEquals(output,"10,5,3,");
	}

}
