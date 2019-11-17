import java.util.Iterator;


public class NullIterator implements Iterator<TreeNode> {
	@Override
	public boolean hasNext() {
		return false;
	}
	@Override
	public TreeNode next() {
		throw new UnsupportedOperationException("이것 호출되면 곤란");
	}
}
