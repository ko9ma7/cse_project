
public class Test {

	public static void main(String[] args) {
		Node value1 = new ValueNode(1);
		Node value2 = new ValueNode(2);
		Node value3 = new ValueNode(3);
		Node multNode = new MultiplyNode(value2,value3);
		Node addNode = new AddNode(value1,multNode);
		System.out.println(addNode.evaluate());
	}

}
