import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;


public class TreeIteratorBFS implements Iterator<TreeNode> {
	Queue<Iterator<TreeNode>> queue = new ArrayDeque<>();
	public TreeIteratorBFS(Iterator<TreeNode> iterator){
		queue.add(iterator);
	}
	@Override
	public boolean hasNext() {
		if(queue.isEmpty()) return false;
		else{
			Iterator<TreeNode> iterator = queue.peek();
	 		if(iterator.hasNext()) return true;
			else{ 
				queue.poll(); 
				return hasNext(); 
			}
 		} 
	}
	@Override
	public TreeNode next() {
		Iterator<TreeNode> iterator = queue.peek();
		TreeNode node = iterator.next();
		if(node instanceof NonLeaf){
			queue.add(node.iterator());
			if(!iterator.hasNext()) queue.poll();
			return next();
		}
		else return node;
	}
}
