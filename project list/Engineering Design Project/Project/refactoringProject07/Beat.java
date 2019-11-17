package refactoringProject07;

// 노트의 종류와 박자를 다룰 수 있는 클래스
public class Beat {
	private int time;
	private String noteName;
	
	public Beat(int time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
}
