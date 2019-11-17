import java.util.ArrayList;
import java.util.Iterator;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Composite Pattern
 * NonLeaf.java
 * 자식이 있는 중간 노드
 * @author 김상진
 */
public class NonLeaf extends Node {
	private ArrayList<Node> childs = new ArrayList<>();  // 자식을 유지 가능함
	public NonLeaf(String name){
		super(name); 
	}
	@Override
	public int numberOfChilds(){
		return childs.size();
	}
	@Override
	public void add(Node node) {
		childs.add(node);
	}
	@Override
	public void remove(Node node) {
		childs.remove(node);
	}
	@Override
	public Node getChild(int index) {
		if(index>=0&&index<childs.size())
			return childs.get(index);
		else throw new IndexOutOfBoundsException("해당 색인에 해당되는 노드가 없음");
	}
	@Override
	public Iterator<Node> iterator() {
		//return new TreeIteratorDFS(childs.iterator());
		//return new TreeIteratorBFS(childs.iterator());
		//return new BFSIterator(this);
		return new DFSIterator(this);
	}
	@Override
	public String list() {
		String output = getName()+"\n";
		indent += "     ";
		for(Node node: childs){
			output += indent + node.list();
		}
		indent = indent.substring(0,indent.length()-5);
		return output;
	}

}
