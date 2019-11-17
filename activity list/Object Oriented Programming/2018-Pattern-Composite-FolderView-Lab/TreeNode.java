import java.util.Iterator;

public abstract class TreeNode implements Iterable<TreeNode>{
	private String name;
	public TreeNode(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void add(TreeNode node) {
		throw new UnsupportedOperationException();
	}
	public void remove(TreeNode node) {
		throw new UnsupportedOperationException();
	}
	public TreeNode getChild(int index) {
		throw new UnsupportedOperationException();
	}
	public int numberOfChilds(){
		return 0;
	}
	public TreeNode getRearranged(){
		throw new UnsupportedOperationException();
	}
	public Iterator<TreeNode> iterator() {
		return new NullIterator();
	}
}