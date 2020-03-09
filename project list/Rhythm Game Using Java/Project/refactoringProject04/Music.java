package refactoringProject04;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private String path = "/2018-Pattern-SoftWareEngineering_Project-RhythmGame/src/music/";
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	private String titleImage; // 게임 시작 전 앨범 이름 이미지
	private String startImage; // 게임 시작 전 앨범 이미지
	private String gameImage;  // 게임 시작 배경 이미지
	private String startMusic; // 곡 선택 전에 미리 듣기
	private String gameMusic;  // 곡 선택 후 게임 시작 배경 곡
	private String titleName;  // 곡의 제목
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(path + name);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {
		if (player == null) return 0;
		return player.getPosition();
	}
	
	public void close() { // 음악 종료
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() {
		try {
			do { 
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
