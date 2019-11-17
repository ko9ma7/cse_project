import java.util.Iterator;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Composite Pattern
 * Node.java
 * 트리 노드를 나타내는 추상 타입
 * @author 김상진
 */
public abstract class Node implements Iterable<Node>{
	public static String indent = "";
	private String name;
	private boolean hasChanged = false;
	public Node(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public boolean hasChanged(){
		return hasChanged;
	}
	public void setChanged(boolean flag){
		hasChanged = flag;
	}
	public int numberOfChilds(){
		return 0;
	}
	public abstract String list();
	public abstract void add(Node node);
	public abstract void remove(Node node);
	public abstract Node getChild(int index);
	public abstract Iterator<Node> iterator();
}
