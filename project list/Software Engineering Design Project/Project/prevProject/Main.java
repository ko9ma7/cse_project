package prevProject;

public class Main {
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	public static final int NOTE_SPEED = 7; // 노트가 떨어지는 속도
	public static final int SLEEP_NOTE = 3; // 떨어지는 주기
	
	public static final int REACH_TIME = 1; // 노트가 판정라인까지 떨어지는데 도달하는 시간
	
	public static void main(String[] args) {
		new RhythmGame();
	}

}