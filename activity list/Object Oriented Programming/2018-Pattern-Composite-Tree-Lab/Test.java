import java.util.Iterator;


public class Test {

	public static void main(String[] args) {
		Node root = new NonLeaf("Root");
		Node sub1 = new NonLeaf("Sub1");
		Node sub2 = new NonLeaf("Sub2");
		Node file1 = new Leaf("a1"); 
		Node file2 = new Leaf("c1");
		file1.setChanged(true);
		file2.setChanged(true);
		root.add(file1);
		root.add(sub1);
		root.add(new Leaf("a2"));
		root.add(sub2);
		sub1.add(new Leaf("b1"));
		sub1.add(new Leaf("b2"));
		sub2.add(file2);
		sub2.add(new Leaf("c2"));
		sub2.add(new Leaf("c3"));
		
		
		System.out.println(root.list());
		
		for(Node node: root){ // for-each문  사용
			System.out.println(node.getName());
		}
		
		/*
		for(Node node: root){ // 반복자로 사용
			if(node.hasChanged()) System.out.println(node.getName());
		}
		*/
		
	}

}
