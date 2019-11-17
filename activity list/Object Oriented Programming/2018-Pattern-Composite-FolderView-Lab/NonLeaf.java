import java.util.ArrayList;
import java.util.Iterator;

public class NonLeaf extends TreeNode {
	private ArrayList<TreeNode> childs = new ArrayList<>(); 
	public NonLeaf(String name){
		super(name); 
	}
	@Override
	public void add(TreeNode node) {
		childs.add(node);
	}
	@Override
	public void remove(TreeNode node) {
		childs.remove(node);
	}
	@Override
	public TreeNode getChild(int index) {
		if(index>=0&&index<childs.size())
			return childs.get(index);
		else throw new IndexOutOfBoundsException("주어진 색인에 해당되는 노드가 없음");
	}
	@Override
	public int numberOfChilds(){
		return childs.size();
	}
	
	// 이 메소드는 폴더와 파일 순으로 보여주고, 폴더간에는 또는 파일간에는 이름 순으로 보여주도록 함. 이때 실제 자식 목록을 수정하지 않고 동일된 노드이지만
	// ArrayList에 저장된 자식 노드들의 순서가 바뀐 노드를 반환하여야 함
	@Override
	public NonLeaf getRearranged(){
		NonLeaf node = new NonLeaf(getName());
		node.childs.addAll(childs);
		node.childs.sort((a,b)->{
			if(a instanceof NonLeaf && b instanceof Leaf) return -1;
			else if(a instanceof Leaf && b instanceof NonLeaf) return 1;
			else return a.getName().compareTo(b.getName());
		});
		return node;
	}
	@Override
	public Iterator<TreeNode> iterator() {
		//return new TreeIteratorDFS(childs.iterator());
		return new TreeIteratorBFS(childs.iterator());
	}
}
