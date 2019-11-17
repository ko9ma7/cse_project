import static org.junit.jupiter.api.Assertions.*;

import java.util.ListIterator;

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
		for(var n: list)
			output += n+",";
		assertEquals(3,list.size());
		assertEquals(output,"10,5,3,");
	}
	@Test
	void listIterator_setTest() {
		ArrayList<Integer> list = new ArrayList<>();
		list.pushBack(10);
		list.pushBack(5);
		list.pushBack(3);
		ListIterator<Integer> it = list.listIterator();
		it.next();
		it.set(4);
		assertEquals(Integer.valueOf(5),it.next());
		String output = "";
		for(var n: list)
			output += n+",";
		assertEquals(3,list.size());
		assertEquals(output,"4,5,3,");
	}
	@Test
	void listIterator_addTest() {
		ArrayList<Integer> list = new ArrayList<>();
		list.pushBack(10);
		list.pushBack(5);
		list.pushBack(3);
		ListIterator<Integer> it = list.listIterator();
		it.next();
		it.add(4);
		assertEquals(Integer.valueOf(5),it.next());
		String output = "";
		for(var n: list)
			output += n+",";
		assertEquals(4,list.size());
		assertEquals(output,"10,4,5,3,");
	}
	@Test
	void listIterator_removeTest() {
		ArrayList<Integer> list = new ArrayList<>();
		list.pushBack(10);
		list.pushBack(5);
		list.pushBack(3);
		ListIterator<Integer> it = list.listIterator();
		it.next();
		it.remove();
		assertEquals(Integer.valueOf(5),it.next());
		it.next();
		it.remove();
		String output = "";
		for(var n: list)
			output += n+",";
		assertEquals(1,list.size());
		assertEquals(output,"5,");
	}
}
