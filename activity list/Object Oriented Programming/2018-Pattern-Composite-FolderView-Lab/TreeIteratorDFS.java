import java.util.Iterator;
import java.util.Stack;


public class TreeIteratorDFS implements Iterator<TreeNode> {
	Stack<Iterator<TreeNode>> stack = new Stack<>();
	public TreeIteratorDFS(Iterator<TreeNode> iterator){
		stack.push(iterator);
	}
	@Override
	public boolean hasNext() {
		if(stack.empty()) return false;
		else{
			Iterator<TreeNode> iterator = stack.peek();
	 		if(iterator.hasNext()) return true;
			else{ 
				stack.pop(); 
				return hasNext(); 
			}
 		} 
	}
	@Override
	public TreeNode next() {
		Iterator<TreeNode> iterator = stack.peek();
		TreeNode node = iterator.next();
		if(node instanceof NonLeaf){
			stack.push(node.iterator());
			return next();
		}
		else return node;
	}
}
