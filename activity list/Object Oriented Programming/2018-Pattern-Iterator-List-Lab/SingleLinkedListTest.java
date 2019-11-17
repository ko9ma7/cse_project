import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SingleLinkedListTest {
	@Test
	void pushFront_popFrontTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.pushFront(10);
		list.pushFront(5);
		list.pushFront(3);
		String output = "";
		while(!list.isEmpty()) {
			output += list.popFront()+",";
		}
		assertEquals(output,"3,5,10,");
	}
	@Test
	void removeTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.pushFront(10);
		list.pushFront(5);
		list.pushFront(3);
		list.remove(3);
		assertEquals(Integer.valueOf(5),list.peekFront());
		list.remove(10);
		assertEquals(1,list.size());
	}
	@Test
	void iteratorTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.pushFront(10);
		list.pushFront(5);
		list.pushFront(3);
		String output = "";
		for(var n: list)
			output += n+",";
		assertEquals(3,list.size());
		assertEquals(output,"3,5,10,");
	}
	
}
